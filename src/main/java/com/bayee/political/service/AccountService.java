package com.bayee.political.service;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.User;

/**
 * @author shentuqiwei
 * @version 2020年5月22日 下午1:37:52
 */
@Service
public interface AccountService {

	// 系统登录
	User login(String policeId, String phone, String password);

	// 注册、修改密码验证
	User userCheck(String policeId, String name, String idCard);

}
