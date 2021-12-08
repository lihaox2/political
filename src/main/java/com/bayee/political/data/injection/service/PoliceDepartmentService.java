package com.bayee.political.data.injection.service;

import com.bayee.political.data.injection.domain.param.PoliceDepartmentParam;
import com.bayee.political.data.injection.domain.result.PoliceDepartmentResult;
import com.bayee.political.utils.JsonResult;

import java.util.List;

/**
 * @author lichenghu
 * @Title: 获取部门信息的接口
 * @Description: )
 * @date 2021/11/17 10:57
 */
public interface PoliceDepartmentService {
    public List<PoliceDepartmentResult> readDepartment(PoliceDepartmentParam param);

    JsonResult<?> list();

    JsonResult<?> deps();
}
