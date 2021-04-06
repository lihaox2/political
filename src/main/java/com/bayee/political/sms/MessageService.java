/**
 * 
 */
package com.bayee.political.sms;

import java.rmi.RemoteException;

/**
 * @author shawnkuo
 *
 */
public class MessageService {

	public static void sendAlertMessage(String userPhone, String message) {
		try {
			int code = SingletonClient.getClient().sendSMS(
					new String[] { userPhone },
							"【QTFJ】" + message,
							"", 5);
			System.out.println(code);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
	}
	
	public  static void main(String [] args) {
//		int status = -99999;
//		try {
//			status = SingletonClient.getClient().registEx("634112");
//		} catch (RemoteException e) {
//			e.printStackTrace();
//		}
//		System.out.println(status);
		MessageService.sendAlertMessage("13641689665", "【QTFJ】李明申请了申请了工伤假，请及时开展相关维权慰问等工作。");
	}
}
