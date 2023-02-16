package mod.gottsch.fabric.gottschcore.config;

/**
 * 
 * @author Mark Gottschling on Feb 7, 2023
 *
 */
public abstract class AbstractSimpleConfig implements IConfig {
	public static String level;
	public static String folder;
	public static String size;

	public static final SimpleConfig.DefaultConfig LOGGING;

	static {
		LOGGING = new SimpleConfig.DefaultConfig() {
			@Override
			public String get(String namespace) {
				return  "###############################\n" +
						"# Logging Properties\n" +
						"###############################\n" +
						"# The logging level. Set to 'off' to disable logging.\n" +
						"# Values = [trace|debug|info|warn|error|off]\n" +
						"# Default: info\n" +
						"level=" + DEFAULT_LOGGER_LEVEL + "\n" +
						"# The size a log file can be before rolling over to a new file.\n" +
						"size=" + DEFAULT_LOGGER_SIZE + "\n" +
						"# The directory where the logs should be stored.\n" +
						"# This is relative to the Minecraft install path.\n" +
						"folder=" + DEFAULT_LOGGER_FOLDER + "\n";
			}
		};
	}

	/**
	 *
	 * @return
	 */
	public abstract SimpleConfig.DefaultConfig getProvider();

	/**
	 *
	 * @param config
	 */
	public void register(SimpleConfig config) {
		level = config.getOrDefault("level", DEFAULT_LOGGER_LEVEL);
		size = config.getOrDefault("size", DEFAULT_LOGGER_SIZE);
		folder = config.getOrDefault("folder", DEFAULT_LOGGER_FOLDER);
	}
}
