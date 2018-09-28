package kr.co.rtst.autosar.ap4x.core.artop;

/**
 * ADAPTIVE-APPLICATION-SW-COMPONENT-TYPE model agent.
 * @author USER
 *
 */
public class AASWCTAgent {
	
	private static AASWCTAgent instance;
	
	public static AASWCTAgent getInstance() {
		if(instance == null) {
			instance = new AASWCTAgent();
		}
		return instance;
	}
	
	private AASWCTAgent() {
		// TODO Auto-generated constructor stub
	}
	
	public void init() {
//		EcorePlatformUtil.
	}

}
