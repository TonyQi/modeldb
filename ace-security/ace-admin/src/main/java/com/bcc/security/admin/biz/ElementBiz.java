package com.bcc.security.admin.biz;

import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.bcc.security.admin.entity.Element;
import com.bcc.security.admin.mapper.ElementMapper;
import com.bcc.security.common.biz.BaseBiz;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-06-23 20:27
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ElementBiz extends BaseBiz<ElementMapper,Element> {
    @Cache(key="permission:ele:u{1}")
    public List<Element> getAuthorityElementByUserId(String userId){
       return mapper.selectAuthorityElementByUserId(userId);
    }
    public List<Element> getAuthorityElementByUserId(String userId,String menuId){
        return mapper.selectAuthorityMenuElementByUserId(userId,menuId);
    }

    @Override
    @Cache(key="permission:ele")
    public List<Element> selectListAll() {
        return super.selectListAll();
    }

    @Override
    @CacheClear(keys={"permission:ele","permission"})
    public void insertSelective(Element entity) {
        super.insertSelective(entity);
    }

    @Override
    @CacheClear(keys={"permission:ele","permission"})
    public void updateSelectiveById(Element entity) {
        super.updateSelectiveById(entity);
    }
}
