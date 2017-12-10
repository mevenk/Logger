/**
 * 
 */
package mevenk.logging.config;

import java.io.File;
import java.nio.charset.Charset;

import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;

/**
 * @author Venkatesh
 *
 */
public final class LoggerLoggingConfigurationConstants {

	private LoggerLoggingConfigurationConstants() {
		throw new IllegalStateException("Utility class");

	}
	
	public static final Charset CHARSET = Charset.forName("UTF-8");
	
	public static final String FILE_PATH_SEPARATOR = File.pathSeparator;
	public static final String EXT_LOG = ".log";
	public static final String EXT_HTML = ".html";

	public static final String LOG_PATTERN_LAYOUT = "%d{MMMdd,yyyy-HH:mm:ss.SSS}#TypicalWebApp#[%X{LoggerAppCorrelationId}][%t]|%-5level|%C{1}#%M-%msg%n";
	public static final String LOG_FILE_PATTERN_SUFFIX = "_%d{yyyy-MM-dd}_%i";

	public static final String SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_HOST = "LoggerAppLog4jSocketHost";
	public static final String SYSTEM_PROPERTY_KEY_LOGGER_APP_LOG4J_SOCKET_PORT = "LoggerAppLog4jSocketPort";
	public static final String SYSTEM_PROPERTY_KEY_LOGGER_APP_LOGS_DIRECTORY_LOACTION = "LoggerAppLogsDirectoryLoaction";

	public static final String PROPERTY_UTIL_LOG_FILES_DIR = "utilLogFilesDir";
	public static final String PROPERTY_UTIL_LOG_FILE_NAME = "utilLogFileName";

	public static final String PROPERTY_APP_LOG_FILES_DIR = "appLogFilesDir";
	public static final String PROPERTY_APP_LOG_FILE_NAME = "appLogFileName";
	public static final String APPENDER_APP_LOG_ROLLING_FILE_NAME = "appLogRollingFile";

	public static final String PROPERTY_ERROR_LOG_FILES_DIR = "errorLogFilesDir";
	public static final String PROPERTY_ERROR_LOG_FILE_NAME = "errorLogFileName";

	public static final String PROPERTY_HTML_LOG_FILES_DIR = "htmlLogFilesDir";
	public static final String PROPERTY_HTML_LOG_FILE_NAME = "htmlLogFileName";

	public static final String PROPERTY_POLLING_LOG_FILES_DIR = "pollingLogFilesDir";
	public static final String PROPERTY_POLLING_LOG_FILE_NAME = "pollingLogFileName";

	public static final String PROPERTY_TRIGGER_LOG_FILES_DIR = "triggerLogFilesDir";
	public static final String PROPERTY_TRIGGER_LOG_FILE_NAME = "triggerLogFileName";

	public static final String PROPERTY_CONFIG_LOG_FILES_DIR = "configLogFilesDir";
	public static final String PROPERTY_CONFIG_LOG_FILE_NAME = "configLogFileName";

	public static final String PROPERTY_LOGGER_APP_LOG_PATTERN_LAYOUT = "LoggerAppLogPatternLayout";

	public static final String PATTERN_LAYOUT_PLUGIN_NAME = "PatternLayout";

	public static final String CONSOLE_APPENDER_PLUGIN_NAME = ConsoleAppender.PLUGIN_NAME;
	public static final String SOCKET_APPENDER_PLUGIN_NAME = "Socket";

	public static final String ROLLING_FILE_APPENDER_PLUGIN_NAME = RollingFileAppender.PLUGIN_NAME;

	public static final String CONSOLE_APPENDER_NAME = CONSOLE_APPENDER_PLUGIN_NAME + "_";
	public static final String SOCKET_APPENDER_NAME = SOCKET_APPENDER_PLUGIN_NAME + "_";

	public static final String PATTERN = "pattern";
	public static final String NAME = "name";
	public static final String FILE_NAME = "fileName";
	public static final String FILE_PATTERN = "filePattern";
	public static final String ADDITIVITY = "additivity";
	public static final String APPENDER_REF = "AppenderRef";
	public static final String REF = "ref";
	public static final String LEVEL = "level";
	public static final String FILTER = "filter";
	public static final String TARGET = "target";
	public static final String HOST = "host";
	public static final String PORT = "port";

}
