package com.bcc.security.admin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "file_info")
public class FileInfo {
    @Id
    private String id;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "data_type")
    private String dataType;
    
    @Column(name="secret")
    private Integer secret;
    
    @Column(name="description")
    private String description;
    
    @Column(name = "upload_time")
    private Date uploadTime;

    @Column(name = "upload_user_id")
    private String uploadUserId;
    
    @Column(name = "upload_user")
    private String uploadUser;
    @Column(name="data_file_uri")
    private String dataFileUri;
    @Column(name="AIMS_ID")
    private String aimsId;
    @Column(name="AIMS_NAME")
    private String aimsName;
	
	public String getDataType() {
		return dataType;
	}
	public void setDataType(String dataType) {
		this.dataType = dataType;
	}
	public Date getUploadTime() {
		return uploadTime;
	}
	public void setUploadTime(Date uploadTime) {
		this.uploadTime = uploadTime;
	}
	public String getUploadUser() {
		return uploadUser;
	}
	public void setUploadUser(String uploadUser) {
		this.uploadUser = uploadUser;
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
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUploadUserId() {
		return uploadUserId;
	}
	public void setUploadUserId(String uploadUserId) {
		this.uploadUserId = uploadUserId;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getDataFileUri() {
		return dataFileUri;
	}
	public void setDataFileUri(String dataFileUri) {
		this.dataFileUri = dataFileUri;
	}
	public String getAimsId() {
		return aimsId;
	}
	public void setAimsId(String aimsId) {
		this.aimsId = aimsId;
	}
	public String getAimsName() {
		return aimsName;
	}
	public void setAimsName(String aimsName) {
		this.aimsName = aimsName;
	}
}