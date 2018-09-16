package com.trade.bot.data.decisionmaker;

import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.trade.bot.util.DateUtil;
import org.jetbrains.annotations.NotNull;

import com.trade.bot.CandleStickData;
import com.trade.bot.CommercialDecision;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.logging.LoggerProvider;

/**
 * @author Ozan Ay
 */
public class HullMovingAverageDecisionMaker extends CommercialDecisionMakerBase {
    private static final Logger logger = LoggerProvider.getLogger(HullMovingAverageDecisionMaker.class.getName());
    private static final String NO_TRADE_LOG_MESSAGE = "NO TRADE!";
    private static final String BAR_CHANGED_LOG_MESSAGE = "Current candle stick bar is changed.";
    private static final String STARS_FOR_LOG = "***************************************************************************";
    private final Indicator indicator;
    private final TradeClient tradeClient;
    private final TradeSymbol tradeSymbol;
    private final TradeClientCandleStickInterval candleStickInterval;
    private CommercialDecision lastDecision = CommercialDecision.NONE;
    private AtomicBoolean isRunning = new AtomicBoolean();
    private Date closeTimeOfCurrentCandleStick;

    HullMovingAverageDecisionMaker(Indicator indicator, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                   TradeClientCandleStickInterval candleStickInterval) {
        this.indicator = indicator;
        this.tradeClient = tradeClient;
        this.tradeSymbol = tradeSymbol;
        this.candleStickInterval = candleStickInterval;
    }
    
    @Override
    public void run() {
        while (isRunning.get()) {
            TradeData lastData = tradeClient.getLastTradeData();
            if (lastData != null) {
                decide(lastData);
            }
        }
    }

    @Override
    public void start() {
        if (!isRunning.get()) {
            isRunning.set(true);
        }

        tradeClient.subscribeTradeEvent(tradeSymbol);
    }

    @Override
    public void stop() {
        if (isRunning.get()) {
            isRunning.set(false);
        }
    }

    @Override
    void decide(TradeData tradeData) {
        if (isCurrentCandleStickChanged(tradeData)) {
            logger.info(BAR_CHANGED_LOG_MESSAGE);
            List<CandleStickData> allCandleStickDataList = getAllCandleStickDataList();

            List<TradeData> closingTradeDataListTillTwoPreviousBar = mapClosingTradeDataListTillTwoPreviousBar(allCandleStickDataList);
            int previousHullMovingAverage = applyIndicator(closingTradeDataListTillTwoPreviousBar);

            List<TradeData> closingTradeDataListTillPreviousBar = mapClosingTradeDataListTillPreviousBar(allCandleStickDataList);
            int hullMovingAverage = applyIndicator(closingTradeDataListTillPreviousBar);

            tradeIfLatestOrderChanged(tradeData, previousHullMovingAverage, hullMovingAverage);

            updateCloseTimeOfCurrentCandleStick(allCandleStickDataList);
        }
    }

    private static void logHMAs(int previousHullMovingAverage, int hullMovingAverage) {
        logger.log(Level.INFO, "HMA[1]: {0}", hullMovingAverage);
        logger.log(Level.INFO, "HMA[2]: {0}", previousHullMovingAverage);
    }

    private static void logPrice(TradeData tradeData) {
        logger.log(Level.INFO, () -> String.format("Price: %s at %s", tradeData.getPrice(), DateUtil.format(tradeData.getEventTime())));
    }

    private static void logStars() {
        logger.info(STARS_FOR_LOG);
    }

    private int applyIndicator(List<TradeData> tradeDataList) {
        return (int) Math.round((double) indicator.apply(tradeDataList).getValue());
    }

    private boolean isCurrentCandleStickChanged(TradeData tradeData) {
        return closeTimeOfCurrentCandleStick == null || closeTimeOfCurrentCandleStick.compareTo(tradeData.getEventTime()) < 0;
    }

    private void tradeIfLatestOrderChanged(TradeData tradeData, int previousHullMovingAverage, int hullMovingAverage) {
        logStars();
        if (hullMovingAverage > previousHullMovingAverage && !lastDecision.equals(CommercialDecision.BUY)) {
            buy(tradeData);
        } else if (hullMovingAverage < previousHullMovingAverage && !lastDecision.equals(CommercialDecision.SELL)) {
            sell(tradeData);
        } else {
            logger.info(NO_TRADE_LOG_MESSAGE);
            logPrice(tradeData);
        }

        logHMAs(previousHullMovingAverage, hullMovingAverage);
        logStars();
    }

    private void sell(TradeData tradeData) {
        tradeClient.sell(tradeData);
        lastDecision = CommercialDecision.SELL;
    }
    
    private void buy(TradeData tradeData) {
        tradeClient.buy(tradeData);
        lastDecision = CommercialDecision.BUY;
    }
    
    private List<TradeData> mapClosingTradeDataListTillPreviousBar(List<CandleStickData> allCandleStickData) {
        int limitedSize = allCandleStickData.size() - 1;
        return mapClosingTradeDataTillLimitedSize(allCandleStickData, limitedSize);
    }

    private List<TradeData> mapClosingTradeDataListTillTwoPreviousBar(List<CandleStickData> allCandleStickData) {
        int limitedSize = allCandleStickData.size() - 2;
        return mapClosingTradeDataTillLimitedSize(allCandleStickData, limitedSize);
    }

    @NotNull
    private List<TradeData> mapClosingTradeDataTillLimitedSize(List<CandleStickData> allCandleStickData, int limitedSize) {
        return allCandleStickData.stream().map(CandleStickData::getCloseTradeData).limit(limitedSize).collect(Collectors.toList());
    }

    private List<CandleStickData> getAllCandleStickDataList() {
        return tradeClient.getCandleStickData(tradeSymbol, candleStickInterval);
    }

    private void updateCloseTimeOfCurrentCandleStick(List<CandleStickData> allCandleStickData) {
        int currentCandleStickDataIndex = allCandleStickData.size() - 1;
        CandleStickData currentCandleStickData = allCandleStickData.get(currentCandleStickDataIndex);
        closeTimeOfCurrentCandleStick = currentCandleStickData.getCloseTradeData().getEventTime();
    }
}
