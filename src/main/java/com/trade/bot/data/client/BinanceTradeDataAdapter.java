package com.trade.bot.data.client;

import com.binance.api.client.domain.event.AggTradeEvent;
import com.binance.api.client.domain.market.TickerPrice;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Ozan Ay
 */
public class BinanceTradeDataAdapter implements TradeData {
    private final double price;
    private final Date eventTime;

    BinanceTradeDataAdapter(AggTradeEvent tradeEvent) {
        this.price = Double.parseDouble(tradeEvent.getPrice());
        this.eventTime = new Date(tradeEvent.getEventTime());
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public Date getEventTime() {
        return this.eventTime;
    }

    @Override
    public int compareByDate(TradeData tradeData) {
        return this.eventTime.compareTo(tradeData.getEventTime());
    }
}
