package com.trade.bot.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that holds the {@link ControllerCommand} commands for agent management.
 *
 * @author Selen Kartoglu
 * @author Ozan Ay
 */
public final class CommandRepository {

    /**
     * Private constructor responsible for creating commands.
     */
    private CommandRepository(){
        //Empty due to static method calls.
    }
    /**
     * Creates a list of accepted {@link ControllerCommand} objects.
     *
     * @return A new list of commands.
     */
    public static List<ControllerCommand> getCommands() {
        List<ControllerCommand> commands = new ArrayList<>();
        
        return commands;
    }
}
