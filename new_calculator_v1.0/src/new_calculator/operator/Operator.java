package new_calculator.operator;

public class Operator {
	
	private int num1; // 정수형 field변수 선언 
	private int num2;

	public Operator(int num1, int num2) { /* 현재의 class가 다른 class에 호출이 될 경우 생성자의 parameter에
											 입력된 변수를 받아들인다. */
		this.num1 = num1; // class가 호출되면서 parameter에 받아들인 local변수를 field변수에 담아준다.
		this.num2 = num2;
	}

	public int plus() { // 이 method가 실행이 되면 실행된 값을 return해주는 return method이다.
		return num1 + num2; // field변수인 num1과 num2를 합한 값을 return해준다.
	}

	public int minus() {
		return num1 - num2; // field변수인 num1과 num2를 뺀 값을 return해준다.
	}
	
	public int product() {
		return num1 * num2; // field변수인 num1과 num2를 곱한 값을 return해준다.
	}
	
	public int devide() {
		return num1 / num2; // field변수인 num1과 num2를  나눈 값을 return해준다.
	}

}
