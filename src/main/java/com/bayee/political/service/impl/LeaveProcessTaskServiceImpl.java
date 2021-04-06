/**
 * 
 */
package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessTask;
import com.bayee.political.mapper.LeaveProcessTaskMapper;
import com.bayee.political.service.LeaveProcessTaskService;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveProcessTaskServiceImpl implements LeaveProcessTaskService {
	
	@Autowired
	private LeaveProcessTaskMapper mapper;

	@Override
	public void deleteByProcessId(long processId) {
		mapper.deleteByProcessId(processId);
	}

	@Override
	public void save(LeaveProcessTask processTask) {
		mapper.save(processTask);
	}

}
