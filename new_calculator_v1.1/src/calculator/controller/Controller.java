package calculator.controller;

import java.util.Scanner;

import calculator.operation.Operator;

public class Controller {

	public Controller() {
		controller(title(), menu());
	}
	
	private void controller(String title, String[] menu) {
		boolean flag;
		String[] userData = new String[3];
		int result;
		
		print(title
				+ " [시작할까요?]\n"
				+ "1. 시작      0. 종료"
				+ "_____________________ : ");
		flag = (userInput().equals("1"))?true:false;
		
		while(flag) {
			for(int i=0;i<menu.length;i++) {
				print(title + menu[i]);
				userData[i] = userInput();
			}
			
			Operator op = new Operator();
			result = op.operationControl(userData);
			
			print("[연산 결과]\n"
					+ userData[0]
					+ (userData[1].equals("1")?" ＋ "
							:userData[1].equals("2")?" － "
									:userData[1].equals("3")?" × ":" ÷ ")
					+ userData[2] + " = " + result + "\n\n"
					+ "continue? (y/n)\n"
					+ "_____________________ : ");
			flag = userInput().equals("n")?false:true;
		}
		print("..............프로그램을 종료합니다.");
	}

	private String title() {
		StringBuilder sb = new StringBuilder();
		sb.append("\n\n\n");
		sb.append("------------------------------------------\n\n");
		sb.append("\tFour Arithmetical Operator v1.0\n\n");
		sb.append("\t\t\tby nuka\n\n");
		sb.append("------------------------------------------\n\n");
		return sb.toString();
	}
	
	private String[] menu() {
		String[] pattern = new String[3];
		pattern[0] = "[숫자 입력]\n"
				+ " : ";
		pattern[1] = "[연산자 선택]\n"
				+ "1. 더하기    2. 빼기\n"
				+ "3. 곱하기    4. 나누기\n"
				+ "_____________________ : ";
		pattern[2] = "[숫자 입력]\n"
				+ " : ";
		return pattern;
	}

	private String userInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private void print(String str) {
		System.out.println(str);
	}

}
