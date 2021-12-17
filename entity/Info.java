package jp.co.sss.project.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "info")
public class Info {
	@Id
	private Integer infoId;
	@Column
	private String subject;
	@Column
	private String contents;
	@Column
	private Date keisaiDate;
	@Column
	private Date keisaiFrom;
	@Column
	private Date keisaiTo;
	@Column
	private String deleteFlg;
	
	public Integer getInfoId() {
		return infoId;
	}
	public void setInfoId(Integer infoId) {
		this.infoId = infoId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	public Date getKeisaiDate() {
		return keisaiDate;
	}
	public void setKeisaiDate(Date keisaiDate) {
		this.keisaiDate = keisaiDate;
	}
	public Date getKeisaiFrom() {
		return keisaiFrom;
	}
	public void setKeisaiFrom(Date keisaiFrom) {
		this.keisaiFrom = keisaiFrom;
	}
	public Date getKeisaiTo() {
		return keisaiTo;
	}
	public void setKeisaiTo(Date keisaiTo) {
		this.keisaiTo = keisaiTo;
	}
	public String getDeleteFlg() {
		return deleteFlg;
	}
	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}
}
