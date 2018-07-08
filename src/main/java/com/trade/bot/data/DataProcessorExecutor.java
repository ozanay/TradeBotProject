package com.trade.bot.data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ozan Ay
 */
public class DataProcessorExecutor {
    private static final long INITIAL_DELAY = 1L;
    private static final long PERIOD = 1L;
    private final DataProcessor dataProcessor;
    private final ScheduledExecutorService executorService;

    DataProcessorExecutor(DataProcessor dataProcessor) {
        this.dataProcessor = dataProcessor;
        this.executorService = Executors.newSingleThreadScheduledExecutor();
    }

    public void start() {
        executorService.scheduleAtFixedRate(dataProcessor, INITIAL_DELAY, PERIOD, TimeUnit.MINUTES);
    }

    public void stop() {
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
