package com.bayee.political.mapper;

import com.bayee.political.domain.DisciplinaryRecordInfo;
import com.bayee.political.json.DAListPageParam;
import com.bayee.political.json.DisciplinaryActionInfoResult;
import org.apache.ibatis.annotations.Param;

import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public interface DisciplinaryRecordInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DisciplinaryRecordInfo record);

    int insertSelective(DisciplinaryRecordInfo record);

    DisciplinaryRecordInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DisciplinaryRecordInfo record);

    int updateByPrimaryKeyWithBLOBs(DisciplinaryRecordInfo record);

    int updateByPrimaryKey(DisciplinaryRecordInfo record);

    /**
     * 纪律处分分页查询
     * @param param
     * @return
     */
    List<DisciplinaryActionInfoResult> listPage(@Param("param") DAListPageParam param);

    /**
     * 根据id 查询分页详情
     */
    DisciplinaryActionInfoResult info(@Param("id")Integer id);

    /**
     * 根据警号和年份查询纪律处分记录
     */
    List<DisciplinaryRecordInfo> selectPoliceId(@Param("policeId")String policeId, @Param("year")Date year);
}