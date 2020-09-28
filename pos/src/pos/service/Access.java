package pos.service;

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
			if(dao.isEmployeeCode(0, eb)) {
				if(dao.isAccessCode(0, eb)) {
					result = true;
					dao.getEmployeeData(0, eb);
					dao.setEmployeeHistory(1, eb);
				}
			}
			
			eb.setAccessCode(null);
			
			return result;
		}
		
		private void employeeReg(EmployeeBean eb) {
			
		}
		
		private void employeeMod(EmployeeBean eb) {
			
		}

}
