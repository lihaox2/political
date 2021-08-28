package com.bayee.political.service;

import com.bayee.political.domain.PoliceComment;

import java.util.List;

/**
 * @author xxl
 * @date 2021/6/5
 */
public interface PoliceCommentService {

    /**
     * 查询警员的评价
     *
     * @param policeId
     * @return
     */
    List<PoliceComment> findCommentByPoliceId(String policeId);

    /**
     * 查询警员最新的一条评价
     * @param policeId
     * @return
     */
    PoliceComment findNewCommentByPoliceId(String policeId);

    /**
     * 查询警员的评价
     * @param policeId
     * @param year
     * @param month
     * @return
     */
    List<PoliceComment> findCommentByPoliceIdAndYearAndMonth(String policeId, String year, String month);

    /**
     * 通过时间查询查询警员评价
     * @param policeId
     * @param date
     * @return
     */
    List<PoliceComment> findPoliceCommentByPoliceIdAndDate(String policeId, String date);

}
