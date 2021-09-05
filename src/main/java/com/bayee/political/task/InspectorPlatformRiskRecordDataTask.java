package com.bayee.political.task;

import com.bayee.political.domain.*;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

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
    DepartmentService departmentService;

    @Autowired
    RiskConductBureauRuleTypeService riskConductBureauRuleTypeService;

    @Autowired
    MeasuresService measuresService;

    @Autowired
    MeasuresViewsService measuresViewsService;

    /**
     * 局规记分
     */
    public void riskConductBureauRecord() {
        List<ConductBureauRecordViews> recordViewList = conductBureauRecordViewsService.findAll();
        if (recordViewList == null || recordViewList.size() <= 0) {
            return;
        }

        for (ConductBureauRecordViews bureauRecord : recordViewList) {
            Department department = departmentService.findByDeptCode(bureauRecord.getMarkOrgId());
            RiskConductBureauRuleType bureauRuleType = riskConductBureauRuleTypeService.findTypeByCode(bureauRecord.getFlag());

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

            Integer id = riskConductBureauRuleRecordService.findIdByInspectorKey(bureauRecord.getId());
            if (id != null && id > 0) {
                bureauRuleRecord.setId(id);
                riskConductBureauRuleRecordService.updateByPrimaryKey(bureauRuleRecord);
            } else {
                riskConductBureauRuleRecordService.insert(bureauRuleRecord);
            }
        }

    }

    /**
     * 采取措施(问题问责)
     */
    public void measures() {
        List<MeasuresViews> measuresViewsList = measuresViewsService.findAll();

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
    public void conductBureauType() {
        List<ConductBureauRecordTypeViews> recordTypeViewsList = conductBureauRecordTypeViewsService.findAll();

        for (ConductBureauRecordTypeViews recordTypeViews : recordTypeViewsList) {
//            RiskConductBureauRuleType bureauRuleType =
        }
    }

}
