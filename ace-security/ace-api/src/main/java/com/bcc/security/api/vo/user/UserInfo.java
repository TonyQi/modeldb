package com.bcc.security.api.vo.user;

import java.io.Serializable;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-21 8:12
 */
@Data
public class UserInfo implements Serializable{
	private static final long serialVersionUID = -8175097928216609824L;
	public String id;
    public String username;
    public String password;
    public String name;
    private String description;
}
