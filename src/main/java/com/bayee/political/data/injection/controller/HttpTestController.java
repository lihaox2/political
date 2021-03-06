package com.bayee.political.data.injection.controller;

import com.bayee.political.data.injection.service.HttpTestService;
import com.bayee.political.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/12/8 13:46
 */
@RestController
@RequestMapping("/http/users")
public class HttpTestController {

    @Resource
    private HttpTestService service;

    /**
     * 获取部门接口 信息
     * @return
     */
    @GetMapping("/deps")
    public JsonResult<?> deps(){
        return service.deps();
    }

    /**
     * 获取用户数据信息
     */
    @GetMapping("list")
    public JsonResult<?> list(){
        return service.list();
    }


    /**
     * 认证接口
     */
    @GetMapping("authentication")
    public String authentication(String REQUESTID,String TOKEN,String FHLX){
        return service.authentication(REQUESTID,TOKEN,FHLX);
    }


}
