package com.bayee.political.domain.service;

import com.bayee.political.domain.domain.param.PoliceDepartmentParam;
import com.bayee.political.domain.domain.result.PoliceDepartmentResult;

/**
 * @author lichenghu
 * @Title: 获取部门信息的接口
 * @Description: )
 * @date 2021/11/17 10:57
 */
public interface PoliceDepartmentService {
    public PoliceDepartmentResult readDepartment(PoliceDepartmentParam param);
}
