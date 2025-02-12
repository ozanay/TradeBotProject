package com.trade.bot.data.indicator;

/**
 * @author Ozan Ay
 */
public class IndicatorResult<T> {
    private final T value;

    public IndicatorResult(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
