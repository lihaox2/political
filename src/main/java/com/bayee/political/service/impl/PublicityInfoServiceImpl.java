package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.json.PublicityInfoSaveParam;
import com.bayee.political.mapper.*;
import com.bayee.political.pojo.dto.DepartmentDO;
import com.bayee.political.pojo.dto.PublicityInfoDO;
import com.bayee.political.pojo.dto.PublicityUserInfoDO;
import com.bayee.political.pojo.dto.UserDO;
import com.bayee.political.service.PublicityInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class PublicityInfoServiceImpl implements PublicityInfoService {

    @Autowired
    private PublicityInfoMapper publicityInfoMapper; //宣传信息

    @Autowired
    private PublicityUserInfoMapper publicityUserInfoMapper; //中间表

    @Autowired
    private PublicityTypeMapper publicityTypeMapper; //宣传类型

    @Autowired
    private UserMapper userMapper; //民警

    @Autowired
    private DepartmentMapper departmentMapper; //民警部门


    /**
     * 新增宣传信息
     *
     * @param saveParam
     * @return
     */
    @Override
    public Integer addPublicityInfo(PublicityInfoSaveParam saveParam) {
        PublicityInfo publicityInfo = new PublicityInfo();
        publicityInfo.setTitleName(saveParam.getTitleName());
        publicityInfo.setTypeId(saveParam.getTypeId());
        publicityInfo.setLevelName(saveParam.getLevelName());
        publicityInfo.setMediaName(saveParam.getMediaName());
        publicityInfo.setContent(saveParam.getContent());
        publicityInfo.setImgUrl(saveParam.getImgUrl());
        publicityInfo.setCreateTime(new Date());
        publicityInfo.setUpdateTime(new Date());
        Integer result = publicityInfoMapper.addPublicityInfo(publicityInfo);
        //如果有多个警号，使用，分隔
        String[] strings = saveParam.getPoliceArr().split(",");
        //循环遍历添加到中间表
        for (int i = 0; i < strings.length; i++) {
            PublicityUserInfo publicityUserInfo = new PublicityUserInfo();
            publicityUserInfo.setInfoId(publicityInfo.getId());
            publicityUserInfo.setPoliceId(strings[i]);
            publicityUserInfoMapper.addPublicityUserInfo(publicityUserInfo);
        }
        return result;
    }


    /**
     * 修改宣传信息
     *
     * @param saveParam
     * @return
     */
    @Override
    public Integer updatePublicityInfo(PublicityInfoSaveParam saveParam) {
        PublicityInfo publicityInfo = new PublicityInfo();
        publicityInfo.setId(saveParam.getId());
        publicityInfo.setTitleName(saveParam.getTitleName());
        publicityInfo.setTypeId(saveParam.getTypeId());
        publicityInfo.setLevelName(saveParam.getLevelName());
        publicityInfo.setMediaName(saveParam.getMediaName());
        publicityInfo.setContent(saveParam.getContent());
        publicityInfo.setImgUrl(saveParam.getImgUrl());
        publicityInfo.setUpdateTime(new Date());
        Integer result = publicityInfoMapper.updatePublicityInfo(publicityInfo);

        //根据宣传信息id删除中间表
        publicityUserInfoMapper.deletePublicityUserInfo(saveParam.getId());
        //如果有多个警民用，隔开
        String[] split = saveParam.getPoliceArr().split(",");
        //循环遍历添加
        for (int i = 0; i < split.length; i++) {
            PublicityUserInfo publicityUserInfo = new PublicityUserInfo();
            publicityUserInfo.setPoliceId(split[i]);
            publicityUserInfo.setInfoId(saveParam.getId());
            publicityUserInfoMapper.addPublicityUserInfo(publicityUserInfo);
        }
        return result;
    }


    /**
     * 删除宣传信息
     *
     * @param id
     * @return
     */
    @Override
    public Integer deletePublicityInfo(Integer id) {
        return publicityInfoMapper.deletePublicityInfo(id);
    }


    /**
     * 点击新增宣传时，查询所有宣传类型
     *
     * @return
     */
    @Override
    public List<PublicityType> findAllPublicType() {
        return publicityTypeMapper.findAllPublicityType();
    }


    /**
     * 点击新增宣传时，查询所有民警信息
     *
     * @return
     */
    @Override
    public List<User> findAllUser() {
        return userMapper.userAllList();
    }


    /**
     * 点击新增宣传时，查询所有民警部门信息
     *
     * @return
     */
    @Override
    public List<Department> findAllDept() {
        return departmentMapper.findAll();
    }


    /**
     * 根据id查询类型名称
     *
     * @param id
     * @return
     */
    @Override
    public Map<String, Object> findPublicityInfo(Integer id) {
        PublicityInfoDO publicityInfoDO = publicityInfoMapper.findPublicityInfo(id);
        if(publicityInfoDO.getImgUrl() != null && !"".equals(publicityInfoDO.getImgUrl())){
            String[] imgUrl = publicityInfoDO.getImgUrl().split(",");
            publicityInfoDO.setImgUrls(Arrays.asList(imgUrl));
        }
        List<PublicityUserInfoDO> publicityUserInfo = publicityUserInfoMapper.findPublicityUserInfo(publicityInfoDO.getId());

        Map<String, Object> result = new HashMap<>();
        result.put("data", publicityInfoDO);
        result.put("result", publicityUserInfo);
        return result;
    }


    /**
     * 列表
     *
     * @return
     */
    @Override
    public List<PublicityInfoDO> findPageList(Integer typeId,
                                                  Integer deptId,
                                                  Integer levelName,
                                                  String inputDate,
                                                  String likeName,
                                                  Integer pageIndex, Integer pageSize) {
        if (pageIndex == null) {
            pageIndex = 1;
        }
        if (pageSize == null) {
            pageSize = 10;
        }
        pageIndex = (pageIndex - 1) * pageSize;

        List<PublicityInfoDO> publicityInfoDOList = publicityInfoMapper.findPageList(typeId, deptId, levelName,
                inputDate, likeName, pageIndex, pageSize);

        for (PublicityInfoDO infoDO : publicityInfoDOList) {
            List<UserDO> userDOList = publicityInfoMapper.findPublicityInfoPoliceById(infoDO.getId());

            String policeNameStr = "";
            if (userDOList != null && userDOList.size() > 0 && userDOList.get(0) != null) {
                for (int i=0; i<userDOList.size(); i++) {
                    policeNameStr += userDOList.get(i).getUserName();

                    if (i != userDOList.size() - 1) {
                        policeNameStr += ",";
                    }
                }
            }
            infoDO.setUserName(policeNameStr);
        }
        return publicityInfoDOList;
    }

    @Override
    public Integer findPageListCount(Integer typeId, Integer deptId, Integer levelName, String inputDate, String likeName) {
        return publicityInfoMapper.findPageListCount(typeId, deptId, levelName, inputDate, likeName);
    }


    /**
     * 宣传报道单位排名TOP5
     * @return
     */
    @Override
    public List<DepartmentDO> findPublicityCountNum() {
        return departmentMapper.findPublicityCountNum();
    }


    /**
     * 查询宣传报道警员排名TOP5
     * @return
     */
    @Override
    public List<UserDO> findUserRanKing() {
        List<UserDO> userList = userMapper.findUserRanKing();
        return userList;
    }





}
