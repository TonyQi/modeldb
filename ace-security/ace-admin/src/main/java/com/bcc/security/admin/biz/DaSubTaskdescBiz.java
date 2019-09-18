package com.bcc.security.admin.biz;

import com.bcc.security.admin.dataparse.dataobj.DaData;
import com.bcc.security.admin.dataparse.dataobj.DaSubTaskdesc;
import com.bcc.security.admin.mapper.DaSubTaskdescMapper;

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
public class DaSubTaskdescBiz {
	@Autowired
	private DaSubTaskdescMapper daSubTaskdescMapper;
	
	public void insert(DaSubTaskdesc daSubTaskdesc) {
		daSubTaskdescMapper.insert(daSubTaskdesc);
	}
	
	public List<DaSubTaskdesc> selectByTaskId(String taskId){
		return daSubTaskdescMapper.selectByTaskId(taskId);
	}
}
