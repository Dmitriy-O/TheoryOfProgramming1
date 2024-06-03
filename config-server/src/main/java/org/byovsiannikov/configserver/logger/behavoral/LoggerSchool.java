package org.byovsiannikov.configserver.logger.behavoral;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerSchool implements LoggerStrategy {
private final static Logger logger= LoggerFactory.getLogger(LoggerSchool.class);
    @Override
    public void log (String message) {
        logger.info("School logs: " + message);
    }
}
