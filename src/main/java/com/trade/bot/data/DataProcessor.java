package com.trade.bot.data;

import com.trade.bot.TradeData;

import java.util.List;

/**
 * @author Ozan Ay
 */
public class DataProcessor implements Runnable{
    private final DataReader dataReader;
    private final DataStore dataStore;

    public DataProcessor(DataReader dataReader, DataStore dataStore) {
        this.dataReader = dataReader;
        this.dataStore = dataStore;
    }

    @Override
    public void run() {
        List<TradeData> tradeData = dataReader.drainData();
        //Execute data
        dataStore.store(tradeData);
    }
}
