package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaSubTaskdesc;
import com.bcc.security.admin.dataparse.dataobj.DaSystem;
import com.bcc.security.admin.mapper.DaSubTaskdescMapper;
import com.bcc.security.admin.mapper.DaSystemMapper;
import com.bcc.security.common.biz.BaseBiz;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-06-23 20:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DaSystemBiz {
	@Autowired
	private DaSystemMapper daSystemMapper;
	
	public void insert(DaSystem daSystem) {
		daSystemMapper.insert(daSystem);
	}
	
	public List<DaSystem> selectByTaskId(String taskId){
		return daSystemMapper.selectByTaskId(taskId);
	}
}
