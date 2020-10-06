package pos.controller;

import pos.database.EmployeeBean;
import pos.database.GoodsBean;
import pos.service.Access;
import pos.service.Sale;

public class BackController {
	private String[] userInfo;

	public BackController() {
		userInfo = null;
	}

	public String[] signIn(String[] userInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest(userInfo[0]);
		eb.setEmployeeCode(userInfo[1]);
		eb.setAccessCode(userInfo[2]);

		ac = new Access();

		if(ac.entrance(eb)) {
			this.userInfo = new String[4];
			this.userInfo[0] = eb.getEmployeeCode();
			this.userInfo[1] = eb.getEmployeeName();
			this.userInfo[2] = eb.isEmployeeLevel()?"Manager":"Mate";
			this.userInfo[3] = eb.getAccessTime();
		}else {
			this.userInfo = null;
		}

		return this.userInfo;
	}

	public String[] employeeReg(String[] regInfo) {
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

		return this.userInfo;
	}

	public String[] employeeMod(String[] modInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest(modInfo[0]);
		eb.setEmployeeCode(modInfo[1]);
		eb.setAccessCode(modInfo[2]);

		ac = new Access();

		ac.entrance(eb);

		return this.userInfo;
	}

	public String[] saleGoodsInfo(String[] saleInfo) {
		String[] goodsInfo = null;
		GoodsBean gb = new GoodsBean();
		Sale sale;

		gb.setRequest(saleInfo[0]);
		gb.setGoodsCode(saleInfo[1]);

		sale = new Sale();
		if(sale.entrance(gb)) {
			goodsInfo = new String[5];
			goodsInfo[0] = gb.getGoodsCode();
			goodsInfo[1] = gb.getGoodsName();
			goodsInfo[2] = gb.getGoodsAmount() + "";
			goodsInfo[3] = gb.getGoodsPrice() + "";
			goodsInfo[4] = gb.getGoodsExpireDate();
		}
		
		return goodsInfo;
	}
	
	public void setSaleInfo() {
		
	}

}
