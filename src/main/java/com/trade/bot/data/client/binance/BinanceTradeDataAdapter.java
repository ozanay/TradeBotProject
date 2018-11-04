package com.trade.bot.data.client.binance;

import java.util.Date;

import com.binance.api.client.domain.event.AggTradeEvent;
import com.trade.bot.TradeData;
import com.trade.bot.util.DateUtil;

/**
 * @author Ozan Ay
 */
public class BinanceTradeDataAdapter implements TradeData {
    private double price;
    private Date eventTime;

    BinanceTradeDataAdapter(String priceStr, long time) {
        this.price = Double.parseDouble(priceStr);
        this.eventTime = new Date(time);
    }

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
        return new Date(eventTime.getTime());
    }

    @Override
    public int compareByDate(TradeData tradeData) {
        return this.eventTime.compareTo(tradeData.getEventTime());
    }
    
    @Override
    public String toString() {
        return "BinanceTradeDataAdapter{" +
            "price=" + price +
            ", eventTime=" + DateUtil.format(eventTime) +
            '}';
    }
}
