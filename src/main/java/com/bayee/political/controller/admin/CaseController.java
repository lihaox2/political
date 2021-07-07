package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskCaseAbilityRecord;
import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.User;
import com.bayee.political.pojo.dto.CaseLawEnforcementDetailsDO;
import com.bayee.political.pojo.dto.CaseLawEnforcementPageDO;
import com.bayee.political.pojo.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
                                             @RequestParam("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
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
            }else{
                passCount++;
            }
            if (e.getLawEnforcementFaultStatus() != null && e.getLawEnforcementFaultStatus().equals(1)) {
                errorCount++;
            }else{
                passCount++;
            }
            if (e.getJudicialSupervisionStatus() != null && e.getJudicialSupervisionStatus().equals(1)) {
                errorCount++;
            }else{
                passCount++;
            }

            if (e.getReconsiderationLitigationStatus() != null && e.getReconsiderationLitigationStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
            }

            if (e.getCaseExpertStatus() != null && e.getCaseExpertStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
            }

            if (e.getExcellentLegalOfficerStatus() != null && e.getExcellentLegalOfficerStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
            }

            if (e.getBasicTestStatus() != null && e.getBasicTestStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
            }

            if (e.getHighTestStatus() != null && e.getHighTestStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
            }

            if (e.getJudicialTestStatus() != null && e.getJudicialTestStatus().equals(1)) {
                passCount++;
            }else{
                errorCount++;
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
    public ResponseEntity<?> addCaseAbility(@RequestBody CaseAbilitySaveParam caseAbilitySaveParam) {
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
        totalRiskDetailsService.caseRiskDetails(caseAbilitySaveParam.getPoliceId(), LocalDate.parse(caseAbilitySaveParam.getDate()));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/ability/{id}")
    public ResponseEntity<?> updateCaseAbility(@PathVariable("id") Integer id,
                                               @RequestBody CaseAbilitySaveParam caseAbilitySaveParam) {
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
        riskCaseAbilityRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.caseRiskDetails(caseAbilitySaveParam.getPoliceId(), LocalDate.parse(caseAbilitySaveParam.getDate()));
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
    public ResponseEntity<?> deleteCaseAbility(@RequestParam("id") Integer id) {
        RiskCaseAbilityRecord record = riskCaseAbilityRecordService.selectByPrimaryKey(id);

        riskCaseAbilityRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(record.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
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
                                                    @RequestParam("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
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
    public ResponseEntity<?> addCaseLawEnforcement(@RequestBody CaseLawEnforcementSaveParam saveParam) {
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

        riskCaseLawEnforcementRecordService.insert(record);
        totalRiskDetailsService.caseRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate().substring(0, 10)));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/law/enforcement/{id}")
    public ResponseEntity<?> updateCaseLawEnforcement(@PathVariable("id") Integer id,
                                                      @RequestBody CaseLawEnforcementSaveParam saveParam) {
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

        riskCaseLawEnforcementRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.caseRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate().substring(0, 10)));
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
    public ResponseEntity<?> deleteCaseLawEnforcement(@RequestParam("id") Integer id) {
        RiskCaseLawEnforcementRecord record = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);

        riskCaseLawEnforcementRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(record.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
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
                                          @RequestParam("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
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
    public ResponseEntity<?> addCaseTest(@RequestBody CaseTestSaveParam saveParam) {
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
        totalRiskDetailsService.caseRiskDetails(saveParam.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/test/{id}")
    public ResponseEntity<?> updateCaseTest(@PathVariable("id") Integer id,
                                            @RequestBody CaseTestSaveParam saveParam) {
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
        riskCaseTestRecordService.updateByPrimaryKey(oldRecord);
        totalRiskDetailsService.caseRiskDetails(saveParam.getPoliceId(), LocalDate.parse(DateUtils.formatDate(oldRecord.getCreationDate(), "yyyy-MM-dd")));
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
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd HH:mm:ss"));

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/test")
    public ResponseEntity<?> deleteCaseTest(@RequestParam("id") Integer id) {
        RiskCaseTestRecord record = riskCaseTestRecordService.selectByPrimaryKey(id);

        riskCaseTestRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.caseRiskDetails(record.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

}
