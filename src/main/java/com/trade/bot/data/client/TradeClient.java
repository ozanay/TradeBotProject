package com.trade.bot.data.client;

import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

import java.util.Date;
import java.util.List;

public interface TradeClient {
    List<CandleStickData> getCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    CandleStickData getCurrentCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    TradeData getCurrentCloseTradeData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);

    void buy(TradeData tradeData);

    void sell(TradeData tradeData);
    
    void subscribeEvent(TradeSymbol tradeSymbol);
}
