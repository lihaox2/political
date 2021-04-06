package com.bayee.political.mapper;

import com.bayee.political.domain.RiskConduct;
import com.bayee.political.domain.ScreenDoubeChart;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface RiskConductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskConduct record);

    int insertSelective(RiskConduct record);

    RiskConduct selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskConduct record);

    int updateByPrimaryKey(RiskConduct record);

    /**
     * ��ѯ��Ա��Ϊ�淶����ָ��
     * @param policeId
     * @param date
     * @return
     */
    RiskConduct findByPoliceIdAndDate(@Param("policeId") String policeId,@Param("date") String date);

    /**
     * ��ѯ��Ա��Ϊ�淶����ָ��ͼ��
     * @param policeId
     * @return
     */
    List<ScreenDoubeChart> findRiskConductChart(String policeId);

    /**
     * ����ͳ��
     * @param policeId
     * @param date
     * @return
     */
    List<Map<String, Object>> countByConductType(@Param("policeId") String policeId,@Param("date") String date);

    /**
     * ��ѯ�����ص�״̬ & �ܹ���������
     * @param policeId
     * @param date
     * @return
     */
    Map<String, Object> findMostSeriousStatusAndTotalCount(@Param("policeId") String policeId,@Param("date") String date);

}