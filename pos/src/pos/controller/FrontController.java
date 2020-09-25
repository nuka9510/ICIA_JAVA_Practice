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
		this.select(title, userInfo);
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
	
	private void select(String title, String[] userinfo) {
		this.print(title + "selectService\n\n[ ");
		for(int i=0;i<userinfo.length;i++) {
			this.print(userinfo[i] + " ");
		}
		this.print("]");
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
