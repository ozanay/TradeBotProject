package com.trade.bot.data.client;

import com.binance.api.client.domain.market.TickerPrice;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

/**
 * @author Ozan Ay
 */
public class BinanceTradeDataAdapter implements TradeData {
    private final double price;
    private final TradeSymbol symbol;

    BinanceTradeDataAdapter(TickerPrice tickerPrice) {
        this.price = Double.parseDouble(tickerPrice.getPrice());
        this.symbol = TradeSymbol.fromValue(tickerPrice.getSymbol());
    }

    public double getPrice() {
        return price;
    }

    public TradeSymbol getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "BinanceTradeDataAdapter{" +
                "price=" + price +
                ", symbol=" + symbol +
                '}';
    }
}
