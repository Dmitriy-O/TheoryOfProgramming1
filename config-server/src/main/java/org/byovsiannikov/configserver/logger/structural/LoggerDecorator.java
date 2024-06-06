package org.byovsiannikov.configserver.logger.structural;

import org.byovsiannikov.configserver.logger.behavoral.LoggerStrategy;

public abstract class LoggerDecorator implements LoggerStrategy {
    protected LoggerStrategy decoratedLogger;

    public LoggerDecorator(LoggerStrategy decoratedLogger) {
        this.decoratedLogger = decoratedLogger;
    }

    @Override
    public void log(String message) {
        decoratedLogger.log(message);
    }
}
