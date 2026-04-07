package com.utils;

import java.util.logging.ConsoleHandler;
import java.util.logging.Formatter;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

public final class LogUtil {

    private static final Logger logger = Logger.getLogger("AutomationFramework");

    static {
        logger.setUseParentHandlers(false);
        ConsoleHandler handler = new ConsoleHandler();
        handler.setLevel(Level.ALL);
        handler.setFormatter(new Formatter() {
            @Override
            public String format(LogRecord record) {
                return String.format("[%s] [%s] %s%n",
                        record.getLevel(),
                        Thread.currentThread().getName(),
                        record.getMessage());
            }
        });
        logger.addHandler(handler);
        logger.setLevel(Level.ALL);
    }

    private LogUtil() {}

    public static void info(String message) {
        logger.info(message);
    }

    public static void warn(String message) {
        logger.warning(message);
    }

    public static void error(String message) {
        logger.severe(message);
    }
}
