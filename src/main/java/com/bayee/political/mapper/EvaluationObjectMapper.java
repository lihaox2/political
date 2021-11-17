package com.bayee.political.mapper;

import com.bayee.political.domain.EvaluationObject;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationObjectMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationObject record);

    int insertSelective(EvaluationObject record);

    EvaluationObject selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluationObject record);

    int updateByPrimaryKey(EvaluationObject record);

    /**
     * 评价对象-查询所有
     * @return
     */
    List<EvaluationObject> findAll();

    /**
     * 警号查询
     * @return
     */
    EvaluationObject selectByPoliceId(@Param("policeId") String policeId);

    /**
     * 评价对象-查询所有警员
     * @return
     */
    int countAllObject();
}
