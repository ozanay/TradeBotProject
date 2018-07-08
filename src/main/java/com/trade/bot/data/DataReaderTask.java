package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class DataReaderTask implements Runnable {
    private static final Logger LOGGER = Logger.getLogger(DataReaderTask.class.getName());
    private static final SynchronousQueue<TradeData> TradeDataQueue = new SynchronousQueue<>();
    private final AtomicBoolean isRunning = new AtomicBoolean(false);
    private final TradeClient tradeClient;
    private final TradeSymbol tradeSymbol;

    DataReaderTask(TradeClient tradeClient, TradeSymbol tradeSymbol) {
        this.tradeClient = tradeClient;
        this.tradeSymbol = tradeSymbol;
    }

    @Override
    public void run() {
        while(isRunning.get()) {
            readData();
        }
    }

    List<TradeData> drainData() {
        List<TradeData> tradeData = new ArrayList<>();
        TradeDataQueue.drainTo(tradeData);
        return tradeData;
    }

    void start() {
        isRunning.set(true);
    }

    void stop() {
        isRunning.set(false);
    }

    private void readData() {
        TradeData data = null;
        try {
            data = tradeClient.getData(tradeSymbol);
            LOGGER.log(Level.INFO, "Data is: " + data.toString());
            TradeDataQueue.put(data);
        } catch (InterruptedException exception) {
            LOGGER.log(Level.SEVERE,String.format("Exception occurred while putting data to Q. Data : <%s>", data.toString()));
        }
    }
}
