/**
 * 
 */
package com.bayee.political.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.bayee.political.domain.Department;
import com.bayee.political.service.DepartmentService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.StatusCode;
import com.taobao.api.ApiException;

/**
 * @author seanguo
 *
 */
@Controller
public class DepartmentController extends BaseController {

	@Autowired
	private DepartmentService departmentService;

//	@RequestMapping(value={"/department/snyc"})
//	public ResponseEntity<String> snycDataFromDingtalk(Model model) throws ApiException {
//		
//		String accessToken = getAccessToken();
//		
//		DingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/department/list");
//		OapiDepartmentListRequest request = new OapiDepartmentListRequest();
//		request.setHttpMethod("GET");
//		OapiDepartmentListResponse response = client.execute(request, accessToken);
//		List<Department> departmentList = response.getDepartment();
//		int count = 0;
//		for(Department dept : departmentList) {
//			long id = dept.getId();
//			String name = dept.getName();
//			
//			com.bayee.political.domain.Department department = new com.bayee.political.domain.Department();
//			department.setId(id);
//			department.setName(name);
//			if(dept.getParentid() != null) {
//				department.setParentId(dept.getParentid());
//			}
//			
//			departmentService.save(department);
//			count++;
//		}
//		
//		return new ResponseEntity<String>(String.valueOf("Department saved: " + count), HttpStatus.OK);
//	}

	@RequestMapping(value = "/department", method = RequestMethod.GET)
	public ResponseEntity<?> departmentList(@RequestParam(value = "id", required = false) Integer id)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<Department> departmentList = departmentService.departmentList(id);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(departmentList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}
	
	/**
	 * 根据部门类别获取部门
	 * @param parentId
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(value = "/department/parent", method = RequestMethod.GET)
	public ResponseEntity<?> departmentListByParent(
			@RequestParam(value = "parentId", required = false) Integer parentId)
			throws ApiException {
		DataListReturn dlr = new DataListReturn();
		List<Department> departmentList = departmentService.getDepartmentByType(parentId);
		dlr.setStatus(true);
		dlr.setMessage("success");
		dlr.setResult(departmentList);
		dlr.setCode(StatusCode.getSuccesscode());
		return new ResponseEntity<DataListReturn>(dlr, HttpStatus.OK);
	}
	
}
