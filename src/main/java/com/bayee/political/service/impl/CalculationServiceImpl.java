package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.Calculation;
import com.bayee.political.domain.CalculationFactor;
import com.bayee.political.domain.CalculationPolicePost;
import com.bayee.political.domain.CalculationAlarm;
import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.CalculationDetail;
import com.bayee.political.domain.CalculationPoliceStation;
import com.bayee.political.domain.CalculationProject;
import com.bayee.political.domain.CalculationResourceAllocate;
import com.bayee.political.domain.PoliceStation;
import com.bayee.political.mapper.CalculationFactorMapper;
import com.bayee.political.mapper.CalculationAlarmMapper;
import com.bayee.political.mapper.CalculationDetailMapper;
import com.bayee.political.mapper.CalculationMapper;
import com.bayee.political.mapper.CalculationPolicePostMapper;
import com.bayee.political.mapper.CalculationPoliceStationMapper;
import com.bayee.political.mapper.CalculationProjectMapper;
import com.bayee.political.mapper.PoliceStationMapper;
import com.bayee.political.service.CalculationService;

/**
 * @author shentuqiwei
 * @version 2020年5月20日 下午2:46:37
 */
@Service
public class CalculationServiceImpl implements CalculationService {

	@Autowired
	CalculationMapper calculationMapper;

	@Autowired
	CalculationProjectMapper calculationProjectMapper;

	@Autowired
	CalculationDetailMapper calculationDetailMapper;

	@Autowired
	CalculationPolicePostMapper calculationPolicePostMapper;

	@Autowired
	CalculationPoliceStationMapper calculationPoliceStationMapper;

	@Autowired
	CalculationAlarmMapper calculationAlarmMapper;// 警力测算预警记录

	@Autowired
	PoliceStationMapper policeStationMapper;// 派出所

	@Autowired
	CalculationFactorMapper calculationFactorMapper;// 警力行政拘留记录

	// 警力测算饼图统计
	@Override
	public List<CalculationChart> policeStatisticsList(Integer policeType, Integer postType) {
		return calculationMapper.policeStatisticsList(policeType, postType);
	}

	// 所需警力查询(api)
	@Override
	public List<CalculationChart> getStatisticsApiList(Integer policeType) {
		return calculationMapper.getStatisticsApiList(policeType);
	}

	// 实际警力查询(api)
	@Override
	public List<CalculationChart> actualStatisticsApiList(Integer policeType) {
		return calculationMapper.actualStatisticsApiList(policeType);
	}

	// 案件类型
	@Override
	public List<CalculationProject> projectList() {
		return calculationProjectMapper.projectList();
	}

	// 根据案件id查询
	@Override
	public List<Calculation> calculationList(Integer caseId, Integer policeType) {
		return calculationMapper.calculationList(caseId, policeType);
	}

	// 新增测算
	@Override
	public int calculationCreat(Calculation calculation) {
		return calculationMapper.calculationCreat(calculation);
	}

	// 修改测算
	@Override
	public int calculationUpdate(Calculation calculation) {
		return calculationMapper.calculationUpdate(calculation);
	}

	// 测算结果查询
	@Override
	public List<Calculation> calculationOverList(Integer policeStationId, Integer stationPostId) {
		return calculationMapper.calculationOverList(policeStationId, stationPostId);
	}

	// 各派出所案件数量查询(api)
	@Override
	public List<CalculationDetail> calculationDetailApiList(Integer caseId, Integer policeType) {
		return calculationDetailMapper.calculationDetailApiList(caseId, policeType);
	}

	// 测算详情案件数据新增(api)
	@Override
	public int calculationDetailCreat(CalculationDetail calculationDetail) {
		return calculationDetailMapper.calculationDetailCreat(calculationDetail);
	}

	// 测算详情案件数据修改(api)
	@Override
	public int calculationDetailUpdate(CalculationDetail calculationDetail) {
		return calculationDetailMapper.calculationDetailUpdate(calculationDetail);
	}

	// 查询打击岗警力数据计算列表(api)
	@Override
	public List<Calculation> strikeList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.strikeList(policeType, stationPostId, policeStationId);
	}

	// 根据派出所id和岗位id修改(api)
	@Override
	public int calculationStatisticsUpdate(Calculation calculation) {
		return calculationMapper.calculationStatisticsUpdate(calculation);
	}

	// 查询基础岗警力数据计算列表(api)
	@Override
	public List<Calculation> baseList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.baseList(policeType, stationPostId, policeStationId);
	}

	// 查询综合岗警力数据计算列表(api)
	@Override
	public List<Calculation> overAllList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.overAllList(policeType, stationPostId, policeStationId);
	}

	// 最后更新时间查询(api)
	@Override
	public Calculation lastTimeApiItem(Integer policeType) {
		return calculationMapper.lastTimeApiItem(policeType);
	}

	// 查询综合勤务室辅警数据计算列表(api)
	@Override
	public List<Calculation> serviceRoomList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.serviceRoomList(policeType, stationPostId, policeStationId);
	}

	// 查询法制室辅警数据计算列表(api)
	@Override
	public List<Calculation> legalRoomList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.legalRoomList(policeType, stationPostId, policeStationId);
	}

	// 查询户籍室辅警数据计算列表(api)
	@Override
	public List<Calculation> registerRoomList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.registerRoomList(policeType, stationPostId, policeStationId);
	}

	// 查询其他辅警警力辅警数据计算列表(api)
	@Override
	public List<Calculation> otherList(Integer policeType, Integer stationPostId, Integer policeStationId) {
		return calculationMapper.otherList(policeType, stationPostId, policeStationId);
	}

	// 各派出所岗位人员数据查询(后台)
	@Override
	public List<Calculation> calculationPageList(Integer policeType, Integer stationPostId) {
		return calculationMapper.calculationPageList(policeType, stationPostId);
	}

	// 警力详情查询
	@Override
	public Calculation calculationItem(Integer id, Integer policeType, Integer policeStationId, Integer stationPostId) {
		return calculationMapper.calculationItem(id, policeType, policeStationId, stationPostId);
	}

	// 民警案件类型查询（后台）
	@Override
	public List<CalculationProject> policeProjectList(Integer policeStationId) {
		return calculationProjectMapper.policeProjectList(policeStationId);
	}

	// 辅警案件类型查询（后台）
	@Override
	public List<CalculationProject> auxiliaryPoliceProjectList(Integer policeStationId) {
		return calculationProjectMapper.auxiliaryPoliceProjectList(policeStationId);
	}

	// 各派出所案件数据查询(后台)
	@Override
	public List<CalculationDetail> calDetailsList(Integer caseId, Integer policeStationId) {
		return calculationDetailMapper.calDetailsList(caseId, policeStationId);
	}

	// 案件详情查询(后台)
	@Override
	public CalculationDetail calculationDetailItem(Integer id, Integer policeStationId, Integer caseId) {
		return calculationDetailMapper.calculationDetailItem(id, caseId, policeStationId);
	}

	@Override
	public List<Calculation> calculationOverListSum(Integer policeType) {
		return calculationMapper.calculationOverListSum(policeType);
	}

	@Override
	public List<Calculation> calculationOverListSumY(Integer policeType) {
		return calculationMapper.calculationOverListSumY(policeType);
	}

	@Override
	public int calculationPolicePostUpdateByid(Integer id, Integer sum) {
		return calculationPolicePostMapper.calculationPolicePostUpdateByid(id, sum);
	}

	@Override
	public int CalculationPoliceStationUpdateByid(Integer id, Integer sum) {
		return calculationPoliceStationMapper.CalculationPoliceStationUpdateByid(id, sum);
	}

	@Override
	public CalculationPoliceStation getAllByPoliceIdType(Integer policeId, Integer policeType) {
		return calculationPoliceStationMapper.getAllByPoliceIdType(policeId, policeType);
	}

	// 最新警力预警查询
	@Override
	public List<Calculation> calculationAlarmNewest(Integer policeType) {
		return calculationMapper.calculationAlarmNewest(policeType);
	}

	// 资源调配建议
	@Override
	public List<CalculationResourceAllocate> calculationResourceAllocateAdviseList(Integer policeType) {
		return calculationMapper.calculationResourceAllocateAdviseList(policeType);
	}

	// 警力测算预警新增
	@Override
	public int calculationAlarmCreat(CalculationAlarm record) {
		return calculationAlarmMapper.calculationAlarmCreat(record);
	}

	// 警力测算预警详情查询
	@Override
	public CalculationAlarm calculationAlarmItem(Integer id, Integer policeType, Integer policeStationId) {
		return calculationAlarmMapper.calculationAlarmItem(id, policeType, policeStationId);
	}

	// 警力测算预警修改
	@Override
	public int calculationAlarmUpdate(CalculationAlarm record) {
		return calculationAlarmMapper.calculationAlarmUpdate(record);
	}

	// 警力测算预警列表查询
	@Override
	public List<CalculationAlarm> calculationAlarmList() {
		return calculationAlarmMapper.calculationAlarmList();
	}

	// 警力预警趋势查询
	@Override
	public List<CalculationChart> calculationAlarmTrendList(Integer policeType, Integer policeStationId,
			Integer isLack) {
		return calculationAlarmMapper.calculationAlarmTrendList(policeType, policeStationId, isLack);
	}

	// 查询预警派出所
	@Override
	public List<PoliceStation> calculationStationList(Integer policeType) {
		return policeStationMapper.calculationAlarmTrendList(policeType);
	}

	@Override
	public Integer getSumByCondition(Integer policeType, Integer policeStationId) {
		return calculationMapper.getSumByCondition(policeType, policeStationId);
	}

	// 警力行政拘留新增
	@Override
	public int administrativeDetentionCreat(CalculationFactor record) {
		return calculationFactorMapper.administrativeDetentionCreat(record);
	}

	// 警力行政拘留详情查询
	@Override
	public CalculationFactor administrativeDetentionItem(Integer id, Integer caseId, Integer policeStationId) {
		return calculationFactorMapper.administrativeDetentionItem(id, caseId, policeStationId);
	}

	// 警力行政拘留修改
	@Override
	public int administrativeDetentionUpdate(CalculationFactor record) {
		return calculationFactorMapper.administrativeDetentionUpdate(record);
	}

	@Override
	public CalculationAlarm getCalculationAlarmBaseNew(Integer policeType, Integer policeStationId) {
		return calculationAlarmMapper.getCalculationAlarmBaseNew(policeType, policeStationId);
	}

	// 今日警力查询
	@Override
	public List<Calculation> todayCalculationList(Integer policeType) {
		return calculationMapper.todayCalculationList(policeType);
	}

	@Override
	public Integer allPoliceCount(Integer policeType) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.allPoliceCount(policeType);
	}

	@Override
	public List<CalculationAlarm> calculationRandTwo(Integer policeType) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.calculationRandTwo(policeType);
	}

	@Override
	public Integer nearDayDifferPoliceNum(Integer policeStationId, Integer policeType, String day) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.nearDayDifferPoliceNum(policeStationId, policeType, day);
	}

	@Override
	public CalculationAlarm maxDifferPoliceNumThirty(Integer isLack, Integer policeType, Integer day) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.maxDifferPoliceNumThirty(isLack, policeType, day);
	}

	@Override
	public CalculationAlarm maxDifferPoliceNum(Integer isLack, Integer policeType) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.maxDifferPoliceNum(isLack, policeType);
	}

	@Override
	public List<CalculationAlarm> newPoliceStationActual(Integer policeType) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.newPoliceStationActual(policeType);
	}

	@Override
	public CalculationAlarm policeStationNewData(Integer policeType, Integer policeStationId) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.policeStationNewData(policeType, policeStationId);
	}

	@Override
	public Integer sumPoliceNum(Integer policeType) {
		// TODO Auto-generated method stub
		return calculationAlarmMapper.sumPoliceNum(policeType);
	}

	// 根据id修改警力测算数据
	@Override
	public int calculationIdUpdate(Calculation calculation) {
		return calculationMapper.calculationIdUpdate(calculation);
	}

	// 警力岗位人数计算
	@Override
	public List<CalculationChart> policepieStatisticsList(Integer policeType, Integer postType) {
		return calculationMapper.policepieStatisticsList(policeType, postType);
	}

	// 根据派出所id和policeType修改
	@Override
	public int pStationUpdate(CalculationPoliceStation pStation) {
		return calculationPoliceStationMapper.pStationUpdate(pStation);
	}

	// 查询当前岗位数量
	@Override
	public CalculationPolicePost calculationPolicePostNumItem(Integer policeType, Integer stationPostId) {
		return calculationPolicePostMapper.calculationPolicePostNumItem(policeType, stationPostId);
	}
	
	// 根据岗位id和民警类型type修改
	@Override
	public int calculationPolicePostUpdate(CalculationPolicePost record) {
		return calculationPolicePostMapper.calculationPolicePostUpdate(record);
	}

}
