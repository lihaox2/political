//package com.bayee.political.mapper;
//
//import com.bayee.political.domain.JobUpdateRecordInfo;
//import org.apache.ibatis.annotations.Param;
//
//import java.util.List;
//
//public interface JobUpdateRecordInfoMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(JobUpdateRecordInfo record);
//
//    int insertSelective(JobUpdateRecordInfo record);
//
//    JobUpdateRecordInfo selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(JobUpdateRecordInfo record);
//
//    int updateByPrimaryKey(JobUpdateRecordInfo record);
//
//    /**
//     * 查询警员任职记录
//     */
//    List<JobUpdateRecordInfo> selectPoliceIds(@Param("policeId")String policeId);
//}