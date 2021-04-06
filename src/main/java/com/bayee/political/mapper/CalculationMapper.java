package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.Calculation;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.CalculationResourceAllocate;

public interface CalculationMapper {

	// 警力测算饼图统计
	List<CalculationChart> policeStatisticsList(@Param("policeType") Integer policeType,
			@Param("postType") Integer postType);

	// 所需警力查询(api)
	List<CalculationChart> getStatisticsApiList(@Param("policeType") Integer policeType);

	// 实际警力查询(api)
	List<CalculationChart> actualStatisticsApiList(@Param("policeType") Integer policeType);

	// 根据案件id查询
	List<Calculation> calculationList(@Param("caseId") Integer caseId, @Param("policeType") Integer policeType);

	// 新增测算
	int calculationCreat(Calculation calculation);

	// 修改测算
	int calculationUpdate(Calculation calculation);

	// 测算结果查询
	List<Calculation> calculationOverList(@Param("policeStationId") Integer policeStationId,
			@Param("stationPostId") Integer stationPostId);

	// 测算结果总数查询
	List<Calculation> calculationOverListSum(@Param("policeType") Integer policeType);

	// 测算结果总数查询
	List<Calculation> calculationOverListSumY(@Param("policeType") Integer policeType);

	// 查询打击岗警力数据计算列表(api)
	List<Calculation> strikeList(@Param("policeType") Integer policeType, @Param("stationPostId") Integer stationPostId,
			@Param("policeStationId") Integer policeStationId);

	// 查询基础岗警力数据计算列表(api)
	List<Calculation> baseList(@Param("policeType") Integer policeType, @Param("stationPostId") Integer stationPostId,
			@Param("policeStationId") Integer policeStationId);

	// 查询综合岗警力数据计算列表(api)
	List<Calculation> overAllList(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId, @Param("policeStationId") Integer policeStationId);

	// 查询综合勤务室辅警数据计算列表(api)
	List<Calculation> serviceRoomList(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId, @Param("policeStationId") Integer policeStationId);

	// 查询法制室辅警数据计算列表(api)
	List<Calculation> legalRoomList(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId, @Param("policeStationId") Integer policeStationId);

	// 查询户籍室辅警数据计算列表(api)
	List<Calculation> registerRoomList(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId, @Param("policeStationId") Integer policeStationId);

	// 查询其他辅警警力辅警数据计算列表(api)
	List<Calculation> otherList(@Param("policeType") Integer policeType, @Param("stationPostId") Integer stationPostId,
			@Param("policeStationId") Integer policeStationId);

	// 根据派出所id和岗位id修改(api)
	int calculationStatisticsUpdate(Calculation calculation);

	// 最后更新时间查询(api)
	Calculation lastTimeApiItem(@Param("policeType") Integer policeType);

	// 各派出所岗位人员数据查询(后台)
	List<Calculation> calculationPageList(@Param("policeType") Integer policeType,
			@Param("stationPostId") Integer stationPostId);

	// 警力详情查询
	Calculation calculationItem(@Param("id") Integer id, @Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId, @Param("stationPostId") Integer stationPostId);

	// 最新警力预警查询
	List<Calculation> calculationAlarmNewest(@Param("policeType") Integer policeType);

	// 资源调配建议
	List<CalculationResourceAllocate> calculationResourceAllocateAdviseList(@Param("policeType") Integer policeType);

	/**
	 * 根据policeType及policeStationId获得所需警力数量的总数
	 * 
	 * @param policeType
	 * @param policeStationId
	 * @return
	 */
	Integer getSumByCondition(@Param("policeType") Integer policeType,
			@Param("policeStationId") Integer policeStationId);

	// 今日警力查询
	List<Calculation> todayCalculationList(@Param("policeType") Integer policeType);

	// 根据id修改警力测算数据
	int calculationIdUpdate(Calculation calculation);

	// 警力岗位人数计算
	List<CalculationChart> policepieStatisticsList(@Param("policeType") Integer policeType,
			@Param("postType") Integer postType);

	// 各派出所岗位比例
	List<CalculationChart> screenPoliceStationPostList();

	// 各派出所警力分布
	List<CalculationChart> screenPoliceStationForcesList(@Param("stationPostId") Integer stationPostId);

}