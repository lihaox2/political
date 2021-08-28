package com.bayee.political.controller.admin;

import com.bayee.political.json.*;
import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/20
 */
@RestController
@RequestMapping("/train")
public class AdminTrainController {

    @Autowired
    UserService userService;

    @Autowired
    TrainPhysicalService trainPhysicalService;

    @Autowired
    TrainService trainService;

    @Autowired
    TrainFirearmService trainFirearmService;

    @Autowired
    TrainFirearmAchievementService trainFirearmAchievementService;

    @Autowired
    TrainPhysicalAchievementService trainPhysicalAchievementService;

    @Autowired
    TrainPhysicalAchievementDetailsService trainPhysicalAchievementDetailsService;

    DecimalFormat df = new DecimalFormat(".00");

    @GetMapping("/physical/page")
    public ResponseEntity<?> trainPhysicalPage(@RequestParam("pageIndex") Integer pageIndex,
                                               @RequestParam("pageSize") Integer pageSize,
                                               @RequestParam("trainName") String trainName,
                                               @RequestParam(value = "trainBeginDate", required = false) String trainBeginDate,
                                               @RequestParam(value = "trainEndDate", required = false) String trainEndDate,
                                               @RequestParam("position") Integer position) {
        List<TrainPhysicalPageResult> resultList = trainPhysicalService.findTrainPhysicalPage(pageIndex, pageSize,
                trainBeginDate, trainEndDate, trainName, position).stream().map(e -> {
                    Integer signUpNum = trainService.signUpNum(e.getId());
                    Integer qualifiedNum = trainService.qualifiedNum(e.getId(), 2);
                    Integer unqualifiedNum = trainService.qualifiedNum(e.getId(), 1);

                    TrainPhysicalPageResult pageResult = new TrainPhysicalPageResult();
                    pageResult.setId(e.getId());
                    pageResult.setTrainName(e.getName());
                    pageResult.setTrainDate(DateUtils.formatDate(e.getTrainStartDate(), "yyyy/MM/dd"));
                    pageResult.setEligibleCount(qualifiedNum);
                    pageResult.setNotEligibleCount(unqualifiedNum);
                    pageResult.setTrainType(e.getTrainType());

                    return pageResult;
                }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("data", resultList);
        result.put("totalCount", trainPhysicalService.countTrainPhysicalPage(trainBeginDate, trainEndDate, trainName, position));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/physical/details/label")
    public ResponseEntity<?> trainPhysicalDetailsLabel(@RequestParam("physicalId") Integer physicalId,
                                                       @RequestParam("position") Integer position) {
        Integer userNum = userService.countAllPolice();
        //参加人数
        Integer signUpNum = trainService.signUpNum(physicalId);
        //合格人数
        Integer qualifiedNum = trainService.qualifiedNum(physicalId, 2);
        //免测人数
        Integer unTestNum = trainService.unTestPoliceCount(physicalId);

        Integer unqualifiedNum = trainService.qualifiedNum(physicalId, 1);

        TrainPhysicalDetailsLabelResult result = new TrainPhysicalDetailsLabelResult();
        result.setAttendCount(signUpNum);
        result.setNotAttendCount(userNum - signUpNum);
        result.setEligibleCount(qualifiedNum);
        result.setNotEligibleCount(unqualifiedNum);
        result.setUnTestCount(unTestNum);

        result.setEligibleRatio(0d);
        result.setNotEligibleRatio(0d);
        if (signUpNum > 0) {
            result.setEligibleRatio(Double.valueOf(df.format(((double)qualifiedNum / signUpNum) * 100)));
            result.setNotEligibleRatio(Double.valueOf(df.format(((double)unqualifiedNum / signUpNum) * 100)));
            result.setUnTestRatio(Double.valueOf(df.format(((double)unTestNum / signUpNum) * 100)));
        }
        result.setOntToTwoNotOverCount(trainService.getNotEligibleCount(physicalId,1, 2));
        result.setThreeToFiveNotOverCount(trainService.getNotEligibleCount(physicalId, 3, 4));
        result.setMoreThanFiveNotOverCount(trainService.getNotEligibleCount(physicalId, 5, null));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/physical/details/page")
    public ResponseEntity<?> trainPhysicalDetailsPage(@RequestParam("pageIndex") Integer pageIndex,
                                                      @RequestParam("pageSize") Integer pageSize,
                                                      @RequestParam("physicalId") Integer physicalId,
                                                      @RequestParam("position") Integer position,
                                                      @RequestParam("key") String key,
                                                      @RequestParam("trainStr") String trainStr,
                                                      @RequestParam("trainFlag") Integer trainFlag,
                                                      @RequestParam("searchFlag") Integer searchFlag,
                                                      @RequestParam("deptId") Integer deptId) {
        List<TrainPhysicalDetailsPageResult> resultList = trainPhysicalAchievementService.
                findTrainPhysicalAchievementPage(pageIndex, pageSize, physicalId, position, key,
                        trainStr, trainFlag, searchFlag, deptId).stream().map(e -> {
            TrainPhysicalDetailsPageResult pageResult = new TrainPhysicalDetailsPageResult();
            pageResult.setId(e.getId());
            pageResult.setPoliceName(e.getName());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setDeptName(e.getDepartmentName());
            pageResult.setEligibleCount(trainPhysicalAchievementService.countPhysicalByAchievementGrade(e.getId(), e.getPoliceId(), 2));
            pageResult.setNotEligibleCount(trainPhysicalAchievementService.countPhysicalByAchievementGrade(e.getId(), e.getPoliceId(), 1));
            pageResult.setEligibleFlag(e.getIsTestFree() == 1 ? "免测" : e.getAchievementGrade() == null ? "--" : e.getAchievementGrade() == 1 ? "不合格"
                    : e.getAchievementGrade() == 2 ? "合格" : "");
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy/MM/dd HH:mm:ss"));
            return pageResult;
        }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("data", resultList);
        result.put("totalCount", trainPhysicalAchievementService.countTrainPhysicalAchievementPage(physicalId, position,
                key, trainStr, trainFlag, searchFlag, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/physical/police/achievement")
    public ResponseEntity<?> trainPhysicalPoliceDetails(@RequestParam("id") Integer id) {
        return new ResponseEntity<>(DataListReturn.ok(trainPhysicalAchievementDetailsService.
                findPoliceAchievementById(id)), HttpStatus.OK);
    }

    @GetMapping("/firearm/page")
    public ResponseEntity<?> trainFirearmPage(@RequestParam("pageIndex") Integer pageIndex,
                                              @RequestParam("pageSize") Integer pageSize,
                                              @RequestParam(value = "trainBeginDate", required = false) String trainBeginDate,
                                              @RequestParam(value = "trainEndDate", required = false) String trainEndDate,
                                              @RequestParam("trainName") String trainName,
                                              @RequestParam("position") Integer position) {
        List<TrainFirearmPageResult> resultList = trainFirearmService.findTrainFirearmPage(pageIndex, pageSize,
                trainBeginDate, trainEndDate, trainName, position).stream().map(e -> {
                    Integer firearmQualifiedNum = trainService.firearmQualifiedNum(e.getId());
                    Integer firearmUnqualifiedNum = trainService.firearmUnQualifiedNum(e.getId());

                    TrainFirearmPageResult pageResult = new TrainFirearmPageResult();
                    pageResult.setId(e.getId());
                    pageResult.setTrainName(e.getName());
                    pageResult.setDate(DateUtils.formatDate(e.getTrainStartDate(), "yyyy/MM/dd"));
                    pageResult.setFiringType(e.getTrainFirearmTypeName());
                    pageResult.setEligibleCount(firearmQualifiedNum);
                    pageResult.setNotEligibleCount(firearmUnqualifiedNum);
                    return pageResult;
                }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("data", resultList);
        result.put("totalCount", trainFirearmService.countTrainFirearmPage(trainBeginDate, trainEndDate, trainName, position));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/firearm/details/label")
    public ResponseEntity<?> trainFirearmDetailsLabel(@RequestParam("firearmId") Integer firearmId,
                                                      @RequestParam("position") Integer position) {
        Integer userNum = userService.countAllPolice();
        //参加人数
        Integer firearmSignUpNum = trainService.firearmSignUpNum(firearmId);
        //合格人数
        Integer firearmQualifiedNum = trainService.firearmQualifiedNum(firearmId);
        //不合格人数
        Integer firearmUnqualifiedNum = trainService.firearmUnQualifiedNum(firearmId);

        TrainFirearmDetailsLabelResult result = new TrainFirearmDetailsLabelResult();
        result.setAttendCount(firearmSignUpNum);
        result.setNotAttendCount(userNum - firearmSignUpNum);
        result.setEligibleCount(firearmQualifiedNum);
        result.setNotEligibleCount(firearmUnqualifiedNum);

        result.setEligibleRatio(0d);
        result.setNotEligibleRatio(0d);
        if (firearmSignUpNum > 0) {
            result.setEligibleRatio(Double.valueOf(df.format(((double)firearmQualifiedNum / firearmSignUpNum) * 100)));
            result.setNotEligibleRatio(Double.valueOf(df.format(((double)firearmUnqualifiedNum / firearmSignUpNum) * 100)));
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/firearm/details/page")
    public ResponseEntity<?> trainFirearmDetailsPage(@RequestParam("pageIndex") Integer pageIndex,
                                                     @RequestParam("pageSize") Integer pageSize,
                                                     @RequestParam("firearmId") Integer firearmId,
                                                     @RequestParam("position") Integer position,
                                                     @RequestParam("key") String key,
                                                     @RequestParam("searchFlag") Integer searchFlag,
                                                     @RequestParam("deptId") Integer deptId) {
        List<TrainFirearmDetailsPageResult> resultList = trainFirearmAchievementService.findTrainFirearmAchievementPage(pageIndex,
                pageSize, firearmId, position, key, searchFlag, deptId).stream().map(e -> {
            TrainFirearmDetailsPageResult pageResult = new TrainFirearmDetailsPageResult();
            pageResult.setId(e.getId());
            pageResult.setPoliceName(e.getPoliceName());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setDeptName(e.getDepartmentName());
            pageResult.setEligibleFlag(e.getAchievementGrade() != null ? e.getAchievementGrade() == 2 ? "合格" : "不合格" : "--");
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy/MM/dd HH:mm:ss"));
            pageResult.setAchievement(e.getAchievement() == null ? 0 : e.getAchievement().intValue());
            return pageResult;
        }).collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("data", resultList);
        result.put("totalCount", trainFirearmAchievementService.countTrainFirearmAchievementPage(firearmId, position,
                key, searchFlag, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/physical/project")
    public ResponseEntity<?> trainProject() {
        List<TrainProjectResult> resultList = trainPhysicalAchievementService.findAllPhysicalTrainProject().stream()
                .map(e -> {
                    TrainProjectResult result = new TrainProjectResult();
                    result.setId(e.getId());
                    result.setName(e.getName());
                    return result;
                }).collect(Collectors.toList());

        return new ResponseEntity(DataListReturn.ok(resultList), HttpStatus.OK);
    }

}
