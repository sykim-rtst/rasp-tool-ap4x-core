package kr.co.rtst.autosar.ap4x.core.model;

import org.eclipse.core.resources.IProject;

public class AdaptiveAutosarProject implements IAdaptiveAutosarProject{
	
	private final IProject project;
	private final IAPTopElement types;
	private final IAPTopElement applications;
	private final IAPTopElement services;
	private final IAPTopElement machines;
	private final IAPTopElement[] topElements;
	
	public AdaptiveAutosarProject(IProject project) {
		super();
		this.project = project;
		
		types = new ProjectTopElement(this, "Types", "types");
		applications = new ProjectTopElement(this, "Applications", "applications");
		services = new ProjectTopElement(this, "Services", "services");
		machines = new ProjectTopElement(this, "Machines", "machines");
		
		topElements = new IAPTopElement[4];
		topElements[0] = types;
		topElements[1] = applications;
		topElements[2] = services;
		topElements[3] = machines;
	}
	
	@Override
	public IAPTopElement[] getTopElements() {
		return topElements;
	}

	public IProject getProject() {
		return project;
	}

	public IAPTopElement getTypes() {
		return types;
	}

	public IAPTopElement getApplications() {
		return applications;
	}

	public IAPTopElement getServices() {
		return services;
	}

	public IAPTopElement getMachines() {
		return machines;
	}
	
}
