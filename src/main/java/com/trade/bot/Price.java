package com.trade.bot;

import com.trade.bot.util.DateUtil;

import java.text.ParseException;
import java.util.Date;

/**
 * @author Ozan Ay
 */
public class Price {
    private double value;
    private Date date;

    public Price(double value, Date date) {
        this.value = value;
        this.date = date;
    }

    public Price(double value, String dateString) throws ParseException {
        this.value = value;
        this.date = DateUtil.fromString(dateString);
    }

    public double getValue() {
        return value;
    }

    public Date getDate() {
        return date;
    }

    public int compareTo(Price price) {
        return this.date.compareTo(price.getDate());
    }
}
