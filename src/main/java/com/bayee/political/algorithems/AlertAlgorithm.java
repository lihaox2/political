/**
 * 
 */
package com.bayee.political.algorithems;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcess;

/**
 * @author seanguo
 *
 */
@Service
public interface AlertAlgorithm {

	public void match(LeaveProcess leaveProcess);
}
