package com.trade.bot.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * @author Ozan Ay
 */
public class JsonUtil {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private JsonUtil() {}
    
    public static <T> T parse(String json, Class<T> tClass) throws IOException {
        return objectMapper.readValue(json, tClass);
    }
}
