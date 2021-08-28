package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceWorkingPositionLog;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliceWorkingPositionLogMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceWorkingPositionLog record);

    PoliceWorkingPositionLog selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PoliceWorkingPositionLog record);

    /**
     * 查询警员部门职务记录
     * @param deptLogId
     * @param policeId
     * @return
     */
    List<PoliceWorkingPositionLog> findByDeptLogIdAndPoliceId(@Param("deptLogId") Integer deptLogId,
                                                              @Param("policeId") String policeId);

}