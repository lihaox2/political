package com.bayee.political.service;

import com.bayee.political.domain.EvaluationActivity;
import com.bayee.political.json.ActivityPageQueryParam;
import com.bayee.political.json.ActivityParticipationResult;
import com.bayee.political.pojo.ActivityPageQueryResultDO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;


/**
 * @author tlt
 * @date 2021/10/22
 */
public interface EvaluationActivityService {

    /**
     *评价活动-新增记录
     * @param evaluationActivity
     */
    void addEvaluationActivity(EvaluationActivity evaluationActivity);

    /**
     * 评价活动-修改记录0000
     * @param evaluationActivity
     */
    void updateEvaluationActivity(EvaluationActivity evaluationActivity);

    /**
     * 评价活动-删除记录
     * @param id
     */
    void deleteEvaluationActivity(Integer id);

    /**
     * 接警执勤-详情查询
     * @param id
     * @return
     */
    EvaluationActivity findById(Integer id);

    /**
     *评价活动-分页查询
     * @param queryParam
     * @return
     */
    List<ActivityPageQueryResultDO> activityPage(ActivityPageQueryParam queryParam);

    /**
     * 分页统计
     * @param queryParam
     * @return
     */
    Integer activityPageCount(ActivityPageQueryParam queryParam);

    /**
     * 评价活动-结束活动
     * @param id
     */
    void overActivity(Integer id);

    /**
     * 评价活动-导出数据
     * @param response
     * @param queryParam
     */
    void exportExcel(HttpServletResponse response, String fileName, ActivityPageQueryParam queryParam);

    /**
     * 评价活动-查询所有已开始活动
     * @return
     */
    ActivityParticipationResult findStartedSatusActivity(Integer userId);
}

