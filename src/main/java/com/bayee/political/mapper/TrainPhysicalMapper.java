package com.bayee.political.mapper;

import java.util.List;

import com.bayee.political.json.PhysicalTrainPageQueryParam;
import com.bayee.political.json.TrainCensusResult;
import com.bayee.political.pojo.dto.PhysicalTrainPageResultDO;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.TrainChartStatistics;
import com.bayee.political.domain.TrainPersonalAchievement;
import com.bayee.political.domain.TrainPersonalResult;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainStatisticsAnalysis;
import com.bayee.political.domain.TrainTimeName;

public interface TrainPhysicalMapper {

	// 综合体能删除
	int trainPhysicalDelete(@Param("id") Integer id);

	// 综合体能新增
	int trainPhysicalCreat(TrainPhysical record);

	// 综合体能修改
	int trainPhysicalUpdate(TrainPhysical record);

	/**
	 * 综合体能修改（特殊）
	 * 
	 * @param trainPhysical
	 * @return
	 */
	int trainPhysicalUpdateSpecial(TrainPhysical trainPhysical);

	// 综合体能详情查询
	TrainPhysical trainPhysicalItem(@Param("id") Integer id);

	// 综合体能列表查询
	List<TrainPhysical> trainPhysicalList(@Param("policeId") String policeId, @Param("type") Integer type);

	// 综合体能列表总数统计
	int trainPhysicalCount(@Param("policeId") String policeId, @Param("type") Integer type);

	// 枪械详情查询(使用综合体能实体类)
	TrainPhysical trainPhFirearmItem(@Param("id") Integer id);

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
	 * @param status   状态
	 * @param keyWords 关键字
	 * @return
	 */
	int getTrainPhysicalCount(@Param("departmentId") Integer departmentId, @Param("status") Integer status,
			@Param("reprotTime") String reprotTime, @Param("signTime") String signTime,
			@Param("keyWords") String keyWords);

	// 近期训练查询
	List<TrainPhysical> trainRecentList(@Param("policeId") String policeId);

	// 我的训练列表查询
	List<TrainPhysical> trainMyList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("status") Integer status, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 我的训练列表总数统计
	int trainMyCount(@Param("policeId") String policeId, @Param("type") Integer type, @Param("status") Integer status);

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

	// 领队训练列表查询
	List<TrainPhysical> trainLeaderList(@Param("policeId") String policeId, @Param("status") Integer status,
			@Param("departmentId") Integer departmentId, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 领队训练列表数量统计
	int trainLeaderCount(@Param("policeId") String policeId, @Param("status") Integer status,
			@Param("departmentId") Integer departmentId);

	// 领队训练报名中列表查询
	List<TrainPhysical> trainLeaderSignUpList(@Param("policeId") String policeId,
			@Param("signUpStatus") Integer signUpStatus, @Param("departmentId") Integer departmentId,
			@Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 领队训练报名中列表数量统计
	int trainLeaderSignUpCount(@Param("policeId") String policeId, @Param("signUpStatus") Integer signUpStatus,
			@Param("departmentId") Integer departmentId);

	// 综合训练列表查询（定时任务修改约谈状态进程）
	List<TrainPhysical> physicaStatuslList();

	// 综合训练次数趋势图(近12个月)
	List<CalculationChart> trainDepPhysicalChart(@Param("departmentId") Integer departmentId);

	// 单位组织训练类型饼图
	List<CalculationChart> trainDepTypeChart(@Param("departmentId") Integer departmentId);

	// 训练总体数据统计分析
	TrainStatisticsAnalysis trainTotalStatistics(@Param("departmentId") Integer departmentId);

	// 训练总体数据最近部分统计分析
	TrainStatisticsAnalysis trainTotalLastStatistics(@Param("departmentId") Integer departmentId);

	// 查询最近一次分局训练
	TrainPhysical trainRecentPhysicalItem();

	// 查询最近一次分局综合训练合格率
	TrainStatisticsAnalysis trainPhysicalLastStatistics(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 查询最近一次分局枪械合格率
	TrainStatisticsAnalysis trainFirearmLastStatistics(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("departmentId") Integer departmentId);

	// 比赛总体数据统计分析
	TrainStatisticsAnalysis matchTotalStatistics(@Param("departmentId") Integer departmentId);

	// 单位组织赛事次数趋势图(近12个月)
	List<CalculationChart> matchDepChart(@Param("departmentId") Integer departmentId, @Param("nature") Integer nature);

	// 单位组织赛事类型饼图
	List<CalculationChart> matchDepTypeChart(@Param("departmentId") Integer departmentId);

	// 本周训练查询
	List<TrainPhysical> trainWeekList(@Param("policeId") String policeId, @Param("departmentId") Integer departmentId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 即将训练查询
	List<TrainPhysical> trainSoonList(@Param("policeId") String policeId, @Param("departmentId") Integer departmentId);

	// 训练/赛事通知查询
	List<TrainPhysical> trainMatchNotificationList(@Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId);

	// 查询连续5次，枪械训练各项成绩Top3，可获得该奖章
	List<TrainPhysical> trainFirearmContinuityFivePassList(@Param("policeId") String policeId);

	// 查询训练最近一次成绩
	TrainPhysical newsetScoreEnterItem(@Param("policeId") String policeId);

	// 个人最好成绩查询
	List<TrainPhysical> trainPersonalBestAchievementList(@Param("policeId") String policeId);

	// 个人训练成绩列表查询
	List<TrainPersonalAchievement> trainPersonalAchievementList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 个人训练成绩时间查询
	List<TrainTimeName> trainTimeNameList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 个人训练成绩列表总数查询
	int trainPersonalAchievementCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 个人训练成绩详情查询
	TrainPersonalAchievement trainPersonalAchievementItem(@Param("policeId") String policeId, @Param("id") Integer id,
			@Param("objectId") Integer objectId);

	// 个人综合体能训练统计
	TrainChartStatistics trainPersonalPhysicalStatisticsItem(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 个人训练柱状图统计(周)
	int trainPersonalWeekChartList(@Param("policeId") String policeId, @Param("objectType") Integer objectType,
			@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("tableName") String tableName, @Param("fromTableName") String fromTableName);

	// 个人枪械训练统计
	TrainChartStatistics trainPersonalFirearmStatisticsItem(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 个人训练柱状图统计(月)
	List<CalculationChart> trainPersonalMonthChartList(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("tableName") String tableName,
			@Param("fromTableName") String fromTableName);

	// 个人训练柱状图统计(年)
	List<CalculationChart> trainPersonalYearChartList(@Param("policeId") String policeId,
			@Param("startTime") String startTime, @Param("endTime") String endTime,
			@Param("tableName") String tableName, @Param("yearTime") String yearTime);

	// 个人训练柱状图统计(总)
	List<CalculationChart> trainPersonalTotalChartList(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("tableName") String tableName,
			@Param("fromTableName") String fromTableName);

	// 个人赛事成绩时间查询
	List<TrainTimeName> matchTimeNameList(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize, @Param("pageNum") Integer pageNum);

	// 个人赛事成绩列表查询
	List<TrainPersonalAchievement> matchPersonalAchievementList(@Param("policeId") String policeId,
			@Param("type") Integer type, @Param("dateTime") String dateTime, @Param("pageSize") Integer pageSize,
			@Param("pageNum") Integer pageNum);

	// 个人赛事成绩列表总数查询
	int matchPersonalAchievementCount(@Param("policeId") String policeId, @Param("type") Integer type,
			@Param("dateTime") String dateTime);

	// 个人赛事柱状图统计(周)
	int matchPersonalWeekChartList(@Param("policeId") String policeId, @Param("objectType") Integer objectType,
			@Param("matchTypeId") Integer matchTypeId, @Param("startTime") String startTime,
			@Param("endTime") String endTime);

	// 个人赛事柱状图统计(月)
	List<CalculationChart> matchPersonalMonthChartList(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("matchTypeId") Integer matchTypeId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 个人赛事柱状图统计(年)
	List<CalculationChart> matchPersonalYearChartList(@Param("policeId") String policeId,
			@Param("matchTypeId") Integer matchTypeId, @Param("startTime") String startTime,
			@Param("endTime") String endTime, @Param("yearTime") String yearTime);

	// 个人赛事柱状图统计(总)
	List<CalculationChart> matchPersonalTotalChartList(@Param("policeId") String policeId,
			@Param("objectType") Integer objectType, @Param("matchTypeId") Integer matchTypeId,
			@Param("startTime") String startTime, @Param("endTime") String endTime);

	// 进行中的训练/赛事查询
	List<TrainPhysical> trainMatchOngoingList(@Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId);

	// 正在报名的训练/赛事查询
	List<TrainPhysical> trainMatchSignUpList(@Param("policeId") String policeId,
			@Param("departmentId") Integer departmentId);

	// 个人训练成绩综合查询查询
	TrainPersonalResult trainPersonalResultItem(@Param("policeId") String policeId);

	// 个人分局各训练类型所占比
	List<CalculationChart> trainPersonalResultOfficeList(@Param("policeId") String policeId);

	// 个人单位各训练类型所占比
	List<CalculationChart> trainPersonalResultDepList(@Param("policeId") String policeId);

	// 个人最近成绩查询
	List<TrainPhysical> trainPersonalLatelyAchievementList(@Param("policeId") String policeId);

	// 分局签到率/合格率/不合格率/优秀率/良好率统计
	List<CalculationChart> trainPhysicalUpdateSpecial(String policeId, Integer type, String tableName);

	// 分局综合训练签到率/合格率/不合格率/优秀率/良好率统计
	List<LeaveChart> trainOfficeRateStatisticsList(@Param("id") Integer id, @Param("type") Integer type);

	// 分局枪械签到率/合格率/不合格率/优秀率/良好率统计
	List<LeaveChart> trainOfficeFirRateStatisticsList(@Param("id") Integer id, @Param("type") Integer type);

	// 进行中的训练查询
	List<TrainPhysical> trainInProgressList(@Param("policeId") String policeId, @Param("status") Integer status,
			@Param("sort") String sort);

	/**
	 * 本周综合体能加枪械训练的次数
	 * 
	 * @return
	 */
	@Select("select (select count(*) as count from train_physical where YEARWEEK(date_format(registration_start_date,'%Y-%m-%d')) = YEARWEEK(now()))+(select count(*) as count from train_firearm where YEARWEEK(date_format(registration_start_date,'%Y-%m-%d')) = YEARWEEK(now())) as count")
	Integer AllTrainNum();

	// 综合训练报名人头像查询(排除当前用户)
	List<CalculationChart> trainPhysicalHeadImageList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId, @Param("num") Integer num);

	// 枪械报名人头像查询(排除当前用户)
	List<CalculationChart> trainFirearmHeadImageList(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId, @Param("num") Integer num);

	// 综合训练报名当前用户头像查询
	CalculationChart trainPhysicalHeadImageItem(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId);

	// 枪械报名当前用户头像查询
	CalculationChart trainFirearmHeadImageItem(@Param("trainPhysicalId") Integer trainPhysicalId,
			@Param("policeId") String policeId);

	// 综合体能判断当前用户是否可扫码当前人员
	TrainPhysical physicalScorerPoliceItem(@Param("id") Integer id, @Param("scorerPoliceId") String scorerPoliceId);

	// 训练成绩合格率前5名
	List<TrainRank> screenTrainNewestRankList();

	/**
	 * 统计所有综合训练数据
	 * @return
	 */
	Integer countAll();

	/**
	 * 分页查询综合训练
	 * @param pageIndex
	 * @param pageSize
	 * @param date
	 * @param trainName
	 * @param position
	 * @return
	 */
	List<TrainPhysical> findTrainPhysicalPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize,
											  @Param("trainBeginDate") String trainBeginDate,
											  @Param("trainEndDate") String trainEndDate,
											  @Param("trainName") String trainName,
											  @Param("position") Integer position);

	/**
	 * 统计分页数据条数
	 * @param date
	 * @param trainName
	 * @param position
	 * @return
	 */
	Integer countTrainPhysicalPage(@Param("trainBeginDate") String trainBeginDate,
								   @Param("trainEndDate") String trainEndDate,
								   @Param("trainName") String trainName,
								   @Param("position") Integer position);

	/**
	 * 统计训练总数及本月训练数
	 * @return
	 */
	TrainCensusResult getTotalAndThisMonthCount();

	/**
	 * 查询综合体能训练总数及抽测数
	 * @param type
	 * @return
	 */
	Integer getCountByTrainType(@Param("type") Integer type);

	/**
	 * 查询警员不合格的综合训练项目
	 * @param policeId
	 * @return
	 */
	List<TrainPhysical> findPoliceUnQualifiedTrainPhysical(String policeId);

	/**
	 * 查询警员的不合格项目
	 * @param physicalId
	 * @param policeId
	 * @return
	 */
	List<String> findPoliceUnQualifiedProject(@Param("physicalId") Integer physicalId, @Param("policeId") String policeId);

}