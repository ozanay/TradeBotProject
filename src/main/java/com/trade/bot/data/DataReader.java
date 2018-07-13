package com.trade.bot.data;

import com.trade.bot.TradeData;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ozan Ay
 */
class DataReader {
    private final ExecutorService executorService;
    private final DataReaderTask dataReaderTask;

    DataReader(DataReaderTask dataReaderTask) {
        this.dataReaderTask = dataReaderTask;
        this.executorService = Executors.newSingleThreadExecutor();
    }

    void startRead() {
        dataReaderTask.start();
        executorService.execute(dataReaderTask);
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
