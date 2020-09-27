package pos.service;

import java.io.BufferedWriter;
import java.io.FileWriter;

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
			sb.setEmployeeName("Maginot");
			sb.setEmployeeLevel(true);
			sb.setAccessTime("20200925");
		}
		
		private void salerReg(SalerBean sb) {
			String fileName = "D:\\ICIA\\Jong Won\\java\\pos\\src\\pos\\database\\salerData.txt";
			FileWriter fileWriter = null;
			BufferedWriter bufferedWriter = null;

			String regInfo = sb.getEmployeeCode() + "\t" + sb.getAccessCode() + "\t" + sb.getEmployeeName()
					+ "\t" + sb.getEmployeephone() + "\t" + (sb.isEmployeeLevel()?"Manager":"Mate");
			System.out.println(regInfo);
			
			try {
				fileWriter = new FileWriter(fileName, true);
				bufferedWriter = new BufferedWriter(fileWriter);
				bufferedWriter.newLine();
				bufferedWriter.write(regInfo);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if(bufferedWriter != null) {
						bufferedWriter.close();
					}
					if(fileWriter != null) {
						fileWriter.close();
					}
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		private void salerMod(SalerBean sb) {
			
		}

}
