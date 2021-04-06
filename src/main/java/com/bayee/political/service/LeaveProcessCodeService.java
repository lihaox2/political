/**
 * 
 */
package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessCode;

/**
 * @author seanguo
 *
 */
@Service
public interface LeaveProcessCodeService {

	void save(LeaveProcessCode process);

	List<LeaveProcessCode> findAll();
}
