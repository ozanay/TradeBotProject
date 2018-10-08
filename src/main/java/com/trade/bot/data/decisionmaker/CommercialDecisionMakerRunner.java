package com.trade.bot.data.decisionmaker;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author Ozan Ay
 */
public class CommercialDecisionMakerRunner {
    private final ExecutorService executorService = Executors.newFixedThreadPool(1);
    private final CommercialDecisionApplier commercialDecisionApplier;
    
    CommercialDecisionMakerRunner(CommercialDecisionApplier commercialDecisionApplier) {
        this.commercialDecisionApplier = commercialDecisionApplier;
    }
    
    public void start() {
        commercialDecisionApplier.start();
        executorService.submit(commercialDecisionApplier);
    }
    
    public void stop() {
        commercialDecisionApplier.stop();
        if (!executorService.isShutdown()) {
            executorService.shutdownNow();
        }
    }
}
