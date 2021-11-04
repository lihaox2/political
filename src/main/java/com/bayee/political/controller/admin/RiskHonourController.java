package com.bayee.political.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.RiskDataOperationLog;
import com.bayee.political.domain.RiskHonour;
import com.bayee.political.domain.RiskHonourType;
import com.bayee.political.domain.User;
import com.bayee.political.enums.RiskDataOperationType;
import com.bayee.political.filter.UserSession;
import com.bayee.political.json.RiskHonourDetailsResult;
import com.bayee.political.json.RiskHonourPageQueryParam;
import com.bayee.political.json.RiskHonourSaveParam;
import com.bayee.political.service.RiskDataOperationLogService;
import com.bayee.political.service.RiskHonourService;
import com.bayee.political.service.RiskHonourTypeService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author xxl
 * @date 2021/8/26 20:24
 */
@RestController
@RequestMapping("/honour")
public class RiskHonourController {

    @Autowired
    RiskHonourTypeService riskHonourTypeService;

    @Autowired
    RiskHonourService riskHonourService;

    @Autowired
    UserService userService;

    @Autowired
    RiskDataOperationLogService riskDataOperationLogService;

    @GetMapping("/page")
    public ResponseEntity<?> riskHonourPage(RiskHonourPageQueryParam queryParam) {

        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());
        result.put("data", riskHonourService.riskHonourPage(queryParam));
        result.put("totalCount", riskHonourService.riskHonourPageCount(queryParam));
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addRiskHonour(@RequestBody RiskHonourSaveParam saveParam,
                                           HttpServletRequest httpServletRequest) {
        User user = userService.findByPoliceId(saveParam.getPoliceId());
        if (user == null || user.getId() == null) {
            return new ResponseEntity<>(DataListReturn.error("警员不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskHonour honour = new RiskHonour();
        honour.setPoliceId(saveParam.getPoliceId());
        honour.setHonourName(saveParam.getHonourName());
        honour.setHonourReason(saveParam.getHonourReason());
        honour.setHonourTypeCode(saveParam.getHonourTypeCode());
        honour.setHonourUnit(saveParam.getHonourUnit());
        honour.setHonourUnitLevel(saveParam.getHonourUnitLevel());
        honour.setRemark(saveParam.getRemark());
        honour.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime(), "yyyy-MM-dd"));
        honour.setCreationDate(new Date());
        honour.setDocumentNumber(saveParam.getDocumentNumber());
        riskHonourService.addRiskHonour(honour);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11009);
        log.setDataOriginId(honour.getId());
        log.setDataOriginPoliceId(honour.getPoliceId());
        log.setDataOriginBusinessDate(honour.getBusinessTime());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/details")
    public ResponseEntity<?> riskHonourDetails(@RequestParam("id") Integer id) {

        RiskHonour honour = riskHonourService.riskHonourDetails(id);
        User user = userService.findByPoliceId(honour.getPoliceId());
        RiskHonourType honourType = riskHonourTypeService.findByTypeCode(honour.getHonourTypeCode());

        RiskHonourDetailsResult result = new RiskHonourDetailsResult();
        result.setPoliceId(honour.getPoliceId());
        result.setPoliceName(user.getName());
        result.setHonourName(honour.getHonourName());
        result.setHonourReason(honour.getHonourReason());
        result.setHonourTypeCode(honour.getHonourTypeCode());
        result.setHonourTypeName(honourType.getName());
        result.setHonourUnit(honour.getHonourUnit());
        result.setHonourUnitLevel(honour.getHonourUnitLevel());
        result.setRemark(honour.getRemark());
        result.setDocumentNumber(honour.getDocumentNumber());
        result.setBusinessTime(DateUtils.formatDate(honour.getBusinessTime(), "yyyy-MM"));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/update/{id}")
    public ResponseEntity<?> updateRiskHonour(@PathVariable Integer id, @RequestBody RiskHonourSaveParam saveParam,
                                              HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskHonour honour = riskHonourService.riskHonourDetails(id);
        honour.setHonourName(saveParam.getHonourName());
        honour.setHonourReason(saveParam.getHonourReason());
        honour.setHonourTypeCode(saveParam.getHonourTypeCode());
        honour.setHonourUnit(saveParam.getHonourUnit());
        honour.setHonourUnitLevel(saveParam.getHonourUnitLevel());
        honour.setRemark(saveParam.getRemark());
        honour.setBusinessTime(DateUtils.parseDate(saveParam.getBusinessTime(), "yyyy-MM-dd"));
        honour.setUpdateDate(new Date());
        honour.setDocumentNumber(saveParam.getDocumentNumber());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11009);
        log.setDataOriginId(honour.getId());
        log.setDataOriginPoliceId(honour.getPoliceId());
        log.setDataOriginBusinessDate(honour.getBusinessTime());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskHonourService.updateRiskHonour(honour);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @DeleteMapping("/delete/honour")
    public ResponseEntity<?> deleteRiskHonour(@RequestParam("id") Integer id,
                                              HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskHonour honour = riskHonourService.riskHonourDetails(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11009);
        log.setDataOriginId(honour.getId());
        log.setDataOriginPoliceId(honour.getPoliceId());
        log.setDataOriginBusinessDate(honour.getBusinessTime());
        log.setCreationDate(new Date());
        riskDataOperationLogService.insertOperationLog(log);

        riskHonourService.deleteRiskHonour(id);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 表彰奖励类型
     * @return
     */
    @GetMapping("/type/list")
    public ResponseEntity<?> getHonourTypeList() {
        return new ResponseEntity<>(DataListReturn.ok(riskHonourTypeService.queryAllHonourType()), HttpStatus.OK);
    }

    /**
     * 授奖单位
     * @return
     */
    @GetMapping("/unit/list")
    public ResponseEntity<?> getHonourUnitList() {
        Map<String, String> result0 = new HashMap<>();
        result0.put("key", "区政府");
        result0.put("value", "区政府");

        Map<String, String> result1 = new HashMap<>();
        result1.put("key", "市局");
        result1.put("value", "市局");

        Map<String, String> result2 = new HashMap<>();
        result2.put("key", "市政府");
        result2.put("value", "市政府");

        Map<String, String> result3 = new HashMap<>();
        result3.put("key", "省厅");
        result3.put("value", "省厅");

        Map<String, String> result4 = new HashMap<>();
        result4.put("key", "分局");
        result4.put("value", "分局");

        Map<String, String> result5 = new HashMap<>();
        result5.put("key", "省委");
        result5.put("value", "省委");

        Map<String, String> result6 = new HashMap<>();
        result6.put("key", "省政府");
        result6.put("value", "省政府");

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result0, result1, result2, result3, result4,
                result5, result6)), HttpStatus.OK);
    }

    /**
     * 授奖单位级别
     * @return
     */
    @GetMapping("/unitLevel/list")
    public ResponseEntity<?> getHonourUnitLevelList() {
        Map<String, String> result0 = new HashMap<>();
        result0.put("key", "区政府级");
        result0.put("value", "区政府级");

        Map<String, String> result1 = new HashMap<>();
        result1.put("key", "市局级");
        result1.put("value", "市局级");

        Map<String, String> result2 = new HashMap<>();
        result2.put("key", "市政府级");
        result2.put("value", "市政府级");

        Map<String, String> result3 = new HashMap<>();
        result3.put("key", "省厅级");
        result3.put("value", "省厅级");

        Map<String, String> result4 = new HashMap<>();
        result4.put("key", "分局级");
        result4.put("value", "分局级");

        Map<String, String> result5 = new HashMap<>();
        result5.put("key", "省委级");
        result5.put("value", "省委级");

        Map<String, String> result6 = new HashMap<>();
        result6.put("key", "省政府级");
        result6.put("value", "省政府级");

        Map<String, String> result7 = new HashMap<>();
        result7.put("key", "区委级");
        result7.put("value", "区委级");

        return new ResponseEntity<>(DataListReturn.ok(Arrays.asList(result0, result1, result2, result3, result4,
                result5, result6, result7)), HttpStatus.OK);
    }

}
