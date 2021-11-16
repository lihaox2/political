/*
package com.bayee.political.mapper;

import com.bayee.political.domain.EvaluationInfoUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EvaluationInfoUserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(EvaluationInfoUser record);

    int insertSelective(EvaluationInfoUser record);

    EvaluationInfoUser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(EvaluationInfoUser record);

    int updateByPrimaryKey(EvaluationInfoUser record);

 */
/*   *//*
*/
/**
     * 用户管理-分页查询
     * @param queryParam
     * @return
     *//*
*/
/*
    List<UserPageQueryResultDO> userPage(@Param("param") UserPageQueryParam queryParam);*//*


    */
/**
     * 用户管理-登录
     * @param userName
     * @return
     *//*

    EvaluationInfoUser queryByUserName(@Param("userName") String userName);

    */
/**
     * 用户管理-禁用用户
     * @param id
     *//*

    void disable(@Param("id") Integer id);

    */
/**
     * 用户管理-启用用户
     * @param id
     *//*

    void enabled(@Param("id") Integer id);

    */
/**
     * 查询警号是否绑定
     * @param policeId
     * @return
     *//*

    EvaluationInfoUser selectByPoliceId(@Param("policeId") String policeId);
}*/
