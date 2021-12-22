package jp.co.sss.project.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "goods")
public class Goods {
	@Id
	private Integer goodsId;
	@Column
	private String goodsName;
	@Column
	private String goodsNameKana;
	@Column
	private int price;
	@Column
	private String deleteFlg;
	
	public Integer getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(Integer goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public String getGoodsNameKana() {
		return goodsNameKana;
	}
	public void setGoodsNameKana(String goodsNameKana) {
		this.goodsNameKana = goodsNameKana;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	} 
}
