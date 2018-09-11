package com.trade.bot.command;


import java.util.Arrays;
import java.util.List;

/**
 * @author Ozan Ay
 */
public final class CommandRepository {

    private CommandRepository(){}
    /**
     * Creates a list of accepted {@link Command} objects.
     *
     * @return A new list of commands.
     */
    public static List<Command> getCommands() {
        return Arrays.asList(new StartCommand(), new StopCommand());
    }
}
