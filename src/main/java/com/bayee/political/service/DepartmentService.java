/**
 * 
 */
package com.bayee.political.service;

import java.util.List;

import com.bayee.political.pojo.dto.HolographicDeptListDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.PoliceStation;
import com.bayee.political.domain.PoliceStationPost;

/**
 * @author seanguo
 *
 */
@Service
public interface DepartmentService {

	Department findByDeptCode(String deptCode);

	void save(Department department);

	Department findById(Integer id);

	List<Department> findAll();

	// 派出所查询(api)
	List<PoliceStation> policeStationApiList(Integer policeStationId);

	// 派出所及总数查询(api)
	List<PoliceStation> policeStationApiListSum(@Param("policeType") Integer policeType);

	// 派出所民警岗位查询(api)
	List<PoliceStationPost> policeStationPostApiList(Integer policeType);

	// 派出所民警岗位及总数查询(api)
	List<PoliceStationPost> policeStationPostApiListSum(@Param("policeType") Integer policeType);

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
	List<Department> departmentList(Integer id);

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
	List<Department> alarmDepBuckleItem(String policeId, String dateTime);

	// 加分型统计分析部门查询
	List<Department> alarmDepAddItem(String policeId, String dateTime);

	/**
	 * 警员全息首页-部门列表查询
	 * @param policeId
	 * @return
	 */
	List<HolographicDeptListDO> findHolographicDeptList(String policeId);

}
