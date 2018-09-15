package com.trade.bot;

import java.io.IOException;

import org.jetbrains.annotations.Contract;

import com.beust.jcommander.JCommander;
import com.trade.bot.command.Command;
import com.trade.bot.command.CommandExecutor;
import com.trade.bot.command.CommandRepository;

public class BotRunner {
    private static final String APPLICATION_NAME = "Trader";
    private static final CommandExecutor commandExecutor = new CommandExecutor();
    private static final JCommander commander = new JCommander();
    static {
        commander.setProgramName(APPLICATION_NAME);
        addAllCommands();
    }

    @Contract("null -> fail")
    public static void main(String[] args) throws IOException {
        if (args == null || args.length == 0) {
            throw new IllegalArgumentException("Arguments cannot be empty!");
        }

        Command command = parseCommand(args);
        command.execute(commandExecutor);
    }

    private static Command parseCommand(String[] args) {
        commander.parse(args);
        JCommander parsedCommander = commander.getCommands().get(commander.getParsedCommand());
        return (Command) parsedCommander.getObjects().get(0);
    }

    private static void addAllCommands() {
        CommandRepository.getCommands().forEach(BotRunner::addCommand);
    }
    
    private static void addCommand(Command command) {
        commander.addCommand(command);
    }
}
