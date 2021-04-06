package com.bayee.political.service.impl;
/** 
* @author  shentuqiwei 
* @version 2020年5月22日 下午1:38:18 
*/

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.User;
import com.bayee.political.mapper.UserMapper;
import com.bayee.political.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	UserMapper userMapper;

	// 系统登录
	@Override
	public User login(String policeId, String phone, String password) {
		return userMapper.login(policeId, phone, password);
	}

	// 注册、修改密码验证
	@Override
	public User userCheck(String policeId, String name, String idCard) {
		return userMapper.userCheck(policeId, name, idCard);
	}

}
