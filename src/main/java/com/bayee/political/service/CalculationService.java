package com.bayee.political.service;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Calculation;
import com.bayee.political.domain.CalculationFactor;
import com.bayee.political.domain.CalculationPolicePost;
import com.bayee.political.domain.CalculationAlarm;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.CalculationDetail;
import com.bayee.political.domain.CalculationPoliceStation;
import com.bayee.political.domain.CalculationProject;
import com.bayee.political.domain.CalculationResourceAllocate;
import com.bayee.political.domain.PoliceStation;

/**
 * @author shentuqiwei
 * @version 2020年5月20日 下午2:46:19
 */
@Service
public interface CalculationService {

	// 警力测算饼图统计
	List<CalculationChart> policeStatisticsList(Integer policeType, Integer postType);

	// 所需警力查询(api)
	List<CalculationChart> getStatisticsApiList(Integer policeType);

	// 实际警力查询(api)
	List<CalculationChart> actualStatisticsApiList(Integer policeType);

	// 案件类型
	List<CalculationProject> projectList();

	// 根据案件id查询
	List<Calculation> calculationList(Integer caseId, Integer policeType);

	// 新增测算
	int calculationCreat(Calculation calculation);

	// 修改测算
	int calculationUpdate(Calculation calculation);

	// 测算结果查询
	List<Calculation> calculationOverList(Integer policeStationId, Integer stationPostId);

	// 各派出所案件数量查询(api)
	List<CalculationDetail> calculationDetailApiList(Integer caseId, Integer policeType);

	// 测算详情案件数据新增(api)
	int calculationDetailCreat(CalculationDetail calculationDetail);

	// 测算详情案件数据修改(api)
	int calculationDetailUpdate(CalculationDetail calculationDetail);

	// 查询打击岗警力数据计算列表(api)
	List<Calculation> strikeList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 查询基础岗警力数据计算列表(api)
	List<Calculation> baseList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 查询综合岗警力数据计算列表(api)
	List<Calculation> overAllList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 根据派出所id和岗位id修改(api)
	int calculationStatisticsUpdate(Calculation calculation);

	// 最后更新时间查询(api)
	Calculation lastTimeApiItem(Integer policeType);

	// 查询综合勤务室辅警数据计算列表(api)
	List<Calculation> serviceRoomList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 查询法制室辅警数据计算列表(api)
	List<Calculation> legalRoomList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 查询户籍室辅警数据计算列表(api)
	List<Calculation> registerRoomList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 查询其他辅警警力辅警数据计算列表(api)
	List<Calculation> otherList(Integer policeType, Integer stationPostId, Integer policeStationId);

	// 各派出所岗位人员数据查询(后台)
	List<Calculation> calculationPageList(Integer policeType, Integer stationPostId);

	// 警力详情查询
	Calculation calculationItem(Integer id, Integer policeType, Integer policeStationId, Integer stationPostId);

	// 民警案件类型查询(后台)
	List<CalculationProject> policeProjectList(Integer policeStationId);

	// 辅警案件类型查询(后台)
	List<CalculationProject> auxiliaryPoliceProjectList(Integer policeStationId);

	// 各派出所案件数据查询(后台)
	List<CalculationDetail> calDetailsList(Integer caseId, Integer policeStationId);

	// 案件详情查询(后台)
	CalculationDetail calculationDetailItem(Integer id, Integer policeStationId, Integer caseId);

	// 测算结果总数查询
	List<Calculation> calculationOverListSum(@Param("policeType") Integer policeType);

	// 测算结果总数查询
	List<Calculation> calculationOverListSumY(@Param("policeType") Integer policeType);

	/**
	 * 根据id修改总数
	 */
	int calculationPolicePostUpdateByid(@Param("id") Integer id, @Param("sum") Integer sum);

	/**
	 * 根据id修改总数
	 */
	int CalculationPoliceStationUpdateByid(@Param("id") Integer id, @Param("sum") Integer sum);

	/**
	 * 根据派出所id及警员类型查询总数
	 */
	CalculationPoliceStation getAllByPoliceIdType(@Param("policeId") Integer policeId,
			@Param("policeType") Integer policeType);

	/**
	 * 根据policeType及policeStationId获得所需警力数量的总数
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	Integer getSumByCondition(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);

	// 最新警力预警查询
	List<Calculation> calculationAlarmNewest(Integer policeType);

	// 资源调配建议
	List<CalculationResourceAllocate> calculationResourceAllocateAdviseList(Integer policeType);

	// 警力测算预警新增
	int calculationAlarmCreat(CalculationAlarm record);

	// 警力测算预警详情查询
	CalculationAlarm calculationAlarmItem(Integer id, Integer policeType, Integer policeStationId);

	// 警力测算预警修改
	int calculationAlarmUpdate(CalculationAlarm record);

	// 警力测算预警列表查询
	List<CalculationAlarm> calculationAlarmList();

	// 警力预警趋势查询
	List<CalculationChart> calculationAlarmTrendList(Integer policeType, Integer policeStationId, Integer isLack);

	// 查询预警派出所
	List<PoliceStation> calculationStationList(Integer policeType);

	// 警力行政拘留新增
	int administrativeDetentionCreat(CalculationFactor record);

	// 警力行政拘留详情查询
	CalculationFactor administrativeDetentionItem(Integer id, Integer caseId, Integer policeStationId);

	// 警力行政拘留修改
	int administrativeDetentionUpdate(CalculationFactor record);

	/**
	 * 查询最新一条记录
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	CalculationAlarm getCalculationAlarmBaseNew(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);

	// 今日警力查询
	List<Calculation> todayCalculationList(Integer policeType);

	/**
	 * 随机查询两条民警或辅警超出或缺少的派出所(该派出所的最新记录)
	 * 
	 * @param policeType 1民警2辅警
	 * @return
	 */
	List<CalculationAlarm> calculationRandTwo(@Param("policeType") Integer policeType);

	/**
	 * 所有民警或所有辅警
	 * 
	 * @param policeType 1民警2辅警
	 * @return
	 */
	Integer allPoliceCount(@Param("policeType") Integer policeType);

	/**
	 * 查询近N天的超出或紧缺人数
	 * 
	 * @param policeStationId 派出所id
	 * @param isLack          0紧缺2超出
	 * @param policeType      1民警2辅警
	 * @param day             近多少天日期yyy-MM-dd
	 * @return
	 */
	Integer nearDayDifferPoliceNum(@Param("policeStationId") Integer policeStationId,
			@Param("policeType") Integer policeType, @Param("day") String day);

	/**
	 * 近N天紧缺或超出最大的派出所
	 * 
	 * @param isLack     0紧缺2超出
	 * @param policeType 1民警2辅警
	 * @return
	 */
	CalculationAlarm maxDifferPoliceNumThirty(@Param("isLack") Integer isLack, @Param("policeType") Integer policeType,
			@Param("day") Integer day);

	/**
	 * 今天紧缺或超出最大的派出所
	 * 
	 * @param isLack     0紧缺2超出
	 * @param policeType 1民警2辅警
	 * @return
	 */
	CalculationAlarm maxDifferPoliceNum(@Param("isLack") Integer isLack, @Param("policeType") Integer policeType);

	/**
	 * 查询单位最新民警/辅警实际数
	 * 
	 * @param policeType
	 * @return
	 */
	List<CalculationAlarm> newPoliceStationActual(@Param("policeType") Integer policeType);

	/**
	 * 查询某派出所最新数据
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	CalculationAlarm policeStationNewData(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);

	/**
	 * 民警辅警总数
	 * 
	 * @param policeType
	 * @return
	 */
	Integer sumPoliceNum(@Param("policeType") Integer policeType);

	// 根据id修改警力测算数据
	int calculationIdUpdate(Calculation calculation);

	// 警力岗位人数计算
	List<CalculationChart> policepieStatisticsList(Integer policeType, Integer postType);

	// 根据派出所id和policeType修改
	int pStationUpdate(CalculationPoliceStation pStation);

	// 查询当前岗位数量
	CalculationPolicePost calculationPolicePostNumItem(Integer policeType, Integer stationPostId);

	// 根据岗位id和民警类型type修改
	int calculationPolicePostUpdate(CalculationPolicePost record);
}
