package com.trade.bot.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ozan Ay
 */
public class DateUtil {
    private static final String DATE_FORMAT = "dd-M-yyyy hh:mm:ss";

    private DateUtil() {}

    public static String format(Date date) {
        SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        return formatter.format(date);
    }
}
