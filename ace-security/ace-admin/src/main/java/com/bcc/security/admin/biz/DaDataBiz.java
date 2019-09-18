package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaData;
import com.bcc.security.admin.mapper.DaDataMapper;
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
public class DaDataBiz {
	@Autowired
	private DaDataMapper daDataMapper;
	public void insert(DaData daData) {
		daDataMapper.insert(daData);
	}
	
	public List<DaData> selectByTaskId(String taskId){
		return daDataMapper.selectByTaskId(taskId);
	}
	
}
