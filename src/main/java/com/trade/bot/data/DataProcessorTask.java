package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.data.inducator.IndicatorResult;
import com.trade.bot.data.inducator.Indicator;
import com.trade.bot.data.order.OrderExecutor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class DataProcessorTask implements Runnable {
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
        LOGGER.info("/************ DATA WILL BE PROCESSED ************/");

        List<TradeData> tradeData = new ArrayList<>();
        tradeDataQueue.drainTo(tradeData);
        if (tradeData.isEmpty()) {
            LOGGER.info("Drained data is empty.");
        } else {
            int dataSize = tradeData.size();
            LOGGER.info("Data size is " + dataSize);

            double price = tradeData.get(dataSize - 1).getPrice();
            LOGGER.info("Latest price is " + price);

            IndicatorResult result = indicator.apply(tradeData);
            double fish = result.getFish();
            double trigger = result.getTrigger();
            LOGGER.info("FISH: " + fish + " TRIGGER: " + trigger);
            if (fish > trigger) {
                orderExecutor.buy(price);
            } else if (fish < trigger) {
                orderExecutor.sell(price);
            } else {
                LOGGER.info("NO ORDER.");
            }
        }
    }
}
