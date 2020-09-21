package frontend_calculator;

import java.util.Scanner;

import operator_calculator.Calculate;

public class FrontEnd {
	private String title;

	public FrontEnd() {
		this.title = this.title();
		this.entrance();
	}

	private void entrance() {
		String menu = new String();
		String number1 = new String();
		String number2 = new String();
		String calculate = new String();
		int result = 0;
		boolean continuos = false; // boolean�� true�� false�� �޴� datatype�̴�.
		
			this.print(this.title+"\n"
					+"[�����ұ��?]\n"
					+"1. ����\t0. ����\n"
					+"________________ : ");
			menu = this.userInput();
			if(menu.equals("1")) {
				continuos = true;
			} else {

				this.print("END");
			}
			
			while(continuos) { // �ݺ��� while�� condition�� ���� true�� ��� �������� ���๮�� �ݺ� �������ְ� false�� ��� ������ ���� �ʴ´�.
			this.print(this.title+"\n"
					+" [���� �Է�]\n"   
					+" : " );
			number1 = this.userInput();
			
			this.print(this.title+"\n"
					+"[������ ����]\n"
					+ "  1. ���ϱ�    2. ����\n"
					+ "  3. ���ϱ�    4. ������\n"
					+ " ________________________ : ");
			calculate = this.userInput();
			
			this.print(this.title+"\n"
					+" [���� �Է�]\n"
					+"  : ");
			number2 = this.userInput();
			
			Calculate ca = new Calculate(Integer.parseInt(number1), Integer.parseInt(number2));
			/* Calculate class�� ������ Calculate(int num1, int num2);�� parameter�� ������ datatype int�� �޾ƾ��ϴµ�
			   number1�� number2�� datatype�� String�̹Ƿ� parse����ȯ�� ���ش�. */
			
			switch(calculate) { // switch(expression) case(value)������ expression�� value�� ���� ���̴�. 
			case("1") : // value�� expression�� ������ �� ���� true�� ��� statement�� �������ش�.
				calculate = " �� ";
				result = ca.plus();
				break; /* switch case���� ���ǹ��Ӱ� ���ÿ� �ݺ����� ������ �����⿡
						  statement�� ����Ǹ� break;�� switch case���� ������ ���ߴ°���
						  cpu�� �������� �۾��� �ٿ��ټ� �ִ�.*/
			case("2") :
				calculate = " �� ";
				result = ca.minus();
				break;
			case("3") :
				calculate = " �� ";
				result = ca.product();
				break;
			default :
				calculate = " �� ";
				result = ca.devide();
				break;
			}
			
			this.print(this.title + "\n"
					+ "[���� ���]\n"
					+ number1 + calculate + number2 + " = " + result + "\n\n");
			
			this.print("countinue? (y/n) : ");
			if(this.userInput().equals("n")) {
				this.print("END");
				break; /* ���� while���� condition�� �Էµ� continuos������ true�� ��� ���ѹݺ������� �ǹǷ�
						  if���� condition���� true�� ��� break;�� �ݺ������� ���ߵ��� �Ѵ�. */
			}
		}
	}

	private String userInput() {
		Scanner scanner = new Scanner(System.in);
		return scanner.next();
	}

	private String title() {
		StringBuffer title = new StringBuffer();
		title.append("\n\n\n");
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
