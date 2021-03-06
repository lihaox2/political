package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.json.TrainLineChartResult;
import com.bayee.political.json.TrainRankingResult;
import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.RiskTrainFirearmRecord;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainFirearmAchievement;

public interface TrainFirearmAchievementMapper {

	// 枪械报名新增
	int trainFirearmAchievementCreat(TrainFirearmAchievement item);

	// 枪械报名修改
	int trainFirearmAchievementUpdate(TrainFirearmAchievement item);

	/**
	 * 修改枪械报名根据train_firearm_id及police_id
	 * 
	 * @param item
	 * @return
	 */
	int trainFirearmAchievementUpdateExport(TrainFirearmAchievement item);

	// 枪械项目报名人数统计
	int singleTrainFirearmAchievementCount(@Param("trainFirearmId") Integer trainFirearmId);

	// 枪械项目报名详情
	TrainFirearmAchievement trainFirearmAchievementItem(@Param("id") Integer id,
			@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId);

	// 根据警号详情id项目id修改
	int trainFirearmAchievementPoliceUpdate(TrainFirearmAchievement item);

	// 根据警号详情id项目id批量修改枪械成绩
	void updateFirearmAchievementBatch(List<TrainFirearmAchievement> updateList);

	/**
	 * 枪械训练查看数据
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	List<TrainFirearmAchievement> getFirearmAchievementData(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword,
			@Param("pageSize") Integer pageSize);

	/**
	 * 枪械训练查看数据数量
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	int getFirearmAchievementDataCount(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

	/**
	 * 根据警号及训练id查询在该枪械训练下的成绩排名
	 */
	int getFirearmRank(@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId,
			@Param("sort") Integer sort);

	/**
	 * 修改枪械训练成绩
	 * 
	 * @param id
	 * @param trainFirearmId
	 * @param achievement
	 * @return
	 */
	int updateFirearmAchievement(@Param("id") Integer id, @Param("trainFirearmId") Integer trainFirearmId,
			@Param("achievement") Double achievement);

	/**
	 * 根据id获得枪械成绩详情
	 * 
	 * @param id
	 * @return
	 */
	TrainFirearmAchievement getTrainFirearmAchievementById(@Param("id") Integer id);

	/**
	 * 枪械报名人数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmSignUpNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械签到人数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmSignInNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械合格人数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmQualifiedNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械合格人数
	 *
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmUnQualifiedNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械U型靶良好人数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmUFineNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械U型靶优秀人数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmUGoodNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 枪械单位报名数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmCompanyNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 查询枪械成绩模板
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	List<TrainAchievementTemplate> getFirearmTrainAchievementTemplate(@Param("trainFirearmId") Integer trainFirearmId);

	// 枪械报名批量新增
	void trainFirearmAchievementCreatBatch(List<TrainFirearmAchievement> creatList);

	// 批量修改警员枪械成绩
	void trainFirearmAchievementUpdateBatch(List<TrainFirearmAchievement> finalList);

	// 警员警务技能枪械数据列表查询
	List<RiskTrainFirearmRecord> riskTrainFirearmRecordList(@Param("policeId") String policeId,
			@Param("dateTime") String dateTime, @Param("lastMonthTime") String lastMonthTime,
			@Param("timeType") Integer timeType);

	/**
	 * 查询警员枪械训练应扣除的分数
	 * @param policeId
	 * @param date
	 * @return
	 */
	Double getPoliceFirearmDeductionScore(@Param("policeId") String policeId,@Param("date") String date);

	/**
	 * 分页查询枪械训练数据
	 * @param pageIndex
	 * @param pageSize
	 * @param firearmId
	 * @param position
	 * @param key
	 * @param searchFlag
	 * @return
	 */
	List<TrainFirearmAchievement> findTrainFirearmAchievementPage(@Param("pageIndex") Integer pageIndex,
																  @Param("pageSize") Integer pageSize,
																  @Param("firearmId") Integer firearmId,
																  @Param("position") Integer position,
																  @Param("key") String key,
																  @Param("searchFlag") Integer searchFlag,
																  @Param("deptId") Integer deptId);

	/**
	 * 统计分页数据条数
	 * @param firearmId
	 * @param position
	 * @param key
	 * @param searchFlag
	 * @return
	 */
	Integer countTrainFirearmAchievementPage(@Param("firearmId") Integer firearmId, @Param("position") Integer position,
											 @Param("key") String key,@Param("searchFlag") Integer searchFlag,
											 @Param("deptId") Integer deptId);

	/**
	 * 枪械合格率集合
	 * @return
	 */
	List<TrainRankingResult> trainFirearmQualifiedRate();

	/**
	 * 近6月枪械合格情况数据
	 * @return
	 */
	List<TrainLineChartResult> getLineChartData();

	/**
	 * 删除枪械训练成绩
	 * @param firearmId
	 */
	void deleteTrainFirearmAchievementByFirearmId(@Param("firearmId") Integer firearmId);

	/**
	 * 根据年份和月份进行查询
	 * @param firearmRecordYear
	 * @param firearmRecordMonth
	 * @param policeId
	 * @return
	 */
	List<TrainFirearmAchievement> findFirearmRecordYearAndMonth(
			@Param("firearmRecordYear") String firearmRecordYear,
			@Param("firearmRecordMonth") String firearmRecordMonth,
			@Param("policeId") String policeId
	);

	/**
	 * 统计警员平均射击成绩
	 * @param policeId
	 * @param beginDate
	 * @param endDate
	 * @return
	 */
	Integer policeFirearmAchievementAvg(@Param("policeId") String policeId, @Param("beginDate") String beginDate,
										@Param("endDate") String endDate);

}