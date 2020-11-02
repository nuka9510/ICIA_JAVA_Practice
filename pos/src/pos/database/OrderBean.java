package pos.database;

public class OrderBean {
	private String request;
	private String odCode;
	private String stCode;
	private String emCode;
	private String cmCode;
	private String odState;
	public String getOdCode() {
		return odCode;
	}
	public void setOdCode(String odCode) {
		this.odCode = odCode;
	}
	public String getStCode() {
		return stCode;
	}
	public void setStCode(String stCode) {
		this.stCode = stCode;
	}
	public String getEmCode() {
		return emCode;
	}
	public void setEmCode(String emCode) {
		this.emCode = emCode;
	}
	public String getCmCode() {
		return cmCode;
	}
	public void setCmCode(String cmCode) {
		this.cmCode = cmCode;
	}
	public String getOdState() {
		return odState;
	}
	public void setOdState(String odState) {
		this.odState = odState;
	}
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}

}
