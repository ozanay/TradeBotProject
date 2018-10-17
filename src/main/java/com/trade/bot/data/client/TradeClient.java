package com.trade.bot.data.client;

import java.util.List;

import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

public interface TradeClient {
    List<CandleStickData> getCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    CandleStickData getCurrentCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    TradeData getCurrentCloseTradeData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    void buy(TradeData tradeData);

    void sell(TradeData tradeData);
    
    void subscribeTradeEvent(TradeSymbol tradeSymbol);
    
    TradeData getLastTradeData();
}
