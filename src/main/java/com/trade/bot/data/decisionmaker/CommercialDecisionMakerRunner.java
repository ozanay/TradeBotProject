package com.trade.bot.data.decisionmaker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ozan Ay
 */
public class CommercialDecisionMakerRunner {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final CommercialDecisionMaker commercialDecisionMaker;
    
    CommercialDecisionMakerRunner(CommercialDecisionMaker commercialDecisionMaker) {
        this.commercialDecisionMaker = commercialDecisionMaker;
    }
    
    public void start() {
        commercialDecisionMaker.start();
        executorService.submit(commercialDecisionMaker);
    }
    
    public void stop() {
        commercialDecisionMaker.stop();
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
