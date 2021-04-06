package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveIntegralManage;

public interface LeaveIntegralManageMapper {
	/**
	 * 根据id删除积分管理
	 * @param id
	 * @return
	 */
    int deleteLeaveIntegralManageById(Integer id);

    int insert(LeaveIntegralManage record);

    int insertSelective(LeaveIntegralManage record);

    LeaveIntegralManage selectByPrimaryKey(Integer id);

    /**
     * 根据id修改积分管理
     * @param record
     * @return
     */
    int updateLeaveIntegralManageById(LeaveIntegralManage leaveIntegralManage);

    int updateByPrimaryKey(LeaveIntegralManage record);
    
    /**
     * 查询全部的积分管理数据或根据条件查询积分管理数据
     * @return
     */
    List<LeaveIntegralManage> getLeaveIntegralManageList(
    		@Param("policeMonth") Integer policeMonth,
    		@Param("departmentId") Integer departmentId,
    		@Param("keyword") String keyword,
    		@Param("pageSize") Integer pageSize
    		);
    
    /**
     * 查询全部的积分管理数量或根据条件查询积分管理数量
     * @return
     */
    int getLeaveIntegralManageCount(
    		@Param("policeMonth") Integer policeMonth,
    		@Param("departmentId") Integer departmentId,
    		@Param("keyword") String keyword
    		);
    
    /**
     * 新增积分
     * @return
     */
    Integer addLeaveIntegralManage(@Param("leaveIntegralManage") LeaveIntegralManage leaveIntegralManage);
    
    /**
     * 根据id获得积分管理
     * @param id
     * @return
     */
    LeaveIntegralManage getLeaveIntegralManageOne(@Param("id") Integer id);
    
    /**
     * 批量新增积分
     * @return
     */
    int addMoreLeaveIntegralManage(@Param("leaveIntegralManageList")List<LeaveIntegralManage> leaveIntegralManageList);

    /**
     * 判断当前月是否已添加过数据
     * @return
     */
    Integer nowMonthHave(
            @Param("policeId") String policeId,
            @Param("policeMonth") Integer policeMonth);

    
}