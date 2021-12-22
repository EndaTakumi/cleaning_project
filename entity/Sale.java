package jp.co.sss.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sale")
public class Sale {
	@Id
	private Integer saleId;
	
	@Column
	private String saleName;
	
	@Column
	private int discount;
	
	@Column
	private Date saleFrom;
	
	@Column
	private Date saleTo;
	
	@Column
	private String deleteFlg;

	public Integer getSaleId() {
		return saleId;
	}

	public void setSaleId(Integer saleId) {
		this.saleId = saleId;
	}

	public String getSaleName() {
		return saleName;
	}

	public void setSaleName(String saleName) {
		this.saleName = saleName;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public Date getSaleFrom() {
		return saleFrom;
	}

	public void setSaleFrom(Date saleFrom) {
		this.saleFrom = saleFrom;
	}

	public Date getSaleTo() {
		return saleTo;
	}

	public void setSaleTo(Date saleTo) {
		this.saleTo = saleTo;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
	
}
