package at.yedel.antimations.config;



public enum ConfigCategory {
	SWING_CUSTOMIZATION("Swing Customization"),
	ITEM_RESET("Item Reset Customization"),
	OTHER("Other");

	private final String name;

	public String getName() {
		return name;
	}

	ConfigCategory(String name) {
		this.name = name;
	}
}
