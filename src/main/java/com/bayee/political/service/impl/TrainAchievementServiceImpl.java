package com.bayee.political.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bayee.political.domain.MatchAchievementStatistics;
import com.bayee.political.domain.MatchAchievementTotalList;
import com.bayee.political.domain.MatchDepAchievementStatistics;
import com.bayee.political.domain.TrainAchievementStatistics;
import com.bayee.political.domain.TrainAchievementTotalList;
import com.bayee.political.domain.TrainDepAchievementStatistics;
import com.bayee.political.mapper.TrainFirearmAchievementMapper;
import com.bayee.political.mapper.TrainFirearmMapper;
import com.bayee.political.mapper.TrainGroupMapper;
import com.bayee.political.mapper.TrainLeaderMapper;
import com.bayee.political.mapper.TrainMatchAchievementMapper;
import com.bayee.political.mapper.TrainMatchMapper;
import com.bayee.political.mapper.TrainMatchTypeMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementDetailsMapper;
import com.bayee.political.mapper.TrainPhysicalAchievementMapper;
import com.bayee.political.mapper.TrainPhysicalMapper;
import com.bayee.political.mapper.TrainPhysicalProjectRecordMapper;
import com.bayee.political.mapper.TrainProjectMapper;
import com.bayee.political.mapper.TrainProjectRuleMapper;
import com.bayee.political.mapper.TrainProjectURuleMapper;
import com.bayee.political.mapper.TrainScorerMapper;
import com.bayee.political.mapper.TrainUnitMapper;
import com.bayee.political.service.TrainAchievementService;

/**
 * @author shentuqiwei
 * @version 2021年1月11日 下午2:41:28
 */
@Service
public class TrainAchievementServiceImpl implements TrainAchievementService {

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

	// 训练成绩统计查询
	@Override
	public TrainAchievementStatistics trainAchievementStatisticsItem(String policeId, Integer dateType,
			String dateTime) {
		return trainPhysicalAchievementDetailsMapper.trainAchievementStatisticsItem(policeId, dateType, dateTime);
	}

	// 单位/分局训练成绩查询
	@Override
	public TrainDepAchievementStatistics trainDepAchievementStatisticsItem(String policeId, Integer type,
			Integer dateType, String dateTime) {
		return trainPhysicalAchievementDetailsMapper.trainDepAchievementStatisticsItem(policeId, type, dateType,
				dateTime);
	}

	// 训练成绩记录查询
	@Override
	public List<TrainAchievementTotalList> trainAchievementTotalList(String policeId, Integer type, Integer dateType,
			String dateTime) {
		return trainPhysicalAchievementDetailsMapper.trainAchievementTotalList(policeId, type, dateType, dateTime);
	}

	// 赛事成绩统计查询
	@Override
	public MatchAchievementStatistics matchAchievementStatisticsItem(String policeId, Integer dateType,
			String dateTime) {
		return trainMatchAchievementMapper.matchAchievementStatisticsItem(policeId, dateType, dateTime);
	}

	// 单位/分局赛事成绩查询
	@Override
	public MatchDepAchievementStatistics matchDepAchievementStatisticsItem(String policeId, Integer type,
			Integer dateType, String dateTime) {
		return trainMatchAchievementMapper.matchDepAchievementStatisticsItem(policeId, type, dateType, dateTime);
	}

	// 赛事成绩记录查询
	@Override
	public List<MatchAchievementTotalList> matchAchievementTotalList(String policeId, Integer type, Integer dateType,
			String dateTime) {
		return trainMatchAchievementMapper.matchAchievementTotalList(policeId, type, dateType, dateTime);
	}

}
