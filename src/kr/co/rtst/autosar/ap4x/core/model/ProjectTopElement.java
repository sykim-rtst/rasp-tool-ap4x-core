package kr.co.rtst.autosar.ap4x.core.model;

public class ProjectTopElement {
	
	private final String name;
	private final String packageName;
	
	public ProjectTopElement(String name, String packageName) {
		super();
		this.name = name;
		this.packageName = packageName;
	}

	public String getName() {
		return name;
	}
	
	public String getPackageName() {
		return packageName;
	}

}
