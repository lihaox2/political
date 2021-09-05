package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceRelevant;
import com.bayee.political.json.PoliceRelevantPageQueryParam;
import com.bayee.political.pojo.PoliceRelevantPageResultDO;

import java.util.List;

public interface PoliceRelevantMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceRelevant record);

    int insertSelective(PoliceRelevant record);

    PoliceRelevant selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PoliceRelevant record);

    int updateByPrimaryKey(PoliceRelevant record);

    /**
     * 分页查询动态排摸
     * @param queryParam
     * @return
     */
    List<PoliceRelevantPageResultDO> policeRelevantPage(PoliceRelevantPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer policeRelevantPageCount(PoliceRelevantPageQueryParam queryParam);
}
