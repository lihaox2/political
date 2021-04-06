/**
 * 
 */
package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveProcessOperationRecord;

/**
 * @author seanguo
 *
 */
public interface LeaveProcessOperationRecordMapper {

	void deleteByProcessId(@Param("processId")long processId);

	void save(LeaveProcessOperationRecord operationRecord);

}
