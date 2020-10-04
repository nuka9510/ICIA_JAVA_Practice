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

	public String[][] sale(String[] goodsInfo, String[][] saleInfo) {
		GoodsBean gb = new GoodsBean();
		Sale sale = new Sale();
		String[][] result = null;
		int row = 1;

		gb.setRequest(goodsInfo[0]);
		gb.setGoodsCode(goodsInfo[1]);

		if(sale.entrance(gb)) {
			if(saleInfo == null) {
				result = new String[row][3];

				result[row-1][0] = gb.getGoodsCode();
				result[row-1][1] = gb.getGoodsName();
				result[row-1][2] = gb.getGoodsPrice();
			}else {
				for(int i=0;i<saleInfo.length;i++) {
					System.out.println(saleInfo[i][0]);
					if(saleInfo[i][0].equals(gb.getGoodsCode())) {
						row--;
					}
					row++;
				}
				System.out.println(row);
				result = new String[row][3];
				for(int i=0;i<saleInfo.length;i++) {
					result[i][0] = saleInfo[i][0];
					result[i][1] = saleInfo[i][1];
					result[i][2] = saleInfo[i][2];
				}
				if(result[row-1][0] == null) {
					result[row-1][0] = gb.getGoodsCode();
					result[row-1][1] = gb.getGoodsName();
					result[row-1][2] = gb.getGoodsPrice();
				}
			}
		}
		return result;
	}

}
