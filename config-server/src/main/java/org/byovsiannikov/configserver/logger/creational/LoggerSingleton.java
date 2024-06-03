package org.byovsiannikov.configserver.logger.creational;

import org.byovsiannikov.configserver.logger.behavoral.LoggerStrategy;

public class LoggerSingleton {

    private static LoggerSingleton instance;
    private LoggerStrategy loggerStrategy;

    private LoggerSingleton () {}

    public static LoggerSingleton getInstance () {
        if (instance == null) {
            instance = new LoggerSingleton();
        }
        return instance;
    }

    public LoggerSingleton setLoggerStrategy (LoggerStrategy strategy) {
        this.loggerStrategy = strategy;
        return instance;
    }

    public void log (String message) {
        loggerStrategy.log(message);
    }

}
