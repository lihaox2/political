/**
 * 
 */
package com.bayee.political.service.impl;

import java.io.File;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.json.*;
import com.bayee.political.mapper.PoliceLabelMapper;
import com.bayee.political.pojo.dto.HolographicPoliceListDO;
import com.bayee.political.pojo.dto.RiskReportRecordDO;
import com.bayee.political.service.RiskReportRecordService;
import com.bayee.political.service.RiskService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.PolicePosition;
import com.bayee.political.domain.User;
import com.bayee.political.mapper.PolicePositionMapper;
import com.bayee.political.mapper.UserMapper;
import com.bayee.political.service.UserService;

/**
 * @author seanguo
 *
 */
@Service

public class UserServiceImpl implements UserService {

	private Logger log= LoggerFactory.getLogger(UserServiceImpl.class);

	private final String HOST = "http://8.136.146.186:8099/static";

	@Autowired
	UserMapper userMapper;

	@Autowired
	PolicePositionMapper policePositionMapper;

	@Autowired
	PoliceLabelMapper policeLabelMapper;

	@Autowired
	RiskReportRecordService riskReportRecordService;

	@Autowired
	private RiskService riskService;

	// 查询全部警员数据
	@Override
	public List<User> userAllList() {
		return userMapper.userAllList();
	}

	// 人员新增
	@Override
	public int userCreat(User user) {
		return userMapper.userCreat(user);
	}

	// 人员修改
	@Override
	public int userUpdate(User user) {
		return userMapper.userUpdate(user);
	}

	@Override
	public void save(User user) {
		if (findByDDUserId(user.getDdUserId()) == null)
			userMapper.save(user);
	}

	@Override
	public List<User> findAll() {
		return userMapper.findAll();
	}

	@Override
	public User findByDDUserId(String ddUserId) {
		return userMapper.findByDDUserId(ddUserId);
	}

	@Override
	public int countTotal() {
		return userMapper.countTotal();
	}

	@Override
	public void updatePhone(User user) {
		userMapper.updatePhone(user);
	}

	@Override
	public User authenticate(String phone, String password) {
		return userMapper.authenticate(phone, password);
	}

	@Override
	public List<User> findUserByName(String name) {
		return userMapper.findUserByName(name);
	}

	@Override
	public void update(User user) {
		userMapper.update(user);
	}

	@Override
	public User findByResidentId(String residentId) {
		return userMapper.findByResidentId(residentId);
	}

	// 人员列表查询
	@Override
	public List<User> userList(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre,
			String keywords, Integer pageSize) {
		return userMapper.userList(departmentId, positionId, isUnitLeader, isCadre, keywords, pageSize);
	}

	// 人员列表数量统计
	@Override
	public int userListCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre,
			String keywords) {
		return userMapper.userListCount(departmentId, positionId, isUnitLeader, isCadre, keywords);
	}

	// 人员详情查询
	@Override
	public User userItem(Integer id) {
		return userMapper.userItem(id);
	}

	// 职位列表
	@Override
	public List<PolicePosition> policePositionList() {
		return policePositionMapper.policePositionList();
	}

	// 人员删除
	@Override
	public int userDelete(Integer id) {
		return userMapper.userDelete(id);
	}

	// 查询参与人相关评价任务(api)
	@Override
	public List<User> userTaskList(Integer departmentId, Integer parentId, Integer isUnitLeader, Integer templateId) {
		return userMapper.userTaskList(departmentId, parentId, isUnitLeader, templateId);
	}

	// 人员详情查询根据policeId
	@Override
	public User policeItem(String policeId, String password, String ddUserId) {
		return userMapper.policeItem(policeId, password, ddUserId);
	}

	@Override
	public List<User> getUserByGroupId(Integer groupId, Integer departmentId, Integer positionId, Integer isUnitLeader,
			Integer isCadre, String keywords, Integer currentPage) {
		return userMapper.getUserByGroupId(groupId, departmentId, positionId, isUnitLeader, isCadre, keywords,
				currentPage);
	}

	@Override
	public Integer userListCountSum(Integer groupId, Integer departmentId, Integer positionId, Integer isUnitLeader,
			Integer isCadre, String keywords) {
		return userMapper.userListCountSum(groupId, departmentId, positionId, isUnitLeader, isCadre, keywords);
	}

	// 警号，身份证号码，手机号唯一性验证
	@Override
	public User accountOnly(String policeId, String phone, String idCard) {
		return userMapper.accountOnly(policeId, phone, idCard);
	}

	// 查询当前群组是否被引用
	@Override
	public List<User> groupPolicesList(Integer groupId) {
		return userMapper.groupPolicesList(groupId);
	}

	@Override
	public List<User> getUsersByPoliceId(String[] policeIds) {
		return userMapper.getUsersByPoliceId(policeIds);
	}

	@Override
	public List<User> getUsersByDepUnitCard(Integer departmentId) {
		return userMapper.getUsersByDepUnitCard(departmentId);
	}

	@Override
	public List<User> getUsersByNotDepUnitCard(Integer departmentId, String policeId) {
		return userMapper.getUsersByNotDepUnitCard(departmentId, policeId);
	}

	@Override
	public List<User> getUsersByDepartmentId(Integer departmentId) {
		return userMapper.getUsersByDepartmentId(departmentId);
	}

	@Override
	public List<User> allUser(Integer departmentId) {
		return userMapper.allUser(departmentId);
	}

	@Override
	public String getUserNameByPoliceIds(String PoliceIds) {
		return userMapper.getUserNameByPoliceIds(PoliceIds);
	}

	@Override
	public PolicePosition getPolicePositionByPoliceId(String policeId) {
		return policePositionMapper.getPolicePositionByPoliceId(policeId);
	}

	// 录分训练计划中综合体能项目查询
	@Override
	public List<User> trainRecordPoliceHeightList(Integer projectId, Integer trainPhysicalId) {
		return userMapper.trainRecordPoliceHeightList(projectId, trainPhysicalId);
	}

	// 根据警员人员数据修改
	@Override
	public int userPoliceUpdate(User user) {
		return userMapper.userPoliceUpdate(user);
	}

	@Override
	public int addHeightWeight(String policeId, Double height, Double weight) {
		return userMapper.addHeightWeight(policeId, height, weight);
	}

	@Override
	public String getPoliceIdByDepartmentId(Integer departmentId) {
		return userMapper.getPoliceIdByDepartmentId(departmentId);
	}

	@Override
	public List<User> getPoliceByDepartmentId(Integer departmentId) {
		return userMapper.getPoliceByDepartmentId(departmentId);
	}

	@Override
	public List<User> getAllLeaders() {
		return userMapper.getAllLeaders();
	}

	@Override
	public String getResponsibleDepartment(String policeId) {
		return userMapper.getResponsibleDepartment(policeId);
	}

	// 警力在线民警总数
	@Override
	public int policeForceOnlineCount() {
		return userMapper.policeForceOnlineCount();
	}

	// 用户数量统计
	@Override
	public int userProcessCount(Integer departmentId, Integer positionId, Integer isUnitLeader, Integer isCadre) {
		return userMapper.userProcessCount(departmentId, positionId, isUnitLeader, isCadre);
	}

	// 训练人员列表查询
	@Override
	public List<User> userTrainList() {
		return userMapper.userTrainList();
	}

	// 查询全部警员详细信息数据
	@Override
	public List<User> userInfoAllList() {
		return userMapper.userInfoAllList();
	}

	@Override
	public User findByPoliceId(String policeId) {
		return userMapper.findByPoliceId(policeId);
	}

	@Override
	public Integer countAllPolice() {
		return userMapper.countAllPolice();
	}

	@Override
	public List<User> findUserByDeptId(Integer deptId) {
		return userMapper.findUserByDeptId(deptId);
	}

	@Override
	public boolean checkPoliceExists(String policeId) {
		User user = userMapper.findByPoliceId(policeId);

		return user != null && user.getPoliceId() != null;
	}

	@Override
	public List<HolographicPoliceListDO> holographicFindPoliceByKey(String key) {
		return userMapper.holographicFindPoliceByKey(key);
	}

	@Override
	public List<User> findByDeptId(Integer deptId) {
		return userMapper.findByDeptId(deptId);
	}

	@Override
	public List<TalentsUserResult> talentsFindPageList(TalentsUserParam talentsUserParam) {
		if(talentsUserParam.getPageIndex() == null || talentsUserParam.getPageIndex() < 1){
			talentsUserParam.setPageIndex(1);
		}
		talentsUserParam.setPageIndex((talentsUserParam.getPageIndex() - 1) * talentsUserParam.getPageSize());

		return userMapper.talentsFindPageList(talentsUserParam).parallelStream().map(e -> {
			//查询警员标签
			e.setLabelList(policeLabelMapper.findPoliceLabel(e.getPoliceId()));
			return e;
		}).collect(Collectors.toList());
	}

	@Override
	public Integer talentsFindPageCount(TalentsUserParam talentsUserParam) {
		return userMapper.talentsFindPageCount(talentsUserParam);
	}

	@Override
	public TalentsParticularsResultList findTalentsUserInfo(String firstId, String sendId) throws ParseException {
		//第一个人的信息
		TalentsParticularsResult firstList = userMapper.findTalentsUserInfo(firstId);


		//第二人的信息
		TalentsParticularsResult sendList = userMapper.findTalentsUserInfo(sendId);

		String dateTime = DateUtils.formatDate(new Date(), "yyyy-MM");
		String lastMonthTime = DateUtils.lastMonthTime();

		RiskReportRecord item1 = riskService.riskReportRecordItem(null, firstId, dateTime, null, lastMonthTime, 1);
		firstList.setDutyScore(item1.getDutyNum());
		firstList.setSocialContactScore(item1.getSocialContactNum());
		firstList.setFamilyEvaluationNum(item1.getAmilyEvaluationNum());
		firstList.setSynthesizeScore(item1.getTotalSumNum());
		firstList.setConductScore(item1.getConductNum());
		firstList.setEnforceScore(item1.getHandlingCaseNum());
		firstList.setPerformScore(item1.getDutyNum());
		firstList.setSkillScore(item1.getTrainNum());
		firstList.setHealthScore(item1.getHealthNum());

		RiskReportRecord item2 = riskService.riskReportRecordItem(null, sendId, dateTime, null, lastMonthTime, 1);
		sendList.setDutyScore(item2.getDutyNum());
		sendList.setSocialContactScore(item2.getSocialContactNum());
		sendList.setFamilyEvaluationNum(item2.getAmilyEvaluationNum());
		sendList.setSynthesizeScore(item2.getTotalSumNum());
		sendList.setConductScore(item2.getConductNum());
		sendList.setEnforceScore(item2.getHandlingCaseNum());
		sendList.setPerformScore(item2.getDutyNum());
		sendList.setSkillScore(item2.getTrainNum());
		sendList.setHealthScore(item2.getHealthNum());

		TalentsParticularsResultList resultList = new TalentsParticularsResultList();
		resultList.setFirstListMessage(firstList);
		resultList.setSendListMessage(sendList);

		return resultList;
	}

	@Override
	public void updateRiskHealthShowFlagByPoliceId(String policeId, Integer showFlag) {
		userMapper.updateRiskHealthShowFlagByPoliceId(policeId, showFlag);
	}

	/**
	 * 用户花名册导出
	 * @return
	 */
	@Override
	public ResponseEntity<?> export() {
		List<User> users = userMapper.userExport();
		List<Map<String,Object>> list=new ArrayList<>();
		if(users!=null && users.size()>0){
			users.stream().forEach(e->{
				if(e.getName()!="admin"){
					Map<String,Object> map=new HashMap<>();
//				map.put("序列",e.getId());
//				map.put("单位职务",e.getTitle());
//				map.put("姓 名",e.getName());
//				map.put("性 别",e.getGender()==1?"男":"女");
//				map.put("出生年月",e.getBirthday());
//				map.put("籍贯",e.getNativePlace());
//				map.put("学历",e.getDegree());
//				map.put("参加工作时间",e.getWorkingStartDate());
//				map.put("参加公安时间",e.getEmploymentDate());
//				map.put("入党时间",e.getJoiningPartyTime());
////				map.put("职务序列改革后职务","");
////				map.put("改革后职务任职起算时间","");
//				map.put("警  衔",e.getPoliceRank());
////				map.put("授衔时间","");

					map.put("id",e.getId());
					map.put("title",e.getPositionName());
					map.put("name",e.getName());
					map.put("gender",e.getGender()==1?"男":"女");
					map.put("birthday",e.getBirthday());
					map.put("nativePlace",e.getNativePlace());
					map.put("degree",e.getDegree());
					map.put("workingStartDate",e.getWorkingStartDate());
					map.put("employmentDate",e.getEmploymentDate());
					map.put("joiningPartyTime",e.getJoiningPartyTime());
					map.put("reform","");
					map.put("reformTime","");
					map.put("policeRank",e.getPoliceRank());
					map.put("confermentTime","");
					log.info("======================map:{}",map);
					list.add(map);
				}
			});
			try {
				File file = new File("/mnt/qiantang/policeInfo/警员花名册.xlsx");
				file.delete();
				//通过工具类创建writer
				ExcelWriter writer = ExcelUtil.getWriter("/mnt/qiantang/policeInfo/警员花名册.xlsx");

				//跳过当前行，既第一行，非必须，在此演示用
				writer.passCurrentRow();
				writer.addHeaderAlias("id","序列");
				writer.addHeaderAlias("title","单位职务");
				writer.addHeaderAlias("name","姓 名");
				writer.addHeaderAlias("gender","性 别");
				writer.addHeaderAlias("birthday","出生年月");
				writer.addHeaderAlias("nativePlace","籍贯");
				writer.addHeaderAlias("degree","学历");
				writer.addHeaderAlias("workingStartDate","参加工作时间");
				writer.addHeaderAlias("employmentDate","参加公安时间");
				writer.addHeaderAlias("joiningPartyTime","入党时间");
				writer.addHeaderAlias("reform","职务序列改革后职务");
				writer.addHeaderAlias("reformTime","改革后职务任职起算时间");
				writer.addHeaderAlias("policeRank","警  衔");
				writer.addHeaderAlias("confermentTime","授衔时间");
				//合并单元格后的标题行，使用默认标题样式
				writer.merge(13, "警员花名册");
				//一次性写出内容，强制输出标题
				ExcelWriter write = writer.write(list,true);

				log.info("======================{}");
				//关闭writer，释放内存
				writer.close();

				return new ResponseEntity<DataListReturn>(DataListReturn.ok(HOST+"/policeInfo/警员花名册.xlsx"), HttpStatus.OK);
			}catch (Exception e){
				return new ResponseEntity<DataListReturn>(DataListReturn.error("异常报错"), HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
		return  new ResponseEntity<DataListReturn>(DataListReturn.ok(HOST+"/policeInfo/警员花名册.xlsx"), HttpStatus.OK);
	}

}
