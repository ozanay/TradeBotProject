package com.trade.bot.data.decisionmaker;

import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.trade.bot.util.DateUtil;
import org.jetbrains.annotations.NotNull;

import com.trade.bot.CandleStickData;
import com.trade.bot.CommercialFlag;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.logging.LoggerProvider;

/**
 * @author Ozan Ay
 */
public class IndicatorCommercialDecisionApplier implements CommercialDecisionApplier {
    private static final Logger logger = LoggerProvider.getLogger(IndicatorCommercialDecisionApplier.class.getName());
    private static final Map<CommercialFlag, Consumer<TradeData>> actionMap = new EnumMap<>(CommercialFlag.class);
    private static final String NO_TRADE_LOG_MESSAGE = "NO TRADE!";
    private static final String BAR_CHANGED_LOG_MESSAGE = "Current candle stick bar is changed.";
    private static final String STARS_FOR_LOG = "***************************************************************************";
    private final Indicator indicator;
    private final TradeClient tradeClient;
    private final TradeSymbol tradeSymbol;
    private final TradeClientCandleStickInterval candleStickInterval;
    private CommercialFlag lastDecision = CommercialFlag.NONE;
    private AtomicBoolean isRunning = new AtomicBoolean();
    private Date closeTimeOfCurrentCandleStick;

    IndicatorCommercialDecisionApplier(Indicator indicator, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                       TradeClientCandleStickInterval candleStickInterval) {
        this.indicator = indicator;
        this.tradeClient = tradeClient;
        this.tradeSymbol = tradeSymbol;
        this.candleStickInterval = candleStickInterval;
        initializeActions();
    }

    private void initializeActions() {
        actionMap.put(CommercialFlag.NONE, this::noTrade);
        actionMap.put(CommercialFlag.BUY, this::buy);
        actionMap.put(CommercialFlag.SELL, this::sell);
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

    private void decide(TradeData tradeData) {
        if (isCurrentCandleStickChanged(tradeData)) {
            List<CandleStickData> allCandleStickDataList = getAllCandleStickDataList();
            TradeData previousCandleStickCloseData = getPreviousCandleStickCloseData(allCandleStickDataList);
            if (isCurrentCandleStickChanged(previousCandleStickCloseData)) {
                logStars();
                logger.info(BAR_CHANGED_LOG_MESSAGE);
                logger.log(Level.INFO, () -> String.format("Trade data time is %s", DateUtil.format(tradeData.getEventTime())));

                List<TradeData> closingTradeDataListTillPreviousBar = mapClosingTradeDataListTillPreviousBar(allCandleStickDataList);
                CommercialFlag flag = indicator.apply(closingTradeDataListTillPreviousBar);
                tradeIfLatestOrderChanged(tradeData, flag);

                updateCloseTimeOfCurrentCandleStick(allCandleStickDataList);
                logger.log(Level.INFO, () -> String.format("Current close time is %s", DateUtil.format(closeTimeOfCurrentCandleStick)));
                logStars();
            }
        }
    }

    private TradeData getPreviousCandleStickCloseData(List<CandleStickData> candleStickDataList) {
        int lastElementIndex = candleStickDataList.size() - 1;
        return candleStickDataList.get(lastElementIndex).getCloseTradeData();
    }

    private static void logPrice(TradeData tradeData) {
        logger.log(Level.INFO, () -> String.format("Price: %s at %s", tradeData.getPrice(), DateUtil.format(tradeData.getEventTime())));
    }

    private static void logStars() {
        logger.info(STARS_FOR_LOG);
    }

    private boolean isCurrentCandleStickChanged(TradeData tradeData) {
        return closeTimeOfCurrentCandleStick == null || closeTimeOfCurrentCandleStick.compareTo(tradeData.getEventTime()) < 0;
    }

    private void tradeIfLatestOrderChanged(TradeData tradeData, CommercialFlag commercialFlag) {
        actionMap.get(commercialFlag).accept(tradeData);
    }

    private void noTrade(TradeData tradeData) {
        logger.info(NO_TRADE_LOG_MESSAGE);
        logPrice(tradeData);
    }

    private void sell(TradeData tradeData) {
        if (!lastDecision.equals(CommercialFlag.SELL)) {
            tradeClient.sell(tradeData);
            lastDecision = CommercialFlag.SELL;
        } else {
            noTrade(tradeData);
        }
    }
    
    private void buy(TradeData tradeData) {
        if (!lastDecision.equals(CommercialFlag.BUY)) {
            tradeClient.buy(tradeData);
            lastDecision = CommercialFlag.BUY;
        } else {
            noTrade(tradeData);
        }
    }
    
    private List<TradeData> mapClosingTradeDataListTillPreviousBar(List<CandleStickData> allCandleStickData) {
        int limitedSize = allCandleStickData.size() - 1;
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
