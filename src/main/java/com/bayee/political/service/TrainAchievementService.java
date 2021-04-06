package com.bayee.political.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.bayee.political.domain.MatchAchievementStatistics;
import com.bayee.political.domain.MatchAchievementTotalList;
import com.bayee.political.domain.MatchDepAchievementStatistics;
import com.bayee.political.domain.TrainAchievementStatistics;
import com.bayee.political.domain.TrainAchievementTotalList;
import com.bayee.political.domain.TrainDepAchievementStatistics;

/**
 * @author shentuqiwei
 * @version 2021年1月11日 下午2:41:11
 */
@Service
public interface TrainAchievementService {

	// 训练成绩统计查询
	TrainAchievementStatistics trainAchievementStatisticsItem(String policeId, Integer dateType, String dateTime);

	// 单位/分局训练成绩查询
	TrainDepAchievementStatistics trainDepAchievementStatisticsItem(String policeId, Integer type, Integer dateType,
			String dateTime);

	// 训练成绩记录查询
	List<TrainAchievementTotalList> trainAchievementTotalList(String policeId, Integer type, Integer dateType,
			String dateTime);

	// 赛事成绩统计查询
	MatchAchievementStatistics matchAchievementStatisticsItem(String policeId, Integer dateType, String dateTime);

	// 单位/分局赛事成绩查询
	MatchDepAchievementStatistics matchDepAchievementStatisticsItem(String policeId, Integer type, Integer dateType,
			String dateTime);

	// 赛事成绩记录查询
	List<MatchAchievementTotalList> matchAchievementTotalList(String policeId, Integer type, Integer dateType,
			String dateTime);

}
