package com.bayee.political.mapper;

import com.bayee.political.domain.EvaluationInfo;
import com.bayee.political.json.EvaluationPageQueryParam;
import com.bayee.political.json.EvaluationStartParam;
import com.bayee.political.pojo.EvaluationPageQueryResultDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationInfo record);

    int insertSelective(EvaluationInfo record);

    EvaluationInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluationInfo record);

    int updateByPrimaryKey(EvaluationInfo record);

    /**
     * 评价信息-分页查询
     * @param queryParam
     * @return
     */
    List<EvaluationPageQueryResultDO> evaluationPage(@Param("param") EvaluationPageQueryParam queryParam);

    /**
     * 评价信息-分页统计
     * @param queryParam
     * @return
     */
    Integer evaluationPageCount(@Param("param") EvaluationPageQueryParam queryParam);

     /**
     * 评价信息-查询已评价人数
     * @param id
     * @return
     */
    int countHaveEvaluation(@Param("id") Integer id);


     /**
     * 查询是参与否评价
     * @param startParam
     * @return
     */
    EvaluationInfo selectByStartParam(@Param("param") EvaluationStartParam startParam);


     /**
     *按活动id删除评价
     * @param activityId
     */
    void deleteByActivityId(@Param("activityId") Integer activityId);
}
