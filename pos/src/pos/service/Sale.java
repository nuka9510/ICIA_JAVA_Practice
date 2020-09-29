package pos.service;

import pos.database.DataAccessObject;
import pos.database.GoodsBean;

public class Sale {
	
	public Sale() {
		
	}
	
	public void entrance(GoodsBean gb) {
		switch(gb.getRequest()) {
		case "S1":
			this.sale(gb);
			break;
		}
		
	}
	
	private void sale(GoodsBean gb) {
		DataAccessObject dao = new DataAccessObject();
		
		dao.getSaleInfo(2, gb);
		
	}

}
