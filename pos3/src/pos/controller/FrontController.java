package pos.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class FrontController {
	private Scanner sc;

	public FrontController() {
		this.sc = new Scanner(System.in);
		this.init(this.title());
		this.sc.close();
	}

	private void init(String title) {
		String[][] goodsList = null;
		String[] saleInfo;
		String[] goodsInfo;
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
						while(true) {
							saleInfo = this.sale(title, userInfo);

							if(saleInfo == null) {
								break;
							}

							goodsInfo = bc.saleGoodsInfo(saleInfo, goodsList);
							goodsList = this.makeList(goodsInfo);

							while(true) {
								saleInfo = this.sale(title, userInfo, goodsList);

								if(saleInfo == null) {
									break;
								}

								switch(saleInfo[0]) {
								case "S1":
									goodsInfo = bc.saleGoodsInfo(saleInfo, goodsList);
									goodsList = this.makeList(goodsInfo, goodsList);
									break;
								case "S2":
									if(this.payment(title, userInfo, goodsList)) {
										bc.setSaleInfo(saleInfo, goodsList);
									}
									break;
								}
								if(!saleInfo[0].equals("S1")) {
									break;
								}
							}
						}
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

	private String[] sale(String title, String[] userInfo) {
		String[] saleInfo = new String[2];

		saleInfo[0] = "S1";
		this.print(title + "Sale\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n0. 이전화면");
		this.print("\n\n상품코드 : ");
		saleInfo[1] = sc.next();

		if(saleInfo[1].equals("0")) {
			saleInfo = null;
		}

		return saleInfo;
	}

	private String[] sale(String title, String[] userInfo, String[][] goodsList) {
		SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date originalDate;
		String date = null;
		String[] saleInfo = new String[2];
		String select;
		int totalCost = 0;

		try {
			originalDate = originalDateFormat.parse(goodsList[goodsList.length-1][0]);
			date = dateFormat.format(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int i=0;i<goodsList.length;i++) {
			totalCost += (Integer.parseInt(goodsList[i][3]) * Integer.parseInt(goodsList[i][4]));
		}

		saleInfo[0] = "S1";
		this.print(title + "Sale\n\n[ ");

		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}

		this.print("]\n\n--------------------------------------------------\n" +
				date + "\n" + 
				"--------------------------------------------------\n" +
				"\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n"); 

		for(int i=0;i<goodsList.length;i++) {
			for(int j=1;j<goodsList[i].length-1;j++) {
				this.print("\t" + goodsList[i][j]);
			}
			this.print("\n");
		}

		this.print("--------------------------------------------------\n\n" +
				"\t\t\t총 합계 : " + totalCost + "\n" + 
				"0. 이전화면\n\n1. 다음상품\t\t2. 결제\n" +
				"\n\n________________________________ Select : ");
		select = sc.next();

		if(select.equals("0")) {
			saleInfo = null;
		} else {
			if(select.equals("2")) {
				saleInfo[0] = "S2";
			} else {
				this.print("\n\n상품코드 : ");
				saleInfo[1] = sc.next();
			}
		}

		return saleInfo;
	}

	private String[][] makeList(String[] goodsInfo) {
		String[][] goodsList = new String[1][];

		goodsList[0] = goodsInfo;

		return goodsList;
	}

	private String[][] makeList(String[] goodsInfo, String[][] preGoodsList) {
		String[][] goodsList;
		boolean flag = false;

		for(int i=0;i<preGoodsList.length;i++) {
			if(preGoodsList[i][1].contentEquals(goodsInfo[1])) {
				flag = true;
			}
		}
		if(flag) {
			goodsList = new String[preGoodsList.length][];
		} else {
			goodsList = new String[preGoodsList.length+1][];
		}


		for(int i=0;i<goodsList.length;i++) {
			if(i<preGoodsList.length) {
				goodsList[i] = preGoodsList[i];	
				if(goodsInfo[1].equals(preGoodsList[i][1])) {
					goodsList[i][3] = goodsInfo[3];
				}
			} else {
				goodsList[i] = goodsInfo;
			}
		}

		return goodsList;
	}

	private boolean payment(String title, String[] userInfo, String[][] goodsList) {
		SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date originalDate;
		String date = null;
		boolean result = false;
		String select;
		int totalCost = 0;
		int money;

		try {
			originalDate = originalDateFormat.parse(goodsList[goodsList.length-1][0]);
			date = dateFormat.format(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int i=0;i<goodsList.length;i++) {
			totalCost += (Integer.parseInt(goodsList[i][3]) * Integer.parseInt(goodsList[i][4]));
		}

		this.print(title + "Sale\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n--------------------------------------------------\n" + 
				date + "\n" + 
				"--------------------------------------------------\n" +
				"\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n"); 
		for(int i=0;i<goodsList.length;i++) {
			for(int j=1;j<goodsList[i].length-1;j++) {
				this.print("\t" + goodsList[i][j]);
			}
			this.print("\n");
		}
		this.print("--------------------------------------------------\n\n" +
				"\t\t\t총 합계 : " + totalCost + "\n" + 
				"결제하시겠습니까?(y/n) :");
		select = sc.next();

		if(select.equals("y")) {
			this.print("받은금액 : ");
			money = sc.nextInt();
			this.print("거스름돈 : " + (money-totalCost) +
					"\n\n1. 결제" +
					"\n__________: ");
			select = sc.next();
			if(select.equals("1")) {
				result = true;
			}
		}

		return result;
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
