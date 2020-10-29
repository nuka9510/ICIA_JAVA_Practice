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
		String[][] refundList;
		String[] userInfo = null;
		String[] saleInfo;
		String[] goodsInfo;
		String[] refundInfo;
		String selectService;
		String empoyeeManage;
		String salesManage;
		BackController bc = new BackController();

		while(true) {
			userInfo = this.signIn(title);

			if(userInfo[1].equals("0")) {
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

							goodsInfo = bc.saleGoodsInfo(saleInfo);
							if(goodsInfo != null) {
								if(this.isExpireDate(goodsInfo)) {
									goodsList = this.makeList(goodsInfo);

									while(true) {
										saleInfo = this.sale(title, userInfo, goodsList);

										if(saleInfo == null) {
											break;
										}

										switch(saleInfo[0]) {
										case "S1":
											goodsInfo = bc.saleGoodsInfo(saleInfo);
											if(goodsInfo != null) {
												if(this.isExpireDate(goodsInfo)) {
													goodsList = this.makeList(goodsInfo, goodsList);
												}
											}
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
							}
						}
						break;
					case "2":
						refundInfo = this.refund(title, userInfo);
						refundList = bc.getRefundList(refundInfo);
						refundInfo = this.refund(title, userInfo, refundList);

						switch(refundInfo[0]) {
						case "0":
							break;
						case "S4":
							bc.setRefundList(refundInfo, refundList);
							break;
						case "S5":
							goodsList = this.makeRefundList(refundInfo, refundList);
							while(true) {
								refundInfo = this.refund(title, userInfo, refundList, goodsList);
								if(refundInfo[0].equals("S4")) {
									bc.setRefundList(refundInfo, goodsList);
									break;
								}
								goodsList = this.makeRefundList(refundInfo, refundList, goodsList);
							}
							break;
						}
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
						break;
					case "4":
						while(true) {
							salesManage = this.salesManage(title, userInfo);
							switch(salesManage) {
							case "0":
								break;
							case "1":
								goodsInfo = this.goodsReg(title, userInfo);
								bc.goodsReg(goodsInfo);
								break;
							case "3":
							case "4":
							case "5":
								while(true) {
									saleInfo = this.dailySaleInfo(title, userInfo, salesManage);

									if(saleInfo[0].equals("0")) {
										break;	
									}

									goodsList = bc.getDailySaleInfo(saleInfo);
									saleInfo = this.dailySaleInfo(title, userInfo, salesManage, goodsList);

									if(saleInfo[0].equals("0")) {
										break;	
									}
								}
								break;
							}
							if(salesManage.equals("0")) {
								break;
							}
						}
					}

					if(selectService.equals("0")) {
						selectService = null;
						break;
					}
				}
			}
			bc.signOut(userInfo);
		}
	}

	private String[] signIn(String title) {
		String[] userInfo = new String[4];

		userInfo[0] = "A1";
		this.print(title+"\t[Sign In]\n" + 
				"\n" + 
				"\t[Store Code] : ");
		userInfo[1] = this.sc.next();
		if(!userInfo[1].equals("0")) {
			this.print("\t[Employee Code]   : ");
			userInfo[2] = this.sc.next();
			this.print("\t[Access Code]   : ");
			userInfo[3] = this.sc.next();
		}

		return userInfo;
	}

	private String select(String title, String[] userInfo) {

		this.print(title + "selectService\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n1. 상품판매\t\t2. 상품반품\n"); 
		if(userInfo[4].equals("Manager")) {
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
		regInfo[1] = userInfo[0];
		this.print(title + "Employee Registration\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		} 
		this.print("]\n" + 
				"\n" + 
				"\t[Employee Code] : ");
		regInfo[2] = sc.next();
		this.print("\t[Access Code]   : ");
		regInfo[3] = sc.next();
		this.print("\t[Employee Name]   : ");
		regInfo[4] = sc.next();
		this.print("\t[Employee Level]   : ");
		regInfo[5] = sc.next();

		return regInfo;
	}

	private String[] employeeMod(String title, String[] userInfo) {
		String[] modInfo = new String[4];
		modInfo[0] = "A3";
		modInfo[1] = userInfo[0];
		this.print(title + "Employee Modify\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n\t[Employee Code]   : ");
		modInfo[2] = this.sc.next();
		this.print("\t[Access Code]   : ");
		modInfo[3] = this.sc.next();

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
				"결제번호\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n"); 

		for(int i=0;i<goodsList.length;i++) {
			this.print((i+1)+"");
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
				saleInfo[1] = "S";
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
			if(preGoodsList[i][1].equals(goodsInfo[1])) {
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

	private boolean isExpireDate(String[] goodsInfo) {
		boolean result = true;

		if(Integer.parseInt(goodsInfo[5]) < Integer.parseInt(goodsInfo[0].substring(0, 8))) {
			result = false;
		}

		return result;
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

	private String[] refund(String title, String[] userInfo) {
		String[] result = new String[2];
		result[0] = "S3";
		this.print(title + "Refund\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n0. 이전화면"); 
		this.print("\n\n주문코드________ : ");
		result[1] = sc.next();
		if(result[1].equals("0")) {
			result = null;
		}
		return result;
	}

	private String[] refund(String title, String[] userInfo, String[][] refundList) {
		SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date originalDate;
		String date = null;
		String[] refundInfo = new String[2];
		String select;
		int totalCost = 0;

		try {
			originalDate = originalDateFormat.parse(refundList[refundList.length-1][0]);
			date = dateFormat.format(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int i=0;i<refundList.length;i++) {
			totalCost += (Integer.parseInt(refundList[i][3]) * Integer.parseInt(refundList[i][4]));
		}

		refundInfo[0] = null;
		this.print(title + "Refund\n\n[ ");

		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}

		this.print("]\n\n--------------------------------------------------\n" +
				date + "\n" + 
				"--------------------------------------------------\n" +
				"결제번호\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n"); 

		for(int i=0;i<refundList.length;i++) {
			this.print((i+1)+"");
			for(int j=1;j<refundList[i].length-1;j++) {
				this.print("\t" + refundList[i][j]);
			}
			this.print("\n");
		}

		this.print("--------------------------------------------------\n\n" +
				"\t\t\t총 합계 : " + totalCost + "\n" + 
				"0. 이전화면\n\n1. 전 품목\t\t2. 선택 품목" +
				"\n\n________________________________ Select : ");
		select = sc.next();

		switch(select) {
		case "0":
			break;
		case "1":
			refundInfo[0] = "S4";
			refundInfo[1] = "C";
			break;
		case "2":
			refundInfo[0] = "S5";
			this.print("\n\n결제번호 : ");
			refundInfo[1] = sc.next();
			break;
		}

		return refundInfo;
	}

	private String[] refund(String title, String[] userInfo, String[][] refundList, String[][] goodsList) {
		SimpleDateFormat originalDateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
		Date originalDate;
		String date = null;
		String[] refundInfo = new String[2];
		String select;
		int totalCost = 0;

		try {
			originalDate = originalDateFormat.parse(refundList[refundList.length-1][0]);
			date = dateFormat.format(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int i=0;i<refundList.length;i++) {
			totalCost += (Integer.parseInt(refundList[i][3]) * Integer.parseInt(refundList[i][4]));
		}
		
		for(int i=0;i<goodsList.length;i++) {
			totalCost -= (Integer.parseInt(goodsList[i][3]) * Integer.parseInt(goodsList[i][4]));
		}

		refundInfo[0] = null;
		this.print(title + "Refund\n\n[ ");

		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}

		this.print("]\n\n--------------------------------------------------\n" +
				date + "\n" + 
				"--------------------------------------------------\n" +
				"결제번호\t상품코드\t상품명\t수량\t단가\n" + 
				"--------------------------------------------------\n"); 

		for(int i=0;i<refundList.length;i++) {
			this.print((i+1)+"");
			for(int j=1;j<refundList[i].length-1;j++) {
				this.print("\t" + refundList[i][j]);
			}
			this.print("\n");
		}
		this.print("--------------------------------------------------\n"); 


		for(int i=0;i<goodsList.length;i++) {
			for(int j=1;j<goodsList[i].length-1;j++) {
				if(j==4) {
					this.print("\t" + "－" + goodsList[i][j]);
				}else {
					this.print("\t" + goodsList[i][j]);
				}
			}
			this.print("\n");
		}

		this.print("--------------------------------------------------\n\n" +
				"\t\t\t총 합계 : " + totalCost + "\n" + 
				"0. 이전화면\n\n1. 다음 품목\t\t2. 반품" +
				"\n\n________________________________ Select : ");
		select = sc.next();

		switch(select) {
		case "0":
			refundInfo = null;
			break;
		case "1":
			refundInfo[0] = "S5";
			this.print("\n\n결제번호 : ");
			refundInfo[1] = sc.next();
			break;
		case "2":
			refundInfo[0] = "S4";
			break;
		}

		return refundInfo;
	}

	private String[][] makeRefundList(String[] refundInfo, String[][] refundList) {
		String[][] result = new String[1][refundList[0].length];

		for(int i=0;i<refundList[0].length;i++) {
			result[0][i] = refundList[Integer.parseInt(refundInfo[1])-1][i];
		}

		return result;
	}

	private String[][] makeRefundList(String[] refundInfo, String[][] refundList, String[][] goodsList) {
		boolean flag = true;
		String[][] result;

		for(int i=0;i<goodsList.length;i++) {
			if(goodsList[i].equals(refundList[Integer.parseInt(refundInfo[1])-1])) {
				flag = false;
			}
		}

		if(flag) {
			result = new String[goodsList.length+1][refundList[0].length];
		}else {
			result = new String[goodsList.length][refundList[0].length];
		}

		for(int i=0;i<result.length;i++) {
			if(i<goodsList.length) {
				result[i] = goodsList[i];
			}else {
				result[i] = refundList[Integer.parseInt(refundInfo[1])-1];
			}
		}

		return result;
	}

	private String salesManage(String title, String[] userInfo) {
		this.print(title + "Employee Management\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		} 
		this.print("]\n\n1. 상품등록\t\t2. 상품정보수정\n3. 일별매출현황\t\t4. 월별매출현황\n5. 월별베스트상품\n0. 이전화면\n\n________________________________ Select : ");
		return sc.next();
	}

	private String[] goodsReg(String title, String[] userInfo) {
		String[] goodsInfo = new String[7];

		goodsInfo[0] = "M1";
		this.print(title + "Employee Registration\n\n[ ");
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		} 
		this.print("]\n" + 
				"\n" + 
				"\t[Goods Code] : ");
		goodsInfo[1] = sc.next();
		this.print("\t[Goods Name]   : ");
		goodsInfo[2] = sc.next();
		this.print("\t[Goods Price]   : ");
		goodsInfo[3] = sc.next();
		this.print("\t[Goods Expire Date]   : ");
		goodsInfo[4] = sc.next();
		this.print("\t[Goods Stock]   : ");
		goodsInfo[5] = sc.next();
		this.print("\t[Goods Safety Stock]   : ");
		goodsInfo[6] = sc.next();

		return goodsInfo;
	}

	private String[] dailySaleInfo(String title, String[] userInfo, String salesManage) {
		String[] result = new String[2];
		System.out.println(salesManage);
		
		result[0] = null;
		
		this.print(title);
		
		if(salesManage.equals("3")) {
			this.print("일별매출현황\n\n[ ");
			result[0] = "M3";
		}else if(salesManage.equals("4")) {
			this.print("월별매출현황\n\n[ ");
			result[0] = "M3";
		}else if(salesManage.equals("5")) {
			this.print("월별베스트상품\n\n[ ");
			result[0] = "M4";
		}
		
		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}
		this.print("]\n\n0. 이전화면"); 
		this.print("\n\n날짜________ : ");
		result[1] = sc.next();

		if(result[1].equals("0")) {
			result[0] = "0";
		}

		return result;
	}

	private String[] dailySaleInfo(String title, String[] userInfo, String salesManage, String[][] goodsList) {
		SimpleDateFormat originalDateFormat = null;
		SimpleDateFormat dateFormat = null;
		Date originalDate;
		String date = null;
		String[] result = new String[2];
		boolean flag = true;
		int totalCost = 0;
		int count = 1;

		if(salesManage.equals("3")) {
			originalDateFormat = new SimpleDateFormat("yyyyMMdd");
			dateFormat = new SimpleDateFormat("yyyy.MM.dd");
		}else if(salesManage.equals("4")||salesManage.equals("5")) {
			originalDateFormat = new SimpleDateFormat("yyyyMM");
			dateFormat = new SimpleDateFormat("yyyy.MM");
		}

		try {
			originalDate = originalDateFormat.parse(goodsList[goodsList.length-1][0]);
			date = dateFormat.format(originalDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		for(int i=0;i<goodsList.length;i++) {
			totalCost += Integer.parseInt(goodsList[i][5]);
		}

		this.print(title);
		if(salesManage.equals("3")) {
			this.print("일별매출현황\n\n[ ");
			result[0] = "M3";
		}else if(salesManage.equals("4")) {
			this.print("월별매출현황\n\n[ ");
			result[0] = "M3";
		}else if(salesManage.equals("5")) {
			this.print("월별베스트상품\n\n[ ");
			result[0] = "M4";
		}

		for(int i=0;i<userInfo.length;i++) {
			this.print(userInfo[i] + " ");
		}

		this.print("]\n\n--------------------------------------------------\n" +
				date + "\n" + 
				"--------------------------------------------------\n" +
				"순번\t상품코드\t상품명\t상품단가\t수량\t금액\n" + 
				"--------------------------------------------------\n"); 


		for(int i=0;i<goodsList.length;i++) {
			if(i>=1) {
				if(!goodsList[i][0].equals(goodsList[i-1][0])) {
					flag = true;
					count++;
				}
			}
			if(flag) {
				this.print(count+"");
				flag = false;
			}

			for(int j=1;j<goodsList[i].length-1;j++) {
				this.print("\t" + goodsList[i][j]);
			}
			this.print("\n");
		}

		this.print("--------------------------------------------------\n\n" +
				"\t\t\t총 합계 : " + totalCost + "\n" + 
				"0. 관리메뉴\n\n1. 확인" +
				"\n\n________________________________ Select : ");
		result[1] = sc.next();

		if(result[1].equals("0")) {
			result[0] = "0";
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
