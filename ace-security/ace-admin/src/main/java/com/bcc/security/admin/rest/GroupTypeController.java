package com.bcc.security.admin.rest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bcc.security.admin.biz.GroupTypeBiz;
import com.bcc.security.admin.entity.GroupType;
import com.bcc.security.common.rest.BaseController;

/**
 * ${DESCRIPTION}
 *
 * @author wanghaobin
 * @create 2017-06-08 11:51
 */
@Controller
@RequestMapping("groupType")
public class GroupTypeController extends BaseController<GroupTypeBiz,GroupType> {

}
