package com.bayee.political.mapper;

import com.bayee.political.domain.PublicityUserInfo;
import com.bayee.political.pojo.dto.PublicityUserInfoDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PublicityUserInfoMapper {

    /**
     * 新增中间表信息
     * @return
     */
    Integer addPublicityUserInfo(PublicityUserInfo publicityUserInfo);

    /**
     * 根据宣传信息id删除中间表
     * @param id
     * @return
     */
    Integer deletePublicityUserInfo(@Param("id") Integer id);


    /**
     * 根据宣传id查询民警 部门信息
     * @param infoId
     * @return
     */
    List<PublicityUserInfoDO> findPublicityUserInfo(@Param("infoId") Integer infoId);




}
