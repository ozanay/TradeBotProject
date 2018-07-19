package com.trade.bot.data.inducator;

import com.trade.bot.TradeData;

import java.util.List;
import java.util.Stack;

/**
 * @author Ozan Ay
 */
public class FisherTransform implements Inducator{
    private static final Stack<List<TradeData>> periodicDataStack = new Stack<>();

    @Override
    public AppliedInducatorResult apply(List<TradeData> tradeData) {
        
        return null;
    }
}
