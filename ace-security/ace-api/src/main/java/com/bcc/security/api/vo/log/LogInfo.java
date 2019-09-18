package com.bcc.security.api.vo.log;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-07-01 11:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogInfo implements Serializable{

	private static final long serialVersionUID = 1446055325198683697L;
	private String menu;
    private String opt;
    private String uri;
    private Date crtTime;
    private String crtUser;
    private String crtName;
    private String crtHost;

}
