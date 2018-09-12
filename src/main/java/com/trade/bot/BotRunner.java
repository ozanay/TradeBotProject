package com.trade.bot;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.beust.jcommander.JCommander;
import com.trade.bot.command.Command;
import com.trade.bot.command.CommandExecutor;
import com.trade.bot.command.CommandRepository;

public class BotRunner {
    private static final Logger logger = Logger.getLogger(BotRunner.class.getName());
    private static final String APPLICATION_NAME = "Trader";
    
    public static void main(String[] args) throws IOException {
        JCommander commander = new JCommander();
        commander.setProgramName(APPLICATION_NAME);
        if (args.length == 0) {
            commander.usage();
        } else {
            List<Command> commands = CommandRepository.getCommands();
        
            for (Command command : commands) {
                commander.addCommand(command);
            }
        
            Command commandObject = null;
            try {
                commander.parse(args);
                JCommander parsedCommander = commander.getCommands().get(commander.getParsedCommand());
                commandObject = (Command) parsedCommander.getObjects().get(0);
            } catch (RuntimeException exception) {
                logger.log(Level.SEVERE, "Command not recognized.", exception);
                commander.usage();
            }
            if (commandObject != null) {
                CommandExecutor commandExecutor = new CommandExecutor();
                commandObject.execute(commandExecutor);
            }
        }
    }
}
