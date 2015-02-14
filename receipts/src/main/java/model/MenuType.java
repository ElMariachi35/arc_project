package model;

public enum MenuType {
	STARTER("Vorspeise"), MAIN("Hauptspeise"), DESSERT("Nachspeise");

	private MenuType (String name){
		this.displayName =name;
	}
	
	private String displayName;

	public String getDisplayName() {
		return displayName;
	}
}
