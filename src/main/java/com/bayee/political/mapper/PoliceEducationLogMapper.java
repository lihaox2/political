package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceEducationLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliceEducationLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceEducationLog record);

    PoliceEducationLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PoliceEducationLog record);

    /**
     * 通过警号查询警员学历记录
     * @param policeId
     * @return
     */
    List<PoliceEducationLog> findEducationLodByPoliceId(@Param("policeId") String policeId, @Param("year") String year,
                                                        @Param("month") String month);

}