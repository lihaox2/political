package com.bayee.political.controller;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.PoliceHotSearch;
import com.bayee.political.domain.PoliceRecentlySearch;
import com.bayee.political.domain.User;
import com.bayee.political.json.*;
import com.bayee.political.pojo.dto.RiskReportRecordDO;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.apache.commons.collections.ListUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

import static cn.hutool.core.util.StrUtil.isNotBlank;

/**
 * @author xxl
 * @date 2021/6/5
 */
@RestController
@RequestMapping("/holographic")
public class RiskHolographicController {

    @Autowired
    UserService userService;

    @Autowired
    PoliceCommentService policeCommentService;

    @Autowired
    PoliceWorkingDeptLogService policeWorkingDeptLogService;

    @Autowired
    PoliceWorkingPositionLogService policeWorkingPositionLogService;

    @Autowired
    PoliceEducationLogService policeEducationLogService;

    @Autowired
    RiskReportRecordService riskReportRecordService;
	
	@Autowired
    RiskHolographicService riskHolographicService;

	@Autowired
	DepartmentService departmentService;

	@Autowired
	PoliceLabelService policeLabelService;

    /**
     * 警员全息热搜排名
     */
    @Autowired
    private PoliceHotSearchService policeHotSearchService;

    /**
     * 警员全息最近搜素排名
     */
    @Autowired
    private PoliceRecentlySearchService policeRecentlySearchService;

    /**
     * 查询警员全息热搜
     * @return
     */
    @GetMapping("/find/holographic/host")
    public ResponseEntity<?> findHolographicHostSearch(){
        List<PoliceHotSearch> policeHotSearchList = policeHotSearchService.findHolographicHostSearch();
        if(policeHotSearchList == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.ok(policeHotSearchList), HttpStatus.OK);
    }

    /**
     * 查询警员全息最近搜素
     * @return
     */
    @GetMapping("/find/holographic/recently")
    public ResponseEntity<?> findHolographicRecentlySearch(){
        List<PoliceRecentlySearch> searchList = policeRecentlySearchService.findHolographicRecentlySearch();
        if(searchList == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.ok(searchList), HttpStatus.OK);
    }

    /**
     * 首页-部门列表
     * @param policeId
     * @return
     */
    @GetMapping("/dept/list")
    public ResponseEntity<?> holographicDeptList(@RequestParam("policeId") String policeId) {

        return new ResponseEntity<>(DataListReturn.ok(departmentService.findHolographicDeptList(policeId)), HttpStatus.OK);
    }

    /**
     * 首页-部门-警员信息
     * @param deptId
     * @return
     */
    @GetMapping("/dept/police/list")
    public ResponseEntity<?> holographicDeptPolice(Integer deptId) {

        return new ResponseEntity<>(DataListReturn.ok(userService.findByDeptId(deptId).parallelStream().map(e -> {
            HolographicDeptPoliceResult result = new HolographicDeptPoliceResult();
            result.setPoliceId(e.getPoliceId());
            result.setPoliceName(e.getName());
            result.setHeadImage(e.getHeadImage());
            result.setDeptName(e.getDepartmentName());
            result.setPoliceType(e.getPositionName());
            return result;
        }).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * 首页搜索
     * @param key
     * @return
     */
    @GetMapping("/police/search")
    public ResponseEntity<?> policeSearch(@RequestParam("key") String key) {

        return new ResponseEntity<>(DataListReturn.ok(userService.holographicFindPoliceByKey(key)), HttpStatus.OK);
    }

    /**
     * 警员信息
     * @param policeId
     * @return
     */
    @GetMapping("/police/details")
    public ResponseEntity<?> holographicPoliceDetails(@RequestParam("policeId") String policeId) {
        User user = userService.findByPoliceId(policeId);
        Date currentDate = new Date();
        Date birthday = DateUtils.parseDate(user.getBirthday(), "yyyyMMdd");

        //热搜处理
        policeHotSearchService.HolographicSearchHotDetails(user);
        //最近排行处理
        policeRecentlySearchService.findHolographicRecentlyPoliceId(user);

        //计算年龄
        String currentYear = DateUtils.formatDate(currentDate, "yyyy");
        String birthdayYear = DateUtils.formatDate(birthday, "yyyy");
        int age = 0;
        if (isNotBlank(currentYear) && isNotBlank(birthdayYear)){
            age = Integer.parseInt(currentYear) - Integer.parseInt(birthdayYear);
        }

        //计算工作年份
        String workingYear = DateUtils.formatDate(user.getWorkingStartDate(), "yyyy");
        int workingYearCount = 0;
        if (isNotBlank(currentYear) && isNotBlank(workingYear)) {
            workingYearCount = Integer.parseInt(currentYear) - Integer.parseInt(workingYear);
        }

        //处理返回结果
        HolographicPoliceDetailsResult userInfo = new HolographicPoliceDetailsResult();
        userInfo.setPoliceId(user.getPoliceId());
        userInfo.setPoliceName(user.getName());
        userInfo.setHeadImg(user.getHeadImage());
        userInfo.setPoliceType(user.getPositionName());
        userInfo.setDeptName(user.getDepartmentName());
        userInfo.setSex(user.getGender() == 1 ? "男" : user.getGender() == 2 ? "女" : "--");
        userInfo.setAge(age);
        userInfo.setBirthday(DateUtils.formatDate(birthday, "yyyy-MM-dd"));
        userInfo.setNativePlace(user.getNativePlace());
        userInfo.setPoliticalOutlook(user.getPoliticalStatus());
        userInfo.setWorkYearCount(workingYearCount);
        userInfo.setPublicSecurityDate(DateUtils.formatDate(user.getEmploymentDate(), "yyyy年MM月dd日")+" - 至今");
        userInfo.setOrganizeDate(DateUtils.formatDate(user.getJoiningPartyTime(), "yyyy-MM-dd"));
//        e.setLabelList(policeLa.findPoliceLabel(e.getPoliceId()));
        String label = "";
        List<String> labelList = policeLabelService.findPoliceLabel(user.getPoliceId());
        if (labelList != null && labelList.size() > 0) {
            for (int i=0; i<labelList.size(); i++) {
                label += labelList.get(i);
                if (i != labelList.size() -1) {
                    label += ",";
                }
            }
        }
        userInfo.setPoliceLabel(label);

        return new ResponseEntity<>(DataListReturn.ok(userInfo), HttpStatus.OK);
    }

    /**
     * 警务数据
     * @param policeId
     * @return
     * @throws ParseException
     */
    @GetMapping("/compive/evaluate")
    public ResponseEntity<?> holographicComprehensiveEvaluate(@RequestParam("policeId") String policeId) throws ParseException {
        String date = DateUtils.formatDate(new Date(), "yyyy-MM");
        String lastDate = DateUtils.lastMonthTime();
        RiskReportRecordDO reportRecordDO = riskReportRecordService.findRiskReportByPoliceIdToYear(policeId, date, lastDate);

        ComprehensiveEvaluateResult result = new ComprehensiveEvaluateResult();
        result.setIndexNum(reportRecordDO.getTotalNum());
//        result.setAwardScore(reportRecordDO.getTotalAwardScore());
//        result.setDeductionScore(reportRecordDO.getTotalDeductionScore());
        result.setHealthDesc(reportRecordDO.getHealthDesc());

        result.setIndexNumFlag(1);

        ChartResult conductChart = new ChartResult();
        conductChart.setId(1);
        conductChart.setName("行为规范");
        conductChart.setValue(reportRecordDO.getConductNum());

        ChartResult disciplineStyleChart = new ChartResult();
        disciplineStyleChart.setId(2);
        disciplineStyleChart.setName("接警执勤");
        disciplineStyleChart.setValue(reportRecordDO.getDutyNum());

        ChartResult caseChart = new ChartResult();
        caseChart.setId(3);
        caseChart.setName("执法办案");
        caseChart.setValue(reportRecordDO.getHandlingCaseNum());

        ChartResult trainChart = new ChartResult();
        trainChart.setId(4);
        trainChart.setName("警务技能");
        trainChart.setValue(reportRecordDO.getTrainNum());

        ChartResult socialContactChart = new ChartResult();
        socialContactChart.setId(5);
        socialContactChart.setName("社交风险");
        socialContactChart.setValue(reportRecordDO.getSocialContactNum());

        ChartResult amilyEvaluationChart = new ChartResult();
        amilyEvaluationChart.setId(6);
        amilyEvaluationChart.setName("家属评价");
        amilyEvaluationChart.setValue(reportRecordDO.getAmilyEvaluationNum());

        ChartResult healthChart = new ChartResult();
        healthChart.setId(7);
        healthChart.setName("健康指数");
        healthChart.setValue(reportRecordDO.getHealthNum());

        result.setChartResultList(Arrays.asList(conductChart, disciplineStyleChart, caseChart, trainChart,
                socialContactChart, amilyEvaluationChart, healthChart));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 警员评价
     * @param policeId
     * @return
     */
    @GetMapping("/police/comment")
    public ResponseEntity<?> holographicPoliceComment(@RequestParam("policeId") String policeId) {

        return new ResponseEntity<>(DataListReturn.ok(policeCommentService.findCommentByPoliceId(policeId).parallelStream().map(e -> {
            HolographicPoliceCommentResult commentResult = new HolographicPoliceCommentResult();
            commentResult.setId(e.getId());
            commentResult.setComment(e.getComment());
            commentResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy年MM月dd日"));
            return commentResult;
        }).collect(Collectors.toList())), HttpStatus.OK);
    }

    /**
     * 获取职业生涯月份
     * @param policeId
     * @param year
     * @return
     */
    @GetMapping("/career/month")
    public ResponseEntity<?> getPoliceAllRiskMonth(@RequestParam("policeId") String policeId,
                                                   @RequestParam("year") String year) {

        return new ResponseEntity<>(DataListReturn.ok(riskReportRecordService.findPoliceAllRiskMonth(policeId, year)),
                HttpStatus.OK);
    }

    /**
     * 职业生涯
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    @GetMapping("/career/log")
    public ResponseEntity<?> holographicCareer(@RequestParam("policeId") String policeId,
                                               @RequestParam("year") String year,
                                               @RequestParam("month") String month,
                                               @RequestParam("type") Integer type) {
        if (!StrUtil.isNotBlank(year)) {
            year = DateUtils.formatDate(new Date(), "yyyy");
        }

        return new ResponseEntity<>(DataListReturn.ok(riskHolographicService.getPoliceCareer(policeId, year, month, type)), HttpStatus.OK);
    }

    /**
     * 职业生涯类型
     * @return
     */
    @GetMapping("/career/typeList")
    public ResponseEntity<?> holographicCareerType() {

        return new ResponseEntity<>(DataListReturn.ok(riskHolographicService.getPoliceCareerType()), HttpStatus.OK);
    }

}
