package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.MatchRecordScore;
import com.bayee.political.domain.TrainMatch;

public interface TrainMatchMapper {

	/**
	 * 删除赛事
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMatch(Integer id);

	// 赛事新增
	int trainMatchCreat(TrainMatch record);

	// 赛事修改
	int trainMatchUpdate(TrainMatch record);

	/**
	 * 赛事修改(特殊)
	 * 
	 * @param trainMatch
	 * @return
	 */
	int trainMatchUpdateSpecial(TrainMatch trainMatch);

	/**
	 * 获得赛事数据
	 * 
	 * @param type                  赛事类型
	 * @param status                赛事状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate训练结束时间
	 * @param keyWords              关键字
	 * @param pageSize              分页大小
	 * @return
	 */
	List<TrainMatch> getTrainMatchList(@Param("departmentId") Integer departmentId, @Param("type") Integer type,
			@Param("status") Integer status, @Param("registrationStartDate") String registrationStartDate,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获得赛事数据数量
	 * 
	 * @param type                  赛事类型
	 * @param status                赛事状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate训练结束时间
	 * @param keyWords              关键字
	 * @return
	 */
	int getTrainMatchCount(@Param("departmentId") Integer departmentId, @Param("type") Integer type,
			@Param("status") Integer status, @Param("registrationStartDate") String registrationStartDate,
			@Param("registrationEndDate") String registrationEndDate, @Param("trainStartDate") String trainStartDate,
			@Param("trainEndDate") String trainEndDate, @Param("keyWords") String keyWords);

	// 赛事列表查询
	List<TrainMatch> matchList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("departmentId") Integer departmentId);

	// 赛事总数统计
	int matchListCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("departmentId") Integer departmentId);

	/**
	 * 立即开始
	 * 
	 * @param id
	 * @return
	 */
	int startTrainMatch(@Param("id") Integer id);

	/**
	 * 立即结束
	 * 
	 * @param id
	 * @return
	 */
	int endTrainMatch(@Param("id") Integer id);

	/**
	 * 重启任务
	 * 
	 * @param id
	 * @return
	 */
	int reStartTrainMatch(@Param("id") Integer id, @Param("time") String time);

	// 我的赛事列表查询
	List<TrainMatch> myMatchList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("status") Integer status, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 我的赛事总数统计
	int myMatchListCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("status") Integer status);

	// 赛事详情查询
	TrainMatch matchItem(@Param("id") Integer id);

	// 赛事未录分数量
	int matchNoScoreNum(@Param("policeId") String policeId);

	// 需要录分的赛事活动查询
	List<MatchRecordScore> matchRecordScoreList(@Param("policeId") String policeId);

	/**
	 * 赛事详情查询
	 * 
	 * @param id
	 * @return
	 */
	TrainMatch getTrainMatchById(@Param("id") Integer id);

	// 领队比赛列表查询
	List<TrainMatch> matchLeaderPageList(@Param("policeId") String policeId,
			@Param("signUpStatus") Integer signUpStatus, @Param("status") Integer status,
			@Param("departmentId") Integer departmentId, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 领队比赛列表数量统计
	int matchLeaderPageCount(@Param("policeId") String policeId, @Param("signUpStatus") Integer signUpStatus,
			@Param("status") Integer status, @Param("departmentId") Integer departmentId);

	// 查询分局赛事
	List<TrainMatch> trainOfficeMatchList();

	// 本周赛事查询
	List<TrainMatch> matchWeekList(@Param("policeId") String policeId, @Param("departmentId") Integer departmentId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 即将比赛赛事查询
	List<TrainMatch> matchSoonList(@Param("policeId") String policeId, @Param("departmentId") Integer departmentId);

	// 比赛列表查询（定时任务修改约谈状态进程）
	List<TrainMatch> matchStatuslList();

	// 个人已经参加赛事查询
	List<TrainMatch> matchPersonalAlreadyEnterList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("matchTypeId") Integer matchTypeId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 进行中的赛事查询
	List<TrainMatch> matchInProgressList(@Param("policeId") String policeId, @Param("status") Integer status,
			@Param("sort") String sort);

	// 判断当前用户是否可扫码当前人员
	TrainMatch matchScorerPoliceItem(@Param("id") Integer id, @Param("scorerPoliceId") String scorerPoliceId);

	// 赛事活动已完成录分查询
	List<MatchRecordScore> matchRecordOverScoreList(@Param("policeId") String policeId);

	/**
	 * 根据matchProjectId查询赛事
	 * 
	 * @param matchProjectId
	 * @return
	 */
	List<TrainMatch> getTrainMatchByMatchProjectId(@Param("matchProjectId") Integer matchProjectId);

	/**
	 * 根据trainProjectId查询赛事
	 * 
	 * @param trainProjectId
	 * @return
	 */
	List<TrainMatch> getTrainMatchByTrainProjectId(@Param("trainProjectId") Integer trainProjectId);

	// 最新赛事详情查询
	TrainMatch screenMatchNewest();

}