package com.bayee.political.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.bayee.political.domain.AlarmScoreItem;

public interface AlarmScoreItemMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AlarmScoreItem record);

    int insertSelective(AlarmScoreItem record);

    AlarmScoreItem selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AlarmScoreItem record);

    int updateByPrimaryKey(AlarmScoreItem record);
    
    /**
     * 查询所有记分项目名
     * @return
     */
    List<AlarmScoreItem> getAllItemName();
    
	/**
	 * 根据police_id查询本月未上传的指标
	 * @return
	 */
	List<AlarmScoreItem> notUploaded(String scoringPoliceId);
	
	/**
	 * 根据计分项目名查询计分项目
	 * @return
	 */
	AlarmScoreItem selectByName(@Param("dimension") Integer dimension, @Param("name") String name);
	
}