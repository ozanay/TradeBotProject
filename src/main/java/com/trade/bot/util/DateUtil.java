package com.trade.bot.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Ozan Ay
 */
public class DateUtil {
    public static Date fromString(String dateString) throws ParseException {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-M-yyyy hh:mm:ss");
        return simpleDateFormat.parse(dateString);
    }
}
