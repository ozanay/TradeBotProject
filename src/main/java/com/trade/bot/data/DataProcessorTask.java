package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.data.inducator.AppliedIndicatorResult;
import com.trade.bot.data.inducator.Indicator;
import com.trade.bot.data.order.OrderExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class DataProcessorTask implements Runnable{
    private static final Logger LOGGER = Logger.getLogger(DataProcessorTask.class.getName());
    private final BlockingQueue<TradeData> tradeDataQueue;
    private final Indicator indicator;
    private final OrderExecutor orderExecutor;

    DataProcessorTask(BlockingQueue<TradeData> tradeDataQueue, Indicator indicator, OrderExecutor orderExecutor) {
        this.tradeDataQueue = tradeDataQueue;
        this.indicator = indicator;
        this.orderExecutor = orderExecutor;
    }

    @Override
    public void run() {
        List<TradeData> tradeData = new ArrayList<>();
        tradeDataQueue.drainTo(tradeData);
        LOGGER.log(Level.INFO, String.format("Drained data %s is processed.", tradeData));

        double price = tradeData.get(tradeData.size() - 1).getPrice();
        AppliedIndicatorResult result = indicator.apply(tradeData);
        if (result.getFish() > result.getTrigger()) {
            orderExecutor.buy(price);
        } else if (result.getFish() < result.getTrigger()) {
            orderExecutor.sell(price);
        }
    }
}
