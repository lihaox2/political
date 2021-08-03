package com.bayee.political.service.impl;

import com.bayee.political.json.*;
import com.bayee.political.mapper.*;
import com.bayee.political.service.PoliceSkillsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.NumberFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author zouya
 */
@Service
public class PoliceSkillsServiceImpl implements PoliceSkillsService {

    @Autowired
    private TrainPhysicalMapper trainPhysicalMapper;

    @Autowired
    private TrainFirearmMapper trainFirearmMapper;

    @Autowired
    private TrainPhysicalAchievementMapper trainPhysicalAchievementMapper;

    @Autowired
    private TrainPhysicalAchievementDetailsMapper trainPhysicalAchievementDetailsMapper;

    @Autowired
    private TrainFirearmAchievementMapper trainFirearmAchievementMapper;

    @Override
    public TrainCensusResult census() {
        TrainCensusResult result=trainPhysicalMapper.getTotalAndThisMonthCount();
        //参与总人数
        //所有（包含已参加和未参加）
        List<TrainIsEntryResult> list= trainPhysicalAchievementDetailsMapper.getParticipantsTotal();
        //未参加数量
        Integer notJoin=list.stream().filter(s->s.getIsEntry()==1).collect(Collectors.toList()).size();
        result.setParticipantsTotal(list.size()-notJoin);
        //总合格率
        Double qualifiedRate=trainPhysicalAchievementMapper.getQualifiedRate();
        result.setQualifiedRate(qualifiedRate);
        return result;
    }

    @Override
    public Map<String, Object> rankData() {
        Map<String,Object> map=new HashMap<>();
        //综合体能合格率集合
        List<TrainRankingResult> physicalList=trainPhysicalAchievementDetailsMapper.trainPhysicalQualifiedRate();
        List<TrainRankingResult> physicalSort=getRanking(physicalList);
        List<TrainRankingResult> physicalTop10=physicalSort.stream().filter(s->s.getRank()<11).collect(Collectors.toList());
        map.put("physicalTop10",physicalTop10.stream().sorted(Comparator.comparing(TrainRankingResult::getRank)).collect(Collectors.toList()));
        //枪械合格率集合
        List<TrainRankingResult> firearmList=trainFirearmAchievementMapper.trainFirearmQualifiedRate();
        List<TrainRankingResult> firearmSort=getRanking(firearmList);
        List<TrainRankingResult> firearmTop10=firearmSort.stream().filter(s->s.getRank()<11).collect(Collectors.toList());
        map.put("firearmTop10",firearmTop10.stream().sorted(Comparator.comparing(TrainRankingResult::getRank)).collect(Collectors.toList()));

        //总体合格率集合
        List<TrainRankingResult> totalList=trainPhysicalAchievementDetailsMapper.totalQualifiedRate();
        List<TrainRankingResult> totalSort=getRanking(totalList);
        List<TrainRankingResult> totalTop10=totalSort.stream().filter(s->s.getRank()<11).collect(Collectors.toList());
        map.put("totalTop10",totalTop10.stream().sorted(Comparator.comparing(TrainRankingResult::getRank)).collect(Collectors.toList()));
        return map;
    }

    /**
     * 排序（同合格率同名次）
     * @param ranks
     * @return
     */
    private static List<TrainRankingResult> getRanking(List<TrainRankingResult> ranks) {
        // 按照合格率排序
        ranks.sort(new Comparator<TrainRankingResult>() {
            @Override
            public int compare(TrainRankingResult s1, TrainRankingResult s2) {
                return -Double.compare(s1.getQualifiedRate(), s2.getQualifiedRate());
            }
        });
        // 排名
        int index = 0;
        // 最近一次的合格率
        double lastScore = -1;

        for (int i = 0; i < ranks.size(); i++) {
            TrainRankingResult s = ranks.get(i);
            if (Double.compare(lastScore, s.getQualifiedRate())!=0) {
                // 如果合格率和上一名的合格率不相同,那么排名+1
                lastScore = s.getQualifiedRate();
                index++;
            }
            s.setRank(index);
        }
        return ranks;
    }


    @Override
    public Map<String, Object> near6Month() {
        Map<String,Object> map=new HashMap<>();
        //综合体能
        List<TrainLineChartResult> physicalList=trainPhysicalAchievementMapper.getLineChartData(0);
        map.put("physicalList",physicalList);
        //枪械
        List<TrainLineChartResult> firearmList=trainFirearmAchievementMapper.getLineChartData();
        map.put("firearmList",firearmList);
        //抽测
        List<TrainLineChartResult> drawList=trainPhysicalAchievementMapper.getLineChartData(2);
        map.put("drawList",drawList);
        return map;
    }

    @Override
    public List<TrainQuantityResult> trainingQuantity() {
        List<TrainQuantityResult> list=new ArrayList<>();
        TrainQuantityResult result=new TrainQuantityResult();
        //综合体能总数
        Integer physicalTotalCount=trainPhysicalMapper.getCountByTrainType(0);
        //抽测数
        Integer drawCount=trainPhysicalMapper.getCountByTrainType(2);
        //枪械数
        Integer firearmCount=trainFirearmMapper.getCount();

        Integer total=physicalTotalCount+firearmCount;

        // 创建一个数值格式化对象
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        result=new TrainQuantityResult();
        result.setName("综合体能");
        result.setValue(numberFormat.format((float)physicalTotalCount/(float)total*100));
        list.add(result);

        result=new TrainQuantityResult();
        result.setName("枪械");
        result.setValue(numberFormat.format((float)firearmCount/(float)total*100));
        list.add(result);

        result=new TrainQuantityResult();
        result.setName("抽测");
        result.setValue(numberFormat.format((float)drawCount/(float)total*100));
        list.add(result);

        return list;
    }
}
