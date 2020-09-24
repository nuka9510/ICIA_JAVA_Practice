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
		this.select(this.signIn(title));
	}
	
	private String[] signIn(String title) {
		String[] signIn;
		String[] userInfo = new String[2];
		BackController bc = new BackController();
		
		this.print(title+"\t[Sign In]\n" + 
	            "\n" + 
	            "\t[Employee Code] : ");
		userInfo[0] = this.sc.next();
		this.print("\t[Access Code]   : ");
		userInfo[1] = this.sc.next();
		
		signIn = bc.signIn(userInfo);
		return signIn;
	}
	
	private void select(String[] info) {
		this.print("selectService\n");
		this.print(info[0] + "\n");
		this.print(info[1] + "\n");
		this.print(info[2] + "\n");
		this.print(info[3] + "\n");
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
