package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaConditions;
import com.bcc.security.admin.mapper.DaConditionsMapper;
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
public class DaConditionsBiz {
	@Autowired
	private DaConditionsMapper daConditionsMapper;
	
	public void insert(DaConditions daConditions) {
		daConditionsMapper.insert(daConditions);
	};
	
	public List<DaConditions> selectByTaskId(String taskId){
		return daConditionsMapper.selectByTaskId(taskId);
	}
}
