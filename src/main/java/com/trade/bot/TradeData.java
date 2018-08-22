package com.trade.bot;

import java.util.Date;

/**
 * @author Ozan Ay
 */
public interface TradeData {
    double getPrice();
    Date getEventTime();
    int compareByDate(TradeData tradeData);
}
