package com.trade.bot.data.inducator.fishertransformation;

/**
 * @author Ozan Ay
 */
class FisherTransformationVariable {
    private double average;
    private double min;
    private double max;
    private double value;
    private double fish;

    public FisherTransformationVariable() {
        this.average = 0;
        this.min = 0;
        this.max = 0;
        this.value = 0;
        this.fish = 0;
    }

    public double getAverage() {
        return this.average;
    }

    public double getMin() {
        return this.min;
    }

    public double getMax() {
        return this.max;
    }

    public double getValue() {
        return this.value;
    }

    public double getFish() {
        return fish;
    }

    public void setAverage(double average) {
        this.average = average;
    }

    public void setMin(double min) {
        this.min = min;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public void setFish(double fish) {
        this.fish = fish;
    }
}
