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
		String[] userInfo = this.signIn(title);
		userInfo = bc.signIn(userInfo);
		String[] select = this.select(title, userInfo);
		System.out.println(bc.selectService(select));
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
	
	private String[] select(String title, String[] userInfo) {
		String service;
		String[] resultInfo = null;
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
		
		switch(service) {
		case "1":
			resultInfo = this.sale(title, userInfo);
			break;
		case "2":
			resultInfo = this.payback(title, userInfo);
			break;
		case "3":
			resultInfo = this.salerManagement(title, userInfo);
			break;
		case "4":
			resultInfo = this.saleManagement(title, userInfo);
			break;
		}
		
		return resultInfo;
	}
	
	private String[] sale(String title, String[] userInfo) {
		String[] resultInfo = null;
		return resultInfo;
	}
	
	private String[] payback(String title, String[] userInfo) {
		String[] resultInfo = null;
		return resultInfo;
	}
	
	private String[] salerManagement(String title, String[] userInfo) {
		String service;
		String[] resultInfo = null;
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\nselectService\n\n1. 직원등록\t\t2. 지원정보수정\n\n_____________________ Select : ");
		service = sc.next();
		
		switch(service) {
		case "1":
			resultInfo = this.salerReg(title, userInfo);
			break;
		case "2":
			resultInfo = this.salerMod(title, userInfo);
			break;
		}
		return resultInfo;
	}
	
	private String[] saleManagement(String title, String[] userInfo) {
		String[] resultInfo = null;
		return resultInfo;
	}
	
	private String[] salerReg(String title, String[] userInfo) {
		String[] regInfo = new String[6];
		this.print(title + "[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		regInfo[0] = "A2";
		this.print("]\n\nselectService\n\n[Employee Code]  : ");
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
	
	private String[] salerMod(String title, String[] userInfo) {
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
