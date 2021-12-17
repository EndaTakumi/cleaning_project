package jp.co.sss.project.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class LoginFormValidation {
	
	@NotBlank
	@Pattern(regexp = "^[a-z0-9A-Z]+$")
	private String employeeId;
	
	@NotBlank
	@Pattern(regexp = "^[a-z0-9A-Z]+$")
	private String employeePw;
	
	public String getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeePw() {
		return employeePw;
	}
	public void setEmployeePw(String employeePw) {
		this.employeePw = employeePw;
	}
}
