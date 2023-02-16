
package mod.gottsch.fabric.gottschcore;

import mod.gottsch.fabric.gottschcore.config.CoreSimpleConfig;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GottschCore implements ModInitializer {
	// constants
	public static final String MODID = "gottschcore";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		// register configs
		// NOTE ensure that these properties are only used server-side and not for registration properties.
		CoreSimpleConfig.register(MODID + "-config");
		CoreSimpleConfig.instance.addRollingFileAppender(MODID);
		LOGGER.info("GottschCore by gottsch.");
	}
}
