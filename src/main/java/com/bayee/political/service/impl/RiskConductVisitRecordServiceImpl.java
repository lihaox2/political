package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.RiskConductVisitRecord;
import com.bayee.political.mapper.RiskConductVisitRecordMapper;
import com.bayee.political.mapper.RiskConductVisitTypeMapper;
import com.bayee.political.service.RiskConductVisitRecordService;

@Service
public class RiskConductVisitRecordServiceImpl implements RiskConductVisitRecordService{
	
	@Autowired
	private RiskConductVisitRecordMapper riskConductVisitRecordMapper;
	
	@Autowired
	private RiskConductVisitTypeMapper riskConductVisitTypeMapper;

	@Override
	public int deleteByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(RiskConductVisitRecord record) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.insert(record);
	}

	@Override
	public int insertSelective(RiskConductVisitRecord record) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.insertSelective(record);
	}

	@Override
	public RiskConductVisitRecord selectByPrimaryKey(Integer id) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(RiskConductVisitRecord record) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.updateByPrimaryKeySelective(record);
	}

	@Override
	public int updateByPrimaryKey(RiskConductVisitRecord record) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.updateByPrimaryKey(record);
	}

	@Override
	public List<RiskConductVisitRecord> riskConductVisitRecordList(String policeId, String dateTime, String lastMonthTime, Integer timeType) {
		// TODO Auto-generated method stub
		return riskConductVisitRecordMapper.riskConductVisitRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	@Override
	public Integer selectByName(String name) {
		// TODO Auto-generated method stub
		return riskConductVisitTypeMapper.selectByName(name);
	}

	@Override
	public List<RiskConductVisitRecord> riskConductVisitRecordPage(Integer pageIndex, Integer pageSize) {
		if (pageIndex < 1) {
			pageIndex = 1;
		}
		pageIndex = (pageIndex - 1) * pageSize;
		return riskConductVisitRecordMapper.riskConductVisitRecordPage(pageIndex, pageSize);
	}

	@Override
	public Integer getRiskConductVisitRecordPageCount() {
		return riskConductVisitRecordMapper.getRiskConductVisitRecordPageCount();
	}

	@Override
	public Integer countByTypeId(Integer typeId) {
		return riskConductVisitRecordMapper.countByTypeId(typeId);
	}

}
