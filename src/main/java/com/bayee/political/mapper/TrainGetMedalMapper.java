package com.bayee.political.mapper;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.TrainGetMedal;
import com.bayee.political.domain.TrainMedalPerson;

public interface TrainGetMedalMapper {

	// 警员奖章删除
	int trainGetMedalDelete(@Param("id") Integer id);

	// 警员奖章新增
	int trainGetMedalCreat(TrainGetMedal record);

	// 警员奖章修改
	int trainGetMedalUpdate(TrainGetMedal record);

	// 个人奖章详情查询
	TrainMedalPerson medalPersonalItem(@Param("policeId") String policeId);

	// 警员奖章详情记录查询
	TrainGetMedal trainGetMedalPoliceItem(@Param("id") Integer id, @Param("policeId") String policeId,
			@Param("medalManageId") Integer medalManageId);
}