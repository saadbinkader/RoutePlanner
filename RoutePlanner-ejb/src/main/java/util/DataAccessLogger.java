package util;

import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Singleton;

/**
 * Created with IntelliJ IDEA.
 * User: saad
 * Date: 10/12/13
 * Time: 7:55 PM
 * To change this template use File | Settings | File Templates.
 */

@Singleton
@LocalBean
public class DataAccessLogger {
    private Logger logger;

    public Logger getLogger() {
        return logger;
    }

    public void setLogger(Logger logger) {
        this.logger = logger;
    }

    @PostConstruct
    public void setup() {
        logger = Logger.getRootLogger();
    }
}
