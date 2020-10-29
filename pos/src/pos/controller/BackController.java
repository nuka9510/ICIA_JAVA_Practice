package pos.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
		eb.setStoreCode(userInfo[1]);
		eb.setEmployeeCode(userInfo[2]);
		eb.setAccessCode(userInfo[3]);
		eb.setAccessState(1);

		ac = new Access();
		ac.entrance(eb);

		if(eb.isResult()) {
			this.userInfo = new String[6];
			this.userInfo[0] = eb.getStoreCode();
			this.userInfo[1] = eb.getStoreName();
			this.userInfo[2] = eb.getEmployeeCode();
			this.userInfo[3] = eb.getEmployeeName();
			this.userInfo[4] = eb.isEmployeeLevel()?"Manager":"Mate";
			this.userInfo[5] = eb.getAccessTime();
		}else {
			this.userInfo = null;
		}

		return this.userInfo;
	}
	
	public void signOut(String[] userInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest("A4");
		eb.setStoreCode(userInfo[0]);
		eb.setEmployeeCode(userInfo[2]);
		eb.setAccessState(-1);

		ac = new Access();
		ac.entrance(eb);
	}

	public String[] employeeReg(String[] regInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest(regInfo[0]);
		eb.setStoreCode(regInfo[1]);
		eb.setEmployeeCode(regInfo[2]);
		eb.setAccessCode(regInfo[3]);
		eb.setEmployeeName(regInfo[4]);
		eb.setEmployeeLevel(regInfo[5].equals("Manager")?true:false);

		ac = new Access();
		ac.entrance(eb);

		return this.userInfo;
	}

	public String[] employeeMod(String[] modInfo) {
		EmployeeBean eb = new EmployeeBean();
		Access ac;

		eb.setRequest(modInfo[0]);
		eb.setStoreCode(modInfo[1]);
		eb.setEmployeeCode(modInfo[2]);
		eb.setAccessCode(modInfo[3]);

		ac = new Access();

		ac.entrance(eb);

		return this.userInfo;
	}

	public String[] saleGoodsInfo(String[] saleInfo) {
		Date date = new Date();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		String[] goodsInfo = null;
		GoodsBean gb = new GoodsBean();
		Sale sale;

		gb.setSaleDate(dateFormat.format(date));
		gb.setRequest(saleInfo[0]);
		gb.setGoodsCode(saleInfo[1]);

		sale = new Sale();
		sale.entrance(gb);	

		if(gb.isResult()) {
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
		ArrayList<GoodsBean> goodsListBeanArrayList = new ArrayList<GoodsBean>();
		Sale sale;
		GoodsBean gb = new GoodsBean();
		GoodsBean goodsListBean;

		gb.setRequest(saleInfo[0]);
		gb.setState(saleInfo[1]);

		for(int i=0;i<goodsList.length;i++) {
			goodsListBean = new GoodsBean();

			goodsListBean.setSaleDate(goodsList[goodsList.length-1][0]);
			goodsListBean.setGoodsCode(goodsList[i][1]);
			goodsListBean.setGoodsName(goodsList[i][2]);
			goodsListBean.setGoodsAmount(Integer.parseInt(goodsList[i][3]));
			goodsListBean.setGoodsPrice(Integer.parseInt(goodsList[i][4]));
			goodsListBean.setGoodsExpireDate(goodsList[i][5]);

			goodsListBeanArrayList.add(goodsListBean);
		}

		sale = new Sale();

		sale.entrance(gb, goodsListBeanArrayList);
	}

	public String[][] getRefundList(String[] refundInfo) {
		String[][] refundList = null;
		ArrayList<GoodsBean> refundListArrayList;
		GoodsBean gb = new GoodsBean();
		Sale sale;

		gb.setRequest(refundInfo[0]);
		gb.setSaleDate(refundInfo[1]);

		sale = new Sale();

		refundListArrayList = sale.entrance(gb);

		if(gb.isResult()) {
			refundList = new String[refundListArrayList.size()][6];
			for(int i=0;i<refundListArrayList.size();i++) {
				refundList[i][0] = refundListArrayList.get(i).getSaleDate();
				refundList[i][1] = refundListArrayList.get(i).getGoodsCode();
				refundList[i][2] = refundListArrayList.get(i).getGoodsName();
				refundList[i][3] = refundListArrayList.get(i).getGoodsAmount() + "";
				refundList[i][4] = refundListArrayList.get(i).getGoodsPrice() + "";
				refundList[i][5] = refundListArrayList.get(i).getGoodsExpireDate();
			}
		}

		return refundList;
	}

	public void setRefundList(String[] refundInfo, String[][] goodsList) {
		GoodsBean gb = new GoodsBean();
		GoodsBean goodsListBean;
		ArrayList<GoodsBean> goodsListBeanArrayList = new ArrayList<GoodsBean>();

		Sale sale = new Sale();

		gb.setRequest(refundInfo[0]);

		for(int i=0;i<goodsList.length;i++) {
			goodsListBean = new GoodsBean();

			goodsListBean.setSaleDate(goodsList[i][0]);
			goodsListBean.setGoodsCode(goodsList[i][1]);
			goodsListBean.setGoodsName(goodsList[i][2]);
			goodsListBean.setGoodsAmount(Integer.parseInt(goodsList[i][3]));
			goodsListBean.setGoodsPrice(Integer.parseInt(goodsList[i][4]));
			goodsListBean.setGoodsExpireDate(goodsList[i][5]);

			goodsListBeanArrayList.add(goodsListBean);
		}

		sale.entrance(gb, goodsListBeanArrayList);
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

	public String[][] getDailySaleInfo(String[] saleInfo) {
		GoodsBean gb = new GoodsBean();
		Management manage;
		String[][] dailySaleInfo;
		ArrayList<GoodsBean> dailySaleInfoArrayList;

		gb.setRequest(saleInfo[0]);
		gb.setSaleDate(saleInfo[1]);

		manage = new Management();

		dailySaleInfoArrayList = manage.entrance(gb);

		dailySaleInfo = new String[dailySaleInfoArrayList.size()][6];

		for(int i=0;i<dailySaleInfoArrayList.size();i++) {
			dailySaleInfo[i][0] = dailySaleInfoArrayList.get(i).getSaleDate();
			dailySaleInfo[i][1] = dailySaleInfoArrayList.get(i).getGoodsCode();
			dailySaleInfo[i][2] = dailySaleInfoArrayList.get(i).getGoodsName();
			dailySaleInfo[i][3] = dailySaleInfoArrayList.get(i).getGoodsAmount() + "";
			dailySaleInfo[i][4] = dailySaleInfoArrayList.get(i).getGoodsPrice() + "";
			dailySaleInfo[i][5] = (dailySaleInfoArrayList.get(i).getGoodsAmount() * dailySaleInfoArrayList.get(i).getGoodsPrice()) + "";
		}

		return dailySaleInfo;
	}

}
