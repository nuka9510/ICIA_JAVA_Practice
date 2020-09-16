package controller_calculator;

import operator_calculator.Calculate;

public class Input_Output {

	public static void main(String[] args) {
		Calculate ca;
		ca = new Calculate();
		System.out.println("결과값 : "+ca.plus(2, 5));
		System.out.println("결과값 : "+ca.minus(2, 5));
		System.out.println("결과값 : "+ca.product(2, 5));
		System.out.println("결과값 : "+ca.devide(2, 5));
		
	}

}
