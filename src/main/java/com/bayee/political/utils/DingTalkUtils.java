/**
 * 
 */
package com.bayee.political.utils;

import org.springframework.beans.factory.annotation.Value;

import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;

/**
 * @author seanguo
 *
 */
public class DingTalkUtils {

	@Value("${AppKey}")
	private String appKey;
	
	@Value("${AppSecret}")
	private String appSecret;
	
	private String accessToken;
	
	private final static DingTalkUtils instacne = new DingTalkUtils();

	public static DingTalkUtils getInstance() {
		return instacne;
	}
	
	public void retrieveAccessToken() {
		try {
			DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
			OapiGettokenRequest request = new OapiGettokenRequest();
			request.setAppkey(appKey);
			request.setAppsecret(appSecret);
			request.setHttpMethod("GET");
			OapiGettokenResponse response = client.execute(request);
			accessToken = response.getAccessToken();
		} catch (ApiException e) {
			e.printStackTrace();
		}
	}
	
	public String getAccessToken() {
		if(accessToken == null) {
			retrieveAccessToken();
		}
		return accessToken;
	}
}
