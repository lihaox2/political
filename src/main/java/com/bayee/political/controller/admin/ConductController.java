package com.bayee.political.controller.admin;

import com.bayee.political.pojo.json.*;
import com.bayee.political.utils.DataListReturn;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/conduct")
public class ConductController {

    /**
     * 局规计分
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/page")
    public ResponseEntity<?> conductBureauRulePage(@RequestParam("pageIndex") Integer pageIndex,
                                                   @RequestParam("pageSize") Integer pageSize) {
        ConductBureauRulePageResult pageResult = new ConductBureauRulePageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule")
    public ResponseEntity<?> addConductBureauRule(@RequestBody ConductBureauRuleSaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule")
    public ResponseEntity<?> updateConductBureauRule(@RequestParam("id") Integer id,
                                                     @RequestBody ConductBureauRuleSaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/details")
    public ResponseEntity<?> conductBureauRuleDetails(@RequestParam("id") Integer id) {
        ConductBureauRuleDetailsResult result = new ConductBureauRuleDetailsResult();

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/bureau/rule")
    public ResponseEntity<?> deleteConductBureauRule(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 信访投诉
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/visit/page")
    public ResponseEntity<?> conductVisitPage(@RequestParam("pageIndex") Integer pageIndex,
                                              @RequestParam("pageSize") Integer pageSize) {
        ConductVisitPageResult pageResult = new ConductVisitPageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/visit")
    public ResponseEntity<?> addConductVisit(@RequestBody ConductVisitSaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/visit")
    public ResponseEntity<?> updateConductVisit(@RequestParam("id") Integer id,
                                                @RequestBody ConductVisitSaveParam saveParam) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/visit/details")
    public ResponseEntity<?> conductVisitDetails(@RequestParam("id") Integer id) {
        ConductVisitDetailsResult result = new ConductVisitDetailsResult();

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/visit")
    public ResponseEntity<?> deleteConductVisit(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 局规计分 计分项管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/type/page")
    public ResponseEntity<?> conductBureauRuleTypePage(@RequestParam("pageIndex") Integer pageIndex,
                                                       @RequestParam("pageSize") Integer pageSize) {
        HealthPageResult pageResult = new HealthPageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/conduct/bureau/rule/type")
    public ResponseEntity<?> addConductBureauRuleType() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/type")
    public ResponseEntity<?> updateConductBureauRuleType(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/type/details")
    public ResponseEntity<?> conductBureauRuleTypeDetails(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/bureau/rule/type")
    public ResponseEntity<?> deleteConductBureauRuleType(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
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
        HealthPageResult pageResult = new HealthPageResult();

        Map<String, Object> result = new HashMap<>();
        result.put("data", pageResult);
        result.put("totalCount", 100);
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/visit/type")
    public ResponseEntity<?> addConductVisitType() {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/update/visit/type")
    public ResponseEntity<?> updateConductVisitType(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/visit/type/details")
    public ResponseEntity<?> conductVisitTypeDetails(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/delete/visit/type")
    public ResponseEntity<?> deleteConductVisitType(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

}
