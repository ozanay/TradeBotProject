package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.data.inducator.Indicator;
import com.trade.bot.data.order.OrderExecutor;

import java.util.concurrent.BlockingQueue;

/**
 * @author Ozan Ay
 */
public class DataProcessorExecutorFactory {
    public static final DataProcessorExecutorFactory instance = new DataProcessorExecutorFactory();

    private DataProcessorExecutorFactory() {}

    public DataProcessorExecutor create(BlockingQueue<TradeData> tradeDataQueue, Indicator indicator) {
        OrderExecutor orderExecutor = new OrderExecutor();
        DataProcessorTask dataProcessorTask = new DataProcessorTask(tradeDataQueue, indicator, orderExecutor);
        return new DataProcessorExecutor(dataProcessorTask);
    }
}
