package com.bayee.political.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.bayee.political.domain.EvaluationActivity;
import com.bayee.political.domain.EvaluationInfo;
import com.bayee.political.domain.EvaluationInfoUser;
import com.bayee.political.json.*;
import com.bayee.political.mapper.*;
import com.bayee.political.pojo.ActivityPageQueryResultDO;
import com.bayee.political.service.EvaluationActivityService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DateUtils;
import com.bayee.political.utils.ExcelUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * @author tlt
 * @date 2021/10/22
 */

@Service
public class EvaluationActivityServiceImpl implements EvaluationActivityService {

    @Autowired
    private EvaluationActivityMapper evaluationActivityMapper;

    @Autowired
    private EvaluationTopicMapper evaluationTopicMapper;
    
    @Autowired
    private EvaluationInfoMapper evaluationMapper;

    @Autowired
    private EvaluationObjectMapper evaluationObjectMapper;

    @Autowired
    private EvaluationInfoUserMapper EvaluationInfoUserMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public void addEvaluationActivity(EvaluationActivity evaluationActivity) {
        evaluationActivityMapper.insert(evaluationActivity);
    }

    @Override
    public void updateEvaluationActivity(EvaluationActivity evaluationActivity) {
        evaluationActivityMapper.updateByPrimaryKey(evaluationActivity);
    }

    @Override
    public void deleteEvaluationActivity(Integer id) {
        evaluationActivityMapper.deleteByPrimaryKey(id);
        evaluationTopicMapper.deleteByActivityId(id);
        evaluationMapper.deleteByActivityId(id);
    }

    @Override
    public EvaluationActivity findById(Integer id) {
        return evaluationActivityMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<ActivityPageQueryResultDO> activityPage(ActivityPageQueryParam queryParam) {

        if (queryParam.getPageIndex() == null || queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        if (queryParam.getPageSize() == null) {
            queryParam.setPageSize(10);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        List<ActivityPageQueryResultDO> resultList = evaluationActivityMapper.activityPage(queryParam);

        for(int i = 0; i < resultList.size(); i++){
            resultList.get(i).setTopicCount(evaluationTopicMapper.countByPrimaryKey(resultList.get(i).getId()));
            resultList.get(i).setHaveEvaluationCount(evaluationMapper.countHaveEvaluation(resultList.get(i).getId()));
        }

        return resultList;
    }

    @Override
    public Integer activityPageCount(ActivityPageQueryParam queryParam) {
        return evaluationActivityMapper.activityPageCount(queryParam);
    }

    @Override
    public void overActivity(Integer id) {
        evaluationActivityMapper.updateStatusById(id);
    }

    @Override
    public void exportExcel(HttpServletResponse response, String fileName, ActivityPageQueryParam queryParam) {
        List<ActivityPageQueryResultDO> dataList = evaluationActivityMapper.activityPage(queryParam);

        for(int i = 0; i < dataList.size(); i++){
            dataList.get(i).setTopicCount(evaluationTopicMapper.countByPrimaryKey(dataList.get(i).getId()));
            dataList.get(i).setHaveEvaluationCount(evaluationMapper.countHaveEvaluation(dataList.get(i).getId()));
        }
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        map.put("idNum", "ID编号");
        map.put("activityName", "活动名称");
        map.put("belongMonth", "所属月份");
        map.put("activityStatus", "活动状态");
        map.put("topicCount", "题目数");
        map.put("haveEvaluationCount", "已评价数");
        map.put("creationDate", "创建时间");

        JSONArray jsonArray = JSON.parseArray(JSON.toJSONString(dataList));
        ExcelUtil.exportData(response, fileName, map, jsonArray);
    }

    @Override
    public ActivityParticipationResult findStartedSatusActivity(Integer userId) {
        List<EvaluationActivity> activityList = evaluationActivityMapper.selectStartedSatusActivity();

        List<ActivityStart> havaParticipationActivityList = new ArrayList<>();
        List<ActivityStart> noParticipationActivityList = new ArrayList<>();
        for (EvaluationActivity activity : activityList) {
            EvaluationInfoUser user = EvaluationInfoUserMapper.selectByPrimaryKey(userId);
            ActivityStartResult startResult = new ActivityStartResult();
            startResult.setActivityName(activity.getActivityName());
            startResult.setObjectName(userMapper.getUserNameByPoliceIds(user.getFamilyPoliceId()));
            startResult.setId(activity.getId());
            startResult.setCreationYear(DateUtils.formatDate(activity.getCreationDate(), "yyyy"));
            startResult.setTopicCount(evaluationTopicMapper.countByPrimaryKey(activity.getId()));

            EvaluationStartParam startParam = new EvaluationStartParam();
            startParam.setActivityId(activity.getId());
            startParam.setUserId(userId);
            EvaluationInfo evaluation = evaluationMapper.selectByStartParam(startParam);
            // 已评价
            if (evaluation != null) {
                startResult.setEvaluationId(evaluation.getId());

                boolean flag = false;
                for(int i = 0; i < havaParticipationActivityList.size(); i++) {
                    ActivityStart start = havaParticipationActivityList.get(i);
                    List<ActivityStartResult> startList = start.getActivityList();
                    if (start.getYear().equals(startResult.getCreationYear())) {
                        startList.add(startResult);
                        start.setActivityList(startList);
                        havaParticipationActivityList.remove(i);
                        havaParticipationActivityList.add(start);
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    ActivityStart start = new ActivityStart();
                    List<ActivityStartResult> startList = new ArrayList<>();
                    startList.add(startResult);
                    start.setYear(startResult.getCreationYear());
                    start.setActivityList(startList);
                    havaParticipationActivityList.add(start);
                }

            } else {  // 未评价

                boolean flag = false;
                for(int i = 0; i < noParticipationActivityList.size(); i++) {
                    ActivityStart start = noParticipationActivityList.get(i);
                    List<ActivityStartResult> startList = start.getActivityList();
                    if (start.getYear().equals(startResult.getCreationYear())) {
                        startList.add(startResult);
                        start.setActivityList(startList);
                        noParticipationActivityList.remove(i);
                        noParticipationActivityList.add(start);
                        flag = true;
                        break;
                    }
                }

                if (!flag) {
                    ActivityStart start = new ActivityStart();
                    List<ActivityStartResult> startList = new ArrayList<>();
                    startList.add(startResult);
                    start.setYear(startResult.getCreationYear());
                    start.setActivityList(startList);
                    noParticipationActivityList.add(start);
                }
            }
        }
        ActivityParticipationResult result = new ActivityParticipationResult();
        result.setHavaParticipationActivityList(havaParticipationActivityList);
        result.setNoParticipationActivityList(noParticipationActivityList);

        return result;
    }
}
