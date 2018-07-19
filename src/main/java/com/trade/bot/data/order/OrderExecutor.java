package com.trade.bot.data.order;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Ozan Ay
 */
public class OrderExecutor {
    private static final Logger LOGGER = Logger.getLogger(OrderExecutor.class.getName());
    public void buy(double price) {
        LOGGER.log(Level.INFO,"BUY price: " + price);
    }

    public void sell(double price) {
        LOGGER.log(Level.INFO,"SELL price: " + price);
    }
}
