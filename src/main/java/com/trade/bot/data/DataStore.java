package com.trade.bot.data;

import com.trade.bot.TradeData;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
class DataStore {
    private static final Logger LOGGER = Logger.getLogger(DataStore.class.getName());

    void store(List<TradeData> tradeData) {
        LOGGER.log(Level.INFO, "Stored data: <" + tradeData + ">");
    }
}
