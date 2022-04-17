package org.gloryseekers.infra.log;



import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.gloryseekers.domain.model.LogType;

public class GSLogger {

    public static void log(Class cls, LogType type, String msg) {
        
        Logger log = LogManager.getLogger(cls);
		
		switch (type) 
		{
			case DEBUG:
				log.debug(msg);
				break;
			case ERROR:
				log.error(msg);
				break;
			case FATAL:
				log.fatal(msg);
				break;
			case INFO:
				log.info(msg);
				break;
			case WARNING:
				log.warn(msg);
		}
        
    }

    
    
}
