package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceWorkingDeptLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliceWorkingDeptLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceWorkingDeptLog record);

    PoliceWorkingDeptLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PoliceWorkingDeptLog record);

    /**
     * 查询警员工作记录
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<PoliceWorkingDeptLog> findPoliceWorkingLog(@Param("policeId") String policeId,@Param("year") String year,
                                                    @Param("month") String month);

}