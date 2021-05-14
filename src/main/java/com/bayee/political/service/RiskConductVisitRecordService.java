package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConductVisitRecord;

public interface RiskConductVisitRecordService {
	
	int deleteByPrimaryKey(Integer id);

    int insert(RiskConductVisitRecord record);

    int insertSelective(RiskConductVisitRecord record);

    RiskConductVisitRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConductVisitRecord record);

    int updateByPrimaryKey(RiskConductVisitRecord record);
    
    List<RiskConductVisitRecord> riskConductVisitRecordList(String policeId,String dateTime, String lastMonthTime, Integer timeType);
    
    Integer selectByName(String name);

    /**
     * 分页查询
     * @param pageIndex
     * @param pageSize
     * @param bigType
     * @param smallType
     * @param key
     * @return
     */
    List<RiskConductVisitRecord> riskConductVisitRecordPage(Integer pageIndex, Integer pageSize, Integer bigType,
                                                            Integer smallType, String key);

    /**
     * 统计分页数据条数
     * @param bigType
     * @param smallType
     * @param key
     * @return
     */
    Integer getRiskConductVisitRecordPageCount(Integer bigType, Integer smallType, String key);

    /**
     * 根绝类型统计树蕨条数
     * @param typeId
     * @return
     */
    Integer countByTypeId(Integer typeId);

}
