package com.bayee.political.controller.admin;

import com.bayee.political.domain.RiskHealthRecord;
import com.bayee.political.domain.RiskHealthRecordInfo;
import com.bayee.political.domain.RiskReportRecord;
import com.bayee.political.domain.User;
import com.bayee.political.pojo.json.HealthDetailsResult;
import com.bayee.political.pojo.json.HealthPageResult;
import com.bayee.political.pojo.json.HealthSaveParam;
import com.bayee.political.service.RiskHealthRecordInfoService;
import com.bayee.political.service.RiskHealthRecordService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
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
                                        @RequestParam("pageSize") Integer pageSize,
                                        @RequestParam("type") Integer type,@RequestParam("typeFlag") Integer typeFlag,
                                        @RequestParam("key") String key) {
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
            pageResult.setIsOverweight(e.getIsOverweight() == null ? "否" : e.getIsOverweight() == 1 ? "是" : "否");
            pageResult.setIsHyperlipidemia(e.getIsHyperlipidemia() == null ? "否" : e.getIsHyperlipidemia() == 1 ? "是" : "否");
            pageResult.setIsHypertension(e.getIsHypertension() == null ? "否" : e.getIsHypertension() == 1 ? "是" : "否");
            pageResult.setIsHyperglycemia(e.getIsHyperglycemia() == null ? "否" : e.getIsHyperglycemia() == 1 ? "是" : "否");
            pageResult.setIsHyperuricemia(e.getIsHyperuricemia() == null ? "否" : e.getIsHyperuricemia() == 1 ? "是" : "否");
            return pageResult;
        }).collect(Collectors.toList()));
        result.put("totalCount", riskHealthRecordService.getRiskReportRecordPageCount());
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        return new ResponseEntity(DataListReturn.ok(result), HttpStatus.OK);
    }

    @PostMapping("/add/health")
    public ResponseEntity<?> addHealth(@RequestBody HealthSaveParam saveParam) {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));

        Double bmi = saveParam.getWeight()/Math.pow(saveParam.getHeight()/100,2);
        Integer bmiId = 0;
        if(bmi<=18.40) {
            bmiId = 1;
        }else if(bmi>=18.41 && bmi<=23.99) {
            bmiId = 2;
        }else if(bmi>=24 && bmi<=27.99) {
            bmiId = 3;
        }else if(bmi>=28 && bmi<=100.00) {
            bmiId = 4;
        }

        RiskHealthRecord record = new RiskHealthRecord();
        record.setPoliceId(saveParam.getPoliceId());
        record.setYear(year);
        record.setHeight(saveParam.getHeight());
        record.setWeight(saveParam.getWeight());
        record.setBmi(bmi);
        record.setBmiId(bmiId);
        record.setIsHyperlipidemia(0);
        if(saveParam.getTriglyceride() > 1.7 || saveParam.getCholesterol() > 5.72) {
            record.setIsHyperlipidemia(1);
        }

        record.setIsHypertension(0);
        if (saveParam.getReceiveCompression() > 140 || saveParam.getDiastolicPressure() > 90) {
            record.setIsHypertension(1);
        }

        record.setIsHyperglycemia(0);
        if (saveParam.getBloodSugar() > 6.1) {
            record.setIsHyperglycemia(1);
        }

        record.setIsHyperuricemia(0);
        if (saveParam.getSerumUricAcid() > 420) {
            record.setIsHyperuricemia(1);
        }
        record.setIsProstate(saveParam.getIsProstate() == null ? 0 : saveParam.getIsProstate());
        record.setIsMajorDiseases(saveParam.getIsMajorDiseases() == null ? 0 : saveParam.getIsMajorDiseases());
        record.setIsHeart(saveParam.getIsHeart() == null ? 0 : saveParam.getIsHeart());
        record.setIsTumorAntigen(saveParam.getIsTumorAntigen() == null ? 0 : saveParam.getIsTumorAntigen());
        record.setIsOrthopaedics(saveParam.getIsOrthopaedics() == null ? 0 : saveParam.getIsOrthopaedics());
        record.setOrthopaedicsDescribe(saveParam.getOrthopaedicsDesc());
        record.setTumorAntigenDescribe(saveParam.getTumorAntigenDesc());
        record.setHeartDescribe(saveParam.getHeartDesc());
        record.setIsOverweight(0);
        record.setMajorDiseasesDescribe(saveParam.getMajorDiseasesDesc());
        record.setCreationDate(new Date());
        /*record.setUpdateDate();
        record.setBlood();*/

        riskHealthRecordService.insert(record);

        RiskHealthRecordInfo recordInfo = new RiskHealthRecordInfo();
        recordInfo.setRecordId(record.getId());
        recordInfo.setMajorDiseasesDesc(saveParam.getMajorDiseasesDesc());
        recordInfo.setHeartDesc(saveParam.getHeartDesc());
        recordInfo.setTumorAntigenDesc(saveParam.getTumorAntigenDesc());
        recordInfo.setOrthopaedicsDesc(saveParam.getOrthopaedicsDesc());
        recordInfo.setPoliceId(saveParam.getPoliceId());
        recordInfo.setHeight(saveParam.getHeight());
        recordInfo.setWeight(saveParam.getWeight());
        recordInfo.setHighDensityLipoprotein(saveParam.getHighDensityLipoprotein());
        recordInfo.setLowDensityLipoprotein(saveParam.getLowDensityLipoprotein());
        recordInfo.setTriglyceride(saveParam.getTriglyceride());
        recordInfo.setCholesterol(saveParam.getCholesterol());
        recordInfo.setReceiveCompression(saveParam.getReceiveCompression());
        recordInfo.setDiastolicPressure(saveParam.getDiastolicPressure());
        recordInfo.setBloodSugar(saveParam.getBloodSugar());
        recordInfo.setSerumUricAcid(saveParam.getSerumUricAcid());
        recordInfo.setIsProstate(saveParam.getIsProstate());
        recordInfo.setProstateDesc(saveParam.getProstateDesc());
        recordInfo.setIsMajorDiseases(saveParam.getIsMajorDiseases());
        recordInfo.setIsHeart(saveParam.getIsHeart());
        recordInfo.setIsTumorAntigen(saveParam.getIsTumorAntigen());
        recordInfo.setIsOrthopaedics(saveParam.getIsOrthopaedics());
        recordInfo.setCreationDate(new Date());

        riskHealthRecordInfoService.insert(recordInfo);
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @PostMapping("/update/health/{id}")
    public ResponseEntity<?> updateHealth(@PathVariable("id") Integer id, @RequestBody HealthSaveParam saveParam) {
        String year = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy"));

        Double bmi = saveParam.getWeight()/Math.pow(saveParam.getHeight()/100,2);
        Integer bmiId = 0;
        if(bmi<=18.40) {
            bmiId = 1;
        }else if(bmi>=18.41 && bmi<=23.99) {
            bmiId = 2;
        }else if(bmi>=24 && bmi<=27.99) {
            bmiId = 3;
        }else if(bmi>=28 && bmi<=100.00) {
            bmiId = 4;
        }

        RiskHealthRecord record = riskHealthRecordService.selectByPrimaryKey(id);
        record.setPoliceId(saveParam.getPoliceId());
        record.setYear(year);
        record.setHeight(saveParam.getHeight());
        record.setWeight(saveParam.getWeight());
        record.setBmi(bmi);
        record.setBmiId(bmiId);
        record.setIsHyperlipidemia(0);
        if(saveParam.getTriglyceride() > 1.7 || saveParam.getCholesterol() > 5.72) {
            record.setIsHyperlipidemia(1);
        }

        record.setIsHypertension(0);
        if (saveParam.getReceiveCompression() > 140 || saveParam.getDiastolicPressure() > 90) {
            record.setIsHypertension(1);
        }

        record.setIsHyperglycemia(0);
        if (saveParam.getBloodSugar() > 6.1) {
            record.setIsHyperglycemia(1);
        }

        record.setIsHyperuricemia(0);
        if (saveParam.getSerumUricAcid() > 420) {
            record.setIsHyperuricemia(1);
        }
        record.setIsProstate(saveParam.getIsProstate() == null ? 0 : saveParam.getIsProstate());
        record.setIsMajorDiseases(saveParam.getIsMajorDiseases() == null ? 0 : saveParam.getIsMajorDiseases());
        record.setIsHeart(saveParam.getIsHeart() == null ? 0 : saveParam.getIsHeart());
        record.setIsTumorAntigen(saveParam.getIsTumorAntigen() == null ? 0 : saveParam.getIsTumorAntigen());
        record.setIsOrthopaedics(saveParam.getIsOrthopaedics() == null ? 0 : saveParam.getIsOrthopaedics());
        record.setOrthopaedicsDescribe(saveParam.getOrthopaedicsDesc());
        record.setTumorAntigenDescribe(saveParam.getTumorAntigenDesc());
        record.setHeartDescribe(saveParam.getHeartDesc());
        record.setIsOverweight(0);
        record.setMajorDiseasesDescribe(saveParam.getMajorDiseasesDesc());
        record.setUpdateDate(new Date());
        /*record.setUpdateDate();
        record.setBlood();*/

        riskHealthRecordService.updateByPrimaryKey(record);

        RiskHealthRecordInfo recordInfo = riskHealthRecordInfoService.findByRecordId(record.getId());
        if (recordInfo != null) {
//        recordInfo.setRecordId(record.getId());
            recordInfo.setMajorDiseasesDesc(saveParam.getMajorDiseasesDesc());
            recordInfo.setHeartDesc(saveParam.getHeartDesc());
            recordInfo.setTumorAntigenDesc(saveParam.getTumorAntigenDesc());
            recordInfo.setOrthopaedicsDesc(saveParam.getOrthopaedicsDesc());
            recordInfo.setPoliceId(saveParam.getPoliceId());
            recordInfo.setHeight(saveParam.getHeight());
            recordInfo.setWeight(saveParam.getWeight());
            recordInfo.setHighDensityLipoprotein(saveParam.getHighDensityLipoprotein());
            recordInfo.setLowDensityLipoprotein(saveParam.getLowDensityLipoprotein());
            recordInfo.setTriglyceride(saveParam.getTriglyceride());
            recordInfo.setCholesterol(saveParam.getCholesterol());
            recordInfo.setReceiveCompression(saveParam.getReceiveCompression());
            recordInfo.setDiastolicPressure(saveParam.getDiastolicPressure());
            recordInfo.setBloodSugar(saveParam.getBloodSugar());
            recordInfo.setSerumUricAcid(saveParam.getSerumUricAcid());
            recordInfo.setIsProstate(saveParam.getIsProstate());
            recordInfo.setProstateDesc(saveParam.getProstateDesc());
            recordInfo.setIsMajorDiseases(saveParam.getIsMajorDiseases());
            recordInfo.setIsHeart(saveParam.getIsHeart());
            recordInfo.setIsTumorAntigen(saveParam.getIsTumorAntigen());
            recordInfo.setIsOrthopaedics(saveParam.getIsOrthopaedics());
            recordInfo.setCreationDate(new Date());

            riskHealthRecordInfoService.updateByPrimaryKey(recordInfo);
        }
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    @GetMapping("/health/details")
    public ResponseEntity<?> healthDetails(@RequestParam("id") Integer id) {
        HealthDetailsResult result = new HealthDetailsResult();
        RiskHealthRecord record = riskHealthRecordService.selectByPrimaryKey(id);
        RiskHealthRecordInfo recordInfo = riskHealthRecordInfoService.findByRecordId(id);
        if (recordInfo == null) {
            recordInfo = new RiskHealthRecordInfo();
        }
        User user = userService.findByPoliceId(record.getPoliceId());
        if (user != null) {
            result.setPoliceName(user.getName());
        }
        result.setPoliceId(record.getPoliceId());
        result.setHeight(record.getHeight());
        result.setWeight(record.getWeight());
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
        result.setMajorDiseasesDesc(recordInfo.getMajorDiseasesDesc());
        result.setIsHeart(recordInfo.getIsHeart());
        result.setHeartDesc(recordInfo.getHeartDesc());
        result.setIsTumorAntigen(recordInfo.getIsTumorAntigen());
        result.setTumorAntigenDesc(recordInfo.getTumorAntigenDesc());
        result.setIsOrthopaedics(recordInfo.getIsOrthopaedics());
        result.setOrthopaedicsDesc(recordInfo.getOrthopaedicsDesc());

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    @DeleteMapping("/delete/health")
    public ResponseEntity<?> deleteHealth(@RequestParam("id") Integer id) {
        riskHealthRecordService.deleteByPrimaryKey(id);
        riskHealthRecordInfoService.deleteByRecordId(id);

        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

}
