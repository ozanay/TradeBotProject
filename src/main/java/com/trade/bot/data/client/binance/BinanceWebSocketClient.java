package com.trade.bot.data.client.binance;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiWebSocketClient;
import com.binance.api.client.domain.event.AggTradeEvent;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;
import com.trade.bot.data.client.TradeWebSocketClient;
import com.trade.bot.data.decisionmaker.CommercialDecisionMaker;

/**
 * @author Ozan Ay
 */
public class BinanceWebSocketClient implements TradeWebSocketClient {
    private static final BinanceApiWebSocketClient client = BinanceApiClientFactory.newInstance().newWebSocketClient();
    private final CommercialDecisionMaker commercialDecisionMaker;

    BinanceWebSocketClient(CommercialDecisionMaker commercialDecisionMaker) {
        this.commercialDecisionMaker = commercialDecisionMaker;
    }

    @Override
    public void subscribeEvent(TradeSymbol tradeSymbol) {
        client.onAggTradeEvent(tradeSymbol.getValue().toLowerCase(), this::addSubscribedData);
    }

    private void addSubscribedData(AggTradeEvent response) {
        TradeData tradeData = new BinanceTradeDataAdapter(response);
        commercialDecisionMaker.decide(tradeData);
    }
}
