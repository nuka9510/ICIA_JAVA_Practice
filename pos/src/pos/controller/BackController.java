package pos.controller;

import pos.database.EmployeeBean;
import pos.database.GoodsBean;
import pos.service.Access;
import pos.service.Sale;

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
	
	public void employeeReg(String[] regInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;
		
		eb.setRequest(regInfo[0]);
		eb.setEmployeeCode(regInfo[1]);
		eb.setAccessCode(regInfo[2]);
		eb.setEmployeeName(regInfo[3]);
		eb.setEmployeephone(regInfo[4]);
		eb.setEmployeeLevel(regInfo[5].equals("Manager")?true:false);
		
		ac = new Access();
		ac.entrance(eb);
		
	}
	
	public String[] sale(String[] saleInfo) {
		String[] saleResult = null;
		GoodsBean gb = new GoodsBean();
		Sale sale;
		
		gb.setRequest(saleInfo[0]);
		gb.setGoodsCode(saleInfo[1]);
		
		sale = new Sale();
		sale.entrance(gb);
		
		saleResult = new String[3];
		saleResult[0] = gb.getGoodsCode();
		saleResult[1] = gb.getGoodsName();
		saleResult[2] = gb.getGoodsPrice();
		
		
		return saleResult;
	}

}
