/**
 * 
 */
package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.Department;

/**
 * @author seanguo
 *
 */
public interface DepartmentMapper {

	void save(Department department);

	List<Department> findAll();

	// 根据人员查询部门(api)
	List<Department> participantDepList(@Param("policeId") String policeId, @Param("taskId") Integer taskId);

	Department findById(@Param("id") Integer id);

	/**
	 * 根据部门类别获取部门
	 */
	List<Department> getDepartmentByType(Integer parentId);

	/**
	 * 根据部门id或部门id集合查询部门name及id
	 * 
	 * @return
	 */
	List<Department> getDepartmentByIds(@Param("ids") String ids);

	// 部门查询
	List<Department> departmentList(@Param("id") Integer id);

	/**
	 * 根据部门名称查询部门
	 * 
	 * @param name
	 * @return
	 */
	Department getDepartmentByName(@Param("name") String name);

	/**
	 * 根据警号查询部门
	 * 
	 * @return
	 */
	Department getDepartmentByPoliceId(@Param("policeId") String policeId);

	// 扣分型统计分析部门查询
	List<Department> alarmDepBuckleItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

	// 加分型统计分析部门查询
	List<Department> alarmDepAddItem(@Param("policeId") String policeId, @Param("dateTime") String dateTime);

}
