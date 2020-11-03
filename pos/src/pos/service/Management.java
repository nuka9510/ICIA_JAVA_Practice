package pos.service;

import java.util.ArrayList;
import pos.database.DataAccessObject;
import pos.database.GoodsBean;
import pos.database.OrderBean;

public class Management {
	DataAccessObject dao;
	
	public Management() {
		dao = new DataAccessObject();
	}
	
	public ArrayList<GoodsBean> entrance(GoodsBean gb) {
		ArrayList<GoodsBean> result = null;
		
		switch(gb.getRequest()) {
		case "M1":
			this.goodsReg(gb);
			break;
		}
		return result;
	}
	
	public ArrayList<GoodsBean> entrance(OrderBean odb) {
		ArrayList<GoodsBean> result = null;
		
		switch(odb.getRequest()) {
		case "M3":
			result = this.getDailySaleInfo(odb);
			break;
		case "M4":
			result = this.getBestSaleInfo(odb);
			break;
		}
		return result;
	}
	
	private void goodsReg(GoodsBean gb) {
		this.dao.createConnection("NITO", "0000");
		
		dao.setGoodsInfo(2, gb);
	}
	
	private ArrayList<GoodsBean> getDailySaleInfo(OrderBean odb) {
		this.dao.createConnection("FANA", "0000");
		ArrayList<GoodsBean> dailySaleInfo;
		
		dailySaleInfo = dao.getDailySaleInfo(odb);
		
		return dailySaleInfo;
	}
	
	private ArrayList<GoodsBean> getBestSaleInfo(OrderBean odb) {
		this.dao.createConnection("FANA", "0000");
		ArrayList<GoodsBean> alignSaleInfo;

		alignSaleInfo = dao.getBestDailySaleInfo(odb);
		
		return alignSaleInfo;
	}

}
