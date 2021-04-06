/**
 * 
 */
package com.bayee.political.controller;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Modifier;
import java.nio.charset.Charset;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.domain.User;
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.taobao.api.ApiException;

/**
 * @author shawnkuo
 *
 */
public class BaseController {

	public final static String SESSION_USER = "user";

	@Value("${AppKey}")
	private String appKey;

	@Value("${AppSecret}")
	private String appSecret;

	private String accessToken;

	protected final HttpHeaders headers;
	protected final Gson gson;

	@Value("${file_container}")
	private String fileContainer;
//	private String fileContainer = "D:\\Develop\\tomcat\\apache-tomcat-8.5.31\\webapps"; 

	public BaseController() {
		gson = new GsonBuilder().excludeFieldsWithModifiers(Modifier.TRANSIENT).create();
		headers = new HttpHeaders();
		MediaType mediaType = new MediaType("text", "json", Charset.forName("UTF-8"));
		headers.setContentType(mediaType);
	}

	public String getRequestURL(HttpServletRequest request) {
		String requestUrl = request.getRequestURL().toString();
		String queryString = request.getQueryString();
		if (!StringUtils.isEmpty(queryString)) {
			requestUrl += "?" + queryString;
		}
		return requestUrl;
	}

	public String getAccessToken() {
		if (accessToken == null) {
			retrieveAccessToken();
		}
		return accessToken;
	}

	private void retrieveAccessToken() {
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

	/**
	 * 设置session
	 * 
	 * @param account
	 * @param request
	 */
	public void setSessionAccount(HttpServletRequest request, User account) {
		request.getSession().setAttribute(BaseController.SESSION_USER, account);
		request.getSession().setMaxInactiveInterval(3 * 60 * 60);
	}

	/**
	 * 获取session
	 * 
	 * @param request
	 * @return
	 */
	public User getSessionAccount(HttpServletRequest request) {
		User account = (User) request.getSession().getAttribute(BaseController.SESSION_USER);
		return account;
	}

	public String getRandomFileName(MultipartFile imageFile) {
		String randomFileName = UUID.randomUUID().toString();
		String originalFileExtension = imageFile.getOriginalFilename()
				.substring(imageFile.getOriginalFilename().lastIndexOf("."));
		return randomFileName + originalFileExtension;
	}

	public void deleteExistFile(String primaryImagePath) {
		File imageFile = new File(fileContainer + File.separator + primaryImagePath);
		if (imageFile.exists()) {
			imageFile.delete();
		}
	}

	public String writeImage(MultipartFile imageFile, String imageDirectory) {
		if (imageFile == null || StringUtils.isEmpty(imageFile.getOriginalFilename())) {
			return null;
		}

		File directory = new File(fileContainer + File.separator + imageDirectory);
		if (!directory.exists()) {
			directory.mkdir();
		}

		String fileName = getRandomFileName(imageFile);
		String fileFullPath = imageDirectory + File.separator + fileName;

		File newFile = new File(fileContainer + File.separator + fileFullPath);

		try {
			imageFile.transferTo(newFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileFullPath;
	}

}
