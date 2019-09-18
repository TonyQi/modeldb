package com.bcc.security.admin.entity;

import java.util.Date;
import javax.persistence.*;

@Table(name = "file_item_info")
public class FileItem {
    @Id
    private String id;
    
    

	@Column(name = "file_info_id")
    private String fileInfoId;
    
    @Column(name = "file_name")
    private String fileName;
    
    @Column(name = "stroage_url")
    private String storageUrl;


    public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
    
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getStorageUrl() {
		return storageUrl;
	}

	public void setStorageUrl(String storageUrl) {
		this.storageUrl = storageUrl;
	}

	public String getFileInfoId() {
		return fileInfoId;
	}

	public void setFileInfoId(String fileInfoId) {
		this.fileInfoId = fileInfoId;
	}

	
    
    
	
}