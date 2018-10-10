package kr.co.rtst.autosar.ap4x.core.model;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import kr.co.rtst.autosar.ap4x.core.nature.AdaptiveAutosarProjectNature;

public class ProjectManager {

private static ProjectManager instance;
	
	private Map<IProject, IAdaptiveAutosarProject> coreModelRegistry;
	
	public static ProjectManager getInstance() {
		if(instance == null) {
			instance = new ProjectManager();
		}
		return instance;
	}
	
	private ProjectManager() {
		coreModelRegistry = new HashMap<>();
	}
	
	public IAdaptiveAutosarProject getAdaptiveAutosarProject(IProject project) {
		if(!coreModelRegistry.containsKey(project)) {
			coreModelRegistry.put(project, new AdaptiveAutosarProject(project));
		}
		return coreModelRegistry.get(project);
	}
	
	/**
	 * 주어진 프로젝트에 필요한 네이처 정보를 추가한다.
	 * @param project
	 * @param monitor
	 * @throws CoreException
	 */
	public static void addRTSTAdaptiveAutosarNatures(IProject project, IProgressMonitor monitor) throws CoreException {
		String[] natureIds = new String[] {
			AdaptiveAutosarProjectNature.ID,
		};
		
		try {
			if (monitor == null) {
				monitor = new NullProgressMonitor();
			}
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length+natureIds.length];
			
			System.arraycopy(natureIds, 0, newNatures, 0, natureIds.length);
			System.arraycopy(prevNatures, 0, newNatures, natureIds.length, prevNatures.length);
			
//			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
//			System.arraycopy(natureIds, 0, newNatures, prevNatures.length, natureIds.length);
			
			description.setNatureIds(newNatures);

			project.setDescription(description, IResource.FORCE, monitor);
			
		} finally {
			monitor.done();
		}
	}
}
