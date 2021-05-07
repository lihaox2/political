package com.bayee.political.controller.admin;

import com.bayee.political.pojo.json.CaseTypeListResult;
import com.bayee.political.pojo.json.PoliceListResult;
import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/global")
public class SystemController {

    @GetMapping("/police/list")
    public ResponseEntity<?> policeList() {
        PoliceListResult result = new PoliceListResult();

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result)), HttpStatus.OK);
    }

    @GetMapping("/law/enforcement/type")
    public ResponseEntity<?> getCaseLawEnforcementTypeList() {
        CaseTypeListResult result = new CaseTypeListResult();

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result)), HttpStatus.OK);
    }

    @GetMapping("/duty/type")
    public ResponseEntity<?> getCaseDutyTypeList() {
        CaseTypeListResult result = new CaseTypeListResult();

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result)), HttpStatus.OK);
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
