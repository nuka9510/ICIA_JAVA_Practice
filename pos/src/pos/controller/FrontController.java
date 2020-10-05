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
		String[] userInfo = null;
		String selectService;
		String empoyeeManage;
		BackController bc = new BackController();
		while(true) {
			userInfo = this.signIn(title);
			if(userInfo == null) {
				break;
			}
			userInfo = bc.signIn(userInfo);
			if(userInfo != null) {
				while(true) {
					selectService = this.select(title, userInfo);
					switch(selectService) {
					case "0":
						break;
					case "1":
						String[] saleInfo = this.saleFirst(title, userInfo);
						String[] saleResult = bc.sale(saleInfo);
						this.saleSecond(title, userInfo, saleResult);
						break;
					case "2":
						break;
					case "3":
						while(true) {
							empoyeeManage = this.employeeManage(title, userInfo);
							switch(empoyeeManage) {
							case "0":
								break;
							case "1":
								bc.employeeReg(this.employeeReg(title, userInfo));
								break;
							case "2":
								bc.employeeMod(this.employeeMod(title, userInfo));
								break;
							default :
								continue;
							}
							if(empoyeeManage.equals("0")) {
								empoyeeManage = null;
								break;
							}
						}
					case "4":
						break;
					default :
						continue;
					}
					if(selectService.equals("0")) {
						selectService = null;
						userInfo = null;
						break;
					}
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
		if(userInfo[1].equals("0")) {
			userInfo = null;
		}else {
			this.print("\t[Access Code]   : ");
			userInfo[2] = this.sc.next();
		}
		
		return userInfo;
	}

	private String select(String title, String[] userInfo) {

		this.print(title + "selectService\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n1. 상품판매\t\t2. 상품반품\n"); 
		if(userInfo[2].equals("Manager")) {
			this.print("3. 직원관리\t\t4. 영업관리\n");
		}
		this.print("0. 이전화면\n\n________________________________ Select : ");
		return sc.next();
	}

	private String employeeManage(String title, String[] userInfo) {
		this.print(title + "Employee Management\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		} 
		this.print("]\n\n1. 직원등록\t\t2. 직원정보수정\n0. 이전화면\n\n________________________________ Select : ");
		return sc.next();	
	}

	private String[] employeeReg(String title, String[] userInfo) {
		String[] regInfo = new String[6];

		regInfo[0] = "A2";
		this.print(title + "Employee Registration\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		} 
		this.print("]\n" + 
				"\n" + 
				"\t[Employee Code] : ");
		regInfo[1] = sc.next();
		this.print("\t[Access Code]   : ");
		regInfo[2] = sc.next();
		this.print("\t[Employee Name]   : ");
		regInfo[3] = sc.next();
		this.print("\t[Employee Phone]   : ");
		regInfo[4] = sc.next();
		this.print("\t[Employee Level]   : ");
		regInfo[5] = sc.next();

		return regInfo;
	}
	
	private String[] employeeMod(String title, String[] userInfo) {
		String[] modInfo = new String[3];
		modInfo[0] = "A3";
		this.print(title + "Employee Modify\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n\t[Employee Code]   : ");
		modInfo[1] = this.sc.next();
		this.print("\t[Access Code]   : ");
		modInfo[2] = this.sc.next();
		
		return modInfo;
	}

	private String[] saleFirst(String title, String[] userInfo) {
		String[] saleInfo = new String[2];

		saleInfo[0] = "S1";
		this.print(title + "Sale\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n상품코드 : ");
		saleInfo[1] = sc.next();
		return saleInfo;
	}
	
	private String[] saleSecond(String title, String[] userInfo, String[] saleResult) {
		String[] saleInfo = new String[2];

		saleInfo[0] = "S1";
		this.print(title + "Sale\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n--------------------------------------------------\n" + 
				"\t상품코드\t상품명\t단가\n" + 
				"--------------------------------------------------\n" + 
				"\t" + saleResult[0] + "\t" + saleResult[1] + "\t" + saleResult[2] + "\n" + 
				"--------------------------------------------------\n\n");
		this.print("상품코드 : ");
		saleInfo[1] = sc.next();
		return saleInfo;
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
