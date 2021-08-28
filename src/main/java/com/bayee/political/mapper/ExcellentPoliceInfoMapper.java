package com.bayee.political.mapper;

import com.bayee.political.domain.ExcellentPoliceInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ExcellentPoliceInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ExcellentPoliceInfo record);

    int insertSelective(ExcellentPoliceInfo record);

    ExcellentPoliceInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ExcellentPoliceInfo record);

    int updateByPrimaryKey(ExcellentPoliceInfo record);

    /**
     * 判断是否存在数据
     * @param policeId
     * @param label
     * @return
     */
    Integer checkExists(@Param("policeId") String policeId,@Param("label") Integer label);

    /**
     * 批量添加人才库数据
     * @param excellentPoliceInfoList
     */
    void insertExcellentPoliceInfoList(List<ExcellentPoliceInfo> excellentPoliceInfoList);

    /**
     *
     * @param policeId
     * @param labelId
     * @return
     */
    Integer countByPoliceIdAndCount(@Param("policeId") String policeId,@Param("labelId") Integer labelId);
}