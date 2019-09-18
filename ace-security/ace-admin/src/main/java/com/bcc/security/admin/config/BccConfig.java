package com.bcc.security.admin.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix="bcc")
public class BccConfig {
	private String filelocation;

	public String getFilelocation() {
		return filelocation;
	}

	public void setFilelocation(String filelocation) {
		this.filelocation = filelocation;
	}
	
	
}
