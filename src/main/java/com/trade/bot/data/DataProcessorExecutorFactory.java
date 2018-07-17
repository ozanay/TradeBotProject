package com.trade.bot.data;

import com.trade.bot.TradeData;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class DataProcessorExecutorFactory {
    public static final DataProcessorExecutorFactory instance = new DataProcessorExecutorFactory();

    private DataProcessorExecutorFactory() {}

    public DataProcessorExecutor create(BlockingQueue<TradeData> tradeDataQueue) {
        DataProcessorTask dataProcessorTask = new DataProcessorTask(tradeDataQueue);
        return new DataProcessorExecutor(dataProcessorTask);
    }
}
