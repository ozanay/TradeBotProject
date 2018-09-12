package com.trade.bot.command;

import com.beust.jcommander.Parameters;

/**
 * @author Ozan Ay
 */
@Parameters (commandNames = "stop", commandDescription = "Stops the trade bot.")
public class StopCommand implements Command{
    
    @Override
    public void execute(CommandExecutor commandExecutor) {
        commandExecutor.stop();
    }
}
