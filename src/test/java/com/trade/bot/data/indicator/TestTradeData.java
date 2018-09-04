package com.trade.bot.data.indicator;

import com.trade.bot.TradeData;

import java.util.Date;

/**
 * @author Ozan Ay
 */
public class TestTradeData implements TradeData {
    private double price;
    private Date date;

    TestTradeData(double price, Date date) {
        this.price = price;
        this.date = date;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public Date getEventTime() {
        return this.date;
    }

    @Override
    public int compareByDate(TradeData tradeData) {
        return this.date.compareTo(tradeData.getEventTime());
    }
}
