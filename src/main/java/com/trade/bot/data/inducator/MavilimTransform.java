package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;
import com.trade.bot.math.WeightedMovingAverage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.trade.bot.Constants.FIRST;

/**
 * @author Ozan Ay
 */
public class MavilimTransform implements Indicator {
    private List<TradeData> closingPrices = new ArrayList<>();
    private List<Double> M1s = new ArrayList<>();
    private List<Double> M2s = new ArrayList<>();
    private List<Double> M3s = new ArrayList<>();
    private List<Double> M4s = new ArrayList<>();
    private List<Double> M5s = new ArrayList<>();

    private Mavilim mavilim;
    private int warmUpLimit;

    MavilimTransform(Mavilim mavilim) {
        this.mavilim = mavilim;
        this.warmUpLimit = calculateWarmUpLimit(mavilim);
    }

    @Override
    public IndicatorResult apply(TradeData tradeData) {
        addLastTradeData(tradeData);
        removeDeprecatedTradeData();

        addMValues();
        removeDeprecatedMValues();

        double value = calculateMaviValue();
        return new IndicatorResult<>(value);
    }

    @Override
    public boolean warmUp(TradeData tradeData) {
        addLastTradeData(tradeData);
        addMValues();
        return this.warmUpLimit == closingPrices.size();
    }

    private double calculateMaviValue() {
        return WeightedMovingAverage.calculate(M5s, this.mavilim.getSMAL());
    }

    private void addLastTradeData(TradeData tradeData) {
        closingPrices.add(FIRST, tradeData);
    }

    private void removeDeprecatedTradeData() {
        int deprecatedPriceIndex = closingPrices.size() - 1;
        closingPrices.remove(deprecatedPriceIndex);
    }

    private void addMValues() {
        addM1();
        addM2();
        addM3();
        addM4();
        addM5();
    }

    private void removeDeprecatedMValues() {
        int deprecatedM1Index = M1s.size() - 1;
        M1s.remove(deprecatedM1Index);

        int deprecatedM2Index = M2s.size() - 1;
        M2s.remove(deprecatedM2Index);

        int deprecatedM3Index = M3s.size() - 1;
        M3s.remove(deprecatedM3Index);

        int deprecatedM4Index = M4s.size() - 1;
        M4s.remove(deprecatedM4Index);

        int deprecatedM5Index = M5s.size() - 1;
        M5s.remove(deprecatedM5Index);
    }

    private void addM1() {
        List<Double> prices = this.closingPrices.stream().sorted(TradeData::compareByDate).map(TradeData::getPrice).collect(Collectors.toList());
        addMvalue(prices, M1s, this.mavilim.getFirstMovingAverageLength());
    }

    private void addM2() {
        addMvalue(M1s, M2s, this.mavilim.getSecondMovingAverageLength());
    }

    private void addM3() {
        addMvalue(M2s, M3s, this.mavilim.getTMAL());
    }

    private void addM4() {
        addMvalue(M3s, M4s, this.mavilim.getFMAL());
    }

    private void addM5() {
        addMvalue(M4s, M5s, this.mavilim.getFTMAL());
    }

    private static void addMvalue(List<Double> pricesFrom, List<Double> addTo, int length) {
        if (pricesFrom.size() >= length) {
            double m = WeightedMovingAverage.calculate(pricesFrom, length);
            addTo.add(FIRST, m);
        }
    }

    private int calculateWarmUpLimit(Mavilim mavilim) {
        return (mavilim == null) ? 0 : mavilim.getFirstMovingAverageLength() + mavilim.getSecondMovingAverageLength() + mavilim.getTMAL()
                + mavilim.getFMAL() + mavilim.getFTMAL() + mavilim.getSMAL() + 1;
    }
}
