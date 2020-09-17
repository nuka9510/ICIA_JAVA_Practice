package operator_calculator;

public class Calculate {
	int num1;
	int num2;
	
	public Calculate(int num1, int num2) {
		this.num1 = num1;
		this.num2 = num2;
	}
	
	public int plus() {
		return num1+num2;
	}
	
	public int minus() {
		return num1-num2;
	}
	
	public int product() {
		return num1*num2;
	}
	
	public int devide() {
		return num1/num2;
	}

}
