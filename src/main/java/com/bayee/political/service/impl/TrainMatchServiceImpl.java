package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.CalculationChart;
import com.bayee.political.domain.MatchRecordScore;
import com.bayee.political.domain.MatchSubmit;
import com.bayee.political.domain.TrainActivityStyle;
import com.bayee.political.domain.TrainConstitution;
import com.bayee.political.domain.TrainEvaluateRecord;
import com.bayee.political.domain.TrainGetMedal;
import com.bayee.political.domain.TrainLikeRecord;
import com.bayee.political.domain.TrainMatch;
import com.bayee.political.domain.TrainMatchAchievement;
import com.bayee.political.domain.TrainMatchBestAchievement;
import com.bayee.political.domain.TrainMatchDepartmentAchievement;
import com.bayee.political.domain.TrainMatchPersonalResult;
import com.bayee.political.domain.TrainMatchProject;
import com.bayee.political.domain.TrainMatchType;
import com.bayee.political.domain.TrainMedalManage;
import com.bayee.political.domain.TrainMedalPerson;
import com.bayee.political.domain.TrainPacesetter;
import com.bayee.political.domain.TrainPersonalAchievementStatistics;
import com.bayee.political.domain.TrainRank;
import com.bayee.political.domain.TrainRecommendPolice;
import com.bayee.political.domain.TrainRecordPolice;
import com.bayee.political.mapper.TrainActivityStyleMapper;
import com.bayee.political.mapper.TrainConstitutionMapper;
import com.bayee.political.mapper.TrainEvaluateRecordMapper;
import com.bayee.political.mapper.TrainGetMedalMapper;
import com.bayee.political.mapper.TrainLikeRecordMapper;
import com.bayee.political.mapper.TrainMatchAchievementMapper;
import com.bayee.political.mapper.TrainMatchBestAchievementMapper;
import com.bayee.political.mapper.TrainMatchDepartmentAchievementMapper;
import com.bayee.political.mapper.TrainMatchMapper;
import com.bayee.political.mapper.TrainMatchProjectMapper;
import com.bayee.political.mapper.TrainMatchTypeMapper;
import com.bayee.political.mapper.TrainMedalManageMapper;
import com.bayee.political.mapper.TrainPacesetterMapper;
import com.bayee.political.service.TrainMatchService;

/**
 * @author shentuqiwei
 * @version 2020???10???25??? ??????4:44:44
 */
@Service
public class TrainMatchServiceImpl implements TrainMatchService {

	@Autowired
	TrainMatchMapper trainMatchMapper;// ??????

	@Autowired
	TrainMatchAchievementMapper trainMatchAchievementMapper;// ??????????????????

	@Autowired
	TrainMatchDepartmentAchievementMapper trainMatchDepartmentAchievementMapper;// ??????????????????

	@Autowired
	TrainMatchTypeMapper trainMatchTypeMapper; // ????????????

	@Autowired
	TrainMatchProjectMapper trainMatchProjectMapper;// ????????????

	@Autowired
	TrainActivityStyleMapper trainActivityStyleMapper;// ????????????

	@Autowired
	TrainEvaluateRecordMapper trainEvaluateRecordMapper;// ????????????

	@Autowired
	TrainLikeRecordMapper trainLikeRecordMapper;// ????????????

	@Autowired
	TrainPacesetterMapper trainPacesetterMapper;// ????????????

	@Autowired
	TrainConstitutionMapper trainConstitutionMapper;// ????????????

	@Autowired
	TrainGetMedalMapper trainGetMedalMapper;// ??????????????????????????????

	@Autowired
	TrainMedalManageMapper trainMedalManageMapper;// ????????????

	@Autowired
	TrainMatchBestAchievementMapper trainMatchBestAchievementMapper;// ????????????????????????

	// ????????????
	@Override
	public int trainMatchCreat(TrainMatch record) {
		return trainMatchMapper.trainMatchCreat(record);
	}

	// ????????????
	@Override
	public int trainMatchUpdate(TrainMatch record) {
		return trainMatchMapper.trainMatchUpdate(record);
	}

	// ??????????????????
	@Override
	public List<TrainMatch> matchList(String policeId, Integer type, Integer departmentId) {
		return trainMatchMapper.matchList(policeId, type, departmentId);
	}

	// ??????????????????
	@Override
	public int matchListCount(String policeId, Integer type, Integer departmentId) {
		return trainMatchMapper.matchListCount(policeId, type, departmentId);
	}

	// ????????????????????????
	@Override
	public List<TrainMatch> myMatchList(String policeId, Integer type, Integer status, Integer pageSize,
			Integer pageNum) {
		return trainMatchMapper.myMatchList(policeId, type, status, pageSize, pageNum);
	}

	// ????????????????????????
	@Override
	public int myMatchListCount(String policeId, Integer type, Integer status) {
		return trainMatchMapper.myMatchListCount(policeId, type, status);
	}

	// ??????????????????
	@Override
	public TrainMatch matchItem(Integer id) {
		return trainMatchMapper.matchItem(id);
	}

	@Override
	public int getTrainMatchTypeCount(String keyword) {
		return trainMatchTypeMapper.getTrainMatchTypeCount(keyword);
	}

	@Override
	public List<TrainMatchType> getTrainMatchTypeList(String keyword, Integer pageSize) {
		return trainMatchTypeMapper.getTrainMatchTypeList(keyword, pageSize);
	}

	@Override
	public int addTrainMatchType(TrainMatchType trainMatchType) {
		return trainMatchTypeMapper.addTrainMatchType(trainMatchType);
	}

	@Override
	public int updateTrainMatchType(TrainMatchType trainMatchType) {
		return trainMatchTypeMapper.updateTrainMatchType(trainMatchType);
	}

	@Override
	public int deleteTrainMatchType(Integer id) {
		return trainMatchTypeMapper.deleteTrainMatchType(id);
	}

	@Override
	public List<TrainMatchProject> getTrainMatchProjectList(String keyword, Integer pageSize) {
		return trainMatchProjectMapper.getTrainMatchProjectList(keyword, pageSize);
	}

	@Override
	public int getTrainMatchProjectCount(String keyword) {
		return trainMatchProjectMapper.getTrainMatchProjectCount(keyword);
	}

	@Override
	public int addTrainMatchProject(TrainMatchProject trainMatchProject) {
		return trainMatchProjectMapper.addTrainMatchProject(trainMatchProject);
	}

	@Override
	public int deleteTrainMatchProject(Integer id) {
		return trainMatchProjectMapper.deleteTrainMatchProject(id);
	}

	@Override
	public int updateTrainMatchProject(TrainMatchProject trainMatchProject) {
		return trainMatchProjectMapper.updateTrainMatchProject(trainMatchProject);
	}

	@Override
	public TrainMatchType selectTrainMatchTypeById(Integer id) {
		return trainMatchTypeMapper.selectTrainMatchTypeById(id);
	}

	@Override
	public TrainMatchProject selectTrainMatchProjectById(Integer id) {
		return trainMatchProjectMapper.selectTrainMatchProjectById(id);
	}

	@Override
	public int deleteTrainMatch(Integer id) {
		return trainMatchMapper.deleteTrainMatch(id);
	}

	@Override
	public int endTrainMatch(Integer id) {
		return trainMatchMapper.endTrainMatch(id);
	}

	@Override
	public int reStartTrainMatch(Integer id, String time) {
		return trainMatchMapper.reStartTrainMatch(id, time);
	}

	@Override
	public int startTrainMatch(Integer id) {
		return trainMatchMapper.startTrainMatch(id);
	}

	@Override
	public List<TrainMatchProject> getTrainMatchProjectByType(Integer type) {
		return trainMatchProjectMapper.getTrainMatchProjectByType(type);
	}

	// ??????????????????????????????
	@Override
	public TrainMatchAchievement matchPersonalSignUpSuccessItem(Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchPersonalSignUpSuccessItem(trainMatchId, policeId);
	}

	// ????????????????????????
	@Override
	public int matchAchievementCreat(TrainMatchAchievement record) {
		return trainMatchAchievementMapper.matchAchievementCreat(record);
	}

	// ????????????????????????
	@Override
	public int matchAchievementUpdate(TrainMatchAchievement record) {
		return trainMatchAchievementMapper.matchAchievementUpdate(record);
	}

	// ????????????????????????
	@Override
	public TrainMatchAchievement matchAchievementItem(Integer id, Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchAchievementItem(id, trainMatchId, policeId);
	}

	// ?????????????????????
	@Override
	public int matchNoScoreNum(String policeId) {
		return trainMatchMapper.matchNoScoreNum(policeId);
	}

	// ?????????????????????????????????
	@Override
	public List<MatchRecordScore> matchRecordScoreList(String policeId) {
		return trainMatchMapper.matchRecordScoreList(policeId);
	}

	@Override
	public int getTrainMatchAchievementCount(Integer trainMatchId, Integer departmentId, String keyword) {
		return trainMatchAchievementMapper.getTrainMatchAchievementCount(trainMatchId, departmentId, keyword);
	}

	@Override
	public List<TrainMatchAchievement> getTrainMatchAchievementList(Integer trainMatchId, Integer departmentId,
			String keyword, Integer pageSize) {
		return trainMatchAchievementMapper.getTrainMatchAchievementList(trainMatchId, departmentId, keyword, pageSize);
	}

	// ?????????????????????
	@Override
	public TrainRecordPolice matchRecordPoliceItem(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchRecordPoliceItem(trainMatchId);
	}

	// ????????????????????????
	@Override
	public List<TrainRank> matchRecordPoliceScoreList(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchRecordPoliceScoreList(trainMatchId);
	}

	// ?????????????????????
	@Override
	public TrainRecordPolice matchRecordDepartmentItem(Integer trainMatchId) {
		return trainMatchDepartmentAchievementMapper.matchRecordDepartmentItem(trainMatchId);
	}

	// ????????????????????????
	@Override
	public List<TrainRank> matchRecordDepartmentScoreList(Integer trainMatchId) {
		return trainMatchDepartmentAchievementMapper.matchRecordDepartmentScoreList(trainMatchId);
	}

	// ????????????????????????
	@Override
	public int matchDepartmentAchievementCreat(TrainMatchDepartmentAchievement record) {
		return trainMatchDepartmentAchievementMapper.matchDepartmentAchievementCreat(record);
	}

	// ????????????????????????
	@Override
	public int matchDepartmentAchievementUpdate(TrainMatchDepartmentAchievement record) {
		return trainMatchDepartmentAchievementMapper.matchDepartmentAchievementUpdate(record);
	}

	// ??????????????????
	@Override
	public TrainMatchDepartmentAchievement trainMatchDepartmentAchievementItem(Integer id, Integer trainMatchId,
			Integer departmentId) {
		return trainMatchDepartmentAchievementMapper.trainMatchDepartmentAchievementItem(id, trainMatchId,
				departmentId);
	}

	// ????????????????????????
	@Override
	public List<TrainMatch> matchLeaderPageList(String policeId, Integer signUpStatus, Integer status,
			Integer departmentId, Integer pageSize, Integer pageNum) {
		return trainMatchMapper.matchLeaderPageList(policeId, signUpStatus, status, departmentId, pageSize, pageNum);
	}

	// ??????????????????????????????
	@Override
	public int matchLeaderPageCount(String policeId, Integer signUpStatus, Integer status, Integer departmentId) {
		return trainMatchMapper.matchLeaderPageCount(policeId, signUpStatus, status, departmentId);
	}

	@Override
	public TrainMatch getTrainMatchById(Integer id) {
		return trainMatchMapper.getTrainMatchById(id);
	}

	// ????????????????????????????????????
	@Override
	public int alreadySignUpPoliceNum(Integer trainMatchId, Integer sex) {
		return trainMatchAchievementMapper.alreadySignUpPoliceNum(trainMatchId, sex);
	}

	// ???????????????????????????
	@Override
	public List<TrainRank> matchLeaderRankList(Integer trainMatchId, Integer limitNum) {
		return trainMatchAchievementMapper.matchLeaderRankList(trainMatchId, limitNum);
	}

	// ????????????????????????
	@Override
	public List<TrainRecommendPolice> matchRecommendPoliceList(Integer departmentId, Integer type) {
		return trainMatchAchievementMapper.matchRecommendPoliceList(departmentId, type);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId, Integer type, String keywords) {
		return trainMatchAchievementMapper.matchDepPoliceList(departmentId, type, keywords);
	}

	// ????????????????????????????????????
	@Override
	public List<MatchSubmit> matchOfficeSubmitScoreList(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchOfficeSubmitScoreList(trainMatchId);
	}

	// ??????????????????
	@Override
	public List<TrainMatch> trainOfficeMatchList() {
		return trainMatchMapper.trainOfficeMatchList();
	}

	// ??????????????????
	@Override
	public List<TrainMatch> matchWeekList(String policeId, Integer departmentId, String startTime, String endTime) {
		return trainMatchMapper.matchWeekList(policeId, departmentId, startTime, endTime);
	}

	// ????????????????????????
	@Override
	public List<TrainMatch> matchSoonList(String policeId, Integer departmentId) {
		return trainMatchMapper.matchSoonList(policeId, departmentId);
	}

	// ????????????
	@Override
	public List<TrainActivityStyle> recommendList(Integer limitNum) {
		return trainActivityStyleMapper.recommendList(limitNum);
	}

	// ??????????????????
	@Override
	public List<TrainActivityStyle> activityStyleList() {
		return trainActivityStyleMapper.activityStyleList();
	}

	// ??????????????????
	@Override
	public List<TrainActivityStyle> pacesetterList() {
		return trainActivityStyleMapper.pacesetterList();
	}

	// ??????????????????
	@Override
	public List<TrainActivityStyle> constitutionList() {
		return trainActivityStyleMapper.constitutionList();
	}

	// ????????????????????????
	@Override
	public TrainActivityStyle activityStyleItem(Integer id) {
		return trainActivityStyleMapper.activityStyleItem(id);
	}

	// ????????????????????????
	@Override
	public TrainActivityStyle pacesetterItem(Integer id) {
		return trainActivityStyleMapper.pacesetterItem(id);
	}

	// ????????????????????????
	@Override
	public TrainActivityStyle constitutionItem(Integer id) {
		return trainActivityStyleMapper.constitutionItem(id);
	}

	// ????????????
	@Override
	public int trainEvaluateRecordDelete(Integer id) {
		return trainEvaluateRecordMapper.trainEvaluateRecordDelete(id);
	}

	// ????????????
	@Override
	public int trainEvaluateRecordCreat(TrainEvaluateRecord record) {
		return trainEvaluateRecordMapper.trainEvaluateRecordCreat(record);
	}

	// ????????????
	@Override
	public int trainEvaluateRecordUpdate(TrainEvaluateRecord record) {
		return trainEvaluateRecordMapper.trainEvaluateRecordUpdate(record);
	}

	// ??????????????????
	@Override
	public TrainEvaluateRecord trainEvaluateRecordItem(Integer id, Integer objectId, Integer activityId,
			String policeId) {
		return trainEvaluateRecordMapper.trainEvaluateRecordItem(id, objectId, activityId, policeId);
	}

	// ??????????????????
	@Override
	public List<TrainEvaluateRecord> trainEvaluateRecordList(Integer objectId, Integer activityId, String policeId) {
		return trainEvaluateRecordMapper.trainEvaluateRecordList(objectId, activityId, policeId);
	}

	// ????????????
	@Override
	public int trainLikeRecordDelete(Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordDelete(objectId, activityId, policeId);
	}

	// ????????????
	@Override
	public int trainLikeRecordCreat(TrainLikeRecord record) {
		return trainLikeRecordMapper.trainLikeRecordCreat(record);
	}

	// ????????????
	@Override
	public int trainLikeRecordUpdate(TrainLikeRecord record) {
		return trainLikeRecordMapper.trainLikeRecordUpdate(record);
	}

	// ??????????????????
	@Override
	public TrainLikeRecord trainLikeRecordItem(Integer id, Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordItem(id, objectId, activityId, policeId);
	}

	// ??????????????????
	@Override
	public List<TrainLikeRecord> trainLikeRecordList(Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordList(objectId, activityId, policeId);
	}

	// ??????????????????
	@Override
	public int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle) {
		return trainActivityStyleMapper.updateTrainActivityStyle(trainActivityStyle);
	}

	// ??????????????????
	@Override
	public int updateTrainPacesetter(TrainPacesetter trainPacesetter) {
		return trainPacesetterMapper.updateTrainPacesetter(trainPacesetter);
	}

	// ??????????????????
	@Override
	public int updateTrainConstitution(TrainConstitution trainConstitution) {
		return trainConstitutionMapper.updateTrainConstitution(trainConstitution);
	}

	// ????????????????????????
	@Override
	public TrainMedalPerson medalPersonalItem(String policeId) {
		return trainGetMedalMapper.medalPersonalItem(policeId);
	}

	// ????????????????????????/??????????????????
	@Override
	public List<TrainMedalManage> trainGetMedalPersonalList(String policeId, Integer type) {
		return trainMedalManageMapper.trainGetMedalPersonalList(policeId, type);
	}

	// ??????????????????/????????????????????????
	@Override
	public TrainMedalManage trainGetMedalPersonalItem(String policeId, Integer type, Integer medalManageId) {
		return trainMedalManageMapper.trainGetMedalPersonalItem(policeId, type, medalManageId);
	}

	// ?????????????????????
	@Override
	public List<TrainMedalManage> trainMedalPersonalWallList(String policeId, Integer type) {
		return trainMedalManageMapper.trainMedalPersonalWallList(policeId, type);
	}

	// ????????????????????????????????????????????????????????????
	@Override
	public List<TrainMatch> matchStatuslList() {
		return trainMatchMapper.matchStatuslList();
	}

	// ??????????????????????????????
	@Override
	public TrainGetMedal trainGetMedalPoliceItem(Integer id, String policeId, Integer medalManageId) {
		return trainGetMedalMapper.trainGetMedalPoliceItem(id, policeId, medalManageId);
	}

	// ??????????????????
	@Override
	public int trainGetMedalCreat(TrainGetMedal record) {
		return trainGetMedalMapper.trainGetMedalCreat(record);
	}

	// ??????????????????
	@Override
	public int trainGetMedalUpdate(TrainGetMedal record) {
		return trainGetMedalMapper.trainGetMedalUpdate(record);
	}

	// ??????????????????
	@Override
	public int trainGetMedalDelete(Integer id) {
		return trainGetMedalMapper.trainGetMedalDelete(id);
	}

	// ??????????????????????????????
	@Override
	public List<TrainRecommendPolice> matchLeaderSignUpSuccessList(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchLeaderSignUpSuccessList(trainMatchId, departmentId);
	}

	// ????????????????????????
	@Override
	public TrainPersonalAchievementStatistics trainPersonalAchievementItem(String policeId, Integer type,
			String dateTime) {
		return trainMatchAchievementMapper.trainPersonalAchievementItem(policeId, type, dateTime);
	}

	// ????????????????????????????????????
	@Override
	public TrainPersonalAchievementStatistics trainPhysicallAchievementItem(String policeId) {
		return trainMatchAchievementMapper.trainPhysicallAchievementItem(policeId);
	}

	// ??????????????????????????????
	@Override
	public TrainPersonalAchievementStatistics trainFirearmAchievementItem(String policeId) {
		return trainMatchAchievementMapper.trainFirearmAchievementItem(policeId);
	}

	// ????????????????????????
	@Override
	public List<TrainMatchType> matchPersonalTypeList(String policeId) {
		return trainMatchTypeMapper.matchPersonalTypeList(policeId);
	}

	// ??????????????????
	@Override
	public List<TrainMedalManage> getMedalNumList() {
		return trainMedalManageMapper.getMedalNumList();
	}

	// ??????????????????
	@Override
	public int updateTrainMedal(TrainMedalManage item) {
		return trainMedalManageMapper.updateTrainMedal(item);
	}

	@Override
	public int trainMatchUpdateSpecial(TrainMatch trainMatch) {
		return trainMatchMapper.trainMatchUpdateSpecial(trainMatch);
	}

	// ??????????????????????????????
	@Override
	public TrainMatchPersonalResult matchPersonalResultItem(String policeId) {
		return trainMatchAchievementMapper.matchPersonalResultItem(policeId);
	}

	// ??????????????????????????????
	@Override
	public List<TrainMatch> matchPersonalAlreadyEnterList(String policeId, Integer type, Integer matchTypeId,
			String startTime, String endTime) {
		return trainMatchMapper.matchPersonalAlreadyEnterList(policeId, type, matchTypeId, startTime, endTime);
	}

	// ????????????????????????
	@Override
	public TrainRank matchPersonalRankItem(Integer trainMatchId, String policeId, String sortStr) {
		return trainMatchAchievementMapper.matchPersonalRankItem(trainMatchId, policeId, sortStr);
	}

	// ??????????????????????????????
	@Override
	public TrainRank matchDepRankItem(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchDepRankItem(trainMatchId, departmentId);
	}

	// ??????????????????????????????
	@Override
	public int matchBestAchievementCreat(TrainMatchBestAchievement record) {
		return trainMatchBestAchievementMapper.matchBestAchievementCreat(record);
	}

	// ??????????????????????????????
	@Override
	public int matchBestAchievementUpdate(TrainMatchBestAchievement record) {
		return trainMatchBestAchievementMapper.matchBestAchievementUpdate(record);
	}

	// ????????????????????????????????????
	@Override
	public TrainMatchBestAchievement matchBestAchievementItem(Integer id, Integer trainMatchId, Integer type,
			String policeId, Integer departmentId) {
		return trainMatchBestAchievementMapper.matchBestAchievementItem(id, trainMatchId, type, policeId, departmentId);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainMatchAchievement> trainMatchAlreadyAchievementList(Integer trainMatchId) {
		return trainMatchAchievementMapper.trainMatchAlreadyAchievementList(trainMatchId);
	}

	// ????????????????????????
	@Override
	public List<TrainMatch> matchInProgressList(String policeId, Integer status, String sort) {
		return trainMatchMapper.matchInProgressList(policeId, status, sort);
	}

	// ????????????
	@Override
	public List<TrainRank> matchMyEntryRankList(Integer trainMatchId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryRankList(trainMatchId, sortStr);
	}

	// ??????????????????
	@Override
	public TrainRank matchMyEntryRankItem(Integer trainMatchId, String policeId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryRankItem(trainMatchId, policeId, sortStr);
	}

	// ?????????????????????????????????
	@Override
	public List<TrainRank> matchMyEntryMoreRankList(Integer trainMatchId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryMoreRankList(trainMatchId, sortStr);
	}

	// ????????????????????????????????????
	@Override
	public List<TrainRecommendPolice> matchLeaderDepAchievementList(Integer trainMatchId, Integer departmentId,
			Integer isSign) {
		return trainMatchAchievementMapper.matchLeaderDepAchievementList(trainMatchId, departmentId, isSign);
	}

	// ??????????????????????????????
	@Override
	public List<TrainMatchAchievement> matchApplicantsLeaderList(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchApplicantsLeaderList(trainMatchId, departmentId);
	}

	// ??????????????????id
	@Override
	public List<TrainMedalManage> surplusMedalList(Integer type, Integer medalManageId) {
		return trainMedalManageMapper.surplusMedalList(type, medalManageId);
	}

	// ???????????????????????????
	@Override
	public List<CalculationChart> matchHeadImageList(Integer trainMatchId, String policeId, Integer num) {
		return trainMatchAchievementMapper.matchHeadImageList(trainMatchId, policeId, num);
	}

	// ????????????????????????????????????
	@Override
	public CalculationChart matchHeadImageItem(Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchHeadImageItem(trainMatchId, policeId);
	}

	// ?????????????????????????????????????????????
	@Override
	public TrainMatch matchScorerPoliceItem(Integer id, String scorerPoliceId) {
		return trainMatchMapper.matchScorerPoliceItem(id, scorerPoliceId);
	}

	// ?????????????????????????????????
	@Override
	public List<MatchRecordScore> matchRecordOverScoreList(String policeId) {
		return trainMatchMapper.matchRecordOverScoreList(policeId);
	}

	@Override
	public List<TrainMatch> getTrainMatchByMatchProjectId(Integer matchProjectId) {
		return trainMatchMapper.getTrainMatchByMatchProjectId(matchProjectId);
	}

	// ???????????????????????????????????????
	@Override
	public List<TrainRank> matchLeaderDepPoliceRankList(Integer trainMatchId, Integer departmentId, String sortStr) {
		return trainMatchAchievementMapper.matchLeaderDepPoliceRankList(trainMatchId, departmentId, sortStr);
	}

	@Override
	public String getUnitNameByAchievementId(Integer achievementId) {
		// TODO Auto-generated method stub
		return trainMatchAchievementMapper.getUnitNameByAchievementId(achievementId);
	}
	
	@Override
	public TrainMatchAchievement getTrainMatchAchievementById(Integer id) {
		// TODO Auto-generated method stub
		return trainMatchAchievementMapper.getTrainMatchAchievementById(id);
	}
	
	// ???????????????????????????????????????
	@Override
	public List<TrainMatchAchievement> registrationLompletedList(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.registrationLompletedList(trainMatchId, departmentId);
	}
	
	@Override
	public List<TrainMatch> getTrainMatchByTrainProjectId(Integer trainProjectId) {
		// TODO Auto-generated method stub
		return trainMatchMapper.getTrainMatchByTrainProjectId(trainProjectId);
	}

}
