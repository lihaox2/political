package com.bayee.political.service.impl;

import java.util.List;

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
 * @version 2020年9月28日 上午9:47:26
 */
@Service
public class TrainServiceImpl implements TrainService {

	@Autowired
	TrainPhysicalMapper trainPhysicalMapper;// 综合体能

	@Autowired
	TrainPhysicalAchievementMapper trainPhysicalAchievementMapper;// 综合体能项目报名详情

	@Autowired
	TrainGroupMapper trainGroupMapper;// 训练组别管理

	@Autowired
	TrainFirearmAchievementMapper trainFirearmAchievementMapper;// 训练枪械成绩

	@Autowired
	TrainFirearmMapper trainFirearmMapper;// 枪械

	@Autowired
	TrainMatchMapper trainMatchMapper; // 赛事

	@Autowired
	TrainProjectMapper trainProjectMapper;// 训练项目管理

	@Autowired
	TrainScorerMapper trainScorerMapper;// 记分员配置

	@Autowired
	TrainProjectRuleMapper trainProjectRuleMapper;// 项目规则管理

	@Autowired
	TrainMedalManageMapper trainMedalManageMapper;// 奖章管理

	@Autowired
	TrainActivityStyleMapper trainActivityStyleMapper;// 活动风采

	@Autowired
	TrainPacesetterMapper trainPacesetterMapper;// 训练标兵

	@Autowired
	TrainConstitutionMapper trainConstitutionMapper;// 训练章程

	@Autowired
	TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper;// 体能项目成绩

	@Autowired
	TrainMatchTypeMapper trainMatchTypeMapper;// 赛事类型

	@Autowired
	TrainUnitMapper trainUnitMapper;// 单位

	@Autowired
	TrainPhysicalProjectRecordMapper trainPhysicalProjectRecordMapper;// 组别项目对应

	@Autowired
	TrainProjectURuleMapper trainProjectURuleMapper;// U型靶等级规则

	@Autowired
	TrainLeaderMapper trainLeaderMapper;// U型靶等级规则

	@Autowired
	TrainMatchAchievementMapper trainMatchAchievementMapper;// 赛事成绩

	// 综合体能删除
	@Override
	public int trainPhysicalDelete(Integer id) {
		return trainPhysicalMapper.trainPhysicalDelete(id);
	}

	// 综合体能新增
	@Override
	public int trainPhysicalCreat(TrainPhysical record) {
		return trainPhysicalMapper.trainPhysicalCreat(record);
	}

	// 综合体能修改
	@Override
	public int trainPhysicalUpdate(TrainPhysical record) {
		return trainPhysicalMapper.trainPhysicalUpdate(record);
	}

	// 综合体能详情查询
	@Override
	public TrainPhysical trainPhysicalItem(Integer id) {
		return trainPhysicalMapper.trainPhysicalItem(id);
	}

	// 综合体能列表查询
	@Override
	public List<TrainPhysical> trainPhysicalList(String policeId, Integer type) {
		return trainPhysicalMapper.trainPhysicalList(policeId, type);
	}

	// 综合体能列表总数统计
	@Override
	public int trainPhysicalCount(String policeId, Integer type) {
		return trainPhysicalMapper.trainPhysicalCount(policeId, type);
	}

	// 单项综合体能项目报名人数统计
	@Override
	public int singleTrainPhysicalAchievementCount(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.singleTrainPhysicalAchievementCount(trainPhysicalId);
	}

	// 查询组别列表
	@Override
	public List<TrainGroup> trainGroupList(Integer id) {
		return trainGroupMapper.trainGroupList(id);
	}

	// 查询组别详情
	@Override
	public TrainGroup trainGroupItem(Integer id) {
		return trainGroupMapper.trainGroupItem(id);
	}

	// 综合体能项目报名新增
	@Override
	public int trainPhysicalAchievementCreat(TrainPhysicalAchievement record) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementCreat(record);
	}

	// 综合体能项目报名修改
	@Override
	public int trainPhysicalAchievementUpdate(TrainPhysicalAchievement record) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementUpdate(record);
	}

	// 单项综合体能项目报名详情
	@Override
	public TrainPhysicalAchievement trainPhysicalAchievementItem(Integer id, Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementMapper.trainPhysicalAchievementItem(id, trainPhysicalId, policeId);
	}

	// 查询组别民警
	@Override
	public List<TrainGroupPolice> trainGroupPoliceList(String policeId) {
		return trainGroupMapper.trainGroupPoliceList(policeId);
	}

	// 枪械详情查询(使用综合体能实体类)
	@Override
	public TrainPhysical trainPhFirearmItem(Integer id) {
		return trainPhysicalMapper.trainPhFirearmItem(id);
	}

	@Override
	public String getTrainGroupByIds(String ids) {
		return trainGroupMapper.getTrainGroupByIds(ids);
	}

	// 枪械项目报名人数统计
	@Override
	public int singleTrainFirearmAchievementCount(Integer trainFirearmId) {
		return trainFirearmAchievementMapper.singleTrainFirearmAchievementCount(trainFirearmId);
	}

	// 枪械项目报名详情
	@Override
	public TrainFirearmAchievement trainFirearmAchievementItem(Integer id, Integer trainFirearmId, String policeId) {
		return trainFirearmAchievementMapper.trainFirearmAchievementItem(id, trainFirearmId, policeId);
	}

	// 枪械详情查询
	@Override
	public TrainFirearm trainFirearmItem(Integer id) {
		return trainFirearmMapper.trainFirearmItem(id);
	}

	// 枪械报名新增
	@Override
	public int trainFirearmAchievementCreat(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementCreat(item);
	}

	// 枪械报名修改
	@Override
	public int trainFirearmAchievementUpdate(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementUpdate(item);
	}

	// 近期训练查询
	@Override
	public List<TrainPhysical> trainRecentList(String policeId) {
		return trainPhysicalMapper.trainRecentList(policeId);
	}

	// 我的训练列表查询
	@Override
	public List<TrainPhysical> trainMyList(String policeId, Integer type, Integer status, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainMyList(policeId, type, status, pageSize, pageNum);
	}

	// 我的训练列表总数统计
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

	// 我的参赛记录查询
	@Override
	public List<TrainPhysicalAchievementDetails> trainMyEntryRecordList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainMyEntryRecordList(trainPhysicalId, trainPhysicalAchievementId,
				policeId);
	}

	// 参训成绩新增
	@Override
	public int trainPhysicalAchievementDetailsCreat(TrainPhysicalAchievementDetails record) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsCreat(record);
	}

	// 参训成绩修改
	@Override
	public int trainPhysicalAchievementDetailsUpdate(TrainPhysicalAchievementDetails record) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsUpdate(record);
	}

	// 参赛项目名称查询
	@Override
	public List<CalculationChart> trainProjectRankList(Integer trainPhysicalId, Integer trainPhysicalAchievementId,
			String policeId, Integer projectId) {
		return trainProjectMapper.trainProjectRankList(trainPhysicalId, trainPhysicalAchievementId, policeId,
				projectId);
	}

	// 根据年龄组和训练项目排名
	@Override
	public List<TrainRank> trainPersonalRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalRankList(projectId, trainPhysicalId, policeId,
				departmentId, sort);
	}

	// 根据年龄组和训练项目查询个人具体排名
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

	// 参赛枪械项目名称查询
	@Override
	public List<CalculationChart> trainProjectFirearmRankList(Integer trainFirearmId, String policeId) {
		return trainProjectMapper.trainProjectFirearmRankList(trainFirearmId, policeId);
	}

	// 根据年龄组和枪械项目排名
	@Override
	public List<TrainRank> trainPersonalFirearmRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmRankList(projectId, trainFirearmId, policeId,
				departmentId, sort);
	}

	// 根据年龄组和枪械项目查询个人具体排名
	@Override
	public TrainRank trainPersonalFirearmRankItem(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmRankItem(projectId, trainFirearmId, policeId,
				departmentId, sort);
	}

	// 查询当前民警所属组别中的综合体能项目
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

	// 根据年龄组和训练项目排名(不限制人数)
	@Override
	public List<TrainRank> trainPersonalMoreRankList(Integer projectId, Integer trainPhysicalId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalMoreRankList(projectId, trainPhysicalId, policeId,
				departmentId, sort);
	}

	// 根据年龄组和枪械项目排名(不限制人数)
	@Override
	public List<TrainRank> trainPersonalFirearmMoreRankList(Integer projectId, Integer trainFirearmId, String policeId,
			Integer departmentId, String sort) {
		return trainPhysicalAchievementDetailsMapper.trainPersonalFirearmMoreRankList(projectId, trainFirearmId,
				policeId, departmentId, sort);
	}

	// 综合体能未录分数量
	@Override
	public int trainPhysicalNoScoreNum(String policeId) {
		return trainScorerMapper.trainPhysicalNoScoreNum(policeId);
	}

	// 需要录分的训练查询
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

	// 录分训练计划中综合体能项目查询
	@Override
	public List<TrainProject> trainRecordProjectPhysicalList(Integer trainPhysicalId) {
		return trainProjectMapper.trainRecordProjectPhysicalList(trainPhysicalId);
	}

	// 录分训练计划中枪械查询
	@Override
	public List<TrainProject> trainRecordProjectFirearmList(Integer trainFirearmId) {
		return trainProjectMapper.trainRecordProjectFirearmList(trainFirearmId);
	}

	// 已录人数统计
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

	// 训练人员成绩查询
	@Override
	public List<TrainRank> trainRecordPoliceScoreList(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordPoliceScoreList(projectId, trainPhysicalId);
	}

	// 已录枪械人数统计
	@Override
	public TrainRecordPolice trainRecordFirearmPoliceItem(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordFirearmPoliceItem(projectId, trainPhysicalId);
	}

	// 枪械训练人员成绩查询
	@Override
	public List<TrainRank> trainRecordFirearmPoliceScoreList(Integer projectId, Integer trainPhysicalId) {
		return trainPhysicalAchievementDetailsMapper.trainRecordFirearmPoliceScoreList(projectId, trainPhysicalId);
	}

	// 根据项目id/组别查询算分规则
	@Override
	public TrainProjectRule trainProjectPoliceRuleItem(Integer projectId, Integer groupId) {
		return trainProjectRuleMapper.trainProjectPoliceRuleItem(projectId, groupId);
	}

	// 根据警号详情id项目id修改
	@Override
	public int trainFirearmAchievementPoliceUpdate(TrainFirearmAchievement item) {
		return trainFirearmAchievementMapper.trainFirearmAchievementPoliceUpdate(item);
	}

	@Override
	public int updateTrainGroup(TrainGroup trainGroup) {
		return trainGroupMapper.updateTrainGroup(trainGroup);
	}

	// 根据项目规则id查询U型靶成绩规则
	@Override
	public List<TrainProjectURule> TrainProjectURuleList(Integer ruleId) {
		return trainProjectURuleMapper.TrainProjectURuleList(ruleId);
	}

	// 根据警号详情id项目id批量修改枪械成绩
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

	// 查询当前用户所在组包括的项目
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

	// 警号详情id项目id修改综合体能成绩
	@Override
	public int trainDetailsPoliceUpdate(TrainPhysicalAchievementDetails item) {
		return trainPhysicalAchievementDetailsMapper.trainDetailsPoliceUpdate(item);
	}

	@Override
	public Integer getRank(Integer trainPhysicalId, Integer trainGroupId, Integer projectId, String policeId,
			Integer sort) {
		return trainPhysicalAchievementDetailsMapper.getRank(trainPhysicalId, trainGroupId, projectId, policeId, sort);
	}

	// 枪械修改
	@Override
	public int trainFirearmUpdate(TrainFirearm trainFirearm) {
		return trainFirearmMapper.trainFirearmUpdate(trainFirearm);
	}

	// 领队训练列表查询
	@Override
	public List<TrainPhysical> trainLeaderList(String policeId, Integer status, Integer departmentId, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainLeaderList(policeId, status, departmentId, pageSize, pageNum);
	}

	// 领队训练列表数量统计
	@Override
	public int trainLeaderCount(String policeId, Integer status, Integer departmentId) {
		return trainPhysicalMapper.trainLeaderCount(policeId, status, departmentId);
	}

	// 领队删除
	@Override
	public int trainLeaderDelete(Integer id) {
		return trainLeaderMapper.trainLeaderDelete(id);
	}

	// 领队新增
	@Override
	public int trainLeaderCreat(TrainLeader record) {
		return trainLeaderMapper.trainLeaderCreat(record);
	}

	// 领队修改
	@Override
	public int trainLeaderUpdate(TrainLeader record) {
		return trainLeaderMapper.trainLeaderUpdate(record);
	}

	// 领队详情查询
	@Override
	public TrainLeader trainLeaderItem(Integer id, String policeId, Integer departmentId) {
		return trainLeaderMapper.trainLeaderItem(id, policeId, departmentId);
	}

	// 领队训练报名中列表查询
	@Override
	public List<TrainPhysical> trainLeaderSignUpList(String policeId, Integer signUpStatus, Integer departmentId,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.trainLeaderSignUpList(policeId, signUpStatus, departmentId, pageSize, pageNum);
	}

	// 领队训练报名中列表数量统计
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

	// 综合训练推荐人员信息查询
	@Override
	public List<TrainRecommendPolice> trainPhysicalRecommendPoliceList(Integer trainPhysicalId, Integer departmentId,
			String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalRecommendPoliceList(trainPhysicalId, departmentId,
				policeId);
	}

	// 枪械推荐人员信息查询
	@Override
	public List<TrainRecommendPolice> trainFirearmRecommendPoliceList(Integer departmentId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmRecommendPoliceList(departmentId, policeId);
	}

	// 综合训练部门参加人员信息查询
	@Override
	public List<TrainRecommendPolice> trainPhysicalDepPoliceList(Integer trainPhysicalId, Integer departmentId,
			String keywords) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalDepPoliceList(trainPhysicalId, departmentId,
				keywords);
	}

	// 枪械部门参加人员信息查询
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

	// 领队综合训练报名成功查询
	@Override
	public List<TrainRecommendPolice> trainPhysicalLeaderSignUpSuccessList(Integer trainPhysicalId,
			Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalLeaderSignUpSuccessList(trainPhysicalId,
				departmentId);
	}

	// 领队枪械报名成功查询
	@Override
	public List<TrainRecommendPolice> trainFirearmLeaderSignUpSuccessList(Integer trainPhysicalId,
			Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmLeaderSignUpSuccessList(trainPhysicalId, departmentId);
	}

	// 领队综合训练成绩查询
	@Override
	public TrainLeaderAchievement trainPhysicalLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainPhysicalLeaderAchievemenItem(trainPhysicalId, departmentId);
	}

	// 领队枪械成绩查询
	@Override
	public TrainLeaderAchievement trainFirearmLeaderAchievemenItem(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainFirearmLeaderAchievemenItem(trainPhysicalId, departmentId);
	}

	// 领队综合体能签到率排行榜查询
	@Override
	public List<TrainRank> trainLeaderPhysicalSignUpRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalSignUpRateRankList(trainPhysicalId, limitNum);
	}

	// 领队综合体能合格率排行榜查询
	@Override
	public List<TrainRank> trainLeaderPhysicalPassRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalPassRateRankList(trainPhysicalId, limitNum);
	}

	// 领队综合体能不合格率排行榜查询
	@Override
	public List<TrainRank> trainLeaderPhysicalFailRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderPhysicalFailRateRankList(trainPhysicalId, limitNum);
	}

	// 领队枪械签到率排行榜查询
	@Override
	public List<TrainRank> trainLeaderFirearmSignUpRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmSignUpRateRankList(trainPhysicalId, limitNum);
	}

	// 领队枪械优秀,良好,合格率排行榜查询
	@Override
	public List<TrainRank> trainLeaderFirearmGoodRateRankList(Integer trainPhysicalId, Integer gradeType,
			Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmGoodRateRankList(trainPhysicalId, gradeType,
				limitNum);
	}

	// 领队枪械不合格率排行榜查询
	@Override
	public List<TrainRank> trainLeaderFirearmFailRateRankList(Integer trainPhysicalId, Integer limitNum) {
		return trainPhysicalAchievementDetailsMapper.trainLeaderFirearmFailRateRankList(trainPhysicalId, limitNum);
	}

	// 查询当前用户成绩是否合格
	@Override
	public List<TrainPhysicalAchievementDetails> detailsFailList(Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.detailsFailList(trainPhysicalId, policeId);
	}

	// 领队综合训练已签到人员查询
	@Override
	public List<TrainRecommendPolice> signMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.signMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队综合训练未签到人员查询
	@Override
	public List<TrainRecommendPolice> noSignMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.noSignMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队综合训练合格人员查询
	@Override
	public List<TrainRecommendPolice> passMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.passMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队综合训练不合格人员查询
	@Override
	public List<TrainRecommendPolice> failMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.failMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队枪械已签到人员查询
	@Override
	public List<TrainRecommendPolice> signFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.signFirearmMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队枪械未签到人员查询
	@Override
	public List<TrainRecommendPolice> noSignFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.noSignFirearmMoreAchievementList(trainPhysicalId, departmentId);
	}

	// 领队枪械优秀,良好,合格人员查询
	@Override
	public List<TrainRecommendPolice> passFirearmMoreAchievementList(Integer trainPhysicalId, Integer departmentId,
			Integer gradeType) {
		return trainPhysicalAchievementDetailsMapper.passFirearmMoreAchievementList(trainPhysicalId, departmentId,
				gradeType);
	}

	// 领队枪械不合格人员查询
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

	// 综合训练列表查询（定时任务修改约谈状态进程）
	@Override
	public List<TrainPhysical> physicaStatuslList() {
		return trainPhysicalMapper.physicaStatuslList();
	}

	// 枪械列表查询（定时任务修改约谈状态进程）
	@Override
	public List<TrainFirearm> firearmStatuslList() {
		return trainFirearmMapper.firearmStatuslList();
	}

	// 综合训练次数趋势图(近12个月)
	@Override
	public List<CalculationChart> trainDepPhysicalChart(Integer departmentId) {
		return trainPhysicalMapper.trainDepPhysicalChart(departmentId);
	}

	// 枪械次数趋势图(近12个月)
	@Override
	public List<CalculationChart> trainDepFirearmChart(Integer departmentId) {
		return trainFirearmMapper.trainDepFirearmChart(departmentId);
	}

	// 单位组织训练类型饼图
	@Override
	public List<CalculationChart> trainDepTypeChart(Integer departmentId) {
		return trainPhysicalMapper.trainDepTypeChart(departmentId);
	}

	// 警员参加训练次数查询
	@Override
	public List<TrainRecommendPolice> trainDepPoliceList(Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.trainDepPoliceList(departmentId);
	}

	// 训练总体数据统计分析
	@Override
	public TrainStatisticsAnalysis trainTotalStatistics(Integer departmentId) {
		return trainPhysicalMapper.trainTotalStatistics(departmentId);
	}

	// 训练总体数据最近部分统计分析
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

	// 查询最近一次分局训练
	@Override
	public TrainPhysical trainRecentPhysicalItem() {
		return trainPhysicalMapper.trainRecentPhysicalItem();
	}

	// 查询最近一次分局综合训练合格率
	@Override
	public TrainStatisticsAnalysis trainPhysicalLastStatistics(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalMapper.trainPhysicalLastStatistics(trainPhysicalId, departmentId);
	}

	// 查询最近一次分局枪械合格率
	@Override
	public TrainStatisticsAnalysis trainFirearmLastStatistics(Integer trainPhysicalId, Integer departmentId) {
		return trainPhysicalMapper.trainFirearmLastStatistics(trainPhysicalId, departmentId);
	}

	@Override
	public TrainFirearmAchievement getTrainFirearmAchievementById(Integer id) {
		return trainFirearmAchievementMapper.getTrainFirearmAchievementById(id);
	}

	// 比赛总体数据统计分析
	@Override
	public TrainStatisticsAnalysis matchTotalStatistics(Integer departmentId) {
		return trainPhysicalMapper.matchTotalStatistics(departmentId);
	}

	// 单位组织赛事次数趋势图(近12个月)
	@Override
	public List<CalculationChart> matchDepChart(Integer departmentId, Integer nature) {
		return trainPhysicalMapper.matchDepChart(departmentId, nature);
	}

	// 单位组织训练类型饼图
	@Override
	public List<CalculationChart> matchDepTypeChart(Integer departmentId) {
		return trainPhysicalMapper.matchDepTypeChart(departmentId);
	}

	// 警员参加赛事次数查询
	@Override
	public List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId) {
		return trainPhysicalAchievementDetailsMapper.matchDepPoliceList(departmentId);
	}

	// 本周训练查询
	@Override
	public List<TrainPhysical> trainWeekList(String policeId, Integer departmentId, String startTime, String endTime) {
		return trainPhysicalMapper.trainWeekList(policeId, departmentId, startTime, endTime);
	}

	// 即将训练查询
	@Override
	public List<TrainPhysical> trainSoonList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainSoonList(policeId, departmentId);
	}

	// 训练/赛事通知查询
	@Override
	public List<TrainPhysical> trainMatchNotificationList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchNotificationList(policeId, departmentId);
	}

	// 查询任意5次训练活动各项成绩均合格
	@Override
	public int trainArbitrarilyFivePassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainArbitrarilyFivePassCount(policeId);
	}

	// 查询连续5次训练活动各项成绩均合格
	@Override
	public int trainContinuityFivePassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainContinuityFivePassCount(policeId);
	}

	// 连续3个月参加训练，可获得该奖章
	@Override
	public int trainContinuityThreeMonthPassCount(String policeId) {
		return trainPhysicalAchievementMapper.trainContinuityThreeMonthPassCount(policeId);
	}

	// 查询连续5次，枪械训练各项成绩Top3，可获得该奖章
	@Override
	public List<TrainPhysical> trainFirearmContinuityFivePassList(String policeId) {
		return trainPhysicalMapper.trainFirearmContinuityFivePassList(policeId);
	}

	// 查询训练最近一次成绩
	@Override
	public TrainPhysical newsetScoreEnterItem(String policeId) {
		return trainPhysicalMapper.newsetScoreEnterItem(policeId);
	}

	// 个人最好成绩查询
	@Override
	public List<TrainPhysical> trainPersonalBestAchievementList(String policeId) {
		return trainPhysicalMapper.trainPersonalBestAchievementList(policeId);
	}

	// 个人训练成绩列表查询
	@Override
	public List<TrainPersonalAchievement> trainPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.trainPersonalAchievementList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 个人训练成绩时间查询
	@Override
	public List<TrainTimeName> trainTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.trainTimeNameList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 个人训练成绩列表总数查询
	@Override
	public int trainPersonalAchievementCount(String policeId, Integer type, String dateTime) {
		return trainPhysicalMapper.trainPersonalAchievementCount(policeId, type, dateTime);
	}

	// 个人赛事成绩时间查询
	@Override
	public List<TrainTimeName> matchTimeNameList(String policeId, Integer type, String dateTime, Integer pageSize,
			Integer pageNum) {
		return trainPhysicalMapper.matchTimeNameList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 个人赛事成绩列表查询
	@Override
	public List<TrainPersonalAchievement> matchPersonalAchievementList(String policeId, Integer type, String dateTime,
			Integer pageSize, Integer pageNum) {
		return trainPhysicalMapper.matchPersonalAchievementList(policeId, type, dateTime, pageSize, pageNum);
	}

	// 个人赛事成绩列表总数查询
	@Override
	public int matchPersonalAchievementCount(String policeId, Integer type, String dateTime) {
		return trainPhysicalMapper.matchPersonalAchievementCount(policeId, type, dateTime);
	}

	// 个人训练成绩详情查询
	@Override
	public TrainPersonalAchievement trainPersonalAchievementItem(String policeId, Integer id, Integer objectId) {
		return trainPhysicalMapper.trainPersonalAchievementItem(policeId, id, objectId);
	}

	// 个人综合体能训练统计
	@Override
	public TrainChartStatistics trainPersonalPhysicalStatisticsItem(String policeId, Integer objectType,
			String startTime, String endTime) {
		return trainPhysicalMapper.trainPersonalPhysicalStatisticsItem(policeId, objectType, startTime, endTime);
	}

	// 个人枪械训练统计
	@Override
	public TrainChartStatistics trainPersonalFirearmStatisticsItem(String policeId, Integer objectType,
			String startTime, String endTime) {
		return trainPhysicalMapper.trainPersonalFirearmStatisticsItem(policeId, objectType, startTime, endTime);
	}

	// 个人训练柱状图统计(周)
	@Override
	public int trainPersonalWeekChartList(String policeId, Integer objectType, String startTime, String endTime,
			String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalWeekChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// 个人训练柱状图统计(月)
	@Override
	public List<CalculationChart> trainPersonalMonthChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalMonthChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// 个人训练柱状图统计(年)
	@Override
	public List<CalculationChart> trainPersonalYearChartList(String policeId, String startTime, String endTime,
			String tableName, String yearTime) {
		return trainPhysicalMapper.trainPersonalYearChartList(policeId, startTime, endTime, tableName, yearTime);
	}

	// 个人训练柱状图统计(总)
	@Override
	public List<CalculationChart> trainPersonalTotalChartList(String policeId, Integer objectType, String startTime,
			String endTime, String tableName, String fromTableName) {
		return trainPhysicalMapper.trainPersonalTotalChartList(policeId, objectType, startTime, endTime, tableName,
				fromTableName);
	}

	// 个人赛事柱状图统计(周)
	@Override
	public int matchPersonalWeekChartList(String policeId, Integer objectType, Integer matchTypeId, String startTime,
			String endTime) {
		return trainPhysicalMapper.matchPersonalWeekChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	// 个人赛事柱状图统计(月)
	@Override
	public List<CalculationChart> matchPersonalMonthChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime) {
		return trainPhysicalMapper.matchPersonalMonthChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	// 个人赛事柱状图统计(年)
	@Override
	public List<CalculationChart> matchPersonalYearChartList(String policeId, Integer matchTypeId, String startTime,
			String endTime, String yearTime) {
		return trainPhysicalMapper.matchPersonalYearChartList(policeId, matchTypeId, startTime, endTime, yearTime);
	}

	// 个人赛事柱状图统计(总)
	@Override
	public List<CalculationChart> matchPersonalTotalChartList(String policeId, Integer objectType, Integer matchTypeId,
			String startTime, String endTime) {
		return trainPhysicalMapper.matchPersonalTotalChartList(policeId, objectType, matchTypeId, startTime, endTime);
	}

	@Override
	public Integer qualifiedNum(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.qualifiedNum(trainPhysicalId);
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

	// 进行中的训练/赛事查询
	@Override
	public List<TrainPhysical> trainMatchOngoingList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchOngoingList(policeId, departmentId);
	}

	// 正在报名的训练/赛事查询
	@Override
	public List<TrainPhysical> trainMatchSignUpList(String policeId, Integer departmentId) {
		return trainPhysicalMapper.trainMatchSignUpList(policeId, departmentId);
	}

	// 个人训练成绩综合查询查询
	@Override
	public TrainPersonalResult trainPersonalResultItem(String policeId) {
		return trainPhysicalMapper.trainPersonalResultItem(policeId);
	}

	// 个人分局各训练类型所占比
	@Override
	public List<CalculationChart> trainPersonalResultOfficeList(String policeId) {
		return trainPhysicalMapper.trainPersonalResultOfficeList(policeId);
	}

	// 个人单位各训练类型所占比
	@Override
	public List<CalculationChart> trainPersonalResultDepList(String policeId) {
		return trainPhysicalMapper.trainPersonalResultDepList(policeId);
	}

	// 个人最近成绩查询
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

	// 分局综合训练签到率/合格率/不合格率/优秀率/良好率统计
	@Override
	public List<LeaveChart> trainOfficeRateStatisticsList(Integer id, Integer type) {
		return trainPhysicalMapper.trainOfficeRateStatisticsList(id, type);
	}

	// 分局枪械签到率/合格率/不合格率/优秀率/良好率统计
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

	// 查询当前训练下的项目
	@Override
	public List<TrainPhysicalAchievementDetails> trainSignInProjectList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer isSign) {
		return trainPhysicalAchievementDetailsMapper.trainSignInProjectList(trainPhysicalId, trainPhysicalAchievementId,
				policeId, isSign);
	}

	// 查询当前训练项详情
	@Override
	public TrainPhysicalAchievementDetails physicalDetailsItem(Integer id, Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer projectId) {
		return trainPhysicalAchievementDetailsMapper.physicalDetailsItem(id, trainPhysicalId,
				trainPhysicalAchievementId, policeId, projectId);
	}

	// 进行中的训练查询
	@Override
	public List<TrainPhysical> trainInProgressList(String policeId, Integer status, String sort) {
		return trainPhysicalMapper.trainInProgressList(policeId, status, sort);
	}

	// 领队报名人员查询
	@Override
	public List<TrainPhysicalAchievement> trainApplicantsLeaderList(Integer trainPhysicalId, Integer departmentId,
			String tableName, String tableId) {
		return trainPhysicalAchievementMapper.trainApplicantsLeaderList(trainPhysicalId, departmentId, tableName,
				tableId);
	}

	// 成绩起始时间查询
	@Override
	public ReportDataFillTime trainMatchTimeItem(String tableName) {
		return trainPhysicalAchievementMapper.trainMatchTimeItem(tableName);
	}

	// 根据射击类型名称获得射击类型(射击项目中的射击类型)
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

	// 综合训练报名人头像查询(排除当前用户)
	@Override
	public List<CalculationChart> trainPhysicalHeadImageList(Integer trainPhysicalId, String policeId, Integer num) {
		return trainPhysicalMapper.trainPhysicalHeadImageList(trainPhysicalId, policeId, num);
	}

	// 枪械报名人头像查询(排除当前用户)
	@Override
	public List<CalculationChart> trainFirearmHeadImageList(Integer trainPhysicalId, String policeId, Integer num) {
		return trainPhysicalMapper.trainFirearmHeadImageList(trainPhysicalId, policeId, num);
	}

	// 综合训练报名当前用户头像查询
	@Override
	public CalculationChart trainPhysicalHeadImageItem(Integer trainPhysicalId, String policeId) {
		return trainPhysicalMapper.trainPhysicalHeadImageItem(trainPhysicalId, policeId);
	}

	// 枪械报名当前用户头像查询
	@Override
	public CalculationChart trainFirearmHeadImageItem(Integer trainPhysicalId, String policeId) {
		return trainPhysicalMapper.trainFirearmHeadImageItem(trainPhysicalId, policeId);
	}

	// 查询已经签到的项目
	@Override
	public List<TrainPhysicalAchievementDetails> isWholeSignList(Integer trainPhysicalId, String policeId) {
		return trainPhysicalAchievementDetailsMapper.isWholeSignList(trainPhysicalId, policeId);
	}

	// 是否是记分员判断查询
	@Override
	public List<TrainLeader> trainIsScorerList(String policeId) {
		return trainLeaderMapper.trainIsScorerList(policeId);
	}

	// 已完成录分的训练查询
	@Override
	public List<TrainRecordScore> trainPhysicalOverScoreList(String policeId) {
		return trainPhysicalAchievementMapper.trainPhysicalOverScoreList(policeId);
	}

	// 更新成绩时间
	@Override
	public int achievementDateUpdate(TrainPhysicalAchievementDetails reacord) {
		return trainPhysicalAchievementDetailsMapper.achievementDateUpdate(reacord);
	}

	// 综合体能判断当前用户是否可扫码当前人员
	@Override
	public TrainPhysical physicalScorerPoliceItem(Integer id, String scorerPoliceId) {
		return trainPhysicalMapper.physicalScorerPoliceItem(id, scorerPoliceId);
	}

	// 枪械判断当前用户是否可扫码当前人员
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

	// 训练报名批量新增
	@Override
	public void trainPhysicalAchievementCreatBatch(List<TrainPhysicalAchievement> creatList) {
		trainPhysicalAchievementMapper.trainPhysicalAchievementCreatBatch(creatList);

	}

	// 训练报名批量修改
	@Override
	public void trainPhysicalAchievementUpdateBatch(List<TrainPhysicalAchievement> updateList) {
		trainPhysicalAchievementMapper.trainPhysicalAchievementUpdateBatch(updateList);

	}

	// 体能项目成绩批量新增
	@Override
	public void trainPhysicalAchievementDetailsCreatBatch(List<TrainPhysicalAchievementDetails> creatList) {
		trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsCreatBatch(creatList);

	}

	// 体能项目成批量修改
	@Override
	public void trainPhysicalAchievementDetailsUpdateBatch(List<TrainPhysicalAchievementDetails> updateList) {
		trainPhysicalAchievementDetailsMapper.trainPhysicalAchievementDetailsUpdateBatch(updateList);
	}

	// 根据项目名查询项目id
	@Override
	public TrainProject trainProjectIdItem(String name) {
		return trainProjectMapper.trainProjectIdItem(name);
	}

	// 根据训练id查询报名人员list
	@Override
	public List<TrainPhysicalAchievement> updateGradeList(Integer trainPhysicalId) {
		return trainPhysicalAchievementMapper.updateGradeList(trainPhysicalId);
	}

	// 枪械报名批量新增
	@Override
	public void trainFirearmAchievementCreatBatch(List<TrainFirearmAchievement> creatList) {
		trainFirearmAchievementMapper.trainFirearmAchievementCreatBatch(creatList);

	}

	// 批量修改警员枪械成绩
	@Override
	public void trainFirearmAchievementUpdateBatch(List<TrainFirearmAchievement> finalList) {
		trainFirearmAchievementMapper.trainFirearmAchievementUpdateBatch(finalList);

	}

	// 警员警务技能枪械数据列表查询
	@Override
	public List<RiskTrainFirearmRecord> riskTrainFirearmRecordList(String policeId, String dateTime,
			String lastMonthTime, Integer timeType) {
		return trainFirearmAchievementMapper.riskTrainFirearmRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	// 警员警务技能综合训练数据列表查询
	@Override
	public List<RiskTrainPhysicalRecord> riskTrainPhysicalRecordList(String policeId, String dateTime,
			String lastMonthTime, Integer timeType) {
		return trainPhysicalAchievementMapper.riskTrainPhysicalRecordList(policeId, dateTime, lastMonthTime, timeType);
	}

	// 查询警员风险当前训练下的项目
	@Override
	public List<RiskTrainPhysicalAchievementDetails> riskTrainSignInProjectList(Integer trainPhysicalId,
			Integer trainPhysicalAchievementId, String policeId, Integer isSign) {
		return trainPhysicalAchievementDetailsMapper.riskTrainSignInProjectList(trainPhysicalId,
				trainPhysicalAchievementId, policeId, isSign);
	}
}
