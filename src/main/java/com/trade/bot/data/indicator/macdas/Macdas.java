package com.trade.bot.data.indicator.macdas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.trade.bot.CommercialFlag;
import com.trade.bot.TradeData;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.util.JsonUtil;
import com.trade.bot.util.math.ExponentialMovingAverage;

/**
 * @author Ozan Ay
 */
public class Macdas implements Indicator {
    private final ExponentialMovingAverage emaForFastMA;
    private final ExponentialMovingAverage emaForSlowMA;
    private final ExponentialMovingAverage emaForSignal;
    private final ExponentialMovingAverage emaForSignalAS;
    private final MacdasParameters macdasParameters;
    private boolean isInitialRun = true;

    Macdas(String parameters) throws IOException {
        this.macdasParameters = JsonUtil.parse(parameters, MacdasParameters.class);
        this.emaForFastMA = new ExponentialMovingAverage(macdasParameters.getFastPeriod());
        this.emaForSlowMA = new ExponentialMovingAverage(macdasParameters.getSlowPeriod());
        this.emaForSignal = new ExponentialMovingAverage(macdasParameters.getSignalPeriod());
        this.emaForSignalAS = new ExponentialMovingAverage(macdasParameters.getSignalPeriod());
    }

    @Override
    public CommercialFlag apply(List<TradeData> tradeDataList) {
        List<Double> prices = tradeDataList.stream().map(TradeData::getPrice).collect(Collectors.toList());
        if (isInitialRun) {
            
        }
        return null;
    }
}
