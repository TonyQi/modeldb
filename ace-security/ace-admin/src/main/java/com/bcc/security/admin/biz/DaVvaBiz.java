package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaVva;
import com.bcc.security.admin.mapper.DaVvaMapper;
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
public class DaVvaBiz {
	@Autowired
	private DaVvaMapper daVvaMapper;
	
	public void insert(DaVva daVva) {
		daVvaMapper.insert(daVva);
	}
	
	public List<DaVva> selectByTaskId(String taskId){
		return daVvaMapper.selectByTaskId(taskId);
	}
}
