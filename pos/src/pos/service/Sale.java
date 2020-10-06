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
			result = this.goodsSearch(gb);
			break;
		case "S2":
			result = this.payment();
			break;
		}
		return result;
	}

	private boolean goodsSearch(GoodsBean gb) {
		DataAccessObject dao = new DataAccessObject();
		boolean result = false;

		if(dao.getGoodsInfo(2, gb)) {
			result = true;
		}
		return result;
	}

	private boolean payment() {
		boolean result = false;
		return result;
	}

}
