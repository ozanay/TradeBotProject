package com.trade.bot.data.decisionmaker;

/**
 * @author Ozan Ay
 */
public interface CommercialDecisionApplier extends Runnable{
    void start();
    void stop();
}
