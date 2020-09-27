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
		signInInfo[1] = sb.getEmployeeName();
		signInInfo[2] = sb.isEmployeeLevel()?"Manager":"Mate";
		signInInfo[3] = sb.getAccessTime();
		
		return signInInfo;
	}
	
	public String selectService(String[] select) {
		SalerBean sb = new SalerBean();
		Access ac;
		String result = null;
		
		switch(select[0]) {
		case "A2":
			sb.setRequest(select[0]);
			sb.setEmployeeCode(select[1]);
			sb.setAccessCode(select[2]);
			sb.setEmployeeName(select[3]);
			sb.setEmployeephone(select[4]);
			sb.setEmployeeLevel((select[5].equals("Manager"))?true:false);
			ac = new Access();
			ac.entrance(sb);
			result = "¿Ï·á";
		break ;
		}
		return result;
	}

}
