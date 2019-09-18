package com.bcc.security.admin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcc.security.admin.biz.GateLogBiz;
import com.bcc.security.admin.entity.GateLog;
import com.bcc.security.common.rest.BaseController;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-07-01 20:32
 */
@Controller
@RequestMapping("gateLog")
public class GateLogController extends BaseController<GateLogBiz,GateLog> {
}
