package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/global")
public class SystemController {

    @Autowired
    UserService userService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RiskService riskService;

    @Autowired
    RiskCaseLawEnforcementTypeService riskCaseLawEnforcementTypeService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    RiskConductVisitTypeService riskConductVisitTypeService;

    @Autowired
    MeasuresService measuresService;

    @Autowired
    RiskConductVisitService riskConductVisitService;

    @Autowired
    RiskDutyErrorTypeService riskDutyErrorTypeService;

    @Autowired
    RiskDutyInformationTypeService riskDutyInformationTypeService;

    @GetMapping("/police/list")
    public ResponseEntity<?> policeList(@Param("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
            deptId = null;
        }

        return new ResponseEntity<>(DataListReturn.ok(userService.findUserByDeptId(deptId).stream().map(e -> {
            PoliceListResult result = new PoliceListResult();
            result.setPoliceId(e.getPoliceId());
            result.setPoliceName(e.getName());
            return result;
        }).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/law/enforcement/type")
    public ResponseEntity<?> getCaseLawEnforcementTypeList() {
        List<CaseTypeListResult> resultList = new ArrayList<>();
        List<CaseTypeListResult> queryResult = riskCaseLawEnforcementTypeService.getLawEnforcementType().stream().map(e -> {
            CaseTypeListResult result = new CaseTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            result.setParentId(e.getParentId());
            return result;
        }).collect(Collectors.toList());

        for (CaseTypeListResult result : queryResult) {
            if (result.getParentId() == 0) {
                resultList.add(caseTypeListResultChildTypeDetails(queryResult, result));
            }
        }

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @GetMapping("/duty/type")
    public ResponseEntity<?> getCaseDutyTypeList() {
        return new ResponseEntity<>(DataListReturn.ok(riskCaseLawEnforcementTypeService.getDutyType().stream().map(e -> {
            CaseTypeListResult result = new CaseTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/conduct/bureau/rule/type")
    public ResponseEntity<?> getConductBureauRuleTypeList() {
        List<ConductBureauRuleTypeListResult> result = new ArrayList<>();
        List<ConductBureauRuleTypeListResult> ruleTypeList = riskConductBureauRuleTypeService
                .getAllRiskConductBureauRuleType().stream().map(e -> {
                    ConductBureauRuleTypeListResult typeListResult = new ConductBureauRuleTypeListResult();
                    typeListResult.setParentId(e.getParentId());
                    typeListResult.setId(e.getId());
                    typeListResult.setName(e.getName());
                    typeListResult.setDeductScore(e.getDeductScore());
                    return typeListResult;
                }).collect(Collectors.toList());

        for (ConductBureauRuleTypeListResult ruleType : ruleTypeList) {
            if (ruleType.getParentId() == 0) {
                result.add(bureauRuleTypeChildDetails(ruleType, ruleTypeList));
            }
        }
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }


    @GetMapping("/conduct/visit/type")
    public ResponseEntity<?> getConductVisitTypeList() {
        List<ConductVisitTypeListResult> results = new ArrayList<>();
        List<ConductVisitTypeListResult> typeListResultList = riskConductVisitTypeService.
                getAllRiskConductVisitType().stream().map(e -> {
            ConductVisitTypeListResult result = new ConductVisitTypeListResult();
            result.setParentId(e.getParentId());
            result.setId(e.getId());
            result.setValue(e.getId());
            result.setName(e.getName());
            result.setDeductScore(e.getDeductScore());
            result.setLevel(e.getLevel());
            return result;
        }).collect(Collectors.toList());

        for (ConductVisitTypeListResult typeListResult : typeListResultList) {
            if (typeListResult.getParentId() == 0) {
                results.add(visitTypeChildDetails(typeListResult, typeListResultList));
            }
        }

        return new ResponseEntity<>(DataListReturn.ok(results), HttpStatus.OK);
    }

    @GetMapping("/case/ability/type/list")
    public ResponseEntity<?> getCaseAbilityTypeList() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key", "reconsideration_litigation_status");
        data1.put("name", "??????????????????");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("key", "letter_visit_status");
        data2.put("name", "????????????????????????????????????");

        Map<String, Object> data3 = new HashMap<>();
        data3.put("key", "law_enforcement_fault_status");
        data3.put("name", "??????????????????");

        Map<String, Object> data4 = new HashMap<>();
        data4.put("key", "judicial_supervision_status");
        data4.put("name", "?????????????????????????????????");

        Map<String, Object> data5 = new HashMap<>();
        data5.put("key", "case_expert_status");
        data5.put("name", "??????????????????");

        Map<String, Object> data6 = new HashMap<>();
        data6.put("key", "excellent_legal_officer_status");
        data6.put("name", "???????????????");

        Map<String, Object> data7 = new HashMap<>();
        data7.put("key", "basic_test_status");
        data7.put("name", "?????????????????????");

        Map<String, Object> data8 = new HashMap<>();
        data8.put("key", "high_test_status");
        data8.put("name", "??????????????????");

        Map<String, Object> data9 = new HashMap<>();
        data9.put("key", "judicial_test_status");
        data9.put("name", "????????????");

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(data1, data2, data3, data4, data5, data6, data7,
                data8, data9)), HttpStatus.OK);
    }

    @GetMapping("/health/type/list")
    public ResponseEntity<?> getHealthTypeList() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key", "is_overweight");
        data1.put("name", "????????????");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("key", "is_hyperlipidemia");
        data2.put("name", "?????????");

        Map<String, Object> data3 = new HashMap<>();
        data3.put("key", "is_hypertension");
        data3.put("name", "?????????");

        Map<String, Object> data4 = new HashMap<>();
        data4.put("key", "is_hyperglycemia");
        data4.put("name", "?????????");

        Map<String, Object> data5 = new HashMap<>();
        data5.put("key", "is_hyperuricemia");
        data5.put("name", "????????????");


        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(data1, data2, data3, data4, data5)), HttpStatus.OK);
    }

    @GetMapping("/conduct/measures/type/list")
    public ResponseEntity<?> getMeasuresTypeList() {
        List<MeasuresTypeListResult> resultList = measuresService.findAllMeasures().stream().map(e -> {
            MeasuresTypeListResult result = new MeasuresTypeListResult();
            result.setId(e.getName());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @GetMapping("/dept/list")
    public ResponseEntity<?> getDeptList() {
        //
        List<Department> unit1 = departmentService.getDepartmentByType(1);
        // ????????????
        List<Department> unit = departmentService.getDepartmentByType(2);
        // ?????????
        List<Department> policeStation = departmentService.getDepartmentByType(3);

        List<DeptListResult> resultList = new ArrayList<>();
        resultList.addAll(unit1.stream().map(e -> {
            DeptListResult result = new DeptListResult();
            result.setId(e.getId());
            result.setDeptName(e.getName());
            return result;
        }).collect(Collectors.toList()));

        resultList.addAll(unit.stream().map(e -> {
            DeptListResult result = new DeptListResult();
            result.setId(e.getId());
            result.setDeptName(e.getName());
            return result;
        }).collect(Collectors.toList()));

        resultList.addAll(policeStation.stream().map(e -> {
            DeptListResult result = new DeptListResult();
            result.setId(e.getId());
            result.setDeptName(e.getName());
            return result;
        }).collect(Collectors.toList()));

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @GetMapping("/duty/information/type/list")
    public ResponseEntity<?> getDutyInformationType() {
        List<DutyInformationTypeListResult> resultList = riskDutyInformationTypeService.getAll().stream().map(e -> {
            DutyInformationTypeListResult result = new DutyInformationTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @GetMapping("/duty/error/type/list")
    public ResponseEntity<?> getDutyErrorType() {
        List<DutyErrorTypeListResult> resultList = riskDutyErrorTypeService.getAll().stream().map(e -> {
            DutyErrorTypeListResult result = new DutyErrorTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @PostMapping("/upload/img")
    public ResponseEntity<?> uploadImg(@RequestParam("fileArr") MultipartFile[] fileArr) throws IOException {
        if (fileArr == null || fileArr.length == 0 || fileArr[0].getContentType() == null) {
            return new ResponseEntity<>(DataListReturn.error("???????????????!"), HttpStatus.OK);
        }

        String path = "/mnt/qiantang/img/";
        String returnPath = "/img/";
        List<String> filePathList = new ArrayList<>();
        for (MultipartFile file : fileArr) {
            String fileName = System.currentTimeMillis()+file.getOriginalFilename();
            fileName = fileName.replace(",","y");

            File newFile = new File(path, fileName);
            file.transferTo(newFile);

            filePathList.add(returnPath + fileName);
        }
        return new ResponseEntity<>(DataListReturn.ok(filePathList), HttpStatus.OK);
    }

    /**
     * ????????????????????????
     * @param list
     * @param result
     * @return
     */
    private CaseTypeListResult caseTypeListResultChildTypeDetails(List<CaseTypeListResult> list, CaseTypeListResult result) {
        for (CaseTypeListResult data : list) {
            if (data.getParentId().equals(result.getId())) {
                if (result.getChildType() == null) {
                    result.setChildType(new ArrayList<>());
                }
                result.getChildType().add(caseTypeListResultChildTypeDetails(list, data));
            }
        }
        return result;
    }

    /**
     * ????????????????????????
     * @param result
     * @param ruleTypeList
     * @return
     */
    private ConductBureauRuleTypeListResult bureauRuleTypeChildDetails(ConductBureauRuleTypeListResult result,
                                                                       List<ConductBureauRuleTypeListResult> ruleTypeList) {
        for (ConductBureauRuleTypeListResult typeListResult : ruleTypeList) {
            if (typeListResult.getParentId().equals(result.getId())) {
                if (result.getChildType() == null) {
                    result.setChildType(new ArrayList<>());
                }
                result.getChildType().add(bureauRuleTypeChildDetails(typeListResult, ruleTypeList));
            }
        }
        return result;
    }

    /**
     * ??????????????????
     * @param result
     * @param listResults
     * @return
     */
    private ConductVisitTypeListResult visitTypeChildDetails(ConductVisitTypeListResult result,
                                                             List<ConductVisitTypeListResult> listResults) {
        for (ConductVisitTypeListResult typeListResult : listResults) {
            if (typeListResult.getParentId() == result.getId()) {
                if (result.getChildType() == null) {
                    result.setChildType(new ArrayList<>());
                }
                result.getChildType().add(visitTypeChildDetails(typeListResult, listResults));
            }
        }
        return result;
    }

}
