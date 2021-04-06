package com.bayee.political.controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.Feedback;
import com.bayee.political.domain.FeedbackType;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.service.FeedbackService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;

import cn.hutool.core.date.DateUtil;

/**
 * 意见反馈
 * 
 * @author shentuqiwei
 * @version 2020年6月5日 下午1:09:35
 */
@Controller
public class FeedbackController extends BaseController {

	@Autowired
	private FeedbackService feedbackService;

	@Value("${feedback_images}")
	private String imageDirectory; // = "uploads";

	@Autowired
	DepartmentService departmentService;

	private String createItemImages(MultipartFile[] itemImages) {
		String[] itemImageFileArr = new String[itemImages.length];
		for (int i = 0; i < itemImages.length; i++) {
			String itemImageFilePath = writeImage(itemImages[i], imageDirectory);
			itemImageFileArr[i] = itemImageFilePath;
		}
		return StringUtils.join(itemImageFileArr, ",");
	}

	// 反馈意见列表查询
	@RequestMapping(value = "/feedback/type/list", method = RequestMethod.GET)
	public ResponseEntity<?> feedbackTypeList() {
		DataListReturn dlr = new DataListReturn();
		List<FeedbackType> feedbackTypeList = feedbackService.feedbackTypeApiList();
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(feedbackTypeList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// 意见反馈新增
	@RequestMapping(value = "/feedback/creat", method = RequestMethod.POST)
	public ResponseEntity<?> feedbackCreat(@RequestParam(name = "policeId", required = true) String policeId,
			@RequestParam(name = "type", required = false) Integer type,
			@RequestParam(name = "opinion", required = false) String opinion,
			@RequestParam(value = "file", required = false) MultipartFile[] file, HttpServletRequest request)
			throws Exception {
		DataListReturn dlr = new DataListReturn();
		Feedback feedback = new Feedback();
		String imagePath = createItemImages(file);
		imagePath = imagePath.replaceAll("\\\\", "/");
		if (!StringUtils.isEmpty(imagePath)) {
			feedback.setImages(imagePath);
		}
		feedback.setPoliceId(policeId);
//		feedback.setType(type);
		feedback.setOpinion(opinion);
		feedback.setCreationDate(new Date());
		try {
			int count = feedbackService.feedbackApiCreat(feedback);
			dlr.setStatus(true);
			dlr.setMessage("反馈成功");
			dlr.setResult(count);
			dlr.setCode(StatusCode.getSuccesscode());
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
			dlr.setStatus(false);
			dlr.setResult(0);
			dlr.setCode(StatusCode.getFailcode());
			dlr.setMessage("反馈失败");
			return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
		}
	}

	// 反馈意见列表查询
	@RequestMapping(value = "/feedback/list", method = RequestMethod.GET)
	public ResponseEntity<?> feedbackList(@RequestParam(name = "policeId", required = true) String policeId) {
		DataListReturn dlr = new DataListReturn();
		List<Feedback> feedbackApiList = feedbackService.feedbackApiList(policeId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(feedbackApiList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}

	// **************************** 调皮的分割线***********************后台接口

	/**
	 * 查询意见反馈
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/feedback/feed-back-all")
	public @ResponseBody byte[] getFeedback(@Param("pageSize") Integer pageSize,
			@Param("departmentId") Integer departmentId, @Param("searchValue") String searchValue)
			throws UnsupportedEncodingException {
		ArrayList<Object> list = new ArrayList<Object>();
		List<Feedback> feedback = feedbackService.getFeedback((pageSize - 1) * 10, departmentId, searchValue);
		// 格式化时间
		for (Feedback f : feedback) {
			f.setCreationDateStr(DateUtil.format(f.getCreationDate(), "yyyy-MM-dd HH:mm"));
		}
		Integer feedbackSum = feedbackService.getFeedbackSum(departmentId, searchValue);
		List<Department> findAll = departmentService.findAll();
		list.add(feedback);
		list.add(feedbackSum);
		list.add(findAll);
		return gson.toJson(list).getBytes("utf-8");
	}

	/**
	 * 查询
	 * 
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping("/feedback/feedback-details")
	public @ResponseBody byte[] get(Integer id) throws UnsupportedEncodingException {

		Feedback feedback = feedbackService.getFeedbackById(id);

		feedback.setCreationDateStr(DateUtil.format(feedback.getCreationDate(), "yyyy-MM-dd HH:mm"));

		return gson.toJson(feedback).getBytes("utf-8");

	};

}
