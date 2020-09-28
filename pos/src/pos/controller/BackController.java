package pos.controller;

import pos.database.EmployeeBean;
import pos.service.Access;

public class BackController {

	public BackController() {

	}

	public String[] signIn(String[] userInfo) {
		String[] result = null;
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest(userInfo[0]);
		eb.setEmployeeCode(userInfo[1]);
		eb.setAccessCode(userInfo[2]);

		ac = new Access();

		if(ac.entrance(eb)) {
			result = new String[4];
			result[0] = eb.getEmployeeCode();
			result[1] = eb.getEmployeeName();
			result[2] = eb.isEmployeeLevel()?"Manager":"Mate";
			result[3] = eb.getAccessTime();
		}

		return result;
	}

}
