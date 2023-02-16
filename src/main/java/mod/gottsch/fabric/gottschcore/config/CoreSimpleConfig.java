package mod.gottsch.fabric.gottschcore.config;

/**
 * Created by Mark Gottschling on 2/7/2023
 */
public class CoreSimpleConfig extends AbstractSimpleConfig {
	public static CoreSimpleConfig instance = new CoreSimpleConfig();

	private CoreSimpleConfig() {}

    @Override
	public SimpleConfig.DefaultConfig getProvider() {
		return new SimpleConfig.DefaultConfig() {
			@Override
			public String get(String namespace) {
                // NOTE GottschCore doesn't have any properties of it's own
                // so return just the LOGGING properties
				return  LOGGING.get(namespace);
			}
		};
	}

	/**
	 *
	 * @param filename
	 */
    public static void register(String filename) {
		SimpleConfig CONFIG = SimpleConfig.of(filename).provider(instance.getProvider()).request();
		instance.register(CONFIG);
		// load any additional config properties
	}

	public String getLogsFolder() {
		return CoreSimpleConfig.folder;
	}

	public String getLogSize() {
		return CoreSimpleConfig.size;
	}

	public String getLoggingLevel() {
		return CoreSimpleConfig.level;
	}
}
