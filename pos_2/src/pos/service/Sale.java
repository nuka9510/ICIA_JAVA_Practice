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
			result = this.sale(gb);
			break;
		case "S2":
			break;
		case "S3":
			break;
		}
		
		return result;
	}
	
	private boolean sale(GoodsBean gb) {
		boolean result = false;
		DataAccessObject dao = new DataAccessObject();
			
		if(dao.getSaleInfo(2, gb)) {
			result = true;
		}
		
		return result;
	}

}
