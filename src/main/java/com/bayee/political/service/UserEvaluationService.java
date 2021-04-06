/**
 * 
 */
package com.bayee.political.service;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.UserEvaluation;

/**
 * @author seanguo
 *
 */
@Service
public interface UserEvaluationService {

	void save(UserEvaluation userEval);

}
