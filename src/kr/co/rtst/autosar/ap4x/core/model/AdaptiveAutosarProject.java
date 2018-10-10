package kr.co.rtst.autosar.ap4x.core.model;

import org.eclipse.core.resources.IProject;

public class AdaptiveAutosarProject implements IAdaptiveAutosarProject{
	
	private final IProject project;
	private final ProjectTopElement types;
	private final ProjectTopElement applications;
	private final ProjectTopElement services;
	private final ProjectTopElement machines;
	
	public AdaptiveAutosarProject(IProject project) {
		super();
		this.project = project;
		types = new ProjectTopElement("Types", "types");
		applications = new ProjectTopElement("Applications", "applications");
		services = new ProjectTopElement("Services", "services");
		machines = new ProjectTopElement("Machines", "machines");
	}

	public IProject getProject() {
		return project;
	}

	public ProjectTopElement getTypes() {
		return types;
	}

	public ProjectTopElement getApplications() {
		return applications;
	}

	public ProjectTopElement getServices() {
		return services;
	}

	public ProjectTopElement getMachines() {
		return machines;
	}
	
}
