package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.LeaveRestManage;

public interface LeaveRestManageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LeaveRestManage record);

    int insertSelective(LeaveRestManage record);

    LeaveRestManage selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LeaveRestManage record);

    int updateByPrimaryKey(LeaveRestManage record);
    
    /**
     * 查询全部或按条件查询调休管理
     * @return
     */
    List<LeaveRestManage> getLeaveRestManageList(
    		@Param("departmentId") Integer departmentId, 
    		@Param("positionId") Integer positionId,
    		@Param("keyword") String keyword,
    		@Param("pageSize") Integer pageSize
    		);
    
    /**
     * 查询全部或按条件查询调休管理总数量
     * @return
     */
    int getLeaveRestManageListCount(
    		@Param("departmentId") Integer departmentId, 
    		@Param("positionId") Integer positionId,
    		@Param("keyword") String keyword
    		);
    
    /**
     * 根据id查询一条或按条件查询调休管理
     * @return
     */
    LeaveRestManage getLeaveRestManageOne(@Param("id") Integer id);
        
    
}