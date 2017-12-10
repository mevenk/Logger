/**
 * 
 */
package mevenk.logging.config;

import static mevenk.logging.config.CustomConfigurationFactoryBuilder.getApplicationLog4j2Configuration;

import java.net.URI;

import org.apache.logging.log4j.core.LoggerContext;
import org.apache.logging.log4j.core.config.Configuration;
import org.apache.logging.log4j.core.config.ConfigurationFactory;
import org.apache.logging.log4j.core.config.ConfigurationSource;
import org.apache.logging.log4j.core.config.Order;
import org.apache.logging.log4j.core.config.plugins.Plugin;

/**
 * @author Venkatesh
 *
 */
//@Plugin(name = "CustomConfigurationFactory", category = ConfigurationFactory.CATEGORY)
//@Order(1)
public class CustomConfigurationFactory extends ConfigurationFactory {
	
	private ConfigurationSource configurationSource;
	
	@Override
	public Configuration getConfiguration(LoggerContext loggerContext, ConfigurationSource configurationSource) {
		this.configurationSource = configurationSource;
		return getConfiguration(loggerContext, configurationSource.toString(), null);
	}

	@Override
	public Configuration getConfiguration(final LoggerContext loggerContext, final String name,
			final URI configLocation) {
		return getApplicationLog4j2Configuration(loggerContext, configurationSource, name);
	}

	@Override
	protected String[] getSupportedTypes() {
		return new String[] { "*" };
	}

}
