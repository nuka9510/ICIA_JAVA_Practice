package pos.controller;

import java.text.SimpleDateFormat;
//import java.util.ArrayList;
import java.util.Date;

import pos.database.EmployeeBean;
import pos.database.GoodsBean;
import pos.service.Access;
import pos.service.Management;
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

	public String[] saleGoodsInfo(String[] saleInfo, String[][] goodsList) {
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String[] goodsInfo = null;
		GoodsBean gb = new GoodsBean();
		Sale sale;

		gb.setSaleDate(simpleDateFormat.format(date));
		gb.setRequest(saleInfo[0]);
		gb.setGoodsCode(saleInfo[1]);
		gb.setSaleInfoList(goodsList);

		sale = new Sale();
		if(sale.entrance(gb)) {
			goodsInfo = new String[6];
			goodsInfo[0] = gb.getSaleDate();
			goodsInfo[1] = gb.getGoodsCode();
			goodsInfo[2] = gb.getGoodsName();
			goodsInfo[3] = gb.getGoodsAmount() + "";
			goodsInfo[4] = gb.getGoodsPrice() + "";
			goodsInfo[5] = gb.getGoodsExpireDate();
		}
		
		return goodsInfo;
	}
	
	public void setSaleInfo(String[] saleInfo, String[][] goodsList) {
		//ArrayList<GoodsBean> saleInfoList = new ArrayList<GoodsBean>();
		Sale sale;
		GoodsBean gb = new GoodsBean();
		//GoodsBean gbList;
		
		gb.setRequest(saleInfo[0]);
		gb.setState(saleInfo[1]);
		/*
		for(int i=0;i<goodsList.length;i++) {
			gbList = new GoodsBean();
			
			gbList.setGoodsCode(goodsList[i][0]);
			gbList.setGoodsName(goodsList[i][1]);
			gbList.setGoodsAmount(Integer.parseInt(goodsList[i][2]));
			gbList.setGoodsPrice(Integer.parseInt(goodsList[i][3]));
			gbList.setGoodsExpireDate(goodsList[i][4]);
			gbList.setSaleDate(simpleDateFormat.format(date));
			
			saleInfoList.add(gbList);
		}
		*/
		gb.setSaleInfoList(goodsList);
		
		sale = new Sale();
		
		sale.entrance(gb);
	}
	
	public String[][] getRefundList(String[] refundInfo) {
		String[][] refundList = null;
		GoodsBean gb = new GoodsBean();
		Sale sale;
		
		gb.setRequest(refundInfo[0]);
		gb.setSaleDate(refundInfo[1]);
		
		sale = new Sale();
		
		if(sale.entrance(gb)) {
			refundList = new String[gb.getRefundList().size()][6];
			for(int i=0;i<gb.getRefundList().size();i++) {
				refundList[i][0] = gb.getRefundList().get(i).getSaleDate();
				refundList[i][1] = gb.getRefundList().get(i).getGoodsCode();
				refundList[i][2] = gb.getRefundList().get(i).getGoodsName();
				refundList[i][3] = gb.getRefundList().get(i).getGoodsAmount() + "";
				refundList[i][4] = gb.getRefundList().get(i).getGoodsPrice() + "";
				refundList[i][5] = gb.getRefundList().get(i).getGoodsExpireDate();
			}
		}
		
		return refundList;
	}
	
	public void setRefundList(String[] refundInfo, String[][] goodsList) {
		 GoodsBean gb = new GoodsBean();
		 Sale sale = new Sale();
		 
		 gb.setRequest(refundInfo[0]);
		 gb.setSaleInfoList(goodsList);
		 
		 sale.entrance(gb);
	}
	
	public void goodsReg(String[] goodsInfo) {
		GoodsBean gb = new GoodsBean();
		Management manage;
		
		gb.setRequest(goodsInfo[0]);
		gb.setGoodsCode(goodsInfo[1]);
		gb.setGoodsName(goodsInfo[2]);
		gb.setGoodsPrice(Integer.parseInt(goodsInfo[3]));
		gb.setGoodsExpireDate(goodsInfo[4]);
		gb.setGoodsStock(Integer.parseInt(goodsInfo[5]));
		gb.setGoodsSafetyStock(Integer.parseInt(goodsInfo[6]));
		
		manage = new Management();
		manage.entrance(gb);
	}

}
