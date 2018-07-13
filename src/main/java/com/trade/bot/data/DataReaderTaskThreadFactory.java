package com.trade.bot.data;

import java.util.concurrent.ThreadFactory;

/**
 * @author Ozan Ay
 */
public class DataReaderTaskThreadFactory implements ThreadFactory {
    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable, "DataReaderTask");
        thread.setName(thread.getName() + "_" + thread.getId());
        return thread;
    }
}
