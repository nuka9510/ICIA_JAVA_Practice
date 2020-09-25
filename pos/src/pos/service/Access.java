package pos.service;

import pos.database.SalerBean;

public class Access {
	
		public Access() {
			
		}
		
		public void entrance(SalerBean sb) {
			switch(sb.getRequest()) {
			case "A1":
				this.signIn(sb);
				break;
			case "A2":
				this.salerReg(sb);
				break;
			case "A3":
				this.salerMod(sb);
				break;
			}
		}
		
		private void signIn(SalerBean sb) {
			sb.setSalerName("Maginot");
			sb.setSalerLevel(true);
			sb.setAccessTime("20200925");
		}
		
		private void salerReg(SalerBean sb) {
			
		}
		
		private void salerMod(SalerBean sb) {
			
		}

}
