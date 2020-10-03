package pos.service;

import java.text.SimpleDateFormat;
import java.util.Date;

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
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		
		if(dao.getSaleInfo(2, gb)) {
			gb.setSaleDate(format.format(date));
			result = true;
		}
		
		return result;
	}

}
