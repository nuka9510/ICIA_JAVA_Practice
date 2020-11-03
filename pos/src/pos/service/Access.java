package pos.service;

import pos.database.DataAccessObject;
import pos.database.EmployeeBean;

public class Access {

	DataAccessObject dao;

	public Access() {
		this.dao = new DataAccessObject();
	}

	public void entrance(EmployeeBean eb) {
		switch(eb.getRequest()) {
		case "A1":
			this.signIn(eb);
			break;
		case "A2":
			this.employeeReg(eb);
			break;
		case "A3":
			this.employeeMod(eb);
			break;
		case "A4":
			this.signOut(eb);
			break;
		}
	}

	private void signIn(EmployeeBean eb) {
		boolean tran = false;
		this.dao.createConnection("FANA", "0000");
		this.dao.setAutoTransAction(tran);

		if(this.dao.isEmployeeCode(eb)) {
			if(this.dao.isAccessCode(eb)) {
				tran = this.dao.setEmployeeHistory(eb);
				
				this.dao.getEmployeeData(eb);
				
				eb.setAccessCode(null);
				eb.setResult(tran);
			}
		}
		this.dao.endAutoTransAction(tran);
	}

	private void signOut(EmployeeBean eb) {
		boolean tran = false;
		this.dao.createConnection("FANA", "0000");
		this.dao.setAutoTransAction(tran);

		tran = this.dao.setEmployeeHistory(eb);

		this.dao.endAutoTransAction(tran);
	}

	private void employeeReg(EmployeeBean eb) {
		boolean tran = false;
		this.dao.createConnection("FANA", "0000");
		this.dao.setAutoTransAction(tran);

		tran =this.dao.setEmployeeReg(eb);

		this.dao.endAutoTransAction(tran);
	}

	private void employeeMod(EmployeeBean eb) {
		boolean tran = false;
		this.dao.createConnection("FANA", "0000");
		this.dao.setAutoTransAction(tran);

		tran = this.dao.setEmployeeMod(eb);

		this.dao.endAutoTransAction(tran);
	}

}
