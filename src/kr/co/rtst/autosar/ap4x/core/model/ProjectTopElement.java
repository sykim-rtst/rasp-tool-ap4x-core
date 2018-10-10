package kr.co.rtst.autosar.ap4x.core.model;

public class ProjectTopElement implements IAPTopElement{
	
	private final IAdaptiveAutosarProject apProject;
	private final String name;
	private final String packageName;
	
	public ProjectTopElement(IAdaptiveAutosarProject apProject, String name, String packageName) {
		super();
		this.apProject = apProject;
		this.name = name;
		this.packageName = packageName;
	}
	
	@Override
	public IAdaptiveAutosarProject getApProject() {
		return this.apProject;
	}

	public String getName() {
		return name;
	}
	
	public String getPackageName() {
		return packageName;
	}

}
