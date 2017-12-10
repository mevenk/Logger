/**
 * 
 */
package mevenk.logging.config;

import java.util.Date;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * @author Venkatesh
 *
 */
public class Log4j2Config {

	Logger LOGGER = LogManager.getLogger(getClass());

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Properties properties = new Properties();
		properties.setProperty(LoggerLoggingConfigurationConstants.PROPERTY_UTIL_LOG_FILES_DIR, "/");
		properties.setProperty("log4j2.debug", "true");
		properties.setProperty("log4j.configurationFactory", "mevenk.logging.config.CustomConfigurationFactory");
		System.setProperties(new Properties());
		new Log4j2Config().configureLog4j2();

	}

	private void configureLog4j2() {
		for (int i = 0; i < 10; i++) {
			LOGGER.debug("Date: {}", new Date());
		}
	}

}
