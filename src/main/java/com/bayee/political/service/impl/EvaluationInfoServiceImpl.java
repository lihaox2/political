/*
package com.bayee.political.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bayee.evaluation.common.web.JsonResult;
import com.bayee.evaluation.common.web.PageHandler;
import com.bayee.evaluation.common.web.PageParam;
import com.bayee.evaluation.domain.*;
import com.bayee.evaluation.json.*;
import com.bayee.evaluation.mapper.*;
import com.bayee.evaluation.pojo.EvaluationPageQueryResultDO;
import com.bayee.evaluation.service.EvaluationInfoService;
import com.bayee.evaluation.utils.DateUtils;
import com.bayee.political.domain.EvaluationInfo;
import com.bayee.political.json.EvaluationSaveParam;
import com.bayee.political.json.EvaluationTopicSaveParam;
import com.bayee.political.mapper.*;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

*/
/**
 * @author tlt
 * @date 2021/10/25
 *//*

@Service
public class EvaluationInfoServiceImpl implements EvaluationInfoService {

    @Autowired
    private EvaluationInfoMapper evaluationMapper;

    @Autowired
    private EvaluationActivityMapper evaluationActivityMapper;

    @Autowired
    private EvaluationTopicMapper evaluationTopicMapper;

    @Autowired
    private EvaluationObjectMapper evaluationObjectMapper;

    @Autowired
    private EvaluationInfoUserMapper userMapper;

    @Override
    public void addEvaluation(EvaluationSaveParam saveParam) {
        EvaluationInfo evaluation = new EvaluationInfo();
        evaluation.setActivityId(saveParam.getActivityId());
        evaluation.setUserId(saveParam.getUserId());
        evaluation.setBusinessTime(new Date());
        evaluation.setCreationDate(new Date());

        List<EvaluationTopicSaveParam> topicList = saveParam.getTopicList();

        Integer totalScore = 0;
        for (EvaluationTopicSaveParam topic : topicList) {
            EvaluationTopic evaluationTopic = new EvaluationTopic();
            evaluationTopic.setIsChoose(topic.getIsChoose());
            evaluationTopic.setUserId(saveParam.getUserId());
            evaluationTopic.setCreationDate(new Date());
            evaluationTopic.setTopicName(topic.getTopicName());
            evaluationTopic.setActivityId(saveParam.getActivityId());
            evaluationTopicMapper.insert(evaluationTopic);

            if (topic.getIsChoose() == 1) {
                totalScore++;
            }
        }

        evaluation.setTotalScore(totalScore);

        evaluationMapper.insert(evaluation);
    }

    @Override
    public PageHandler<EvaluationPageQueryResultDO> evaluationPage(PageParam pageParam, EvaluationPageQueryParam queryParam) {
        PageHelper.startPage(pageParam.getPageIndex(), pageParam.getPageSize());

        return new PageHandler<>(new PageInfo<>(evaluationMapper.evaluationPage(queryParam)));
    }

    @Override
    public EvaluationPageCountResult evaluationCount(Integer activityId) {
        EvaluationActivity activity = evaluationActivityMapper.selectByPrimaryKey(activityId);
        Integer objectCount = evaluationObjectMapper.countAllObject();
        Integer haveEvaluationCount = evaluationMapper.countHaveEvaluation(activityId);
        Integer noEvaluationCount = objectCount - haveEvaluationCount;
        EvaluationPageCountResult result = new EvaluationPageCountResult();
        result.setObjectCount(objectCount);
        result.setHaveEvaluationCount(haveEvaluationCount);
        result.setNoEvaluationCount(noEvaluationCount);
        result.setActivityName(activity.getActivityName());
        return result;
    }

    @Override
    public Evaluation findById(Integer id) {
        return evaluationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countHaveEvaluation(Integer activityId) {
        return evaluationMapper.countHaveEvaluation(activityId);
    }

    @Override
    public Evaluation selectByStartParam(EvaluationStartParam startParam) {
        return evaluationMapper.selectByStartParam(startParam);
    }

    @Override
    public JsonResult<EvaluationStartResult> startEvaluation(EvaluationStartParam startParam) {
        EvaluationActivity activity = evaluationActivityMapper.selectByPrimaryKey(startParam.getActivityId());
        if (activity != null && activity.getActivityStatus() == 1) {
            Evaluation evaluation = evaluationMapper.selectByStartParam(startParam);
            if (evaluation != null) {
                return JsonResult.error("您已参与本次评价活动！");
            } else {
                EvaluationTopic evaluationTopic = new EvaluationTopic();
                evaluationTopic.setActivityId(startParam.getActivityId());
                List<EvaluationTopic> evaluationTopicList = evaluationTopicMapper.selectByActityId(evaluationTopic);
                User user = userMapper.selectByPrimaryKey(startParam.getUserId());
                EvaluationObject object = evaluationObjectMapper.selectByPoliceId(user.getFamilyPoliceId());
                EvaluationStartResult startResult = new EvaluationStartResult();
                startResult.setActivityId(activity.getId());
                startResult.setActivityName(activity.getActivityName());
                startResult.setObjectName(object.getObjectName());
                startResult.setTopicCount(evaluationTopicList.size());
                startResult.setTopicList(evaluationTopicList);
                return JsonResult.ok(startResult);
            }
        } else {
            return JsonResult.error("本次评价活动未开始！");
        }
    }

    @Override
    public void exportExcel(HttpServletResponse response, EvaluationPageQueryParam queryParam) {
        List<EvaluationPageQueryResultDO> dataList = evaluationMapper.evaluationPage(queryParam);
        EvaluationActivity activity = evaluationActivityMapper.selectByPrimaryKey(queryParam.getActivityId());
        List<EvaluationExportResult> exportResultsList = new ArrayList<>();
        for(int i = 0; i < dataList.size(); i++){
            EvaluationExportResult exportResults = new EvaluationExportResult();
            exportResults.setSerialNum(i+1);
            exportResults.setAppraiserName(dataList.get(i).getAppraiserName());
            exportResults.setAppraiserPhone(dataList.get(i).getAppraiserPhone());
            exportResults.setObjectName(dataList.get(i).getObjectName());
            exportResults.setTotalScore(dataList.get(i).getTotalScore());
            exportResults.setBusinessTime(dataList.get(i).getBusinessTime());
            User user = userMapper.selectByPrimaryKey(dataList.get(i).getUserId());
            exportResults.setPoliceId(user.getFamilyPoliceId());
            exportResults.setBelongMonth(DateUtils.formatDate(activity.getBelongMonth(),"yyyy-MM"));
            exportResultsList.add(exportResults);
        }

        // 通过工具类创建writer，默认创建xls格式
        ExcelWriter writer = ExcelUtil.getWriter();
        //自定义标题别名
        writer.addHeaderAlias("serialNum", "序号");
        writer.addHeaderAlias("belongMonth", "所属年月");
        writer.addHeaderAlias("policeId", "警号");
        writer.addHeaderAlias("objectName", "姓名");
        writer.addHeaderAlias("totalScore", "评价得分");
        writer.addHeaderAlias("appraiserName", "评价人");
        writer.addHeaderAlias("appraiserPhone", "评价人账号");
        writer.addHeaderAlias("businessTime", "评价时间");
        // 合并单元格后的标题行，使用默认标题样式
        writer.merge(7, activity.getActivityName());
        // 一次性写出内容，使用默认样式，强制输出标题
        writer.write(exportResultsList, true);

        String fileName = activity.getActivityName() + "_" + activity.getIdNum() + ".xlsx";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        try {
            response.setHeader("Content-disposition", "attachment;filename=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        */
/* response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");*//*


        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭writer，释放内存
            writer.close();
        }
        //此处记得关闭输出Servlet流
        IoUtil.close(out);

        */
/*JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(dataList));
        ExcelUtil.exportData(response, fileName, map, jsonArray);*//*

    }
}
*/
