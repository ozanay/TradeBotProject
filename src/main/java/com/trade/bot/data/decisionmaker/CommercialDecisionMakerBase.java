package com.trade.bot.data.decisionmaker;

import com.trade.bot.TradeData;

/**
 * @author Ozan Ay
 */
abstract class CommercialDecisionMakerBase implements CommercialDecisionMaker{
    abstract void decide(TradeData tradeData);
}
