package com.bcc.security.admin.vo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.bcc.security.admin.entity.FileItem;

public class FileInfoVo {
	
	private String id;
	    
    private String dataType;
    
    private Integer secret;
    
    private String description;
    
    private Date uploadTime;

    private String uploadUserId;
    
    private String uploadUser;

    private String fileName;
    
    public String getAimsName() {
		return aimsName;
	}

	public void setAimsName(String aimsName) {
		this.aimsName = aimsName;
	}

	private String aimsName;
    

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private List<FileItem> items;
    
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getSecret() {
		return secret;
	}

	public void setSecret(Integer secret) {
		this.secret = secret;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}

	public String getUploadUserId() {
		return uploadUserId;
	}

	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}

	public String getUploadUser() {
		return uploadUser;
	}

	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
	}

	public List<FileItem> getItems() {
		return items==null?new LinkedList<FileItem>():items;
	}

	public void setItems(List<FileItem> items) {
		this.items = items;
	}
}
