package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.json.TrainIsEntryResult;
import com.bayee.political.json.TrainRankingResult;
import com.bayee.political.pojo.dto.RiskPhysicalTrainingRecordDO;
import com.bayee.political.pojo.dto.TrainPhysicalPoliceDetailsResultDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskTrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainAchievementStatistics;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainAchievementTotalList;
import com.bayee.political.domain.TrainDepAchievementStatistics;
import com.bayee.political.domain.TrainLeaderAchievement;
import com.bayee.political.domain.TrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;

public interface TrainPhysicalAchievementDetailsMapper {

	// 参训成绩新增
	int trainPhysicalAchievementDetailsCreat(TrainPhysicalAchievementDetails record);

	// 参训成绩修改
	int trainPhysicalAchievementDetailsUpdate(TrainPhysicalAchievementDetails record);

	/**
	 * 参训成绩修改根据train_firearm_id及police_id及project_id
	 * 
	 * @param record
	 * @return
	 */
	int trainPhysicalAchievementDetailsUpdateCondition(TrainPhysicalAchievementDetails record);

	// 我的参赛记录查询
	List<TrainPhysicalAchievementDetails> trainMyEntryRecordList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId,
			@Param("policeId") String policeId);

	// 根据年龄组和训练项目排名
	List<TrainRank> trainPersonalRankList(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 根据年龄组和训练项目查询个人具体排名
	TrainRank trainPersonalRankItem(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 根据年龄组和枪械项目排名
	List<TrainRank> trainPersonalFirearmRankList(@Param("projectId") Integer projectId,
			@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 根据年龄组和枪械项目查询个人具体排名
	TrainRank trainPersonalFirearmRankItem(@Param("projectId") Integer projectId,
			@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 根据年龄组和训练项目排名(不限制人数)
	List<TrainRank> trainPersonalMoreRankList(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 根据年龄组和枪械项目排名(不限制人数)
	List<TrainRank> trainPersonalFirearmMoreRankList(@Param("projectId") Integer projectId,
			@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId, @Param("sort") String sort);

	// 已录人数统计
	TrainRecordPolice trainRecordPoliceItem(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId);

	// 训练人员成绩查询
	List<TrainRank> trainRecordPoliceScoreList(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId);

	// 已录枪械人数统计
	TrainRecordPolice trainRecordFirearmPoliceItem(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId);

	// 枪械训练人员成绩查询
	List<TrainRank> trainRecordFirearmPoliceScoreList(@Param("projectId") Integer projectId,
			@Param("trainPhysicalId") Integer trainPhysicalId);

	// 警号详情id项目id修改综合体能成绩
	int trainDetailsPoliceUpdate(TrainPhysicalAchievementDetails item);

	/**
	 * 根据体能训练Id及训练详情id查询训练项目
	 * 
	 * @param trainPhysicalId
	 * @param trainPhysicalAchievementId
	 * @return
	 */
	List<TrainPhysicalAchievementDetails> getTrainPhysicalAchievementDetailsByCondition(
			@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId);

	/**
	 * 根据体能训练成绩详情id修改成绩
	 * 
	 * @param id
	 * @return
	 */
	Integer updateTrainPhysicalAchievementDetailsById(@Param("id") Integer id,
			@Param("achievement") Double achievement);

	/**
	 * 根据训练id及训练详情id获得某警员的项目及成绩
	 * 
	 * @param trainPhysicalId
	 * @param trainPhysicalAchievementId
	 * @return
	 */
	List<TrainPhysicalAchievementDetails> getAchievementDetailsProjectByCondition(
			@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId);

	/**
	 * 查询训练计划下某警员的某个项目所在组别中的分数排名
	 * 
	 * @param trainPhysicalId
	 * @param trainGroupId
	 * @param projectId
	 * @param policeId
	 * @param sort            1越大越好2越小越好
	 * @return
	 */
	Integer getRank(@Param("trainPhysicalId") Integer trainPhysicalId, @Param("trainGroupId") Integer trainGroupId,
			@Param("projectId") Integer projectId, @Param("policeId") String policeId, @Param("sort") Integer sort);

	// 综合训练推荐人员信息查询
	List<TrainRecommendPolice> trainPhysicalRecommendPoliceList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId, @Param("policeId") String policeId);

	// 枪械推荐人员信息查询
	List<TrainRecommendPolice> trainFirearmRecommendPoliceList(@Param("departmentId") Integer departmentId,
			@Param("policeId") String policeId);

	// 综合训练部门参加人员信息查询
	List<TrainRecommendPolice> trainPhysicalDepPoliceList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId, @Param("keywords") String keywords);

	// 枪械部门参加人员信息查询
	List<TrainRecommendPolice> trainFirearmDepPoliceList(@Param("departmentId") Integer departmentId,
			@Param("keywords") String keywords);

	/**
	 * 查询某警员所在组别参加的项目
	 * 
	 * @param trainPhysicalId
	 * @param trainPhysicalAchievementId
	 * @return
	 */
	List<TrainPhysicalAchievementDetails> getProjectByCondition(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId);

	/**
	 * 查询某项目在该组内的成绩排行
	 * 
	 * @param trainPhysicalId
	 * @param trainGroupId
	 * @param projectId
	 * @param keyword
	 * @param pageSize
	 * @return
	 */
	List<TrainPhysicalAchievementDetails> getAchievementRank(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainGroupId") Integer trainGroupId, @Param("projectId") Integer projectId,
			@Param("sort") Integer sort, @Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询某项目在该组内的成绩排行數量
	 * 
	 * @param trainPhysicalId
	 * @param trainGroupId
	 * @param projectId
	 * @param keyword
	 * @return
	 */
	int getAchievementRankCount(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainGroupId") Integer trainGroupId, @Param("projectId") Integer projectId,
			@Param("keyword") String keyword);

	// 领队综合训练报名成功查询
	List<TrainRecommendPolice> trainPhysicalLeaderSignUpSuccessList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队枪械报名成功查询
	List<TrainRecommendPolice> trainFirearmLeaderSignUpSuccessList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队综合训练成绩查询
	TrainLeaderAchievement trainPhysicalLeaderAchievemenItem(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队枪械成绩查询
	TrainLeaderAchievement trainFirearmLeaderAchievemenItem(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队综合体能签到率排行榜查询
	List<TrainRank> trainLeaderPhysicalSignUpRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("limitNum") Integer limitNum);

	// 领队综合体能合格率排行榜查询
	List<TrainRank> trainLeaderPhysicalPassRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("limitNum") Integer limitNum);

	// 领队综合体能不合格率排行榜查询
	List<TrainRank> trainLeaderPhysicalFailRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("limitNum") Integer limitNum);

	// 领队枪械签到率排行榜查询
	List<TrainRank> trainLeaderFirearmSignUpRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("limitNum") Integer limitNum);

	// 领队枪械优秀,良好,合格率排行榜查询
	List<TrainRank> trainLeaderFirearmGoodRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("gradeType") Integer gradeType, @Param("limitNum") Integer limitNum);

	// 领队枪械不合格率排行榜查询
	List<TrainRank> trainLeaderFirearmFailRateRankList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("limitNum") Integer limitNum);

	// 查询当前用户成绩是否合格
	List<TrainPhysicalAchievementDetails> detailsFailList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId);

	// 领队综合训练已签到人员查询
	List<TrainRecommendPolice> signMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队综合训练未签到人员查询
	List<TrainRecommendPolice> noSignMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队综合训练合格人员查询
	List<TrainRecommendPolice> passMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队综合训练不合格人员查询
	List<TrainRecommendPolice> failMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队枪械已签到人员查询
	List<TrainRecommendPolice> signFirearmMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队枪械未签到人员查询
	List<TrainRecommendPolice> noSignFirearmMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 领队枪械优秀,良好,合格人员查询
	List<TrainRecommendPolice> passFirearmMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId, @Param("gradeType") Integer gradeType);

	// 领队枪械不合格人员查询
	List<TrainRecommendPolice> failFirearmMoreAchievementList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 警员参加训练次数查询
	List<TrainRecommendPolice> trainDepPoliceList(@Param("departmentId") Integer departmentId);

	// 警员参加赛事次数查询
	List<TrainRecommendPolice> matchDepPoliceList(@Param("departmentId") Integer departmentId);

	// 查询当前训练下的项目
	List<TrainPhysicalAchievementDetails> trainSignInProjectList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId, @Param("policeId") String policeId,
			@Param("isSign") Integer isSign);

	// 查询当前训练项详情
	TrainPhysicalAchievementDetails physicalDetailsItem(@Param("id") Integer id,
			@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId, @Param("policeId") String policeId,
			@Param("projectId") Integer projectId);

	/**
	 * 获取体能训练的数据导入模板
	 * 
	 * @param trainPhysicalId
	 * @return
	 */
	List<TrainAchievementTemplate> getPhysicalTrainAchievementTemplateList(
			@Param("trainPhysicalId") Integer trainPhysicalId);

	// 查询已经签到的项目
	List<TrainPhysicalAchievementDetails> isWholeSignList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId);

	// 更新成绩时间
	int achievementDateUpdate(TrainPhysicalAchievementDetails reacord);

	// 训练成绩统计查询
	TrainAchievementStatistics trainAchievementStatisticsItem(@Param("policeId") String policeId,
			@Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 单位/分局训练成绩查询
	TrainDepAchievementStatistics trainDepAchievementStatisticsItem(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 训练成绩记录查询
	List<TrainAchievementTotalList> trainAchievementTotalList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateType") Integer dateType, @Param("dateTime") String dateTime);

	// 体能项目成绩批量新增
	void trainPhysicalAchievementDetailsCreatBatch(List<TrainPhysicalAchievementDetails> creatList);

	// 体能项目成批量修改
	void trainPhysicalAchievementDetailsUpdateBatch(List<TrainPhysicalAchievementDetails> updateList);

	// 查询警员风险当前训练下的项目
	List<RiskTrainPhysicalAchievementDetails> riskTrainSignInProjectList(
			@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainPhysicalAchievementId") Integer trainPhysicalAchievementId, @Param("policeId") String policeId,
			@Param("isSign") Integer isSign);


	/**
	 * 查询警员综合训练应扣除的分数
	 * @param policeId
	 * @param date
	 * @return
	 */
	Double getPolicePhysicalDeductionScore(@Param("policeId") String policeId,@Param("date") String date);

	/**
	 * 获取警号及合格情况 并去重
	 * @return
	 */
	List<TrainIsEntryResult> getParticipantsTotal();

	/**
	 * 综合体能合格率集合
	 * @return
	 */
	List<TrainRankingResult> trainPhysicalQualifiedRate();

	/**
	 * 总体合格率集合
	 * @return
	 */
	List<TrainRankingResult> totalQualifiedRate();

	/**
	 * 查询警员训练
	 * @param physicalAchievementId
	 * @return
	 */
	List<TrainPhysicalPoliceDetailsResultDO> findPoliceAchievementById(@Param("physicalAchievementId") Integer physicalAchievementId);

	/**
	 * 删除综合训练成绩
	 * @param physicalId
	 */
	void deleteTrainPhysicalAchievementDetailsByPhysicalId(@Param("physicalId") Integer physicalId);

	/**
	 * 根据年份和月份进行查询
	 * @return
	 */
	List<RiskPhysicalTrainingRecordDO> physicalTrainingRecordCareerQuery(Integer physicalAchievementId);
}