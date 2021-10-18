package com.bayee.political.mapper;

import com.bayee.political.domain.RiskRelevantRecord;
import com.bayee.political.json.RiskRelevantPageQueryParam;
import com.bayee.political.pojo.RiskRelevantPageResultDO;

import java.util.List;

public interface RiskRelevantRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RiskRelevantRecord record);

    int insertSelective(RiskRelevantRecord record);

    RiskRelevantRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RiskRelevantRecord record);

    int updateByPrimaryKey(RiskRelevantRecord record);

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
}
