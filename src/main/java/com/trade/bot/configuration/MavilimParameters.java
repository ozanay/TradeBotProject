package com.trade.bot.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author Ozan Ay
 */
public class MavilimParameters {
    @JsonProperty("fmal")
    private int fmal;
    
    @JsonProperty("smal")
    private int smal;
    
    public int getFmal() {
        return fmal;
    }
    
    public int getSmal() {
        return smal;
    }
}
