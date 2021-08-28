package com.bayee.political.mapper;

import com.bayee.political.domain.PublicityType;

import java.util.List;

public interface PublicityTypeMapper {


    /**
     * 查询所有宣传类型
     * @return
     */
    List<PublicityType> findAllPublicityType();




}