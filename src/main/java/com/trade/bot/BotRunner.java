package com.trade.bot;

import com.trade.bot.data.DataProcessorExecutor;
import com.trade.bot.data.DataProcessorExecutorFactory;

public class BotRunner {
    public static void main(String[] args) {
        DataProcessorExecutor dataProcessorExecutor = DataProcessorExecutorFactory.instance.create();
        dataProcessorExecutor.start();
    }
}
