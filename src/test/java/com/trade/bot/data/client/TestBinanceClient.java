package com.trade.bot.data.client;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertTrue;

/**
 * @author Ozan Ay
 */
public class TestBinanceClient {
    private BinanceClient sut;

    @BeforeMethod
    public void beforeMethod() {
        sut = BinanceClient.getInstance();
    }

    @Test
    public void data_are_read_from_binance_api() {
        TradeData data = sut.getData(TradeSymbol.BTC_USDT);

        assertNotNull(data);
        assertTrue(data.getPrice() > 0);
    }
}
