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
 * @version 2020年10月25日 下午4:44:44
 */
@Service
public class TrainMatchServiceImpl implements TrainMatchService {

	@Autowired
	TrainMatchMapper trainMatchMapper;// 赛事

	@Autowired
	TrainMatchAchievementMapper trainMatchAchievementMapper;// 赛事个人成绩

	@Autowired
	TrainMatchDepartmentAchievementMapper trainMatchDepartmentAchievementMapper;// 赛事部门成绩

	@Autowired
	TrainMatchTypeMapper trainMatchTypeMapper; // 赛事类型

	@Autowired
	TrainMatchProjectMapper trainMatchProjectMapper;// 比赛项目

	@Autowired
	TrainActivityStyleMapper trainActivityStyleMapper;// 活动风采

	@Autowired
	TrainEvaluateRecordMapper trainEvaluateRecordMapper;// 评论记录

	@Autowired
	TrainLikeRecordMapper trainLikeRecordMapper;// 点赞记录

	@Autowired
	TrainPacesetterMapper trainPacesetterMapper;// 训练标兵

	@Autowired
	TrainConstitutionMapper trainConstitutionMapper;// 训练章程

	@Autowired
	TrainGetMedalMapper trainGetMedalMapper;// 训练警员奖章详情记录

	@Autowired
	TrainMedalManageMapper trainMedalManageMapper;// 奖章管理

	@Autowired
	TrainMatchBestAchievementMapper trainMatchBestAchievementMapper;// 个人赛事最好成绩

	// 赛事新增
	@Override
	public int trainMatchCreat(TrainMatch record) {
		return trainMatchMapper.trainMatchCreat(record);
	}

	// 赛事修改
	@Override
	public int trainMatchUpdate(TrainMatch record) {
		return trainMatchMapper.trainMatchUpdate(record);
	}

	// 赛事列表查询
	@Override
	public List<TrainMatch> matchList(String policeId, Integer type, Integer departmentId) {
		return trainMatchMapper.matchList(policeId, type, departmentId);
	}

	// 赛事总数统计
	@Override
	public int matchListCount(String policeId, Integer type, Integer departmentId) {
		return trainMatchMapper.matchListCount(policeId, type, departmentId);
	}

	// 我的赛事列表查询
	@Override
	public List<TrainMatch> myMatchList(String policeId, Integer type, Integer status, Integer pageSize,
			Integer pageNum) {
		return trainMatchMapper.myMatchList(policeId, type, status, pageSize, pageNum);
	}

	// 我的赛事总数统计
	@Override
	public int myMatchListCount(String policeId, Integer type, Integer status) {
		return trainMatchMapper.myMatchListCount(policeId, type, status);
	}

	// 赛事详情查询
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

	// 个人赛事报名成功查询
	@Override
	public TrainMatchAchievement matchPersonalSignUpSuccessItem(Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchPersonalSignUpSuccessItem(trainMatchId, policeId);
	}

	// 个人赛事成绩新增
	@Override
	public int matchAchievementCreat(TrainMatchAchievement record) {
		return trainMatchAchievementMapper.matchAchievementCreat(record);
	}

	// 个人赛事成绩修改
	@Override
	public int matchAchievementUpdate(TrainMatchAchievement record) {
		return trainMatchAchievementMapper.matchAchievementUpdate(record);
	}

	// 个人赛事详情查询
	@Override
	public TrainMatchAchievement matchAchievementItem(Integer id, Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchAchievementItem(id, trainMatchId, policeId);
	}

	// 赛事未录分数量
	@Override
	public int matchNoScoreNum(String policeId) {
		return trainMatchMapper.matchNoScoreNum(policeId);
	}

	// 需要录分的赛事活动查询
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

	// 已签到人数统计
	@Override
	public TrainRecordPolice matchRecordPoliceItem(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchRecordPoliceItem(trainMatchId);
	}

	// 赛事人员成绩查询
	@Override
	public List<TrainRank> matchRecordPoliceScoreList(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchRecordPoliceScoreList(trainMatchId);
	}

	// 已签到部门统计
	@Override
	public TrainRecordPolice matchRecordDepartmentItem(Integer trainMatchId) {
		return trainMatchDepartmentAchievementMapper.matchRecordDepartmentItem(trainMatchId);
	}

	// 赛事部门成绩查询
	@Override
	public List<TrainRank> matchRecordDepartmentScoreList(Integer trainMatchId) {
		return trainMatchDepartmentAchievementMapper.matchRecordDepartmentScoreList(trainMatchId);
	}

	// 赛事部门成绩新增
	@Override
	public int matchDepartmentAchievementCreat(TrainMatchDepartmentAchievement record) {
		return trainMatchDepartmentAchievementMapper.matchDepartmentAchievementCreat(record);
	}

	// 赛事部门成绩修改
	@Override
	public int matchDepartmentAchievementUpdate(TrainMatchDepartmentAchievement record) {
		return trainMatchDepartmentAchievementMapper.matchDepartmentAchievementUpdate(record);
	}

	// 赛事详情查询
	@Override
	public TrainMatchDepartmentAchievement trainMatchDepartmentAchievementItem(Integer id, Integer trainMatchId,
			Integer departmentId) {
		return trainMatchDepartmentAchievementMapper.trainMatchDepartmentAchievementItem(id, trainMatchId,
				departmentId);
	}

	// 领队比赛列表查询
	@Override
	public List<TrainMatch> matchLeaderPageList(String policeId, Integer signUpStatus, Integer status,
			Integer departmentId, Integer pageSize, Integer pageNum) {
		return trainMatchMapper.matchLeaderPageList(policeId, signUpStatus, status, departmentId, pageSize, pageNum);
	}

	// 领队比赛列表数量统计
	@Override
	public int matchLeaderPageCount(String policeId, Integer signUpStatus, Integer status, Integer departmentId) {
		return trainMatchMapper.matchLeaderPageCount(policeId, signUpStatus, status, departmentId);
	}

	@Override
	public TrainMatch getTrainMatchById(Integer id) {
		return trainMatchMapper.getTrainMatchById(id);
	}

	// 查询当前项目已经报名人数
	@Override
	public int alreadySignUpPoliceNum(Integer trainMatchId, Integer sex) {
		return trainMatchAchievementMapper.alreadySignUpPoliceNum(trainMatchId, sex);
	}

	// 比赛领队排行榜查询
	@Override
	public List<TrainRank> matchLeaderRankList(Integer trainMatchId, Integer limitNum) {
		return trainMatchAchievementMapper.matchLeaderRankList(trainMatchId, limitNum);
	}

	// 领队推荐人员查询
	@Override
	public List<TrainRecommendPolice> matchRecommendPoliceList(Integer departmentId, Integer type) {
		return trainMatchAchievementMapper.matchRecommendPoliceList(departmentId, type);
	}

	// 部门参加人员信息查询
	@Override
	public List<TrainRecommendPolice> matchDepPoliceList(Integer departmentId, Integer type, String keywords) {
		return trainMatchAchievementMapper.matchDepPoliceList(departmentId, type, keywords);
	}

	// 分局赛事录分提交页面查询
	@Override
	public List<MatchSubmit> matchOfficeSubmitScoreList(Integer trainMatchId) {
		return trainMatchAchievementMapper.matchOfficeSubmitScoreList(trainMatchId);
	}

	// 查询分局赛事
	@Override
	public List<TrainMatch> trainOfficeMatchList() {
		return trainMatchMapper.trainOfficeMatchList();
	}

	// 本周赛事查询
	@Override
	public List<TrainMatch> matchWeekList(String policeId, Integer departmentId, String startTime, String endTime) {
		return trainMatchMapper.matchWeekList(policeId, departmentId, startTime, endTime);
	}

	// 即将比赛赛事查询
	@Override
	public List<TrainMatch> matchSoonList(String policeId, Integer departmentId) {
		return trainMatchMapper.matchSoonList(policeId, departmentId);
	}

	// 推荐查询
	@Override
	public List<TrainActivityStyle> recommendList(Integer limitNum) {
		return trainActivityStyleMapper.recommendList(limitNum);
	}

	// 活动风采查询
	@Override
	public List<TrainActivityStyle> activityStyleList() {
		return trainActivityStyleMapper.activityStyleList();
	}

	// 训练标兵查询
	@Override
	public List<TrainActivityStyle> pacesetterList() {
		return trainActivityStyleMapper.pacesetterList();
	}

	// 训练章程查询
	@Override
	public List<TrainActivityStyle> constitutionList() {
		return trainActivityStyleMapper.constitutionList();
	}

	// 活动风采详情查询
	@Override
	public TrainActivityStyle activityStyleItem(Integer id) {
		return trainActivityStyleMapper.activityStyleItem(id);
	}

	// 训练标兵详情查询
	@Override
	public TrainActivityStyle pacesetterItem(Integer id) {
		return trainActivityStyleMapper.pacesetterItem(id);
	}

	// 训练章程详情查询
	@Override
	public TrainActivityStyle constitutionItem(Integer id) {
		return trainActivityStyleMapper.constitutionItem(id);
	}

	// 评论删除
	@Override
	public int trainEvaluateRecordDelete(Integer id) {
		return trainEvaluateRecordMapper.trainEvaluateRecordDelete(id);
	}

	// 评论新增
	@Override
	public int trainEvaluateRecordCreat(TrainEvaluateRecord record) {
		return trainEvaluateRecordMapper.trainEvaluateRecordCreat(record);
	}

	// 评论修改
	@Override
	public int trainEvaluateRecordUpdate(TrainEvaluateRecord record) {
		return trainEvaluateRecordMapper.trainEvaluateRecordUpdate(record);
	}

	// 评论详情查询
	@Override
	public TrainEvaluateRecord trainEvaluateRecordItem(Integer id, Integer objectId, Integer activityId,
			String policeId) {
		return trainEvaluateRecordMapper.trainEvaluateRecordItem(id, objectId, activityId, policeId);
	}

	// 评论列表查询
	@Override
	public List<TrainEvaluateRecord> trainEvaluateRecordList(Integer objectId, Integer activityId, String policeId) {
		return trainEvaluateRecordMapper.trainEvaluateRecordList(objectId, activityId, policeId);
	}

	// 点赞删除
	@Override
	public int trainLikeRecordDelete(Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordDelete(objectId, activityId, policeId);
	}

	// 点赞新增
	@Override
	public int trainLikeRecordCreat(TrainLikeRecord record) {
		return trainLikeRecordMapper.trainLikeRecordCreat(record);
	}

	// 点赞修改
	@Override
	public int trainLikeRecordUpdate(TrainLikeRecord record) {
		return trainLikeRecordMapper.trainLikeRecordUpdate(record);
	}

	// 点赞详情查询
	@Override
	public TrainLikeRecord trainLikeRecordItem(Integer id, Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordItem(id, objectId, activityId, policeId);
	}

	// 点赞列表查询
	@Override
	public List<TrainLikeRecord> trainLikeRecordList(Integer objectId, Integer activityId, String policeId) {
		return trainLikeRecordMapper.trainLikeRecordList(objectId, activityId, policeId);
	}

	// 修改活动风采
	@Override
	public int updateTrainActivityStyle(TrainActivityStyle trainActivityStyle) {
		return trainActivityStyleMapper.updateTrainActivityStyle(trainActivityStyle);
	}

	// 修改训练标兵
	@Override
	public int updateTrainPacesetter(TrainPacesetter trainPacesetter) {
		return trainPacesetterMapper.updateTrainPacesetter(trainPacesetter);
	}

	// 修改训练章程
	@Override
	public int updateTrainConstitution(TrainConstitution trainConstitution) {
		return trainConstitutionMapper.updateTrainConstitution(trainConstitution);
	}

	// 个人奖章详情查询
	@Override
	public TrainMedalPerson medalPersonalItem(String policeId) {
		return trainGetMedalMapper.medalPersonalItem(policeId);
	}

	// 个人获得训练奖章/赛事奖章查询
	@Override
	public List<TrainMedalManage> trainGetMedalPersonalList(String policeId, Integer type) {
		return trainMedalManageMapper.trainGetMedalPersonalList(policeId, type);
	}

	// 个人训练奖章/赛事奖章详情查询
	@Override
	public TrainMedalManage trainGetMedalPersonalItem(String policeId, Integer type, Integer medalManageId) {
		return trainMedalManageMapper.trainGetMedalPersonalItem(policeId, type, medalManageId);
	}

	// 个人奖章墙查询
	@Override
	public List<TrainMedalManage> trainMedalPersonalWallList(String policeId, Integer type) {
		return trainMedalManageMapper.trainMedalPersonalWallList(policeId, type);
	}

	// 比赛列表查询（定时任务修改约谈状态进程）
	@Override
	public List<TrainMatch> matchStatuslList() {
		return trainMatchMapper.matchStatuslList();
	}

	// 警员奖章详情记录查询
	@Override
	public TrainGetMedal trainGetMedalPoliceItem(Integer id, String policeId, Integer medalManageId) {
		return trainGetMedalMapper.trainGetMedalPoliceItem(id, policeId, medalManageId);
	}

	// 警员奖章新增
	@Override
	public int trainGetMedalCreat(TrainGetMedal record) {
		return trainGetMedalMapper.trainGetMedalCreat(record);
	}

	// 警员奖章修改
	@Override
	public int trainGetMedalUpdate(TrainGetMedal record) {
		return trainGetMedalMapper.trainGetMedalUpdate(record);
	}

	// 警员奖章删除
	@Override
	public int trainGetMedalDelete(Integer id) {
		return trainGetMedalMapper.trainGetMedalDelete(id);
	}

	// 领队比赛人员成功查询
	@Override
	public List<TrainRecommendPolice> matchLeaderSignUpSuccessList(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchLeaderSignUpSuccessList(trainMatchId, departmentId);
	}

	// 个人成绩统计查询
	@Override
	public TrainPersonalAchievementStatistics trainPersonalAchievementItem(String policeId, Integer type,
			String dateTime) {
		return trainMatchAchievementMapper.trainPersonalAchievementItem(policeId, type, dateTime);
	}

	// 最近一次综合训练成绩查询
	@Override
	public TrainPersonalAchievementStatistics trainPhysicallAchievementItem(String policeId) {
		return trainMatchAchievementMapper.trainPhysicallAchievementItem(policeId);
	}

	// 最近一次枪械成绩查询
	@Override
	public TrainPersonalAchievementStatistics trainFirearmAchievementItem(String policeId) {
		return trainMatchAchievementMapper.trainFirearmAchievementItem(policeId);
	}

	// 个人赛事类型查询
	@Override
	public List<TrainMatchType> matchPersonalTypeList(String policeId) {
		return trainMatchTypeMapper.matchPersonalTypeList(policeId);
	}

	// 查询奖章数量
	@Override
	public List<TrainMedalManage> getMedalNumList() {
		return trainMedalManageMapper.getMedalNumList();
	}

	// 修改奖章数量
	@Override
	public int updateTrainMedal(TrainMedalManage item) {
		return trainMedalManageMapper.updateTrainMedal(item);
	}

	@Override
	public int trainMatchUpdateSpecial(TrainMatch trainMatch) {
		return trainMatchMapper.trainMatchUpdateSpecial(trainMatch);
	}

	// 个人赛事成绩综合查询
	@Override
	public TrainMatchPersonalResult matchPersonalResultItem(String policeId) {
		return trainMatchAchievementMapper.matchPersonalResultItem(policeId);
	}

	// 个人已经参加赛事查询
	@Override
	public List<TrainMatch> matchPersonalAlreadyEnterList(String policeId, Integer type, Integer matchTypeId,
			String startTime, String endTime) {
		return trainMatchMapper.matchPersonalAlreadyEnterList(policeId, type, matchTypeId, startTime, endTime);
	}

	// 个人赛事排名查询
	@Override
	public TrainRank matchPersonalRankItem(Integer trainMatchId, String policeId, String sortStr) {
		return trainMatchAchievementMapper.matchPersonalRankItem(trainMatchId, policeId, sortStr);
	}

	// 个人分局赛事排名查询
	@Override
	public TrainRank matchDepRankItem(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchDepRankItem(trainMatchId, departmentId);
	}

	// 个人赛事最好成绩新增
	@Override
	public int matchBestAchievementCreat(TrainMatchBestAchievement record) {
		return trainMatchBestAchievementMapper.matchBestAchievementCreat(record);
	}

	// 个人赛事最好成绩修改
	@Override
	public int matchBestAchievementUpdate(TrainMatchBestAchievement record) {
		return trainMatchBestAchievementMapper.matchBestAchievementUpdate(record);
	}

	// 个人赛事最好成绩详情查询
	@Override
	public TrainMatchBestAchievement matchBestAchievementItem(Integer id, Integer trainMatchId, Integer type,
			String policeId, Integer departmentId) {
		return trainMatchBestAchievementMapper.matchBestAchievementItem(id, trainMatchId, type, policeId, departmentId);
	}

	// 查询当前赛事已经有成绩的人
	@Override
	public List<TrainMatchAchievement> trainMatchAlreadyAchievementList(Integer trainMatchId) {
		return trainMatchAchievementMapper.trainMatchAlreadyAchievementList(trainMatchId);
	}

	// 进行中的赛事查询
	@Override
	public List<TrainMatch> matchInProgressList(String policeId, Integer status, String sort) {
		return trainMatchMapper.matchInProgressList(policeId, status, sort);
	}

	// 赛事排名
	@Override
	public List<TrainRank> matchMyEntryRankList(Integer trainMatchId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryRankList(trainMatchId, sortStr);
	}

	// 赛事个人排名
	@Override
	public TrainRank matchMyEntryRankItem(Integer trainMatchId, String policeId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryRankItem(trainMatchId, policeId, sortStr);
	}

	// 赛事更多榜单排名榜查询
	@Override
	public List<TrainRank> matchMyEntryMoreRankList(Integer trainMatchId, String sortStr) {
		return trainMatchAchievementMapper.matchMyEntryMoreRankList(trainMatchId, sortStr);
	}

	// 领队部门人员比赛成绩查询
	@Override
	public List<TrainRecommendPolice> matchLeaderDepAchievementList(Integer trainMatchId, Integer departmentId,
			Integer isSign) {
		return trainMatchAchievementMapper.matchLeaderDepAchievementList(trainMatchId, departmentId, isSign);
	}

	// 赛事领队报名人员查询
	@Override
	public List<TrainMatchAchievement> matchApplicantsLeaderList(Integer trainMatchId, Integer departmentId) {
		return trainMatchAchievementMapper.matchApplicantsLeaderList(trainMatchId, departmentId);
	}

	// 查询剩余奖牌id
	@Override
	public List<TrainMedalManage> surplusMedalList(Integer type, Integer medalManageId) {
		return trainMedalManageMapper.surplusMedalList(type, medalManageId);
	}

	// 赛事报名人头像查询
	@Override
	public List<CalculationChart> matchHeadImageList(Integer trainMatchId, String policeId, Integer num) {
		return trainMatchAchievementMapper.matchHeadImageList(trainMatchId, policeId, num);
	}

	// 赛事报名当前用户头像查询
	@Override
	public CalculationChart matchHeadImageItem(Integer trainMatchId, String policeId) {
		return trainMatchAchievementMapper.matchHeadImageItem(trainMatchId, policeId);
	}

	// 判断当前用户是否可扫码当前人员
	@Override
	public TrainMatch matchScorerPoliceItem(Integer id, String scorerPoliceId) {
		return trainMatchMapper.matchScorerPoliceItem(id, scorerPoliceId);
	}

	// 赛事活动已完成录分查询
	@Override
	public List<MatchRecordScore> matchRecordOverScoreList(String policeId) {
		return trainMatchMapper.matchRecordOverScoreList(policeId);
	}

	@Override
	public List<TrainMatch> getTrainMatchByMatchProjectId(Integer matchProjectId) {
		return trainMatchMapper.getTrainMatchByMatchProjectId(matchProjectId);
	}

	// 领队自己部门比赛成绩排行榜
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
	
	// 查询本单位是否已经完成报名
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
