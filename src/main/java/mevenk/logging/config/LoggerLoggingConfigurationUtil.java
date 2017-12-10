package mevenk.logging.config;

import static mevenk.logging.config.LoggerLoggingConfigurationConstants.APPENDER_APP_LOG_ROLLING_FILE_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.CHARSET;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.CONSOLE_APPENDER_NAME;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.LOG_PATTERN_LAYOUT;
import static mevenk.logging.config.LoggerLoggingConfigurationConstants.SOCKET_APPENDER_NAME;
import static org.apache.logging.log4j.Level.ALL;
import static org.apache.logging.log4j.core.appender.ConsoleAppender.Target.SYSTEM_OUT;
import static org.apache.logging.log4j.core.config.AppenderRef.createAppenderRef;

import java.util.List;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.Filter.Result;
import org.apache.logging.log4j.core.appender.ConsoleAppender;
import org.apache.logging.log4j.core.appender.RollingFileAppender;
import org.apache.logging.log4j.core.appender.SocketAppender;
import org.apache.logging.log4j.core.appender.rolling.SizeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.appender.rolling.TimeBasedTriggeringPolicy;
import org.apache.logging.log4j.core.config.AppenderRef;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.LoggerConfig;
import org.apache.logging.log4j.core.config.Property;
import org.apache.logging.log4j.core.filter.ThresholdFilter;
import org.apache.logging.log4j.core.layout.PatternLayout;
import org.apache.logging.log4j.core.net.SocketOptions;

public class LoggerLoggingConfigurationUtil {

	private LoggerLoggingConfigurationUtil() {
		throw new IllegalStateException("Utility class");
	}

	public static AppenderRef appenderRef(String ref, Level level, Filter filter) {
		return createAppenderRef(ref, level, filter);
	}

	public static SocketOptions socketOptions() {
		return SocketOptions.newBuilder().setKeepAlive(true).setSoTimeout(15000).build();
	}

	public static PatternLayout patternLayoutLogs(Configuration configuration, boolean isShutdownHookEnabled) {
		return PatternLayout.newBuilder().withPattern(LOG_PATTERN_LAYOUT).withPatternSelector(null)
				.withConfiguration(configuration).withRegexReplacement(null).withCharset(CHARSET)
				.withAlwaysWriteExceptions(isShutdownHookEnabled).withNoConsoleNoAnsi(isShutdownHookEnabled)
				.withHeader(null).withFooter(null).build();

	}

	public static ThresholdFilter thresholdFilter(Level level, Result match, Result mismatch) {
		return ThresholdFilter.createFilter(level, match, mismatch);
	}

	public static SizeBasedTriggeringPolicy sizeBasedTriggeringPolicy(String size) {
		return SizeBasedTriggeringPolicy.createPolicy(size);
	}

	public static TimeBasedTriggeringPolicy timeBasedTriggeringPolicy(int interval, boolean modulate) {
		return TimeBasedTriggeringPolicy.newBuilder().withInterval(interval).withModulate(modulate)
				.withMaxRandomDelay(1).build();
	}

	public static ConsoleAppender consoleAppender(Configuration configuration, boolean isShutdownHookEnabled) {
		return ConsoleAppender.newBuilder().setConfiguration(configuration).withName(CONSOLE_APPENDER_NAME)
				.setTarget(SYSTEM_OUT).withLayout(patternLayoutLogs(configuration, isShutdownHookEnabled)).build();
	}

	public static SocketAppender socketAppender(Configuration configuration, String socketHost, int socketPort) {
		return SocketAppender.newBuilder().setConfiguration(configuration).withName(SOCKET_APPENDER_NAME)
				.withHost(socketHost).withPort(socketPort).withSocketOptions(socketOptions()).build();
	}

	public static RollingFileAppender appLogRollingFileAppender(Configuration configuration,
			boolean isShutdownHookEnabled) {

		return RollingFileAppender.newBuilder().withName(APPENDER_APP_LOG_ROLLING_FILE_NAME)
				.withFileName("E:\\work\\temporary\\Logger\\LoggerAppRollingFile.log")
				.withFilePattern("E:\\work\\temporary\\Logger\\LoggerAppRollingFile.log_%d{yyyy-MM-dd}_%i")
				.withLayout(patternLayoutLogs(configuration, isShutdownHookEnabled)).build();
	}

	public static LoggerConfig loggerConfig(Configuration configuration, String loggerName, Level level,
			boolean additivity, Filter filter, List<AppenderRef> appenderRefs, List<Property> properties) {

		AppenderRef[] arrayAppenderRefs = new AppenderRef[appenderRefs.size()];
		arrayAppenderRefs = appenderRefs.toArray(arrayAppenderRefs);
		Property[] arrayProperties = new Property[properties.size()];
		arrayProperties = properties.toArray(arrayProperties);

		return LoggerConfig.createLogger(additivity, level, loggerName, "", arrayAppenderRefs, arrayProperties,
				configuration, filter);

	}

	public static LoggerConfig rootLogger(Configuration configuration, List<AppenderRef> appenderRefs,
			List<Property> properties) {

		AppenderRef[] arrayAppenderRefs = new AppenderRef[appenderRefs.size()];
		arrayAppenderRefs = appenderRefs.toArray(arrayAppenderRefs);
		Property[] arrayProperties = new Property[properties.size()];
		arrayProperties = properties.toArray(arrayProperties);

		return LoggerConfig.RootLogger.createLogger("false", ALL, "yes", arrayAppenderRefs, arrayProperties,
				configuration, null);
	}
}
