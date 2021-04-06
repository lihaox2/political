package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ReportDataFillTime;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainChartStatistics;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.domain.TrainFirearmAchievement;
import com.bayee.political.domain.TrainGroup;
import com.bayee.political.domain.TrainGroupPolice;
import com.bayee.political.domain.TrainLeader;
import com.bayee.political.domain.TrainLeaderAchievement;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPersonalAchievement;
import com.bayee.political.domain.TrainPersonalResult;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.domain.TrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainPhysicalProjectRecord;
import com.bayee.political.domain.TrainProject;
import com.bayee.political.domain.TrainProjectRule;
import com.bayee.political.domain.TrainProjectURule;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;
import com.bayee.political.domain.TrainRecordScore;
import com.bayee.political.domain.TrainScorer;
import com.bayee.political.domain.TrainStatisticsAnalysis;
import com.bayee.political.domain.TrainTimeName;
import com.bayee.political.domain.TrainUnit;

/**
 * @author shentuqiwei
 * @version 2020年9月28日 上午9:46:50
 */
@Service
public interface TrainService {

	// 综合体能删除
	int trainPhysicalDelete(Integer id);

	// 综合体能新增
	int trainPhysicalCreat(TrainPhysical record);

	// 综合体能修改
	int trainPhysicalUpdate(TrainPhysical record);

	// 综合体能详情查询
	TrainPhysical trainPhysicalItem(Integer id);

	// 综合体能列表查询
	List<TrainPhysical> trainPhysicalList(String policeId, Integer type);

	// 综合体能列表总数统计
	int trainPhysicalCount(String policeId, Integer type);

	// 单项综合体能项目报名人数统计
	int singleTrainPhysicalAchievementCount(Integer trainPhysicalId);

	// 查询组别列表
	List<TrainGroup> trainGroupList(Integer id);

	// 查询组别详情
	TrainGroup trainGroupItem(Integer id);

	// 综合体能项目报名新增
	int trainPhysicalAchievementCreat(TrainPhysicalAchievement record);

	// 综合体能项目报名修改
	int trainPhysicalAchievementUpdate(TrainPhysicalAchievement record);

	// 单项综合体能项目报名详情
	TrainPhysicalAchievement trainPhysicalAchievementItem(Integer id, Integer trainPhysicalId, String policeId);

	/**
	 * 用于后端页面查询综合体能数据
	 * 
	 * @param status                状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate          训练结束时间
	 * @param keyWords              关键字
	 * @param pageSize              分页大小
	 * @return
	 */
	List<TrainPhysical> getTrainPhysicalList(@Param("departmentId") Integer departmentId,
			@Param("status") Integer status, @Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 用于后端页面查询综合体能总数据数量
	 *
	 * @param status                状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate          训练结束时间
	 * @param keyWords              关键字
	 * @return
	 */
	int getTrainPhysicalCount(@Param("departmentId") Integer departmentId, @Param("status") Integer status,
			@Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords);

	/**
	 * 根据id字符串 id1,id2,id3...获得训练组别姓名字符串 name1,name2,name3...
	 *
	 * @param ids ids 组别id字符串
	 * @return
	 */
	public String getTrainGroupByIds(@Param("ids") String ids);

	/**
	 * 用于后端页面查询枪械总数据
	 *
	 * @param status                状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate          训练结束时间
	 * @param keyWords              关键字
	 * @return
	 */
	List<TrainFirearm> getTrainFirearmList(@Param("departmentId") Integer departmentId, @Param("status") Integer status,
			@Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 用于后端页面查询枪械总数据数量
	 *
	 * @param status                状态
	 * @param registrationStartDate 报名开始时间
	 * @param registrationEndDate   报名结束时间
	 * @param trainStartDate        训练开始时间
	 * @param trainEndDate          训练结束时间
	 * @param keyWords              关键字
	 * @return
	 */
	int getTrainFirearmCount(@Param("departmentId") Integer departmentId, @Param("status") Integer status,
			@Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords);

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

	/**
	 * 查询组别数据
	 * 
	 * @param sex      性别 1男2女
	 * @param minAge   最小年龄
	 * @param maxAge   最大年龄
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainGroup> getTrainGroupList(@Param("sex") Integer sex, @Param("minAge") Integer minAge,
			@Param("maxAge") Integer maxAge, @Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 查询组别数据
	 * 
	 * @param sex      性别 1男2女
	 * @param minAge   最小年龄
	 * @param maxAge   最大年龄
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	int getTrainGroupCount(@Param("sex") Integer sex, @Param("minAge") Integer minAge, @Param("maxAge") Integer maxAge,
			@Param("keyWords") String keyWords);

	/**
	 * 训练项目管理查询
	 * 
	 * @param keyWords
	 * @param pageSize
	 * @return
	 */
	List<TrainProject> getTrainProjectList(@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 训练项目管理查询
	 * 
	 * @param keyWords
	 * @return
	 */
	int getTrainProjectCount(@Param("keyWords") String keyWords);

	/**
	 * 训练项目管理查询项目名id是否U型
	 * 
	 * @return
	 */
	List<TrainProject> getTrainProjectNameIdIsU();

	/**
	 * 记分员配置列表
	 * 
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainScorer> getTrainScorerList(@Param("keyWords") String keyWords, @Param("pageSize") Integer pageSize);

	/**
	 * 记分员配置列表数量
	 * 
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainScorerCount(@Param("keyWords") String keyWords);

	/**
	 * 项目规则管理
	 * 
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainProjectRule> getTrainProjectRuleList(@Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize);

	/**
	 * 项目规则管理数量
	 * 
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainProjectRuleCount(@Param("keyWords") String keyWords);

	/**
	 * 查询奖章
	 * 
	 * @param type     奖章发行类型
	 * @param keyWords 关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainMedalManage> getTrainMedalList(@Param("type") Integer type, @Param("keyWords") String keyWords,
			@Param("sort") Integer sort, @Param("pageSize") Integer pageSize);

	/**
	 * 查询奖章
	 * 
	 * @param type     奖章发行类型
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainMedalCount(@Param("type") Integer type, @Param("keyWords") String keyWords);

	/**
	 * 获得活动风采
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @param pageSize    分页大小
	 * @return
	 */
	List<TrainActivityStyle> getTrainActivityStyleList(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获得活动风采数量
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @return
	 */
	int getTrainActivityStyleCount(@Param("status") Integer status, @Param("isRecommend") Integer isRecommend,
			@Param("keyWords") String keyWords);

	/**
	 * 获得训练标兵
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @param pageSize    分页大小
	 * @return
	 */
	List<TrainPacesetter> getTrainPacesetterList(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize);

	/**
	 * 获得训练标兵数量
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @param pageSize    分页大小
	 * @return
	 */
	int getTrainPacesetterCount(@Param("status") Integer status, @Param("isRecommend") Integer isRecommend,
			@Param("keyWords") String keyWords);

	/**
	 * 训练章程
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @param pageSize    分页大小
	 * @return
	 */
	List<TrainConstitution> getTrainConstitutionList(@Param("status") Integer status,
			@Param("isRecommend") Integer isRecommend, @Param("keyWords") String keyWords, @Param("sort") Integer sort,
			@Param("pageSize") Integer pageSize);

	/**
	 * 训练章程数量
	 * 
	 * @param status      状态
	 * @param isRecommend 是否推荐
	 * @param keyWords    关键字
	 * @return
	 */
	int getTrainConstitutionCount(@Param("status") Integer status, @Param("isRecommend") Integer isRecommend,
			@Param("keyWords") String keyWords);

	/**
	 * 查询所有换算单位
	 * 
	 * @return
	 */
	List<TrainUnit> getTrainUnitList();

	/**
	 * 查询所有组别的id和name
	 * 
	 * @return
	 */
	List<TrainGroup> getTrainGroupNameId();

	/**
	 * 立即开始综合体能训练
	 * 
	 * @param id 主键
	 * @return
	 */
	int startTrainPhysical(@Param("id") Integer id);

	/**
	 * 立即结束综合体能训练
	 * 
	 * @param id 主键
	 * @return
	 */
	int endTrainPhysical(@Param("id") Integer id);

	/**
	 * 重启综合体能训练
	 * 
	 * @param id 主键
	 * @return
	 */
	int restartTrainPhysical(@Param("id") Integer id, @Param("endTime") String endTime);

	/**
	 * 获得综合体能训练详情
	 * 
	 * @param id
	 * @return
	 */
	TrainPhysical getTrainPhysicalDetails(@Param("id") Integer id);

	/**
	 * 查询所有组别对应的项目
	 * 
	 * @return
	 */
	List<TrainProjectRule> getGroAndPro();

	/**
	 * 获得所有射击类型
	 * 
	 * @return
	 */
	List<TrainProjectRule> getTrainFirearmType();

	/**
	 * 根据射击类型名称获得射击类型(射击项目中的射击类型)
	 * 
	 * @return
	 */
	TrainProjectRule getTrainFirearmTypeByName(@Param("name") String name);

	/**
	 * 添加组别项目对应
	 * 
	 * @param record
	 * @return
	 */
	int addTrainPhysicalProjectRecord(TrainPhysicalProjectRecord record);

	/**
	 * 根据多个组别字符串及体能训练id查询对应组别体能项目
	 * 
	 * @param ids 组别字符串
	 * @param id  体能训练id
	 * @return
	 */
	List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordByGroupIds(@Param("ids") String ids,
			@Param("id") Integer id);

	/**
	 * 根据项目ids字符串查询项目名names
	 * 
	 * @param ids多个id的字符串
	 * @return
	 */
	String getTrainProjectNamesByIds(@Param("ids") String ids);

	/**
	 * 根据trainPhysicalId删除记录项目
	 * 
	 * @param trainPhysicalId
	 * @return
	 */
	int deleteByTrainPhysicalId(@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 添加组别
	 * 
	 * @param trainGroup 组别实体类
	 * @return
	 */
	int addTrainGroup(TrainGroup trainGroup);

	/**
	 * 删除组别
	 * 
	 * @param id
	 * @return
	 */
	int deleteGroup(Integer id);

	/**
	 * 根据组别id查询组别是否被引用 >=0既被引用
	 * 
	 * @param id组别id
	 * @return
	 */
	int groupQuote(@Param("id") Integer id);

	/**
	 * 更新组别
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainGroup(TrainGroup trainGroup);

	/**
	 * 新增训练项目
	 * 
	 * @param trainProject
	 * @return
	 */
	int addTrainProject(TrainProject trainProject);

	/**
	 * 删除训练项目
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainProject(Integer id);

	/**
	 * 修改训练项目
	 * 
	 * @param trainProject
	 * @return
	 */
	int updateTrainProject(TrainProject trainProject);

	/**
	 * 根据id查询训练项目详情
	 * 
	 * @param id
	 * @return
	 */
	TrainProject getTrainProjectById(@Param("id") Integer id);

	/**
	 * 添加记分员
	 * 
	 * @param trainScorer
	 * @return
	 */
	int addTrainScorer(TrainScorer trainScorer);

	/**
	 * 删除记分员
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainScorer(Integer id);

	/**
	 * 根据id查询记分员配置表详情
	 * 
	 * @param id
	 * @return
	 */
	TrainScorer getTrainScorerById(@Param("id") Integer id);

	/**
	 * 修改记分员配置
	 * 
	 * @param trainScorer
	 * @return
	 */
	int updateTrainScorer(TrainScorer trainScorer);

	/**
	 * 新增项目规则
	 * 
	 * @param trainProjectRule
	 * @return
	 */
	int addTrainProjectRule(TrainProjectRule trainProjectRule);

	/**
	 * 添加U型射击项目规则数据
	 * 
	 * @param trainProjectURule
	 * @return
	 */
	int addTrainProjectURule(TrainProjectURule trainProjectURule);

	/**
	 * 根据id查询项目规则
	 * 
	 * @param id
	 * @return
	 */
	TrainProjectRule getTrainProjectRuleById(@Param("id") Integer id);

	/**
	 * 删除项目规则根据id
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainProjectRule(Integer id);

	/**
	 * 根据ruleId删除U型靶规则
	 * 
	 * @return
	 */
	int deleteTrainProjectURule(@Param("ruleId") Integer ruleId);

	/**
	 * 修改项目规则
	 * 
	 * @param trainProjectRule
	 * @return
	 */
	int updateTrainProjectRule(TrainProjectRule trainProjectRule);

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
	 * 根据组别和体能训练项目查询合格分数及项目的规则
	 * 
	 * @param projectId
	 * @param groupId
	 * @return
	 */
	TrainProjectRule getAchievement(@Param("projectId") Integer projectId, @Param("groupId") Integer groupId);

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
	 * 根据训练id及组别id及项目id及警号查询排名
	 * 
	 * @param trainPhysicalId
	 * @param trainGroupId
	 * @param projectId
	 * @param policeId
	 * @return
	 */
	Integer getRank(@Param("trainPhysicalId") Integer trainPhysicalId, @Param("trainGroupId") Integer trainGroupId,
			@Param("projectId") Integer projectId, @Param("policeId") String policeId, @Param("sort") Integer sort);

	/**
	 * 修改活动风采
	 * 
	 * @param record
	 * @return
	 */
	int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle);

	/**
	 * 添加活动风采
	 * 
	 * @param trainActivityStyle
	 * @return
	 */
	int addTrainActivityStyle(TrainActivityStyle trainActivityStyle);

	/**
	 * 删除活动风采
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainActivityStyle(Integer id);

	/**
	 * 根据id获得活动风采
	 * 
	 * @param id
	 */
	TrainActivityStyle getTrainActivityStyleOnely(@Param("id") Integer id);

	/**
	 * 删除训练标兵
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainPacesetter(Integer id);

	/**
	 * 添加训练标兵
	 * 
	 * @param trainPacesetter
	 * @return
	 */
	int addTrainPacesetter(TrainPacesetter trainPacesetter);

	/**
	 * 根据id查询训练标兵
	 * 
	 * @param id
	 * @return
	 */
	TrainPacesetter getTrainPacesetterOnely(Integer id);

	/**
	 * 修改训练标兵
	 * 
	 * @param trainPacesetter
	 * @return
	 */
	int updateTrainPacesetter(TrainPacesetter trainPacesetter);

	/**
	 * 删除训练章程
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainConstitution(Integer id);

	/**
	 * 添加训练章程
	 * 
	 * @param trainConstitution
	 * @return
	 */
	int addTrainConstitution(TrainConstitution trainConstitution);

	/**
	 * 根据id查询训练章程
	 * 
	 * @param id
	 * @return
	 */
	TrainConstitution getTrainConstitutionOnely(Integer id);

	/**
	 * 修改训练章程
	 * 
	 * @param trainConstitution
	 * @return
	 */
	int updateTrainConstitution(TrainConstitution trainConstitution);

	/**
	 * 设置推荐或上下架
	 * 
	 * @param id
	 * @param isRecommend
	 * @param status
	 * @return
	 */
	Integer isRecommendOrStatus(@Param("id") Integer id, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status);

	/**
	 * 训练章程设置推荐或上下架
	 * 
	 * @param id
	 * @param isRecommend
	 * @param status
	 * @return
	 */
	Integer constitutionIsRecommendOrStatus(@Param("id") Integer id, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status);

	/**
	 * 训练标兵设置推荐或上下架
	 * 
	 * @param id
	 * @param isRecommend
	 * @param status
	 * @return
	 */
	Integer pacesetterIsRecommendOrStatus(@Param("id") Integer id, @Param("isRecommend") Integer isRecommend,
			@Param("status") Integer status);

	/**
	 * 编辑奖章
	 * 
	 * @param trainMedalManage
	 * @return
	 */
	int updateTrainMedal(TrainMedalManage trainMedalManage);

	/**
	 * 查询奖章详情
	 * 
	 * @param id
	 * @return
	 */
	TrainMedalManage getTrainMedalOnely(Integer id);

	/**
	 * 删除奖章
	 * 
	 * @param id
	 * @return
	 */
	int deleteTrainMedal(Integer id);

	/**
	 * 查询领队
	 * 
	 * @param keyword  关键字
	 * @param pageSize 分页大小
	 * @return
	 */
	List<TrainLeader> getTrainLeaderList(@Param("keyword") String keyword, @Param("pageSize") Integer pageSize);

	/**
	 * 查询领队数量
	 * 
	 * @param keyword 关键字
	 * @return
	 */
	int getTrainLeaderListCount(@Param("keyword") String keyword);

	/**
	 * 根据id查询领队
	 * 
	 * @param id
	 * @return
	 */
	TrainLeader getTrainLeaderById(@Param("id") Integer id);

	/**
	 * 添加枪械
	 * 
	 * @param trainFirearm
	 * @return
	 */
	int trainFirearmAdd(TrainFirearm trainFirearm);

	/**
	 * 删除枪械
	 * 
	 * @param id
	 * @return
	 */
	int trainFirearmDelete(Integer id);

	/**
	 * 立即开始枪械训练
	 * 
	 * @param id
	 * @return
	 */
	int trainFirearmStart(@Param("id") Integer id);

	/**
	 * 立即结束枪械训练
	 * 
	 * @param id
	 * @return
	 */
	int trainFirearmEnd(@Param("id") Integer id);

	/**
	 * 重启枪械训练
	 * 
	 * @param id
	 * @return
	 */
	int trainFirearmReStart(@Param("id") Integer id, @Param("time") String time);

	/**
	 * 查询枪械详情
	 * 
	 * @param id
	 * @return
	 */
	TrainFirearm getTrainFirearmById(@Param("id") Integer id);

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
	 * 根据警号及训练id查询在该枪械训练下的成绩排名
	 */
	int getFirearmRank(@Param("trainFirearmId") Integer trainFirearmId, @Param("policeId") String policeId,
			@Param("sort") Integer sort);

	/**
	 * 枪械训练查看数据数量
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	int getFirearmAchievementDataCount(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("departmentId") Integer departmentId, @Param("keyword") String keyword);

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
	 * 枪械修改(特殊)
	 * 
	 * @param trainFirearm
	 * @return
	 */
	int trainFirearmUpdateSpecial(TrainFirearm trainFirearm);

	/**
	 * 综合体能单位报名数
	 * 
	 * @param trainPhysicalId 体能训练id
	 * @return
	 */
	Integer companyNum(@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 枪械单位报名数
	 * 
	 * @param trainFirearmId 枪械训练id
	 * @return
	 */
	Integer firearmCompanyNum(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 根据项目名称查询该项目的数量
	 * 
	 * @param name 项目名称
	 * @return
	 */
	int getRepeatTrainProjectCount(@Param("name") String name);

	// 查询组别民警
	List<TrainGroupPolice> trainGroupPoliceList(String policeId);

	// 枪械详情查询(使用综合体能实体类)
	TrainPhysical trainPhFirearmItem(Integer id);

	// 枪械项目报名人数统计
	int singleTrainFirearmAchievementCount(Integer trainFirearmId);

	// 枪械项目报名详情
	TrainFirearmAchievement trainFirearmAchievementItem(Integer id, Integer trainFirearmId, String policeId);

	// 枪械详情查询
	TrainFirearm trainFirearmItem(Integer id);

	// 枪械报名新增
	int trainFirearmAchievementCreat(TrainFirearmAchievement item);

	// 枪械报名修改
	int trainFirearmAchievementUpdate(TrainFirearmAchievement item);

	// 近期训练查询
	List<TrainPhysical> trainRecentList(String policeId);

	// 我的训练列表查询
	List<TrainPhysical> trainMyList(String policeId, Integer type, Integer status, Integer pageSize, Integer pageNum);

	// 我的训练列表总数统计
	int trainMyCount(String policeId, Integer type, Integer status);

	// 我的参赛记录查询
	List<TrainPhysicalAchievementDetails> trainMyEntryRecordList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId);

	// 参训成绩新增
	int trainPhysicalAchievementDetailsCreat(TrainPhysicalAchievementDetails record);

	// 参训成绩修改
	int trainPhysicalAchievementDetailsUpdate(TrainPhysicalAchievementDetails record);

	// 参赛项目名称查询
	List<CalculationChart> trainProjectRankList(Integer trainPhysicalId, Integer trainPhysicalAchievementId,
			String policeId, Integer projectId);

	// 根据年龄组和训练项目排名
	List<TrainRank> trainPersonalRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort);

	// 根据年龄组和训练项目查询个人具体排名
	TrainRank trainPersonalRankItem(Integer projectId, Integer trainPhysicalId, String policeId, Integer departmentId,
			String sort);

	// 根据年龄组和训练项目排名(不限制人数)
	List<TrainRank> trainPersonalMoreRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort);

	// 参赛枪械项目名称查询
	List<CalculationChart> trainProjectFirearmRankList(Integer trainFirearmId, String policeId);

	// 根据年龄组和枪械项目排名
	List<TrainRank> trainPersonalFirearmRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort);

	// 根据年龄组和枪械项目排名(不限制人数)
	List<TrainRank> trainPersonalFirearmMoreRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort);

	// 根据年龄组和训练项目查询个人具体排名
	TrainRank trainPersonalFirearmRankItem(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort);

	// 查询当前民警所属组别中的综合体能项目
	List<TrainProject> trainPoliceBelongToList(Integer trainPhysicalId, Integer groupId);

	// 综合体能未录分数量
	int trainPhysicalNoScoreNum(String policeId);

	// 需要录分的训练查询
	List<TrainRecordScore> trainPhysicalNoScoreList(String policeId);

	// 录分训练计划中综合体能项目查询
	List<TrainProject> trainRecordProjectPhysicalList(Integer trainPhysicalId);

	// 录分训练计划中枪械查询
	List<TrainProject> trainRecordProjectFirearmList(Integer trainFirearmId);

	// 已录人数统计
	TrainRecordPolice trainRecordPoliceItem(Integer projectId, Integer trainPhysicalId);

	// 训练人员成绩查询
	List<TrainRank> trainRecordPoliceScoreList(Integer projectId, Integer trainPhysicalId);

	// 已录枪械人数统计
	TrainRecordPolice trainRecordFirearmPoliceItem(Integer projectId, Integer trainPhysicalId);

	// 枪械训练人员成绩查询
	List<TrainRank> trainRecordFirearmPoliceScoreList(Integer projectId, Integer trainPhysicalId);

	// 根据项目id/组别查询算分规则
	TrainProjectRule trainProjectPoliceRuleItem(Integer projectId, Integer groupId);

	// 根据警号详情id项目id枪械修改
	int trainFirearmAchievementPoliceUpdate(TrainFirearmAchievement item);

	// 根据警号详情id项目id批量修改枪械成绩
	void updateFirearmAchievementBatch(List<TrainFirearmAchievement> updateList);

	// 根据项目规则id查询U型靶成绩规则
	List<TrainProjectURule> TrainProjectURuleList(Integer ruleId);

	// 查询当前用户所在组包括的项目
	TrainPhysicalProjectRecord projectNamesItem(Integer trainPhysicalId, String policeId);

	// 警号详情id项目id修改综合体能成绩
	int trainDetailsPoliceUpdate(TrainPhysicalAchievementDetails item);

	// 枪械修改
	int trainFirearmUpdate(TrainFirearm trainFirearm);

	// 领队删除
	int trainLeaderDelete(Integer id);

	// 领队新增
	int trainLeaderCreat(TrainLeader record);

	// 领队修改
	int trainLeaderUpdate(TrainLeader record);

	// 领队详情查询
	TrainLeader trainLeaderItem(Integer id, String policeId, Integer departmentId);

	// 领队训练列表查询
	List<TrainPhysical> trainLeaderList(String policeId, Integer status, Integer departmentId, Integer pageSize,
			Integer pageNum);

	// 领队训练列表数量统计
	int trainLeaderCount(String policeId, Integer status, Integer departmentId);

	// 领队训练报名中列表查询
	List<TrainPhysical> trainLeaderSignUpList(String policeId, Integer signUpStatus, Integer departmentId,
			Integer pageSize, Integer pageNum);

	// 领队训练报名中列表数量统计
	int trainLeaderSignUpCount(String policeId, Integer signUpStatus, Integer departmentId);

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
	 * 综合体能修改（特殊）
	 * 
	 * @param trainPhysical
	 * @return
	 */
	int trainPhysicalUpdateSpecial(TrainPhysical trainPhysical);

	/**
	 * 查询枪械成绩模板
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	List<TrainAchievementTemplate> getFirearmTrainAchievementTemplate(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 赛事成绩模板
	 * 
	 * @param trainFirearmId
	 * @return
	 */
	List<TrainAchievementTemplate> getMatchTrainAchievementTemplate(@Param("trainFirearmId") Integer trainFirearmId);

	/**
	 * 修改枪械报名根据train_firearm_id及police_id
	 * 
	 * @param item
	 * @return
	 */
	int trainFirearmAchievementUpdateExport(TrainFirearmAchievement item);

	/**
	 * 修改枪械报名根据train_match_id及police_id
	 * 
	 * @param record
	 * @return
	 */
	int matchAchievementUpdateExport(TrainMatchAchievement record);

	/**
	 * 根据训练成绩id及警号 查询训练项目
	 * 
	 * @param trainFirearmId
	 * @param policeId
	 * @return
	 */
	TrainProject getTrainProjectByConditon(@Param("trainFirearmId") Integer trainFirearmId,
			@Param("policeId") String policeId);

	/**
	 * 获取体能训练的数据导入模板
	 * 
	 * @param trainPhysicalId
	 * @return
	 */
	List<TrainAchievementTemplate> getPhysicalTrainAchievementTemplateList(
			@Param("trainPhysicalId") Integer trainPhysicalId);

	/**
	 * 参训成绩修改根据train_firearm_id及police_id及project_id
	 * 
	 * @param record
	 * @return
	 */
	int trainPhysicalAchievementDetailsUpdateCondition(TrainPhysicalAchievementDetails record);

	/**
	 * 根据项目名称查询项目id
	 * 
	 * @param name
	 * @return
	 */
	Integer getTrainProjectByName(@Param("name") String name);

	/**
	 * 根据physical_id及police_id修改
	 * 
	 * @param record
	 * @return
	 */
	int trainPhysicalAchievementUpdateByCondition(TrainPhysicalAchievement record);

	// 综合训练推荐人员信息查询
	List<TrainRecommendPolice> trainPhysicalRecommendPoliceList(Integer trainPhysicalId, Integer departmentId,
			String policeId);

	// 枪械推荐人员信息查询
	List<TrainRecommendPolice> trainFirearmRecommendPoliceList(Integer departmentId, String policeId);

	// 综合训练部门参加人员信息查询
	List<TrainRecommendPolice> trainPhysicalDepPoliceList(Integer trainPhysicalId, Integer departmentId,
			String keywords);

	// 枪械部门参加人员信息查询
	List<TrainRecommendPolice> trainFirearmDepPoliceList(Integer departmentId, String keywords);

	// 领队综合训练报名成功查询
	List<TrainRecommendPolice> trainPhysicalLeaderSignUpSuccessList(Integer trainPhysicalId, Integer departmentId);

	// 领队枪械报名成功查询
	List<TrainRecommendPolice> trainFirearmLeaderSignUpSuccessList(Integer trainPhysicalId, Integer departmentId);

	// 领队综合训练成绩查询
	TrainLeaderAchievement trainPhysicalLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId);

	// 领队枪械成绩查询
	TrainLeaderAchievement trainFirearmLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId);

	// 领队综合体能签到率排行榜查询
	List<TrainRank> trainLeaderPhysicalSignUpRateRankList(Integer trainPhysicalId, Integer limitNum);

	// 领队综合体能合格率排行榜查询
	List<TrainRank> trainLeaderPhysicalPassRateRankList(Integer trainPhysicalId, Integer limitNum);

	// 领队综合体能不合格率排行榜查询
	List<TrainRank> trainLeaderPhysicalFailRateRankList(Integer trainPhysicalId, Integer limitNum);

	// 领队枪械签到率排行榜查询
	List<TrainRank> trainLeaderFirearmSignUpRateRankList(Integer trainPhysicalId, Integer limitNum);

	// 领队枪械优秀,良好,合格率排行榜查询
	List<TrainRank> trainLeaderFirearmGoodRateRankList(Integer trainPhysicalId, Integer gradeType, Integer limitNum);

	// 领队枪械不合格率排行榜查询
	List<TrainRank> trainLeaderFirearmFailRateRankList(Integer trainPhysicalId, Integer limitNum);

	// 查询当前用户成绩是否合格
	List<TrainPhysicalAchievementDetails> detailsFailList(Integer trainPhysicalId, String policeId);

	// 领队综合训练已签到人员查询
	List<TrainRecommendPolice> signMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队综合训练未签到人员查询
	List<TrainRecommendPolice> noSignMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队综合训练合格人员查询
	List<TrainRecommendPolice> passMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队综合训练不合格人员查询
	List<TrainRecommendPolice> failMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队枪械已签到人员查询
	List<TrainRecommendPolice> signFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队枪械未签到人员查询
	List<TrainRecommendPolice> noSignFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 领队枪械优秀,良好,合格人员查询
	List<TrainRecommendPolice> passFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId,
			Integer gradeType);

	// 领队枪械不合格人员查询
	List<TrainRecommendPolice> failFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId);

	// 综合训练列表查询（定时任务修改约谈状态进程）
	List<TrainPhysical> physicaStatuslList();

	// 枪械列表查询（定时任务修改约谈状态进程）
	List<TrainFirearm> firearmStatuslList();

	// 综合训练次数趋势图(近12个月)
	List<CalculationChart> trainDepPhysicalChart(Integer departmentId);

	// 枪械次数趋势图(近12个月)
	List<CalculationChart> trainDepFirearmChart(Integer departmentId);

	// 单位组织训练类型饼图
	List<CalculationChart> trainDepTypeChart(Integer departmentId);

	// 警员参加训练次数查询
	List<TrainRecommendPolice> trainDepPoliceList(Integer departmentId);

	// 训练总体数据统计分析
	TrainStatisticsAnalysis trainTotalStatistics(Integer departmentId);

	// 训练总体数据最近部分统计分析
	TrainStatisticsAnalysis trainTotalLastStatistics(Integer departmentId);

	// 查询最近一次分局训练
	TrainPhysical trainRecentPhysicalItem();

	// 查询最近一次分局综合训练合格率
	TrainStatisticsAnalysis trainPhysicalLastStatistics(Integer trainPhysicalId, Integer departmentId);

	// 查询最近一次分局枪械合格率
	TrainStatisticsAnalysis trainFirearmLastStatistics(Integer trainPhysicalId, Integer departmentId);

	// 比赛总体数据统计分析
	TrainStatisticsAnalysis matchTotalStatistics(Integer departmentId);

	// 单位组织赛事次数趋势图(近12个月)
	List<CalculationChart> matchDepChart(Integer departmentId, Integer nature);

	// 单位组织赛事类型饼图
	List<CalculationChart> matchDepTypeChart(Integer departmentId);

	// 警员参加赛事次数查询
	List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId);

	// 本周训练查询
	List<TrainPhysical> trainWeekList(String policeId, Integer departmentId, String startTime, String endTime);

	// 即将训练查询
	List<TrainPhysical> trainSoonList(String policeId, Integer departmentId);

	// 训练/赛事通知查询
	List<TrainPhysical> trainMatchNotificationList(String policeId, Integer departmentId);

	// 查询任意5次训练活动各项成绩均合格
	int trainArbitrarilyFivePassCount(String policeId);

	// 查询连续5次训练活动各项成绩均合格
	int trainContinuityFivePassCount(String policeId);

	// 连续3个月参加训练，可获得该奖章
	int trainContinuityThreeMonthPassCount(String policeId);

	// 查询连续5次，枪械训练各项成绩Top3，可获得该奖章
	List<TrainPhysical> trainFirearmContinuityFivePassList(String policeId);

	// 查询训练最近一次成绩
	TrainPhysical newsetScoreEnterItem(String policeId);

	// 个人最好成绩查询
	List<TrainPhysical> trainPersonalBestAchievementList(String policeId);

	// 个人训练成绩列表查询
	List<TrainPersonalAchievement> trainPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum);

	// 个人训练成绩时间查询
	List<TrainTimeName> trainTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum);

	// 个人训练成绩列表总数查询
	int trainPersonalAchievementCount(String policeId, Integer type, String dateTime);

	// 个人训练成绩详情查询
	TrainPersonalAchievement trainPersonalAchievementItem(String policeId, Integer id, Integer objectId);

	// 个人赛事成绩时间查询
	List<TrainTimeName> matchTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum);

	// 个人赛事成绩列表查询
	List<TrainPersonalAchievement> matchPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum);

	// 个人赛事成绩列表总数查询
	int matchPersonalAchievementCount(String policeId, Integer type, String dateTime);

	// 个人综合体能训练统计
	TrainChartStatistics trainPersonalPhysicalStatisticsItem(String policeId, Integer objectType, String startTime,
			String endTime);

	// 个人枪械训练统计
	TrainChartStatistics trainPersonalFirearmStatisticsItem(String policeId, Integer objectType, String startTime,
			String endTime);

	// 个人训练柱状图统计(周)
	int trainPersonalWeekChartList(String policeId, Integer objectType, String startTime, String endTime,
			String tableName, String fromTableName);

	// 个人训练柱状图统计(月)
	List<CalculationChart> trainPersonalMonthChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName);

	// 个人训练柱状图统计(年)
	List<CalculationChart> trainPersonalYearChartList(String policeId, String startTime, String endTime,
			String tableName, String yearTime);

	// 个人训练柱状图统计(总)
	List<CalculationChart> trainPersonalTotalChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName);

	// 个人赛事柱状图统计(周)
	int matchPersonalWeekChartList(String policeId, Integer objectType, Integer matchTypeId, String startTime,
			String endTime);

	// 个人赛事柱状图统计(月)
	List<CalculationChart> matchPersonalMonthChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime);

	// 个人赛事柱状图统计(年)
	List<CalculationChart> matchPersonalYearChartList(String policeId, Integer matchTypeId, String startTime,
			String endTime, String yearTime);

	// 个人赛事柱状图统计(总)
	List<CalculationChart> matchPersonalTotalChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime);

	// 进行中的训练/赛事查询
	List<TrainPhysical> trainMatchOngoingList(String policeId, Integer departmentId);

	// 正在报名的训练/赛事查询
	List<TrainPhysical> trainMatchSignUpList(String policeId, Integer departmentId);

	// 个人训练成绩综合查询查询
	TrainPersonalResult trainPersonalResultItem(String policeId);

	// 个人分局各训练类型所占比
	List<CalculationChart> trainPersonalResultOfficeList(String policeId);

	// 个人单位各训练类型所占比
	List<CalculationChart> trainPersonalResultDepList(String policeId);

	// 个人最近成绩查询
	List<TrainPhysical> trainPersonalLatelyAchievementList(String policeId);

	// 分局综合训练签到率/合格率/不合格率/优秀率/良好率统计
	List<LeaveChart> trainOfficeRateStatisticsList(Integer id, Integer type);

	// 分局枪械签到率/合格率/不合格率/优秀率/良好率统计
	List<LeaveChart> trainOfficeFirRateStatisticsList(Integer id, Integer type);

	// 查询当前训练下的项目
	List<TrainPhysicalAchievementDetails> trainSignInProjectList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer isSign);

	// 查询当前训练项详情
	TrainPhysicalAchievementDetails physicalDetailsItem(Integer id, Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer projectId);

	// 进行中的训练查询
	List<TrainPhysical> trainInProgressList(String policeId, Integer status, String sort);

	// 领队报名人员查询
	List<TrainPhysicalAchievement> trainApplicantsLeaderList(Integer trainPhysicalId, Integer departmentId,
			String tableName, String tableId);

	// 成绩起始时间查询
	ReportDataFillTime trainMatchTimeItem(String tableName);

	/**
	 * 本周综合体能加枪械训练的次数
	 * 
	 * @return
	 */
	Integer AllTrainNum();

	// 综合训练报名人头像查询(排除当前用户)
	List<CalculationChart> trainPhysicalHeadImageList(Integer trainPhysicalId, String policeId, Integer num);

	// 综合训练报名当前用户头像查询
	CalculationChart trainPhysicalHeadImageItem(Integer trainPhysicalId, String policeId);

	// 枪械报名人头像查询
	List<CalculationChart> trainFirearmHeadImageList(Integer trainPhysicalId, String policeId, Integer num);

	// 枪械报名当前用户头像查询
	CalculationChart trainFirearmHeadImageItem(Integer trainPhysicalId, String policeId);

	// 查询已经签到的项目
	List<TrainPhysicalAchievementDetails> isWholeSignList(Integer trainPhysicalId, String policeId);

	// 是否是记分员判断查询
	List<TrainLeader> trainIsScorerList(String policeId);

	// 已完成录分的训练查询
	List<TrainRecordScore> trainPhysicalOverScoreList(String policeId);

	// 更新成绩时间
	int achievementDateUpdate(TrainPhysicalAchievementDetails reacord);

	// 综合体能判断当前用户是否可扫码当前人员
	TrainPhysical physicalScorerPoliceItem(Integer id, String scorerPoliceId);

	// 枪械判断当前用户是否可扫码当前人员
	TrainFirearm firearmScorerPoliceItem(Integer id, String scorerPoliceId);

	/**
	 * 根据综合体能训练成绩id及群组id查询合格成绩及该项目的升降序
	 * 
	 * @return
	 */
	TrainProjectRule getQualifiedAchievement(@Param("id") Integer id, @Param("goupId") Integer goupId);

	/**
	 * 根据项目Id查询项目规则
	 * 
	 * @param projectId
	 * @return
	 */
	List<TrainProjectRule> getTrainProjectRuleByProjectId(@Param("projectId") Integer projectId);

	/**
	 * 根据规则ID查询是训练是否引用了该规则
	 * 
	 * @param projectRuleId
	 * @return
	 */
	List<TrainPhysicalProjectRecord> getTrainPhysicalProjectRecordByProjectRuleId(
			@Param("projectRuleId") Integer projectRuleId);

	/**
	 * 根据projectRuleId查询是枪械是否应用了该项目
	 * 
	 * @param projectRuleId
	 * @return
	 */
	List<TrainFirearm> getTrainFirearmByProjectRuleId(@Param("projectRuleId") Integer projectRuleId);

	// 训练报名批量新增
	void trainPhysicalAchievementCreatBatch(List<TrainPhysicalAchievement> creatList);

	// 训练报名批量修改
	void trainPhysicalAchievementUpdateBatch(List<TrainPhysicalAchievement> updateList);

	// 体能项目成绩批量新增
	void trainPhysicalAchievementDetailsCreatBatch(List<TrainPhysicalAchievementDetails> creatList);

	// 体能项目成批量修改
	void trainPhysicalAchievementDetailsUpdateBatch(List<TrainPhysicalAchievementDetails> updateList);

	// 根据项目名查询项目id
	TrainProject trainProjectIdItem(String name);

	// 根据训练id查询报名人员list
	List<TrainPhysicalAchievement> updateGradeList(Integer trainPhysicalId);

	// 枪械报名批量新增
	void trainFirearmAchievementCreatBatch(List<TrainFirearmAchievement> creatList);
}
