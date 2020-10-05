package pos.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import pos.database.DataAccessObject;
import pos.database.EmployeeBean;

public class Access {
	
		public Access() {
			
		}
		
		public boolean entrance(EmployeeBean eb) {
			boolean result = false;
			switch(eb.getRequest()) {
			case "A1":
				if(this.signIn(eb)) {
					result = true;
				}
				break;
			case "A2":
				this.employeeReg(eb);
				break;
			case "A3":
				this.employeeMod(eb);
				break;
			}
			return result;
		}
		
		private boolean signIn(EmployeeBean eb) {
			boolean result = false;
			DataAccessObject dao = new DataAccessObject();
			Date date;
			SimpleDateFormat dateFormat;
			
			if(dao.isEmployeeCode(0, eb)) {
				if(dao.isAccessCode(0, eb)) {
					result = true;
					dao.getEmployeeData(0, eb);
					date = new Date();
					dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
					eb.setAccessTime(dateFormat.format(date));
					dao.setEmployeeHistory(1, eb);
				}
			}
			
			eb.setAccessCode(null);
			
			return result;
		}
		
		private void employeeReg(EmployeeBean eb) {
			DataAccessObject dao = new DataAccessObject();
			dao.setEmployeeReg(0, eb);
		}
		
		private void employeeMod(EmployeeBean eb) {
			DataAccessObject dao = new DataAccessObject();
			ArrayList<EmployeeBean> employeeList;
			
			employeeList = dao.getEmployeesData(0);
			System.out.println(employeeList.size());
			
			for(int i=0;i<employeeList.size();i++) {
				if(employeeList.get(i).getEmployeeCode().equals(eb.getEmployeeCode())) {
					employeeList.get(i).setAccessCode(eb.getAccessCode());
					break;
				}
			}
			
			dao.setEmployeeMod(0, employeeList);
		}

}
