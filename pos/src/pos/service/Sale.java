package pos.service;

import java.util.ArrayList;
import pos.database.DataAccessObject;
import pos.database.GoodsBean;
import pos.database.OrderBean;
import pos.database.OrderDetailBean;

public class Sale {
	DataAccessObject dao;
	
	public Sale() {
		this.dao = new DataAccessObject();
	}

	public ArrayList<GoodsBean> entrance(GoodsBean gb) {
		ArrayList<GoodsBean> result = null;
		
		switch(gb.getRequest()) {
		case "S1":
			this.goodsSearch(gb);
			break;
		}
		
		return result;
	}
	
	public ArrayList<GoodsBean> entrance(OrderBean odb) {
		ArrayList<GoodsBean> result = null;
		
		switch(odb.getRequest()) {
		case "S3":
			result = this.getRefundList(odb);
			break;
		}
		
		return result;
	}
	
	public void entrance(OrderBean odb, ArrayList<OrderDetailBean> goodsList) {
		switch(odb.getRequest()) {
		case "S2":
			this.setSaleInfo(odb, goodsList);
			break;
		case "S4":
			this.setRefundList(odb, goodsList);
			break;
		}
	}

	private void goodsSearch(GoodsBean gb) {
		this.dao.createConnection("FANA", "0000");
		
		this.dao.getGoodsInfo(gb);
	}

	private void setSaleInfo(OrderBean odb, ArrayList<OrderDetailBean> goodsList) {
		boolean tran = false;
		this.dao.createConnection("FANA", "0000");
		this.dao.setAutoTransAction(tran);
		
		
//		this.dao.setOrderInfo(gb);
//		this.dao.getOrderCode(gb);
		
		
		tran = this.dao.regOrder(odb);
		if(tran) {
			for(OrderDetailBean otb : goodsList) {
				otb.setOdCode(odb.getOdCode());
				if(this.dao.setSaleInfo(otb)) {
					tran = true;
				}else {
					tran = false;
					break;
				}
			}
		}
		
		this.dao.endAutoTransAction(tran);
		
	}
	
	private ArrayList<GoodsBean> getRefundList(OrderBean odb) {
		this.dao.createConnection("FANA", "0000");
		ArrayList<GoodsBean> refundList;
		
		refundList = dao.getRefundList(odb);
		
		return refundList;
	}
	
	private void setRefundList(OrderBean odb, ArrayList<OrderDetailBean> refundList) {
		boolean tran = false;
		this.dao.createConnection("SON", "0000");
		this.dao.setAutoTransAction(tran);
		
		tran = this.dao.setOrderInfo(odb);
		
		if(tran) {
			for(OrderDetailBean otb : refundList) {
				if(this.dao.setSaleInfo(otb)) {
					tran = true;
				}else {
					tran = false;
					break;
				}
			}
		}
		
		this.dao.endAutoTransAction(tran);
	}

}
