/*
package com.bayee.political.service.impl;

import com.bayee.political.domain.EvaluationInfoUser;
import com.bayee.political.domain.EvaluationObject;
import com.bayee.political.json.UserLoginParam;
import com.bayee.political.json.UserLoginResult;
import com.bayee.political.json.UserSaveParam;
import com.bayee.political.mapper.EvaluationInfoUserMapper;
import com.bayee.political.mapper.EvaluationObjectMapper;
import com.bayee.political.service.EvaluationInfoUserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

*/
/**
 * @author tlt
 * @date 2021/10/26
 *//*

@Service
public class EvaluationInfoUserServiceImpl implements EvaluationInfoUserService {

    @Autowired
    private EvaluationInfoUserMapper userMapper;

    @Autowired
    private EvaluationObjectMapper evaluationObjectMapper;

    @Override
    public void addUser(EvaluationInfoUser user) {
        userMapper.insert(user);
    }

    @Override
    public void updateUser(EvaluationInfoUser user) {
        userMapper.updateByPrimaryKey(user);
    }

    @Override
    public void deleteUser(Integer id) {
        userMapper.deleteByPrimaryKey(id);
    }

    @Override
    public EvaluationInfoUser findById(Integer id) {
        return userMapper.selectByPrimaryKey(id);
    }

    @Override
    public DataListReturn logon(UserLoginParam info) {
        UserLoginResult userLogin = new UserLoginResult();
        EvaluationInfoUser userInfo = userMapper.queryByUserName(info.getUserName());
        if (userInfo != null) {
            if (userInfo.getPassword().equals(info.getPassword())) {
                if (userInfo.getIsDisable() == 2) {
                    return DataListReturn.error("该用户已被禁用！");
                } else {
                    //String token = UUID.randomUUID().toString().replaceAll("-","");
                    //userLogin.setToken(token);
                    BeanUtils.copyProperties(userInfo, userLogin);
                    //log.info("============================tokens:{}",redisUtil.set(userLogin.getToken(),userInfo,1800));
                    //log.info("==================================token:{}",redisUtil.get(userLogin.getToken()));
                    return DataListReturn.ok(userLogin);
                }
            } else {
                return DataListReturn.error("密码错误！");
            }
        } else {
            return DataListReturn.error("该用户不存在！");
        }
    }

    @Override
    public DataListReturn register(UserSaveParam saveParam) {
        EvaluationInfoUser userInfo = userMapper.queryByUserName(saveParam.getUserName());
        EvaluationObject object = evaluationObjectMapper.selectByPoliceId(saveParam.getFamilyPoliceId());

        if (userInfo != null) {
            return DataListReturn.error("用户名已存在!");
        } else {
            if (object != null) {
                if (object.getObjectName().equals(saveParam.getFamilyName())) {
                    EvaluationInfoUser user = userMapper.selectByPoliceId(saveParam.getFamilyPoliceId());
                    if (user == null) {
                    EvaluationInfoUser userSave = new EvaluationInfoUser();
                    userSave.setUserName(saveParam.getUserName());
                    userSave.setPassword(saveParam.getPassword());
                    userSave.setName(saveParam.getName());
                    userSave.setFamilyPoliceId(saveParam.getFamilyPoliceId());
                    userSave.setCreationDate(new Date());
                    userSave.setBusinessTime(new Date());
                    userSave.setRoleId(1);
                    userSave.setIsDisable(1);
                    userMapper.insert(userSave);
                    return  DataListReturn.ok("注册成功！");
                    } else {
                    return DataListReturn.error("该警员已经绑定家属！");
                    }
                } else {
                    return DataListReturn.error("警号与警员姓名不匹配！");
                }
            } else {
                return DataListReturn.error("该警员不存在！");
            }
        }
    }

    @Override
    public void disable(Integer id) {
        userMapper.disable(id);
    }

    @Override
    public void enabled(Integer id) {
        userMapper.enabled(id);
    }

    @Override
    public DataListReturn sysLogon(UserLoginParam info) {
        UserLoginResult userLogin = new UserLoginResult();
        EvaluationInfoUser userInfo = userMapper.queryByUserName(info.getUserName());
        if (userInfo != null) {
            if (userInfo.getPassword().equals(info.getPassword())) {
                if (userInfo.getRoleId() == 2) {
                   // String token = UUID.randomUUID().toString().replaceAll("-","");
                    //userLogin.setToken(token);
                    BeanUtils.copyProperties(userInfo, userLogin);
                    //log.info("============================tokens:{}",redisUtil.set(userLogin.getToken(),userInfo,1800));
                    //log.info("==================================token:{}",redisUtil.get(userLogin.getToken()));
                    return DataListReturn.ok(userLogin);
                } else {
                    return DataListReturn.error("该用户不是管理员！");
                }
            } else {
                return DataListReturn.error("密码错误！");
            }
        } else {
            return DataListReturn.error("该用户不存在！");
        }
    }
}
*/
