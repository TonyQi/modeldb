package com.bcc.security.admin.dataparse.model;

public abstract class AbstractTaskData {
	
	protected String dataType;//file data type
	protected String dataCode;//file data code
	protected String dataName;//file data name
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public String getDataCode() {
		return dataCode;
	}
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}
	public String getDataName() {
		return dataName;
	}
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}
	
}
