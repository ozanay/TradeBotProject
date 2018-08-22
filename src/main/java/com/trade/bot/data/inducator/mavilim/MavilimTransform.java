package com.trade.bot.data.inducator.mavilim;

import com.trade.bot.Price;
import com.trade.bot.math.WeightedMovingAverage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.trade.bot.Constants.FIRST;

/**
 * @author Ozan Ay
 */
public class MavilimTransform {
    private List<Price> closingPrices = new ArrayList<>();
    private List<Double> M1s = new ArrayList<>();
    private List<Double> M2s = new ArrayList<>();
    private List<Double> M3s = new ArrayList<>();
    private List<Double> M4s = new ArrayList<>();
    private List<Double> M5s = new ArrayList<>();

    private Mavilim mavilim;
    private int warmUpLimit;

    public MavilimTransform(Mavilim mavilim) {
        this.mavilim = mavilim;
        this.warmUpLimit = calculateWarmUpLimit(mavilim);
    }

    public double apply(Price closingPrice) {
        addLastClosingPrice(closingPrice);
        removeDeprecatedPrice();

        addMValues();
        removeDeprecatedMValues();

        return calculateMaviValue();
    }

    public boolean warmUp(Price closingPrice) {
        addLastClosingPrice(closingPrice);
        addMValues();
        return this.warmUpLimit == closingPrices.size();
    }

    private double calculateMaviValue() {
        return WeightedMovingAverage.calculate(M5s, this.mavilim.getSMAL());
    }

    private void addLastClosingPrice(Price closingPrice) {
        closingPrices.add(FIRST, closingPrice);
    }

    private void removeDeprecatedPrice() {
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
        List<Double> prices = this.closingPrices.stream().sorted(Price::compareByDate).map(Price::getValue).collect(Collectors.toList());
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
