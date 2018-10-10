package kr.co.rtst.autosar.ap4x.core.model;

import org.eclipse.core.resources.IFolder;
import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IProjectDescription;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.runtime.CoreException;
import org.eclipse.core.runtime.IProgressMonitor;
import org.eclipse.core.runtime.NullProgressMonitor;

import kr.co.rtst.autosar.ap4x.core.nature.AdaptiveAutosarProjectNature;

public interface IAdaptiveAutosarProject {
	
	String PREDEFINED_ARXML_NAME 	= "ImplementationDataTypes.arxml";
	String USER_DEFINED_ARXML_NAME 	= "AdaptiveAutosarProject.arxml";

	public IProject getProject();

	public IAPTopElement getTypes();

	public IAPTopElement getApplications();

	public IAPTopElement getServices();

	public IAPTopElement getMachines();
	
	public IAPTopElement[] getTopElements();


	/**
	 * 프로젝트 생성 시 만들어져야하는 최상위 디렉토리들
	 */
	String[] DEFAULT_TOP_DIR = {"Types", "Applications", "Services", "Machines"};
	String[] DEFAULT_TOP_DIR_IMAGE = {"types", "applications", "services", "machines"};
	/**
	 * 최상위 폴더의 배치순서
	 */
	public static final int[] DEFAULT_TOP_DIR_ORDER = {1, 2, 3, 4};
	
	/**
	 * 최상위 프로젝트 폴더를 반환한다.
	 * @return
	 */
	public default String[] getProjectTopFolders() {
		return DEFAULT_TOP_DIR;
	}
	
	/**
	 * 최상위 디렉토리에 대한 순서값을 반환한다. 
	 * @param topDirName
	 * @return 주어진 인자가 최상위 고정 디렉토리라면 해당하는 순서값을 반환하면 그외 다른 리소스가면 Integer의 최대값을 반환한다.
	 */
	public static int getTopDirOrderNumber(String topDirName) {
		for (int i = 0; i < DEFAULT_TOP_DIR.length; i++) {
			if(DEFAULT_TOP_DIR[i].equals(topDirName)) {
				return DEFAULT_TOP_DIR_ORDER[i];
			}
		}
		return Integer.MAX_VALUE;
	}
	
	/**
	 * 최상위 디렉토리에 대한 이미지 명을 반환한다. 
	 * @param topDirName
	 * @return 주어진 인자가 최상위 고정 디렉토리라면 해당하는 이미지 명을 반환하면 그외 다른 리소스가면 null을 반환한다.
	 */
	public static String getTopDirImageName(String topDirName) {
		for (int i = 0; i < DEFAULT_TOP_DIR.length; i++) {
			if(DEFAULT_TOP_DIR[i].equals(topDirName)) {
				return DEFAULT_TOP_DIR_IMAGE[i];
			}
		}
		return null;
	}
	
	/**
	 * 주어진 오브젝트들 중 프로젝트 최상위 고정 폴더가 하나라도 포함되어 있다면 true 아니멸 false를 반환한다.
	 * @param dirs 검사할 객체들
	 * @return 프로젝트 생성 시 만들어져야하는 최상위 디렉토리가 하나라도 있다면 true 아니면 false
	 */
	public static boolean hasAdaptiveAutosarTopDir(Object... dirs) {
		for (int i = 0; i < dirs.length; i++) {
			if(isAdaptiveAutosarTopFolder(dirs[i])) {
				return true;
			}
		}
		return false;
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
	 * 프로젝트의 두 요소를 받아 비교를 진행하고 그 결과를 반환한다.
	 * 최상위 디렉토리의 경우 그 순서에 따라 비교를 진행하며, 나머지는 기본 오름차순으로 정렬한다.
	 * @param f1 첫번째 요소
	 * @param f2 두번째 요소
	 * @return 비교결과
	 */
	public static int compareTopFolder(IResource r1, IResource r2) {
		if( !(r1 instanceof IFolder) ) {
			return 1; // 무조건 e2가 우선
		} else if( !(r2 instanceof IFolder) ) {
			return -1; // 무조건 e1이 우선
		} else {
			int v1 = getTopDirOrderNumber(r1.getName());
			int v2 = getTopDirOrderNumber(r2.getName());
			return Integer.compare(v1, v2);
		}
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
