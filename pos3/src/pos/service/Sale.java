package pos.service;

import pos.database.DataAccessObject;
import pos.database.GoodsBean;

public class Sale {
	
	public Sale() {
		
	}
	
	public boolean entrance(GoodsBean gb) {
		boolean result = false;
		
		switch(gb.getRequest()) {
		case "S1":
			result = this.getGoodsInfo(gb);
			break;
		case "S2":
			result = this.payment(gb);
			break;
		case "S3":
			result = this.getRefundList(gb);
			break;
		}
		
		return result;
	}

	private boolean getGoodsInfo(GoodsBean gb) {
		DataAccessObject dao = new DataAccessObject();
		boolean result = false;
		
		result = dao.getGoodsInfo(2, gb);
		
		return result;
	}
	
	private boolean payment(GoodsBean gb) {
		DataAccessObject dao = new DataAccessObject();
		boolean result = false;
		
		result = dao.setSaleInfo(4, gb);
		
		return result;
	}
	
	private boolean getRefundList(GoodsBean gb) {
		boolean result = false;
		DataAccessObject dao = new DataAccessObject();
		
		result = dao.getRefundList(4, gb);
		
		return result;
	}
}
