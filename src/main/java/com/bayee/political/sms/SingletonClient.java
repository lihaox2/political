package com.bayee.political.sms;

import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SingletonClient {
	private static Client client=null;
	private SingletonClient(){
	}
	public synchronized static Client getClient(String softwareSerialNo,String key){
		if(client==null){
			try {
				client=new Client(softwareSerialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public synchronized static Client getClient(){
		ResourceBundle bundle=PropertyResourceBundle.getBundle("config");
		if(client==null){
			try {
				String serialNo = bundle.getString("softwareSerialNo");
				String key = bundle.getString("key");
				client=new Client(serialNo,key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	public static void main(String str[]){
		SingletonClient.getClient();
	}
	
	
	
	
	
}
