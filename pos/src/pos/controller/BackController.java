package pos.controller;

import pos.database.SalerBean;
import pos.service.Access;

public class BackController {
	
	public BackController() {
		
	}
	
	public String[] signIn(String[] userInfo) {
		SalerBean sb = new SalerBean();
		Access ac;
		String[] signInInfo;
		
		sb.setRequest(userInfo[0]);
		sb.setEmployeeCode(userInfo[1]);
		sb.setAccessCode(userInfo[2]);
		
		ac = new Access();
		ac.entrance(sb);
		
		signInInfo = new String[4];
		signInInfo[0] = sb.getEmployeeCode();
		signInInfo[1] = sb.getSalerName();
		signInInfo[2] = sb.isSalerLevel()?"Manager":"Mate";
		signInInfo[3] = sb.getAccessTime();
		
		return signInInfo;
	}

}
