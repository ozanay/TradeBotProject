package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.client.TradeClient;
import com.trade.bot.client.TradeClientFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ozan Ay
 */
public class DataManager {
    private final List<TradeData> tradeData = new ArrayList<>();
    private final TradeClient client;

    public DataManager(TradeClientFactory clientFactory) {
        this.client = clientFactory.createClient();
    }

    public void read(TradeSymbol tradeSymbol) {
        TradeData data = client.getData(tradeSymbol);
        tradeData.add(data);
    }
}
