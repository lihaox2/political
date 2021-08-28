package com.bayee.political.task;

import com.bayee.political.domain.ConductBureauRecordViews;
import com.bayee.political.domain.RiskConductBureauRuleRecord;
import com.bayee.political.service.ConductBureauRecordTypeViewsService;
import com.bayee.political.service.ConductBureauRecordViewsService;
import com.bayee.political.service.RiskConductBureauRuleRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

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

    public void riskConductBureauRecord() {
        List<ConductBureauRecordViews> recordViewList = conductBureauRecordViewsService.findAll();
        if (recordViewList == null || recordViewList.size() <= 0) {
            return;
        }

        for (ConductBureauRecordViews bureauRecord : recordViewList) {
            /*RiskConductBureauRuleRecord bureauRuleRecord = new RiskConductBureauRuleRecord();
            bureauRuleRecord.setInspectorKey(bureauRecord.getId());
            bureauRuleRecord.setDataOriginFlag(1);
            bureauRuleRecord.setAppealState(0);
            bureauRuleRecord.setIsEffective(1);
            bureauRuleRecord.setScoringLevel();
            bureauRuleRecord.setScoringDept();
            bureauRuleRecord.setMeasures(bureauRecord.getCqcs());
            bureauRuleRecord.setPoliceId(bureauRecord.getPoliceNo());
            bureauRuleRecord.setType();
            bureauRuleRecord.setInputTime();
            bureauRuleRecord.setContent(bureauRecord.getJfnr());
            bureauRuleRecord.setDeductionScore(bureauRecord.getMarkScore());
            bureauRuleRecord.setRemarks(bureauRecord.getMarkDesc());
            bureauRuleRecord.setCreationDate();*/

        }

    }

}
