package com.bayee.political.mapper;

import com.bayee.political.domain.PublicityInfo;
import com.bayee.political.pojo.dto.PublicityInfoDO;
import com.bayee.political.pojo.dto.UserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicityInfoMapper {

    /**
     * 新增宣传信息
     * @param publicityInfo
     * @return
     */
    Integer addPublicityInfo(PublicityInfo publicityInfo);


    /**
     * 修改宣传信息
     * @param publicityInfo
     * @return
     */
    Integer updatePublicityInfo(PublicityInfo publicityInfo);


    /**
     * 删除宣传信息
     * @param id
     * @return
     */
    Integer deletePublicityInfo(@Param("id") Integer id);


    /**
     * 根据id查询类型名称
     * @param id
     * @return
     */
    PublicityInfoDO findPublicityInfo(@Param("id") Integer id);


    /**
     * 列表
     * @return
     */
    List<PublicityInfoDO> findPageList(@Param("typeId") Integer typeId,
                                       @Param("deptId")Integer deptId,
                                       @Param("levelName") Integer levelName,
                                       @Param("inputDate")String inputDate,
                                       @Param("likeName")String likeName,
                                       @Param("pageIndex") Integer pageIndex,
                                       @Param("pageSize") Integer pageSize);

    /**
     * 查询宣传报道该宣传报道的所有警员
     * @param id
     * @return
     */
    List<UserDO> findPublicityInfoPoliceById(Integer id);

    /**
     * 列表
     * @return
     */
    Integer findPageListCount(Integer typeId, Integer deptId, Integer levelName, String inputDate, String likeName);









}