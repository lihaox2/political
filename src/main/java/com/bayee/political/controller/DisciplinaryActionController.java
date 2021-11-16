package com.bayee.political.controller;

import com.bayee.political.domain.DisciplinaryRecordInfo;
import com.bayee.political.json.DAListPageParam;
import com.bayee.political.json.DisciplinaryActionInfoResult;
import com.bayee.political.service.DisciplinaryActionService;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author lichenghu
 * @Title: 纪律处分
 * @Description: )
 * @date 2021/11/15 14:17
 */
@RestController
@RequestMapping("/disciplinary")
public class DisciplinaryActionController {

    @Resource
    private DisciplinaryActionService service;

    /**
     * 分页查询纪律处分
     * @return
     */
    @PostMapping("/list/page")
    public JsonResult<PageHandler<DisciplinaryActionInfoResult>> listPage(@RequestBody DAListPageParam param){
      return service.listPage(param);
    }

    /**
     * 纪律处分详情查询
     * @param id 纪律处分id
     * @return
     */
    @GetMapping("/info")
    public JsonResult<DisciplinaryActionInfoResult> info(Integer id){
        return service.info(id);
    }

    /**
     * 纪律处分添加
     * @param info
     * @return
     */
    @PostMapping("add")
    public JsonResult<?> add(@RequestBody DisciplinaryRecordInfo info){
        return service.add(info);
    }

    /**
     * 编辑纪律处分信息
     * @param info
     * @return
     */
    @PostMapping("modify")
    public JsonResult<?> modify(@RequestBody DisciplinaryRecordInfo info){
        return service.modify(info);
    }

    /**
     * 根据纪律处分id删除纪律处分信息
     * @param id
     * @return
     */
    @GetMapping("/del")
    public JsonResult<?> del(Integer id){
        return service.del(id);
    }

    /**
     * 查询全部的纪律处分类型
     * @return
     */
    @GetMapping("type/list")
    public JsonResult<?> typeList(){
        return service.typeList();
    }

    /**
     * 查询全部的纪律处分机关级别
     * @return
     */
    @GetMapping("/level/list")
    public JsonResult<?> levelList(){
        return service.levelList();
    }

    /**
     * 查询全部的纪律处理机关
     * @return
     */
    @GetMapping("/office/list")
    public JsonResult<?> officeList(){
        return service.officeList();
    }
}
