package org.byovsiannikov.configserver.logger.behavoral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerDefault implements LoggerStrategy{

    private static final Logger logger= LoggerFactory.getLogger(LoggerDefault.class);
    @Override
    public void log (String message) {
        logger.info("Default logs: " + message);
    }
}
