package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.MatchAchievementStatistics;
import com.bayee.political.domain.MatchAchievementTotalList;
import com.bayee.political.domain.MatchDepAchievementStatistics;
import com.bayee.political.domain.MatchSubmit;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMatchPersonalResult;
import com.bayee.political.domain.TrainPersonalAchievementStatistics;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;

public interface TrainMatchAchievementMapper {

	// 个人赛事成绩新增
	int matchAchievementCreat(TrainMatchAchievement record);

	// 个人赛事成绩修改
	int matchAchievementUpdate(TrainMatchAchievement record);

	/**
	 * 修改枪械报名根据train_match_id及police_id
	 * 
	 * @param record
	 * @return
	 */
	int matchAchievementUpdateExport(TrainMatchAchievement record);

	// 个人赛事详情查询
	TrainMatchAchievement matchAchievementItem(@Param("id") Integer id, @Param("trainMatchId") Integer trainMatchId,
			@Param("policeId") String policeId);

	// 个人赛事报名成功查询
	TrainMatchAchievement matchPersonalSignUpSuccessItem(@Param("trainMatchId") Integer trainMatchId,
			@Param("policeId") String policeId);

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
	 * 赛事成绩模板
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	List<TrainAchievementTemplate> getMatchTrainAchievementTemplate(@Param("trainFirearmId") Integer trainFirearmId);
	
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

	// 已签到人数统计
	TrainRecordPolice matchRecordPoliceItem(@Param("trainMatchId") Integer trainMatchId);

	// 赛事人员成绩查询
	List<TrainRank> matchRecordPoliceScoreList(@Param("trainMatchId") Integer trainMatchId);

	// 查询当前项目已经报名人数
	int alreadySignUpPoliceNum(@Param("trainMatchId") Integer trainMatchId, @Param("sex") Integer sex);

	// 比赛领队排行榜查询
	List<TrainRank> matchLeaderRankList(@Param("trainMatchId") Integer trainMatchId,
			@Param("limitNum") Integer limitNum);

	// 领队推荐人员查询
	List<TrainRecommendPolice> matchRecommendPoliceList(@Param("departmentId") Integer departmentId,
			@Param("type") Integer type);

	// 部门参加人员信息查询
	List<TrainRecommendPolice> matchDepPoliceList(@Param("departmentId") Integer departmentId,
			@Param("type") Integer type, @Param("keywords") String keywords);

	// 分局赛事录分提交页面查询
	List<MatchSubmit> matchOfficeSubmitScoreList(@Param("trainMatchId") Integer trainMatchId);

	// 领队比赛人员成功查询
	List<TrainRecommendPolice> matchLeaderSignUpSuccessList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId);

	// 个人成绩统计查询
	TrainPersonalAchievementStatistics trainPersonalAchievementItem(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime);

	// 最近一次综合训练成绩查询
	TrainPersonalAchievementStatistics trainPhysicallAchievementItem(@Param("policeId") String policeId);

	// 最近一次枪械成绩查询
	TrainPersonalAchievementStatistics trainFirearmAchievementItem(@Param("policeId") String policeId);

	// 个人赛事成绩综合查询
	TrainMatchPersonalResult matchPersonalResultItem(@Param("policeId") String policeId);

	// 个人赛事排名查询
	TrainRank matchPersonalRankItem(@Param("trainMatchId") Integer trainMatchId, @Param("policeId") String policeId,
			@Param("sortStr") String sortStr);

	// 个人分局赛事排名查询
	TrainRank matchDepRankItem(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId);

	// 查询当前赛事已经有成绩的人
	List<TrainMatchAchievement> trainMatchAlreadyAchievementList(@Param("trainMatchId") Integer trainMatchId);

	// 赛事排名
	List<TrainRank> matchMyEntryRankList(@Param("trainMatchId") Integer trainMatchId, @Param("sortStr") String sortStr);

	// 赛事个人排名
	TrainRank matchMyEntryRankItem(@Param("trainMatchId") Integer trainMatchId, @Param("policeId") String policeId,
			@Param("sortStr") String sortStr);

	// 赛事更多榜单排名榜查询
	List<TrainRank> matchMyEntryMoreRankList(@Param("trainMatchId") Integer trainMatchId,
			@Param("sortStr") String sortStr);

	// 领队部门人员比赛成绩查询
	List<TrainRecommendPolice> matchLeaderDepAchievementList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId, @Param("isSign") Integer isSign);

	// 赛事领队报名人员查询
	List<TrainMatchAchievement> matchApplicantsLeaderList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId);

	// 赛事成绩统计查询
	MatchAchievementStatistics matchAchievementStatisticsItem(@Param("policeId") String policeId,
			@Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 单位/分局赛事成绩查询
	MatchDepAchievementStatistics matchDepAchievementStatisticsItem(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 赛事成绩记录查询
	List<MatchAchievementTotalList> matchAchievementTotalList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 赛事报名人头像查询
	List<CalculationChart> matchHeadImageList(@Param("trainMatchId") Integer trainMatchId,
			@Param("policeId") String policeId, @Param("num") Integer num);

	// 赛事报名当前用户头像查询
	CalculationChart matchHeadImageItem(@Param("trainMatchId") Integer trainMatchId,
			@Param("policeId") String policeId);

	// 领队自己部门比赛成绩排行榜
	List<TrainRank> matchLeaderDepPoliceRankList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId, @Param("sortStr") String sortStr);

	// 查询本单位是否已经完成报名
	List<TrainMatchAchievement> registrationLompletedList(@Param("trainMatchId") Integer trainMatchId,
			@Param("departmentId") Integer departmentId);

}