package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.json.TrainLineChartResult;
import com.bayee.political.pojo.dto.TrainProjectDO;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.ReportDataFillTime;
import com.bayee.political.domain.RiskTrainPhysicalRecord;
import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.domain.TrainRecordScore;

public interface TrainPhysicalAchievementMapper {
	int deleteByPrimaryKey(Integer id);

	// 综合体能项目报名新增
	int trainPhysicalAchievementCreat(TrainPhysicalAchievement record);

	// 综合体能项目报名修改
	int trainPhysicalAchievementUpdate(TrainPhysicalAchievement record);

	/**
	 * 根据physical_id及police_id修改
	 * 
	 * @param record
	 * @return
	 */
	int trainPhysicalAchievementUpdateByCondition(TrainPhysicalAchievement record);

	// 单项综合体能项目报名人数统计
	int singleTrainPhysicalAchievementCount(@Param("trainPhysicalId") Integer trainPhysicalId);

	// 单项综合体能项目报名详情
	TrainPhysicalAchievement trainPhysicalAchievementItem(@Param("id") Integer id,
			@Param("trainPhysicalId") Integer trainPhysicalId, @Param("policeId") String policeId);

	/**
	 * 体能训练成绩查询
	 * 
	 * @param trainGroupId 组别id
	 * @param keyword      policeId或name
	 * @param pageSize     分页大小
	 * @return
	 */
	List<TrainPhysicalAchievement> getTrainPhysicalAchievement(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainGroupId") String trainGroupId, @Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 体能训练成绩数量
	 * 
	 * @param trainGroupId 组别id
	 * @param keyword      policeId或name
	 * @return
	 */
	int getTrainPhysicalAchievementCount(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("trainGroupId") String trainGroupId, @Param("departmentId") Integer departmentId,
			@Param("keyword") String keyword);

	/**
	 * 查询综合体能训练成绩根据id
	 * 
	 * @param id
	 * @return
	 */
	TrainPhysicalAchievement getTrainPhysicalAchievementById(@Param("id") Integer id);

	// 需要录分的训练查询
	List<TrainRecordScore> trainPhysicalNoScoreList(@Param("policeId") String policeId);

	// 查询任意5次训练活动各项成绩均合格
	int trainArbitrarilyFivePassCount(@Param("policeId") String policeId);

	// 查询连续5次训练活动各项成绩均合格
	int trainContinuityFivePassCount(@Param("policeId") String policeId);

	// 连续3个月参加训练，可获得该奖章
	int trainContinuityThreeMonthPassCount(@Param("policeId") String policeId);

	/**
	 * 综合体能报名人数
	 * 
	 * @param trainPhysicalId 体能训练id
	 * @return
	 */
	Integer signUpNum(@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 综合体能签到人数
	 * 
	 * @param trainPhysicalId 体能训练id
	 * @return
	 */
	Integer signInNum(@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 综合体能合格人数
	 * 
	 * @param trainPhysicalId 体能训练id
	 * @return
	 */
	Integer qualifiedNum(@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 综合体能单位报名数
	 * 
	 * @param trainPhysicalId 体能训练id
	 * @return
	 */
	Integer companyNum(@Param("trainPhysicalId") Integer trainPhysicalId);

	// 领队报名人员查询
	List<TrainPhysicalAchievement> trainApplicantsLeaderList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId, @Param("tableName") String tableName,
			@Param("tableId") String tableId);

	// 成绩起始时间查询
	ReportDataFillTime trainMatchTimeItem(@Param("tableName") String tableName);

	// 已完成录分的训练查询
	List<TrainRecordScore> trainPhysicalOverScoreList(@Param("policeId") String policeId);

	// 训练报名批量新增
	void trainPhysicalAchievementCreatBatch(List<TrainPhysicalAchievement> creatList);

	// 训练报名批量修改
	void trainPhysicalAchievementUpdateBatch(List<TrainPhysicalAchievement> updateList);

	// 根据训练id查询报名人员list
	List<TrainPhysicalAchievement> updateGradeList(@Param("trainPhysicalId") Integer trainPhysicalId);

	// 警员警务技能综合训练数据列表查询
	List<RiskTrainPhysicalRecord> riskTrainPhysicalRecordList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("timeType") Integer timeType);

	/**
	 * 查询报名记录
	 * @param physicalId
	 * @param policeId
	 * @return
	 */
	TrainPhysicalAchievement findTrainPhysicalAchievementByPolice(@Param("physicalId") Integer physicalId,
																  @Param("policeId") String policeId);

	/**
	 *
	 * @param startNum
	 * @param endNum
	 * @return
	 */
	Integer getNotEligibleCount(@Param("physicalId") Integer physicalId, @Param("startNum") Integer startNum, @Param("endNum") Integer endNum);

	/**
	 * 分页查询综合训练数据
	 * @param pageIndex
	 * @param pageSize
	 * @param physicalId
	 * @param position
	 * @param key
	 * @param trainList
	 * @param trainFlag
	 * @param searchFlag
	 * @return
	 */
	List<TrainPhysicalAchievement> findTrainPhysicalAchievementPage(@Param("pageIndex") Integer pageIndex,
																	@Param("pageSize") Integer pageSize,
																	@Param("physicalId") Integer physicalId,
																	@Param("position") Integer position,@Param("key") String key,
																	@Param("list") List<Integer> trainList,
																	@Param("trainFlag") Integer trainFlag,@Param("searchFlag") Integer searchFlag);

	/**
	 * 统计分页数据条数
	 * @param physicalId
	 * @param position
	 * @param key
	 * @param trainList
	 * @param trainFlag
	 * @param searchFlag
	 * @return
	 */
	Integer countTrainPhysicalAchievementPage(@Param("physicalId") Integer physicalId,
											  @Param("position") Integer position,@Param("key") String key,
											  @Param("list") List<Integer> trainList,
											  @Param("trainFlag") Integer trainFlag,@Param("searchFlag") Integer searchFlag);

	/**
	 * 根据合格状态统计警员数据条数
	 * @param physicalId
	 * @param policeId
	 * @param grade
	 * @return
	 */
	Integer countPhysicalByAchievementGrade(@Param("physicalId") Integer physicalId, @Param("policeId") String policeId,
											@Param("grade") Integer grade);

	/**
	 * 查询所有综合训练项目类型
	 * @return
	 */
	List<TrainProjectDO> findAllPhysicalTrainProject();

	/**
	 * 计算合格率
	 * @return
	 */
	double getQualifiedRate();

	/**
	 * 近6月综合体能及抽测合格情况数据
	 * @param type
	 * @return
	 */
	List<TrainLineChartResult> getLineChartData(@Param("type") Integer type);
}