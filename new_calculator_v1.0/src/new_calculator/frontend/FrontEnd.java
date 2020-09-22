package new_calculator.frontend;

import java.util.Scanner;

import new_calculator.operator.Operator;

public class FrontEnd {

	public FrontEnd() { // ������ class�� �ٸ� class�� ȣ��Ǳ� ���� �ʿ��� �������̴�.
		StringBuilder sb = new StringBuilder(); /* ���� ���ڿ��� ���� ������ �̷������ ���̸� String���ٴ�
		 										   StringBuffer �Ǵ� StringBuilder�� ����ϴ� ���� ������,
		 										      �����ϴ� ���α׷��� server���� ����� �Ǵ� �� ��Ƽ������ ȯ�濡��
		 										      ���Ǵ� ���α׷��� ���� StringBuffer�� ����ϰ�,
		 										      ���Ͻ����忡�� ���Ǵ� ���α׷��� ���� StringBuilder��
		 										      ����ϴ� ���� ����. */
		sb.append("\n\n\n"); /* StringBuffer�� StringBuilder class�� ����� ��� append method�� ��������ν�
		 						���ڿ��� �߰����� �� �ִ�.*/
		sb.append("------------------------------------------\n\n");
		sb.append("\tFour Arithmetical Operator v1.0\n\n");
		sb.append("\t\t\tby nuka\n\n");
		sb.append("------------------------------------------\n\n");
	
		enterance(sb.toString(), pattern()); /* enterance method�� String type �������� sb.toString()��
												String type �迭�� pattern()�� �־��ش�.
												StringBuffer�� StringBuilder class�� ����� ���
												toString method�� ��������ν�
									  			�Էµ� ���ڿ��� String datatype���� ��ȯ���� �� �ִ�.*/
	}

	private void enterance(String str, String[] sarr) { /* parameter�� ���� String type �����Ϳ� String type �迭�� 
														   entrance method�� local������ ����Ѵ�. */
		String continuos;
		String[] userData = new String[3]; // ���̰� 3�� String type �迭�� �������ش�.
		int result;
		boolean start;

		print(str
				+ " [�����ұ��?]\n"
				+ "1. ����      0. ����"
				+ "_____________________ : ");
		start = (userInput().equals("1"))?true:false;
		
		while(start) {
			for(int i=0;i<sarr.length;i++) { // i<sarr.length�� ��� �����Ѵ�. ������ �ѵ� i = i + 1�� ���ش�.
				print(str + sarr[i]);
				userData[i] = userInput();
			}

			Operator op = new Operator(Integer.parseInt(userData[0]), Integer.parseInt(userData[2]));
			if(userData[1].equals("1")) {
				userData[1] = " ��  ";
				result = op.plus();
			} else if(userData[1].equals("2")) {
				userData[1] = " �� ";
				result = op.minus();
			} else if(userData[1].equals("3")) {
				userData[1] = " �� ";
				result = op.product();
			} else {
				userData[1] = " �� ";
				result = op.devide();
			}

			print("[���� ���]\n"
					+ userData[0] + userData[1] + userData[2] + " = " + result + "\n\n"
					+ "continue? (y/n)\n"
					+ "_____________________ : ");
			continuos = userInput();
			if(continuos.equals("n")) {
				start = false;
			}
		}
	}
	
	private String[] pattern() { // String type�� �迭�� return���ִ� return method�̴�.
		String[] pattern = new String[3]; // ���̰� 3�� String type�� �迭(pattern)�� �������ش�.
		pattern[0] = "[���� �Է�]\n" // patter[0]�� ���ڿ��� �ִ´�.
				+ " : ";
		pattern[1] = "[������ ����]\n" // patter[1]�� ���ڿ��� �ִ´�.
				+ "1. ���ϱ�    2. ����\n"
				+ "3. ���ϱ�    4. ������\n"
				+ "_____________________ : ";
		pattern[2] = "[���� �Է�]\n" // patter[2]�� ���ڿ��� �ִ´�.
				+ " : ";
		return pattern; // �迭(pattern)�� return���ش�.
	}
	
	private String userInput() { // String type�� �����͸� return���ִ� return method�̴�.
		Scanner scanner = new Scanner(System.in); // Scanner class�� ��������ν� ����ڷκ��� ���ڸ� �Է¹޴´�.
		return scanner.next(); // ����ڷκ��� �Է� ���� ���ڸ� return���ش�.
	}

	private void print(String str) {
		System.out.print(str); // ���� method�� parameter�� ���� local������ ������ش�.
	}

}
