package pos.database;

public class EmployeeBean {
	private String request;
	private String storeCode;
	private String storeName;
	private String employeeCode;
	private String accessCode;
	private String employeeName;
	private String employeephone;
	private String accessTime;
	private int accessState;
	private boolean employeeLevel;
	private boolean result;
	
	public String getRequest() {
		return request;
	}
	public void setRequest(String request) {
		this.request = request;
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
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeephone() {
		return employeephone;
	}
	public void setEmployeephone(String employeephone) {
		this.employeephone = employeephone;
	}
	public String getAccessTime() {
		return accessTime;
	}
	public void setAccessTime(String accessTime) {
		this.accessTime = accessTime;
	}
	public boolean isEmployeeLevel() {
		return employeeLevel;
	}
	public void setEmployeeLevel(boolean employeeLevel) {
		this.employeeLevel = employeeLevel;
	}
	public String getStoreCode() {
		return storeCode;
	}
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	public int getAccessState() {
		return accessState;
	}
	public void setAccessState(int accessState) {
		this.accessState = accessState;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public boolean isResult() {
		return result;
	}
	public void setResult(boolean result) {
		this.result = result;
	}
	
}
