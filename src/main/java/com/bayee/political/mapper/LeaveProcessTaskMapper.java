/**
 * 
 */
package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveProcessTask;

/**
 * @author seanguo
 *
 */
public interface LeaveProcessTaskMapper {

	void deleteByProcessId(@Param("processId")long processId);

	void save(LeaveProcessTask processTask);

}
