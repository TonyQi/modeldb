package com.bcc.security.admin.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name="da_aims")
public class DaAims {
	@Id
	private String id;
	@Column(name="aims_name")
	private String aimsName;
	@Column(name="cre_date")
	private Date creDate;
	public Date getCreDate() {
		return creDate;
	}
	public void setCreDate(Date creDate) {
		this.creDate = creDate;
	}
	@Column(name="note")
	private String note;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAimsName() {
		return aimsName;
	}
	public void setAimsName(String aimsName) {
		this.aimsName = aimsName;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
}
