package com.trade.bot.data.client;

import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
public class BinanceTradeData implements TradeData {
    private final double price;

    BinanceTradeData(double price) {
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "BinanceTradeData{" +
                "price=" + price +
                '}';
    }
}
