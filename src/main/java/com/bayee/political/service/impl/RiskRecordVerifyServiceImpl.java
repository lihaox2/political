package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.mapper.RiskRecordVerifyMapper;
import com.bayee.political.pojo.dto.RiskRecordVerifyDetailsDO;
import com.bayee.political.pojo.dto.RiskRecordVerifyPageResultDO;
import com.bayee.political.pojo.json.RiskRecordVerifyPageQueryParam;
import com.bayee.political.service.*;
import com.bayee.political.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/**
 * @author xxl
 * @date 2021/7/20
 */
@Service
public class RiskRecordVerifyServiceImpl implements RiskRecordVerifyService {

    @Autowired
    RiskRecordVerifyMapper riskRecordVerifyMapper;

    @Autowired
    RiskConductVisitRecordService riskConductVisitRecordService;

    @Autowired
    RiskConductBureauRuleRecordService riskConductBureauRuleRecordService;

    @Autowired
    RiskCaseLawEnforcementRecordService riskCaseLawEnforcementRecordService;

    @Autowired
    RiskDutyDealPoliceRecordService riskDutyDealPoliceRecordService;

    @Autowired
    TotalRiskDetailsService totalRiskDetailsService;

    @Override
    public List<RiskRecordVerifyPageResultDO> riskRecordVerifyPage(RiskRecordVerifyPageQueryParam queryParam) {
        if (queryParam == null) {
            queryParam = new RiskRecordVerifyPageQueryParam();
            queryParam.setPageIndex(1);
            queryParam.setPageSize(5);
        }
        if (queryParam.getPageIndex() < 1) {
            queryParam.setPageIndex(1);
        }
        queryParam.setPageIndex((queryParam.getPageIndex() - 1) * queryParam.getPageSize());

        return riskRecordVerifyMapper.riskRecordVerifyPage(queryParam);
    }

    @Override
    public RiskRecordVerify findById(Integer id) {
        return riskRecordVerifyMapper.selectByPrimaryKey(id);
    }

    @Override
    public RiskRecordVerifyDetailsDO findVerifyDOById(Integer id) {
        return riskRecordVerifyMapper.findVerifyDOById(id);
    }

    @Override
    public void addAppeal(RiskRecordVerify verify) {
        riskRecordVerifyMapper.insert(verify);
    }

    @Override
    public void cancelAppeal(Integer typeId, Integer moduleId) {
        riskRecordVerifyMapper.cancelAppeal(typeId, moduleId);
    }

    @Override
    public RiskRecordVerifyDetailsDO appealDetails(Integer typeId, Integer moduleId) {
        return riskRecordVerifyMapper.appealDetails(typeId, moduleId);
    }

    @Override
    public void checkRecord(RiskRecordVerify verify) {
        //不同意申诉
        if (verify.getIsAgree() == 2) {
            verify.setState(3);
            riskRecordVerifyMapper.updateByPrimaryKey(verify);
            return;
        }

        //同意申诉
        verify.setState(2);
        riskRecordVerifyMapper.updateByPrimaryKey(verify);

        String appealPoliceId = verify.getAppealPoliceId();
        String checkDeductionPoliceId = verify.getCheckDeductionPoliceId();
        Double appealScore = verify.getAppealScore();
        Double checkDeductionScore = verify.getCheckDeductionScore();

        Integer appealType = verify.getTypeId();
        if (1011 == appealType) {
            conductBureauRuleDetails(verify.getModuleId(), checkDeductionPoliceId, checkDeductionScore);
        } else if (1012 == appealType) {
            conductVisitDetails(verify.getModuleId(), checkDeductionPoliceId, checkDeductionScore);
        } else if (1013 == appealType) {
            riskDutyDealPoliceDetails(verify.getModuleId(), checkDeductionPoliceId, checkDeductionScore);
        } else if (1014 == appealType) {
            riskCaseLawEnforcementDetails(verify.getModuleId(), checkDeductionPoliceId, checkDeductionScore);
        }
    }

    /**
     * 信访投诉处理
     * @param id
     * @param checkDeductionPoliceId
     * @param checkDeductionScore
     */
    public void conductVisitDetails(Integer id, String checkDeductionPoliceId, Double checkDeductionScore) {
        RiskConductVisitRecord visitRecord = riskConductVisitRecordService.selectByPrimaryKey(id);
        if (visitRecord.getPoliceId().equals(checkDeductionPoliceId)) {
            visitRecord.setDeductionScore(checkDeductionScore);
            visitRecord.setUpdateDate(new Date());

            riskConductVisitRecordService.updateByPrimaryKey(visitRecord);
            totalRiskDetailsService.conductRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(visitRecord.getCreationDate(), "yyyy-MM-dd")));
        } else {
            RiskConductVisitRecord newVisitRecord = new RiskConductVisitRecord();
            newVisitRecord.setIsEffective(1);
            newVisitRecord.setOriginId(visitRecord.getOriginId());
            newVisitRecord.setIsReally(visitRecord.getIsReally());
            newVisitRecord.setPoliceId(checkDeductionPoliceId);
            newVisitRecord.setType(visitRecord.getType());
            newVisitRecord.setInputTime(visitRecord.getInputTime());
            newVisitRecord.setContent(visitRecord.getContent());
            newVisitRecord.setRemarks(visitRecord.getRemarks());
            newVisitRecord.setCreationDate(visitRecord.getCreationDate());
            newVisitRecord.setDeductionScore(checkDeductionScore);

            riskConductVisitRecordService.insert(newVisitRecord);
            totalRiskDetailsService.conductRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(newVisitRecord.getCreationDate(), "yyyy-MM-dd")));

            visitRecord.setIsEffective(2);
            visitRecord.setDeductionScore(checkDeductionScore);
            visitRecord.setUpdateDate(new Date());
            riskConductVisitRecordService.updateByPrimaryKey(visitRecord);
            totalRiskDetailsService.conductRiskDetails(visitRecord.getPoliceId(),
                    LocalDate.parse(DateUtils.formatDate(visitRecord.getCreationDate(), "yyyy-MM-dd")));
        }
    }

    /**
     * 局规记分处理
     * @param id
     * @param checkDeductionPoliceId
     * @param checkDeductionScore
     */
    public void conductBureauRuleDetails(Integer id, String checkDeductionPoliceId, Double checkDeductionScore) {
        RiskConductBureauRuleRecord bureauRuleRecord = riskConductBureauRuleRecordService.selectByPrimaryKey(id);
        if (checkDeductionPoliceId.equals(bureauRuleRecord.getPoliceId())) {
            bureauRuleRecord.setDeductionScore(checkDeductionScore);
            bureauRuleRecord.setUpdateDate(new Date());

            riskConductBureauRuleRecordService.updateByPrimaryKey(bureauRuleRecord);
            totalRiskDetailsService.conductRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(bureauRuleRecord.getCreationDate(), "yyyy-MM-dd")));
        } else {
            RiskConductBureauRuleRecord newBureauRuleRecord = new RiskConductBureauRuleRecord();
            newBureauRuleRecord.setIsEffective(1);
            newBureauRuleRecord.setImgArr(bureauRuleRecord.getImgArr());
            newBureauRuleRecord.setScoringLevel(bureauRuleRecord.getScoringLevel());
            newBureauRuleRecord.setScoringDept(bureauRuleRecord.getScoringDept());
            newBureauRuleRecord.setMeasures(bureauRuleRecord.getMeasures());
            newBureauRuleRecord.setParentTypeId(bureauRuleRecord.getParentTypeId());
            newBureauRuleRecord.setPoliceId(checkDeductionPoliceId);
            newBureauRuleRecord.setType(bureauRuleRecord.getType());
            newBureauRuleRecord.setInputTime(bureauRuleRecord.getInputTime());
            newBureauRuleRecord.setContent(bureauRuleRecord.getContent());
            newBureauRuleRecord.setDeductionScore(bureauRuleRecord.getDeductionScore());
            newBureauRuleRecord.setRemarks(bureauRuleRecord.getRemarks());
            newBureauRuleRecord.setCreationDate(bureauRuleRecord.getCreationDate());
            newBureauRuleRecord.setUpdateDate(new Date());

            riskConductBureauRuleRecordService.insert(newBureauRuleRecord);
            totalRiskDetailsService.conductRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(newBureauRuleRecord.getCreationDate(), "yyyy-MM-dd")));

            bureauRuleRecord.setIsEffective(2);
            bureauRuleRecord.setDeductionScore(checkDeductionScore);
            bureauRuleRecord.setUpdateDate(new Date());
            riskConductBureauRuleRecordService.updateByPrimaryKey(bureauRuleRecord);
            totalRiskDetailsService.conductRiskDetails(bureauRuleRecord.getPoliceId(),
                    LocalDate.parse(DateUtils.formatDate(bureauRuleRecord.getCreationDate(), "yyyy-MM-dd")));
        }
    }

    /**
     * 执法管理处理
     * @param id
     * @param checkDeductionPoliceId
     * @param checkDeductionScore
     */
    public void riskCaseLawEnforcementDetails(Integer id, String checkDeductionPoliceId, Double checkDeductionScore) {
        RiskCaseLawEnforcementRecord lawEnforcementRecord = riskCaseLawEnforcementRecordService.selectByPrimaryKey(id);
        if (checkDeductionPoliceId.equals(lawEnforcementRecord.getPoliceId())) {
            lawEnforcementRecord.setDeductionScore(checkDeductionScore);
            lawEnforcementRecord.setUpdateDate(new Date());

            riskCaseLawEnforcementRecordService.updateByPrimaryKeySelective(lawEnforcementRecord);
            totalRiskDetailsService.caseRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(lawEnforcementRecord.getCreationDate(), "yyyy-MM-dd")));
        } else {
            RiskCaseLawEnforcementRecord newLawEnforcementRecord = new RiskCaseLawEnforcementRecord();
            newLawEnforcementRecord.setIsEffective(1);
            newLawEnforcementRecord.setImgArr(lawEnforcementRecord.getImgArr());
            newLawEnforcementRecord.setDeptName(lawEnforcementRecord.getDeptName());
            newLawEnforcementRecord.setCaseCode(lawEnforcementRecord.getCaseCode());
            newLawEnforcementRecord.setPoliceId(checkDeductionPoliceId);
            newLawEnforcementRecord.setType(lawEnforcementRecord.getType());
            newLawEnforcementRecord.setContent(lawEnforcementRecord.getContent());
            newLawEnforcementRecord.setInputTime(lawEnforcementRecord.getInputTime());
            newLawEnforcementRecord.setDeductionScore(checkDeductionScore);
            newLawEnforcementRecord.setCreationDate(lawEnforcementRecord.getCreationDate());
            newLawEnforcementRecord.setUpdateDate(new Date());

            riskCaseLawEnforcementRecordService.insert(newLawEnforcementRecord);
            totalRiskDetailsService.caseRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(newLawEnforcementRecord.getCreationDate(), "yyyy-MM-dd")));

            lawEnforcementRecord.setIsEffective(2);
            lawEnforcementRecord.setDeductionScore(checkDeductionScore);
            lawEnforcementRecord.setUpdateDate(new Date());
            riskCaseLawEnforcementRecordService.updateByPrimaryKeySelective(lawEnforcementRecord);
            totalRiskDetailsService.caseRiskDetails(lawEnforcementRecord.getPoliceId(),
                    LocalDate.parse(DateUtils.formatDate(lawEnforcementRecord.getCreationDate(), "yyyy-MM-dd")));
        }
    }

    /**
     * 接警执勤处理
     * @param id
     * @param checkDeductionPoliceId
     * @param checkDeductionScore
     */
    public void riskDutyDealPoliceDetails(Integer id, String checkDeductionPoliceId, Double checkDeductionScore) {
        RiskDutyDealPoliceRecord dutyDealPoliceRecord = riskDutyDealPoliceRecordService.selectByPrimaryKey(id);
        if (checkDeductionPoliceId.equals(dutyDealPoliceRecord.getPoliceId())) {
            dutyDealPoliceRecord.setDeductionScore(checkDeductionScore);
            dutyDealPoliceRecord.setUpdateDate(new Date());

            riskDutyDealPoliceRecordService.updateByPrimaryKeySelective(dutyDealPoliceRecord);
            totalRiskDetailsService.dutyRiskDetails(checkDeductionPoliceId,
                    LocalDate.parse(DateUtils.formatDate(dutyDealPoliceRecord.getCreationDate(), "yyyy-MM-dd")));
        } else {
            RiskDutyDealPoliceRecord newDutyDealPoliceRecord = new RiskDutyDealPoliceRecord();
            newDutyDealPoliceRecord.setIsEffective(1);
            newDutyDealPoliceRecord.setImgArr(dutyDealPoliceRecord.getImgArr());
            newDutyDealPoliceRecord.setInformationId(dutyDealPoliceRecord.getInformationId());
            newDutyDealPoliceRecord.setErrorId(dutyDealPoliceRecord.getErrorId());
            newDutyDealPoliceRecord.setPoliceListCode(dutyDealPoliceRecord.getPoliceListCode());
            newDutyDealPoliceRecord.setJurisdiction(dutyDealPoliceRecord.getJurisdiction());
            newDutyDealPoliceRecord.setPoliceListInfo(dutyDealPoliceRecord.getPoliceListInfo());
            newDutyDealPoliceRecord.setIsVerified(dutyDealPoliceRecord.getIsVerified());
            newDutyDealPoliceRecord.setPoliceId(checkDeductionPoliceId);
            newDutyDealPoliceRecord.setType(dutyDealPoliceRecord.getType());
            newDutyDealPoliceRecord.setContent(dutyDealPoliceRecord.getContent());
            newDutyDealPoliceRecord.setInputTime(dutyDealPoliceRecord.getInputTime());
            newDutyDealPoliceRecord.setCreationDate(dutyDealPoliceRecord.getCreationDate());
            newDutyDealPoliceRecord.setUpdateDate(new Date());
            newDutyDealPoliceRecord.setDeductionScore(checkDeductionScore);

            riskDutyDealPoliceRecordService.insert(newDutyDealPoliceRecord);
            totalRiskDetailsService.dutyRiskDetails(newDutyDealPoliceRecord.getPoliceId(),
                    LocalDate.parse(DateUtils.formatDate(newDutyDealPoliceRecord.getCreationDate(), "yyyy-MM-dd")));

            dutyDealPoliceRecord.setIsEffective(2);
            dutyDealPoliceRecord.setDeductionScore(checkDeductionScore);
            dutyDealPoliceRecord.setUpdateDate(new Date());
            totalRiskDetailsService.dutyRiskDetails(dutyDealPoliceRecord.getPoliceId(),
                    LocalDate.parse(DateUtils.formatDate(dutyDealPoliceRecord.getCreationDate(), "yyyy-MM-dd")));
        }
    }

}
