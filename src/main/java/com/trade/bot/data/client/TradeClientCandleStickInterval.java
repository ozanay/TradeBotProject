package com.trade.bot.data.client;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
public enum TradeClientCandleStickInterval {
    @JsonProperty ("1m")
    ONE_MINUTE("1m"),
    @JsonProperty ("3m")
    THREE_MINUTES("3m"),
    @JsonProperty ("5m")
    FIVE_MINUTES("5m"),
    @JsonProperty ("15m")
    FIFTEEN_MINUTES("15m"),
    @JsonProperty ("30m")
    HALF_HOURLY("30m"),
    @JsonProperty ("1h")
    HOURLY("1h"),
    @JsonProperty ("2h")
    TWO_HOURLY("2h"),
    @JsonProperty ("4h")
    FOUR_HOURLY("4h"),
    @JsonProperty ("6h")
    SIX_HOURLY("6h"),
    @JsonProperty ("8h")
    EIGHT_HOURLY("8h"),
    @JsonProperty ("12h")
    TWELVE_HOURLY("12h"),
    @JsonProperty ("1d")
    DAILY("1d"),
    @JsonProperty ("3d")
    THREE_DAILY("3d"),
    @JsonProperty ("1w")
    WEEKLY("1w"),
    @JsonProperty ("1M")
    MONTHLY("1M");

    private String id;

    TradeClientCandleStickInterval(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
