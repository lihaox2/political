/*
package com.bayee.political.service;

import com.bayee.political.domain.EvaluationInfo;
import com.bayee.political.json.*;
import com.bayee.political.pojo.EvaluationPageQueryResultDO;
import com.bayee.political.utils.DataListReturn;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

*/
/**
 * @author tlt
 * @date 2021/10/25
 *//*

public interface EvaluationInfoService {

    */
/**
     * 评价信息-新增记录
     * @param saveParam
     *//*

    void addEvaluation(EvaluationSaveParam saveParam);

    */
/**
     * 评价信息-分页查询
     * @param queryParam
     * @return
     *//*

    List<EvaluationPageQueryResultDO> evaluationPage(EvaluationPageQueryParam queryParam);

    */
/**
     * 评价信息-查询已评价人与评价对象数量
     * @return
     *//*

    EvaluationPageCountResult evaluationCount(Integer activityId);

    */
/**
     * 评价信息-详情查询
     * @param id
     * @return
     *//*

    EvaluationInfo findById(Integer id);

    */
/**
     * 评价信息-查询已评价人
     * @param activityId
     * @return
     *//*

    Integer countHaveEvaluation(Integer activityId);

    */
/**
     * 评价信息-查询是否参与评价
     * @param startParam
     * @return
     *//*

    EvaluationInfo selectByStartParam(EvaluationStartParam startParam);

    */
/**
     * 评价信息-开始评价
     * @return
     *//*

    DataListReturn startEvaluation(EvaluationStartParam startParam);

    */
/**
     * 评价信息-导出数据
     * @param response
     * @param queryParam
     *//*

    void exportExcel(HttpServletResponse response, EvaluationPageQueryParam queryParam);
}
*/
