package com.bayee.political.controller;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.bayee.political.domain.User;
import com.bayee.political.service.AccountService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.google.gson.Gson;

@Controller
public class AccountController extends BaseController {

	@Autowired
	AccountService accountService;

	@Autowired
	private UserService userService;

	private static Gson gson;

	static {
		gson = new Gson();
	}

	// 用户登录
	@RequestMapping(value = { "/user/login" }, method = RequestMethod.POST)
	public ResponseEntity<?> login(@RequestParam(value = "policeId", required = false) String policeId,
			@RequestParam(value = "phone", required = false) String phone,
			@RequestParam(value = "password", required = true) String password) {
		DataListReturn dlr = new DataListReturn();
		policeId = phone;
		User account = accountService.login(policeId, phone, password);
		if (account != null) {
			if (account.getIsDisable() == 1) {
				dlr.setStatus(true);
				dlr.setMessage("账号已被禁用，请联系管理员");
				dlr.setResult(null);
				dlr.setCode(StatusCode.getAccountdisablecode());
			} else {
				User itemUser = new User();
				itemUser.setId(account.getId());
				itemUser.setIsActive(1);
				itemUser.setLastLoginTime(new Date());
				itemUser.setLoginTimes(account.getLoginTimes() + 1);
				itemUser.setUpdateDate(new Date());
				userService.userUpdate(itemUser);
				User account1 = accountService.login(policeId, phone, password);
				dlr.setStatus(true);
				dlr.setMessage("登录成功");
				dlr.setResult(account1);
				dlr.setCode(StatusCode.getSuccesscode());
			}
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		} else {
			dlr.setStatus(false);
			dlr.setMessage("用户名或密码错误");
			dlr.setResult(account);
			dlr.setCode(StatusCode.getFailcode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		}
	}

	// 用户注册/修改密码验证
	@RequestMapping(value = "/user/check", method = RequestMethod.GET)
	public ResponseEntity<?> userCheck(@RequestParam(value = "policeId", required = true) String policeId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "idCard", required = true) String idCard) {
		DataListReturn dlr = new DataListReturn();
		User account = accountService.userCheck(policeId, name, idCard);
		if (account != null) {
			dlr.setStatus(true);
			dlr.setMessage("success");
			dlr.setResult(account);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("验证失败，请检查验证信息或联系管理员");
			dlr.setResult(account);
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 用户注册/修改密码
	@RequestMapping(value = { "/user/register" }, method = RequestMethod.POST)
	public ResponseEntity<?> userRegister(@RequestParam(value = "policeId", required = true) String policeId,
			@RequestParam(value = "name", required = true) String name,
			@RequestParam(value = "idCard", required = true) String idCard,
			@RequestParam(value = "password", required = true) String password) {
		DataListReturn dlr = new DataListReturn();
		User account = accountService.userCheck(policeId, name, idCard);
		User user = new User();
		if (account != null) {
			user.setId(account.getId());
			user.setPassword(password);
			user.setUpdateDate(new Date());
			// 密码修改成功+1
			user.setLoginTimes(account.getLoginTimes() + 1);
			userService.userUpdate(user);
			dlr.setStatus(true);
			dlr.setMessage("密码修改成功");
			dlr.setResult(account);
			dlr.setCode(StatusCode.getSuccesscode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		} else {
			dlr.setStatus(false);
			dlr.setMessage("密码输入不一致");
			dlr.setCode(StatusCode.getFailcode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		}
	}

	// 用户个人中心修改密码
	@RequestMapping(value = { "/user/update/password" }, method = RequestMethod.POST)
	public ResponseEntity<?> userUpdatePassword(@RequestParam(value = "policeId", required = true) String policeId,
			@RequestParam(value = "newPssword", required = false) String newPssword,
			@RequestParam(value = "password", required = true) String password) {
		DataListReturn dlr = new DataListReturn();
		User userItem = userService.policeItem(policeId, password, null);
		User user = new User();
		if (userItem != null) {
			user.setId(userItem.getId());
			user.setPassword(newPssword);
			user.setUpdateDate(new Date());
			// 密码修改成功+1
			user.setLoginTimes(userItem.getLoginTimes() + 1);
			userService.userUpdate(user);
			dlr.setStatus(true);
			dlr.setMessage("密码重置成功");
			dlr.setCode(StatusCode.getSuccesscode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		} else {
			dlr.setStatus(false);
			dlr.setMessage("旧密码输入错误");
			dlr.setCode(StatusCode.getFailcode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		}
	}

	// **************************** 调皮的分割线***********************后台接口

	@RequestMapping(value = { "/" })
	public ModelAndView index(Model model) {
		return new ModelAndView("redirect:/account/login");
	}

	/**
	 * 登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = { "/account/login" })
	public ModelAndView login(Model model) {
		return new ModelAndView("views/login");
	}

	// 系统登录
	@RequestMapping(value = { "/account/login/submit" })
	public @ResponseBody byte[] accountLogin(String policeId, String phone, String password,
			RedirectAttributes attributes, HttpServletRequest request) throws UnsupportedEncodingException {
		policeId = phone;
		User account = accountService.login(policeId, phone, password);

		DataListReturn dlr = new DataListReturn();

		if (null != account) {
			setSessionAccount(request, account);
			dlr.setCode(200);
			dlr.setMessage("message");
			dlr.setResult(account);
			return gson.toJson(dlr).getBytes("utf-8");
		} else {
			dlr.setCode(100);
			dlr.setMessage("fail");
			attributes.addFlashAttribute("code", 201);
		}
		return gson.toJson(dlr).getBytes("utf-8");
	}

	/**
	 * 注册界面
	 * 
	 * @param model
	 * @return
	 */
//	@RequestMapping(value = { "/account/registered" })
//	public ModelAndView registered(Model model) {
//		ModelAndView mv = new ModelAndView("views/registered");// kpis final
//		List<Department> departments = screeningService.findDepartmentAll();
//		List<PoliceStation> policeStations = screeningService.findPoliceStationAll();
//		mv.addObject("departments", departments);
//		mv.addObject("policeStations", policeStations);
//		return mv;
//	}

	/**
	 * 忘记密码
	 * 
	 * @return
	 */
	@RequestMapping(value = { "/account/forget" })
	public ModelAndView fogetPassWord(Model model) {
		ModelAndView mv = new ModelAndView("views/forget-password");
		return mv;

	}

	/**
	 * 跳转修改密码
	 */
	@RequestMapping(value = "/account/updatePath")
	public ModelAndView updatePath(String id) {

		ModelAndView modelAndView = new ModelAndView("views/update-password");

		modelAndView.addObject("id", id);

		return modelAndView;

	};

//	@RequestMapping(value = { "/account/registered/submit" })
//	public ModelAndView registeredSubmit(Model model, String user, String polCard, String password, String rePassword,
//			String nickName, String email, Integer departmentId, Integer policeStationId, RedirectAttributes attributes,
//			HttpServletRequest request) {
//		ModelAndView mv = new ModelAndView("redirect:/account/registered");// kpis final
//		Account account = accountService.findByUser(user);
//		User userItem = accountService.userItem(null,polCard, nickName);// 查询警员详情
//		if (userItem == null) {
//			attributes.addFlashAttribute("code", 203);
//			return mv;
//		}
//		if (null == account) {
//			account = new Account();
//			account.setName(nickName);
//			account.setPhone(user);
//			account.setPolCard(polCard);
//			account.setPassword(password);
//			account.setEmail(email);
//			account.setDepartmentId(departmentId);
//			account.setPoliceStationId(policeStationId);
//			if (userItem != null) {
//				account.setPower(userItem.getPower());
//			}
//			account.setPoliceStationId(policeStationId);
//			account.setCreationDate(new Date());
//			accountService.save(account);
//			setSessionAccount(request, account);
//			return new ModelAndView("redirect:/final");
//		} else {
//			attributes.addFlashAttribute("user", user);
//			attributes.addFlashAttribute("nickName", nickName);
//			attributes.addFlashAttribute("email", email);
//			attributes.addFlashAttribute("departmentId", departmentId);
//			attributes.addFlashAttribute("policeStationId", policeStationId);
//			attributes.addFlashAttribute("code", 202);
//		}
//		return mv;
//	}

	/**
	 * 退出系统
	 * 
	 * @param model
	 * @param request
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(value = { "/account/logout" })
	public @ResponseBody byte[] logout(HttpServletRequest request) throws UnsupportedEncodingException {
		request.getSession().invalidate();
		return gson.toJson("ok").getBytes("utf-8");
	}

}
