package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.EvaluateTask;
import com.bayee.political.domain.ScreenEvaluationStatistics;

public interface EvaluateTaskMapper {

	// 评价任务新增（后台）
	int evaluateTaskCreat(EvaluateTask evaluateTask);

	// 评价任务修改（后台）
	int evaluateTaskUpdate(EvaluateTask evaluateTask);

	int updateByPrimaryKey(EvaluateTask record);

	// 评价列表查询
//	List<EvaluateTask> evaluationList(@Param("policeId") String policeId, @Param("waitStatus") Integer waitStatus,
//			@Param("overStatus") Integer overStatus, @Param("pageSize") Integer pageSize);
	List<EvaluateTask> evaluationList(@Param("policeId") String policeId, @Param("waitStatus") Integer waitStatus,
			@Param("overStatus") Integer overStatus);

	// 评价列表数量统计
	int evaluationListCount(@Param("policeId") String policeId, @Param("waitStatus") Integer waitStatus,
			@Param("overStatus") Integer overStatus);

	// 评价任务删除
	int evaluationTaskDelete(@Param("id") Integer id);

	// 评价列表查询(后台)
	List<EvaluateTask> evaluateTaskList(@Param("type") Integer type, @Param("process") Integer process,
			@Param("keywords") String keywords, @Param("pageSize") Integer pageSize);

	// 评价列表查询数量统计(后台)
	int evaluateTaskListCount(@Param("type") Integer type, @Param("process") Integer process,
			@Param("keywords") String keywords);

	// 评价详情(后台)
	EvaluateTask evaluateTaskItem(@Param("id") Integer id);

	// 评价列表查询（定时任务修改任务进程）（后台）
	List<EvaluateTask> evaluateTaskTimingList();

	// 评价任务Top5列表查询(后台)
	List<EvaluateTask> homeEvaluationList();

	// 评价任务类型饼图(后台)
	List<CalculationChart> homeEvaluationPieChart();

	// 评价任务类型折线图-统计总数(后台)
	List<CalculationChart> homeLineTotalChart();

	// 评价任务类型折线图-类型数量(后台)
	List<CalculationChart> homeLineChart(@Param("type") Integer type);

	// 判断当前模板是否被任务引用(后台)
	List<EvaluateTask> existTemplateList(@Param("templateId") Integer templateId);

	/**
	 * 本周评价任务数
	 * 
	 * @return
	 */
	@Select("select count(*) as count from evaluate_task where YEARWEEK(date_format(creation_date,'%Y-%m-%d')) = YEARWEEK(now())")
	Integer taskNumWeek();

	/**
	 * 最新5条评价任务
	 * 
	 * @return
	 */
	List<EvaluateTask> newTaskTopFive();

	/**
	 * 评价类型占比
	 * 
	 * @param type     任务类型（1个人2单位3项目）
	 * @param dateType 1月度2季度3年度
	 * @return
	 */
	Double typeRator(@Param("type") Integer type, @Param("dateType") Integer dateType);

	// 评价任务类型统计
	List<CalculationChart> screenEvaluationTypeList();

	// 评价任务数量、评价人次统计
	ScreenEvaluationStatistics screenEvaluationStatistics();

}