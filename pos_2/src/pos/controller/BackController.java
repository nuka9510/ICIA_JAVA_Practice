package pos.controller;

import pos.database.EmployeeBean;
import pos.service.Access;

public class BackController {

	public BackController() {

	}

	public String[] signIn(String[] userInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;
		String[] signInInfo = null;

		eb.setRequest(userInfo[0]);
		eb.setEmployeeCode(userInfo[1]);
		eb.setAccessCode(userInfo[2]);

		ac = new Access();
		
		if(ac.entrance(eb)) {
			signInInfo = new String[4];
			signInInfo[0] = eb.getEmployeeCode();
			signInInfo[1] = eb.getEmployeeName();
			signInInfo[2] = eb.isEmployeeLevel()?"Manager":"Mate";
			signInInfo[3] = eb.getAccessTime();
		}
		
		return signInInfo;
	}

	public void employeeReg(String[] regInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac = new Access();
		
		eb.setRequest(regInfo[0]);
		eb.setEmployeeCode(regInfo[1]);
		eb.setAccessCode(regInfo[2]);
		eb.setEmployeeName(regInfo[3]);
		eb.setEmployeephone(regInfo[4]);
		eb.setEmployeeLevel(regInfo[5].equals("Manager")?true:false);
		
		ac.entrance(eb);
	}

}
