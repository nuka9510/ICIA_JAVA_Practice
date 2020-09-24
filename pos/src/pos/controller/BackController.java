package pos.controller;

import pos.database.SalerDataBean;
import pos.service.Access;

public class BackController {
	
	public BackController() {
		
	}
	
	public String[] signIn(String[] userInfo) {
		Access ac = new Access();
		SalerDataBean sdb = new SalerDataBean();
		String[] info = new String[4];
		
		sdb.setEmployeeCode(userInfo[0]);
		sdb.setAccessCode(userInfo[1]);
		ac.entrance("A1", sdb);
		
		info[0] = sdb.getEmployeeCode();
		info[1] = sdb.getSalerName();
		info[2] = sdb.isSalerLevel()?"Manager":"Mate";
		info[3] = sdb.getAccessTime();
				
		return info;
	}

}
