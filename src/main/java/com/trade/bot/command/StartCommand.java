package com.trade.bot.command;

import java.io.IOException;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

/**
 * @author Ozan Ay
 */
@Parameters (commandNames = "start", commandDescription = "Starts the trade bot.")
public class StartCommand implements Command {
    @Parameter (names = {"--configuration", "-c"}, description = "Configuration file for bot to run.",
        required = true)
    private String configuration;
    
    @Override
    public void execute(CommandExecutor commandExecutor) throws IOException {
        commandExecutor.start(configuration);
    }
}
