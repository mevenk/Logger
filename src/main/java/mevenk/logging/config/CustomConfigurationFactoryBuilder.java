/**
 * 
 */
package mevenk.logging.config;

import static mevenk.logging.config.LoggerLoggingConfigurationConstants.ADDITIVITY;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.APPENDER_APP_LOG_ROLLING_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.APPENDER_REF;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.CONSOLE_APPENDER_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.CONSOLE_APPENDER_PLUGIN_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.EXT_HTML;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.EXT_LOG;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.FILE_PATH_SEPARATOR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.FILE_PATTERN;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.FILTER;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.HOST;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.LEVEL;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.LOG_FILE_PATTERN_SUFFIX;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.LOG_PATTERN_LAYOUT;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PATTERN;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PATTERN_LAYOUT_PLUGIN_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PORT;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_APP_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_APP_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_CONFIG_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_CONFIG_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_ERROR_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_ERROR_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_HTML_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_HTML_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_HOST;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_PORT;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_LOGGER_APP_LOG_PATTERN_LAYOUT;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_POLLING_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_POLLING_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_TRIGGER_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_TRIGGER_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_UTIL_LOG_FILES_DIR;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.PROPERTY_UTIL_LOG_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.REF;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.ROLLING_FILE_APPENDER_PLUGIN_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SOCKET_APPENDER_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SOCKET_APPENDER_PLUGIN_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.TARGET;
import static org.apache.logging.log4j.Level.ALL;
import static org.apache.logging.log4j.Level.INFO;
import static org.apache.logging.log4j.core.appender.ConsoleAppender.Target.SYSTEM_OUT;
import static org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilderFactory.newConfigurationBuilder;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.builder.api.AppenderComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.api.LayoutComponentBuilder;
import org.apache.logging.log4j.core.config.builder.api.LoggerComponentBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

/**
 * @author Venkatesh
 *
 */
public final class CustomConfigurationFactoryBuilder {

	private static ConfigurationBuilder<BuiltConfiguration> configurationBuilder;

	private CustomConfigurationFactoryBuilder() {

	}

	public static Configuration getApplicationLog4j2Configuration(LoggerContext loggerContext,
			ConfigurationSource configurationSource, String configurationName) {
		configurationBuilder = newConfigurationBuilder();
		configurationBuilder.setConfigurationName(configurationName);
		configurationBuilder.setLoggerContext(loggerContext);
		configurationBuilder.setConfigurationSource(configurationSource);
		addProperties();
		generateRollingFileAppenders();
		generateConsoleAppender();
		generateSocketAppender();
		addLoggers();
		addRootLogger();
		return configurationBuilder.build();

	}

	private static void addProperties() {

		configurationBuilder.addProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_HOST,
				System.getProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_HOST));
		configurationBuilder.addProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_PORT,
				System.getProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_PORT));
		configurationBuilder.addProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION,
				System.getProperty(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION));

		configurationBuilder.addProperty(PROPERTY_UTIL_LOG_FILES_DIR, logsDirLoc("utilLogs"));
		configurationBuilder.addProperty(PROPERTY_UTIL_LOG_FILE_NAME, logFileName("LoggerAppUtil"));

		configurationBuilder.addProperty(PROPERTY_APP_LOG_FILES_DIR, logsDirLoc("appLogs"));
		configurationBuilder.addProperty(PROPERTY_APP_LOG_FILE_NAME, logFileName("LoggerApp"));

		configurationBuilder.addProperty(PROPERTY_ERROR_LOG_FILES_DIR, logsDirLoc("errorLogs"));
		configurationBuilder.addProperty(PROPERTY_ERROR_LOG_FILE_NAME, logFileName("LoggerAppError"));

		configurationBuilder.addProperty(PROPERTY_HTML_LOG_FILES_DIR, logsDirLoc("htmlLogs"));
		configurationBuilder.addProperty(PROPERTY_HTML_LOG_FILE_NAME, htmlFileName("LoggerAppLog"));

		configurationBuilder.addProperty(PROPERTY_POLLING_LOG_FILES_DIR, logsDirLoc("pollingLogs"));
		configurationBuilder.addProperty(PROPERTY_POLLING_LOG_FILE_NAME, logFileName("LoggerAppPolling"));

		configurationBuilder.addProperty(PROPERTY_TRIGGER_LOG_FILES_DIR, logsDirLoc("triggerLogs"));
		configurationBuilder.addProperty(PROPERTY_TRIGGER_LOG_FILE_NAME, logFileName("LoggerAppTrigger"));

		configurationBuilder.addProperty(PROPERTY_CONFIG_LOG_FILES_DIR, logsDirLoc("configLogs"));
		configurationBuilder.addProperty(PROPERTY_CONFIG_LOG_FILE_NAME, logFileName("LoggerAppConfig"));

		configurationBuilder.addProperty(PROPERTY_LOGGER_APP_LOG_PATTERN_LAYOUT, LOG_PATTERN_LAYOUT);

	}

	private static String logsDirLoc(String directoryName) {
		return configurationBuilder.build(false).getProperties().get(SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION)
				+ FILE_PATH_SEPARATOR + directoryName;
	}

	private static String logFileName(String logFileName) {
		return logFileName + EXT_LOG;
	}

	private static String htmlFileName(String htmlFileName) {
		return htmlFileName + EXT_HTML;
	}

	private static String getProperty(String propertyName) {
		return configurationBuilder.build(false).getProperties().get(propertyName);
	}

	private static void generateRollingFileAppenders() {
		generateAppLogRollingFileAppender();
	}

	private static void generateAppLogRollingFileAppender() {
		configurationBuilder.add(rollingFileAppender(APPENDER_APP_LOG_ROLLING_FILE_NAME,
				getLogFilePath(PROPERTY_APP_LOG_FILES_DIR, PROPERTY_APP_LOG_FILE_NAME)));
	}

	private static AppenderComponentBuilder rollingFileAppender(String rollingFileAppenderName, String logFilePath) {
		AppenderComponentBuilder rollingFileAppender = configurationBuilder.newAppender(rollingFileAppenderName,
				ROLLING_FILE_APPENDER_PLUGIN_NAME);
		addRollingFileAppenderAttributes(rollingFileAppender, rollingFileAppenderName, logFilePath);
		addPatternLayout(rollingFileAppender);
		return rollingFileAppender;
	}

	private static void generateConsoleAppender() {
		AppenderComponentBuilder consoleAppender = configurationBuilder.newAppender(CONSOLE_APPENDER_NAME,
				CONSOLE_APPENDER_PLUGIN_NAME);
		consoleAppender.addAttribute(TARGET, SYSTEM_OUT);
		addPatternLayout(consoleAppender);
	}

	private static void generateSocketAppender() {
		AppenderComponentBuilder socketAppender = configurationBuilder.newAppender(SOCKET_APPENDER_NAME,
				SOCKET_APPENDER_PLUGIN_NAME);
		socketAppender.addAttribute(HOST, SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_HOST);
		socketAppender.addAttribute(PORT, SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_PORT);
	}

	private static void addLoggers() {
		generateAndAddLogger(Package.getPackage("mevenk.logging.configs").getClass(), ALL, false,
				appenderRef(APPENDER_APP_LOG_ROLLING_FILE_NAME, INFO, null));
	}

	private static void generateAndAddLogger(Class<?> name, Level level, boolean additivity,
			AppenderRef... appenderRefs) {
		LoggerComponentBuilder loggerComponentBuilder = configurationBuilder.newAsyncLogger(name.getName(), level);
		loggerComponentBuilder.addAttribute(ADDITIVITY, additivity);

		for (AppenderRef appenderRefCurrent : appenderRefs) {
			loggerComponentBuilder.addComponent(configurationBuilder.newComponent(APPENDER_REF)
					.addAttribute(REF, appenderRefCurrent.getRef()).addAttribute(LEVEL, appenderRefCurrent.getLevel())
					.addAttribute(FILTER, appenderRefCurrent.getFilter()));
		}

		configurationBuilder.add(loggerComponentBuilder);
	}

	private static AppenderRef appenderRef(String ref, Level level, Filter filter) {
		return AppenderRef.createAppenderRef(ref, level, filter);
	}

	private static void addRootLogger() {
		configurationBuilder.newAsyncRootLogger(ALL).add(configurationBuilder.newAppenderRef(CONSOLE_APPENDER_NAME))
				.add(configurationBuilder.newAppenderRef(SOCKET_APPENDER_NAME));
	}

	private static String getLogFilePath(String propertyDir, String propertyFileName) {
		return getProperty(propertyDir) + FILE_PATH_SEPARATOR + getProperty(propertyFileName);
	}

	private static void addRollingFileAppenderAttributes(AppenderComponentBuilder rollingFileAppender,
			String rollingFileAppenderName, String logFilePath) {
		rollingFileAppender.addAttribute(NAME, rollingFileAppenderName);
		rollingFileAppender.addAttribute(FILE_NAME, logFilePath);
		rollingFileAppender.addAttribute(FILE_PATTERN, logFilePath + LOG_FILE_PATTERN_SUFFIX);
	}

	private static void addPatternLayout(AppenderComponentBuilder appenderComponentBuilder) {
		LayoutComponentBuilder patternlayout = configurationBuilder.newLayout(PATTERN_LAYOUT_PLUGIN_NAME);
		patternlayout.addAttribute(PATTERN, LOG_PATTERN_LAYOUT);
		appenderComponentBuilder.add(patternlayout);
	}
}
