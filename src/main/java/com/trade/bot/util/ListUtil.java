package com.trade.bot.util;

import org.jetbrains.annotations.Contract;

import java.util.List;

/**
 * @author Ozan Ay
 */
public class ListUtil {
    @Contract(value = "null -> false", pure = true)
    public static boolean hasAnyValue(List<?> values) {
        return values != null && !values.isEmpty();
    }
}
