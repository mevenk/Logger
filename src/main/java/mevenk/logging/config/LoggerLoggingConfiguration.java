package mevenk.logging.config;

import static mevenk.logging.config.LoggerLoggingConfigurationUtil.appenderRef;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.CONSOLE_APPENDER_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION;
import static mevenk.logging.config.LoggerLoggingConfigurationUtil.appLogRollingFileAppender;
import static mevenk.logging.config.LoggerLoggingConfigurationUtil.consoleAppender;
import static mevenk.logging.config.LoggerLoggingConfigurationUtil.socketAppender;
import static mevenk.logging.config.LoggerLoggingConfigurationUtil.rootLogger;
import static org.apache.logging.log4j.LogManager.ROOT_LOGGER_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationUtil.loggerConfig;
import static org.apache.logging.log4j.Level.ALL;
import java.util.ArrayList;
import java.util.List;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.APPENDER_APP_LOG_ROLLING_FILE_NAME;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.DefaultConfiguration;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.config.plugins.Plugin;

public class LoggerLoggingConfiguration {

	private static final String LOGGER_LOG4J2_CONFIGURATION_NAME = "Logger_Log4j2Configuration";

	@Plugin(category = ConfigurationFactory.CATEGORY, name = "LoggerConfigurationFactory")
	@Order(15)
	public static class LoggerConfigurationFactory extends ConfigurationFactory {

		@Override
		protected String[] getSupportedTypes() {
			return new String[] { "*" };
		}

		@Override
		public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource configurationSource) {
			Property.createProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION,
					System.getProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION));
			return new LoggerLog4j2Configuration(configurationSource);

		}

	}

	private static class LoggerLog4j2Configuration extends DefaultConfiguration {
		public LoggerLog4j2Configuration(ConfigurationSource configurationSource) {
			super.doConfigure();
			setName(LOGGER_LOG4J2_CONFIGURATION_NAME);
			ConsoleAppender consoleAppender = consoleAppender(this, isShutdownHookEnabled);
			addAppender(consoleAppender);
			SocketAppender socketAppender = socketAppender(this, "Venkatesh_Vaio", 4445);
			addAppender(socketAppender);
			RollingFileAppender appLogRollingFileAppender = appLogRollingFileAppender(this, isShutdownHookEnabled);
			addAppender(appLogRollingFileAppender);
			List<AppenderRef> appenderRefs = new ArrayList<>();
			appenderRefs.add(appenderRef(CONSOLE_APPENDER_NAME, null, null));
			List<Property> properties = new ArrayList<>();
			addLogger(ROOT_LOGGER_NAME, rootLogger(this, appenderRefs, properties));
			getLogger(ROOT_LOGGER_NAME).addAppender(consoleAppender, null, null);
			getLogger(ROOT_LOGGER_NAME).addAppender(socketAppender, null, null);
			appenderRefs.clear();
			appenderRefs.add(appenderRef(APPENDER_APP_LOG_ROLLING_FILE_NAME, ALL, null));
			addLogger("mevenk", loggerConfig(this, "mevenk", ALL, false, null, appenderRefs, properties));
		}
	}
}
