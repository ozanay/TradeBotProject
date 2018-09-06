package com.trade.bot.data.decisionmaker;

import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
public interface CommercialDecisionMaker extends Runnable{
    void decide(TradeData tradeData);
    void warmUp();
}
