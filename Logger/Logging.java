package Logger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Logging {

    public static void log(String type, String log) {

        Logger logger = LoggerFactory.getLogger("Juego");

        switch(type){
            case "info":
                logger.info(log);
                break;
            case "warn":
                logger.warn(log);
                break;
            case "deb":
                logger.debug(log);
                break;
        }

    }
}
