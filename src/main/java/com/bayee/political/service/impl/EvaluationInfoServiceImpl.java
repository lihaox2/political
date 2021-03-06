package com.bayee.political.service.impl;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bayee.political.domain.*;
import com.bayee.political.json.*;
import com.bayee.political.mapper.*;
import com.bayee.political.pojo.EvaluationPageQueryResultDO;
import com.bayee.political.service.EvaluationInfoService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author tlt
 * @date 2021/10/25
 */

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
    private EvaluationInfoUserMapper evaluationInfoUserMapper;

    @Autowired
    private UserMapper userMapper;

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
    public List<EvaluationPageQueryResultDO> evaluationPage(EvaluationPageQueryParam queryParam) {
        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return evaluationMapper.evaluationPage(queryParam);
    }

    @Override
    public Integer evaluationPageCount(EvaluationPageQueryParam queryParam) {
        return evaluationMapper.evaluationPageCount(queryParam);
    }

    @Override
    public EvaluationPageCountResult evaluationCount(Integer activityId) {
        EvaluationActivity activity = evaluationActivityMapper.selectByPrimaryKey(activityId);
        Integer objectCount = userMapper.countAllPolice();
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
    public EvaluationInfo findById(Integer id) {
        return evaluationMapper.selectByPrimaryKey(id);
    }

    @Override
    public Integer countHaveEvaluation(Integer activityId) {
        return evaluationMapper.countHaveEvaluation(activityId);
    }

    @Override
    public EvaluationInfo selectByStartParam(EvaluationStartParam startParam) {
        return evaluationMapper.selectByStartParam(startParam);
    }

    @Override
    public DataListReturn startEvaluation(EvaluationStartParam startParam) {
        EvaluationActivity activity = evaluationActivityMapper.selectByPrimaryKey(startParam.getActivityId());
        if (activity != null && activity.getActivityStatus() == 1) {
            EvaluationInfo evaluation = evaluationMapper.selectByStartParam(startParam);
            if (evaluation != null) {
                return DataListReturn.error("?????????????????????????????????");
            } else {
                EvaluationTopic evaluationTopic = new EvaluationTopic();
                evaluationTopic.setActivityId(startParam.getActivityId());
                List<EvaluationTopic> evaluationTopicList = evaluationTopicMapper.selectByActityId(evaluationTopic);
                EvaluationInfoUser evaluationInfoUser = evaluationInfoUserMapper.selectByPrimaryKey(startParam.getUserId());
                User user = userMapper.findByPoliceId(evaluationInfoUser.getFamilyPoliceId());
                EvaluationStartResult startResult = new EvaluationStartResult();
                startResult.setActivityId(activity.getId());
                startResult.setActivityName(activity.getActivityName());
                startResult.setObjectName(user.getName());
                startResult.setTopicCount(evaluationTopicList.size());
                startResult.setTopicList(evaluationTopicList);
                return DataListReturn.ok(startResult);
            }
        } else {
            return DataListReturn.error("??????????????????????????????");
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
            EvaluationInfoUser user = evaluationInfoUserMapper.selectByPrimaryKey(dataList.get(i).getUserId());
            exportResults.setPoliceId(user.getFamilyPoliceId());
            exportResults.setBelongMonth(DateUtils.formatDate(activity.getBelongMonth(),"yyyy-MM"));
            exportResultsList.add(exportResults);
        }

        // ?????????????????????writer???????????????xls??????
        ExcelWriter writer = ExcelUtil.getWriter();
        //?????????????????????
        writer.addHeaderAlias("serialNum", "??????");
        writer.addHeaderAlias("belongMonth", "????????????");
        writer.addHeaderAlias("policeId", "??????");
        writer.addHeaderAlias("objectName", "??????");
        writer.addHeaderAlias("totalScore", "????????????");
        writer.addHeaderAlias("appraiserName", "?????????");
        writer.addHeaderAlias("appraiserPhone", "???????????????");
        writer.addHeaderAlias("businessTime", "????????????");
        // ?????????????????????????????????????????????????????????
        writer.merge(7, activity.getActivityName());
        // ???????????????????????????????????????????????????????????????
        writer.write(exportResultsList, true);

        String fileName = activity.getActivityName() + "_" + activity.getIdNum() + ".xlsx";
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        //test.xls??????????????????????????????????????????????????????????????????????????????
        try {
            response.setHeader("Content-disposition", "attachment;filename=" +
                    new String(fileName.getBytes("gb2312"), "ISO8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        /* response.setHeader("Content-Disposition", "attachment;filename=" + name + ".xls");*/

        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            writer.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // ??????writer???????????????
            writer.close();
        }
        //????????????????????????Servlet???
        IoUtil.close(out);

        /*JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(dataList));
        ExcelUtil.exportData(response, fileName, map, jsonArray);*/

    }
}
