package org.byovsiannikov.configserver.logger.behavoral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerStudent implements LoggerStrategy {

    private static final Logger logger = LoggerFactory.getLogger(LoggerStudent.class);

    @Override
    public void log (String message) {
        logger.info("Student logs: " + message);
    }
}
