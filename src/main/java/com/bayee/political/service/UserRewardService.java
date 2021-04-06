/**
 * 
 */
package com.bayee.political.service;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.UserReward;

/**
 * @author seanguo
 *
 */
@Service
public interface UserRewardService {

	void save(UserReward userReward);

}
