package com.bcc.security.admin.config.druid;

import javax.servlet.annotation.WebServlet;

import com.alibaba.druid.support.http.StatViewServlet;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-20 21:34
 */
@SuppressWarnings("serial")
@WebServlet(urlPatterns = "/druid/*")
public class DruidStatViewServlet extends StatViewServlet {


}
