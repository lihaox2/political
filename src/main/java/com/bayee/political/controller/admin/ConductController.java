package com.bayee.political.controller.admin;

import com.bayee.political.domain.*;
import com.bayee.political.pojo.dto.ConductBureauRuleDetailsDO;
import com.bayee.political.pojo.dto.ConductBureauRuleTypeDetailsDO;
import com.bayee.political.json.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    @Autowired
    RiskConductVisitOriginService riskConductVisitOriginService;

    /**
     * 局规计分
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/bureau/rule/page")
    public ResponseEntity<?> conductBureauRulePage(@RequestParam("pageIndex") Integer pageIndex,
                                                   @RequestParam("pageSize") Integer pageSize,
                                                   @RequestParam("type") String type,
                                                   @RequestParam("key") String key,
                                                   @RequestParam("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
            deptId = null;
        }

        List<RiskConductBureauRuleRecord> recordList = riskConductBureauRuleRecordService.
                riskConductBureauRuleRecordPage(pageIndex, pageSize,type,key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            ConductBureauRulePageResult pageResult = new ConductBureauRulePageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setRootName(e.getRootName());
            pageResult.setId(e.getId());
            pageResult.setType(e.getTypeName());
            pageResult.setContent(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));

            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductBureauRuleRecordService.getRiskConductBureauRuleRecordPageCount(type,key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);

        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule")
    public ResponseEntity<?> addConductBureauRule(@RequestBody ConductBureauRuleSaveParam saveParam) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(saveParam.getTypeId());
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        RiskConductBureauRuleRecord record = new RiskConductBureauRuleRecord();
        record.setScoringLevel(saveParam.getScoringLevel());
        record.setScoringDept(saveParam.getScoringDept());
        record.setMeasures(saveParam.getMeasures());
        record.setType(ruleType.getId());
        record.setContent(ruleType.getName());
        record.setPoliceId(saveParam.getPoliceId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(ruleType.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setImgArr(saveParam.getFileList());
        record.setIsEffective(1);

        riskConductBureauRuleRecordService.insert(record);
        totalRiskDetailsService.conductRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/{id}")
    public ResponseEntity<?> updateConductBureauRule(@PathVariable("id") Integer id,
                                                     @RequestBody ConductBureauRuleSaveParam saveParam) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductBureauRuleRecord record = riskConductBureauRuleRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        record.setScoringLevel(saveParam.getScoringLevel());
        record.setScoringDept(saveParam.getScoringDept());
        record.setMeasures(saveParam.getMeasures());
        record.setType(ruleType.getId());
        record.setContent(ruleType.getName());
        record.setPoliceId(saveParam.getPoliceId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(ruleType.getDeductScore());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() + " " + time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());
        record.setImgArr(saveParam.getFileList());

        riskConductBureauRuleRecordService.updateByPrimaryKey(record);
        totalRiskDetailsService.conductRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/details")
    public ResponseEntity<?> conductBureauRuleDetails(@RequestParam("id") Integer id) {
        ConductBureauRuleDetailsDO detailsDTO = riskConductBureauRuleRecordService.findById(id);
        ConductBureauRuleDetailsResult result = new ConductBureauRuleDetailsResult();
        result.setScoringRootType(detailsDTO.getScoringRootType());
        result.setScoringRootTypeName(detailsDTO.getScoringRootTypeName());
        result.setScoringLevel(detailsDTO.getScoringLevel());
        result.setScoringLevelName(detailsDTO.getScoringLevelName());
        result.setScoringDept(detailsDTO.getScoringDept());
        result.setScoringDeptName(detailsDTO.getScoringDeptName());
        result.setScoringType(detailsDTO.getScoringType());
        result.setScoringTypeName(detailsDTO.getScoringTypeName());
        result.setScoringOption(detailsDTO.getScoringOption());
        result.setScoringOptionName(detailsDTO.getScoringOptionName());
        result.setMeasures(detailsDTO.getMeasures());
        result.setMeasuresName(detailsDTO.getMeasuresName());
        result.setPoliceId(detailsDTO.getPoliceId());
        result.setPoliceName(detailsDTO.getPoliceName());
        result.setDeductScore(detailsDTO.getDeductScore());
        result.setDate(detailsDTO.getDate());
        result.setRemarks(detailsDTO.getRemarks());
        if(detailsDTO.getImgArr() != null && !"".equals(detailsDTO.getImgArr())) {
            result.setFileList(detailsDTO.getImgArr().split(","));
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/bureau/rule")
    public ResponseEntity<?> deleteConductBureauRule(@RequestParam("id") Integer id) {
        ConductBureauRuleDetailsDO detailsDTO = riskConductBureauRuleRecordService.findById(id);

        riskConductBureauRuleRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.conductRiskDetails(detailsDTO.getPoliceId(), LocalDate.parse(detailsDTO.getDate()));
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
                                              @RequestParam("pageSize") Integer pageSize,
                                              @RequestParam("type") String type,
                                              @RequestParam("key") String key,
                                              @RequestParam("deptId") Integer deptId) {
        if (deptId != null && deptId == 1) {
            deptId = null;
        }

        List<RiskConductVisitRecord> recordList = riskConductVisitRecordService.riskConductVisitRecordPage(pageIndex, pageSize, type, key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            ConductVisitPageResult pageResult = new ConductVisitPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());

            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setBigType(e.getBigTypeName());
            pageResult.setSmallType(e.getSmallTypeName());
            pageResult.setContent(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductVisitRecordService.getRiskConductVisitRecordPageCount(type, key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/visit")
    public ResponseEntity<?> addConductVisit(@RequestBody ConductVisitSaveParam saveParam) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

//        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductVisitRecord record = new RiskConductVisitRecord();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setContent(saveParam.getDesc());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());
        record.setIsReally(saveParam.getIsReally());
        record.setOriginId(saveParam.getOriginId());
        record.setIsEffective(1);

        riskConductVisitRecordService.insert(record);
        totalRiskDetailsService.conductRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/visit/{id}")
    public ResponseEntity<?> updateConductVisit(@PathVariable("id") Integer id,
                                                @RequestBody ConductVisitSaveParam saveParam) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

//        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(saveParam.getTypeId());
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setContent(saveParam.getDesc());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+time, "yyyy-MM-dd HH:mm:ss"));
        record.setType(saveParam.getTypeId());
        record.setRemarks(saveParam.getRemarks());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());
        record.setIsReally(saveParam.getIsReally());
        record.setOriginId(saveParam.getOriginId());

        riskConductVisitRecordService.updateByPrimaryKey(record);
        totalRiskDetailsService.conductRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/details")
    public ResponseEntity<?> conductVisitDetails(@RequestParam("id") Integer id) {
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);
        RiskConductVisitOrigin visitOrigin = riskConductVisitOriginService.findById(record.getOriginId());

        ConductVisitDetailsResult result = new ConductVisitDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setBigTypeId(record.getBigType());
        result.setSmallTypeId(record.getSmallType());
        result.setBigType(record.getBigTypeName());
        result.setSmallType(record.getSmallTypeName());
        result.setContent(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd"));
        result.setRemarks(record.getRemarks());
        result.setIdReally(record.getIsReally());
        result.setOriginId(record.getOriginId());
        if (visitOrigin != null ){
            result.setOriginName(visitOrigin.getName());
        }

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/visit")
    public ResponseEntity<?> deleteConductVisit(@RequestParam("id") Integer id) {
        RiskConductVisitRecord record = riskConductVisitRecordService.selectByPrimaryKey(id);

        riskConductVisitRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.conductRiskDetails(record.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
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
                                                       @RequestParam("pageSize") Integer pageSize,
                                                       @RequestParam("type") String type,
                                                       @RequestParam("key") String key) {
        List<RiskConductBureauRuleType> ruleTypeList = riskConductBureauRuleTypeService.riskConductBureauRuleTypePage(pageIndex, pageSize, type, key);

        Map<String, Object> result = new HashMap<>();
        result.put("data", ruleTypeList.stream().map(e -> {
            ConductBureauRuleTypePageResult pageResult = new ConductBureauRuleTypePageResult();
            pageResult.setId(e.getId());
            pageResult.setRootName(e.getRootName());
            pageResult.setTypeName(e.getTypeName());
            pageResult.setName(e.getName());
            pageResult.setDeductScore(e.getDeductScore());
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskConductBureauRuleTypeService.getRiskConductBureauRuleTypePageCount(type,key));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/bureau/rule/type")
    public ResponseEntity<?> addConductBureauRuleType(@RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        if (riskConductBureauRuleTypeService.countRuleTypeByNameAndRuleType(saveParam.getName(), saveParam.getParentId(), null) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductBureauRuleType ruleType = new RiskConductBureauRuleType();
        ruleType.setLevel(3);
        ruleType.setParentId(saveParam.getParentId());
        ruleType.setName(saveParam.getName());
        ruleType.setDeductScore(saveParam.getDeductScore());
        ruleType.setCreationDate(new Date());

        riskConductBureauRuleTypeService.insert(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/bureau/rule/type/{id}")
    public ResponseEntity<?> updateConductBureauRuleType(@PathVariable("id") Integer id,
                                                         @RequestBody ConductBureauRuleTypeSaveParam saveParam) {
        if (riskConductBureauRuleTypeService.countRuleTypeByNameAndRuleType(saveParam.getName(), saveParam.getParentId(), id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductBureauRuleType ruleType = riskConductBureauRuleTypeService.selectByPrimaryKey(id);
        ruleType.setParentId(saveParam.getParentId());
        ruleType.setName(saveParam.getName());
        ruleType.setDeductScore(saveParam.getDeductScore());
        ruleType.setUpdateDate(new Date());

        riskConductBureauRuleTypeService.updateByPrimaryKey(ruleType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/bureau/rule/type/details")
    public ResponseEntity<?> conductBureauRuleTypeDetails(@RequestParam("id") Integer id) {
        ConductBureauRuleTypeDetailsDO detailsDO = riskConductBureauRuleTypeService.findById(id);
        ConductBureauRuleTypeDetailsResult result = new ConductBureauRuleTypeDetailsResult();
        result.setRootTypeId(detailsDO.getRootTypeId());
        result.setRootTypeName(detailsDO.getRootTypeName());
        result.setParentId(detailsDO.getParentId());
        result.setParentTypeName(detailsDO.getParentTypeName());
        result.setName(detailsDO.getName());
        result.setDeductScore(detailsDO.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/bureau/rule/type")
    public ResponseEntity<?> deleteConductBureauRuleType(@RequestParam("id") Integer id) {
        if (riskConductBureauRuleRecordService.countByBureauRuleType(id) > 0) {
            return new ResponseEntity<>(DataListReturn.error("删除失败！类型已被使用！"), HttpStatus.OK);
        }
        riskConductBureauRuleTypeService.deleteByPrimaryKey(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/get/type/by/scoringOption")
    public ResponseEntity<?> getScoringOptionType(@RequestParam("scoringOption") String scoringOption) {
        return new ResponseEntity<>(DataListReturn.ok(riskConductBureauRuleTypeService.
                getTotalTypeByScoringOptionName(scoringOption)), HttpStatus.OK);
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
        if (riskConductVisitTypeService.countByNameAndParentId(saveParam.getName(), saveParam.getParentId(), null) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductVisitType visitType = new RiskConductVisitType();
        visitType.setParentId(saveParam.getParentId());
        visitType.setName(saveParam.getName());
        visitType.setDeductScore(saveParam.getDeductScore());
        visitType.setCreationDate(new Date());

        riskConductVisitTypeService.insert(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/visit/type/{id}")
    public ResponseEntity<?> updateConductVisitType(@PathVariable("id") Integer id,
                                                    @RequestBody ConductVisitTypeSaveParam saveParam) {
        if (riskConductVisitTypeService.countByNameAndParentId(saveParam.getName(), saveParam.getParentId(), id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("该类型已存在！"), HttpStatus.OK);
        }
        RiskConductVisitType visitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        visitType.setParentId(saveParam.getParentId());
        visitType.setName(saveParam.getName());
        visitType.setDeductScore(saveParam.getDeductScore());
        visitType.setUpdateDate(new Date());

        riskConductVisitTypeService.updateByPrimaryKeySelective(visitType);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/type/details")
    public ResponseEntity<?> conductVisitTypeDetails(@RequestParam("id") Integer id) {
        RiskConductVisitType riskConductVisitType = riskConductVisitTypeService.selectByPrimaryKey(id);
        ConductVisitTypeDetailsResult result = new ConductVisitTypeDetailsResult();
        result.setParentId(riskConductVisitType.getParentId());
        result.setTypeName(riskConductVisitType.getTypeName());
        result.setName(riskConductVisitType.getName());
        result.setDeductScore(riskConductVisitType.getDeductScore());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/visit/type")
    public ResponseEntity<?> deleteConductVisitType(@RequestParam("id") Integer id) {
        if (riskConductVisitRecordService.countByTypeId(id) >= 1) {
            return new ResponseEntity<>(DataListReturn.error("删除失败！类型已被使用！"), HttpStatus.OK);
        }
        riskConductVisitTypeService.deleteByPrimaryKey(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/visit/origin")
    public ResponseEntity<?> conductVisitOrigin() {

        return new ResponseEntity<>(DataListReturn.ok(riskConductVisitOriginService.findAllVisitOrigin()), HttpStatus.OK);
    }

}
