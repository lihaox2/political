package com.bayee.political.service;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.PublicityType;
import com.bayee.political.domain.User;
import com.bayee.political.json.PublicityInfoSaveParam;
import com.bayee.political.pojo.dto.DepartmentDO;
import com.bayee.political.pojo.dto.PublicityInfoDO;
import com.bayee.political.pojo.dto.UserDO;

import java.util.List;
import java.util.Map;

public interface PublicityInfoService {

    /**
     * 新增宣传信息
     * @param saveParam
     * @return
     */
    Integer addPublicityInfo(PublicityInfoSaveParam saveParam);


    /**
     * 修改宣传信息
     * @param saveParam
     * @return
     */
    Integer updatePublicityInfo(PublicityInfoSaveParam saveParam);


    /**
     * 删除宣传信息
     * @param id
     * @return
     */
    Integer deletePublicityInfo(Integer id);


    /**
     * 点击新增宣传时，查询所有宣传类型
     * @return
     */
    List<PublicityType> findAllPublicType();


    /**
     * 点击新增宣传时，查询所有民警信息
     * @return
     */
    List<User> findAllUser();


    /**
     * 点击新增宣传时，查询所有民警部门信息
     * @return
     */
    List<Department> findAllDept();


    /**
     * 根据id查询类型名称
     * @param id
     * @return
     */
    Map<String, Object> findPublicityInfo(Integer id);


    /**
     * 列表
     * @return
     */
    List<PublicityInfoDO> findPageList(Integer typeId,
                                       Integer deptId,
                                       Integer levelName,
                                       String inputDate,
                                       String likeName,
                                       Integer pageIndex, Integer pageSize);

    /**
     * 列表
     * @return
     */
    Integer findPageListCount(Integer typeId, Integer deptId, Integer levelName, String inputDate, String likeName);

    /**
     * 宣传报道单位排名TOP5
     * @return
     */
    List<DepartmentDO> findPublicityCountNum();



    /**
     * 查询宣传报道警员排名TOP5
     * @return
     */
    List<UserDO> findUserRanKing();



}