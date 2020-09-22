package new_calculator.controller;

import new_calculator.frontend.FrontEnd;

public class Controller {

	public static void main(String[] args) { /* 프로그램을 실행했을 경우 가장 먼저 실행되는 class에는
	 											static void method를 입력해준다.*/
		new FrontEnd(); // FrontEnd class의 생성자를 입력함으로써 현재의 class에 FrontEnd class의 member를 호출한다.

	}

}
