/**
 * 
 */
package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.domain.LeaveProcessCode;

/**
 * @author seanguo
 *
 */
public interface LeaveProcessCodeMapper {

	void save(LeaveProcessCode process);

	List<LeaveProcessCode> findAll();

}
