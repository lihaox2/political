package com.bayee.political.service;

import com.bayee.political.domain.RiskRelevantRecord;
import com.bayee.political.json.RiskRelevantPageQueryParam;
import com.bayee.political.pojo.RiskRelevantItemRecordResultDO;
import com.bayee.political.pojo.RiskRelevantPageResultDO;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:09
 */
public interface RiskRelevantRecordService {

    /**
     * 分页查询动态排摸
     * @param queryParam
     * @return
     */
    List<RiskRelevantPageResultDO> policeRelevantPage(RiskRelevantPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer policeRelevantPageCount(RiskRelevantPageQueryParam queryParam);

    /**
     * 新增方法
     * @param policeRelevant
     */
    void insertPoliceRelevant(RiskRelevantRecord policeRelevant);

    /**
     * 修改
     * @param policeRelevant
     */
    void updatePoliceRelevant(RiskRelevantRecord policeRelevant);

    /**
     * 根据id删除
     * @param id
     */
    void deleteById(Integer id);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    RiskRelevantRecord findById(Integer id);

    /**
     * 警员风险查询
     * @param policeId
     * @param date yyyy-MM
     * @param timeType
     * @return
     */
    RiskRelevantItemRecordResultDO riskRelevantItemRecord(String policeId, String date, Integer timeType);

}
