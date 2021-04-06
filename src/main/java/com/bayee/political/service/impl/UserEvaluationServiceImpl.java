/**
 * 
 */
package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.UserEvaluation;
import com.bayee.political.mapper.UserEvaluationMapper;
import com.bayee.political.service.UserEvaluationService;

/**
 * @author seanguo
 *
 */
@Service
public class UserEvaluationServiceImpl implements UserEvaluationService {
	
	@Autowired
	UserEvaluationMapper mapper;

	@Override
	public void save(UserEvaluation userEval) {
		mapper.save(userEval);
	}

}
