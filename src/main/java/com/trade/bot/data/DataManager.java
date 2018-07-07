package com.trade.bot.data;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.client.TradeClient;
import com.trade.bot.client.TradeClientFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.SynchronousQueue;

/**
 * @author Ozan Ay
 */
public class DataManager {
    private static final SynchronousQueue<TradeData> tradeDataQueue = new SynchronousQueue<>();
    private final TradeClient client;

    public DataManager(TradeClientFactory clientFactory) {
        this.client = clientFactory.createClient();
    }

    public void read(TradeSymbol tradeSymbol) throws InterruptedException {
        TradeData data = client.getData(tradeSymbol);
        tradeDataQueue.put(data);
    }

    public List<TradeData> drainData() {
        List<TradeData> tradeData = new ArrayList<>();
        tradeDataQueue.drainTo(tradeData);
        return tradeData;
    }
}
