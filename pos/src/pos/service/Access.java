package pos.service;

import pos.database.SalerDataBean;

public class Access {
	
		public Access() {
			
		}
		
		public void entrance(String request, SalerDataBean sdb) {
			switch(request) {
			case "A1":
				signIn(sdb);
				break;
			case "A2":
				salerReg(sdb);
				break;
			case "A3":
				salerMod(sdb);
				break;
			}
		}
		
		private void signIn(SalerDataBean sdb) {
				sdb.setSalerName("Maginot");
				sdb.setSalerLevel(true);
				sdb.setAccessTime("202009241812");
		}
		
		private void salerReg(SalerDataBean sdb) {
			
		}
		
		private void salerMod(SalerDataBean sdb) {
			
		}

}
