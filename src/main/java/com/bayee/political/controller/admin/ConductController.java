package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.enums.RiskDataOperationType;
import com.bayee.political.filter.UserSession;
import com.bayee.political.pojo.dto.ConductBureauRuleDetailsDO;
import com.bayee.political.pojo.dto.ConductBureauRuleTypeDetailsDO;
import com.bayee.political.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/conduct")
public class ConductController {

    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    @Autowired
    RiskConductVisitTypeService riskConductVisitTypeService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    UserService userService;

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    @Autowired
    RiskConductVisitOriginService riskConductVisitOriginService;

    @Autowired
    PoliceRelevantTypeService policeRelevantTypeService;

    @Autowired
    RiskRelevantRecordService policeRelevantService;

    @Autowired
    RiskDataOperationLogService riskDataOperationLogService;

    /**
     * 动态排摸
     * @param queryParam
     * @return
     */
    @GetMapping("/police/relevant/page")
    public ResponseEntity<?> policeRelevantPage(RiskRelevantPageQueryParam queryParam,
                                                HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        if (loginUser == null) {
//            return new ResponseEntity(DataListReturn.error("请重新登录"), HttpStatus.OK);
//        }
        if (loginUser != null) {
            queryParam.setDeptId(loginUser.getDepartmentId());
            if (loginUser.getDepartmentId() != null && loginUser.getDepartmentId() == 1
                    || (loginUser.getIsActive() != null && loginUser.getIsActive() == 999)) {
                queryParam.setDeptId(null);
            }
        }


        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", policeRelevantService.policeRelevantPage(queryParam));
        result.put("totalCount", policeRelevantService.policeRelevantPageCount(queryParam));
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/police/relevant/details")
    public ResponseEntity<?> policeRelevantDetails(@RequestParam("id") Integer id) {

        RiskRelevantRecord relevant = policeRelevantService.findById(id);
        User user = userService.findByPoliceId(relevant.getPoliceId());
        RiskRelevantType relevantType = policeRelevantTypeService.findByCode(relevant.getTypeCode());
        RiskRelevantType parentRelevantType = policeRelevantTypeService.findByCode(relevantType.getpCode());

        PoliceRelevantDetailsResult result = new PoliceRelevantDetailsResult();
        result.setPoliceId(relevant.getPoliceId());
        result.setPoliceName(user.getName());
        result.setDeptName(user.getDepartmentName());
        result.setTypeCode(parentRelevantType.getCode());
        result.setTypeCodeName(parentRelevantType.getName());
        result.setChildTypeCode(relevantType.getCode());
        result.setChildTypeCodeName(relevantType.getName());
        result.setDeductionScore(relevant.getDeductionScore());
        result.setRemark(relevant.getRemark());
        result.setBusinessDate(DateUtils.formatDate(relevant.getBusinessDate(), "yyyy-MM-dd"));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/update/police/relevant/{id}")
    public ResponseEntity<?> updatePoliceRelevant(@PathVariable Integer id,
                                                  @RequestBody PoliceRelevantSaveParam saveParam,
                                                  HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskRelevantRecord relevant = policeRelevantService.findById(id);
        relevant.setPoliceId(saveParam.getPoliceId());
        relevant.setTypeCode(saveParam.getTypeCode());
        relevant.setDeductionScore(saveParam.getDeductionScore());
        relevant.setBusinessDate(DateUtils.parseDate(saveParam.getBusinessDate(), "yyyy-MM-dd"));
        relevant.setRemark(saveParam.getRemark());
        relevant.setUdpateDate(new Date());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110023);
        log.setDataOriginId(relevant.getId());
        log.setDataOriginPoliceId(relevant.getPoliceId());
        log.setDataOriginBusinessDate(relevant.getBusinessDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        policeRelevantService.updatePoliceRelevant(relevant);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/add/police/relevant")
    public ResponseEntity<?> addPoliceRelevant(@RequestBody PoliceRelevantSaveParam saveParam,
                                               HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskRelevantRecord relevant = new RiskRelevantRecord();
        relevant.setPoliceId(saveParam.getPoliceId());
        relevant.setTypeCode(saveParam.getTypeCode());
        relevant.setDeductionScore(saveParam.getDeductionScore());
        relevant.setBusinessDate(DateUtils.parseDate(saveParam.getBusinessDate(), "yyyy-MM-dd"));
        relevant.setRemark(saveParam.getRemark());
        relevant.setCreationDate(new Date());
        policeRelevantService.insertPoliceRelevant(relevant);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110023);
        log.setDataOriginId(relevant.getId());
        log.setDataOriginPoliceId(relevant.getPoliceId());
        log.setDataOriginBusinessDate(relevant.getBusinessDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/police/relevant")
    public ResponseEntity<?> deletePoliceRelevant(@RequestParam("id") Integer id,
                                                  HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskRelevantRecord relevant = policeRelevantService.findById(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110023);
        log.setDataOriginId(relevant.getId());
        log.setDataOriginPoliceId(relevant.getPoliceId());
        log.setDataOriginBusinessDate(relevant.getBusinessDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        policeRelevantService.deleteById(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 动态排摸类型
     * @return
     */
    @GetMapping("/relevant/type/list")
    public ResponseEntity<?> policeRelevantTypeList() {
        List<PoliceRelevantTypeListResult> queryResult = policeRelevantTypeService.queryAll().parallelStream().map(e -> {
            PoliceRelevantTypeListResult result = new PoliceRelevantTypeListResult();
            result.setCode(e.getCode());
            result.setName(e.getName());
            result.setLevel(e.getLevel());
            result.setpCode(e.getpCode());
            return result;
        }).collect(Collectors.toList());

        List<PoliceRelevantTypeListResult> results = new ArrayList<>();
        for (PoliceRelevantTypeListResult node : queryResult) {
            if ("0".equals(node.getpCode())) {
                results.add(relevantTypeChildDetails(node, queryResult));
            }
        }
        return new ResponseEntity<>(DataListReturn.ok(results), HttpStatus.OK);
    }

    /**
     * 局规计分
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/page")
    public ResponseEntity<?> conductBureauRulePage(@RequestParam("pageIndex") Integer pageIndex,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("key") String key,
                                                   @RequestParam("deptId") Integer deptId,
                                                   HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        if (loginUser == null) {
//            return new ResponseEntity(DataListReturn.error("请重新登录"), HttpStatus.OK);
//        }
        if (deptId != null && deptId == 1 || (loginUser != null && loginUser.getIsActive() != null
                && loginUser.getIsActive() == 999)) {
            deptId = null;
        }

        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordService.
                riskConductBureauRuleRecordPage(pageIndex, pageSize,type,key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            ConductBureauRulePageResult pageResult = new ConductBureauRulePageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setRootName(e.getRootName());
            pageResult.setId(e.getId());
            pageResult.setType(e.getTypeName());
            pageResult.setContent(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));

            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductBureauRuleRecordService.getRiskConductBureauRuleRecordPageCount(type,key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule")
    public ResponseEntity<?> addConductBureauRule(@RequestBody ConductBureauRuleSaveParam saveParam,
                                                  HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(saveParam.getTypeId());
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        RiskConductBureauRuleRecord record = new RiskConductBureauRuleRecord();
        record.setScoringLevel(saveParam.getScoringLevel());
        record.setScoringDept(saveParam.getScoringDept());
        record.setMeasures(saveParam.getMeasures());
        record.setType(ruleType.getId());
        record.setContent(ruleType.getName());
        record.setPoliceId(saveParam.getPoliceId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(ruleType.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setImgArr(saveParam.getFileList());
        record.setIsEffective(1);

        riskConductBureauRuleRecordService.insert(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110021);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/{id}")
    public ResponseEntity<?> updateConductBureauRule(@PathVariable("id") Integer id,
                                                     @RequestBody ConductBureauRuleSaveParam saveParam,
                                                     HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductBureauRuleRecord record = riskConductBureauRuleRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        record.setScoringLevel(saveParam.getScoringLevel());
        record.setScoringDept(saveParam.getScoringDept());
        record.setMeasures(saveParam.getMeasures());
        record.setType(ruleType.getId());
        record.setContent(ruleType.getName());
        record.setPoliceId(saveParam.getPoliceId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(ruleType.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());
        record.setImgArr(saveParam.getFileList());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110021);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskConductBureauRuleRecordService.updateByPrimaryKey(record);
        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/details")
    public ResponseEntity<?> conductBureauRuleDetails(@RequestParam("id") Integer id) {
        ConductBureauRuleDetailsDO detailsDTO = riskConductBureauRuleRecordService.findById(id);
        ConductBureauRuleDetailsResult result = new ConductBureauRuleDetailsResult();
        result.setScoringRootType(detailsDTO.getScoringRootType());
        result.setScoringRootTypeName(detailsDTO.getScoringRootTypeName());
        result.setScoringLevel(detailsDTO.getScoringLevel());
        result.setScoringLevelName(detailsDTO.getScoringLevelName());
        result.setScoringDept(detailsDTO.getScoringDept());
        result.setScoringDeptName(detailsDTO.getScoringDeptName());
        result.setScoringType(detailsDTO.getScoringType());
        result.setScoringTypeName(detailsDTO.getScoringTypeName());
        result.setScoringOption(detailsDTO.getScoringOption());
        result.setScoringOptionName(detailsDTO.getScoringOptionName());
        result.setMeasures(detailsDTO.getMeasures());
        result.setMeasuresName(detailsDTO.getMeasuresName());
        result.setPoliceId(detailsDTO.getPoliceId());
        result.setPoliceName(detailsDTO.getPoliceName());
        result.setDeductScore(detailsDTO.getDeductScore());
        result.setDate(detailsDTO.getDate());
        result.setRemarks(detailsDTO.getRemarks());
        if(detailsDTO.getImgArr() != null && !"".equals(detailsDTO.getImgArr())) {
            result.setFileList(detailsDTO.getImgArr().split(","));
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/bureau/rule")
    public ResponseEntity<?> deleteConductBureauRule(@RequestParam("id") Integer id,
                                                     HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskConductBureauRuleRecord record = riskConductBureauRuleRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110021);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskConductBureauRuleRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 信访投诉
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/visit/page")
    public ResponseEntity<?> conductVisitPage(@RequestParam("pageIndex") Integer pageIndex,
                                              @RequestParam("pageSize") Integer pageSize,
                                              @RequestParam("type") String type,
                                              @RequestParam("key") String key,
                                              @RequestParam("deptId") Integer deptId,
                                              HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        if (loginUser == null) {
//            return new ResponseEntity(DataListReturn.error("请重新登录"), HttpStatus.OK);
//        }
        if (deptId != null && deptId == 1 || (loginUser != null && loginUser.getIsActive() != null
                && loginUser.getIsActive() == 999)) {
            deptId = null;
        }

        List<RiskConductVisitRecord> recordList = riskConductVisitRecordService.riskConductVisitRecordPage(pageIndex, pageSize, type, key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            ConductVisitPageResult pageResult = new ConductVisitPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());

            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setBigType(e.getBigTypeName());
            pageResult.setSmallType(e.getSmallTypeName());
            pageResult.setContent(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductVisitRecordService.getRiskConductVisitRecordPageCount(type, key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/visit")
    public ResponseEntity<?> addConductVisit(@RequestBody ConductVisitSaveParam saveParam,
                                             HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductVisitRecord record = new RiskConductVisitRecord();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setContent(saveParam.getDesc());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());
        record.setIsReally(saveParam.getIsReally());
        record.setOriginId(saveParam.getOriginId());
        record.setIsEffective(1);

        riskConductVisitRecordService.insert(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110022);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/visit/{id}")
    public ResponseEntity<?> updateConductVisit(@PathVariable("id") Integer id,
                                                @RequestBody ConductVisitSaveParam saveParam,
                                                HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setContent(saveParam.getDesc());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setType(saveParam.getTypeId());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());
        record.setIsReally(saveParam.getIsReally());
        record.setOriginId(saveParam.getOriginId());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110022);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskConductVisitRecordService.updateByPrimaryKey(record);
        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/details")
    public ResponseEntity<?> conductVisitDetails(@RequestParam("id") Integer id) {
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        RiskConductVisitOrigin visitOrigin = riskConductVisitOriginService.findById(record.getOriginId());

        ConductVisitDetailsResult result = new ConductVisitDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setBigTypeId(record.getBigType());
        result.setSmallTypeId(record.getSmallType());
        result.setBigType(record.getBigTypeName());
        result.setSmallType(record.getSmallTypeName());
        result.setContent(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd"));
        result.setRemarks(record.getRemarks());
        result.setIdReally(record.getIsReally());
        result.setOriginId(record.getOriginId());
        if (visitOrigin != null ){
            result.setOriginName(visitOrigin.getName());
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/visit")
    public ResponseEntity<?> deleteConductVisit(@RequestParam("id") Integer id,
                                                HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110022);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskConductVisitRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.conductRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 局规计分 计分项管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/type/page")
    public ResponseEntity<?> conductBureauRuleTypePage(@RequestParam("pageIndex") Integer pageIndex,
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestParam("type") String type,
                                                       @RequestParam("key") String key) {
        List<RiskConductBureauRuleType> ruleTypeList = riskConductBureauRuleTypeService.riskConductBureauRuleTypePage(pageIndex, pageSize, type, key);

        Map<String, Object> result = new HashMap<>();
        result.put("data", ruleTypeList.stream().map(e -> {
            ConductBureauRuleTypePageResult pageResult = new ConductBureauRuleTypePageResult();
            pageResult.setId(e.getId());
            pageResult.setRootName(e.getRootName());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setName(e.getName());
            pageResult.setDeductScore(e.getDeductScore());
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductBureauRuleTypeService.getRiskConductBureauRuleTypePageCount(type,key));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule/type")
    public ResponseEntity<?> addConductBureauRuleType(@RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        if (riskConductBureauRuleTypeService.countRuleTypeByNameAndRuleType(saveParam.getName(), saveParam.getParentId(), null) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductBureauRuleType ruleType = new RiskConductBureauRuleType();
        ruleType.setLevel(3);
        ruleType.setParentId(saveParam.getParentId());
        ruleType.setName(saveParam.getName());
        ruleType.setDeductScore(saveParam.getDeductScore());
        ruleType.setCreationDate(new Date());

        riskConductBureauRuleTypeService.insert(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/type/{id}")
    public ResponseEntity<?> updateConductBureauRuleType(@PathVariable("id") Integer id,
                                                         @RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        if (riskConductBureauRuleTypeService.countRuleTypeByNameAndRuleType(saveParam.getName(), saveParam.getParentId(), id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(id);
        ruleType.setParentId(saveParam.getParentId());
        ruleType.setName(saveParam.getName());
        ruleType.setDeductScore(saveParam.getDeductScore());
        ruleType.setUpdateDate(new Date());

        riskConductBureauRuleTypeService.updateByPrimaryKey(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/type/details")
    public ResponseEntity<?> conductBureauRuleTypeDetails(@RequestParam("id") Integer id) {
        ConductBureauRuleTypeDetailsDO detailsDO = riskConductBureauRuleTypeService.findById(id);
        ConductBureauRuleTypeDetailsResult result = new ConductBureauRuleTypeDetailsResult();
        result.setRootTypeId(detailsDO.getRootTypeId());
        result.setRootTypeName(detailsDO.getRootTypeName());
        result.setParentId(detailsDO.getParentId());
        result.setParentTypeName(detailsDO.getParentTypeName());
        result.setName(detailsDO.getName());
        result.setDeductScore(detailsDO.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/bureau/rule/type")
    public ResponseEntity<?> deleteConductBureauRuleType(@RequestParam("id") Integer id) {
        if (riskConductBureauRuleRecordService.countByBureauRuleType(id) > 0) {
            return new ResponseEntity<>(DataListReturn.error("删除失败！类型已被使用！"), HttpStatus.OK);
        }
        riskConductBureauRuleTypeService.deleteByPrimaryKey(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/get/type/by/scoringOption")
    public ResponseEntity<?> getScoringOptionType(@RequestParam("scoringOption") String scoringOption) {
        return new ResponseEntity<>(DataListReturn.ok(riskConductBureauRuleTypeService.
                getTotalTypeByScoringOptionName(scoringOption)), HttpStatus.OK);
    }

    /**
     * 信访投诉 计分项管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/visit/type/page")
    public ResponseEntity<?> conductVisitTypePage(@RequestParam("pageIndex") Integer pageIndex,
                                                  @RequestParam("pageSize") Integer pageSize) {
        List<RiskConductVisitType> visitTypes = riskConductVisitTypeService.riskConductVisitTypePage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", visitTypes.stream().map(e -> {
            ConductVisitTypePageResult pageResult = new ConductVisitTypePageResult();
            pageResult.setId(e.getId());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setName(e.getName());
            pageResult.setDeductScore(e.getDeductScore());
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductVisitTypeService.getRiskConductVisitTypePageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 添加信访类型
     * @param saveParam
     * @return
     */
    @PostMapping("/add/visit/type")
    public ResponseEntity<?> addConductVisitType(@RequestBody ConductVisitTypeSaveParam saveParam) {
        if (riskConductVisitTypeService.countByNameAndParentId(saveParam.getName(), saveParam.getParentId(), null) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductVisitType visitType = new RiskConductVisitType();
        visitType.setParentId(saveParam.getParentId());
        visitType.setName(saveParam.getName());
        visitType.setDeductScore(saveParam.getDeductScore());
        visitType.setCreationDate(new Date());

        riskConductVisitTypeService.insert(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 修改信访类型
     * @param id
     * @param saveParam
     * @return
     */
    @PostMapping("/update/visit/type/{id}")
    public ResponseEntity<?> updateConductVisitType(@PathVariable("id") Integer id,
                                                    @RequestBody ConductVisitTypeSaveParam saveParam) {
        if (riskConductVisitTypeService.countByNameAndParentId(saveParam.getName(), saveParam.getParentId(), id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductVisitType visitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        visitType.setParentId(saveParam.getParentId());
        visitType.setName(saveParam.getName());
        visitType.setDeductScore(saveParam.getDeductScore());
        visitType.setUpdateDate(new Date());

        riskConductVisitTypeService.updateByPrimaryKeySelective(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 信访类型详情
     * @param id
     * @return
     */
    @GetMapping("/visit/type/details")
    public ResponseEntity<?> conductVisitTypeDetails(@RequestParam("id") Integer id) {
        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        ConductVisitTypeDetailsResult result = new ConductVisitTypeDetailsResult();
        result.setParentId(riskConductVisitType.getParentId());
        result.setTypeName(riskConductVisitType.getTypeName());
        result.setName(riskConductVisitType.getName());
        result.setDeductScore(riskConductVisitType.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 删除信访类型
     * @param id
     * @return
     */
    @DeleteMapping("/delete/visit/type")
    public ResponseEntity<?> deleteConductVisitType(@RequestParam("id") Integer id) {
        if (riskConductVisitRecordService.countByTypeId(id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("删除失败！类型已被使用！"), HttpStatus.OK);
        }
        riskConductVisitTypeService.deleteByPrimaryKey(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 信访来源
     * @return
     */
    @GetMapping("/visit/origin")
    public ResponseEntity<?> conductVisitOrigin() {

        return new ResponseEntity<>(DataListReturn.ok(riskConductVisitOriginService.findAllVisitOrigin()), HttpStatus.OK);
    }

    /**
     * 动态排摸类型 层级处理
     * @param relevantType
     * @param relevantTypeList
     * @return
     */
    private PoliceRelevantTypeListResult relevantTypeChildDetails(PoliceRelevantTypeListResult relevantType,
                                                                  List<PoliceRelevantTypeListResult> relevantTypeList) {
        for (PoliceRelevantTypeListResult node : relevantTypeList) {
            if (relevantType.getCode().equals(node.getpCode())) {
                if (relevantType.getChildList() == null) {
                    relevantType.setChildList(new ArrayList<>());
                }
                relevantType.getChildList().add(relevantTypeChildDetails(node, relevantTypeList));
            }
        }
        return relevantType;
    }

}
