package com.bayee.political.service.impl;

import java.util.List;

import com.bayee.political.json.PhysicalTrainPageQueryParam;
import com.bayee.political.pojo.dto.PhysicalTrainPageResultDO;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.LeaveChart;
import com.bayee.political.domain.ReportDataFillTime;
import com.bayee.political.domain.RiskTrainFirearmRecord;
import com.bayee.political.domain.RiskTrainPhysicalAchievementDetails;
import com.bayee.political.domain.RiskTrainPhysicalRecord;
import com.bayee.political.domain.TrainAchievementTemplate;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainChartStatistics;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainFirearm;
import com.bayee.political.domain.TrainFirearmAchievement;
import com.bayee.political.domain.TrainGroup;
import com.bayee.political.domain.TrainGroupPolice;
import com.bayee.political.domain.TrainLeader;
import com.bayee.political.domain.TrainLeaderAchievement;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPersonalAchievement;
import com.bayee.political.domain.TrainPersonalResult;
import com.bayee.political.domain.TrainPhysical;
import com.bayee.political.domain.TrainPhysicalAchievement;
import com.bayee.political.domain.TrainPhysicalAchievementDetails;
import com.bayee.political.domain.TrainPhysicalProjectRecord;
import com.bayee.political.domain.TrainProject;
import com.bayee.political.domain.TrainProjectRule;
import com.bayee.political.domain.TrainProjectURule;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;
import com.bayee.political.domain.TrainRecordScore;
import com.bayee.political.domain.TrainScorer;
import com.bayee.political.domain.TrainStatisticsAnalysis;
import com.bayee.political.domain.TrainTimeName;
import com.bayee.political.domain.TrainUnit;
import com.bayee.political.mapper.TrainActivityStyleMapper;
import com.bayee.political.mapper.TrainConstitutionMapper;
import com.bayee.political.mapper.TrainFirearmAchievementMapper;
import com.bayee.political.mapper.TrainFirearmMapper;
import com.bayee.political.mapper.TrainGroupMapper;
import com.bayee.political.mapper.TrainLeaderMapper;
import com.bayee.political.mapper.TrainMatchAchievementMapper;
import com.bayee.political.mapper.TrainMatchMapper;
import com.bayee.political.mapper.TrainMatchTypeMapper;
import com.bayee.political.mapper.TrainMedalManageMapper;
import com.bayee.political.mapper.TrainPacesetterMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementMapper;
import com.bayee.political.mapper.TrainPhysicalMapper;
import com.bayee.political.mapper.TrainPhysicalProjectRecordMapper;
import com.bayee.political.mapper.TrainProjectMapper;
import com.bayee.political.mapper.TrainProjectRuleMapper;
import com.bayee.political.mapper.TrainProjectURuleMapper;
import com.bayee.political.mapper.TrainScorerMapper;
import com.bayee.political.mapper.TrainUnitMapper;
import com.bayee.political.service.TrainService;

/**
 * @author shentuqiwei
 * @version 2020???9???28??? ??????9:47:26
 */
@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TrainPhysicalMapper trainPhysicalMapper;// ????????????

	@Autowired
	TrainPhysicalAchievementMapper trainPhysicalAchievementMapper;// ??????????????????????????????

	@Autowired
	TrainGroupMapper trainGroupMapper;// ??????????????????

	@Autowired
	TrainFirearmAchievementMapper trainFirearmAchievementMapper;// ??????????????????

	@Autowired
	TrainFirearmMapper trainFirearmMapper;// ??????

	@Autowired
	TrainMatchMapper trainMatchMapper; // ??????

	@Autowired
	TrainProjectMapper trainProjectMapper;// ??????????????????

	@Autowired
	TrainScorerMapper trainScorerMapper;// ???????????????

	@Autowired
	TrainProjectRuleMapper trainProjectRuleMapper;// ??????????????????

	@Autowired
	TrainMedalManageMapper trainMedalManageMapper;// ????????????

	@Autowired
	TrainActivityStyleMapper trainActivityStyleMapper;// ????????????

	@Autowired
	TrainPacesetterMapper trainPacesetterMapper;// ????????????

	@Autowired
	TrainConstitutionMapper trainConstitutionMapper;// ????????????

	@Autowired
	TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper;// ??????????????????

	@Autowired
	TrainMatchTypeMapper trainMatchTypeMapper;// ????????????

	@Autowired
	TrainUnitMapper trainUnitMapper;// ??????

	@Autowired
	TrainPhysicalProjectRecordMapper trainPhysicalProjectRecordMapper;// ??????????????????

	@Autowired
	TrainProjectURuleMapper trainProjectURuleMapper;// U??????????????????

	@Autowired
	TrainLeaderMapper trainLeaderMapper;// U??????????????????

	@Autowired
	TrainMatchAchievementMapper trainMatchAchievementMapper;// ????????????

	// ??????????????????
	@Override
	public int trainPhysicalDelete(Integer id) {
		return trainPhysicalMapper.trainPhysicalDelete(id);
	}

	// ??????????????????
	@Override
	public int trainPhysicalCreat(TrainPhysical record) {
		return trainPhysicalMapper.trainPhysicalCreat(record);
	}

	// ??????????????????
	@Override
	public int trainPhysicalUpdate(TrainPhysical record) {
		return trainPhysicalMapper.trainPhysicalUpdate(record);
	}

	// ????????????????????????
	@Override
	public TrainPhysical trainPhysicalItem(Integer id) {
		return trainPhysicalMapper.trainPhysicalItem(id);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainPhysicalList(String policeId, Integer type) {
		return trainPhysicalMapper.trainPhysicalList(policeId, type);
	}

	// ??????????????????????????????
	@Override
	public int trainPhysicalCount(String policeId, Integer type) {
		return trainPhysicalMapper.trainPhysicalCount(policeId, type);
	}

	// ??????????????????????????????????????????
	@Override
	public int singleTrainPhysicalAchievementCount(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.singleTrainPhysicalAchievementCount(trainPhysicalId);
	}

	// ??????????????????
	@Override
	public List<TrainGroup> trainGroupList(Integer id) {
		return trainGroupMapper.trainGroupList(id);
	}

	// ??????????????????
	@Override
	public TrainGroup trainGroupItem(Integer id) {
		return trainGroupMapper.trainGroupItem(id);
	}

	// ??????????????????????????????
	@Override
	public int trainPhysicalAchievementCreat(TrainPhysicalAchievement record) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementCreat(record);
	}

	// ??????????????????????????????
	@Override
	public int trainPhysicalAchievementUpdate(TrainPhysicalAchievement record) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementUpdate(record);
	}

	// ????????????????????????????????????
	@Override
	public TrainPhysicalAchievement trainPhysicalAchievementItem(Integer id, Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementItem(id, trainPhysicalId, policeId);
	}

	// ??????????????????
	@Override
	public List<TrainGroupPolice> trainGroupPoliceList(String policeId) {
		return trainGroupMapper.trainGroupPoliceList(policeId);
	}

	// ??????????????????(???????????????????????????)
	@Override
	public TrainPhysical trainPhFirearmItem(Integer id) {
		return trainPhysicalMapper.trainPhFirearmItem(id);
	}

	@Override
	public String getTrainGroupByIds(String ids) {
		return trainGroupMapper.getTrainGroupByIds(ids);
	}

	// ??????????????????????????????
	@Override
	public int singleTrainFirearmAchievementCount(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.singleTrainFirearmAchievementCount(trainFirearmId);
	}

	// ????????????????????????
	@Override
	public TrainFirearmAchievement trainFirearmAchievementItem(Integer id, Integer trainFirearmId, String policeId) {
		return trainFirearmAchievementMapper.trainFirearmAchievementItem(id, trainFirearmId, policeId);
	}

	// ??????????????????
	@Override
	public TrainFirearm trainFirearmItem(Integer id) {
		return trainFirearmMapper.trainFirearmItem(id);
	}

	// ??????????????????
	@Override
	public int trainFirearmAchievementCreat(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementCreat(item);
	}

	// ??????????????????
	@Override
	public int trainFirearmAchievementUpdate(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementUpdate(item);
	}

	// ??????????????????
	@Override
	public List<TrainPhysical> trainRecentList(String policeId) {
		return trainPhysicalMapper.trainRecentList(policeId);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainMyList(String policeId, Integer type, Integer status, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainMyList(policeId, type, status, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public int trainMyCount(String policeId, Integer type, Integer status) {
		return trainPhysicalMapper.trainMyCount(policeId, type, status);
	}

	@Override
	public List<TrainFirearm> getTrainFirearmList(Integer departmentId, Integer status, String reprotTime,
			String signTime, String keyWords, Integer pageSize) {
		return trainFirearmMapper.getTrainFirearmList(departmentId, status, reprotTime, signTime, keyWords, pageSize);
	}

	@Override
	public int getTrainFirearmCount(Integer departmentId, Integer status, String reprotTime, String signTime,
			String keyWords) {
		return trainFirearmMapper.getTrainFirearmCount(departmentId, status, reprotTime, signTime, keyWords);
	}

	@Override
	public List<TrainPhysical> getTrainPhysicalList(Integer departmentId, Integer status, String reprotTime,
			String signTime, String keyWords, Integer pageSize) {
		return trainPhysicalMapper.getTrainPhysicalList(departmentId, status, reprotTime, signTime, keyWords, pageSize);
	}

	@Override
	public int getTrainPhysicalCount(Integer departmentId, Integer status, String reprotTime, String signTime,
			String keyWords) {
		return trainPhysicalMapper.getTrainPhysicalCount(departmentId, status, reprotTime, signTime, keyWords);
	}

	@Override
	public List<TrainMatch> getTrainMatchList(Integer departmentId, Integer type, Integer status,
			String registrationStartDate, String registrationEndDate, String trainStartDate, String trainEndDate,
			String keyWords, Integer pageSize) {
		return trainMatchMapper.getTrainMatchList(departmentId, type, status, registrationStartDate,
				registrationEndDate, trainStartDate, trainEndDate, keyWords, pageSize);
	}

	@Override
	public int getTrainMatchCount(Integer departmentId, Integer type, Integer status, String registrationStartDate,
			String registrationEndDate, String trainStartDate, String trainEndDate, String keyWords) {
		return trainMatchMapper.getTrainMatchCount(departmentId, type, status, registrationStartDate,
				registrationEndDate, trainStartDate, trainEndDate, keyWords);
	}

	@Override
	public List<TrainGroup> getTrainGroupList(Integer sex, Integer minAge, Integer maxAge, String keyWords,
			Integer pageSize) {
		return trainGroupMapper.getTrainGroupList(sex, minAge, maxAge, keyWords, pageSize);
	}

	@Override
	public int getTrainGroupCount(Integer sex, Integer minAge, Integer maxAge, String keyWords) {
		return trainGroupMapper.getTrainGroupCount(sex, minAge, maxAge, keyWords);
	}

	@Override
	public List<TrainProject> getTrainProjectList(String keyWords, Integer pageSize) {
		return trainProjectMapper.getTrainProjectList(keyWords, pageSize);
	}

	@Override
	public int getTrainProjectCount(String keyWords) {
		return trainProjectMapper.getTrainProjectCount(keyWords);
	}

	@Override
	public List<TrainScorer> getTrainScorerList(String keyWords, Integer pageSize) {
		return trainScorerMapper.getTrainScorerList(keyWords, pageSize);
	}

	@Override
	public int getTrainScorerCount(String keyWords) {
		return trainScorerMapper.getTrainScorerCount(keyWords);
	}

	@Override
	public List<TrainProjectRule> getTrainProjectRuleList(String keyWords, Integer pageSize) {
		return trainProjectRuleMapper.getTrainProjectRuleList(keyWords, pageSize);
	}

	@Override
	public int getTrainProjectRuleCount(String keyWords) {
		return trainProjectRuleMapper.getTrainProjectRuleCount(keyWords);
	}

	@Override
	public List<TrainMedalManage> getTrainMedalList(Integer type, String keyWords, Integer sort, Integer pageSize) {
		return trainMedalManageMapper.getTrainMedalList(type, keyWords, sort, pageSize);
	}

	@Override
	public int getTrainMedalCount(Integer type, String keyWords) {
		return trainMedalManageMapper.getTrainMedalCount(type, keyWords);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysicalAchievementDetails> trainMyEntryRecordList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainMyEntryRecordList(trainPhysicalId, trainPhysicalAchievementId,
				policeId);
	}

	// ??????????????????
	@Override
	public int trainPhysicalAchievementDetailsCreat(TrainPhysicalAchievementDetails record) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsCreat(record);
	}

	// ??????????????????
	@Override
	public int trainPhysicalAchievementDetailsUpdate(TrainPhysicalAchievementDetails record) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsUpdate(record);
	}

	// ????????????????????????
	@Override
	public List<CalculationChart> trainProjectRankList(Integer trainPhysicalId, Integer trainPhysicalAchievementId,
			String policeId, Integer projectId) {
		return trainProjectMapper.trainProjectRankList(trainPhysicalId, trainPhysicalAchievementId, policeId,
				projectId);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRank> trainPersonalRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalRankList(projectId, trainPhysicalId, policeId,
				departmentId, sort);
	}

	// ??????????????????????????????????????????????????????
	@Override
	public TrainRank trainPersonalRankItem(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalRankItem(projectId, trainPhysicalId, policeId,
				departmentId, sort);
	}

	@Override
	public List<TrainActivityStyle> getTrainActivityStyleList(Integer status, Integer isRecommend, String keyWords,
			Integer sort, Integer pageSize) {
		return trainActivityStyleMapper.getTrainActivityStyleList(status, isRecommend, keyWords, sort, pageSize);
	}

	@Override
	public int getTrainActivityStyleCount(Integer status, Integer isRecommend, String keyWords) {
		return trainActivityStyleMapper.getTrainActivityStyleCount(status, isRecommend, keyWords);
	}

	@Override
	public List<TrainPacesetter> getTrainPacesetterList(Integer status, Integer isRecommend, String keyWords,
			Integer sort, Integer pageSize) {
		return trainPacesetterMapper.getTrainPacesetterList(status, isRecommend, keyWords, sort, pageSize);
	}

	@Override
	public int getTrainPacesetterCount(Integer status, Integer isRecommend, String keyWords) {
		return trainPacesetterMapper.getTrainPacesetterCount(status, isRecommend, keyWords);
	}

	@Override
	public List<TrainConstitution> getTrainConstitutionList(Integer status, Integer isRecommend, String keyWords,
			Integer sort, Integer pageSize) {
		return trainConstitutionMapper.getTrainConstitutionList(status, isRecommend, keyWords, sort, pageSize);
	}

	@Override
	public int getTrainConstitutionCount(Integer status, Integer isRecommend, String keyWords) {
		return trainConstitutionMapper.getTrainConstitutionCount(status, isRecommend, keyWords);
	}

	@Override
	public List<TrainGroup> getTrainGroupNameId() {
		return trainGroupMapper.getTrainGroupNameId();
	}

	@Override
	public List<TrainUnit> getTrainUnitList() {
		return trainUnitMapper.getTrainUnitList();
	}

	@Override
	public List<TrainProject> getTrainProjectNameIdIsU() {
		return trainProjectMapper.getTrainProjectNameIdIsU();
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> trainProjectFirearmRankList(Integer trainFirearmId, String policeId) {
		return trainProjectMapper.trainProjectFirearmRankList(trainFirearmId, policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRank> trainPersonalFirearmRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmRankList(projectId, trainFirearmId, policeId,
				departmentId, sort);
	}

	// ??????????????????????????????????????????????????????
	@Override
	public TrainRank trainPersonalFirearmRankItem(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmRankItem(projectId, trainFirearmId, policeId,
				departmentId, sort);
	}

	// ??????????????????????????????????????????????????????
	@Override
	public List<TrainProject> trainPoliceBelongToList(Integer trainPhysicalId, Integer groupId) {
		return trainProjectMapper.trainPoliceBelongToList(trainPhysicalId, groupId);
	}

	@Override
	public List<TrainProjectRule> getGroAndPro() {
		return trainProjectRuleMapper.getGroAndPro();
	}

	@Override
	public List<TrainProjectRule> getTrainFirearmType() {
		return trainProjectRuleMapper.getTrainFirearmType();
	}

	// ????????????????????????????????????(???????????????)
	@Override
	public List<TrainRank> trainPersonalMoreRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalMoreRankList(projectId, trainPhysicalId, policeId,
				departmentId, sort);
	}

	// ????????????????????????????????????(???????????????)
	@Override
	public List<TrainRank> trainPersonalFirearmMoreRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmMoreRankList(projectId, trainFirearmId,
				policeId, departmentId, sort);
	}

	// ???????????????????????????
	@Override
	public int trainPhysicalNoScoreNum(String policeId) {
		return trainScorerMapper.trainPhysicalNoScoreNum(policeId);
	}

	// ???????????????????????????
	@Override
	public List<TrainRecordScore> trainPhysicalNoScoreList(String policeId) {
		return trainPhysicalAchievementMapper.trainPhysicalNoScoreList(policeId);
	}

	@Override
	public int addTrainPhysicalProjectRecord(TrainPhysicalProjectRecord record) {
		return trainPhysicalProjectRecordMapper.addTrainPhysicalProjectRecord(record);
	}

	@Override
	public int startTrainPhysical(Integer id) {
		return trainPhysicalMapper.startTrainPhysical(id);
	}

	@Override
	public int endTrainPhysical(Integer id) {
		return trainPhysicalMapper.endTrainPhysical(id);
	}

	@Override
	public int restartTrainPhysical(Integer id, String endTime) {
		return trainPhysicalMapper.restartTrainPhysical(id, endTime);
	}

	@Override
	public TrainPhysical getTrainPhysicalDetails(Integer id) {
		return trainPhysicalMapper.getTrainPhysicalDetails(id);
	}

	// ?????????????????????????????????????????????
	@Override
	public List<TrainProject> trainRecordProjectPhysicalList(Integer trainPhysicalId) {
		return trainProjectMapper.trainRecordProjectPhysicalList(trainPhysicalId);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainProject> trainRecordProjectFirearmList(Integer trainFirearmId) {
		return trainProjectMapper.trainRecordProjectFirearmList(trainFirearmId);
	}

	// ??????????????????
	@Override
	public TrainRecordPolice trainRecordPoliceItem(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordPoliceItem(projectId, trainPhysicalId);
	}

	@Override
	public List<TrainPhysicalProjectRecord> trainPhysicalProjectRecordByGroupIds(String ids, Integer id) {
		return trainPhysicalProjectRecordMapper.trainPhysicalProjectRecordByGroupIds(ids, id);
	}

	@Override
	public String getTrainProjectNamesByIds(String ids) {
		return trainProjectMapper.getTrainProjectNamesByIds(ids);
	}

	@Override
	public int deleteByTrainPhysicalId(Integer trainPhysicalId) {
		return trainPhysicalProjectRecordMapper.deleteByTrainPhysicalId(trainPhysicalId);
	}

	@Override
	public int addTrainGroup(TrainGroup trainGroup) {
		return trainGroupMapper.addTrainGroup(trainGroup);
	}

	@Override
	public int deleteGroup(Integer id) {
		return trainGroupMapper.deleteGroup(id);
	}

	@Override
	public int groupQuote(Integer id) {
		return trainGroupMapper.groupQuote(id);
	}

	// ????????????????????????
	@Override
	public List<TrainRank> trainRecordPoliceScoreList(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordPoliceScoreList(projectId, trainPhysicalId);
	}

	// ????????????????????????
	@Override
	public TrainRecordPolice trainRecordFirearmPoliceItem(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordFirearmPoliceItem(projectId, trainPhysicalId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRank> trainRecordFirearmPoliceScoreList(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordFirearmPoliceScoreList(projectId, trainPhysicalId);
	}

	// ????????????id/????????????????????????
	@Override
	public TrainProjectRule trainProjectPoliceRuleItem(Integer projectId, Integer groupId) {
		return trainProjectRuleMapper.trainProjectPoliceRuleItem(projectId, groupId);
	}

	// ??????????????????id??????id??????
	@Override
	public int trainFirearmAchievementPoliceUpdate(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementPoliceUpdate(item);
	}

	@Override
	public int updateTrainGroup(TrainGroup trainGroup) {
		return trainGroupMapper.updateTrainGroup(trainGroup);
	}

	// ??????????????????id??????U??????????????????
	@Override
	public List<TrainProjectURule> TrainProjectURuleList(Integer ruleId) {
		return trainProjectURuleMapper.TrainProjectURuleList(ruleId);
	}

	// ??????????????????id??????id????????????????????????
	@Override
	public void updateFirearmAchievementBatch(List<TrainFirearmAchievement> updateList) {
		trainFirearmAchievementMapper.updateFirearmAchievementBatch(updateList);
	}

	@Override
	public int addTrainProject(TrainProject trainProject) {
		return trainProjectMapper.addTrainProject(trainProject);
	}

	@Override
	public int deleteTrainProject(Integer id) {
		return trainProjectMapper.deleteTrainProject(id);
	}

	@Override
	public int updateTrainProject(TrainProject trainProject) {
		return trainProjectMapper.updateTrainProject(trainProject);
	}

	@Override
	public TrainProject getTrainProjectById(Integer id) {
		return trainProjectMapper.getTrainProjectById(id);
	}

	@Override
	public int addTrainScorer(TrainScorer trainScorer) {
		return trainScorerMapper.addTrainScorer(trainScorer);
	}

	@Override
	public int deleteTrainScorer(Integer id) {
		return trainScorerMapper.deleteTrainScorer(id);
	}

	@Override
	public TrainScorer getTrainScorerById(Integer id) {
		return trainScorerMapper.getTrainScorerById(id);
	}

	@Override
	public int updateTrainScorer(TrainScorer trainScorer) {
		return trainScorerMapper.updateTrainScorer(trainScorer);
	}

	@Override
	public int addTrainProjectRule(TrainProjectRule trainProjectRule) {
		return trainProjectRuleMapper.addTrainProjectRule(trainProjectRule);
	}

	@Override
	public int addTrainProjectURule(TrainProjectURule trainProjectURule) {
		return trainProjectURuleMapper.addTrainProjectURule(trainProjectURule);
	}

	@Override
	public TrainProjectRule getTrainProjectRuleById(Integer id) {
		return trainProjectRuleMapper.getTrainProjectRuleById(id);
	}

	@Override
	public int deleteTrainProjectRule(Integer id) {
		return trainProjectRuleMapper.deleteTrainProjectRule(id);
	}

	@Override
	public int deleteTrainProjectURule(Integer ruleId) {
		return trainProjectURuleMapper.deleteTrainProjectURule(ruleId);
	}

	@Override
	public int updateTrainProjectRule(TrainProjectRule trainProjectRule) {
		return trainProjectRuleMapper.updateTrainProjectRule(trainProjectRule);
	}

	// ??????????????????????????????????????????
	@Override
	public TrainPhysicalProjectRecord projectNamesItem(Integer trainPhysicalId, String policeId) {
		return trainPhysicalProjectRecordMapper.projectNamesItem(trainPhysicalId, policeId);
	}

	@Override
	public List<TrainPhysicalAchievement> getTrainPhysicalAchievement(Integer trainPhysicalId, String trainGroupId,
			@Param("departmentId") Integer departmentId, String keyword, Integer pageSize) {
		return trainPhysicalAchievementMapper.getTrainPhysicalAchievement(trainPhysicalId, trainGroupId, departmentId,
				keyword, pageSize);
	}

	@Override
	public int getTrainPhysicalAchievementCount(Integer trainPhysicalId, String trainGroupId, Integer departmentId,
			String keyword) {
		return trainPhysicalAchievementMapper.getTrainPhysicalAchievementCount(trainPhysicalId, trainGroupId,
				departmentId, keyword);
	}

	@Override
	public TrainPhysicalAchievement getTrainPhysicalAchievementById(Integer id) {
		return trainPhysicalAchievementMapper.getTrainPhysicalAchievementById(id);
	}

	@Override
	public List<TrainPhysicalAchievementDetails> getTrainPhysicalAchievementDetailsByCondition(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId) {
		return trainPhysicalAchievementDetailsMapper.getTrainPhysicalAchievementDetailsByCondition(trainPhysicalId,
				trainPhysicalAchievementId);
	}

	@Override
	public Integer updateTrainPhysicalAchievementDetailsById(Integer id, Double achievement) {
		return trainPhysicalAchievementDetailsMapper.updateTrainPhysicalAchievementDetailsById(id, achievement);
	}

	@Override
	public TrainProjectRule getAchievement(Integer projectId, Integer groupId) {
		return trainProjectRuleMapper.getAchievement(projectId, groupId);
	}

	@Override
	public List<TrainPhysicalAchievementDetails> getAchievementDetailsProjectByCondition(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId) {
		return trainPhysicalAchievementDetailsMapper.getAchievementDetailsProjectByCondition(trainPhysicalId,
				trainPhysicalAchievementId);
	}

	// ????????????id??????id????????????????????????
	@Override
	public int trainDetailsPoliceUpdate(TrainPhysicalAchievementDetails item) {
		return trainPhysicalAchievementDetailsMapper.trainDetailsPoliceUpdate(item);
	}

	@Override
	public Integer getRank(Integer trainPhysicalId, Integer trainGroupId, Integer projectId, String policeId,
			Integer sort) {
		return trainPhysicalAchievementDetailsMapper.getRank(trainPhysicalId, trainGroupId, projectId, policeId, sort);
	}

	// ????????????
	@Override
	public int trainFirearmUpdate(TrainFirearm trainFirearm) {
		return trainFirearmMapper.trainFirearmUpdate(trainFirearm);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainLeaderList(String policeId, Integer status, Integer departmentId, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainLeaderList(policeId, status, departmentId, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public int trainLeaderCount(String policeId, Integer status, Integer departmentId) {
		return trainPhysicalMapper.trainLeaderCount(policeId, status, departmentId);
	}

	// ????????????
	@Override
	public int trainLeaderDelete(Integer id) {
		return trainLeaderMapper.trainLeaderDelete(id);
	}

	// ????????????
	@Override
	public int trainLeaderCreat(TrainLeader record) {
		return trainLeaderMapper.trainLeaderCreat(record);
	}

	// ????????????
	@Override
	public int trainLeaderUpdate(TrainLeader record) {
		return trainLeaderMapper.trainLeaderUpdate(record);
	}

	// ??????????????????
	@Override
	public TrainLeader trainLeaderItem(Integer id, String policeId, Integer departmentId) {
		return trainLeaderMapper.trainLeaderItem(id, policeId, departmentId);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainPhysical> trainLeaderSignUpList(String policeId, Integer signUpStatus, Integer departmentId,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.trainLeaderSignUpList(policeId, signUpStatus, departmentId, pageSize, pageNum);
	}

	// ???????????????????????????????????????
	@Override
	public int trainLeaderSignUpCount(String policeId, Integer signUpStatus, Integer departmentId) {
		return trainPhysicalMapper.trainLeaderSignUpCount(policeId, signUpStatus, departmentId);
	}

	@Override
	public List<TrainPhysicalAchievementDetails> getProjectByCondition(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId) {
		return trainPhysicalAchievementDetailsMapper.getProjectByCondition(trainPhysicalId, trainPhysicalAchievementId);
	}

	@Override
	public List<TrainPhysicalAchievementDetails> getAchievementRank(Integer trainPhysicalId, Integer trainGroupId,
			Integer projectId, Integer sort, String keyword, Integer pageSize) {
		return trainPhysicalAchievementDetailsMapper.getAchievementRank(trainPhysicalId, trainGroupId, projectId, sort,
				keyword, pageSize);
	}

	@Override
	public int getAchievementRankCount(Integer trainPhysicalId, Integer trainGroupId, Integer projectId,
			String keyword) {
		return trainPhysicalAchievementDetailsMapper.getAchievementRankCount(trainPhysicalId, trainGroupId, projectId,
				keyword);
	}

	@Override
	public int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle) {
		return trainActivityStyleMapper.updateTrainActivityStyle(trainActivityStyle);
	}

	@Override
	public int addTrainActivityStyle(TrainActivityStyle trainActivityStyle) {
		return trainActivityStyleMapper.addTrainActivityStyle(trainActivityStyle);
	}

	@Override
	public int deleteTrainActivityStyle(Integer id) {
		return trainActivityStyleMapper.deleteTrainActivityStyle(id);
	}

	@Override
	public TrainActivityStyle getTrainActivityStyleOnely(Integer id) {
		return trainActivityStyleMapper.getTrainActivityStyleOnely(id);
	}

	@Override
	public int addTrainPacesetter(TrainPacesetter trainPacesetter) {
		return trainPacesetterMapper.addTrainPacesetter(trainPacesetter);
	}

	@Override
	public int deleteTrainPacesetter(Integer id) {
		return trainPacesetterMapper.deleteTrainPacesetter(id);
	}

	@Override
	public TrainPacesetter getTrainPacesetterOnely(Integer id) {
		return trainPacesetterMapper.getTrainPacesetterOnely(id);
	}

	@Override
	public int updateTrainPacesetter(TrainPacesetter trainPacesetter) {
		return trainPacesetterMapper.updateTrainPacesetter(trainPacesetter);
	}

	@Override
	public int addTrainConstitution(TrainConstitution trainConstitution) {
		return trainConstitutionMapper.addTrainConstitution(trainConstitution);
	}

	@Override
	public int deleteTrainConstitution(Integer id) {
		return trainConstitutionMapper.deleteTrainConstitution(id);
	}

	@Override
	public TrainConstitution getTrainConstitutionOnely(Integer id) {
		return trainConstitutionMapper.getTrainConstitutionOnely(id);
	}

	@Override
	public int updateTrainConstitution(TrainConstitution trainConstitution) {
		return trainConstitutionMapper.updateTrainConstitution(trainConstitution);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainPhysicalRecommendPoliceList(Integer trainPhysicalId, Integer departmentId,
			String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalRecommendPoliceList(trainPhysicalId, departmentId,
				policeId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainFirearmRecommendPoliceList(Integer departmentId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmRecommendPoliceList(departmentId, policeId);
	}

	// ??????????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainPhysicalDepPoliceList(Integer trainPhysicalId, Integer departmentId,
			String keywords) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
				keywords);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainFirearmDepPoliceList(Integer departmentId, String keywords) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmDepPoliceList(departmentId, keywords);
	}

	@Override
	public Integer isRecommendOrStatus(Integer id, Integer isRecommend, Integer status) {
		return trainActivityStyleMapper.isRecommendOrStatus(id, isRecommend, status);
	}

	@Override
	public Integer constitutionIsRecommendOrStatus(Integer id, Integer isRecommend, Integer status) {
		return trainConstitutionMapper.constitutionIsRecommendOrStatus(id, isRecommend, status);
	}

	@Override
	public Integer pacesetterIsRecommendOrStatus(Integer id, Integer isRecommend, Integer status) {
		return trainPacesetterMapper.pacesetterIsRecommendOrStatus(id, isRecommend, status);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainPhysicalLeaderSignUpSuccessList(Integer trainPhysicalId,
			Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalLeaderSignUpSuccessList(trainPhysicalId,
				departmentId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainFirearmLeaderSignUpSuccessList(Integer trainPhysicalId,
			Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmLeaderSignUpSuccessList(trainPhysicalId, departmentId);
	}

	// ??????????????????????????????
	@Override
	public TrainLeaderAchievement trainPhysicalLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalLeaderAchievemenItem(trainPhysicalId, departmentId);
	}

	// ????????????????????????
	@Override
	public TrainLeaderAchievement trainFirearmLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmLeaderAchievemenItem(trainPhysicalId, departmentId);
	}

	// ??????????????????????????????????????????
	@Override
	public List<TrainRank> trainLeaderPhysicalSignUpRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
	}

	// ??????????????????????????????????????????
	@Override
	public List<TrainRank> trainLeaderPhysicalPassRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
	}

	// ?????????????????????????????????????????????
	@Override
	public List<TrainRank> trainLeaderPhysicalFailRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRank> trainLeaderFirearmSignUpRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
	}

	// ??????????????????,??????,????????????????????????
	@Override
	public List<TrainRank> trainLeaderFirearmGoodRateRankList(Integer trainPhysicalId, Integer gradeType,
			Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmGoodRateRankList(trainPhysicalId, gradeType,
				limitNum);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainRank> trainLeaderFirearmFailRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainPhysicalAchievementDetails> detailsFailList(Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.detailsFailList(trainPhysicalId, policeId);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> signMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.signMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> noSignMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.noSignMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> passMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.passMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> failMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.failMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainRecommendPolice> signFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.signFirearmMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainRecommendPolice> noSignFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.noSignFirearmMoreAchievementList(trainPhysicalId, departmentId);
	}

	// ??????????????????,??????,??????????????????
	@Override
	public List<TrainRecommendPolice> passFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId,
			Integer gradeType) {
		return trainPhysicalAchievementDetailsMapper.passFirearmMoreAchievementList(trainPhysicalId, departmentId,
				gradeType);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainRecommendPolice> failFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.failFirearmMoreAchievementList(trainPhysicalId, departmentId);
	}

	@Override
	public int deleteTrainMedal(Integer id) {
		return trainMedalManageMapper.deleteTrainMedal(id);
	}

	@Override
	public TrainMedalManage getTrainMedalOnely(Integer id) {
		return trainMedalManageMapper.getTrainMedalOnely(id);
	}

	@Override
	public int updateTrainMedal(TrainMedalManage trainMedalManage) {
		return trainMedalManageMapper.updateTrainMedal(trainMedalManage);
	}

	@Override
	public TrainLeader getTrainLeaderById(Integer id) {
		return trainLeaderMapper.getTrainLeaderById(id);
	}

	@Override
	public List<TrainLeader> getTrainLeaderList(String keyword, Integer pageSize) {
		return trainLeaderMapper.getTrainLeaderList(keyword, pageSize);
	}

	@Override
	public int getTrainLeaderListCount(String keyword) {
		return trainLeaderMapper.getTrainLeaderListCount(keyword);
	}

	@Override
	public int trainFirearmAdd(TrainFirearm trainFirearm) {
		return trainFirearmMapper.trainFirearmAdd(trainFirearm);
	}

	@Override
	public int trainFirearmDelete(Integer id) {
		return trainFirearmMapper.trainFirearmDelete(id);
	}

	@Override
	public int trainFirearmEnd(Integer id) {
		return trainFirearmMapper.trainFirearmEnd(id);
	}

	@Override
	public int trainFirearmReStart(Integer id, String time) {
		return trainFirearmMapper.trainFirearmReStart(id, time);
	}

	@Override
	public int trainFirearmStart(Integer id) {
		return trainFirearmMapper.trainFirearmStart(id);
	}

	@Override
	public TrainFirearm getTrainFirearmById(Integer id) {
		return trainFirearmMapper.getTrainFirearmById(id);
	}

	// ??????????????????????????????????????????????????????????????????
	@Override
	public List<TrainPhysical> physicaStatuslList() {
		return trainPhysicalMapper.physicaStatuslList();
	}

	// ????????????????????????????????????????????????????????????
	@Override
	public List<TrainFirearm> firearmStatuslList() {
		return trainFirearmMapper.firearmStatuslList();
	}

	// ???????????????????????????(???12??????)
	@Override
	public List<CalculationChart> trainDepPhysicalChart(Integer departmentId) {
		return trainPhysicalMapper.trainDepPhysicalChart(departmentId);
	}

	// ?????????????????????(???12??????)
	@Override
	public List<CalculationChart> trainDepFirearmChart(Integer departmentId) {
		return trainFirearmMapper.trainDepFirearmChart(departmentId);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> trainDepTypeChart(Integer departmentId) {
		return trainPhysicalMapper.trainDepTypeChart(departmentId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> trainDepPoliceList(Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainDepPoliceList(departmentId);
	}

	// ??????????????????????????????
	@Override
	public TrainStatisticsAnalysis trainTotalStatistics(Integer departmentId) {
		return trainPhysicalMapper.trainTotalStatistics(departmentId);
	}

	// ??????????????????????????????????????????
	@Override
	public TrainStatisticsAnalysis trainTotalLastStatistics(Integer departmentId) {
		return trainPhysicalMapper.trainTotalLastStatistics(departmentId);
	}

	@Override
	public List<TrainFirearmAchievement> getFirearmAchievementData(Integer trainFirearmId, Integer departmentId,
			String keyword, Integer pageSize) {
		return trainFirearmAchievementMapper.getFirearmAchievementData(trainFirearmId, departmentId, keyword, pageSize);
	}

	@Override
	public int getFirearmRank(Integer trainFirearmId, String policeId, Integer sort) {
		return trainFirearmAchievementMapper.getFirearmRank(trainFirearmId, policeId, sort);
	}

	@Override
	public int getFirearmAchievementDataCount(Integer trainFirearmId, Integer departmentId, String keyword) {
		return trainFirearmAchievementMapper.getFirearmAchievementDataCount(trainFirearmId, departmentId, keyword);
	}

	@Override
	public int updateFirearmAchievement(Integer id, Integer trainFirearmId, Double achievement) {
		return trainFirearmAchievementMapper.updateFirearmAchievement(id, trainFirearmId, achievement);
	}

	// ??????????????????????????????
	@Override
	public TrainPhysical trainRecentPhysicalItem() {
		return trainPhysicalMapper.trainRecentPhysicalItem();
	}

	// ?????????????????????????????????????????????
	@Override
	public TrainStatisticsAnalysis trainPhysicalLastStatistics(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalMapper.trainPhysicalLastStatistics(trainPhysicalId, departmentId);
	}

	// ???????????????????????????????????????
	@Override
	public TrainStatisticsAnalysis trainFirearmLastStatistics(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalMapper.trainFirearmLastStatistics(trainPhysicalId, departmentId);
	}

	@Override
	public TrainFirearmAchievement getTrainFirearmAchievementById(Integer id) {
		return trainFirearmAchievementMapper.getTrainFirearmAchievementById(id);
	}

	// ??????????????????????????????
	@Override
	public TrainStatisticsAnalysis matchTotalStatistics(Integer departmentId) {
		return trainPhysicalMapper.matchTotalStatistics(departmentId);
	}

	// ?????????????????????????????????(???12??????)
	@Override
	public List<CalculationChart> matchDepChart(Integer departmentId, Integer nature) {
		return trainPhysicalMapper.matchDepChart(departmentId, nature);
	}

	// ??????????????????????????????
	@Override
	public List<CalculationChart> matchDepTypeChart(Integer departmentId) {
		return trainPhysicalMapper.matchDepTypeChart(departmentId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.matchDepPoliceList(departmentId);
	}

	// ??????????????????
	@Override
	public List<TrainPhysical> trainWeekList(String policeId, Integer departmentId, String startTime, String endTime) {
		return trainPhysicalMapper.trainWeekList(policeId, departmentId, startTime, endTime);
	}

	// ??????????????????
	@Override
	public List<TrainPhysical> trainSoonList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainSoonList(policeId, departmentId);
	}

	// ??????/??????????????????
	@Override
	public List<TrainPhysical> trainMatchNotificationList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchNotificationList(policeId, departmentId);
	}

	// ????????????5????????????????????????????????????
	@Override
	public int trainArbitrarilyFivePassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainArbitrarilyFivePassCount(policeId);
	}

	// ????????????5????????????????????????????????????
	@Override
	public int trainContinuityFivePassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainContinuityFivePassCount(policeId);
	}

	// ??????3???????????????????????????????????????
	@Override
	public int trainContinuityThreeMonthPassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainContinuityThreeMonthPassCount(policeId);
	}

	// ????????????5??????????????????????????????Top3?????????????????????
	@Override
	public List<TrainPhysical> trainFirearmContinuityFivePassList(String policeId) {
		return trainPhysicalMapper.trainFirearmContinuityFivePassList(policeId);
	}

	// ??????????????????????????????
	@Override
	public TrainPhysical newsetScoreEnterItem(String policeId) {
		return trainPhysicalMapper.newsetScoreEnterItem(policeId);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainPersonalBestAchievementList(String policeId) {
		return trainPhysicalMapper.trainPersonalBestAchievementList(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainPersonalAchievement> trainPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.trainPersonalAchievementList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public List<TrainTimeName> trainTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainTimeNameList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public int trainPersonalAchievementCount(String policeId, Integer type, String dateTime) {
		return trainPhysicalMapper.trainPersonalAchievementCount(policeId, type, dateTime);
	}

	// ??????????????????????????????
	@Override
	public List<TrainTimeName> matchTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.matchTimeNameList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public List<TrainPersonalAchievement> matchPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.matchPersonalAchievementList(policeId, type, dateTime, pageSize, pageNum);
	}

	// ????????????????????????????????????
	@Override
	public int matchPersonalAchievementCount(String policeId, Integer type, String dateTime) {
		return trainPhysicalMapper.matchPersonalAchievementCount(policeId, type, dateTime);
	}

	// ??????????????????????????????
	@Override
	public TrainPersonalAchievement trainPersonalAchievementItem(String policeId, Integer id, Integer objectId) {
		return trainPhysicalMapper.trainPersonalAchievementItem(policeId, id, objectId);
	}

	// ??????????????????????????????
	@Override
	public TrainChartStatistics trainPersonalPhysicalStatisticsItem(String policeId, Integer objectType,
			String startTime, String endTime) {
		return trainPhysicalMapper.trainPersonalPhysicalStatisticsItem(policeId, objectType, startTime, endTime);
	}

	// ????????????????????????
	@Override
	public TrainChartStatistics trainPersonalFirearmStatisticsItem(String policeId, Integer objectType,
			String startTime, String endTime) {
		return trainPhysicalMapper.trainPersonalFirearmStatisticsItem(policeId, objectType, startTime, endTime);
	}

	// ???????????????????????????(???)
	@Override
	public int trainPersonalWeekChartList(String policeId, Integer objectType, String startTime, String endTime,
			String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalWeekChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> trainPersonalMonthChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalMonthChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> trainPersonalYearChartList(String policeId, String startTime, String endTime,
			String tableName, String yearTime) {
		return trainPhysicalMapper.trainPersonalYearChartList(policeId, startTime, endTime, tableName, yearTime);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> trainPersonalTotalChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalTotalChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// ???????????????????????????(???)
	@Override
	public int matchPersonalWeekChartList(String policeId, Integer objectType, Integer matchTypeId, String startTime,
			String endTime) {
		return trainPhysicalMapper.matchPersonalWeekChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> matchPersonalMonthChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime) {
		return trainPhysicalMapper.matchPersonalMonthChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> matchPersonalYearChartList(String policeId, Integer matchTypeId, String startTime,
			String endTime, String yearTime) {
		return trainPhysicalMapper.matchPersonalYearChartList(policeId, matchTypeId, startTime, endTime, yearTime);
	}

	// ???????????????????????????(???)
	@Override
	public List<CalculationChart> matchPersonalTotalChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime) {
		return trainPhysicalMapper.matchPersonalTotalChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	@Override
	public Integer qualifiedNum(Integer trainPhysicalId, Integer grade) {
		return trainPhysicalAchievementMapper.qualifiedNum(trainPhysicalId, grade);
	}

	@Override
	public Integer signInNum(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.signInNum(trainPhysicalId);
	}

	@Override
	public Integer signUpNum(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.signUpNum(trainPhysicalId);
	}

	@Override
	public Integer firearmQualifiedNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmQualifiedNum(trainFirearmId);
	}

	@Override
	public Integer firearmUnQualifiedNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmUnQualifiedNum(trainFirearmId);
	}

	@Override
	public Integer firearmSignInNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmSignInNum(trainFirearmId);
	}

	@Override
	public Integer firearmSignUpNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmSignUpNum(trainFirearmId);
	}

	@Override
	public Integer firearmUFineNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmUFineNum(trainFirearmId);
	}

	@Override
	public Integer firearmUGoodNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmUGoodNum(trainFirearmId);
	}

	// ??????????????????/????????????
	@Override
	public List<TrainPhysical> trainMatchOngoingList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchOngoingList(policeId, departmentId);
	}

	// ?????????????????????/????????????
	@Override
	public List<TrainPhysical> trainMatchSignUpList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchSignUpList(policeId, departmentId);
	}

	// ????????????????????????????????????
	@Override
	public TrainPersonalResult trainPersonalResultItem(String policeId) {
		return trainPhysicalMapper.trainPersonalResultItem(policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<CalculationChart> trainPersonalResultOfficeList(String policeId) {
		return trainPhysicalMapper.trainPersonalResultOfficeList(policeId);
	}

	// ????????????????????????????????????
	@Override
	public List<CalculationChart> trainPersonalResultDepList(String policeId) {
		return trainPhysicalMapper.trainPersonalResultDepList(policeId);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainPersonalLatelyAchievementList(String policeId) {
		return trainPhysicalMapper.trainPersonalLatelyAchievementList(policeId);
	}

	@Override
	public int trainPhysicalUpdateSpecial(TrainPhysical trainPhysical) {
		return trainPhysicalMapper.trainPhysicalUpdateSpecial(trainPhysical);
	}

	@Override
	public int trainFirearmUpdateSpecial(TrainFirearm trainFirearm) {
		return trainFirearmMapper.trainFirearmUpdateSpecial(trainFirearm);
	}

	// ???????????????????????????/?????????/????????????/?????????/???????????????
	@Override
	public List<LeaveChart> trainOfficeRateStatisticsList(Integer id, Integer type) {
		return trainPhysicalMapper.trainOfficeRateStatisticsList(id, type);
	}

	// ?????????????????????/?????????/????????????/?????????/???????????????
	@Override
	public List<LeaveChart> trainOfficeFirRateStatisticsList(Integer id, Integer type) {
		return trainPhysicalMapper.trainOfficeFirRateStatisticsList(id, type);
	}

	@Override
	public Integer companyNum(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.companyNum(trainPhysicalId);
	}

	@Override
	public Integer firearmCompanyNum(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.firearmCompanyNum(trainFirearmId);
	}

	@Override
	public int getRepeatTrainProjectCount(String name) {
		return trainProjectMapper.getRepeatTrainProjectCount(name);
	}

	// ??????????????????????????????
	@Override
	public List<TrainPhysicalAchievementDetails> trainSignInProjectList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer isSign) {
		return trainPhysicalAchievementDetailsMapper.trainSignInProjectList(trainPhysicalId, trainPhysicalAchievementId,
				policeId, isSign);
	}

	// ???????????????????????????
	@Override
	public TrainPhysicalAchievementDetails physicalDetailsItem(Integer id, Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer projectId) {
		return trainPhysicalAchievementDetailsMapper.physicalDetailsItem(id, trainPhysicalId,
				trainPhysicalAchievementId, policeId, projectId);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysical> trainInProgressList(String policeId, Integer status, String sort) {
		return trainPhysicalMapper.trainInProgressList(policeId, status, sort);
	}

	// ????????????????????????
	@Override
	public List<TrainPhysicalAchievement> trainApplicantsLeaderList(Integer trainPhysicalId, Integer departmentId,
			String tableName, String tableId) {
		return trainPhysicalAchievementMapper.trainApplicantsLeaderList(trainPhysicalId, departmentId, tableName,
				tableId);
	}

	// ????????????????????????
	@Override
	public ReportDataFillTime trainMatchTimeItem(String tableName) {
		return trainPhysicalAchievementMapper.trainMatchTimeItem(tableName);
	}

	// ??????????????????????????????????????????(??????????????????????????????)
	@Override
	public TrainProjectRule getTrainFirearmTypeByName(String name) {
		return trainProjectRuleMapper.getTrainFirearmTypeByName(name);
	}

	@Override
	public List<TrainAchievementTemplate> getFirearmTrainAchievementTemplate(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.getFirearmTrainAchievementTemplate(trainFirearmId);
	}

	@Override
	public List<TrainAchievementTemplate> getMatchTrainAchievementTemplate(Integer trainFirearmId) {
		return trainMatchAchievementMapper.getMatchTrainAchievementTemplate(trainFirearmId);
	}

	@Override
	public int trainFirearmAchievementUpdateExport(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementUpdateExport(item);
	}

	@Override
	public int matchAchievementUpdateExport(TrainMatchAchievement record) {
		return trainMatchAchievementMapper.matchAchievementUpdateExport(record);
	}

	@Override
	public TrainProject getTrainProjectByConditon(Integer trainFirearmId, String policeId) {
		return trainProjectMapper.getTrainProjectByConditon(trainFirearmId, policeId);
	}

	@Override
	public List<TrainAchievementTemplate> getPhysicalTrainAchievementTemplateList(Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.getPhysicalTrainAchievementTemplateList(trainPhysicalId);
	}

	@Override
	public int trainPhysicalAchievementDetailsUpdateCondition(TrainPhysicalAchievementDetails record) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsUpdateCondition(record);
	}

	@Override
	public Integer getTrainProjectByName(String name) {
		return trainProjectMapper.getTrainProjectByName(name);
	}

	@Override
	public int trainPhysicalAchievementUpdateByCondition(TrainPhysicalAchievement record) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementUpdateByCondition(record);
	}

	@Override
	public Integer AllTrainNum() {
		return trainPhysicalMapper.AllTrainNum();
	}

	// ?????????????????????????????????(??????????????????)
	@Override
	public List<CalculationChart> trainPhysicalHeadImageList(Integer trainPhysicalId, String policeId, Integer num) {
		return trainPhysicalMapper.trainPhysicalHeadImageList(trainPhysicalId, policeId, num);
	}

	// ???????????????????????????(??????????????????)
	@Override
	public List<CalculationChart> trainFirearmHeadImageList(Integer trainPhysicalId, String policeId, Integer num) {
		return trainPhysicalMapper.trainFirearmHeadImageList(trainPhysicalId, policeId, num);
	}

	// ??????????????????????????????????????????
	@Override
	public CalculationChart trainPhysicalHeadImageItem(Integer trainPhysicalId, String policeId) {
		return trainPhysicalMapper.trainPhysicalHeadImageItem(trainPhysicalId, policeId);
	}

	// ????????????????????????????????????
	@Override
	public CalculationChart trainFirearmHeadImageItem(Integer trainPhysicalId, String policeId) {
		return trainPhysicalMapper.trainFirearmHeadImageItem(trainPhysicalId, policeId);
	}

	// ???????????????????????????
	@Override
	public List<TrainPhysicalAchievementDetails> isWholeSignList(Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.isWholeSignList(trainPhysicalId, policeId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainLeader> trainIsScorerList(String policeId) {
		return trainLeaderMapper.trainIsScorerList(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecordScore> trainPhysicalOverScoreList(String policeId) {
		return trainPhysicalAchievementMapper.trainPhysicalOverScoreList(policeId);
	}

	// ??????????????????
	@Override
	public int achievementDateUpdate(TrainPhysicalAchievementDetails reacord) {
		return trainPhysicalAchievementDetailsMapper.achievementDateUpdate(reacord);
	}

	// ?????????????????????????????????????????????????????????
	@Override
	public TrainPhysical physicalScorerPoliceItem(Integer id, String scorerPoliceId) {
		return trainPhysicalMapper.physicalScorerPoliceItem(id, scorerPoliceId);
	}

	// ???????????????????????????????????????????????????
	@Override
	public TrainFirearm firearmScorerPoliceItem(Integer id, String scorerPoliceId) {
		return trainFirearmMapper.firearmScorerPoliceItem(id, scorerPoliceId);
	}

	@Override
	public TrainProjectRule getQualifiedAchievement(Integer id, Integer goupId) {
		return trainProjectRuleMapper.getQualifiedAchievement(id, goupId);
	}

	@Override
	public List<TrainProjectRule> getTrainProjectRuleByProjectId(Integer projectId) {
		return trainProjectRuleMapper.getTrainProjectRuleByProjectId(projectId);
	}

	@Override
	public List<TrainPhysicalProjectRecord> getTrainPhysicalProjectRecordByProjectRuleId(Integer projectRuleId) {
		return trainPhysicalProjectRecordMapper.getTrainPhysicalProjectRecordByProjectRuleId(projectRuleId);
	}

	@Override
	public List<TrainFirearm> getTrainFirearmByProjectRuleId(Integer projectRuleId) {
		return trainFirearmMapper.getTrainFirearmByProjectRuleId(projectRuleId);
	}

	// ????????????????????????
	@Override
	public void trainPhysicalAchievementCreatBatch(List<TrainPhysicalAchievement> creatList) {
		trainPhysicalAchievementMapper.trainPhysicalAchievementCreatBatch(creatList);

	}

	// ????????????????????????
	@Override
	public void trainPhysicalAchievementUpdateBatch(List<TrainPhysicalAchievement> updateList) {
		trainPhysicalAchievementMapper.trainPhysicalAchievementUpdateBatch(updateList);

	}

	// ??????????????????????????????
	@Override
	public void trainPhysicalAchievementDetailsCreatBatch(List<TrainPhysicalAchievementDetails> creatList) {
		trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsCreatBatch(creatList);

	}

	// ???????????????????????????
	@Override
	public void trainPhysicalAchievementDetailsUpdateBatch(List<TrainPhysicalAchievementDetails> updateList) {
		trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsUpdateBatch(updateList);
	}

	// ???????????????????????????id
	@Override
	public TrainProject trainProjectIdItem(String name) {
		return trainProjectMapper.trainProjectIdItem(name);
	}

	// ????????????id??????????????????list
	@Override
	public List<TrainPhysicalAchievement> updateGradeList(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.updateGradeList(trainPhysicalId);
	}

	// ????????????????????????
	@Override
	public void trainFirearmAchievementCreatBatch(List<TrainFirearmAchievement> creatList) {
		trainFirearmAchievementMapper.trainFirearmAchievementCreatBatch(creatList);

	}

	// ??????????????????????????????
	@Override
	public void trainFirearmAchievementUpdateBatch(List<TrainFirearmAchievement> finalList) {
		trainFirearmAchievementMapper.trainFirearmAchievementUpdateBatch(finalList);

	}

	// ??????????????????????????????????????????
	@Override
	public List<RiskTrainFirearmRecord> riskTrainFirearmRecordList(String policeId, String dateTime,
			String lastMonthTime, Integer timeType) {
		return trainFirearmAchievementMapper.riskTrainFirearmRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	// ????????????????????????????????????????????????
	@Override
	public List<RiskTrainPhysicalRecord> riskTrainPhysicalRecordList(String policeId, String dateTime,
			String lastMonthTime, Integer timeType) {
		return trainPhysicalAchievementMapper.riskTrainPhysicalRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	// ??????????????????????????????????????????
	@Override
	public List<RiskTrainPhysicalAchievementDetails> riskTrainSignInProjectList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer isSign) {
		return trainPhysicalAchievementDetailsMapper.riskTrainSignInProjectList(trainPhysicalId,
				trainPhysicalAchievementId, policeId, isSign);
	}

	@Override
	public TrainPhysicalAchievement findTrainPhysicalAchievementByPolice(Integer physicalId, String policeId) {
		return trainPhysicalAchievementMapper.findTrainPhysicalAchievementByPolice(physicalId, policeId);
	}

	@Override
	public void addTrainPhysicalAchievement(TrainPhysicalAchievement trainPhysicalAchievement) {
		trainPhysicalAchievementMapper.trainPhysicalAchievementCreat(trainPhysicalAchievement);
	}

	@Override
	public Integer getNotEligibleCount(Integer physicalId, Integer startNum, Integer endNum) {
		return trainPhysicalAchievementMapper.getNotEligibleCount(physicalId, startNum, endNum);
	}

	@Override
	public Integer unTestPoliceCount(Integer physicalId) {
		return trainPhysicalAchievementMapper.unTestPoliceCount(physicalId);
	}

	@Override
	public void deleteTrainFirearmAchievementByFirearmId(Integer firearmId) {
		trainFirearmAchievementMapper.deleteTrainFirearmAchievementByFirearmId(firearmId);
	}

	@Override
	public void deleteTrainPhysicalAchievementDetailsByPhysicalId(Integer physicalId) {
		trainPhysicalAchievementDetailsMapper.deleteTrainPhysicalAchievementDetailsByPhysicalId(physicalId);
	}

}
