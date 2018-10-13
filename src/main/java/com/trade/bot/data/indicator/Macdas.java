package com.trade.bot.data.indicator;

import static com.trade.bot.util.UtilConstants.ZERO;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import com.trade.bot.CommercialFlag;
import com.trade.bot.TradeData;
import com.trade.bot.data.indicator.Indicator;
import com.trade.bot.logging.LoggerProvider;
import com.trade.bot.util.JsonUtil;

/**
 * @author Ozan Ay
 */
public class Macdas implements Indicator {
    private static final Logger logger = LoggerProvider.getLogger(Macdas.class.getName());
    private final MacdasFactor fastMa;
    private final MacdasFactor slowMa;
    private final MacdasFactor signal;
    private final MacdasFactor signalAs;
    private boolean isInitialRun = true;

    Macdas(String parameters) throws IOException {
        MacdasParameters macdasParameters = JsonUtil.parse(parameters, MacdasParameters.class);
        this.fastMa = new MacdasFactor(macdasParameters.getFastPeriod());
        this.slowMa = new MacdasFactor(macdasParameters.getSlowPeriod());
        this.signal = new MacdasFactor(macdasParameters.getSignalPeriod());
        this.signalAs = new MacdasFactor(macdasParameters.getSignalPeriod());
    }

    @Override
    public CommercialFlag apply(List<TradeData> tradeDataList) {
        List<Double> prices = tradeDataList.stream().map(TradeData::getPrice).collect(Collectors.toList());
        CommercialFlag flag;
        if (isInitialRun) {
            flag = decideInitialCommercialFlag(prices);
            isInitialRun = false;
        } else {
            flag = decideCommercialFlag(tradeDataList);
        }

        return flag;
    }

    private CommercialFlag decideCommercialFlag(List<TradeData> tradeDataList) {
        double price = getLastPrice(tradeDataList);
        double macdASValue = calculateMacdAS(price);
        double signalASValue = signalAs.calculate(macdASValue);
        return decideFlag(macdASValue, signalASValue);
    }

    private CommercialFlag decideInitialCommercialFlag(List<Double> prices) {
        double macdASValue = ZERO;
        double signalASValue = ZERO;
        for (Double price : prices) {
            macdASValue = calculateMacdAS(price);
            signalASValue = signalAs.calculate(macdASValue);
        }

        return decideFlag(macdASValue, signalASValue);
    }

    private double getLastPrice(List<TradeData> tradeDataList) {
        TradeData lastData = tradeDataList.get(tradeDataList.size() - 1);
        return lastData.getPrice();
    }

    private CommercialFlag decideFlag(double macdASValue, double signalASValue) {
        CommercialFlag commercialFlag = CommercialFlag.NONE;
        if (macdASValue > signalASValue) {
            commercialFlag = CommercialFlag.BUY;
        }

        if (macdASValue < signalASValue) {
            commercialFlag = CommercialFlag.SELL;
        }

        logger.log(Level.INFO, () -> String.format("macdAs: %s signalAS: %s", macdASValue, signalASValue));

        return commercialFlag;
    }

    private double calculateMacdAS(Double price) {
        double fastValue = fastMa.calculate(price);
        double slowValue = slowMa.calculate(price);
        if (fastValue == ZERO || slowValue == ZERO) {
            return ZERO;
        }

        double macdValue = fastValue - slowValue;
        double signalValue = signal.calculate(macdValue);
        return macdValue - signalValue;
    }
}
