package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
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

/**
 * @author shentuqiwei
 * @version 2020年5月22日 下午3:53:08
 */
@Service
public interface EvaluationService {

	// 评价列表查询(api)
//	List<EvaluateTask> evaluationList(String policeId, Integer waitStatus, Integer overStatus, Integer pageSize);
	List<EvaluateTask> evaluationList(String policeId, Integer waitStatus, Integer overStatus);

	// 评价列表数量统计
	int evaluationListCount(String policeId, Integer waitStatus, Integer overStatus);

	// 评价任务删除(后台)
	int evaluationTaskDelete(Integer id);

	// 评价模板查询(后台)
	List<EvaluateTemplate> evaluateTemplateList();

	// 评价列表查询(后台)
	List<EvaluateTask> evaluateTaskList(Integer type, Integer process, String keywords, Integer pageSize);

	// 评价列表查询数量统计(后台)
	int evaluateTaskListCount(Integer type, Integer process, String keywords);

	// 评价详情(后台)
	EvaluateTask evaluateTaskItem(Integer id);

	// 评价任务新增（后台）
	int evaluateTaskCreat(EvaluateTask evaluateTask);

	// 评价任务修改（后台）
	int evaluateTaskUpdate(EvaluateTask evaluateTask);

	// 评价参与人新增(api)
	int evaluateTaskParticipantCreat(EvaluateTaskParticipant evaluateTaskParticipant);

	// 评价参与人批量新增(api)
	void evaluateTaskParticipantBatch(List<EvaluateTaskParticipant> evaluateTaskParticipantList);

	// 评价参与人修改(api)
	int evaluateTaskParticipantUpdate(EvaluateTaskParticipant evaluateTaskParticipant);

	// 查询模板中的机关单位(api)
	List<EvaluateTemplate> templateDepList(Integer id);

	// 根据评价id删除评价参与人（后台）
	int evaluateTaskParticipantDalete(Integer taskId);

	// 根据评价id计算评价任务总条数(api)
	int evaluateTaskParticipantTotal(Integer taskId);

	// 根据评价id计算评价任务已评价条数(api)
	int evaluateTaskParticipantOver(Integer taskId);

	// 评价对象查询(api)
	List<EvaluateTaskParticipant> objectList(String policeId, Integer taskId, Integer departmentId, String sort);

	// 项目单位
	List<EvaluateTaskParticipant> projectList(@Param("policeId") String policeId, @Param("taskId") Integer taskId,
			@Param("sort") String sort);

	// 查询等次规则(api)
	List<EvaluateRank> evaluateRankApiList(Integer type);

	// 查询等次详情规则(api)
	List<EvaluateRankDetail> evaluateRankDetailApiList(Integer rankId);

	// 根据人员查询部门(api)
	List<Department> participantDepList(String policeId, Integer taskId);

	// 评价列表查询（定时任务修改任务进程）（后台）
	List<EvaluateTask> evaluateTaskTimingList();

	// 评价等次列表查询(后台)
	List<EvaluateRank> evaluationRankList(Integer type, Integer childType, Integer pageSize);

	// 查询全部评价等次列表(后台)
	List<EvaluateRank> evaluationAllRankList();

	// 评价等次列表数量统计(后台)
	int evaluationRankListCount(Integer type, Integer childType);

	// 评价等次新增(后台)
	int evaluationRankCreat(EvaluateRank evaluateRank);

	// 评价等次修改(后台)
	int evaluationRankUpdate(EvaluateRank evaluateRank);

	// 评价等次删除(后台)
	int evaluationRankDelete(Integer id);

	// 评价等次详情查询(后台)
	EvaluateRank evaluateRankItem(Integer id);

	// 评价等次详情列表新增(后台)
	int evaluateRankDetailCreat(EvaluateRankDetail detailItem);

	// 评价等次详情列表修改(后台)
	int evaluateRankDetailUpdate(EvaluateRankDetail detailItem);

	// 根据rankId删除等次详情规则(后台)
	int evaluateRankDetailTotalDelete(Integer rankId);

	// 评价模板列表查询(后台)
	List<EvaluateTemplate> evaluationTemplateList(Integer templateType, String keywords, Integer pageSize);

	// 评价模板列表数量统计(后台)
	int evaluationTemplateListCount(Integer templateType, String keywords);

	// 评价对象列表查询(后台)
	List<EvaluateTarget> evaluateTargetList();

	// 评价模板详情查询(后台)
	EvaluateTemplate evaluateTemplateItem(Integer id);

	// 评价模板新增(后台)
	int evaluateTemplateCreat(EvaluateTemplate evaluateTemplate);

	// 评价模板修改(后台)
	int evaluateTemplateUpdate(EvaluateTemplate evaluateTemplate);

	// 评价模板删除(后台)
	int evaluateTemplateDelete(Integer id);

	// 评价权限列表查询(后台)
	List<EvaluateAuthority> evaluationAuthorityList(String keywords, Integer pageSize);

	/**
	 * 根据id查询权限例表
	 */
	EvaluateAuthority getEvaluateAuthorityById(Integer id);

	// 评价权限列表数量统计(后台)
	int evaluationAuthorityListCount(String keywords);

	// 评价权限详情查询(后台)
	EvaluateAuthority evaluateAuthorityItem(Integer id);

	// 评价权限新增(后台)
	int evaluateAuthorityCreat(EvaluateAuthority evaluateAuthority);

	// 评价权限修改(后台)
	int evaluateAuthorityUpdate(EvaluateAuthority evaluateAuthority);

	// 评价权限删除(后台)
	int evaluateAuthorityDelete(Integer id);

	// 评价参与人列表查询(后台)
	List<EvaluateParticipant> evaluateParticipantList();

	// 评价对象数据列表查询(后台)
	List<EvaluateAuthorityTarget> evaluateAuthorityTargetList(Integer id);

	// 评价对象列表新增(后台)
	int evaluateAuthorityTargetCreat(EvaluateAuthorityTarget targetItem);

	// 评价对象列表修改(后台)
	int evaluateAuthorityTargetUpdate(EvaluateAuthorityTarget targetItem);

	// 评价对象列表根据authorityId删除(后台)
	int evaluateAuthorityTargetTotalDelete(Integer authorityId);

	// 评价等次详情信息查询(后台)
	List<EvaluateRankDetail> evaluateRankDetailList(Integer rankId);

	// 项目列表查询(后台)
	List<EvaluateProject> evaluateProjectList();

	/**
	 * 根据id查询等次列表
	 */
	EvaluateRank getEvaluateRankById(Integer id);

	/**
	 * 查询全部权限
	 */
	List<EvaluateAuthority> getAllEvaluateAuthority();

	// 可对项目进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> projectTaskList(Integer id);

	// 可对项目进行评价的单位负责人列表(后台)
	List<EvaluateTaskParticipant> projectUnitLeaderTaskList(Integer id);

	// 可对机关单位进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> officeTaskList(Integer id);

	// 判断模板类型(后台)
	EvaluateTemplate templateType(Integer id);

	// 可对派出所进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> policeStationTaskList(Integer id);

	// 机关单位中层干部进行评价的民警列表(局领导、派出所负责人)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList1(Integer id, Integer positionId);

	// 机关单位中层干部进行评价的民警列表(机关单位中层干部、机关单位民警)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList2(Integer id, Integer isCadre, Integer positionId);

	// 机关单位中层干部进行评价的民警列表(机关单位负责人)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList3(Integer id, Integer isCadre, Integer positionId);

	// 局领导和机关单位负责人对派出所中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList1(Integer id, Integer positionId);

	// 派出所中层干部和民警对派出所本部中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList2(Integer id, Integer isCadre, Integer positionId);

	// 派出所负责人对派出所本部中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList3(Integer id, Integer isCadre, Integer positionId);

	// 可对机关单位全部民警进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> officePoliceTaskList(Integer id);

	// 可对派出所全部民警进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> policemanTaskList(Integer id);

	// 可对评议单位进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> commentsTaskList(Integer id);

	/**
	 * 获取任务数据列表
	 */
	List<EvaluateTaskParticipant> getTaskDataList(@Param("taskId") Integer taskId, @Param("pageSize") Integer pageSize);

	/**
	 * 获得人物列表总数
	 */
	Integer getTaskDataListSum(Integer taskId);

	// 查询评价得分列表
	List<EvaluateTaskParticipant> evaluationRankList(Integer taskId, Integer pageSize);

	// 查询评价得分列表数量
	int evaluationRankListCount(Integer taskId);

	// 查询个人/单位/项目具体排名和分数（后台）
	EvaluateTaskParticipant evaluationRankingItem(String objectId, Integer taskId);

	// 投票分类数量统计（后台）
	List<EvaluateTaskParticipant> evaluationVoteList(String objectId, Integer taskId, Integer participantId);

	// 查询当前任务id中的等级类型（后台）
	List<EvaluateTaskParticipant> voteTypeList(Integer taskId);

	// 查询当前任务参与人id（后台）
	List<EvaluateTaskParticipant> participantTypeList(Integer taskId);

	// 已评价详情-投票信息统计(api)
	Vote VoteItem(Integer taskId);

	// 投票类型分类统计(api)
	List<EvaluateRankDetail> voteDetailTypeList(Integer policeId, Integer taskId);

	// 规则详情查询(api)
	EvaluateRankDetail evaluateRankDetailItem(Integer id);

	// 根据rankId和规则名称查询(api)
	EvaluateRankDetail rankDetailItem(Integer rankId, String detailName);

	// 判断当前模板是否被任务引用(后台)
	List<EvaluateTask> existTemplateList(Integer templateId);

	// 判断当前等次是否被模板引用(后台)
	List<EvaluateTemplate> existRankList(Integer rankId);

	// 判断当前等次详情是否被评价详情列表数据引用(后台)
	List<EvaluateTaskParticipant> existTaskParticipantList(Integer rankDetailId);

	// 评价对象超过100%验证
	EvaluateAuthorityTarget moreOneHundredItem(Integer targetId);

	// 根据参与人类别统计各项投票(后台)
	List<ParticipantVote> participantVoteList(Integer taskId, String objectId);

	// 评价对象临时表批量新增(api)
	void tempBatch(List<EvaluateTaskParticipantTemp> tempList);

	// 查询是否有超过最大数的规则类型（api）
	EvaluateTaskParticipantTemp participantTempItem(String policeId, Integer taskId);

	// 删除临时表全部数据
	int tempDeleteAll(String policeId, Integer taskId);

	/**
	 * 任务推送
	 */
	List<EvaluateTaskParticipant> taskSend();

	// 查询任务是否需要审批(后台)
	EvaluateTaskParticipant operationStatusItem(Integer taskId);

	// 查询任务是否已经完成(后台)
	List<EvaluateTask> taskOversList(Integer taskId);

	// 查询任务为项目时项目所属单位
	EvaluateTaskParticipant projectDepNameItem(Integer id);

	/**
	 * 获得所有的评价对象
	 * 
	 * @return
	 */
	List<EvaluateTaskParticipant> getAllObject(@Param("taskId") Integer taskId);

	/**
	 * 查询所有评价参与人
	 * 
	 * @param taskId
	 * @return
	 */
	List<EvaluateTaskParticipant> getAllParticipants(@Param("taskId") Integer taskId);

	/**
	 * 根据评价参与人名及任务ID筛选
	 * 
	 * @param taskId
	 * @param objectName
	 * @param pageSize
	 * @return
	 */
	List<EvaluateTaskParticipant> taskDataListBySelectParticipants(@Param("taskId") Integer taskId,
			@Param("objectName") String objectName, @Param("pageSize") Integer pageSize,
			@Param("author") String author);

	/**
	 * 根据对象名及任务ID筛选
	 * 
	 * @param taskId
	 * @param objectName
	 * @param pageSize
	 * @return
	 */
	List<EvaluateTaskParticipant> taskDataListByObjName(@Param("taskId") Integer taskId,
			@Param("objectName") String objectName, @Param("pageSize") Integer pageSize,
			@Param("author") String author);

	/**
	 * 查询评价得分的所有对象名字
	 * 
	 * @return
	 */
	List<EvaluateTaskParticipant> evaluationRankListObjName(@Param("taskId") Integer taskId);

	/**
	 * 根据评价ID 名称 查询评价得分
	 * 
	 * @param taskId
	 * @param objectName
	 * @param pageSize
	 * @return
	 */
	List<EvaluateTaskParticipant> evaluationRankListByObjName(@Param("taskId") Integer taskId,
			@Param("objectName") String objectName, @Param("pageSize") Integer pageSize);

	// 名次排名excel导出
	List<EvaluateTaskParticipant> rankSaveExcelList(Integer taskId, String objectName, Integer departmentId);

	// 任务参与人名称查询
	List<EvaluateTaskParticipant> participantNameList(Integer taskId);

	// 个人评分详细数据查询
	List<ParticipantVote> rankItemList(Integer taskId, String objectId);

	// 查询项目所属部门
	EvaluateProject evaluateProjectItem(String objectId);

	/**
	 * 本周评价任务数
	 * 
	 * @return
	 */
	Integer taskNumWeek();

	/**
	 * 最新5条评价任务
	 * 
	 * @return
	 */
	List<EvaluateTask> newTaskTopFive();

	/**
	 * 评价类型占比
	 * 
	 * @param type     任务类型（1个人2单位3项目）
	 * @param dateType 1月度2季度3年度
	 * @return
	 */
	Double typeRator(@Param("type") Integer type, @Param("dateType") Integer dateType);

}
