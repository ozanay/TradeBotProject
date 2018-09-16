package com.trade.bot;

import java.io.IOException;

import org.jetbrains.annotations.Contract;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;

public class Trader {
    @Parameter(names = {"-c"}, description = "Configuration file for bot to run.",
            required = true)
    private static String configuration;

    @Contract("null -> fail")
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments cannot be empty!");
        }

        Trader trader = new Trader();
        JCommander commander = new JCommander(trader);
        commander.parse(args);

        TradeExecutor tradeExecutor = new TradeExecutor();
        tradeExecutor.start(configuration);
    }
}
