package new_calculator.operator;

public class Operator {
	
	private int num1; // ������ field���� ���� 
	private int num2;

	public Operator(int num1, int num2) { /* ������ class�� �ٸ� class�� ȣ���� �� ��� �������� parameter��
											 �Էµ� ������ �޾Ƶ��δ�. */
		this.num1 = num1; // class�� ȣ��Ǹ鼭 parameter�� �޾Ƶ��� local������ field������ ����ش�.
		this.num2 = num2;
	}

	public int plus() { // �� method�� ������ �Ǹ� ����� ���� return���ִ� return method�̴�.
		return num1 + num2; // field������ num1�� num2�� ���� ���� return���ش�.
	}

	public int minus() {
		return num1 - num2; // field������ num1�� num2�� �� ���� return���ش�.
	}
	
	public int product() {
		return num1 * num2; // field������ num1�� num2�� ���� ���� return���ش�.
	}
	
	public int devide() {
		return num1 / num2; // field������ num1�� num2��  ���� ���� return���ش�.
	}

}
