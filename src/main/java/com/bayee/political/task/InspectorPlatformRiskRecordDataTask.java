package com.bayee.political.task;

import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/8/20
 */
@Component
@EnableScheduling
@RestController
public class InspectorPlatformRiskRecordDataTask {

    @Autowired
    ConductBureauRecordViewsService conductBureauRecordViewsService;

    @Autowired
    ConductBureauRecordTypeViewsService conductBureauRecordTypeViewsService;

    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    @Autowired
    DepartmentService departmentService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    MeasuresService measuresService;

    @Autowired
    MeasuresViewsService measuresViewsService;

    @Autowired
    UserService userService;

    /**
     * 局规记分
     */
    @GetMapping("/inspector/conduct/record")
    public void riskConductBureauRecord() {
        List<ConductBureauRecordViews> recordViewList = conductBureauRecordViewsService.findAll();
//        System.out.println("\nrecordViewList：" + recordViewList.size());

        List<RiskConductBureauRuleRecord> bureauRuleRecordList = new ArrayList<>();
        List<RiskConductVisitRecord> visitRecordList = new ArrayList<>();

        for (ConductBureauRecordViews bureauRecord : recordViewList) {
            Department department = departmentService.findByDeptCode(bureauRecord.getMarkOrgId());
            RiskConductBureauRuleType bureauRuleType = riskConductBureauRuleTypeService.findTypeByCode(bureauRecord.getFlag());
            User user = userService.findByPoliceId(bureauRecord.getPoliceNo());
            if (user == null || user.getId() == null) {
                continue;
            }

            RiskConductBureauRuleType levelTwoType = riskConductBureauRuleTypeService.findLevelTwoObjByCode(bureauRecord.getFlag());
            if (levelTwoType != null && "信访投诉".equals(levelTwoType.getName())) {
                RiskConductVisitRecord visitRecord = new RiskConductVisitRecord();
                visitRecord.setAppealState(0);
                visitRecord.setIsEffective(1);
                visitRecord.setOriginId(5);
                visitRecord.setIsReally(1);
                visitRecord.setDataOriginFlag(1);
                visitRecord.setPoliceId(user.getPoliceId());
                visitRecord.setInputTime(bureauRecord.getMarkTime());
                visitRecord.setContent(bureauRecord.getJfnr());
                visitRecord.setRemarks(bureauRecord.getMarkDesc());
                visitRecord.setCreationDate(bureauRecord.getMarkTime());
                visitRecord.setDeductionScore(bureauRecord.getMarkScore());
                visitRecord.setInspectorKey(bureauRecord.getId());
                visitRecord.setType("12345投诉".equals(bureauRuleType.getName()) ? 31 :
                        "12389投诉".equals(bureauRuleType.getName()) ? 32 : 0);

                visitRecordList.add(visitRecord);
            } else {
                // bureauRecord.getCqcs()
                RiskConductBureauRuleRecord bureauRuleRecord = new RiskConductBureauRuleRecord();
                bureauRuleRecord.setInspectorKey(bureauRecord.getId());
                bureauRuleRecord.setDataOriginFlag(1);
                bureauRuleRecord.setAppealState(0);
                bureauRuleRecord.setIsEffective(1);
                bureauRuleRecord.setScoringLevel(bureauRecord.getMarkType());
                bureauRuleRecord.setScoringDept(department.getId());
                bureauRuleRecord.setMeasures(bureauRecord.getCqcs());
                bureauRuleRecord.setPoliceId(bureauRecord.getPoliceNo());
                bureauRuleRecord.setType(bureauRuleType.getId());
                bureauRuleRecord.setInputTime(bureauRecord.getMarkTime());
                bureauRuleRecord.setContent(bureauRecord.getJfnr());
                bureauRuleRecord.setDeductionScore(bureauRecord.getMarkScore());
                bureauRuleRecord.setRemarks(bureauRecord.getMarkDesc());
                bureauRuleRecord.setCreationDate(bureauRecord.getMarkTime());

                bureauRuleRecordList.add(bureauRuleRecord);
            }
        }

        //局规记分处理
        for (RiskConductBureauRuleRecord bureauRuleRecord : bureauRuleRecordList) {
            Integer id = riskConductBureauRuleRecordService.findIdByInspectorKey(bureauRuleRecord.getInspectorKey());
            if (id != null && id > 0) {
                bureauRuleRecord.setId(id);
                riskConductBureauRuleRecordService.updateByPrimaryKey(bureauRuleRecord);
            } else {
                riskConductBureauRuleRecordService.insert(bureauRuleRecord);
            }
        }

        //信访投诉处理
        for (RiskConductVisitRecord visitRecord : visitRecordList) {
            Integer id = riskConductVisitRecordService.findByInspectorKey(visitRecord.getInspectorKey());
            if (id != null && id > 0) {
                visitRecord.setId(id);
                riskConductVisitRecordService.updateByPrimaryKey(visitRecord);
            } else {
                riskConductVisitRecordService.insert(visitRecord);
            }
        }

    }

    /**
     * 采取措施(问题问责)
     */
    @GetMapping("/inspector/measures/record")
    public void measures() {
        List<MeasuresViews> measuresViewsList = measuresViewsService.findAll();
        System.out.println("\nmeasuresViewsList：" + measuresViewsList.size());

        for (MeasuresViews measuresViews : measuresViewsList) {
            Measures measures = measuresService.findByName(measuresViews.getName());
            if (measures == null || measures.getId() == null) {
                measures = new Measures();
                measures.setName(measuresViews.getName());
                measures.setCreationDate(new Date());
                measuresService.insertMeasures(measures);
            }
        }
    }

    /**
     * 局规记分类型
     */
    @GetMapping("/inspector/conduct/bureau/type")
    public void conductBureauType() {
        List<ConductBureauRecordTypeViews> recordTypeViewsList = conductBureauRecordTypeViewsService.findAll();
        System.out.println("\nrecordTypeViewsList：" + recordTypeViewsList.size());

        for (ConductBureauRecordTypeViews recordTypeViews : recordTypeViewsList) {
            RiskConductBureauRuleType old = riskConductBureauRuleTypeService.findTypeByCode(recordTypeViews.getFlag());
            if (old != null && old.getId() != null) {
                continue;
            }

            RiskConductBureauRuleType bureauRuleType = new RiskConductBureauRuleType();
            bureauRuleType.setLevel(recordTypeViews.getTypeLevel());
            bureauRuleType.setId(Integer.parseInt(recordTypeViews.getId()) * 1000);
            bureauRuleType.setName(recordTypeViews.getName());
            bureauRuleType.setParentId(Integer.parseInt(recordTypeViews.getParentId()) * 1000);
            bureauRuleType.setCode(recordTypeViews.getFlag());
            bureauRuleType.setCreationDate(new Date());

            riskConductBureauRuleTypeService.insert(bureauRuleType);
        }
    }

}
