package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.PoliceStation;

public interface PoliceStationMapper {

	// 派出所查询(api)
	List<PoliceStation> policeStationApiList(@Param("policeStationId") Integer policeStationId);

	// 派出所及总数查询(api)
	List<PoliceStation> policeStationApiListSum(@Param("policeType") Integer policeType);

	// 查询预警派出所
	List<PoliceStation> calculationAlarmTrendList(@Param("policeType") Integer policeType);
}