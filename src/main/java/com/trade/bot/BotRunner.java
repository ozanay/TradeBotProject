package com.trade.bot;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;

public class BotRunner {
    private static final String SECRET_KEY = "s8MlZOJKFO2XQhV9dT0lgdJjsKeNo3bPYOjrjwGPZOI5froszAJsoJOTwd4s7EX8";
    private static final String API_KEY = "pEYXmapVpBEILIJ3nnNfwJKYrNkecQVgJ17VuA5KmUTWkg6SEjqn7EyXohFIVL9A";

    public static void main(String[] args) {
        BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance(API_KEY, SECRET_KEY);
        BinanceApiRestClient client = factory.newRestClient();

        while (true) {
            TickerPrice btcUsdtPrice = client.getPrice("BTCUSDT");
            System.out.println("BTC/USDT prices: " + btcUsdtPrice.toString());
        }
    }
}
