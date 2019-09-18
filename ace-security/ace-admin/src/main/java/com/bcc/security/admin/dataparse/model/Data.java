package com.bcc.security.admin.dataparse.model;

public class Data {
	
	private String dataType;//0:数据块(默认)	   1:BMP     2:SEQ
	private String dataContent;
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataContent() {
		return dataContent;
	}
	public void setDataContent(String dataContent) {
		this.dataContent = dataContent;
	}
	
}
