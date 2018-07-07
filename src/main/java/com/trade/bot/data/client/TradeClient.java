package com.trade.bot.data.client;

import com.trade.bot.TradeData;
import com.trade.bot.TradeSymbol;

public interface TradeClient {
   TradeData getData(TradeSymbol tradeSymbol);
}
