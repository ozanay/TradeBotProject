package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;
import com.trade.bot.math.MathUtil;

import java.util.ArrayList;
import java.util.List;

import static com.trade.bot.Constants.FIRST;

/**
 * @author Ozan Ay
 */
class MavilimTransform implements Indicator {
    private List<Double> prices = new ArrayList<>();
    private List<Double> m1s = new ArrayList<>();
    private List<Double> m2s = new ArrayList<>();
    private List<Double> m3s = new ArrayList<>();
    private List<Double> m4s = new ArrayList<>();
    private List<Double> m5s = new ArrayList<>();
    private Mavilim mavilim;

    MavilimTransform(Mavilim mavilim) {
        this.mavilim = mavilim;
    }

    @Override
    public IndicatorResult apply(TradeData tradeData) {
        warmUp(tradeData);

        double maviValue = calculateMaviValue();

        removeDeprecatedValues();

        return new IndicatorResult<>(maviValue);
    }

    @Override
    public void warmUp(TradeData tradeData) {
        prices.add(tradeData.getPrice());

        double m1 = calculateM1();
        m1s.add(m1);

        double m2 = calculateM2();
        m2s.add(m2);

        double m3 = calculateM3();
        m3s.add(m3);

        double m4 = calculateM4();
        m4s.add(m4);

        double m5 = calculateM5();
        m5s.add(m5);
    }

    private void removeDeprecatedValues() {
        prices.remove(FIRST);
        m1s.remove(FIRST);
        m2s.remove(FIRST);
        m3s.remove(FIRST);
        m4s.remove(FIRST);
        m5s.remove(FIRST);
    }

    private double calculateM1() {
        return calculateM(prices, mavilim.getFirstMovingAverageLength());
    }

    private double calculateM2() {
        return calculateM(m1s, mavilim.getSecondMovingAverageLength());
    }

    private double calculateM3() {
        return calculateM(m2s, mavilim.getTMAL());
    }

    private double calculateM4() {
        return calculateM(m3s, mavilim.getFMAL());
    }

    private double calculateM5() {
        return calculateM(m4s, mavilim.getFTMAL());
    }

    private double calculateMaviValue() {
        return calculateM(m5s, mavilim.getSMAL());
    }

    private double calculateM(List<Double> values, int period) {
        double m = 0.0;
        if (values != null && values.size() >= period) {
            m = MathUtil.calculateWeightedMovingAverage(values, period);
        }

        return m;
    }
}
