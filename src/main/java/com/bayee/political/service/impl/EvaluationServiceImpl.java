package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.EvaluateAuthority;
import com.bayee.political.domain.EvaluateAuthorityTarget;
import com.bayee.political.domain.EvaluateParticipant;
import com.bayee.political.domain.EvaluateProject;
import com.bayee.political.domain.EvaluateRank;
import com.bayee.political.domain.EvaluateRankDetail;
import com.bayee.political.domain.EvaluateTarget;
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.EvaluateTaskParticipant;
import com.bayee.political.domain.EvaluateTaskParticipantTemp;
import com.bayee.political.domain.EvaluateTemplate;
import com.bayee.political.domain.ParticipantVote;
import com.bayee.political.domain.Vote;
import com.bayee.political.mapper.DepartmentMapper;
import com.bayee.political.mapper.EvaluateAuthorityMapper;
import com.bayee.political.mapper.EvaluateAuthorityTargetMapper;
import com.bayee.political.mapper.EvaluateParticipantMapper;
import com.bayee.political.mapper.EvaluateProjectMapper;
import com.bayee.political.mapper.EvaluateRankMapper;
import com.bayee.political.mapper.EvaluateTargetMapper;
import com.bayee.political.mapper.EvaluateTaskMapper;
import com.bayee.political.mapper.EvaluateTemplateMapper;
import com.bayee.political.mapper.EvaluateTaskParticipantMapper;
import com.bayee.political.mapper.EvaluateTaskParticipantTempMapper;
import com.bayee.political.mapper.EvaluateRankDetailMapper;
import com.bayee.political.service.EvaluationService;

/**
 * @author shentuqiwei
 * @version 2020年5月22日 下午3:53:35
 */
@Service
public class EvaluationServiceImpl implements EvaluationService {

	@Autowired
	EvaluateAuthorityMapper evaluateAuthorityMapper;// 评价权限

	@Autowired
	EvaluateAuthorityTargetMapper evaluateAuthorityTargetMapper;// 评价权限对象

	@Autowired
	EvaluateParticipantMapper evaluateParticipantMapper;// 评价参与人类型

	@Autowired
	EvaluateRankMapper evaluateRankMapper;// 评价等次规则

	@Autowired
	EvaluateTaskMapper evaluateTaskMapper;// 评价任务

	@Autowired
	EvaluateTemplateMapper evaluateTemplateMapper;// 评价模板

	@Autowired
	EvaluateTaskParticipantMapper evaluateTaskParticipantMapper;// 评价任务参与人

	@Autowired
	EvaluateTaskParticipantTempMapper evaluateTaskParticipantTempMapper;// 评价任务参与人临时表

	@Autowired
	EvaluateTargetMapper evaluateTargetMapper;// 评价对象

	@Autowired
	DepartmentMapper departmentMapper;// 部门

	@Autowired
	EvaluateRankDetailMapper evaluateRankDetailMapper;

	@Autowired
	EvaluateProjectMapper evaluateProjectMapper;// 项目

	// 评价列表查询
//	@Override
//	public List<EvaluateTask> evaluationList(String policeId, Integer waitStatus, Integer overStatus, Integer pageSize) {
//		return evaluateTaskMapper.evaluationList(policeId, waitStatus, overStatus, pageSize);
//	}

	@Override
	public List<EvaluateTask> evaluationList(String policeId, Integer waitStatus, Integer overStatus) {
		return evaluateTaskMapper.evaluationList(policeId, waitStatus, overStatus);
	}

	// 评价列表数量统计
	@Override
	public int evaluationListCount(String policeId, Integer waitStatus, Integer overStatus) {
		return evaluateTaskMapper.evaluationListCount(policeId, waitStatus, overStatus);
	}

	// 评价任务删除
	@Override
	public int evaluationTaskDelete(Integer id) {
		return evaluateTaskMapper.evaluationTaskDelete(id);
	}

	// 评价模板查询(后台)
	@Override
	public List<EvaluateTemplate> evaluateTemplateList() {
		return evaluateTemplateMapper.evaluateTemplateList();
	}

	// 评价列表查询(后台)
	@Override
	public List<EvaluateTask> evaluateTaskList(Integer type, Integer process, String keywords, Integer pageSize) {
		return evaluateTaskMapper.evaluateTaskList(type, process, keywords, pageSize);
	}

	// 评价列表查询数量统计(后台)
	@Override
	public int evaluateTaskListCount(Integer type, Integer process, String keywords) {
		return evaluateTaskMapper.evaluateTaskListCount(type, process, keywords);
	}

	// 评价列表查询（定时任务修改任务进程）（后台）
	@Override
	public List<EvaluateTask> evaluateTaskTimingList() {
		return evaluateTaskMapper.evaluateTaskTimingList();
	}

	// 评价详情(后台)
	@Override
	public EvaluateTask evaluateTaskItem(Integer id) {
		return evaluateTaskMapper.evaluateTaskItem(id);
	}

	// 评价任务新增（后台）
	@Override
	public int evaluateTaskCreat(EvaluateTask evaluateTask) {
		return evaluateTaskMapper.evaluateTaskCreat(evaluateTask);
	}

	// 评价任务修改（后台）
	@Override
	public int evaluateTaskUpdate(EvaluateTask evaluateTask) {
		return evaluateTaskMapper.evaluateTaskUpdate(evaluateTask);
	}

	// 评价参与人新增(api)
	@Override
	public int evaluateTaskParticipantCreat(EvaluateTaskParticipant evaluateTaskParticipant) {
		return evaluateTaskParticipantMapper.evaluateTaskParticipantCreat(evaluateTaskParticipant);
	}

	// 评价参与人批量新增(api)
	@Override
	public void evaluateTaskParticipantBatch(List<EvaluateTaskParticipant> evaluateTaskParticipantList) {
		evaluateTaskParticipantMapper.evaluateTaskParticipantBatch(evaluateTaskParticipantList);

	}

	// 评价参与人修改(api)
	@Override
	public int evaluateTaskParticipantUpdate(EvaluateTaskParticipant evaluateTaskParticipant) {
		return evaluateTaskParticipantMapper.evaluateTaskParticipantUpdate(evaluateTaskParticipant);
	}

	// 查询模板中的机关单位(api)
	@Override
	public List<EvaluateTemplate> templateDepList(Integer id) {
		return evaluateTemplateMapper.templateDepList(id);
	}

	// 根据评价id删除评价参与人（后台）
	@Override
	public int evaluateTaskParticipantDalete(Integer taskId) {
		return evaluateTaskParticipantMapper.evaluateTaskParticipantDalete(taskId);
	}

	// 根据评价id计算评价任务总条数(api)
	@Override
	public int evaluateTaskParticipantTotal(Integer taskId) {
		return evaluateTaskParticipantMapper.evaluateTaskParticipantTotal(taskId);
	}

	// 根据评价id计算评价任务已评价条数(api)
	@Override
	public int evaluateTaskParticipantOver(Integer taskId) {
		return evaluateTaskParticipantMapper.evaluateTaskParticipantOver(taskId);
	}

	// 评价对象查询(api)
	@Override
	public List<EvaluateTaskParticipant> objectList(String policeId, Integer taskId, Integer departmentId,
			String sort) {
		return evaluateTaskParticipantMapper.objectList(policeId, taskId, departmentId, sort);
	}

	// 查询等次规则(api)
	@Override
	public List<EvaluateRank> evaluateRankApiList(Integer type) {
		return evaluateRankMapper.evaluateRankApiList(type);
	}

	// 根据人员查询部门(api)
	@Override
	public List<Department> participantDepList(String policeId, Integer taskId) {
		return departmentMapper.participantDepList(policeId, taskId);
	}

	// 评价等次列表查询(后台)
	@Override
	public List<EvaluateRank> evaluationRankList(Integer type, Integer childType, Integer pageSize) {
		return evaluateRankMapper.evaluationRankList(type, childType, pageSize);
	}

	// 评价等次列表数量统计(后台)
	@Override
	public int evaluationRankListCount(Integer type, Integer childType) {
		return evaluateRankMapper.evaluationRankListCount(type, childType);
	}

	// 评价等次新增(后台)
	@Override
	public int evaluationRankCreat(EvaluateRank evaluateRank) {
		return evaluateRankMapper.evaluationRankCreat(evaluateRank);
	}

	// 评价等次修改(后台)
	@Override
	public int evaluationRankUpdate(EvaluateRank evaluateRank) {
		return evaluateRankMapper.evaluationRankUpdate(evaluateRank);
	}

	// 评价等次删除(后台)
	@Override
	public int evaluationRankDelete(Integer id) {
		return evaluateRankMapper.evaluationRankDelete(id);
	}

	// 评价等次详情查询(后台)
	@Override
	public EvaluateRank evaluateRankItem(Integer id) {
		return evaluateRankMapper.evaluateRankItem(id);
	}

	// 评价模板列表查询(后台)
	@Override
	public List<EvaluateTemplate> evaluationTemplateList(Integer templateType, String keywords, Integer pageSize) {
		return evaluateTemplateMapper.evaluationTemplateList(templateType, keywords, pageSize);
	}

	// 评价模板列表数量统计(后台)
	@Override
	public int evaluationTemplateListCount(Integer templateType, String keywords) {
		return evaluateTemplateMapper.evaluationTemplateListCount(templateType, keywords);
	}

	// 评价对象列表查询(后台)
	@Override
	public List<EvaluateTarget> evaluateTargetList() {
		return evaluateTargetMapper.evaluateTargetList();

	}

	// 评价模板详情查询(后台)
	@Override
	public EvaluateTemplate evaluateTemplateItem(Integer id) {
		return evaluateTemplateMapper.evaluateTemplateItem(id);
	}

	// 评价模板新增(后台)
	@Override
	public int evaluateTemplateCreat(EvaluateTemplate evaluateTemplate) {
		return evaluateTemplateMapper.evaluateTemplateCreat(evaluateTemplate);
	}

	// 评价模板修改(后台)
	@Override
	public int evaluateTemplateUpdate(EvaluateTemplate evaluateTemplate) {
		return evaluateTemplateMapper.evaluateTemplateUpdate(evaluateTemplate);
	}

	// 评价模板删除(后台)
	@Override
	public int evaluateTemplateDelete(Integer id) {
		return evaluateTemplateMapper.evaluateTemplateDelete(id);
	}

	// 评价权限列表查询(后台)
	@Override
	public List<EvaluateAuthority> evaluationAuthorityList(String keywords, Integer pageSize) {
		return evaluateAuthorityMapper.evaluationAuthorityList(keywords, pageSize);
	}

	// 评价权限列表数量统计(后台)
	@Override
	public int evaluationAuthorityListCount(String keywords) {
		return evaluateAuthorityMapper.evaluationAuthorityListCount(keywords);
	}

	// 评价权限详情查询(后台)
	@Override
	public EvaluateAuthority evaluateAuthorityItem(Integer id) {
		return evaluateAuthorityMapper.evaluateAuthorityItem(id);
	}

	// 评价权限新增(后台)
	@Override
	public int evaluateAuthorityCreat(EvaluateAuthority evaluateAuthority) {
		return evaluateAuthorityMapper.evaluateAuthorityCreat(evaluateAuthority);
	}

	// 评价权限修改(后台)
	@Override
	public int evaluateAuthorityUpdate(EvaluateAuthority evaluateAuthority) {
		return evaluateAuthorityMapper.evaluateAuthorityUpdate(evaluateAuthority);
	}

	// 评价权限删除(后台)
	@Override
	public int evaluateAuthorityDelete(Integer id) {
		return evaluateAuthorityMapper.evaluateAuthorityDelete(id);
	}

	// 评价参与人列表查询(后台)
	@Override
	public List<EvaluateParticipant> evaluateParticipantList() {
		return evaluateParticipantMapper.evaluateParticipantList();
	}

	// 评价对象数据列表查询(后台)
	@Override
	public List<EvaluateAuthorityTarget> evaluateAuthorityTargetList(Integer id) {
		return evaluateAuthorityTargetMapper.evaluateAuthorityTargetList(id);
	}

	// 评价对象列表新增(后台)
	@Override
	public int evaluateAuthorityTargetCreat(EvaluateAuthorityTarget targetItem) {
		return evaluateAuthorityTargetMapper.evaluateAuthorityTargetCreat(targetItem);
	}

	// 评价对象列表修改(后台)
	@Override
	public int evaluateAuthorityTargetUpdate(EvaluateAuthorityTarget targetItem) {
		return evaluateAuthorityTargetMapper.evaluateAuthorityTargetUpdate(targetItem);
	}

	// 评价对象列表根据authorityId删除(后台)
	@Override
	public int evaluateAuthorityTargetTotalDelete(Integer authorityId) {
		return evaluateAuthorityTargetMapper.evaluateAuthorityTargetTotalDelete(authorityId);
	}

	// 查询等次详情规则(api)
	@Override
	public List<EvaluateRankDetail> evaluateRankDetailApiList(Integer rankId) {
		return evaluateRankDetailMapper.evaluateRankDetailApiList(rankId);
	}

	// 评价等次详情列表新增(后台)
	@Override
	public int evaluateRankDetailCreat(EvaluateRankDetail detailItem) {
		return evaluateRankDetailMapper.evaluateRankDetailCreat(detailItem);
	}

	// 根据rankId删除等次详情规则(后台)
	@Override
	public int evaluateRankDetailTotalDelete(Integer rankId) {
		return evaluateRankDetailMapper.evaluateRankDetailTotalDelete(rankId);
	}

	// 评价等次详情信息查询(后台)
	@Override
	public List<EvaluateRankDetail> evaluateRankDetailList(Integer rankId) {
		return evaluateRankDetailMapper.evaluateRankDetailList(rankId);
	}

	@Override
	public List<EvaluateRank> evaluationAllRankList() {
		return evaluateRankMapper.evaluationAllRankList();
	}

	@Override
	public EvaluateAuthority getEvaluateAuthorityById(Integer id) {
		return evaluateAuthorityMapper.getEvaluateAuthorityById(id);
	}

	@Override
	public EvaluateRank getEvaluateRankById(Integer id) {
		return evaluateRankMapper.getEvaluateRankById(id);
	}

	@Override
	public List<EvaluateAuthority> getAllEvaluateAuthority() {
		return evaluateAuthorityMapper.getAllEvaluateAuthority();
	}

	// 项目列表查询(后台)
	@Override
	public List<EvaluateProject> evaluateProjectList() {
		return evaluateProjectMapper.evaluateProjectList();
	}

	// 可对项目进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> projectTaskList(Integer id) {
		return evaluateTaskParticipantMapper.projectTaskList(id);
	}

	// 可对项目进行评价的单位负责人列表(后台)
	@Override
	public List<EvaluateTaskParticipant> projectUnitLeaderTaskList(Integer id) {
		return evaluateTaskParticipantMapper.projectUnitLeaderTaskList(id);
	}

	// 可对机关单位进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> officeTaskList(Integer id) {
		return evaluateTaskParticipantMapper.officeTaskList(id);
	}

	// 判断模板类型(后台)
	@Override
	public EvaluateTemplate templateType(Integer id) {
		return evaluateTemplateMapper.templateType(id);
	}

	// 可对派出所进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> policeStationTaskList(Integer id) {
		return evaluateTaskParticipantMapper.policeStationTaskList(id);
	}

	// 机关单位中层干部进行评价的民警列表(局领导、派出所负责人)(后台)
	@Override
	public List<EvaluateTaskParticipant> officeLeaderTaskList1(Integer id, Integer positionId) {
		return evaluateTaskParticipantMapper.officeLeaderTaskList1(id, positionId);
	}

	// 机关单位中层干部进行评价的民警列表(机关单位中层干部、机关单位民警)(后台)
	@Override
	public List<EvaluateTaskParticipant> officeLeaderTaskList2(Integer id, Integer isCadre, Integer positionId) {
		return evaluateTaskParticipantMapper.officeLeaderTaskList2(id, isCadre, positionId);
	}

	// 机关单位中层干部进行评价的民警列表(机关单位负责人)(后台)
	@Override
	public List<EvaluateTaskParticipant> officeLeaderTaskList3(Integer id, Integer isCadre, Integer positionId) {
		return evaluateTaskParticipantMapper.officeLeaderTaskList3(id, isCadre, positionId);
	}

	// 局领导和机关单位负责人对派出所中层干部评价(后台)
	@Override
	public List<EvaluateTaskParticipant> policeLeaderTaskList1(Integer id, Integer positionId) {
		return evaluateTaskParticipantMapper.policeLeaderTaskList1(id, positionId);
	}

	// 派出所中层干部和民警对派出所本部中层干部评价(后台)
	@Override
	public List<EvaluateTaskParticipant> policeLeaderTaskList2(Integer id, Integer isCadre, Integer positionId) {
		return evaluateTaskParticipantMapper.policeLeaderTaskList2(id, isCadre, positionId);
	}

	// 派出所负责人对派出所本部中层干部评价(后台)
	@Override
	public List<EvaluateTaskParticipant> policeLeaderTaskList3(Integer id, Integer isCadre, Integer positionId) {
		return evaluateTaskParticipantMapper.policeLeaderTaskList3(id, isCadre, positionId);
	}

	// 可对机关单位全部民警进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> officePoliceTaskList(Integer id) {
		return evaluateTaskParticipantMapper.officePoliceTaskList(id);
	}

	// 可对派出所全部民警进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> policemanTaskList(Integer id) {
		return evaluateTaskParticipantMapper.policemanTaskList(id);
	}

	// 可对评议单位进行评价的民警列表(后台)
	@Override
	public List<EvaluateTaskParticipant> commentsTaskList(Integer id) {
		return evaluateTaskParticipantMapper.commentsTaskList(id);
	}

	@Override
	public List<EvaluateTaskParticipant> getTaskDataList(Integer taskId, Integer pageSize) {
		return evaluateTaskParticipantMapper.getTaskDataList(taskId, pageSize);
	}

	@Override
	public Integer getTaskDataListSum(Integer taskId) {
		return evaluateTaskParticipantMapper.getTaskDataListSum(taskId);
	}

	// 查询评价得分
	@Override
	public List<EvaluateTaskParticipant> evaluationRankList(Integer taskId, Integer pageSize) {
		return evaluateTaskParticipantMapper.evaluationRankList(taskId, pageSize);
	}

	// 查询评价得分列表数量
	@Override
	public int evaluationRankListCount(Integer taskId) {
		return evaluateTaskParticipantMapper.evaluationRankListCount(taskId);
	}

	// 查询个人/单位/项目具体排名和分数（后台）
	@Override
	public EvaluateTaskParticipant evaluationRankingItem(String objectId, Integer taskId) {
		return evaluateTaskParticipantMapper.evaluationRankingItem(objectId, taskId);
	}

	// 投票分类数量统计（后台）
	@Override
	public List<EvaluateTaskParticipant> evaluationVoteList(String objectId, Integer taskId, Integer participantId) {
		return evaluateTaskParticipantMapper.evaluationVoteList(objectId, taskId, participantId);
	}

	// 查询当前任务id中的等级类型（后台）
	@Override
	public List<EvaluateTaskParticipant> voteTypeList(Integer taskId) {
		return evaluateTaskParticipantMapper.voteTypeList(taskId);
	}

	// 查询当前任务参与人id（后台）
	@Override
	public List<EvaluateTaskParticipant> participantTypeList(Integer taskId) {
		return evaluateTaskParticipantMapper.participantTypeList(taskId);
	}

	// 已评价详情-投票信息统计(api)
	@Override
	public Vote VoteItem(Integer taskId) {
		return evaluateTaskParticipantMapper.VoteItem(taskId);
	}

	// 投票类型分类统计(api)
	@Override
	public List<EvaluateRankDetail> voteDetailTypeList(Integer policeId, Integer taskId) {
		return evaluateRankDetailMapper.voteDetailTypeList(policeId, taskId);
	}

	// 规则详情查询(api)
	@Override
	public EvaluateRankDetail evaluateRankDetailItem(Integer id) {
		return evaluateRankDetailMapper.evaluateRankDetailItem(id);
	}

	// 根据rankId和规则名称查询(api)
	@Override
	public EvaluateRankDetail rankDetailItem(Integer rankId, String detailName) {
		return evaluateRankDetailMapper.rankDetailItem(rankId, detailName);
	}

	// 评价等次详情列表修改(后台)
	@Override
	public int evaluateRankDetailUpdate(EvaluateRankDetail detailItem) {
		return evaluateRankDetailMapper.evaluateRankDetailUpdate(detailItem);
	}

	// 判断当前模板是否被任务引用(后台)
	@Override
	public List<EvaluateTask> existTemplateList(Integer templateId) {
		return evaluateTaskMapper.existTemplateList(templateId);
	}

	// 判断当前等次是否被模板引用(后台)
	@Override
	public List<EvaluateTemplate> existRankList(Integer rankId) {
		return evaluateTemplateMapper.existRankList(rankId);
	}

	// 判断当前等次详情是否被评价详情列表数据引用(后台)
	@Override
	public List<EvaluateTaskParticipant> existTaskParticipantList(Integer rankDetailId) {
		return evaluateTaskParticipantMapper.existTaskParticipantList(rankDetailId);
	}

	// 评价对象超过100%验证
	@Override
	public EvaluateAuthorityTarget moreOneHundredItem(Integer targetId) {
		return evaluateAuthorityTargetMapper.moreOneHundredItem(targetId);
	}

	// 根据参与人类别统计各项投票(后台)
	@Override
	public List<ParticipantVote> participantVoteList(Integer taskId, String objectId) {
		return evaluateTaskParticipantMapper.participantVoteList(taskId, objectId);
	}

	// 评价对象临时表批量新增(api)
	@Override
	public void tempBatch(List<EvaluateTaskParticipantTemp> tempList) {
		evaluateTaskParticipantTempMapper.tempBatch(tempList);
	}

	// 查询是否有超过最大数的规则类型（api）
	@Override
	public EvaluateTaskParticipantTemp participantTempItem(String policeId, Integer taskId) {
		return evaluateTaskParticipantTempMapper.participantTempItem(policeId, taskId);
	}

	// 删除临时表全部数据
	@Override
	public int tempDeleteAll(String policeId, Integer taskId) {
		return evaluateTaskParticipantTempMapper.tempDeleteAll(policeId, taskId);
	}

	// 查询任务是否需要审批(后台)
	@Override
	public EvaluateTaskParticipant operationStatusItem(Integer taskId) {
		return evaluateTaskParticipantMapper.operationStatusItem(taskId);
	}

	@Override
	public List<EvaluateTaskParticipant> taskSend() {
		return evaluateTaskParticipantMapper.taskSend();
	}

	// 查询任务是否已经完成(后台)
	@Override
	public List<EvaluateTask> taskOversList(Integer taskId) {
		return evaluateTaskParticipantMapper.taskOversList(taskId);
	}

	@Override
	public List<EvaluateTaskParticipant> projectList(String policeId, Integer taskId, String sort) {
		return evaluateTaskParticipantMapper.projectList(policeId, taskId, sort);
	}

	// 查询任务为项目时项目所属单位
	@Override
	public EvaluateTaskParticipant projectDepNameItem(Integer id) {
		return evaluateTaskParticipantMapper.projectDepNameItem(id);
	}

	@Override
	public List<EvaluateTaskParticipant> getAllObject(Integer taskId) {
		return evaluateTaskParticipantMapper.getAllObject(taskId);
	}

	@Override
	public List<EvaluateTaskParticipant> taskDataListByObjName(Integer taskId, String objectName, Integer pageSize,
			String author) {
		return evaluateTaskParticipantMapper.taskDataListByObjName(taskId, objectName, pageSize, author);
	}

	@Override
	public List<EvaluateTaskParticipant> evaluationRankListObjName(Integer taskId) {
		return evaluateTaskParticipantMapper.evaluationRankListObjName(taskId);
	}

	@Override
	public List<EvaluateTaskParticipant> evaluationRankListByObjName(Integer taskId, String objectName,
			Integer pageSize) {
		return evaluateTaskParticipantMapper.evaluationRankListByObjName(taskId, objectName, pageSize);
	}

	@Override
	public List<EvaluateTaskParticipant> getAllParticipants(Integer taskId) {
		return evaluateTaskParticipantMapper.getAllParticipants(taskId);
	}

	@Override
	public List<EvaluateTaskParticipant> taskDataListBySelectParticipants(Integer taskId, String objectName,
			Integer pageSize, String author) {
		return evaluateTaskParticipantMapper.taskDataListBySelectParticipants(taskId, objectName, pageSize, author);
	}

	// 名次排名excel导出
	@Override
	public List<EvaluateTaskParticipant> rankSaveExcelList(Integer taskId, String objectName, Integer departmentId) {
		return evaluateTaskParticipantMapper.rankSaveExcelList(taskId, objectName, departmentId);
	}

	// 任务参与人名称查询
	@Override
	public List<EvaluateTaskParticipant> participantNameList(Integer taskId) {
		return evaluateTaskParticipantMapper.participantNameList(taskId);
	}

	// 个人评分详细数据查询
	@Override
	public List<ParticipantVote> rankItemList(Integer taskId, String objectId) {
		return evaluateTaskParticipantMapper.rankItemList(taskId, objectId);
	}

	// 查询项目所属部门
	@Override
	public EvaluateProject evaluateProjectItem(String objectId) {
		return evaluateProjectMapper.evaluateProjectItem(objectId);
	}

	@Override
	public List<EvaluateTask> newTaskTopFive() {
		return evaluateTaskMapper.newTaskTopFive();
	}

	@Override
	public Integer taskNumWeek() {
		return evaluateTaskMapper.taskNumWeek();
	}

	@Override
	public Double typeRator(Integer type, Integer dateType) {
		return evaluateTaskMapper.typeRator(type, dateType);
	}

}
