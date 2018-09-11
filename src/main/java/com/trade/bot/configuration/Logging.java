package com.trade.bot.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
public class Logging {
    @JsonProperty("path")
    private String path;
    
    public String getPath() {
        return path;
    }
}
