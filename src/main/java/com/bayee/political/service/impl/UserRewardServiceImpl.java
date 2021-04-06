/**
 * 
 */
package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.UserReward;
import com.bayee.political.mapper.UserRewardMapper;
import com.bayee.political.service.UserRewardService;

/**
 * @author seanguo
 *
 */
@Service
public class UserRewardServiceImpl implements UserRewardService {

	@Autowired
	UserRewardMapper mapper;

	@Override
	public void save(UserReward userReward) {
		mapper.save(userReward);
	}

}
