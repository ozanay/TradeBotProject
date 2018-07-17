package com.trade.bot.data;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @author Ozan Ay
 */
public class DataProcessorExecutor {
    private static final long INITIAL_DELAY = 0L;
    private static final long PERIOD = 1L;
    private final DataProcessorTask dataProcessorTask;
    private final ScheduledExecutorService executorService;

    DataProcessorExecutor(DataProcessorTask dataProcessorTask) {
        this.dataProcessorTask = dataProcessorTask;
        this.executorService = Executors.newSingleThreadScheduledExecutor(new DataProcessorTaskThreadFactory());
    }

    public void start() {
        executorService.scheduleAtFixedRate(dataProcessorTask, INITIAL_DELAY, PERIOD, TimeUnit.MINUTES);
    }

    public void stop() {
        if (!executorService.isShutdown()) {
            executorService.shutdownNow();
        }
    }
}
