package org.byovsiannikov.configserver.logger.creational;

import org.byovsiannikov.configserver.logger.behavoral.LoggerStrategy;

public class LoggerSingleton {

    private static LoggerSingleton instance;
    private LoggerStrategy loggerStrategy;

    private LoggerSingleton() {}

    public static LoggerSingleton getInstance() {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public LoggerSingleton setLoggerStrategy(LoggerStrategy strategy) {
        this.loggerStrategy = strategy;
        return this;
    }

    public void log(String message) {
        if (loggerStrategy != null) {
            loggerStrategy.log(message);
        } else {
            throw new IllegalStateException("Logger strategy not set");
        }
    }
}
