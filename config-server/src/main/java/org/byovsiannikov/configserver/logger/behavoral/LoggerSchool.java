package org.byovsiannikov.configserver.logger.behavoral;

import org.byovsiannikov.configserver.logger.structural.LoggerDecorator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerSchool extends LoggerDecorator {

    private static final Logger logger = LoggerFactory.getLogger(LoggerSchool.class);

    public LoggerSchool(LoggerStrategy loggerStrategy) {
        super(loggerStrategy);
    }

    @Override
    public void log(String message) {
        super.log(message);
        logger.info("School log: " + message);
    }
}
