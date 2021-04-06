/**
 * 
 */
package com.bayee.political.algorithems;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.LeaveProcess;
import com.bayee.political.domain.User;
import com.bayee.political.service.UserService;
import com.bayee.political.sms.MessageService;

/**
 * @author seanguo
 *
 */
@Service
public class LeaveAlertAlgorithm implements AlertAlgorithm {
	
	@Autowired
	UserService userService;

	@Override
	public void match(LeaveProcess leaveProcess) {
		if(leaveProcess == null) {
			return;
		}
		User user = userService.findByDDUserId(leaveProcess.getUserId());
		if(user != null) {
			if(leaveProcess.getLeaveType().equals("事假")) {
				String message = "";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
				MessageService.sendAlertMessage(user.getPhone(), "【QTFJ】"+"您还有未使用完的年假，请优先使用年假，谢谢。");
			} else if(leaveProcess.getLeaveType().equals("病假")) {
				String message = "【QTFJ】"+user.getName()+"申请了事假，请及时了解"+user.getName()+"家中是否有变故和突发情况发生";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
			} else if(leaveProcess.getLeaveType().equals("婚假")) {
				String message = "【QTFJ】"+user.getName()+"申请了婚假，婚后请及时开展家访，掌握新家属联系方式，开展相关警示教育工作，督查家属协助单位做好民警八小时以外的管理";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
			} else if(leaveProcess.getLeaveType().equals("丧假")) {
				String message = "【QTFJ】"+user.getName()+ "申请了丧假，请及时进行谈心谈话及关心慰问。要掌握请丧假民警的心理变化。";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
			} else if(leaveProcess.getLeaveType().equals("生育假")) {
				String message = "【QTFJ】"+user.getName()+ "申请了丧假，请及时进行谈心谈话及关心慰问。要掌握请丧假民警的心理变化。请在工作上给予适当的便利。";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
			} else if(leaveProcess.getLeaveType().equals("工伤假")) {
				String message = "【QTFJ】"+user.getName()+ "申请了工伤假，请及时开展相关维权慰问等工作。";
				MessageService.sendAlertMessage("13575780303", message);
				MessageService.sendAlertMessage("13666691051", message);
				MessageService.sendAlertMessage("13958113431", message);
				MessageService.sendAlertMessage("13456970607", message);
			}
		}
	}

}
