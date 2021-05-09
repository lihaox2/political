package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskHealthRecordInfo;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.pojo.json.HealthDetailsResult;
import com.bayee.political.pojo.json.HealthPageResult;
import com.bayee.political.service.RiskHealthRecordInfoService;
import com.bayee.political.service.RiskHealthRecordService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 健康
 *
 * @author xxl
 * @date 2021/5/7
 */
@RestController
public class HealthController {

    @Autowired
    RiskHealthRecordService riskHealthRecordService;

    @Autowired
    RiskHealthRecordInfoService riskHealthRecordInfoService;

    @Autowired
    UserService userService;

    /**
     * 健康管理
     * @param pageIndex
     * @param pageSize
     * @return
     */
    @GetMapping("/health/page")
    public ResponseEntity<?> healthPage(@RequestParam("pageIndex") Integer pageIndex,
                                        @RequestParam("pageSize") Integer pageSize) {
        List<RiskHealthRecord> recordList = riskHealthRecordService.riskRiskHealthRecordPage(pageIndex, pageSize);

        Map<String, Object> result = new HashMap<>();
        result.put("data", recordList.stream().map(e -> {
            HealthPageResult pageResult = new HealthPageResult();
            User user = userService.findByPoliceId(e.getPoliceId());
            if (user != null) {
                pageResult.setPoliceName(user.getName());
            }
            pageResult.setId(e.getId());
            pageResult.setPoliceId(e.getPoliceId());
            pageResult.setIsOverweight(e.getIsOverweight() == 1 ? "是" : "否");
            pageResult.setIsHyperlipidemia(e.getIsHyperlipidemia() == 1 ? "是" : "否");
            pageResult.setIsHypertension(e.getIsHypertension() == 1 ? "是" : "否");
            pageResult.setIsHyperglycemia(e.getIsHyperglycemia() == 1 ? "是" : "否");
            pageResult.setIsHyperuricemia(e.getIsHyperuricemia() == 1 ? "是" : "否");
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskHealthRecordService.getRiskReportRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/health")
    public ResponseEntity<?> addHealth() {

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/health")
    public ResponseEntity<?> updateHealth(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/health/details")
    public ResponseEntity<?> healthDetails(@RequestParam("id") Integer id) {
        HealthDetailsResult result = new HealthDetailsResult();
        RiskHealthRecordInfo recordInfo = riskHealthRecordInfoService.findByRecordId(id);
        if (recordInfo == null) {
            recordInfo = new RiskHealthRecordInfo();
        }else {
            User user = userService.findByPoliceId(recordInfo.getPoliceId());
            result.setPoliceName(user.getName());
        }
        result.setPoliceId(recordInfo.getPoliceId());
        result.setHeight(recordInfo.getHeight());
        result.setWeight(recordInfo.getWeight());
        result.setHighDensityLipoprotein(recordInfo.getHighDensityLipoprotein());
        result.setLowDensityLipoprotein(recordInfo.getLowDensityLipoprotein());
        result.setTriglyceride(recordInfo.getTriglyceride());
        result.setCholesterol(recordInfo.getCholesterol());
        result.setReceiveCompression(recordInfo.getReceiveCompression());
        result.setDiastolicPressure(recordInfo.getDiastolicPressure());
        result.setBloodSugar(recordInfo.getBloodSugar());
        result.setSerumUricAcid(recordInfo.getSerumUricAcid());
        result.setIsProstate(recordInfo.getIsProstate());
        result.setProstateDesc(recordInfo.getProstateDesc());
        result.setIsMajorDiseases(recordInfo.getIsMajorDiseases());
        result.setIsHeart(recordInfo.getIsHeart());
        result.setIsTumorAntigen(recordInfo.getIsTumorAntigen());
        result.setIsOrthopaedics(recordInfo.getIsOrthopaedics());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @GetMapping("/delete/health")
    public ResponseEntity<?> deleteHealth(@RequestParam("id") Integer id) {

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
