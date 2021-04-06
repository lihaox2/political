/**
 * 
 */
package com.bayee.political.service;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessTask;

/**
 * @author seanguo
 *
 */
@Service
public interface LeaveProcessTaskService {

	void deleteByProcessId(long processId);

	void save(LeaveProcessTask processTask);

}
