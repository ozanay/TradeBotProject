package com.trade.bot.data.client;

/**
 * @author Ozan Ay
 */
public enum TradeClientCandleStickInterval {
    ONE_MINUTE("1m"),
    THREE_MINUTES("3m"),
    FIVE_MINUTES("5m"),
    FIFTEEN_MINUTES("15m"),
    HALF_HOURLY("30m"),
    HOURLY("1h"),
    TWO_HOURLY("2h"),
    FOUR_HOURLY("4h"),
    SIX_HOURLY("6h"),
    EIGHT_HOURLY("8h"),
    TWELVE_HOURLY("12h"),
    DAILY("1d"),
    THREE_DAILY("3d"),
    WEEKLY("1w"),
    MONTHLY("1M");

    private String id;

    TradeClientCandleStickInterval(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
