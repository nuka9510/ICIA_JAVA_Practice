package pos.controller;

import pos.database.EmployeeBean;
import pos.database.GoodsBean;
import pos.service.Access;
import pos.service.Sale;

public class BackController {

	public BackController() {

	}

	public String[] signIn(String[] userInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;
		String[] result = null;

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

	public void employeeReg(String[] employeeRegInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac = new Access();
		
		eb.setRequest(employeeRegInfo[0]);
		eb.setEmployeeCode(employeeRegInfo[1]);
		eb.setAccessCode(employeeRegInfo[2]);
		eb.setEmployeeName(employeeRegInfo[3]);
		eb.setEmployeephone(employeeRegInfo[4]);
		eb.setEmployeeLevel(employeeRegInfo[5].equals("Manager")?true:false);
		
		ac.entrance(eb);
	}
	
	public void employeeMod(String[] employeeModInfo) {
		
	}
	
	public String[] sale(String[] saleInfo) {
		GoodsBean gb = new GoodsBean();
		Sale sale = new Sale();
		String[] result = null;
		
		gb.setRequest(saleInfo[0]);
		gb.setGoodsCode(saleInfo[1]);
		
		if(sale.entrance(gb)) {
			result = new String[4];
			result[0] = gb.getSaleDate();
			result[1] = gb.getGoodsCode();
			result[2] = gb.getGoodsName();
			result[3] = gb.getGoodsPrice();
		}
		
		return result;
	}

}
