package com.bayee.political.mapper;

import com.bayee.political.domain.PoliceComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PoliceCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PoliceComment record);

    PoliceComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(PoliceComment record);

    /**
     * 查询警员的评价
     *
     * @param policeId
     * @return
     */
    List<PoliceComment> findCommentByPoliceId(@Param("policeId") String policeId);

    /**
     * 查询警员最新的一条评价
     * @param policeId
     * @return
     */
    PoliceComment findNewCommentByPoliceId(@Param("policeId") String policeId);

    /**
     * 查询警员的评价
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<PoliceComment> findCommentByPoliceIdAndYearAndMonth(@Param("policeId") String policeId,
                                                             @Param("year") String year, @Param("month") String month);

    /**
     * 通过时间查询查询警员评价
     * @param policeId
     * @param date
     * @return
     */
    List<PoliceComment> findPoliceCommentByPoliceIdAndDate(@Param("policeId") String policeId, @Param("date") String date);

}