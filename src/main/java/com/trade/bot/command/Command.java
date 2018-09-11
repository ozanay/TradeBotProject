package com.trade.bot.command;

import java.io.IOException;

/**
 * @author Ozan Ay
 */
public interface Command {
    void execute(CommandExecutor commandExecutor) throws IOException;
}
