package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveDuty;

public interface LeaveDutyMapper {

	// 警员值班时长新增
	int leaveDutyCreat(LeaveDuty record);

	// 警员值班时长修改
	int leaveDutyUpdate(LeaveDuty record);

	// 警员值班时长详情
	LeaveDuty leaveDutyItem(@Param("id") Integer id, @Param("policeId") String policeId, @Param("year") String year,
			@Param("month") String month);

}