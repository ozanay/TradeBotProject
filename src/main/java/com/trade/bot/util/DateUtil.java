package com.trade.bot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ozan Ay
 */
public class DateUtil {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss";
    private static final SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);

    public static Date fromString(String dateString) throws ParseException {
        return formatter.parse(dateString);
    }

    public static String format(Date date) {
        return formatter.format(date);
    }
}
