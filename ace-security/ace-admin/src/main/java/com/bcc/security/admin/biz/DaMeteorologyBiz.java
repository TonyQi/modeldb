package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaMeteorology;
import com.bcc.security.admin.mapper.DaMeteorologyMapper;

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
public class DaMeteorologyBiz {
	@Autowired
	private DaMeteorologyMapper daMeteorologyMapper;
	public void insert(DaMeteorology daMeteorology) {
		daMeteorologyMapper.insert(daMeteorology);
	}
	
	public List<DaMeteorology> selectByTaskId(String taskId){
		return daMeteorologyMapper.selectByTaskId(taskId);
	}
}
