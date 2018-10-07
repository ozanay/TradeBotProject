package com.trade.bot.data.indicator;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.trade.bot.CommercialFlag;
import com.trade.bot.TradeData;
import com.trade.bot.util.JsonUtil;
import com.trade.bot.util.math.ExponentialMovingAverage;

/**
 * @author Ozan Ay
 */
public class Macdas implements Indicator{
    private final ExponentialMovingAverage emaForFastMA;
    private final ExponentialMovingAverage emaForSlowMA;
    private final ExponentialMovingAverage emaForSignal;
    private final ExponentialMovingAverage emaForSignalAS;

    Macdas(String parameters) throws IOException {
        MacdasParameters macdasParameters = JsonUtil.parse(parameters, MacdasParameters.class);
        this.emaForFastMA = new ExponentialMovingAverage(macdasParameters.getFastPeriod());
        this.emaForSlowMA = new ExponentialMovingAverage(macdasParameters.getSlowPeriod());
        this.emaForSignal = new ExponentialMovingAverage(macdasParameters.getSignalPeriod());
        this.emaForSignalAS = new ExponentialMovingAverage(macdasParameters.getSignalPeriod());
    }

    @Override
    public CommercialFlag apply(List<TradeData> tradeDataList) {
        List<Double> prices = tradeDataList.stream().map(TradeData::getPrice).collect(Collectors.toList());
        double fastMA = emaForFastMA.calculateMostRecently(prices, macdasParameters.getFastPeriod());
        double slowMA = emaForFastMA.calculateMostRecently(prices, macdasParameters.getSlowPeriod());
        double macd = fastMA - slowMA;
        return null;
    }
}
