package com.bayee.political.service;

import com.bayee.political.domain.MajorAccessory;

import java.util.List;

/**
 * @author tlt
 * @date 2021/11/4
 */
public interface MajorAccessoryService {

    /**
     * 新增附件记录
     * @param accessory
     */
    void addAccessory(MajorAccessory accessory);

    /**
     * 修改附件记录
     * @param accessory
     */
    void updateAccessory(MajorAccessory accessory);

    /**
     * 查询附件详情
     * @param id
     * @return
     */
    MajorAccessory findById(Integer id);

    /**
     * 以报告ID查询附件
     * @param id
     * @return
     */
    List<MajorAccessory> findByReportId(Integer id);

    /**
     * 以报告ID删除附件
     * @param id
     */
    void deleteByReportId(Integer id);

    /**
     * 删除附件
     * @param id
     */
    void deleteAccessory(Integer id);
}
