package jp.co.sss.project.entity;

import java.util.Date;

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
@Table(name = "hist")
public class Hist {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_hist_gen")
	@SequenceGenerator(name = "seq_hist_gen", sequenceName = "seq_hist", allocationSize = 1)
	private Integer histId;
	
	@ManyToOne
	@JoinColumn(name = "customer_id", referencedColumnName = "id")
	private Customer customer;
	
	@Column
	private Date buyDate;
	
	@Column
	private int discount;
	
	@Column
	private int tax;

	public Integer getHistId() {
		return histId;
	}

	public void setHistId(Integer histId) {
		this.histId = histId;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	public int getTax() {
		return tax;
	}

	public void setTax(int tax) {
		this.tax = tax;
	}
	
	

}
