package kr.co.rtst.autosar.ap4x.core.util;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import kr.co.rtst.autosar.ap4x.core.nature.AdaptiveAutosarProjectNature;

public class AdaptiveAutosarProjectUtil {

	public static final int[] DEFAULT_TOP_DIR_ORDER = {1, 2, 3};
	public static final String[] DEFAULT_TOP_DIR = {"Software", "Service", "Machine"};
	
	public static final String ADAPTIVE_APPLICATION_SW_COMPONENT_TYPE_EXTENSION = ".aaswc";
	
	public static boolean isSoftwareTopFolder(String name) {
		return DEFAULT_TOP_DIR[0].equals(name);
	}
	
	public static boolean isServiceTopFolder(String name) {
		return DEFAULT_TOP_DIR[1].equals(name);
	}
	
	public static boolean isMachineTopFolder(String name) {
		return DEFAULT_TOP_DIR[2].equals(name);
	}
	
	public static boolean isAdaptiveAutosarTopFolder(Object folderObject) {
		if(folderObject instanceof IFolder && ((IFolder) folderObject).getParent() instanceof IProject) {
			final String name = ((IFolder) folderObject).getName();
			for (int i = 0; i < DEFAULT_TOP_DIR.length; i++) {
				if(DEFAULT_TOP_DIR[i].equals(name)) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * 고정적으로 존재하는 최상위 폴더들을 정렬한다.
	 * @param f1
	 * @param f2
	 * @return
	 */
	public static int compareTopFolder(String f1, String f2) {
		int v1 = Integer.MAX_VALUE;
		int v2 = Integer.MAX_VALUE;
		if(isSoftwareTopFolder(f1)) {
			v1 = DEFAULT_TOP_DIR_ORDER[0];
		} else if(isServiceTopFolder(f1)) {
			v1 = DEFAULT_TOP_DIR_ORDER[1];
		} else if(isMachineTopFolder(f1)) {
			v1 = DEFAULT_TOP_DIR_ORDER[2];
		}
		
		if(isSoftwareTopFolder(f2)) {
			v2 = DEFAULT_TOP_DIR_ORDER[0];
		} else if(isServiceTopFolder(f2)) {
			v2 = DEFAULT_TOP_DIR_ORDER[1];
		} else if(isMachineTopFolder(f2)) {
			v2 = DEFAULT_TOP_DIR_ORDER[2];
		}
		
		if(v1 == Integer.MAX_VALUE && v2 == Integer.MAX_VALUE) {
			return f1.compareTo(f2);
		}else {
			return Integer.compare(v1, v2);
		}
	}
	
	public static boolean isAdaptiveAutosarProject(IProject project) {
		try {
			return project.hasNature(AdaptiveAutosarProjectNature.ID);
		} catch (CoreException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean isAdaptiveApplicationSWComponentTypeFile(String filename) {
		return filename != null && filename.endsWith(ADAPTIVE_APPLICATION_SW_COMPONENT_TYPE_EXTENSION);
	}
	
	/**
	 * 주어진 프로젝트에 필요한 네이처 정보를 추가한다.
	 * @param project
	 * @param monitor
	 * @throws CoreException
	 */
	public static void addRTSTAdaptiveAutosarNature(IProject project, IProgressMonitor monitor) throws CoreException {
		String[] natureIds = new String[] {
			"org.artop.aal.workspace.autosarnature",
			AdaptiveAutosarProjectNature.ID,
		};
		
		try {
			if (monitor == null) {
				monitor = new NullProgressMonitor();
			}
			IProjectDescription description = project.getDescription();
			String[] prevNatures = description.getNatureIds();
			String[] newNatures = new String[prevNatures.length+natureIds.length];
			
			
			System.arraycopy(prevNatures, 0, newNatures, 0, prevNatures.length);
			System.arraycopy(natureIds, 0, newNatures, prevNatures.length, natureIds.length);
			
			description.setNatureIds(newNatures);

			project.setDescription(description, IResource.FORCE, monitor);
			
		} finally {
			monitor.done();
		}
	}
	
}
