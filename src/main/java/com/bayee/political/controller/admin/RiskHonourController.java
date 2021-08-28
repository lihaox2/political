package com.bayee.political.controller.admin;

import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/8/26 20:24
 */
@RestController
@RequestMapping("/honour")
public class RiskHonourController {

    public ResponseEntity<?> riskHonourPage() {
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/type/list")
    public ResponseEntity<?> getHonourTypeList() {
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/unit/list")
    public ResponseEntity<?> getHonourUnitList() {
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/unitLevel/list")
    public ResponseEntity<?> getHonourUnitLevelList() {
        Map<String, String> result = new HashMap<>();
        result.put("key", "");

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
