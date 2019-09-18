package com.bcc.security.admin.biz;

import com.ace.cache.annotation.Cache;
import com.bcc.security.admin.entity.DaAims;
import com.bcc.security.admin.entity.FileInfo;
import com.bcc.security.admin.entity.FileItem;
import com.bcc.security.admin.mapper.DaAimsMapper;
import com.bcc.security.admin.mapper.FileItemMapper;
import com.bcc.security.common.biz.BaseBiz;
import com.bcc.security.common.msg.TableResultResponse;
import com.bcc.security.common.util.Query;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import tk.mybatis.mapper.entity.Example;

import java.lang.reflect.ParameterizedType;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-07-01 14:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DaAimsBiz extends BaseBiz<DaAimsMapper,DaAims> {

	@Override
	public void insert(DaAims entity) {
		// TODO Auto-generated method stub
		entity.setId(UUID.randomUUID().toString());
		entity.setCreDate(new Date());
		super.insert(entity);
	}

	@Override
	public void insertSelective(DaAims entity) {
		// TODO Auto-generated method stub
		entity.setId(UUID.randomUUID().toString());
		entity.setCreDate(new Date());
		super.insertSelective(entity);
	}
	
	
	public List<DaAims> selectByNameLike(Query query) {
		Example example = new Example(DaAims.class);
        Example.Criteria criteria = example.createCriteria();
        List<DaAims> list = mapper.selectByExample(example);
        String name=(String) query.get("name");
        if (StringUtils.isNotEmpty(name)) {
        	criteria.andLike("aimsName", "%" + name + "%");
		}
        return mapper.selectByExample(example);
    }
	
	public List<DaAims> selectByName(Query query) {
		Example example = new Example(DaAims.class);
        Example.Criteria criteria = example.createCriteria();
        List<DaAims> list = mapper.selectByExample(example);
        String name=(String) query.get("name");
        if (StringUtils.isNotEmpty(name)) {
        	criteria.andEqualTo("aimsName", name);
		}
        return mapper.selectByExample(example);
    }
	
	
}
