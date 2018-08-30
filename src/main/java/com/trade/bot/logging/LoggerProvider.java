package com.trade.bot.logging;

import java.io.IOException;
import java.util.logging.*;

/**
 * @author Ozan Ay
 */
public class LoggerProvider {
    private static final Logger LOGGER = Logger.getLogger(LoggerProvider.class.getName());
    private static final ConsoleHandler consoleHandler = new ConsoleHandler();
    private static final Formatter formatter = new SimpleFormatter();
    private static FileHandler fileHandler = null;

    private LoggerProvider() {}

    public static Logger getLogger(String name) {
        Logger logger = Logger.getLogger(name);
        addFileHandler(logger);

        return logger;
    }

    public static void configureLoggerProvider(String logFilePath) throws IOException {
        fileHandler = new FileHandler(logFilePath);
        fileHandler.setFormatter(formatter);

        fileHandler.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        addFileHandler(LOGGER);
    }

    private static void addFileHandler(Logger logger) {
        if (logger != null) {
            logger.addHandler(fileHandler);
        }
    }
}
