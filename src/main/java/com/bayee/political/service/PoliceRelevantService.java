package com.bayee.political.service;

import com.bayee.political.domain.PoliceRelevant;
import com.bayee.political.json.PoliceRelevantPageQueryParam;
import com.bayee.political.pojo.PoliceRelevantPageResultDO;

import java.util.List;

/**
 * @author xxl
 * @date 2021/9/4 19:09
 */
public interface PoliceRelevantService {

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

    /**
     * 新增方法
     * @param policeRelevant
     */
    void insertPoliceRelevant(PoliceRelevant policeRelevant);

    /**
     * 修改
     * @param policeRelevant
     */
    void updatePoliceRelevant(PoliceRelevant policeRelevant);

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
    PoliceRelevant findById(Integer id);

}
