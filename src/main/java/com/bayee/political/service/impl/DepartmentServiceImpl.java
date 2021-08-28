/**
 * 
 */
package com.bayee.political.service.impl;

import java.util.List;

import com.bayee.political.pojo.dto.HolographicDeptListDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.PoliceStation;
import com.bayee.political.domain.PoliceStationPost;
import com.bayee.political.mapper.DepartmentMapper;
import com.bayee.political.mapper.PoliceStationMapper;
import com.bayee.political.mapper.PoliceStationPostMapper;
import com.bayee.political.service.DepartmentService;

/**
 * @author seanguo
 *
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentMapper mapper;

	@Autowired
	PoliceStationMapper policeStationMapper;// 派出所

	@Autowired
	PoliceStationPostMapper policeStationPostMapper;// 派出所岗位

	@Override
	public void save(Department department) {
		mapper.save(department);
	}

	@Override
	public List<Department> findAll() {
		return mapper.findAll();
	}

	// 派出所查询(api)
	@Override
	public List<PoliceStation> policeStationApiList(Integer policeStationId) {
		return policeStationMapper.policeStationApiList(policeStationId);
	}

	// 派出所民警岗位查询(api)
	@Override
	public List<PoliceStationPost> policeStationPostApiList(Integer policeType) {
		return policeStationPostMapper.policeStationPostApiList(policeType);
	}

	@Override
	public Department findById(Integer id) {
		return mapper.findById(id);
	}

	@Override
	public List<PoliceStation> policeStationApiListSum(Integer policeType) {
		return policeStationMapper.policeStationApiListSum(policeType);
	}

	@Override
	public List<PoliceStationPost> policeStationPostApiListSum(Integer policeType) {
		return policeStationPostMapper.policeStationPostApiListSum(policeType);
	}

	@Override
	public List<Department> getDepartmentByType(Integer parentId) {
		return mapper.getDepartmentByType(parentId);
	}

	@Override
	public List<Department> getDepartmentByIds(String ids) {
		return mapper.getDepartmentByIds(ids);
	}

	// 部门查询
	@Override
	public List<Department> departmentList(Integer id) {
		return mapper.departmentList(id);
	}

	@Override
	public Department getDepartmentByName(String name) {
		// TODO Auto-generated method stub
		return mapper.getDepartmentByName(name);
	}

	@Override
	public Department getDepartmentByPoliceId(String policeId) {
		return mapper.getDepartmentByPoliceId(policeId);
	}

	// 扣分型统计分析部门查询
	@Override
	public List<Department> alarmDepBuckleItem(String policeId, String dateTime) {
		return mapper.alarmDepBuckleItem(policeId, dateTime);
	}

	// 加分型统计分析部门查询
	@Override
	public List<Department> alarmDepAddItem(String policeId, String dateTime) {
		return mapper.alarmDepAddItem(policeId, dateTime);
	}

	@Override
	public List<HolographicDeptListDO> findHolographicDeptList(String policeId) {
		return mapper.findHolographicDeptList(policeId);
	}

}
