package com.bcc.security.admin.biz;

import org.springframework.stereotype.Service;

import com.bcc.security.admin.entity.GroupType;
import com.bcc.security.admin.mapper.GroupTypeMapper;
import com.bcc.security.common.biz.BaseBiz;

import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-06-12 8:48
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class GroupTypeBiz extends BaseBiz<GroupTypeMapper,GroupType> {
}
