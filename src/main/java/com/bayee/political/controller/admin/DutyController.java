package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskDutyDealPoliceRecord;
import com.bayee.political.domain.User;
import com.bayee.political.pojo.json.DutyDetailsResult;
import com.bayee.political.pojo.json.DutyPageResult;
import com.bayee.political.pojo.json.DutySaveParam;
import com.bayee.political.service.RiskDutyDealPoliceRecordService;
import com.bayee.political.service.TotalRiskDetailsService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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

    /**
     * 接警执勤
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/duty/page")
    public ResponseEntity<?> dutyPage(@RequestParam("pageIndex") Integer pageIndex,
                                      @RequestParam("pageSize") Integer pageSize,
                                      @RequestParam("type") Integer type, @RequestParam("key") String key) {
        List<RiskDutyDealPoliceRecord> recordList = riskDutyDealPoliceRecordService.riskDutyDealPoliceRecordPage(pageIndex, pageSize,type,key);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            DutyPageResult pageResult = new DutyPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setTypeName(e.getTypeName());
            pageResult.setDesc(e.getContent());
            pageResult.setDeductScore(e.getDeductionScore());
            pageResult.setDate(DateUtils.formatDate(e.getCreationDate(), "yyyy-MM-dd"));

            return pageResult;
        }).collect(Collectors.toList()));

        result.put("totalCount", riskDutyDealPoliceRecordService.riskDutyDealPoliceRecordPageCount(type,key));
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/duty")
    public ResponseEntity<?> addDuty(@RequestBody DutySaveParam saveParam) {
        RiskDutyDealPoliceRecord record = new RiskDutyDealPoliceRecord();
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getDesc());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));

        riskDutyDealPoliceRecordService.insert(record);
        totalRiskDetailsService.dutyRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/duty/{id}")
    public ResponseEntity<?> updateDuty(@PathVariable("id") Integer id,@RequestBody DutySaveParam saveParam) {
        RiskDutyDealPoliceRecord record = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);
        String time = LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        record.setPoliceId(saveParam.getPoliceId());
        record.setType(saveParam.getTypeId());
        record.setContent(saveParam.getDesc());
        record.setDeductionScore(saveParam.getDeductScore());
        record.setInputTime(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));
        record.setCreationDate(DateUtils.parseDate(saveParam.getDate() +" "+ time, "yyyy-MM-dd HH:mm:ss"));
        record.setUpdateDate(new Date());

        riskDutyDealPoliceRecordService.updateByPrimaryKeySelective(record);
        totalRiskDetailsService.dutyRiskDetails(saveParam.getPoliceId(), LocalDate.parse(saveParam.getDate()));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/duty/details")
    public ResponseEntity<?> dutyDetails(@RequestParam("id") Integer id) {
        RiskDutyDealPoliceRecord record = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);
        DutyDetailsResult result = new DutyDetailsResult();
        User user = userService.findByPoliceId(record.getPoliceId());
        result.setPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setTypeId(record.getType());
        result.setDesc(record.getContent());
        result.setDeductScore(record.getDeductionScore());
        result.setDate(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd"));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/duty")
    public ResponseEntity<?> deleteDuty(@RequestParam("id") Integer id) {
        RiskDutyDealPoliceRecord record = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);

        riskDutyDealPoliceRecordService.deleteByPrimaryKey(id);
        totalRiskDetailsService.dutyRiskDetails(record.getPoliceId(), LocalDate.parse(DateUtils.formatDate(record.getCreationDate(), "yyyy-MM-dd")));
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
