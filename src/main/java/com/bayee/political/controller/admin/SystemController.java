package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskConductBureauRuleType;
import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.pojo.json.CaseTypeListResult;
import com.bayee.political.pojo.json.ConductBureauRuleTypeListResult;
import com.bayee.political.pojo.json.ConductVisitTypeListResult;
import com.bayee.political.pojo.json.PoliceListResult;
import com.bayee.political.service.RiskCaseLawEnforcementTypeService;
import com.bayee.political.service.RiskConductBureauRuleTypeService;
import com.bayee.political.service.RiskConductVisitTypeService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
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
    RiskCaseLawEnforcementTypeService riskCaseLawEnforcementTypeService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    RiskConductVisitTypeService riskConductVisitTypeService;

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
            result.setName(e.getName());
            result.setDeductScore(e.getDeductScore());
            result.setLevel(e.getLevel());
            return result;
        }).collect(Collectors.toList());

        for (ConductVisitTypeListResult typeListResult : typeListResultList) {
            if (typeListResult.getLevel() == 2) {
                results.add(visitTypeChildDetails(typeListResult, typeListResultList));
            }
        }

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(results)), HttpStatus.OK);
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
