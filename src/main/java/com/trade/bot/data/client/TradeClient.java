package com.trade.bot.data.client;

import com.trade.bot.CandleStickData;
import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

import java.util.List;

public interface TradeClient {
    TradeData getData(TradeSymbol tradeSymbol);
    List<CandleStickData> getCandleStickData(TradeSymbol tradeSymbol, TradeClientCandleStickInterval interval);
    void buy(TradeData tradeData);
    void sell(TradeData tradeData);
}
