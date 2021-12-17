package jp.co.sss.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "customer")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_customer_gen")
	@SequenceGenerator(name = "seq_customer_gen", sequenceName = "seq_customer", allocationSize = 1)
	private Integer customerId;
	
	@Column
	private String customerNameSei;
	
	@Column
	private String customerNameMei;
	
	@Column
	private String customerNameSeiKana;
	
	@Column
	private String customerNameMeiKana;
	
	@Column
	private String tellNo;
	
	@Column
	private String postcode_1;
	
	@Column
	private String postcode_2;
	
	@Column
	private String address;
	
	@Column
	private String deleteFlg;
	
	/*ゲッターとセッター*/
	public Integer getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	public String getCustomerNameSei() {
		return customerNameSei;
	}
	public void setCustomerNameSei(String customerNameSei) {
		this.customerNameSei = customerNameSei;
	}
	public String getCustomerNameMei() {
		return customerNameMei;
	}
	public void setCustomerNameMei(String customerNameMei) {
		this.customerNameMei = customerNameMei;
	}
	public String getCustomerNameSeiKana() {
		return customerNameSeiKana;
	}
	public void setCustomerNameSeiKana(String customerNameSeiKana) {
		this.customerNameSeiKana = customerNameSeiKana;
	}
	public String getCustomerNameMeiKana() {
		return customerNameMeiKana;
	}
	public void setCustomerNameMeiKana(String customerNameMeiKana) {
		this.customerNameMeiKana = customerNameMeiKana;
	}
	public String getTellNo() {
		return tellNo;
	}
	public void setTellNo(String tellNo) {
		this.tellNo = tellNo;
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
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
