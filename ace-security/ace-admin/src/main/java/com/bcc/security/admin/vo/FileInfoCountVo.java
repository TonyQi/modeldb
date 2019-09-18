package com.bcc.security.admin.vo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.bcc.security.admin.entity.FileItem;

public class FileInfoCountVo {
	    
    public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	private String dataType;
    
    private Integer num;
}
