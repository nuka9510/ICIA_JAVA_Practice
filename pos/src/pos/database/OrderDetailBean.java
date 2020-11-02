package pos.database;

public class OrderDetailBean {
	private String odCode;
	private String goCode;
	private int qty;
	private String otState;
	public String getOdCode() {
		return odCode;
	}
	public void setOdCode(String odCode) {
		this.odCode = odCode;
	}
	public String getGoCode() {
		return goCode;
	}
	public void setGoCode(String goCode) {
		this.goCode = goCode;
	}
	public int getQty() {
		return qty;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public String getOtState() {
		return otState;
	}
	public void setOtState(String otState) {
		this.otState = otState;
	}

}
