package com.bayee.political.mapper;

import com.bayee.political.domain.LeavePowerObject;

public interface LeavePowerObjectMapper {

	// 审核对象人员删除
	int leavePowerObjectDelete(Integer id);

	// 审核对象新增
	int leavePowerObjectCreat(LeavePowerObject record);

	// 审核对象人员修改
	int leavePowerObjectUpdate(LeavePowerObject record);
	
	/**
	 * 根据powerId删除
	 * @param powerId
	 * @return
	 */
	int leavePowerObjectDeleteByPowerId(Integer powerId);
	
	/**
	 * 根据power_id获得police_object_ids 以"-"号隔开
	 * @param powerId
	 * @return
	 */
	String getPoliceObjectIdsByPowerId(Integer powerId);

}