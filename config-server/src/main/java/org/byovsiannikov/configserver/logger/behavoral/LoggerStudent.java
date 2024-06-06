package org.byovsiannikov.configserver.logger.behavoral;

import org.byovsiannikov.configserver.logger.structural.LoggerDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerStudent extends LoggerDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LoggerStudent.class);

    public LoggerStudent(LoggerStrategy decoratedLogger) {
        super(decoratedLogger);
    }

    @Override
    public void log(String message) {
        super.log(message);
        logger.info("Student log: " + message);
    }
}
