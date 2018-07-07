package com.trade.bot.data;

import com.trade.bot.TradeData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ozan Ay
 */
class DataReader {
    private static final int THREAD_SIZE = 1;
    private final ExecutorService executorService;
    private final DataReaderTask dataReaderTask;

    DataReader(DataReaderTask dataReaderTask) {
        this.dataReaderTask = dataReaderTask;
        this.executorService = Executors.newFixedThreadPool(THREAD_SIZE);
    }

    void startRead() {
        if (executorService.isShutdown()) {
            dataReaderTask.start();
            executorService.execute(dataReaderTask);
        }
    }

    void stopRead() {
        if (!executorService.isShutdown()) {
            dataReaderTask.stop();
            executorService.shutdownNow();
        }
    }

    List<TradeData> drainData() {
        return dataReaderTask.drainData();
    }
}
