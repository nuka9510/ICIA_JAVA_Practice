package frontend_calculator;

public class FrontEnd {
	
	public FrontEnd() {
		
	}
	
	public void title() {
		String title1 = "--------------------------------------------\n\n"
				+ "\tFour Arithmetical Operator v1.0\n\n"
				+ "\t\t\t\tby nuka\n\n"
				+ "--------------------------------------------";
		println(title1);
		StringBuffer title2 = new StringBuffer();
		title2.append("--------------------------------------------\n\n");
		title2.append("\tFour Arithmetical Operator v1.0\n\n");
		title2.append("\t\t\t\tby nuka\n\n");
		title2.append("--------------------------------------------");
		println(title2.toString());
		StringBuilder title3 = new StringBuilder();
		title3.append("--------------------------------------------\n\n");
		title3.append("\tFour Arithmetical Operator v1.0\n\n");
		title3.append("\t\t\t\tby nuka\n\n");
		title3.append("--------------------------------------------");
		println(title3.toString());
		}
	
	public void println(String str) {
		System.out.println(str);
	}
}
