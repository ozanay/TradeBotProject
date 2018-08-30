package com.trade.bot.data.decisionmaker;

import com.trade.bot.CandleStickData;
import com.trade.bot.CommercialDecision;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientCandleStickInterval;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.logging.LoggerProvider;
import com.trade.bot.util.DateUtil;

import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class MavilimDecisionMaker implements CommercialDecisionMaker {
    private static final Logger LOGGER = LoggerProvider.getLogger(MavilimDecisionMaker.class.getName());
    private final Indicator indicator;
    private final TradeClient tradeClient;
    private final TradeSymbol tradeSymbol;
    private final TradeClientCandleStickInterval candleStickInterval;
    private CommercialDecision latestDecision = CommercialDecision.NONE;
    private Date closingTimeForCurrentBar;
    private double maviValue;

    MavilimDecisionMaker(Indicator indicator, TradeClient tradeClient, TradeSymbol tradeSymbol,
                                TradeClientCandleStickInterval candleStickInterval) {
        this.indicator = indicator;
        this.tradeClient = tradeClient;
        this.tradeSymbol = tradeSymbol;
        this.candleStickInterval = candleStickInterval;
    }

    @Override
    public void decide(TradeData tradeData) {
        Date now = new Date();
        boolean isInCurrentBar = now.compareTo(this.closingTimeForCurrentBar) < 0;
        if (isInCurrentBar) {
            tradeIfLatestOrderChanged(tradeData);
        } else {
            updateMaviValueAndCloseTime();
        }
    }

    @Override
    public void warmUp() {
        List<CandleStickData> candleStickData = tradeClient.getCandleStickData(tradeSymbol, candleStickInterval);
        int currentBarIndex = candleStickData.size() - 1;
        for (int index = 0; index < currentBarIndex - 2; index++) {
            CandleStickData candleStick = candleStickData.get(index);
            indicator.warmUp(candleStick.getCloseTradeData());
        }

        TradeData closeTradeData = candleStickData.get(currentBarIndex - 1).getCloseTradeData();
        this.closingTimeForCurrentBar = closeTradeData.getEventTime();
        LOGGER.info("Closing time for WARMED UP data is " + DateUtil.format(this.closingTimeForCurrentBar));
        LOGGER.info("Close price for WARMED UP data is: " + closeTradeData.getPrice());
        this.maviValue = (double) indicator.apply(closeTradeData).getValue();
        LOGGER.info("Warmed up MAVI value: " + this.maviValue);
    }

    private void updateMaviValueAndCloseTime() {
        TradeData currentCloseTradeData = tradeClient.getCurrentCloseTradeData(tradeSymbol, candleStickInterval);
        setCloseTime(currentCloseTradeData.getEventTime());
        setMaviValue(currentCloseTradeData);
    }

    private void tradeIfLatestOrderChanged(TradeData tradeData) {
        if (tradeData.getPrice() < this.maviValue && !this.latestDecision.equals(CommercialDecision.SELL)) {
            sell(tradeData);
        } else if (tradeData.getPrice() >= this.maviValue && !this.latestDecision.equals(CommercialDecision.BUY)) {
            buy(tradeData);
        }
    }

    private void setMaviValue(TradeData closeTradeData) {
        this.maviValue = (double) indicator.apply(closeTradeData).getValue();
        LOGGER.info("MAVI VALUE IS UPDATED. VALUE: " + this.maviValue);
    }

    private void setCloseTime(Date closeTime) {
        this.closingTimeForCurrentBar = closeTime;
        LOGGER.info("CURRENT BAR IS CHANGED AND CLOSE TIME: " + DateUtil.format(closeTime));
    }

    private void buy(TradeData tradeData) {
        this.latestDecision = CommercialDecision.BUY;
        tradeClient.buy(tradeData);
    }

    private void sell(TradeData tradeData) {
        this.latestDecision = CommercialDecision.SELL;
        tradeClient.sell(tradeData);
    }
}
