package com.bayee.political.mapper;

import com.bayee.political.domain.DisciplinaryRecordInfo;
import com.bayee.political.domain.PolicePromotionRecordInfo;
import com.bayee.political.domain.RiskHonour;
import com.bayee.political.json.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface PolicePromotionRecordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PolicePromotionRecordInfo record);

    int insertSelective(PolicePromotionRecordInfo record);

    PolicePromotionRecordInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PolicePromotionRecordInfo record);

    int updateByPrimaryKeyWithBLOBs(PolicePromotionRecordInfo record);

    int updateByPrimaryKey(PolicePromotionRecordInfo record);

    /**
     * 晋升分页查询
     * @return
     */
    List<PolicePromotionRecordInfo> selectPageList(@Param("param")PolicePromotionPageListParam param);

    /**
     * 导出警员晋升信息
     * @param type
     * @return
     */
    List<PolicePromotionRecordInfo> SelectExport(@Param("type")Integer type);


    /**
     * 导出警员晋升信息
     * @return
     */
    List<PolicePromotionRecordInfo> SelectExports(@Param("year") Date year,@Param("type") Integer type);

    /**
     * 警员晋升
     *
     * @return
     */
    List<PolicePromotionRecordInfo> selectPromotionList();

    PolicePromotionRecordInfo selectPromotionInfos(@Param("policeId")String policeId);

    /**
     * 查询警员参加工作时间
     */
    UserWorkingHoursResult selectUserWorkingHours(@Param("policeId")String policeId);

    /**
     * 查询警员参加工作时间是否被处分
     */
    List<PolicePromotionsResult> selectPoliceDisciplinaryActions(@Param("policeId")String policeId);

    /**
     * 查询是否是公务员
     */
    List<RiskHonour> selectIsCivilServant(@Param("policeId")String policeId, @Param("list")List<Date> list);

    /**
     * 查询晋升是是否被处分
     */
    List<DisciplinaryRecordInfo> selectDisciplinaryActions(@Param("policeId")String policeId, @Param("list")List<Date> list);

    /**
     * 查询该晋升的警员
     */
    List<PolicePromotionRecordResult> selectListPage(@Param("year")Date year);

    /**
     * 警员基本信息
     * @param policeId 警号
     */
    PolicePromotionBasicUserInfoResult selectPolicePromotionInfo(@Param("policeId")String policeId);

    /**
     * 查询历任职级
     * @param policeId 警号
     */
    List<PolicePromotionSuccessivePostsResult> selectSuccessivePosts(@Param("policeId")String policeId);

    List<PolicePromotionRecordInfo> findByis(@Param("policeId")String policeId,@Param("nowTime")Date nowTime);
}