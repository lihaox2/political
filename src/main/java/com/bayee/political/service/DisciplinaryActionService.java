package com.bayee.political.service;

import com.bayee.political.domain.DisciplinaryRecordInfo;
import com.bayee.political.json.DAListPageParam;
import com.bayee.political.json.DisciplinaryActionInfoResult;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/15 14:20
 */
public interface DisciplinaryActionService {

    /**
     * 分页查询纪律处分
     * @return
     */
    JsonResult<PageHandler<DisciplinaryActionInfoResult>> listPage(DAListPageParam param);

    /**
     * 纪律处分详情查询
     * @param id 纪律处分id
     * @return
     */
    JsonResult<DisciplinaryActionInfoResult> info(Integer id);

    /**
     * 纪律处分添加
     * @param info
     * @return
     */
    JsonResult<?> add(DisciplinaryRecordInfo info);

    /**
     * 编辑纪律处分信息
     * @param info
     * @return
     */
    JsonResult<?> modify(DisciplinaryRecordInfo info);

    /**
     * 根据纪律处分id删除纪律处分信息
     * @param id
     * @return
     */
    JsonResult<?> del(Integer id);

    /**
     * 查询全部的纪律处分类型
     * @return
     */
    JsonResult<?> typeList();

    /**
     * 查询全部的纪律处分机关级别
     * @return
     */
    JsonResult<?> levelList();

    /**
     * 查询全部的纪律处理机关
     * @return
     */
    JsonResult<?> officeList();
}
