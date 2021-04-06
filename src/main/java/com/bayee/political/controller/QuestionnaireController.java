package com.bayee.political.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.controller.BaseController;
import com.bayee.political.domain.Questionnaire;
import com.bayee.political.domain.QuestionnaireAnswer;
import com.bayee.political.domain.QuestionnaireOption;
import com.bayee.political.domain.User;
import com.bayee.political.service.QuestionnaireService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;

/**
 * 问卷调查
 * 
 * @author shentuqiwei
 * @version 2020年6月8日 下午2:52:03
 */
@Controller
public class QuestionnaireController extends BaseController {

	@Autowired
	private QuestionnaireService questionnaireService;

	@Autowired
	private UserService userService;

	// 问卷调查查询(api)
	@RequestMapping(value = "/questionnaire/list", method = RequestMethod.GET)
	public ResponseEntity<?> questionnaireList() {
		DataListReturn dlr = new DataListReturn();
		// 问卷查询(api)
		List<Questionnaire> questionnaireList = questionnaireService.questionnaireApiList();
		for (Questionnaire x : questionnaireList) {
			// 问卷答案查询(api)
			List<QuestionnaireOption> questionnaireOptionList = questionnaireService.questionnaireOptionList(x.getId());
			x.setQuestionnaireOptionList(questionnaireOptionList);
		}
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(questionnaireList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 问卷提交
	@RequestMapping(value = { "/questionnaire/creat" }, method = RequestMethod.POST)
	public ResponseEntity<?> questionnaireCreat(
			@RequestParam(value = "questionnaireJson", required = false) String questionnaireJson) {
		DataListReturn dlr = new DataListReturn();
		QuestionnaireAnswer answer = new QuestionnaireAnswer();
//		String ss = "[{'questionnaireId':1,'optionId':1,'policeId':'330122'},"
//				+ "{'questionnaireId':2,'optionId':6,'policeId':'330122'},"
//				+ "{'questionnaireId':2,'optionId':7,'policeId':'330122'},"
//				+ "{'questionnaireId':3,'optionId':23,'policeId':'330122'},"
//				+ "{'questionnaireId':3,'optionId':24,'policeId':'330122'},"
//				+ "{'questionnaireId':3,'optionId':25,'policeId':'330122'},"
//				+ "{'questionnaireId':3,'optionId':26,'policeId':'330122'},"
//				+ "{'questionnaireId':4,'optionId':37,'policeId':'330122'},"
//				+ "{'questionnaireId':5,'optionId':55,'policeId':'330122'},"
//				+ "{'questionnaireId':5,'optionId':56,'policeId':'330122'},"
//				+ "{'questionnaireId':5,'optionId':57,'policeId':'330122'},"
//				+ "{'questionnaireId':6,'optionId':66,'policeId':'330122'},"
//				+ "{'questionnaireId':6,'optionId':67,'policeId':'330122'}]";
		JSONArray json = (JSONArray) JSONArray.parse(questionnaireJson);
		String policeId = null;
		for (Object obj : json) {
			JSONObject jo = (JSONObject) obj;
			answer.setQuestionnaireId(jo.getInteger("questionnaireId"));
			answer.setOptionId(jo.getInteger("optionId"));
			answer.setPoliceId(jo.getString("policeId"));
			answer.setCreationDate(new Date());
			policeId = jo.getString("policeId");
			questionnaireService.questionnaireCreat(answer);
		}
		Random ran = new Random();
		HashSet<Integer> hs = new HashSet<Integer>();
		for (;;) {
			int tmp = ran.nextInt(6) + 1;
			hs.add(tmp);
			if (hs.size() == 3)
				break;
		}
		List<Integer> list = new ArrayList<Integer>(hs);
		List<String> strList = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i) == 1) {// 问题1
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 1);
				strList.add(answerItemList.get(0).getOptionLabel());
			} else if (list.get(i) == 2) {// 问题2
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 2);
				if (answerItemList.size() == 1) {
					strList.add(answerItemList.get(0).getOptionLabel());
				} else if (answerItemList.size() >= 3) {
					strList.add("适应能力强");
				} else if (answerItemList.size() == 2) {
					strList.add("兢兢业业");
				}
			} else if (list.get(i) == 3) {// 问题3
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 3);
				if (answerItemList.size() == 1) {
					strList.add(answerItemList.get(0).getOptionLabel());
				} else if (answerItemList.size() >= 2 && answerItemList.size() <= 5) {
					strList.add("才华横溢");
				} else if (answerItemList.size() > 5 && answerItemList.size() < 8) {
					strList.add("才气冲天");
				} else if (answerItemList.size() >= 8) {
					strList.add("能力强到逆天");
				}
			} else if (list.get(i) == 4) {// 问题4
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 4);
				if (answerItemList.size() == 1) {
					strList.add(answerItemList.get(0).getOptionLabel());
				} else if (answerItemList.size() >= 2 && answerItemList.size() <= 5) {
					strList.add("充满热情");
				} else if (answerItemList.size() > 5 && answerItemList.size() <= 8) {
					strList.add("每天活力满格");
				} else if (answerItemList.size() > 8) {
					strList.add("生活一天一个样");
				}
			} else if (list.get(i) == 5) {// 问题5
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 5);
				if (answerItemList.size() == 1) {
					strList.add(answerItemList.get(0).getOptionLabel());
				} else if (answerItemList.size() >= 2) {
					strList.add("理财思想超前");
				}
			} else if (list.get(i) == 6) {// 问题6
				List<QuestionnaireAnswer> answerItemList = questionnaireService.answerItemList(policeId, 6);
				if (answerItemList.size() == 1) {
					strList.add(answerItemList.get(0).getOptionLabel());
				} else if (answerItemList.size() >= 2 && answerItemList.size() <= 5) {
					strList.add("乐趣多样化");
				} else if (answerItemList.size() > 5 && answerItemList.size() <= 8) {
					strList.add("懂得享受生活");
				} else if (answerItemList.size() > 8) {
					strList.add("一生不羁爱折腾");
				}
			}
		}
		String str = StringUtils.join(strList, ",");
		User userItem = userService.policeItem(policeId, null, null);
		User user = new User();
		user.setId(userItem.getId());
		user.setPoliceLabel(str);
		user.setUpdateDate(new Date());
		int count = userService.userUpdate(user);
		if (count > 0) {
			dlr.setStatus(true);
			dlr.setMessage("提交成功");
			dlr.setResult(str);
			dlr.setCode(StatusCode.getSuccesscode());
		} else {
			dlr.setStatus(false);
			dlr.setMessage("提交失败");
			dlr.setCode(StatusCode.getFailcode());
		}
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}
}
