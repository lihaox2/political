package com.bayee.political.controller.admin;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.RiskDataOperationLog;
import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.User;
import com.bayee.political.enums.RiskDataOperationType;
import com.bayee.political.filter.UserSession;
import com.bayee.political.pojo.dto.DutyDetailsDO;
import com.bayee.political.pojo.dto.DutyPageDO;
import com.bayee.political.json.DutyDetailsResult;
import com.bayee.political.json.DutyPageResult;
import com.bayee.political.json.DutySaveParam;
import com.bayee.political.service.RiskDataOperationLogService;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;
import com.bayee.political.service.TotalRiskDetailsService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author xxl
 * @date 2021/5/7
 */
@RestController
public class DutyController {

    @Autowired
    RiskDutyDealPoliceRecordService riskDutyDealPoliceRecordService;

    @Autowired
    UserService userService;

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    @Autowired
    RiskDataOperationLogService riskDataOperationLogService;

    /**
     * 接警执勤
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/duty/page")
    public ResponseEntity<?> dutyPage(@RequestParam("pageIndex") Integer pageIndex,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("errorId") Integer errorId,
                                      @RequestParam("key") String key,
                                      @RequestParam("deptId") Integer deptId,
                                      @RequestParam("type") String type,
                                      HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
//        if (loginUser == null) {
//            return new ResponseEntity(DataListReturn.error("请重新登录"), HttpStatus.OK);
//        }
        if (deptId != null && deptId == 1 || (loginUser != null && loginUser.getIsActive() != null
                && loginUser.getIsActive() == 999)) {
            deptId = null;
        }
        List<Integer> informationId = null;
        if (StrUtil.isNotBlank(type)) {
            informationId = Arrays.stream(type.split(",")).map(Integer::parseInt).collect(Collectors.toList());
        }

        List<DutyPageDO> recordList = riskDutyDealPoliceRecordService.riskDutyDealPoliceRecordPage(pageIndex, pageSize, informationId, errorId, key, deptId);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            DutyPageResult pageResult = new DutyPageResult();
            pageResult.setDate(e.getDate());
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setPoliceName(e.getPoliceName());
            pageResult.setPoliceListCode(e.getPoliceListCode());
            pageResult.setJurisdiction(e.getJurisdiction());
            pageResult.setInformationName(e.getInformationName());
            pageResult.setErrorName(e.getErrorName());
            pageResult.setDeductScore(e.getDeductScore());

            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskDutyDealPoliceRecordService.riskDutyDealPoliceRecordPageCount(informationId, errorId, key, deptId));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/duty")
    public ResponseEntity<?> addDuty(@RequestBody DutySaveParam saveParam,
                                     HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskDutyDealPoliceRecord record = new RiskDutyDealPoliceRecord();
        record.setInformationId(saveParam.getInformationId());
        record.setErrorId(saveParam.getErrorId());
        record.setPoliceListCode(saveParam.getPoliceListCode());
        record.setJurisdiction(saveParam.getJurisdiction());
        record.setPoliceListInfo(saveParam.getPoliceListInfo());
        record.setIsVerified(saveParam.getIsVerified());
        record.setPoliceId(saveParam.getPoliceId());
        record.setContent(saveParam.getDesc());
        record.setInputTime(new Date());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());
        record.setImgArr(saveParam.getFileList());
        record.setIsEffective(1);

        riskDutyDealPoliceRecordService.insert(record);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.ADD.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11004);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        totalRiskDetailsService.dutyRiskDetails(LocalDate.parse(saveParam.getDate().substring(0, 10)));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/duty/{id}")
    public ResponseEntity<?> updateDuty(@PathVariable("id") Integer id,@RequestBody DutySaveParam saveParam,
                                        HttpServletRequest httpServletRequest) {
        if (!userService.checkPoliceExists(saveParam.getPoliceId())){
            return new ResponseEntity(DataListReturn.error("警号不存在！"), HttpStatus.OK);
        }

        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskDutyDealPoliceRecord record = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);
        record.setInformationId(saveParam.getInformationId());
        record.setErrorId(saveParam.getErrorId());
        record.setPoliceListCode(saveParam.getPoliceListCode());
        record.setJurisdiction(saveParam.getJurisdiction());
        record.setPoliceListInfo(saveParam.getPoliceListInfo());
        record.setIsVerified(saveParam.getIsVerified());
        record.setPoliceId(saveParam.getPoliceId());
        record.setContent(saveParam.getDesc());
        record.setUpdateDate(new Date());
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate(), "yyyy-MM-dd HH:mm:ss"));
        record.setDeductionScore(saveParam.getDeductScore());
        record.setImgArr(saveParam.getFileList());

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.UPDATE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11004);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskDutyDealPoliceRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.dutyRiskDetails(LocalDate.parse(saveParam.getDate().substring(0, 10)));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/duty/details")
    public ResponseEntity<?> dutyDetails(@RequestParam("id") Integer id) {
        DutyDetailsDO detailsDO = riskDutyDealPoliceRecordService.findById(id);
        DutyDetailsResult result = new DutyDetailsResult();
        result.setPoliceListCode(detailsDO.getPoliceListCode());
        result.setJurisdiction(detailsDO.getJurisdiction());
        result.setInformationId(detailsDO.getInformationId());
        result.setInformationName(detailsDO.getInformationName());
        result.setPoliceListInfo(detailsDO.getPoliceListInfo());
        result.setErrorId(detailsDO.getErrorId());
        result.setErrorName(detailsDO.getErrorName());
        result.setIsVerified(detailsDO.getIsVerified());
        result.setPoliceId(detailsDO.getPoliceId());
        result.setPoliceName(detailsDO.getPoliceName());
        result.setDesc(detailsDO.getDesc());
        result.setDeductScore(detailsDO.getDeductScore());
        result.setDate(detailsDO.getDate());
        if (detailsDO.getImgArr() != null && !"".equals(detailsDO.getImgArr())) {
            result.setFileList(detailsDO.getImgArr().split(","));
        }
        result.setReplaceErrorCount(riskDutyDealPoliceRecordService.getReplaceErrorCount(detailsDO.getPoliceId(), detailsDO.getErrorId()));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/duty")
    public ResponseEntity<?> deleteDuty(@RequestParam("id") Integer id,
                                        HttpServletRequest httpServletRequest) {
        User loginUser = UserSession.getCurrentLoginPolice(httpServletRequest);
        RiskDutyDealPoliceRecord record = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);

        //添加数据操作记录
        RiskDataOperationLog log = new RiskDataOperationLog();
        log.setOperationType(RiskDataOperationType.DELETE.getValue());
        log.setOperationPoliceId(loginUser == null ? null : loginUser.getPoliceId());
        log.setDataOriginType(11004);
        log.setDataOriginId(record.getId());
        log.setDataOriginPoliceId(record.getPoliceId());
        log.setDataOriginBusinessDate(record.getCreationDate());
        log.setCreationDate(new Date());
        /*riskDataOperationLogService.insertOperationLog(log);*/;

        riskDutyDealPoliceRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.dutyRiskDetails(LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
