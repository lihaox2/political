package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.enums.RiskDataOperationType;
import com.bayee.political.filter.UserSession;
import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;
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
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 执法办案
 *
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/case")
public class CaseController {

    @Autowired
    RiskCaseAbilityRecordService riskCaseAbilityRecordService;

    @Autowired
    RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

    @Autowired
    RiskCaseTestRecordService riskCaseTestRecordService;

    @Autowired
    UserService userService;

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    @Autowired
    RiskCaseIntegralService riskCaseIntegralService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RiskDataOperationLogService riskDataOperationLogService;

    /**
     * 办案积分
     * @param queryParam
     * @return
     */
    @GetMapping("/integral/page")
    public ResponseEntity<?> caseIntegralPage(CaseIntegralPageQueryParam queryParam) {

        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", riskCaseIntegralService.caseIntegralPage(queryParam));
        result.put("totalCount", riskCaseIntegralService.caseIntegralPageCount(queryParam));
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/integral/details")
    public ResponseEntity<?> caseIntegralDetails(@RequestParam("id") Integer id) {
        RiskCaseIntegral integral = riskCaseIntegralService.findById(id);
        User user = userService.findByPoliceId(integral.getPoliceId());
        Department department = departmentService.findById(integral.getDeptId());

        CaseIntegralDetailsResult result = new CaseIntegralDetailsResult();
        result.setPoliceId(user.getPoliceId());
        result.setPoliceName(user.getName());
        result.setDeptId(integral.getDeptId());
        result.setDeptName(department.getName());
        result.setScore(integral.getScore());
        result.setBusinessTime(DateUtils.formatDate(integral.getBusinessTime(), "yyyy-MM"));
        result.setCreationDate(DateUtils.formatDate(integral.getCreationDate(), "yyyy-MM-dd"));
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/integral")
    public ResponseEntity<?> addCaseIntegral(@RequestBody CaseIntegralSaveParam saveParam,
                                             HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskCaseIntegral integral = new RiskCaseIntegral();
        integral.setPoliceId(saveParam.getPoliceId());
        integral.setDeptId(saveParam.getDeptId());
        integral.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime()+"-01", "yyyy-MM-dd"));
        integral.setScore(saveParam.getScore());
        integral.setCreationDate(new Date());

        riskCaseIntegralService.addRiskCaseIntegral(integral);

        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110034);
        log.setDataOriginId(integral.getId());
        log.setDataOriginPoliceId(integral.getPoliceId());
        log.setDataOriginBusinessDate(integral.getBusinessTime());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/integral/{id}")
    public ResponseEntity<?> updateCaseIntegral(@PathVariable Integer id,
                                                @RequestBody CaseIntegralSaveParam saveParam,
                                                HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskCaseIntegral integral = riskCaseIntegralService.findById(id);
        integral.setPoliceId(saveParam.getPoliceId());
        integral.setDeptId(saveParam.getDeptId());
        integral.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime()+"-01", "yyyy-MM-dd"));
        integral.setScore(saveParam.getScore());
        integral.setUpdateDate(new Date());

        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110034);
        log.setDataOriginId(integral.getId());
        log.setDataOriginPoliceId(integral.getPoliceId());
        log.setDataOriginBusinessDate(integral.getBusinessTime());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;
        riskCaseIntegralService.updateRiskCaseIntegral(integral);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/integral")
    public ResponseEntity<?> deleteCaseIntegral(@RequestParam("id") Integer id,
                                                HttpServletRequest httpServletRequest) {
        RiskCaseIntegral integral = riskCaseIntegralService.findById(id);
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);

        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110034);
        log.setDataOriginId(integral.getId());
        log.setDataOriginPoliceId(integral.getPoliceId());
        log.setDataOriginBusinessDate(integral.getBusinessTime());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;
        riskCaseIntegralService.deleteRiskCaseIntegral(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 执法能力
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/ability/page")
    public ResponseEntity<?> caseAbilityPage(@RequestParam("pageIndex") Integer pageIndex,
                                             @RequestParam("pageSize") Integer pageSize,
                                             @RequestParam("type") String type,
                                             @RequestParam("typeFlag") Integer typeFlag,
                                             @RequestParam("key") String key,
                                             @RequestParam("date") String date,
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

        List<String> columnList = new ArrayList<>();
        if (type != null && !"".equals(type)) {
            columnList = Arrays.asList(type.split(","));
        }
        List<RiskCaseAbilityRecord> recordList = riskCaseAbilityRecordService.riskCaseAbilityRecordPage(pageIndex,
                pageSize, columnList, typeFlag, key, date, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            Integer passCount = 0;
            Integer errorCount = 0;
            if (e.getLetterVisitStatus() != null && e.getLetterVisitStatus().equals(1)) {
                errorCount++;
            }
            if (e.getLawEnforcementFaultStatus() != null && e.getLawEnforcementFaultStatus().equals(1)) {
                errorCount++;
            }
            if (e.getJudicialSupervisionStatus() != null && e.getJudicialSupervisionStatus().equals(1)) {
                errorCount++;
            }

            if (e.getReconsiderationLitigationStatus() != null && e.getReconsiderationLitigationStatus().equals(1)) {
                errorCount++;
            }

            if (e.getCaseExpertStatus() != null && e.getCaseExpertStatus().equals(1)) {
                passCount++;
            }

            if (e.getExcellentLegalOfficerStatus() != null && e.getExcellentLegalOfficerStatus().equals(1)) {
                passCount++;
            }

            if (e.getBasicTestStatus() != null && e.getBasicTestStatus().equals(1)) {
                passCount++;
            }

            if (e.getHighTestStatus() != null && e.getHighTestStatus().equals(1)) {
                passCount++;
            }

            if (e.getJudicialTestStatus() != null && e.getJudicialTestStatus().equals(1)) {
                passCount++;
            }

            User user = userService.findByPoliceId(e.getPoliceId());
            CaseAbilityPageResult pageResult = new CaseAbilityPageResult();
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));
            pageResult.setPassCount(passCount);
            pageResult.setErrorCount(errorCount);
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskCaseAbilityRecordService.getRiskCaseAbilityRecordPageCount(columnList, typeFlag, key, date, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/ability")
    public ResponseEntity<?> addCaseAbility(@RequestBody CaseAbilitySaveParam caseAbilitySaveParam,
                                            HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(caseAbilitySaveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseAbilityRecord record = new RiskCaseAbilityRecord();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(caseAbilitySaveParam.getPoliceId());
        record.setReconsiderationLitigationStatus(caseAbilitySaveParam.getReconsiderationLitigationStatus());
        record.setLetterVisitStatus(caseAbilitySaveParam.getLetterVisitStatus());
        record.setLawEnforcementFaultStatus(caseAbilitySaveParam.getLawEnforcementFaultStatus());
        record.setJudicialSupervisionStatus(caseAbilitySaveParam.getJudicialSupervisionStatus());
        record.setCaseExpertStatus(caseAbilitySaveParam.getCaseExpertStatus());
        record.setExcellentLegalOfficerStatus(caseAbilitySaveParam.getExcellentLegalOfficerStatus());
        record.setBasicTestStatus(caseAbilitySaveParam.getBasicTestStatus());
        record.setHighTestStatus(caseAbilitySaveParam.getHighTestStatus());
        record.setJudicialTestStatus(caseAbilitySaveParam.getJudicialTestStatus());
        record.setYear(DateUtils.formatDate(DateUtils.parseDate(caseAbilitySaveParam.getDate(), "yyyy-MM-dd"), "yyyy"));
        record.setCreationDate(DateUtils.parseDate(caseAbilitySaveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));

        Integer id = riskCaseAbilityRecordService.getIdByDateAndPoliceId(caseAbilitySaveParam.getDate(), record.getPoliceId(), null);
        if (id != null) {
            return new ResponseEntity(DataListReturn.error("本警员在该日期内已存在！"), HttpStatus.OK);
        }
        riskCaseAbilityRecordService.insertSelective(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110031);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(caseAbilitySaveParam.getDate()));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/ability/{id}")
    public ResponseEntity<?> updateCaseAbility(@PathVariable("id") Integer id,
                                               @RequestBody CaseAbilitySaveParam caseAbilitySaveParam,
                                               HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(caseAbilitySaveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseAbilityRecord record = riskCaseAbilityRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(caseAbilitySaveParam.getPoliceId());
        record.setReconsiderationLitigationStatus(caseAbilitySaveParam.getReconsiderationLitigationStatus());
        record.setLetterVisitStatus(caseAbilitySaveParam.getLetterVisitStatus());
        record.setLawEnforcementFaultStatus(caseAbilitySaveParam.getLawEnforcementFaultStatus());
        record.setJudicialSupervisionStatus(caseAbilitySaveParam.getJudicialSupervisionStatus());
        record.setCaseExpertStatus(caseAbilitySaveParam.getCaseExpertStatus());
        record.setExcellentLegalOfficerStatus(caseAbilitySaveParam.getExcellentLegalOfficerStatus());
        record.setBasicTestStatus(caseAbilitySaveParam.getBasicTestStatus());
        record.setHighTestStatus(caseAbilitySaveParam.getHighTestStatus());
        record.setJudicialTestStatus(caseAbilitySaveParam.getJudicialTestStatus());
        record.setYear(DateUtils.formatDate(DateUtils.parseDate(caseAbilitySaveParam.getDate(), "yyyy-MM-dd"), "yyyy"));
        record.setCreationDate(DateUtils.parseDate(caseAbilitySaveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());

        Integer recordId = riskCaseAbilityRecordService.getIdByDateAndPoliceId(caseAbilitySaveParam.getDate(), record.getPoliceId(), id);
        if (recordId != null) {
            return new ResponseEntity(DataListReturn.error("本警员在该日期内已存在！"), HttpStatus.OK);
        }

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110031);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseAbilityRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(caseAbilitySaveParam.getDate()));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/ability/details")
    public ResponseEntity<?> caseAbilityDetails(@RequestParam("id") Integer id) {
        RiskCaseAbilityRecord record = riskCaseAbilityRecordService.selectByPrimaryKey(id);
        CaseAbilityDetailsResult result = new CaseAbilityDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setPoliceId(record.getPoliceId());
        result.setReconsiderationLitigationStatus(record.getReconsiderationLitigationStatus());
        result.setLetterVisitStatus(record.getLetterVisitStatus());
        result.setLawEnforcementFaultStatus(record.getLawEnforcementFaultStatus());
        result.setJudicialSupervisionStatus(record.getJudicialSupervisionStatus());
        result.setCaseExpertStatus(record.getCaseExpertStatus());
        result.setExcellentLegalOfficerStatus(record.getExcellentLegalOfficerStatus());
        result.setBasicTestStatus(record.getBasicTestStatus());
        result.setHighTestStatus(record.getHighTestStatus());
        result.setJudicialTestStatus(record.getJudicialTestStatus());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd"));

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/ability")
    public ResponseEntity<?> deleteCaseAbility(@RequestParam("id") Integer id,
                                               HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseAbilityRecord record = riskCaseAbilityRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110031);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseAbilityRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 执法管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/law/enforcement/page")
    public ResponseEntity<?> caseLawEnforcementPage(@RequestParam("pageIndex") Integer pageIndex,
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

        List<CaseLawEnforcementPageDO> recordList = riskCaseLawEnforcementRecordService.
                riskCaseLawEnforcementRecordPage(pageIndex, pageSize, type, key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            CaseLawEnforcementPageResult pageResult = new CaseLawEnforcementPageResult();
            pageResult.setContent(e.getContent());
            pageResult.setDesc(e.getDesc());
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setPoliceName(e.getPoliceName());
            pageResult.setDeptName(e.getDeptName());
            pageResult.setCaseCode(e.getCaseCode());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setDeductScore(e.getDeductScore());
            pageResult.setDate(e.getDate());

            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskCaseLawEnforcementRecordService.riskCaseLawEnforcementRecordPageCount(type, key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/law/enforcement")
    public ResponseEntity<?> addCaseLawEnforcement(@RequestBody CaseLawEnforcementSaveParam saveParam,
                                                   HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseLawEnforcementRecord record = new RiskCaseLawEnforcementRecord();

        record.setDeptName(saveParam.getDeptName());
        record.setCaseCode(saveParam.getCaseCode());
        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getDesc());
        record.setInputTime(new Date());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));
        record.setImgArr(saveParam.getFileList());
        record.setIsEffective(1);

        riskCaseLawEnforcementRecordService.insert(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110032);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(saveParam.getDate().substring(0, 10)));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/law/enforcement/{id}")
    public ResponseEntity<?> updateCaseLawEnforcement(@PathVariable("id") Integer id,
                                                      @RequestBody CaseLawEnforcementSaveParam saveParam,
                                                      HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseLawEnforcementRecord record = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);
        record.setDeptName(saveParam.getDeptName());
        record.setCaseCode(saveParam.getCaseCode());
        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getDesc());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());
        record.setImgArr(saveParam.getFileList());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110032);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseLawEnforcementRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(saveParam.getDate().substring(0, 10)));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/law/enforcement/details")
    public ResponseEntity<?> caseLawEnforcementDetails(@RequestParam("id") Integer id) {
        CaseLawEnforcementDetailsDO detailsDO = riskCaseLawEnforcementRecordService.findById(id);
        CaseLawEnforcementDetailsResult result = new CaseLawEnforcementDetailsResult();
        result.setDeptName(detailsDO.getDeptName());
        result.setCaseCode(detailsDO.getCaseCode());
        result.setParentId(detailsDO.getParentId());
        result.setParentName(detailsDO.getParentName());
        result.setTypeName(detailsDO.getTypeName());
        result.setTypeId(detailsDO.getTypeId());
        result.setPoliceId(detailsDO.getPoliceId());
        result.setPoliceName(detailsDO.getPoliceName());
        result.setDesc(detailsDO.getDesc());
        result.setDeductScore(detailsDO.getDeductScore());
        result.setDate(detailsDO.getDate());
        result.setReplaceErrorCount(riskCaseLawEnforcementRecordService.
                getPoliceReplaceErrorCount(detailsDO.getPoliceId(), detailsDO.getTypeId()));
        if (detailsDO.getImgArr() != null && !"".equals(detailsDO.getImgArr())) {
            result.setFileList(detailsDO.getImgArr().split(","));
        }

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/law/enforcement")
    public ResponseEntity<?> deleteCaseLawEnforcement(@RequestParam("id") Integer id,
                                                      HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseLawEnforcementRecord record = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110032);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseLawEnforcementRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 执法考试
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/test/page")
    public ResponseEntity<?> caseTestPage(@RequestParam("pageIndex") Integer pageIndex,
                                          @RequestParam("pageSize") Integer pageSize,
                                          @RequestParam("date") String date,
                                          @RequestParam("passFlag") Integer passFlag,
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

        List<RiskCaseTestRecord> recordList = riskCaseTestRecordService.riskCaseTestRecordPage(pageIndex, pageSize,
                date, passFlag, key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            CaseTestPageResult pageResult = new CaseTestPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setTestName(e.getName());
            pageResult.setScore(e.getScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));
            return pageResult;
        }).collect(Collectors.toList()));

        result.put("totalCount", riskCaseTestRecordService.riskCaseTestRecordPageCount(date,passFlag,key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/test")
    public ResponseEntity<?> addCaseTest(@RequestBody CaseTestSaveParam saveParam,
                                         HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        RiskCaseTestRecord record = new RiskCaseTestRecord();
        record.setDeductionScore(saveParam.getScore() >= 60 ? 0d : 2d);
        record.setPoliceId(saveParam.getPoliceId());
        record.setSemester(Integer.valueOf(saveParam.getDate().substring(5, 7)));
        record.setScore(saveParam.getScore());
        record.setName(saveParam.getTestName());
        record.setYear(saveParam.getDate().substring(0, 4));
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate()+" "+currentTime, "yyyy-MM-dd HH:mm:ss"));

//        Integer id = riskCaseTestRecordService.isExistence(saveParam.getPoliceId(), saveParam.getYear(), saveParam.getSemester(), null);
//        if (id != null) {
//            return new ResponseEntity(DataListReturn.error("本警员在该年度同一期内已存在！"), HttpStatus.OK);
//        }
        riskCaseTestRecordService.insertTest(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110033);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/test/{id}")
    public ResponseEntity<?> updateCaseTest(@PathVariable("id") Integer id,
                                            @RequestBody CaseTestSaveParam saveParam,
                                            HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        RiskCaseTestRecord oldRecord = riskCaseTestRecordService.selectByPrimaryKey(id);
        oldRecord.setDeductionScore(saveParam.getScore() >= 60 ? 0d : 2d);
        oldRecord.setPoliceId(saveParam.getPoliceId());
        oldRecord.setSemester(Integer.valueOf(saveParam.getDate().substring(5, 7)));
        oldRecord.setScore(saveParam.getScore());
        oldRecord.setYear(saveParam.getDate().substring(0, 4));
        oldRecord.setName(saveParam.getTestName());
        oldRecord.setCreationDate(DateUtils.parseDate(saveParam.getDate()+" "+currentTime, "yyyy-MM-dd HH:mm:ss"));
        oldRecord.setUpdateDate(new Date());

        /*Integer recordId = riskCaseTestRecordService.isExistence(saveParam.getPoliceId(), saveParam.getYear(), saveParam.getSemester(), id);
        if (recordId != null) {
            return new ResponseEntity(DataListReturn.error("本警员在该年度同一期内已存在！"), HttpStatus.OK);
        }*/

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110033);
        log.setDataOriginId(oldRecord.getId());
        log.setDataOriginPoliceId(oldRecord.getPoliceId());
        log.setDataOriginBusinessDate(oldRecord.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseTestRecordService.updateByPrimaryKey(oldRecord);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(oldRecord.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/test/details")
    public ResponseEntity<?> caseTestDetails(@RequestParam("id") Integer id) {
        RiskCaseTestRecord record = riskCaseTestRecordService.selectByPrimaryKey(id);
        CaseTestDetailsResult result = new CaseTestDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setTestName(record.getName());
        result.setScore(record.getScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd"));

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/test")
    public ResponseEntity<?> deleteCaseTest(@RequestParam("id") Integer id,
                                            HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskCaseTestRecord record = riskCaseTestRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(110033);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskCaseTestRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

}
