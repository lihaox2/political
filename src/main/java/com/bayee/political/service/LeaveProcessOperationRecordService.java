/**
 * 
 */
package com.bayee.political.service;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessOperationRecord;

/**
 * @author seanguo
 *
 */
@Service
public interface LeaveProcessOperationRecordService {

	void deleteByProcessId(long processId);

	void save(LeaveProcessOperationRecord operationRecord);

}
