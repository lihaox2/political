package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskCaseAbilityRecord;
import com.bayee.political.domain.RiskCaseLawEnforcementRecord;
import com.bayee.political.domain.RiskCaseTestRecord;
import com.bayee.political.domain.User;
import com.bayee.political.pojo.json.*;
import com.bayee.political.service.RiskCaseAbilityRecordService;
import com.bayee.political.service.RiskCaseLawEnforcementRecordService;
import com.bayee.political.service.RiskCaseTestRecordService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.apache.ibatis.annotations.Delete;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    /**
     * 执法能力
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/ability/page")
    public ResponseEntity<?> caseAbilityPage(@RequestParam("pageIndex") Integer pageIndex,
                                             @RequestParam("pageSize") Integer pageSize) {
        List<RiskCaseAbilityRecord> recordList = riskCaseAbilityRecordService.riskCaseAbilityRecordPage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            Integer passCount = 0;
            Integer errorCount = 0;
            if (e.getReconsiderationLitigationStatus() != null && e.getReconsiderationLitigationStatus().equals(1)) {
                passCount++;
            }
            if (e.getLetterVisitStatus() != null && e.getLetterVisitStatus().equals(1)) {
                errorCount++;
            }
            if (e.getLawEnforcementFaultStatus() != null && e.getLawEnforcementFaultStatus().equals(1)) {
                errorCount++;
            }
            if (e.getJudicialSupervisionStatus() != null && e.getJudicialSupervisionStatus().equals(1)) {
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
            pageResult.setPassCount(passCount);
            pageResult.setErrorCount(errorCount);
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskCaseAbilityRecordService.getRiskCaseAbilityRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/ability")
    public ResponseEntity<?> addCaseAbility(@RequestBody CaseAbilitySaveParam caseAbilitySaveParam) {
        RiskCaseAbilityRecord record = new RiskCaseAbilityRecord();
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
        record.setYear(DateUtils.formatDate(DateUtils.parseDate(caseAbilitySaveParam.getDate(), "YYYY-MM-DD HH:mm:ss"), "YYYY"));
        record.setCreationDate(DateUtils.parseDate(caseAbilitySaveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));

        riskCaseAbilityRecordService.insertSelective(record);
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/ability")
    public ResponseEntity<?> updateCaseAbility(@RequestParam("id") Integer id,
                                               @RequestBody CaseAbilitySaveParam caseAbilitySaveParam) {
        RiskCaseAbilityRecord record = riskCaseAbilityRecordService.selectByPrimaryKey(id);
        record.setReconsiderationLitigationStatus(caseAbilitySaveParam.getReconsiderationLitigationStatus());
        record.setLetterVisitStatus(caseAbilitySaveParam.getLetterVisitStatus());
        record.setLawEnforcementFaultStatus(caseAbilitySaveParam.getLawEnforcementFaultStatus());
        record.setJudicialSupervisionStatus(caseAbilitySaveParam.getJudicialSupervisionStatus());
        record.setCaseExpertStatus(caseAbilitySaveParam.getCaseExpertStatus());
        record.setExcellentLegalOfficerStatus(caseAbilitySaveParam.getExcellentLegalOfficerStatus());
        record.setBasicTestStatus(caseAbilitySaveParam.getBasicTestStatus());
        record.setHighTestStatus(caseAbilitySaveParam.getHighTestStatus());
        record.setJudicialTestStatus(caseAbilitySaveParam.getJudicialTestStatus());
        record.setUpdateDate(new Date());

        riskCaseAbilityRecordService.updateByPrimaryKeySelective(record);
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
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "YYYY-MM-DD HH:mm:ss"));

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/ability")
    public ResponseEntity<?> deleteCaseAbility(@RequestParam("id") Integer id) {
        riskCaseAbilityRecordService.deleteByPrimaryKey(id);

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
                                                    @RequestParam("pageSize") Integer pageSize) {
        List<RiskCaseLawEnforcementRecord> recordList = riskCaseLawEnforcementRecordService.
                riskCaseLawEnforcementRecordPage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            CaseLawEnforcementPageResult pageResult = new CaseLawEnforcementPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setTypeName(e.getTypeName());
            pageResult.setDesc(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "YYYY-MM-DD HH:mm:ss"));
            return pageResult;
        }).collect(Collectors.toList()));

        result.put("totalCount", riskCaseLawEnforcementRecordService.riskCaseLawEnforcementRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/law/enforcement")
    public ResponseEntity<?> addCaseLawEnforcement(@RequestBody CaseLawEnforcementSaveParam saveParam) {
        RiskCaseLawEnforcementRecord record = new RiskCaseLawEnforcementRecord();
        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getDesc());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));

        riskCaseLawEnforcementRecordService.insert(record);
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/law/enforcement")
    public ResponseEntity<?> updateCaseLawEnforcement(@RequestParam("id") Integer id,
                                                      @RequestBody CaseLawEnforcementSaveParam saveParam) {
        RiskCaseLawEnforcementRecord oldRecord = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);
        oldRecord.setPoliceId(saveParam.getPoliceId());
        oldRecord.setType(saveParam.getTypeId());
        oldRecord.setContent(saveParam.getDesc());
        oldRecord.setDeductionScore(saveParam.getDeductScore());
        oldRecord.setUpdateDate(new Date());

        riskCaseLawEnforcementRecordService.updateByPrimaryKeySelective(oldRecord);
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/law/enforcement/details")
    public ResponseEntity<?> caseLawEnforcementDetails(@RequestParam("id") Integer id) {
        RiskCaseLawEnforcementRecord record = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);
        CaseLawEnforcementDetailsResult result = new CaseLawEnforcementDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setType(record.getType());
        result.setDesc(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "YYYY-MM-dd HH:mm:ss"));

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/law/enforcement")
    public ResponseEntity<?> deleteCaseLawEnforcement(@RequestParam("id") Integer id) {
        riskCaseLawEnforcementRecordService.deleteByPrimaryKey(id);

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
                                          @RequestParam("pageSize") Integer pageSize) {
        List<RiskCaseTestRecord> recordList = riskCaseTestRecordService.riskCaseTestRecordPage(pageIndex, pageSize);

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
            pageResult.setYear(e.getYear());
            pageResult.setSemester(e.getSemester());
            return pageResult;
        }).collect(Collectors.toList()));

        result.put("totalCount", riskCaseTestRecordService.riskCaseTestRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/test")
    public ResponseEntity<?> addCaseTest(@RequestBody CaseTestSaveParam saveParam) {
        RiskCaseTestRecord record = new RiskCaseTestRecord();
        record.setPoliceId(saveParam.getPoliceId());
        record.setYear(DateUtils.formatDate(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"), "YYYY"));
        record.setSemester(saveParam.getSemester());
        record.setScore(saveParam.getScore());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));

        riskCaseTestRecordService.insertTest(record);
        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/test")
    public ResponseEntity<?> updateCaseTest(@RequestParam("id") Integer id,
                                            @RequestBody CaseTestSaveParam saveParam) {
        RiskCaseTestRecord oldRecord = riskCaseTestRecordService.selectByPrimaryKey(id);
        oldRecord.setId(id);
        oldRecord.setPoliceId(saveParam.getPoliceId());
        oldRecord.setSemester(saveParam.getSemester());
        oldRecord.setScore(saveParam.getScore());
        oldRecord.setUpdateDate(new Date());

        riskCaseTestRecordService.updateByPrimaryKey(oldRecord);
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
        result.setYear(record.getYear());
        result.setSemester(record.getSemester());

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/test")
    public ResponseEntity<?> deleteCaseTest(@RequestParam("id") Integer id) {
        riskCaseTestRecordService.deleteByPrimaryKey(id);

        return new ResponseEntity(DataListReturn.ok(), HttpStatus.OK);
    }

}
