package com.trade.bot;

import com.trade.bot.util.EnumUtils;

import java.util.function.Function;

public enum TradeSymbol {
    BTC_USDT("BTCUSDT"),
    N_A("NOT APPLICABLE");

    private static final Function<String, TradeSymbol> lookupMap = EnumUtils.lookupMap(TradeSymbol.class, e ->e.getValue(), N_A);
    private final String value;

    TradeSymbol(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static TradeSymbol fromValue(String value) {
        return lookupMap.apply(value);
    }
}
