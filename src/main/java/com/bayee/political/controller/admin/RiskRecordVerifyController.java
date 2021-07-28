package com.bayee.political.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.RiskRecordVerify;
import com.bayee.political.domain.RiskRecordVerifyType;
import com.bayee.political.domain.User;
import com.bayee.political.json.*;
import com.bayee.political.service.RiskRecordVerifyService;
import com.bayee.political.service.RiskRecordVerifyTypeService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author xxl
 * @date 2021/7/20
 */
@RestController
@RequestMapping("/verify")
public class RiskRecordVerifyController {

    @Autowired
    RiskRecordVerifyTypeService riskRecordVerifyTypeService;

    @Autowired
    RiskRecordVerifyService riskRecordVerifyService;

    @Autowired
    UserService userService;

    List<Integer> scorerList = new ArrayList<>(Arrays.asList(3, 4, 5, 6, 7, 9, 10));

    @GetMapping("/record/statistics")
    public ResponseEntity<?> riskRecordVerifyStatistics() {

        return new ResponseEntity<>(DataListReturn.ok(riskRecordVerifyService.riskRecordVerifyStatistics()), HttpStatus.OK);
    }

    /**
     * 数据项审核列表
     * @param queryParam
     * @return
     */
    @GetMapping("/record/page")
    public ResponseEntity<?> riskRecordVerifyPage(RiskRecordVerifyPageQueryParam queryParam) {
        User user = userService.findByPoliceId(queryParam.getCurrentPolice());
        if (!scorerList.contains(user.getScorer())) {
            Map<String, Object> result = new HashMap<>();
            result.put("data", new ArrayList<>());
            result.put("totalCount", 0);
            result.put("pageIndex", queryParam.getPageIndex());
            result.put("pageSize", queryParam.getPageSize());
            return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
        }

        Integer typeId = null;
        switch (user.getScorer()) {
            case 4:
            case 5: typeId = 1011; break;
            case 6: typeId = 1014; break;
            case 7: typeId = 1013; break;
            case 9: typeId = 999; break;
            case 3:
            case 10: typeId = null; break;
        }
        if (queryParam.getAppealType() == null) {
            queryParam.setAppealType(typeId);
        }

        Map<String, Object> result = new HashMap<>();
        result.put("data", riskRecordVerifyService.riskRecordVerifyPage(queryParam));
        result.put("totalCount", riskRecordVerifyService.countRiskRecordVerifyPage(queryParam));
        result.put("pageIndex", queryParam.getPageIndex());
        result.put("pageSize", queryParam.getPageSize());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 数据项审核
     * @param param
     * @return
     */
    @PostMapping("/check/record")
    public ResponseEntity<?> checkRiskRecord(@RequestBody CheckRiskRecordParam param) {
        if (!StrUtil.isNotBlank(param.getCheckPoliceId()) || !StrUtil.isNotBlank(param.getCheckContent()) || param.getId() == null) {
            return new ResponseEntity<>(DataListReturn.error("数据提交异常"), HttpStatus.OK);
        }
        RiskRecordVerify recordVerify = riskRecordVerifyService.findById(param.getId());
        recordVerify.setCheckContent(param.getCheckContent());
        recordVerify.setIsAgree(param.getIsAgree());
        recordVerify.setCheckDeductionScore(param.getCheckDeductionScore());
        recordVerify.setCheckDeductionPoliceId(param.getCheckDeductionPolice());
        recordVerify.setCheckPoliceId(param.getCheckPoliceId());
        recordVerify.setCheckDate(new Date());
        recordVerify.setUpdateDate(new Date());

        riskRecordVerifyService.checkRecord(recordVerify);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 添加申诉
     * @param saveParam
     * @return
     */
    @PostMapping("/add/appeal")
    public ResponseEntity<?> addAppeal(@RequestBody AppealSaveParam saveParam) {
        if (saveParam.getTypeId() == null || saveParam.getModuleId() == null || saveParam.getAppealPoliceId() == null) {
            return new ResponseEntity<>(DataListReturn.error("数据提交异常"), HttpStatus.OK);
        }
        if (riskRecordVerifyService.checkRecordFlag(saveParam.getTypeId(), saveParam.getModuleId(), null)) {
            return new ResponseEntity<>(DataListReturn.error("改项数据已申诉过"), HttpStatus.OK);
        }

        RiskRecordVerify recordVerify = new RiskRecordVerify();
        recordVerify.setTypeId(saveParam.getTypeId());
        recordVerify.setModuleId(saveParam.getModuleId());
        recordVerify.setAppealPoliceId(saveParam.getAppealPoliceId());
        recordVerify.setAppealContent(saveParam.getAppealContent());
        recordVerify.setAppealScore(saveParam.getAppealScore());
        recordVerify.setAppealDate(new Date());
        recordVerify.setState(1);
        recordVerify.setCreationDate(new Date());

        riskRecordVerifyService.addAppeal(recordVerify);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 撤销申诉
     * @param typeId
     * @param moduleId
     * @return
     */
    @PostMapping("/cancel/appeal")
    public ResponseEntity<?> cancelAppeal(@RequestBody CancelAppealParam appealParam) {
        Integer typeId = appealParam.getTypeId();
        Integer moduleId = appealParam.getModuleId();
        if (typeId == null || typeId == 0 || moduleId == null || moduleId == 0) {
            return new ResponseEntity<>(DataListReturn.error("数据提交异常！"), HttpStatus.OK);
        }
        boolean flag = riskRecordVerifyService.checkRecordFlag(typeId, moduleId, 2)
                || riskRecordVerifyService.checkRecordFlag(typeId, moduleId, 3);
        if (flag) {
            return new ResponseEntity<>(DataListReturn.error("撤销失败，数据项已不能撤销！"), HttpStatus.OK);
        }

        riskRecordVerifyService.cancelAppeal(typeId, moduleId);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 申诉详情查询
     * @return
     */
    @GetMapping("/appeal/details")
    public ResponseEntity<?> appealDetails(Integer typeId, Integer moduleId) {

        return new ResponseEntity<>(DataListReturn.ok(riskRecordVerifyService.appealDetails(typeId, moduleId)), HttpStatus.OK);
    }

    /**
     * 数据项未审核查询
     * @param id
     * @return
     */
    @GetMapping("/record/to/verify")
    public ResponseEntity<?> riskRecordVerifyToVerify(Integer id) {
        RiskRecordVerify recordVerify = riskRecordVerifyService.findById(id);
        RiskRecordVerifyType recordVerifyType = riskRecordVerifyTypeService.findById(recordVerify.getTypeId());
        User user = userService.findByPoliceId(recordVerify.getAppealPoliceId());

        RiskRecordVerifyToVerifyResult toVerifyResult = new RiskRecordVerifyToVerifyResult();
        toVerifyResult.setName(user.getName());
        toVerifyResult.setPoliceId(user.getPoliceId());
        toVerifyResult.setAppealType(recordVerify.getTypeId());
        toVerifyResult.setAppealTypeName(recordVerifyType.getName());
        toVerifyResult.setAppealContent(recordVerify.getAppealContent());
        toVerifyResult.setAppealScore(recordVerify.getAppealScore());
        toVerifyResult.setModuleId(recordVerify.getModuleId());
        toVerifyResult.setAppealDate(DateUtils.formatDate(recordVerify.getAppealDate(), "yyyy-MM-dd HH:mm:ss"));

        return new ResponseEntity<>(DataListReturn.ok(toVerifyResult), HttpStatus.OK);
    }

    /**
     * 数据项审核详情
     * @param id
     * @return
     */
    @GetMapping("/record/details")
    public ResponseEntity<?> riskRecordVerifyDetails(Integer id) {

        return new ResponseEntity<>(DataListReturn.ok(riskRecordVerifyService.findVerifyDOById(id)), HttpStatus.OK);
    }

    /**
     * 查询所有申诉类型
     * @return
     */
    @GetMapping("/type/list")
    public ResponseEntity<?> verifyTypeList(@RequestParam("policeId") String policeId) {
        User user = userService.findByPoliceId(policeId);
        if (!scorerList.contains(user.getScorer())) {
            return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
        }

        return new ResponseEntity<>(DataListReturn.ok(riskRecordVerifyTypeService.findAllVerifyType(user.getScorer())), HttpStatus.OK);
    }

}
