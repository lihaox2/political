package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.EvaluateTaskParticipant;
import com.bayee.political.domain.ParticipantVote;
import com.bayee.political.domain.Vote;

public interface EvaluateTaskParticipantMapper {

	int deleteByPrimaryKey(Integer id);

	// 评价参与人新增(api)
	int evaluateTaskParticipantCreat(EvaluateTaskParticipant evaluateTaskParticipant);

	// 评价参与人批量新增(api)
	void evaluateTaskParticipantBatch(List<EvaluateTaskParticipant> evaluateTaskParticipantList);

	// 评价参与人修改(api)
	int evaluateTaskParticipantUpdate(EvaluateTaskParticipant evaluateTaskParticipant);

	// 根据评价id删除评价参与人（后台）
	int evaluateTaskParticipantDalete(@Param("taskId") Integer taskId);

	// 根据评价id计算评价任务总条数(api)
	int evaluateTaskParticipantTotal(@Param("taskId") Integer taskId);

	// 根据评价id计算评价任务已评价条数(api)
	int evaluateTaskParticipantOver(@Param("taskId") Integer taskId);

	// 评价对象查询(api)
	List<EvaluateTaskParticipant> objectList(@Param("policeId") String policeId, @Param("taskId") Integer taskId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 项目单位
	List<EvaluateTaskParticipant> projectList(@Param("policeId") String policeId, @Param("taskId") Integer taskId,
			@Param("sort") String sort);

	// 可对项目进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> projectTaskList(@Param("id") Integer id);

	// 可对项目进行评价的单位负责人列表(后台)
	List<EvaluateTaskParticipant> projectUnitLeaderTaskList(@Param("id") Integer id);

	// 可对机关单位进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> officeTaskList(@Param("id") Integer id);

	// 可对派出所进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> policeStationTaskList(@Param("id") Integer id);

	// 机关单位中层干部进行评价的民警列表(局领导、派出所负责人)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList1(@Param("id") Integer id,
			@Param("positionId") Integer positionId);

	// 机关单位中层干部进行评价的民警列表(机关单位中层干部、机关单位民警)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList2(@Param("id") Integer id, @Param("isCadre") Integer isCadre,
			@Param("positionId") Integer positionId);

	// 机关单位中层干部进行评价的民警列表(机关单位负责人)(后台)
	List<EvaluateTaskParticipant> officeLeaderTaskList3(@Param("id") Integer id, @Param("isCadre") Integer isCadre,
			@Param("positionId") Integer positionId);

	// 局领导和机关单位负责人对派出所中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList1(@Param("id") Integer id,
			@Param("positionId") Integer positionId);

	// 派出所中层干部和民警对派出所本部中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList2(@Param("id") Integer id, @Param("isCadre") Integer isCadre,
			@Param("positionId") Integer positionId);

	// 派出所负责人对派出所本部中层干部评价(后台)
	List<EvaluateTaskParticipant> policeLeaderTaskList3(@Param("id") Integer id, @Param("isCadre") Integer isCadre,
			@Param("positionId") Integer positionId);

	// 可对机关单位全部民警进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> officePoliceTaskList(@Param("id") Integer id);

	// 可对派出所全部民警进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> policemanTaskList(@Param("id") Integer id);

	// 可对评议单位进行评价的民警列表(后台)
	List<EvaluateTaskParticipant> commentsTaskList(@Param("id") Integer id);

	/**
	 * 获取任务数据列表
	 */
	List<EvaluateTaskParticipant> getTaskDataList(@Param("taskId") Integer taskId, @Param("pageSize") Integer pageSize);

	/**
	 * 获得人物列表总数
	 */
	Integer getTaskDataListSum(@Param("taskId") Integer taskId);

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

	// 查询评价得分
	List<EvaluateTaskParticipant> evaluationRankList(@Param("taskId") Integer taskId,
			@Param("pageSize") Integer pageSize);

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

	// 查询评价得分列表数量
	int evaluationRankListCount(@Param("taskId") Integer taskId);

	// 查询个人/单位/项目具体排名和分数（后台）
	EvaluateTaskParticipant evaluationRankingItem(@Param("objectId") String objectId, @Param("taskId") Integer taskId);

	// 投票分类数量统计（后台）
	List<EvaluateTaskParticipant> evaluationVoteList(@Param("objectId") String objectId,
			@Param("taskId") Integer taskId, @Param("participantId") Integer participantId);

	// 查询当前任务id中的等级类型（后台）
	List<EvaluateTaskParticipant> voteTypeList(@Param("taskId") Integer taskId);

	// 查询当前任务参与人id（后台）
	List<EvaluateTaskParticipant> participantTypeList(@Param("taskId") Integer taskId);

	// 已评价详情-投票信息统计(api)
	Vote VoteItem(@Param("taskId") Integer taskId);

	// 最新差评查询(后台)(2条)
	List<EvaluateTaskParticipant> homeEvaluationCheckList();

	// 判断当前等次详情是否被评价详情列表数据引用(后台)
	List<EvaluateTaskParticipant> existTaskParticipantList(@Param("rankDetailId") Integer rankDetailId);

	// 根据参与人类别统计各项投票(后台)
	List<ParticipantVote> participantVoteList(@Param("taskId") Integer taskId, @Param("objectId") String objectId);

	/**
	 * 任务推送
	 */
	List<EvaluateTaskParticipant> taskSend();

	// 查询任务是否需要审批(后台)
	EvaluateTaskParticipant operationStatusItem(@Param("taskId") Integer taskId);

	// 查询任务是否已经完成(后台)
	List<EvaluateTask> taskOversList(@Param("taskId") Integer taskId);

	// 查询任务为项目时项目所属单位
	EvaluateTaskParticipant projectDepNameItem(@Param("id") Integer id);

	// 名次排名excel导出
	List<EvaluateTaskParticipant> rankSaveExcelList(@Param("taskId") Integer taskId,
			@Param("objectName") String objectName, @Param("departmentId") Integer departmentId);

	// 任务参与人名称查询
	List<EvaluateTaskParticipant> participantNameList(@Param("taskId") Integer taskId);

	// 个人评分详细数据查询
	List<ParticipantVote> rankItemList(@Param("taskId") Integer taskId, @Param("objectId") String objectId);
}