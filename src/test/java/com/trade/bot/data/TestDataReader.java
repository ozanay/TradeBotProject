package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeClientFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import static org.testng.Assert.assertFalse;

/**
 * @author Ozan Ay
 */
public class TestDataReader {
    private DataReader sut;

    @BeforeMethod
    public void beforeMethod() {
        DataReaderTask dataReaderTask = new DataReaderTask(TradeClientFactory.create(), TradeSymbol.BTC_USDT);
        sut = new DataReader(dataReaderTask);
    }

    @Test
    public void data_reading_is_started() throws InterruptedException {
        sut.startRead();

        Thread.sleep(2_000);

        List<TradeData> tradeData = sut.drainData();

        assertFalse(tradeData.isEmpty());

        sut.stopRead();
    }
}
