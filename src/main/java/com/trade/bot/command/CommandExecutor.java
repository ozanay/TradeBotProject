package com.trade.bot.command;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.beust.jcommander.JCommander;

/**
 * @author Ozan Ay
 */
public class CommandExecutor {
    private static final Logger logger = Logger.getLogger(CommandExecutor.class.getName());
    private static final JCommander commander = new JCommander();
    public CommandExecutor(String applicationName) {
        commander.setProgramName(applicationName);
    }
    
    public void execute(String[] args) {
        if (args.length == 0) {
            commander.usage();
        } else {
            List<ControllerCommand> commands = CommandRepository.getCommands();
        
            for (ControllerCommand command : commands) {
                commander.addCommand(command);
            }
        
            ControllerCommand commandObject = null;
            try {
                commander.parse(args);
                JCommander parsedCommander = commander.getCommands().get(commander.getParsedCommand());
                commandObject = (ControllerCommand) parsedCommander.getObjects().get(0);
            } catch (RuntimeException exception) {
                logger.log(Level.SEVERE,"Command not recognized.", exception);
                commander.usage();
            }
            if (commandObject != null) {
                    commandObject.execute(args);
            }
        }
    }
}
