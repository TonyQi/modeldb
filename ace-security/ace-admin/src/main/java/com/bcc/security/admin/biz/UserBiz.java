package com.bcc.security.admin.biz;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.ace.cache.annotation.Cache;
import com.ace.cache.annotation.CacheClear;
import com.bcc.security.admin.entity.User;
import com.bcc.security.admin.mapper.UserMapper;
import com.bcc.security.common.biz.BaseBiz;
import com.bcc.security.common.constant.UserConstant;

/**
 * ${DESCRIPTION}
 *
 * @author tj
 * @create 2017-06-08 16:23
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserBiz extends BaseBiz<UserMapper, User> {

	@Override
	public void insertSelective(User entity) {
		String password = new BCryptPasswordEncoder(UserConstant.PW_ENCORDER_SALT).encode(entity.getPassword());
		entity.setPassword(password);
		super.insertSelective(entity);
	}

	@Override
	@CacheClear(pre = "user{1.username}")
	public void updateSelectiveById(User entity) {
		super.updateSelectiveById(entity);
	}

	/**
	 * 根据用户名获取用户信息
	 * 
	 * @param username
	 * @return
	 */
	@Cache(key = "user{1}")
	public User getUserByUsername(String username) {
		User user = new User();
		user.setUsername(username);
		return mapper.selectOne(user);
	}

}
