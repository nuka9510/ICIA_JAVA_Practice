package pos.database;

public class SalerBean {
	private String request;
	private String employeeCode;
	private String accessCode;
	private String salerName;
	private String phone;
	private String accessTime;
	private boolean salerLevel;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
	}
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}

	public String getEmployeeCode() {
		return employeeCode;
	}
	public void setEmployeeCode(String employeeCode) {
		this.employeeCode = employeeCode;
	}
	public String getAccessCode() {
		return accessCode;
	}
	public void setAccessCode(String accessCode) {
		this.accessCode = accessCode;
	}
	public String getSalerName() {
		return salerName;
	}
	public void setSalerName(String salerName) {
		this.salerName = salerName;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public boolean isSalerLevel() {
		return salerLevel;
	}
	public void setSalerLevel(boolean salerLevel) {
		this.salerLevel = salerLevel;
	}

}
