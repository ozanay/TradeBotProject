package com.trade.bot.data;

import com.trade.bot.TradeData;

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

    DataProcessorTask(BlockingQueue<TradeData> tradeDataQueue) {
        this.tradeDataQueue = tradeDataQueue;
    }

    @Override
    public void run() {
        List<TradeData> tradeData = new ArrayList<>();
        tradeDataQueue.drainTo(tradeData);
        //Execute data
        LOGGER.log(Level.INFO, String.format("Drained data %s is processed.", tradeData));
    }
}
