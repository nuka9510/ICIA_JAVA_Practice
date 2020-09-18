package frontend_calculator;

import java.util.Scanner;

public class FrontEnd {
	private String title;
	
	public FrontEnd() {
		this.title = this.title();
		this.entrance();
	}
	
	private void entrance() {
		this.print(this.title+"\n"
					+"[시작할까요?]\n"
					+"1. 시작\t0. 종료\n"
					+"________________ : ");
		String menu = this.userInput();
		if(menu.equals("1")) {
			System.out.println("START");
		} else {
			System.out.println("END");
		}
	}
	
	private String userInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}
	
	private String title() {
		StringBuffer title = new StringBuffer();
		title.append("--------------------------------------------\n\n");
		title.append("\tFour Arithmetical Operator v1.0\n\n");
		title.append("\t\t\t\tby nuka\n\n");
		title.append("--------------------------------------------");
		return title.toString();
		
	}
	
	private void print(String str) {
		System.out.print(str);
	}
}
