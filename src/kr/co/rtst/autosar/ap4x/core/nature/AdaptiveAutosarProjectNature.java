package kr.co.rtst.autosar.ap4x.core.nature;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectNature;
import org.eclipse.core.runtime.CoreException;

public class AdaptiveAutosarProjectNature implements IProjectNature {
	
	public static final String ID = "kr.co.rtst.autosar.ap4x.core.AdaptiveAutosarNature";
	
	private IProject project;
	
	@Override
	public void configure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public void deconfigure() throws CoreException {
		// TODO Auto-generated method stub

	}

	@Override
	public IProject getProject() {
		return this.project;
	}

	@Override
	public void setProject(IProject project) {
		this.project = project;
	}

}