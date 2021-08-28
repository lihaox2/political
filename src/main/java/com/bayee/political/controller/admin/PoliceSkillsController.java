package com.bayee.political.controller.admin;

import com.bayee.political.json.TrainCensusResult;
import com.bayee.political.service.*;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import com.bayee.political.domain.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.ExcelUtil;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonAnyFormatVisitor;
import org.apache.poi.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zouya
 */
@RestController
@RequestMapping("/train/v2")
public class PoliceSkillsController {

    @Autowired
    private TrainService trainService;

    @Autowired
    TrainProjectService projectService;

    @Autowired
    UserService userService;

    @Autowired
    PoliceSkillsService policeSkillsService;

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    DecimalFormat df = new DecimalFormat("#.00");

    /**
     * 统计：训练总数、本月训练数、参与总人数、总合格率
     * @return
     */
    @GetMapping("/census")
    public ResponseEntity<?> census(){
        TrainCensusResult result=policeSkillsService.census();
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警员总体合格率top10、综合体能合格率top10、枪械合格率top10
     * @return
     */
    @GetMapping("/rankData")
    public ResponseEntity<?> rankData(){
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.rankData()),HttpStatus.OK);
    }


    /**
     * 近6个月不同训练类型合格情况趋势
     * @return
     */
    @GetMapping("/near6Month")
    public ResponseEntity<?> near6Month(){
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.near6Month()),HttpStatus.OK);
    }

    /**
     * 不同类型训练数量占比
     * @return
     */
    @GetMapping("/trainingQuantity")
    public ResponseEntity<?> trainingQuantity() {
        return new ResponseEntity<>(DataListReturn.ok(policeSkillsService.trainingQuantity()), HttpStatus.OK);
    }

    /**
     * 综合训练
     * @param trainName
     * @param trainBeginDate
     * @param trainEndDate
     * @param trainType
     * @param file
     * @return
     */
    @PostMapping("/import/physicalTrain/data")
    public ResponseEntity<?> importPhysicalTrainData(@RequestParam("trainName") String trainName,
                                                     @RequestParam("trainBeginDate") String trainBeginDate,
//                                                     @RequestParam("trainEndDate") String trainEndDate,
                                                     @RequestParam("trainType") Integer trainType,
                                                     @RequestParam("file") MultipartFile file) {
        TrainPhysical physical = new TrainPhysical();
        physical.setTrainType(trainType);
        physical.setName(trainName);
        physical.setPlace("分局");
        physical.setRegistrationStartDate(DateUtil.parseDate(trainBeginDate));
        physical.setRegistrationEndDate(DateUtil.parseDate(trainBeginDate));
        physical.setTrainStartDate(DateUtil.parseDate(trainBeginDate));
        physical.setTrainEndDate(DateUtil.parseDate(trainBeginDate));
        physical.setTrainGroupIds(StringUtil.join(projectService.findTrainProjectByType(1).parallelStream()
                .map(TrainProject::getId).collect(Collectors.toList()).toArray(), ","));
        physical.setStatus(3);
        physical.setCoverImg("/template-image/physical-template-2.jpg");
        physical.setTrainImg("/template-image/physical-template-2.jpg");
        physical.setCreationDate(new Date());
        physical.setType(2);
        physical.setDepartmentId(1);
        physical.setIsSubmit(1);
        physical.setScorerPoliceId("052805");
        physical.setSignUpStatus(3);
        physical.setIsLimit(0);
        physical.setSubmitDate(new Date());
        trainService.trainPhysicalCreat(physical);

        List<TrainPhysicalAchievementDetails> detailsList = new ArrayList<>();
        List<JSONObject> physicalDataList = ExcelUtil.dataImport(file, 0, 0);
        if (physicalDataList == null) {
            return new ResponseEntity<>(DataListReturn.error("导入失败！"), HttpStatus.OK);
        }

        int i=0;
        List<JSONObject> errorObjList = new ArrayList<>();
        for (JSONObject jsonObj : physicalDataList) {
            i++;
            String policeId = jsonObj.getString("1");

            User user = userService.findByPoliceId(policeId);
            if (user == null || user.getId() == null) {
                errorObjList.add(createErrorJsonObj(i, policeId, "警号不存在"));
                continue;
            }

            TrainPhysicalAchievement physicalAchievement = new TrainPhysicalAchievement();
            physicalAchievement.setPoliceId(policeId);
            physicalAchievement.setRegistrationDate(physical.getRegistrationStartDate());
            physicalAchievement.setTrainPhysicalId(physical.getId());
            physicalAchievement.setIsSign(2);
            physicalAchievement.setCreationDate(new Date());
            physicalAchievement.setAchievementGrade(checkAllQualified(jsonObj));
            physicalAchievement.setSignDate(new Date());
            physicalAchievement.setTrainStartDate(physical.getTrainStartDate());
            physicalAchievement.setIsTestFree("免测".equals(jsonObj.getString("26")) ? 1 : 2);
            physicalAchievement.setRemark(jsonObj.getString("27"));

            if (physicalAchievement.getIsTestFree() == 1) {
                physicalAchievement.setAchievementGrade(0);
            }
            trainService.addTrainPhysicalAchievement(physicalAchievement);

            if (physicalAchievement.getIsTestFree() == 1) {
                continue;
            }
            detailsList.addAll(physicalAchievementDetailsHandle(jsonObj, policeId, physical.getId(), physicalAchievement.getId()));
        }
        trainService.trainPhysicalAchievementDetailsCreatBatch(detailsList);
        totalRiskDetailsService.skillRiskDetails(LocalDate.parse(DateUtil.format(physical.getTrainStartDate(), "yyyy-MM-dd")));

        Map<String, Object> result = new HashMap<>();
        result.put("data", errorObjList);
        result.put("passCount", detailsList.size());
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 检查所有数据项状态
     * @param jsonObj
     * @return
     */
    private Integer checkAllQualified(JSONObject jsonObj) {
        if (checkedQualified(jsonObj.getString("4")) == 1 || checkedQualified(jsonObj.getString("7")) == 1 ||
                checkedQualified(jsonObj.getString("10")) == 1 || checkedQualified(jsonObj.getString("13")) == 1 ||
                checkedQualified(jsonObj.getString("16")) == 1 || checkedQualified(jsonObj.getString("19")) == 1 ||
                checkedQualified(jsonObj.getString("22")) == 1 || checkedQualified(jsonObj.getString("25")) == 1) {
            return 1;
        }else {
            return 2;
        }
    }

    /**
     * 比赛训练处理
     * @param jsonObj
     * @param policeId
     * @param physicalId
     * @param achievementId
     * @return
     */
    private List<TrainPhysicalAchievementDetails> physicalAchievementDetailsHandle(JSONObject jsonObj, String policeId,
                                                                                   Integer physicalId, Integer achievementId) {
        List<TrainPhysicalAchievementDetails> detailsList = new ArrayList<>();
        //2000米跑
        TrainPhysicalAchievementDetails ad1 = createTrainPhysicalAchievementDetails(19, policeId, physicalId,
                achievementId, jsonObj.getString("3"), jsonObj.getString("4"), jsonObj.getString("2"));
        if (ad1 != null) {
            detailsList.add(ad1);
        }

        //仰卧起坐
       TrainPhysicalAchievementDetails ad2 = createTrainPhysicalAchievementDetails(5, policeId, physicalId,
                achievementId, jsonObj.getString("6"), jsonObj.getString("7"), jsonObj.getString("5"));
        if (ad2 != null) {
            detailsList.add(ad2);
        }

        //引体向上
        TrainPhysicalAchievementDetails ad3 = createTrainPhysicalAchievementDetails(6, policeId, physicalId,
                achievementId, jsonObj.getString("9"), jsonObj.getString("10"), jsonObj.getString("8"));
        if (ad3 != null) {
            detailsList.add(ad3);
        }

        //俯卧撑
        TrainPhysicalAchievementDetails ad4 = createTrainPhysicalAchievementDetails(7, policeId, physicalId,
                achievementId, jsonObj.getString("12"), jsonObj.getString("13"), jsonObj.getString("11"));
        if (ad4 != null) {
            detailsList.add(ad4);
        }

        //立定跳远
        TrainPhysicalAchievementDetails ad5 = createTrainPhysicalAchievementDetails(18, policeId, physicalId,
                achievementId, jsonObj.getString("15"), jsonObj.getString("16"), jsonObj.getString("14"));
        if (ad5 != null) {
            detailsList.add(ad5);
        }

        //握力
        TrainPhysicalAchievementDetails ad6 = createTrainPhysicalAchievementDetails(8, policeId, physicalId,
                achievementId, jsonObj.getString("18"), jsonObj.getString("19"), jsonObj.getString("17"));
        if (ad6 != null) {
            detailsList.add(ad6);
        }

        //做立体前屈
        TrainPhysicalAchievementDetails ad7 = createTrainPhysicalAchievementDetails(20, policeId, physicalId,
                achievementId, jsonObj.getString("21"), jsonObj.getString("22"), jsonObj.getString("20"));
        if (ad7 != null) {
            detailsList.add(ad7);
        }

        //50米跑
        TrainPhysicalAchievementDetails ad8 = createTrainPhysicalAchievementDetails(4, policeId, physicalId,
                achievementId, jsonObj.getString("24"), jsonObj.getString("25"), jsonObj.getString("23"));
        if (ad8 != null) {
            detailsList.add(ad8);
        }

        return detailsList;
    }

    /**
     *
     * @param projectId 训练类型id
     * @param policeId 警号
     * @param physicalId 训练id
     * @param achievementId
     * @param score 分数
     * @param achievement 训练成绩：合格,不合格
     * @param projectName 训练项目名称
     * @return
     */
    private TrainPhysicalAchievementDetails createTrainPhysicalAchievementDetails(Integer projectId, String policeId,
                                                                                  Integer physicalId, Integer achievementId,
                                                                                  String scoreStr, String achievement,
                                                                                  String projectName) {
        if (!StrUtil.isNotBlank(projectName) || !StrUtil.isNotBlank(scoreStr)) {
            return null;
        }
        double score = Double.valueOf(scoreStr.replaceAll("\\s*", ""));
        //Double.valueOf(jsonObj.getString("24").replaceAll("\\s*", ""))

        TrainProject project = trainService.getTrainProjectById(projectId);
        double x = score;
        int m = (int) x;
        double y = Double.valueOf(df.format(x - m));

        TrainPhysicalAchievementDetails achievementDetails = new TrainPhysicalAchievementDetails();
        achievementDetails.setTrainPhysicalId(physicalId);
        achievementDetails.setTrainPhysicalAchievementId(achievementId);
        achievementDetails.setProjectId(projectId);
        achievementDetails.setAchievement(score);
        achievementDetails.setAchievementGrade(checkedQualified(achievement));
        achievementDetails.setCreationDate(new Date());
        achievementDetails.setPoliceId(policeId);
        achievementDetails.setIsEntry(1);
        achievementDetails.setSignDate(new Date());
        achievementDetails.setIsSign(2);
        achievementDetails.setAchievementDate(new Date());
        if (projectName.equals("2000米跑")) {
            achievementDetails.setAchievementStr(m + "分" + Double.valueOf(df.format(y * 60.0)) + "秒");
            achievementDetails.setAchievementFirst(m);
            achievementDetails.setAchievementSecond(Double.valueOf(df.format(y * 60.0)));
        } else {
            achievementDetails.setAchievementStr(score + project.getUnitName());
        }
        return achievementDetails;
    }

   /* private JSONObject createErrorJsonObj(String name, String policeId, String errorDesc) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("policeId", policeId);
        jsonObject.put("policeName", name);
        jsonObject.put("errorDesc", errorDesc);
        return jsonObject;
    }*/

    private JSONObject createErrorJsonObj(int count, String policeId, String desc) {
        JSONObject object = new JSONObject();
        object.put("count", count);
        object.put("policeId", policeId);
        object.put("desc", desc);
        return object;
    }

    /**
     * 获取合格状态
     * @param v
     * @return
     */
    private Integer checkedQualified(String v) {
        int flag = 1;
        if (!StrUtil.isNotBlank(v)) {
            flag = 3;
        } else if ("合格".equals(v)) {
            flag = 2;
        } else if ("不合格".equals(v)) {
            flag = 1;
        }
        return flag;
    }

}
