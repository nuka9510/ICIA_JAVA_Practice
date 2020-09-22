package calculator.operation;

public class Operator {
	
	public Operator() {
		
	}
	
	public int operationControl(String[] userData) {
		int result;
		int[] num;
		num = stringToInt(userData);
		
		switch(num[1]){
			case 1:
				result = plus(num);
				break;
			case 2:
				result = minus(num);
				break;
			case 3:
				result = product(num);
				break;
			case 4:
				result = divide(num);
				break;
			default:
				result = 0;
		}
		return result;
	}
	
	private int[] stringToInt(String[] str) {
		int[] num = new int[str.length];
		for(int i=0;i<str.length;i++) {
			num[i] = Integer.parseInt(str[i]);
		}
		return num;
	}
	
	private int plus(int[] num) {
		return num[0] + num[2];
	}
	
	private int minus(int[] num) {
		return num[0] - num[2];
	}
	
	private int product(int[] num) {
		return num[0] * num[2];
	}
	
	private int divide(int[] num) {
		return num[0] / num[2];
	}

}
