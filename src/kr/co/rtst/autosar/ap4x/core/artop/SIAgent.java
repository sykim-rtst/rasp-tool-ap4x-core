package kr.co.rtst.autosar.ap4x.core.artop;

/**
 * SERVICE-INTERFACE Model Agent
 * @author USER
 *
 */
public class SIAgent {

	private static SIAgent instance;
	
	public static SIAgent getInstance() {
		if(instance == null) {
			instance = new SIAgent();
		}
		return instance;
	}
	
	private SIAgent() {
		// TODO Auto-generated constructor stub
	}
	
}
