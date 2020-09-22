package new_calculator.frontend;

import java.util.Scanner;

import new_calculator.operator.Operator;

public class FrontEnd {

	public FrontEnd() { // 현재의 class가 다른 class에 호출되기 위해 필요한 생성자이다.
		StringBuilder sb = new StringBuilder(); /* 만일 문자열이 자주 수정이 이루어지는 값이면 String보다는
		 										   StringBuffer 또는 StringBuilder를 사용하는 것이 좋은데,
		 										      실행하는 프로그램이 server에서 사용이 되는 즉 멀티스레드 환경에서
		 										      사용되는 프로그램일 경우는 StringBuffer를 사용하고,
		 										      단일스레드에서 사용되는 프로그램일 경우는 StringBuilder를
		 										      사용하는 것이 좋다. */
		sb.append("\n\n\n"); /* StringBuffer나 StringBuilder class를 사용할 경우 append method를 사용함으로써
		 						문자열을 추가해줄 수 있다.*/
		sb.append("------------------------------------------\n\n");
		sb.append("\tFour Arithmetical Operator v1.0\n\n");
		sb.append("\t\t\tby nuka\n\n");
		sb.append("------------------------------------------\n\n");
	
		enterance(sb.toString(), pattern()); /* enterance method에 String type 데이터인 sb.toString()과
												String type 배열인 pattern()을 넣어준다.
												StringBuffer나 StringBuilder class를 사용할 경우
												toString method를 사용함으로써
									  			입력된 문자열을 String datatype으로 변환해줄 수 있다.*/
	}

	private void enterance(String str, String[] sarr) { /* parameter로 받은 String type 데이터와 String type 배열을 
														   entrance method의 local변수로 사용한다. */
		String continuos;
		String[] userData = new String[3]; // 길이가 3인 String type 배열을 선언해준다.
		int result;
		boolean start;

		print(str
				+ " [시작할까요?]\n"
				+ "1. 시작      0. 종료"
				+ "_____________________ : ");
		start = (userInput().equals("1"))?true:false;
		
		while(start) {
			for(int i=0;i<sarr.length;i++) { // i<sarr.length일 경우 실행한다. 실행을 한뒤 i = i + 1을 해준다.
				print(str + sarr[i]);
				userData[i] = userInput();
			}

			Operator op = new Operator(Integer.parseInt(userData[0]), Integer.parseInt(userData[2]));
			if(userData[1].equals("1")) {
				userData[1] = " ＋  ";
				result = op.plus();
			} else if(userData[1].equals("2")) {
				userData[1] = " － ";
				result = op.minus();
			} else if(userData[1].equals("3")) {
				userData[1] = " × ";
				result = op.product();
			} else {
				userData[1] = " ÷ ";
				result = op.devide();
			}

			print("[연산 결과]\n"
					+ userData[0] + userData[1] + userData[2] + " = " + result + "\n\n"
					+ "continue? (y/n)\n"
					+ "_____________________ : ");
			continuos = userInput();
			if(continuos.equals("n")) {
				start = false;
			}
		}
	}
	
	private String[] pattern() { // String type의 배열을 return해주는 return method이다.
		String[] pattern = new String[3]; // 길이가 3인 String type의 배열(pattern)을 선언해준다.
		pattern[0] = "[숫자 입력]\n" // patter[0]에 문자열을 넣는다.
				+ " : ";
		pattern[1] = "[연산자 선택]\n" // patter[1]에 문자열을 넣는다.
				+ "1. 더하기    2. 빼기\n"
				+ "3. 곱하기    4. 나누기\n"
				+ "_____________________ : ";
		pattern[2] = "[숫자 입력]\n" // patter[2]에 문자열을 넣는다.
				+ " : ";
		return pattern; // 배열(pattern)을 return해준다.
	}
	
	private String userInput() { // String type의 데이터를 return해주는 return method이다.
		Scanner scanner = new Scanner(System.in); // Scanner class를 사용함으로써 사용자로부터 문자를 입력받는다.
		return scanner.next(); // 사용자로부터 입력 받은 문자를 return해준다.
	}

	private void print(String str) {
		System.out.print(str); // 현재 method에 parameter로 받은 local변수를 출력해준다.
	}

}
