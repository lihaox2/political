package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.MatchRecordScore;
import com.bayee.political.domain.MatchSubmit;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainEvaluateRecord;
import com.bayee.political.domain.TrainGetMedal;
import com.bayee.political.domain.TrainLikeRecord;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMatchBestAchievement;
import com.bayee.political.domain.TrainMatchDepartmentAchievement;
import com.bayee.political.domain.TrainMatchPersonalResult;
import com.bayee.political.domain.TrainMatchProject;
import com.bayee.political.domain.TrainMatchType;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainMedalPerson;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPersonalAchievementStatistics;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;

/**
 * @author shentuqiwei
 * @version 2020年10月25日 下午4:44:15
 */
@Service
public interface TrainMatchService {

	// 赛事新增
	int trainMatchCreat(TrainMatch record);

	// 赛事修改
	int trainMatchUpdate(TrainMatch record);

	// 赛事列表查询
	List<TrainMatch> matchList(String policeId, Integer type, Integer departmentId);

	// 赛事总数统计
	int matchListCount(String policeId, Integer type, Integer departmentId);

	// 我的赛事列表查询
	List<TrainMatch> myMatchList(String policeId, Integer type, Integer status, Integer pageSize, Integer pageNum);

	// 我的赛事总数统计
	int myMatchListCount(String policeId, Integer type, Integer status);

	// 赛事详情查询
	TrainMatch matchItem(Integer id);

	/**
	 * 查询赛事类型
	 * 
	 * @return
	 */
	List<TrainMatchType> getTrainMatchTypeList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询赛事类型
	 * 
	 * @return
	 */
	int getTrainMatchTypeCount(@Param("keyword") String keyword);

	/**
	 * 新增赛事类型
	 * 
	 * @param trainMatchType
	 * @return
	 */
	int addTrainMatchType(TrainMatchType trainMatchType);

	/**
	 * 修改赛事类型
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainMatchType(TrainMatchType trainMatchType);

	/**
	 * 删除赛事类型
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMatchType(Integer id);

	/**
	 * 获得比赛项目列表
	 * 
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	List<TrainMatchProject> getTrainMatchProjectList(@Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获得比赛项目列表数量
	 * 
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	int getTrainMatchProjectCount(@Param("keyword") String keyword);

	/**
	 * 删除比赛项目
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMatchProject(Integer id);

	/**
	 * 添加比赛项目
	 * 
	 * @param record
	 * @return
	 */
	int addTrainMatchProject(TrainMatchProject trainMatchProject);

	/**
	 * 修改比赛项目
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainMatchProject(TrainMatchProject trainMatchProject);

	/**
	 * 比赛项目详情
	 * 
	 * @param id
	 * @return
	 */
	TrainMatchProject selectTrainMatchProjectById(Integer id);

	/**
	 * 赛事类型详情
	 * 
	 * @param id
	 * @return
	 */
	TrainMatchType selectTrainMatchTypeById(Integer id);

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

	/**
	 * 删除赛事
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMatch(Integer id);

	/**
	 * 根据赛事类型type查询该类型下的项目
	 * 
	 * @param type
	 * @return
	 */
	List<TrainMatchProject> getTrainMatchProjectByType(@Param("type") Integer type);

	/**
	 * 查询赛事成绩
	 * 
	 * @param trainMatchId
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	List<TrainMatchAchievement> getTrainMatchAchievementList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);

	/**
	 * 查询赛事成绩数量
	 * 
	 * @param trainMatchId
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	int getTrainMatchAchievementCount(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

	/**
	 * 赛事详情查询
	 * 
	 * @param id
	 * @return
	 */
	TrainMatch getTrainMatchById(@Param("id") Integer id);

	/**
	 * 赛事修改(特殊)
	 * 
	 * @param trainMatch
	 * @return
	 */
	int trainMatchUpdateSpecial(TrainMatch trainMatch);

	/**
	 * 根据matchProjectId查询赛事
	 * 
	 * @param matchProjectId
	 * @return
	 */
	List<TrainMatch> getTrainMatchByMatchProjectId(@Param("matchProjectId") Integer matchProjectId);
	
	/**
	 * 根据成绩Id查询成绩对应的项目对应的单位名称
	 * @param achievementId
	 * @return
	 */
	String getUnitNameByAchievementId(@Param("achievementId") Integer achievementId);
	
	/**
	 * 根据id查询成绩详情
	 * @param id
	 * @return
	 */
	TrainMatchAchievement getTrainMatchAchievementById(@Param("id") Integer id);

	// 个人赛事报名成功查询
	TrainMatchAchievement matchPersonalSignUpSuccessItem(Integer trainMatchId, String policeId);

	// 个人赛事成绩新增
	int matchAchievementCreat(TrainMatchAchievement record);

	// 个人赛事成绩修改
	int matchAchievementUpdate(TrainMatchAchievement record);

	// 个人赛事详情查询
	TrainMatchAchievement matchAchievementItem(Integer id, Integer trainMatchId, String policeId);

	// 赛事未录分数量
	int matchNoScoreNum(String policeId);

	// 需要录分的赛事活动查询
	List<MatchRecordScore> matchRecordScoreList(String policeId);

	// 已签到人数统计
	TrainRecordPolice matchRecordPoliceItem(Integer trainMatchId);

	// 赛事人员成绩查询
	List<TrainRank> matchRecordPoliceScoreList(Integer trainMatchId);

	// 已签到部门统计
	TrainRecordPolice matchRecordDepartmentItem(Integer trainMatchId);

	// 赛事部门成绩查询
	List<TrainRank> matchRecordDepartmentScoreList(Integer trainMatchId);

	// 赛事部门成绩新增
	int matchDepartmentAchievementCreat(TrainMatchDepartmentAchievement record);

	// 赛事部门成绩修改
	int matchDepartmentAchievementUpdate(TrainMatchDepartmentAchievement record);

	// 部门赛事详情查询
	TrainMatchDepartmentAchievement trainMatchDepartmentAchievementItem(Integer id, Integer trainMatchId,
			Integer departmentId);

	// 领队比赛列表查询
	List<TrainMatch> matchLeaderPageList(String policeId, Integer signUpStatus, Integer status, Integer departmentId,
			Integer pageSize, Integer pageNum);

	// 领队比赛列表数量统计
	int matchLeaderPageCount(String policeId, Integer signUpStatus, Integer status, Integer departmentId);

	// 查询当前项目已经报名人数
	int alreadySignUpPoliceNum(Integer trainMatchId, Integer sex);

	// 比赛领队排行榜查询
	List<TrainRank> matchLeaderRankList(Integer trainMatchId, Integer limitNum);

	// 领队推荐人员查询
	List<TrainRecommendPolice> matchRecommendPoliceList(Integer departmentId, Integer type);

	// 部门参加人员信息查询
	List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId, Integer type, String keywords);

	// 分局赛事录分提交页面查询
	List<MatchSubmit> matchOfficeSubmitScoreList(Integer trainMatchId);

	// 查询分局赛事
	List<TrainMatch> trainOfficeMatchList();

	// 本周赛事查询
	List<TrainMatch> matchWeekList(String policeId, Integer departmentId, String startTime, String endTime);

	// 即将比赛赛事查询
	List<TrainMatch> matchSoonList(String policeId, Integer departmentId);

	// 推荐查询
	List<TrainActivityStyle> recommendList(Integer limitNum);

	// 活动风采查询
	List<TrainActivityStyle> activityStyleList();

	// 训练标兵查询
	List<TrainActivityStyle> pacesetterList();

	// 训练章程查询
	List<TrainActivityStyle> constitutionList();

	// 活动风采详情查询
	TrainActivityStyle activityStyleItem(Integer id);

	// 训练标兵详情查询
	TrainActivityStyle pacesetterItem(Integer id);

	// 训练章程详情查询
	TrainActivityStyle constitutionItem(Integer id);

	// 评论删除
	int trainEvaluateRecordDelete(Integer id);

	// 评论新增
	int trainEvaluateRecordCreat(TrainEvaluateRecord record);

	// 评论修改
	int trainEvaluateRecordUpdate(TrainEvaluateRecord record);

	// 评论详情查询
	TrainEvaluateRecord trainEvaluateRecordItem(Integer id, Integer objectId, Integer activityId, String policeId);

	// 评论列表查询
	List<TrainEvaluateRecord> trainEvaluateRecordList(Integer objectId, Integer activityId, String policeId);

	// 点赞删除
	int trainLikeRecordDelete(Integer objectId, Integer activityId, String policeId);

	// 点赞新增
	int trainLikeRecordCreat(TrainLikeRecord record);

	// 点赞修改
	int trainLikeRecordUpdate(TrainLikeRecord record);

	// 点赞详情查询
	TrainLikeRecord trainLikeRecordItem(Integer id, Integer objectId, Integer activityId, String policeId);

	// 点赞列表查询
	List<TrainLikeRecord> trainLikeRecordList(Integer objectId, Integer activityId, String policeId);

	// 修改活动风采
	int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle);

	// 修改训练标兵
	int updateTrainPacesetter(TrainPacesetter trainPacesetter);

	// 修改训练标兵
	int updateTrainConstitution(TrainConstitution trainConstitution);

	// 个人奖章详情查询
	TrainMedalPerson medalPersonalItem(String policeId);

	// 个人获得训练奖章/赛事奖章查询
	List<TrainMedalManage> trainGetMedalPersonalList(String policeId, Integer type);

	// 个人训练奖章/赛事奖章详情查询
	TrainMedalManage trainGetMedalPersonalItem(String policeId, Integer type, Integer medalManageId);

	// 个人奖章墙查询
	List<TrainMedalManage> trainMedalPersonalWallList(String policeId, Integer type);

	// 比赛列表查询（定时任务修改约谈状态进程）
	List<TrainMatch> matchStatuslList();

	// 警员奖章详情记录查询
	TrainGetMedal trainGetMedalPoliceItem(Integer id, String policeId, Integer medalManageId);

	// 警员奖章新增
	int trainGetMedalCreat(TrainGetMedal record);

	// 警员奖章修改
	int trainGetMedalUpdate(TrainGetMedal record);

	// 警员奖章删除
	int trainGetMedalDelete(Integer id);

	// 领队比赛人员成功查询
	List<TrainRecommendPolice> matchLeaderSignUpSuccessList(Integer trainMatchId, Integer departmentId);

	// 个人成绩统计查询
	TrainPersonalAchievementStatistics trainPersonalAchievementItem(String policeId, Integer type, String dateTime);

	// 最近一次综合训练成绩查询
	TrainPersonalAchievementStatistics trainPhysicallAchievementItem(String policeId);

	// 最近一次枪械成绩查询
	TrainPersonalAchievementStatistics trainFirearmAchievementItem(String policeId);

	// 个人赛事类型查询
	List<TrainMatchType> matchPersonalTypeList(String policeId);

	// 查询奖章数量
	List<TrainMedalManage> getMedalNumList();

	// 修改奖章数量
	int updateTrainMedal(TrainMedalManage item);

	// 个人赛事成绩综合查询
	TrainMatchPersonalResult matchPersonalResultItem(String policeId);

	// 个人已经参加赛事查询
	List<TrainMatch> matchPersonalAlreadyEnterList(String policeId, Integer type, Integer matchTypeId, String startTime,
			String endTime);

	// 个人赛事排名查询
	TrainRank matchPersonalRankItem(Integer trainMatchId, String policeId, String sortStr);

	// 个人分局赛事排名查询
	TrainRank matchDepRankItem(Integer trainMatchId, Integer departmentId);

	// 个人赛事最好成绩新增
	int matchBestAchievementCreat(TrainMatchBestAchievement record);

	// 个人赛事最好成绩修改
	int matchBestAchievementUpdate(TrainMatchBestAchievement record);

	// 个人赛事最好成绩详情查询
	TrainMatchBestAchievement matchBestAchievementItem(Integer id, Integer trainMatchId, Integer type, String policeId,
			Integer departmentId);

	// 查询当前赛事已经有成绩的人
	List<TrainMatchAchievement> trainMatchAlreadyAchievementList(Integer trainMatchId);

	// 进行中的赛事查询
	List<TrainMatch> matchInProgressList(String policeId, Integer status, String sort);

	// 赛事排名
	List<TrainRank> matchMyEntryRankList(Integer trainMatchId, String sortStr);

	// 赛事个人排名
	TrainRank matchMyEntryRankItem(Integer trainMatchId, String policeId, String sortStr);

	// 赛事更多榜单排名榜查询
	List<TrainRank> matchMyEntryMoreRankList(Integer trainMatchId, String sortStr);

	// 领队部门人员比赛成绩查询
	List<TrainRecommendPolice> matchLeaderDepAchievementList(Integer trainMatchId, Integer departmentId,
			Integer isSign);

	// 赛事领队报名人员查询
	List<TrainMatchAchievement> matchApplicantsLeaderList(Integer trainMatchId, Integer departmentId);

	// 查询剩余奖牌id
	List<TrainMedalManage> surplusMedalList(Integer type, Integer medalManageId);

	// 赛事报名人头像查询
	List<CalculationChart> matchHeadImageList(Integer trainMatchId, String policeId, Integer num);

	// 赛事报名当前用户头像查询
	CalculationChart matchHeadImageItem(Integer trainMatchId, String policeId);

	// 判断当前用户是否可扫码当前人员
	TrainMatch matchScorerPoliceItem(Integer id, String scorerPoliceId);

	// 赛事活动已完成录分查询
	List<MatchRecordScore> matchRecordOverScoreList(String policeId);

	// 领队自己部门比赛成绩排行榜
	List<TrainRank> matchLeaderDepPoliceRankList(Integer trainMatchId, Integer departmentId, String sortStr);

	// 查询本单位是否已经完成报名
	List<TrainMatchAchievement> registrationLompletedList(Integer trainMatchId, Integer departmentId);
	
	/**
	 * 根据trainProjectId查询赛事
	 * @param trainProjectId
	 * @return
	 */
	List<TrainMatch> getTrainMatchByTrainProjectId(@Param("trainProjectId") Integer trainProjectId);
	
}
