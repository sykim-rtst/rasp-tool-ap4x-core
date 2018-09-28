package kr.co.rtst.autosar.ap4x.core.artop;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.core.resources.IFile;

import gautosar.ggenericstructure.ginfrastructure.GAUTOSAR;

public class AutosarCoreModelRegistry {

	private static AutosarCoreModelRegistry instance;
	
	private Map<IFile, GAUTOSAR> coreModelRegistry;
	
	public static AutosarCoreModelRegistry getInstance() {
		if(instance == null) {
			instance = new AutosarCoreModelRegistry();
		}
		return instance;
	}
	
	private AutosarCoreModelRegistry() {
		// TODO Auto-generated constructor stub
		coreModelRegistry = new HashMap<>();
	}
	
	public void putCoreModel(IFile file, GAUTOSAR autosarModel) {
		coreModelRegistry.put(file, autosarModel);
	}
	
	public GAUTOSAR getCoreModel(IFile file) {
		return coreModelRegistry.get(file);
	}
}
