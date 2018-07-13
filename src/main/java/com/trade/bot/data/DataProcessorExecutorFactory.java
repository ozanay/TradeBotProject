package com.trade.bot.data;

import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientFactory;

/**
 * @author Ozan Ay
 */
public class DataProcessorExecutorFactory {
    public static final DataProcessorExecutorFactory instance = new DataProcessorExecutorFactory();

    private DataProcessorExecutorFactory() {}

    public DataProcessorExecutor create() {
        TradeClient tradeClient = TradeClientFactory.create();
        DataReaderTask dataReaderTask = new DataReaderTask(tradeClient, TradeSymbol.BTC_USDT);
        DataReader dataReader = new DataReader(dataReaderTask);
        DataStore dataStore = new DataStore();
        DataProcessor dataProcessor = new DataProcessor(dataReader, dataStore);
        return new DataProcessorExecutor(dataProcessor);
    }
}
