package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceLabel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliceLabelMapper {


    /**
     * 查询所有标签
     * @return
     */
    List<PoliceLabel> findLabelAll();

    /**
     * 查询警员标签
     * @param policeId
     * @param date date is null, query all police label.
     * @return
     */
    List<PoliceLabel> findByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

    /**
     * 查询警员标签
     * @param policeId
     * @return
     */
    List<String> findPoliceLabel(@Param("policeId") String policeId);

    /**
     * 通过id查询标签
     * @param id
     * @return
     */
    PoliceLabel findById(Integer id);

    /**
     * 根据名字查询
     * @param labelName
     * @return
     */
    List<PoliceLabel> findLabelByName(String labelName);

    /**
     * 添加警员标签，可返回id
     * @param policeLabel
     */
    void insertPoliceLabel(PoliceLabel policeLabel);

    /**
     *
     * @return
     */
    Integer countByLabelName(String labelName);
}