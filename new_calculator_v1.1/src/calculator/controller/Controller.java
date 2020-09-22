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
				+ " [�����ұ��?]\n"
				+ "1. ����      0. ����"
				+ "_____________________ : ");
		flag = (userInput().equals("1"))?true:false;
		
		while(flag) {
			for(int i=0;i<menu.length;i++) {
				print(title + menu[i]);
				userData[i] = userInput();
			}
			
			Operator op = new Operator();
			result = op.operationControl(userData);
			
			print("[���� ���]\n"
					+ userData[0]
					+ (userData[1].equals("1")?" �� "
							:userData[1].equals("2")?" �� "
									:userData[1].equals("3")?" �� ":" �� ")
					+ userData[2] + " = " + result + "\n\n"
					+ "continue? (y/n)\n"
					+ "_____________________ : ");
			flag = userInput().equals("n")?false:true;
		}
		print("..............���α׷��� �����մϴ�.");
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
		pattern[0] = "[���� �Է�]\n"
				+ " : ";
		pattern[1] = "[������ ����]\n"
				+ "1. ���ϱ�    2. ����\n"
				+ "3. ���ϱ�    4. ������\n"
				+ "_____________________ : ";
		pattern[2] = "[���� �Է�]\n"
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
