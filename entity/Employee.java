package jp.co.sss.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_employee_gen")
	@SequenceGenerator(name = "seq_employee_gen", sequenceName = "seq_employee", allocationSize = 1)
	private Integer employeeId;
	
	@Column
	private String employeePw;
	
	@Column
	private String employeeNameSei;
	
	@Column
	private String employeeNameMei;
	
	@Column
	private String employeeNameSeiKana;
	
	@Column
	private String employeeNameMeiKana;
	
	@Column
	private String telNo;
	
	@Column
	private String postcode_1;
	
	@Column
	private String postcode_2;
	
	@Column
	private String address;
	
	@ManyToOne
	@JoinColumn(name = "authority_id", referencedColumnName = "id")
	private Authority authority;
	
	@Column
	private String deleteFlg;
	
	/*ゲッターとセッター*/
	public Integer getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Integer employeeId) {
		this.employeeId = employeeId;
	}
	public String getEmployeePw() {
		return employeePw;
	}
	public void setEmployeePw(String employeePw) {
		this.employeePw = employeePw;
	}
	public String getEmployeeNameSei() {
		return employeeNameSei;
	}
	public void setEmployeeNameSei(String employeeNameSei) {
		this.employeeNameSei = employeeNameSei;
	}
	public String getEmployeeNameMei() {
		return employeeNameMei;
	}
	public void setEmployeeNameMei(String employeeNameMei) {
		this.employeeNameMei = employeeNameMei;
	}
	public String getEmployeeNameSeiKana() {
		return employeeNameSeiKana;
	}
	public void setEmployeeNameSeiKana(String employeeNameSeiKana) {
		this.employeeNameSeiKana = employeeNameSeiKana;
	}
	public String getEmployeeNameMeiKana() {
		return employeeNameMeiKana;
	}
	public void setEmployeeNameMeiKana(String employeeNameMeiKana) {
		this.employeeNameMeiKana = employeeNameMeiKana;
	}
	
	public String getTelNo() {
		return telNo;
	}
	public void setTelNo(String telNo) {
		this.telNo = telNo;
	}
	
	public String getPostcode_1() {
		return postcode_1;
	}
	public void setPostcode1(String postcode_1) {
		this.postcode_1 = postcode_1;
	}
	public String getPostcode_2() {
		return postcode_2;
	}
	public void setPostcode_2(String postcode_2) {
		this.postcode_2 = postcode_2;
	}
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public Authority getAuthority() {
		return authority;
	}
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}
	
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
}
