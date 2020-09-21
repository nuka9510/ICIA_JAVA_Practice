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
		boolean continuos = false; // boolean은 true와 false를 받는 datatype이다.
		
			this.print(this.title+"\n"
					+"[시작할까요?]\n"
					+"1. 시작\t0. 종료\n"
					+"________________ : ");
			menu = this.userInput();
			if(menu.equals("1")) {
				continuos = true;
			} else {

				this.print("END");
			}
			
			while(continuos) { // 반복문 while은 condition의 값이 true일 경우 영역안의 실행문을 반복 실행해주고 false인 경우 실행을 하지 않는다.
			this.print(this.title+"\n"
					+" [숫자 입력]\n"   
					+" : " );
			number1 = this.userInput();
			
			this.print(this.title+"\n"
					+"[연산자 선택]\n"
					+ "  1. 더하기    2. 빼기\n"
					+ "  3. 곱하기    4. 나누기\n"
					+ " ________________________ : ");
			calculate = this.userInput();
			
			this.print(this.title+"\n"
					+" [숫자 입력]\n"
					+"  : ");
			number2 = this.userInput();
			
			Calculate ca = new Calculate(Integer.parseInt(number1), Integer.parseInt(number2));
			/* Calculate class의 생성자 Calculate(int num1, int num2);는 parameter의 변수를 datatype int로 받아야하는데
			   number1과 number2의 datatype이 String이므로 parse형변환을 해준다. */
			
			switch(calculate) { // switch(expression) case(value)문에서 expression은 value와 비교할 값이다. 
			case("1") : // value와 expression을 비교했을 때 값이 true일 경우 statement를 실행해준다.
				calculate = " ＋ ";
				result = ca.plus();
				break; /* switch case문은 조건문임과 동시에 반복문의 성질을 가지기에
						  statement가 실행되면 break;로 switch case문의 실행을 멈추는것이
						  cpu의 쓸데없는 작업을 줄여줄수 있다.*/
			case("2") :
				calculate = " － ";
				result = ca.minus();
				break;
			case("3") :
				calculate = " × ";
				result = ca.product();
				break;
			default :
				calculate = " ÷ ";
				result = ca.devide();
				break;
			}
			
			this.print(this.title + "\n"
					+ "[연산 결과]\n"
					+ number1 + calculate + number2 + " = " + result + "\n\n");
			
			this.print("countinue? (y/n) : ");
			if(this.userInput().equals("n")) {
				this.print("END");
				break; /* 현재 while문은 condition에 입력된 continuos변수가 true일 경우 무한반복실행이 되므로
						  if문의 condition값이 true일 경우 break;로 반복실행이 멈추도록 한다. */
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
