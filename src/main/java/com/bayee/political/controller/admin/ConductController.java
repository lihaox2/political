package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.pojo.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
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
 * @author xxl
 * @date 2021/5/7
 */
@RestController
@RequestMapping("/conduct")
public class ConductController {

    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    @Autowired
    RiskConductVisitTypeService riskConductVisitTypeService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    UserService userService;

    /**
     * 局规计分
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/page")
    public ResponseEntity<?> conductBureauRulePage(@RequestParam("pageIndex") Integer pageIndex,
                                                   @RequestParam("pageSize") Integer pageSize) {
        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordService.
                riskConductBureauRuleRecordPage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            ConductBureauRulePageResult pageResult = new ConductBureauRulePageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setType(e.getTypeName());
            pageResult.setContent(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "YYYY-MM-DD HH:mm:ss"));

            return pageResult;
        }));
        result.put("totalCount", riskConductBureauRuleRecordService.getRiskConductBureauRuleRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule")
    public ResponseEntity<?> addConductBureauRule(@RequestBody ConductBureauRuleSaveParam saveParam) {
        RiskConductBureauRuleRecord record = new RiskConductBureauRuleRecord();
        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));
        record.setContent(saveParam.getContent());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));

        riskConductBureauRuleRecordService.insert(record);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule")
    public ResponseEntity<?> updateConductBureauRule(@RequestParam("id") Integer id,
                                                     @RequestBody ConductBureauRuleSaveParam saveParam) {
        RiskConductBureauRuleRecord record = riskConductBureauRuleRecordService.selectByPrimaryKey(id);
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getContent());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setUpdateDate(new Date());

        riskConductBureauRuleRecordService.updateByPrimaryKey(record);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/details")
    public ResponseEntity<?> conductBureauRuleDetails(@RequestParam("id") Integer id) {
        RiskConductBureauRuleRecord record = riskConductBureauRuleRecordService.selectByPrimaryKey(id);
        ConductBureauRuleDetailsResult result = new ConductBureauRuleDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setTypeId(record.getType());
        result.setContent(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "YYYY-MM-DD HH:mm:ss"));
        result.setRemarks(record.getRemarks());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/bureau/rule")
    public ResponseEntity<?> deleteConductBureauRule(@RequestParam("id") Integer id) {
        riskConductBureauRuleRecordService.deleteByPrimaryKey(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
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
        RiskConductVisitRecord record = new RiskConductVisitRecord();
        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));
        record.setContent(saveParam.getContent());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "YYYY-MM-DD HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());

        riskConductVisitRecordService.insert(record);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/visit")
    public ResponseEntity<?> updateConductVisit(@RequestParam("id") Integer id,
                                                @RequestBody ConductVisitSaveParam saveParam) {
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getContent());
        record.setRemarks(saveParam.getRemarks());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setUpdateDate(new Date());

        riskConductVisitRecordService.updateByPrimaryKey(record);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/details")
    public ResponseEntity<?> conductVisitDetails(@RequestParam("id") Integer id) {
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        ConductVisitDetailsResult result = new ConductVisitDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setTypeId(record.getType());
        result.setContent(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "YYYY-MM-DD HH:mm:ss"));
        result.setRemarks(record.getRemarks());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/visit")
    public ResponseEntity<?> deleteConductVisit(@RequestParam("id") Integer id) {
        riskConductVisitRecordService.deleteByPrimaryKey(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
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
        List<RiskConductBureauRuleType> ruleTypeList = riskConductBureauRuleTypeService.riskConductBureauRuleTypePage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", ruleTypeList.stream().map(e -> {
            ConductBureauRuleTypePageResult pageResult = new ConductBureauRuleTypePageResult();
            pageResult.setId(e.getId());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setName(e.getName());
            pageResult.setDeductScore(e.getDeductScore());
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductBureauRuleTypeService.getRiskConductBureauRuleTypePageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule/type")
    public ResponseEntity<?> addConductBureauRuleType(@RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        RiskConductBureauRuleType ruleType = new RiskConductBureauRuleType();
        ruleType.setCreationDate(new Date());

        riskConductBureauRuleTypeService.insert(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/type")
    public ResponseEntity<?> updateConductBureauRuleType(@RequestParam("id") Integer id,
                                                         @RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(id);
        ruleType.setUpdateDate(new Date());

        riskConductBureauRuleTypeService.updateByPrimaryKey(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/type/details")
    public ResponseEntity<?> conductBureauRuleTypeDetails(@RequestParam("id") Integer id) {
        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(id);
        ConductBureauRuleTypeDetailsResult result = new ConductBureauRuleTypeDetailsResult();
        result.setParentId(ruleType.getParentId());
        result.setName(ruleType.getName());
        result.setDeductScore(ruleType.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/bureau/rule/type")
    public ResponseEntity<?> deleteConductBureauRuleType(@RequestParam("id") Integer id) {
        if (riskConductBureauRuleRecordService.countByBureauRuleType(id) > 0) {
            return new ResponseEntity<>(DataListReturn.error("删除失败！类型已被使用！"), HttpStatus.OK);
        }
        riskConductBureauRuleTypeService.deleteByPrimaryKey(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
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
        List<RiskConductVisitType> visitTypes = riskConductVisitTypeService.riskConductVisitTypePage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", visitTypes.stream().map(e -> {
            ConductVisitTypePageResult pageResult = new ConductVisitTypePageResult();
            pageResult.setId(e.getId());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setName(e.getName());
            pageResult.setDeductScore(e.getDeductScore());
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductVisitTypeService.getRiskConductVisitTypePageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/visit/type")
    public ResponseEntity<?> addConductVisitType(@RequestBody ConductVisitTypeSaveParam saveParam) {
        RiskConductVisitType visitType = new RiskConductVisitType();
        visitType.setName(saveParam.getName());
        visitType.setCreationDate(new Date());

        riskConductVisitTypeService.insert(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/visit/type")
    public ResponseEntity<?> updateConductVisitType(@RequestParam("id") Integer id,
                                                    @RequestBody ConductVisitTypeSaveParam saveParam) {
        RiskConductVisitType visitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        visitType.setName(saveParam.getName());
        visitType.setUpdateDate(new Date());

        riskConductVisitTypeService.updateByPrimaryKeySelective(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/type/details")
    public ResponseEntity<?> conductVisitTypeDetails(@RequestParam("id") Integer id) {
        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        ConductVisitTypeDetailsResult result = new ConductVisitTypeDetailsResult();
        result.setParentId(riskConductVisitType.getParentId());
        result.setName(riskConductVisitType.getName());
        result.setDeductScore(riskConductVisitType.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/visit/type")
    public ResponseEntity<?> deleteConductVisitType(@RequestParam("id") Integer id) {
        riskConductVisitTypeService.deleteByPrimaryKey(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
