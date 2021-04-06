/**
 * 
 */
package com.bayee.political.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessOperationRecord;
import com.bayee.political.mapper.LeaveProcessOperationRecordMapper;
import com.bayee.political.service.LeaveProcessOperationRecordService;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveProcessOperationRecordServiceImpl implements LeaveProcessOperationRecordService {
	
	@Autowired
	private LeaveProcessOperationRecordMapper mapper;

	@Override
	public void deleteByProcessId(long processId) {
		mapper.deleteByProcessId(processId);
	}

	@Override
	public void save(LeaveProcessOperationRecord operationRecord) {
		mapper.save(operationRecord);
	}

}
