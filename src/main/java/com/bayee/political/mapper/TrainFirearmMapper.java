package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.TrainFirearm;

public interface TrainFirearmMapper {

	/**
	 * 删除枪械
	 * 
	 * @param id
	 * @return
	 */
	int trainFirearmDelete(Integer id);

	/**
	 * 添加枪械
	 * 
	 * @param trainFirearm
	 * @return
	 */
	int trainFirearmAdd(TrainFirearm trainFirearm);

	// 枪械修改
	int trainFirearmUpdate(TrainFirearm trainFirearm);

	/**
	 * 枪械修改(特殊)
	 * 
	 * @param trainFirearm
	 * @return
	 */
	int trainFirearmUpdateSpecial(TrainFirearm trainFirearm);

	// 枪械详情查询
	TrainFirearm trainFirearmItem(@Param("id") Integer id);

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
	List<TrainFirearm> getTrainFirearmList(@Param("departmentId") Integer departmentId, @Param("status") Integer status, @Param("reprotTime") String reprotTime,
			@Param("signTime") String signTime, @Param("keyWords") String keyWords,
			@Param("pageSize") Integer pageSize);

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
	int getTrainFirearmCount(@Param("departmentId") Integer departmentId, @Param("status") Integer status, @Param("reprotTime") String reprotTime,
			@Param("signTime") String signTime, @Param("keyWords") String keyWords);

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

	// 枪械列表查询（定时任务修改约谈状态进程）
	List<TrainFirearm> firearmStatuslList();

	// 枪械次数趋势图(近12个月)
	List<CalculationChart> trainDepFirearmChart(@Param("departmentId") Integer departmentId);

	// 枪械判断当前用户是否可扫码当前人员
	TrainFirearm firearmScorerPoliceItem(@Param("id") Integer id, @Param("scorerPoliceId") String scorerPoliceId);
	
	/**
	 * 根据projectRuleId查询是枪械是否应用了该项目
	 * @param projectRuleId
	 * @return
	 */
	List<TrainFirearm> getTrainFirearmByProjectRuleId(@Param("projectRuleId") Integer projectRuleId);

	/**
	 * 统计所有枪械训练数据
	 * @return
	 */
	Integer countAll();

	/**
	 * 统计所有枪械训练数据
	 * @param date yyyy-MM-dd
	 * @return
	 */
	Integer countByDate(String date);

	/**
	 * 分页查询枪械训练数据
	 * @param pageIndex
	 * @param pageSize
	 * @param date
	 * @param trainName
	 * @param position
	 * @return
	 */
	List<TrainFirearm> findTrainFirearmPage(@Param("pageIndex") Integer pageIndex, @Param("pageSize") Integer pageSize,
											@Param("trainBeginDate") String trainBeginDate,
											@Param("trainEndDate") String trainEndDate,
											@Param("trainName") String trainName, @Param("position") Integer position);

	/**
	 * 统计分页数据条数
	 * @param date
	 * @param trainName
	 * @param position
	 * @return
	 */
	Integer countTrainFirearmPage(@Param("trainBeginDate") String trainBeginDate,
								  @Param("trainEndDate") String trainEndDate,
								  @Param("trainName") String trainName, @Param("position") Integer position);

	/**
	 * 获取枪械训练总数
	 * @return
	 */
	Integer getCount();

	/**
	 * 查询警员不合格的枪械训练项目
	 * @param policeId
	 * @return
	 */
	List<TrainFirearm> findPoliceUnQualifiedTrainFirearm(String policeId);

}