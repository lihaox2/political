package com.bayee.political.controller.admin;

import com.bayee.political.mapper.RiskCaseLawEnforcementTypeMapper;
import com.bayee.political.pojo.json.CaseTypeListResult;
import com.bayee.political.pojo.json.PoliceListResult;
import com.bayee.political.service.RiskCaseLawEnforcementTypeService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
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
        CaseTypeListResult result = new CaseTypeListResult();

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result)), HttpStatus.OK);
    }

    @GetMapping("/conduct/visit/type")
    public ResponseEntity<?> getConductVisitTypeList() {
        CaseTypeListResult result = new CaseTypeListResult();

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result)), HttpStatus.OK);
    }

}
