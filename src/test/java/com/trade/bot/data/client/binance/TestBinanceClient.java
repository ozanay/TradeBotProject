package com.trade.bot.data.client.binance;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.binance.BinanceClient;
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
        sut = new BinanceClient();
    }

    @Test
    public void data_are_read_from_binance_api() {
        TradeData data = sut.getData(TradeSymbol.BTC_USDT);

        assertNotNull(data);
        assertTrue(data.getPrice() > 0);
    }
}
