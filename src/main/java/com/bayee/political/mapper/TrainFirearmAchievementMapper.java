package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

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

}