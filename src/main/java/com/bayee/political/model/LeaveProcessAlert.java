/**
 * 
 */
package com.bayee.political.model;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.bayee.political.algorithems.AlertAlgorithm;
import com.bayee.political.algorithems.LeaveAlertAlgorithm;
import com.bayee.political.domain.LeaveProcess;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveProcessAlert {

	private List<AlertAlgorithm> algorithems = null;
	
	@Autowired
	private LeaveAlertAlgorithm leaveAlertAlgorithm;
	
	
	@Async
	public void process(LeaveProcess leaveProcess) {
//		algorithems = new ArrayList<AlertAlgorithm> ();
//		algorithems.add(leaveAlertAlgorithm);
		
//		for(AlertAlgorithm algorithem : algorithems) {
		leaveAlertAlgorithm.match(leaveProcess);
//		}
	}
}
