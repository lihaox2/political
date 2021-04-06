/**
 * 
 */
package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcessCode;
import com.bayee.political.mapper.LeaveProcessCodeMapper;
import com.bayee.political.service.LeaveProcessCodeService;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveProcessCodeServiceImpl implements LeaveProcessCodeService {
	
	@Autowired
	LeaveProcessCodeMapper mapper;

	@Override
	public void save(LeaveProcessCode process) {
		mapper.save(process);
	}

	@Override
	public List<LeaveProcessCode> findAll() {
		return mapper.findAll();
	}

}
