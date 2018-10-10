package kr.co.rtst.autosar.ap4x.core.util;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;
import org.eclipse.core.runtime.QualifiedName;

import kr.co.rtst.autosar.ap4x.core.nature.AdaptiveAutosarProjectNature;

public class AdaptiveAutosarProjectUtil {
	
	public static final QualifiedName AUTOSAR_FILE_CORE_MODEL = new QualifiedName("kr.co.rtst.autosar.ap4x", "core.model");

	public static final String ADAPTIVE_APPLICATION_SW_COMPONENT_TYPE_EXTENSION = ".aaswc";
	
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
	
}
