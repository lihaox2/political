package com.bayee.political.mapper;

import com.bayee.political.domain.PolicePromotionRecordInfo;
import com.bayee.political.json.PolicePromotionPageListParam;
import org.apache.ibatis.annotations.Param;

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
}