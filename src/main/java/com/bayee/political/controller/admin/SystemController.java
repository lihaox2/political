package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.mapper.TrainFirearmAchievementMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.pojo.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @GetMapping("/police/list")
    public ResponseEntity<?> policeList() {
        return new ResponseEntity<>(DataListReturn.ok(userService.findAll().stream().map(e -> {
            PoliceListResult result = new PoliceListResult();
            result.setPoliceId(e.getPoliceId());
            result.setPoliceName(e.getName());
            return result;
        }).collect(Collectors.toList())), HttpStatus.OK);
    }

    @GetMapping("/law/enforcement/type")
    public ResponseEntity<?> getCaseLawEnforcementTypeList() {
        return new ResponseEntity<>(DataListReturn.ok(riskCaseLawEnforcementTypeService.getLawEnforcementType().stream().map(e -> {
            CaseTypeListResult result = new CaseTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList())), HttpStatus.OK);
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
        data1.put("name", "复议诉讼撤变");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("key", "letter_visit_status");
        data2.put("name", "有责涉案（警）投诉、信访");

        Map<String, Object> data3 = new HashMap<>();
        data3.put("key", "law_enforcement_fault_status");
        data3.put("name", "重大执法过错");

        Map<String, Object> data4 = new HashMap<>();
        data4.put("key", "judicial_supervision_status");
        data4.put("name", "司法监督（检察院纠违）");

        Map<String, Object> data5 = new HashMap<>();
        data5.put("key", "case_expert_status");
        data5.put("name", "执法办案能手");

        Map<String, Object> data6 = new HashMap<>();
        data6.put("key", "excellent_legal_officer_status");
        data6.put("name", "优秀法制员");

        Map<String, Object> data7 = new HashMap<>();
        data7.put("key", "basic_test_status");
        data7.put("name", "基本级执法考试");

        Map<String, Object> data8 = new HashMap<>();
        data8.put("key", "high_test_status");
        data8.put("name", "高级执法考试");

        Map<String, Object> data9 = new HashMap<>();
        data9.put("key", "judicial_test_status");
        data9.put("name", "司法考试");

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(data1, data2, data3, data4, data5, data6, data7,
                data8, data9)), HttpStatus.OK);
    }

    @GetMapping("/health/type/list")
    public ResponseEntity<?> getHealthTypeList() {
        Map<String, Object> data1 = new HashMap<>();
        data1.put("key", "is_overweight");
        data1.put("name", "体重超标");

        Map<String, Object> data2 = new HashMap<>();
        data2.put("key", "is_hyperlipidemia");
        data2.put("name", "高血脂");

        Map<String, Object> data3 = new HashMap<>();
        data3.put("key", "is_hypertension");
        data3.put("name", "高血压");

        Map<String, Object> data4 = new HashMap<>();
        data4.put("key", "is_hyperglycemia");
        data4.put("name", "高血糖");

        Map<String, Object> data5 = new HashMap<>();
        data5.put("key", "is_hyperuricemia");
        data5.put("name", "高血尿酸");


        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(data1, data2, data3, data4, data5)), HttpStatus.OK);
    }

    @GetMapping("/conduct/measures/type/list")
    public ResponseEntity<?> getMeasuresTypeList() {
        List<MeasuresTypeListResult> resultList = measuresService.findAllMeasures().stream().map(e -> {
            MeasuresTypeListResult result = new MeasuresTypeListResult();
            result.setId(e.getId());
            result.setName(e.getName());
            return result;
        }).collect(Collectors.toList());

        return new ResponseEntity<>(DataListReturn.ok(resultList), HttpStatus.OK);
    }

    @GetMapping("/dept/list")
    public ResponseEntity<?> getDeptList() {
        // 机关单位
        List<Department> unit = departmentService.getDepartmentByType(2);
        // 派出所
        List<Department> policeStation = departmentService.getDepartmentByType(3);

        List<DeptListResult> resultList = new ArrayList<>();
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

    /**
     * 局规计分类型处理
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
     * 信访类型处理
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
