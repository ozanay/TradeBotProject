package com.trade.bot.data.decisionmaker;

/**
 * @author Ozan Ay
 */
public interface CommercialDecisionMaker extends Runnable{
    void start();
    void stop();
}
