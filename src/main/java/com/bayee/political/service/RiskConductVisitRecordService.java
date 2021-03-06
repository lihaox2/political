package com.bayee.political.service;

import java.util.List;

import com.bayee.political.domain.RiskConductVisitRecord;
import org.apache.ibatis.annotations.Param;

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
     * @param key
     * @return
     */
    List<RiskConductVisitRecord> riskConductVisitRecordPage(Integer pageIndex, Integer pageSize, String type, String key,
                                                            Integer deptId);

    /**
     * 统计分页数据条数
     * @param key
     * @return
     */
    Integer getRiskConductVisitRecordPageCount(String type, String key, Integer deptId);

    /**
     * 根绝类型统计数据条数
     * @param typeId
     * @return
     */
    Integer countByTypeId(Integer typeId);

    /**
     * 统计所有信访投诉数据
     * @return
     */
    Integer countAll();

    /**
     * 根据年份和月份进行查询
     * @param conductVisitRecordYear
     * @param conductVisitRecordMonth
     * @param policeId
     * @return
     */
    List<RiskConductVisitRecord> findConductVisitRecordYearAndMont(
            @Param("conductVisitRecordYear") String conductVisitRecordYear,
            @Param("conductVisitRecordMonth") String conductVisitRecordMonth,
            @Param("policeId") String policeId
    );

    /**
     * 通过警督平台key进行查询
     * @param inspectorKey
     * @return
     */
    Integer findByInspectorKey(String inspectorKey);

}
