package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClient;
import com.trade.bot.data.client.TradeClientFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertFalse;

/**
 * @author Ozan Ay
 */
public class TestDataReaderTask {
    private DataReaderTask sut;

    @BeforeMethod
    public void beforeMethod() {
        TradeClient tradeClient = TradeClientFactory.create();
        sut = new DataReaderTask(tradeClient, TradeSymbol.BTC_USDT);
    }

    @Test
    public void trade_data_are_read_continuously() throws InterruptedException {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor(new DataReaderTaskThreadFactory());

        executorService.scheduleWithFixedDelay(sut, 0, 1, TimeUnit.SECONDS);

        Thread.sleep(10_000);

        List<TradeData> tradeData = sut.drainData();

        assertFalse(tradeData.isEmpty());

        executorService.shutdown();
    }
}
