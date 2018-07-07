package com.trade.bot.data;

/**
 * @author Ozan Ay
 */
public class DataProcessor {
    private final DataReader dataReader;
    private final DataStore dataStore;

    public DataProcessor(DataReader dataReader, DataStore dataStore) {
        this.dataReader = dataReader;
        this.dataStore = dataStore;
    }
}
