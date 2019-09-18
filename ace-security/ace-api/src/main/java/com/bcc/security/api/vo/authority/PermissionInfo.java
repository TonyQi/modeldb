package com.bcc.security.api.vo.authority;

import java.io.Serializable;

import lombok.Data;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-22 15:19
 */
@Data
public class PermissionInfo implements Serializable{
	private static final long serialVersionUID = 8298241774592446243L;
	private String code;
    private String type;
    private String uri;
    private String method;
    private String name;
    private String menu;

}
