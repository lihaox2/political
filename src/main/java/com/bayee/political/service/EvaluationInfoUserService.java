/*
package com.bayee.political.service;

import com.bayee.political.domain.EvaluationInfoUser;
import com.bayee.political.json.UserLoginParam;
import com.bayee.political.json.UserSaveParam;
import com.bayee.political.utils.DataListReturn;

*/
/**
 * @author tlt
 * @date 2021/10/26
 *//*

public interface EvaluationInfoUserService {

    */
/**
     * 用户管理-添加记录
     * @param userInfo
     *//*

    void addUser(EvaluationInfoUser userInfo);

    */
/**
     * 用户管理-修改记录
     * @param user
     *//*

    void updateUser(EvaluationInfoUser user);

    */
/**
     * 用户管理-删除记录
     * @param id
     *//*

    void deleteUser(Integer id);

    */
/**
     * 用户管理-详情查询
     * @param id
     * @return
     *//*

    EvaluationInfoUser findById(Integer id);

*/
/*    *//*
*/
/**
     * 用户管理-分页查询
     * @param pageParam
     * @param queryParam
     * @return
     *//*
*/
/*
    PageHandler<UserPageQueryResultDO> UserPage(PageParam pageParam, UserPageQueryParam queryParam);*//*


    */
/**
     * 用户登录
     * @param info
     * @return
     *//*

    DataListReturn logon(UserLoginParam info);


    */
/**
     * 用户注册
     * @param saveParam
     * @return
     *//*

    DataListReturn register(UserSaveParam saveParam);

    */
/**
     * 禁用用户
     * @param id
     *//*

    void disable(Integer id);

    */
/**
     * 启用用户
     * @param id
     *//*

    void enabled(Integer id);

    */
/**
     * 后台登录
     * @param info
     * @return
     *//*

    DataListReturn sysLogon(UserLoginParam info);
}
*/
