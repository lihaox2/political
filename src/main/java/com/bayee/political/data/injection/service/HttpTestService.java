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
public interface HttpTestService {
    public List<PoliceDepartmentResult> readDepartment(PoliceDepartmentParam param);

    JsonResult<?> list();

    JsonResult<?> deps();

    /**
     * 认证接口
     * @param requestid 请求码
     * @param token 动态token
     * @param fhlx
     * @return
     */
    String authentication(String requestid, String token, String fhlx);
}
