package com.trade.bot.util;


import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * @author Ozan Ay
 */
public final class EnumUtils {
    private EnumUtils() {}

    public static <T, E extends Enum<E>> Function<T, E> lookupMap(Class<E> eClass, final Function<E, T> mapper, E defaultE) {
        Map<T, E> enumIndexedMap = new HashMap<T, E>();
        E[] allEnums = eClass.getEnumConstants();
        Arrays.stream(allEnums).forEach(eachEnum -> enumIndexedMap.put(mapper.apply(eachEnum), eachEnum));
        return (T key) -> enumIndexedMap.getOrDefault(key, defaultE);
    }
}
