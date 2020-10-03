package pos.controller;

import java.util.Scanner;

public class FrontController {
	Scanner sc;
	
	public FrontController() {
		this.sc = new Scanner(System.in);
		this.init(this.title());
		this.sc.close();
	}
	
	private void init(String title) {
		BackController bc = new BackController();
		String[] userInfo = null;
		while(true) {
			userInfo = this.signIn(title);
			userInfo = bc.signIn(userInfo);
			while(userInfo != null) {
				switch(this.select(title, userInfo)) {
				case "1":
					String[] saleInfo = this.saleFirst(title, userInfo);
					saleInfo = bc.sale(saleInfo);
					if(saleInfo!=null) {
						this.saleSecond(title, userInfo, saleInfo);
					}
					break;
				case "2":
					this.payback(title, userInfo);
					break;
				case "3":
					switch(this.employeeManagement(title, userInfo)) {
					case "1":
						String[] employeeRegInfo = this.employeeReg(title, userInfo);
						bc.employeeReg(employeeRegInfo);
						break;
					case "2":
						this.employeeMod(title, userInfo);
						break;
					}
					break;
				case "4":
					this.saleManagement(title, userInfo);
					break;
				}
			}
		}
	}
	
	private String[] signIn(String title) {
		String[] userInfo = new String[3];
		
		userInfo[0] = "A1";
		this.print(title+"\t[Sign In]\n" + 
	            "\n" + 
	            "\t[Employee Code] : ");
		userInfo[1] = this.sc.next();
		this.print("\t[Access Code]   : ");
		userInfo[2] = this.sc.next();
		
		return userInfo;
	}
	
	private String select(String title, String[] userInfo) {
		String service;
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\nselectService\n\n");
		this.print("1. 상품판매\t\t2. 상품반품\n");
		if(userInfo[2].equals("Manager")) {
			this.print("3. 직원관리\t\t4. 영업관리\n");
		}
		this.print("\n_____________________ Select : ");
		service = sc.next();
		
		return service;
	}
	
	private String[] saleFirst(String title, String[] userInfo) {
		String[] resultInfo = new String[2];
		
		resultInfo[0] = "S1";
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n상품판매\n\n상품코드\n\n_____________________ Select : ");
		resultInfo[1] = sc.next();
		return resultInfo;
	}
	
	private String[] saleSecond(String title, String[] userInfo, String[] saleInfo) {
		String[] result = null;
		
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n[상품 판매]\n\n" +
				"--------------------------------------------------\n" + 
				"결재일자 : " + saleInfo[0] + "\n" + 
				"--------------------------------------------------\n" + 
				"결재번호\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n" + 
				"01\t" + saleInfo[1] + "\t" + saleInfo[2] + "\t1\t" + saleInfo[3] + "\n" + 
				"--------------------------------------------------");
		return result;
	}
	
	private String[] payback(String title, String[] userInfo) {
		String[] resultInfo = null;
		return resultInfo;
	}
	
	private String employeeManagement(String title, String[] userInfo) {
		String service;
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\nselectService\n\n1. 직원등록\t\t2. 직원정보수정\n\n_____________________ Select : ");
		service = sc.next();
		
		return service;
	}
	
	private String[] saleManagement(String title, String[] userInfo) {
		String[] resultInfo = null;
		return resultInfo;
	}
	
	private String[] employeeReg(String title, String[] userInfo) {
		String[] regInfo = new String[6];
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		regInfo[0] = "A2";
		this.print("]\n\n직원등록\n\n[Employee Code]  : ");
		regInfo[1] = sc.next();
		this.print("[Access Code]  : ");
		regInfo[2] = sc.next();
		this.print("[Employee Name]  : ");
		regInfo[3] = sc.next();
		this.print("[Employee Phone]  : ");
		regInfo[4] = sc.next();
		this.print("[Employee Level]  : ");
		regInfo[5] = sc.next();
		
		return regInfo;
	}
	
	private String[] employeeMod(String title, String[] userInfo) {
		String[] modInfo = null;
		return modInfo;
	}
	
	private String title() {
		String title = "\n\n\n" 
				+ "--------------------------------------------------\n\n"
				+ "\t\tPoint Of Sales System v1.0\n\n"
				+ "\t\t\t\tby Maginot\n\n"
				+ "--------------------------------------------------\n\n";
		return title;
	}
	
	private void print(String str) {
		System.out.print(str);
	}

}
