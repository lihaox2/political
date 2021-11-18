package com.bayee.political.controller;

import com.bayee.political.domain.PolicePromotionRecordInfo;
import com.bayee.political.json.LinkageResult;
import com.bayee.political.json.PolicePromotionPageListParam;
import com.bayee.political.service.PolicePromotionService;
import com.bayee.political.utils.JsonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.text.ParseException;
import java.util.List;

/**
 * @author lichenghu
 * @Title: 警员晋升
 * @Description: )
 * @date 2021/11/16 11:09
 */
@Api(tags = "警员晋升")
@RestController
@RequestMapping("/police/promotion")
public class PolicePromotionController {

    @Resource
    private PolicePromotionService service;

    @ApiOperation("警员晋升分页查询")
    @PostMapping("/page/list")
    public JsonResult<T> pageList(@RequestBody PolicePromotionPageListParam param) throws ParseException {
        return service.pageList(param);
    }

    @ApiOperation("查询晋升等级")
    @GetMapping("rank/list")
    public JsonResult<List<LinkageResult>> rankList(){
        return service.rankList();
    }

    @ApiOperation("查询部门")
    @GetMapping("/dep/list")
    private JsonResult<List<LinkageResult>> depList(){
        return service.depList();
    }

    @ApiOperation("职位部门")
    @GetMapping("/post/list")
    private JsonResult<List<LinkageResult>> postList(){
        return service.postList();
    }

    @ApiOperation("查询详情")
    @ApiImplicitParam(name = "id",value = "id",dataType = "Integer",required = true)
    @GetMapping("info")
    public JsonResult<PolicePromotionRecordInfo> info(Integer id){
        return service.info(id);
    }

    @ApiOperation("导出的接口")
    @GetMapping("/export")
    public  JsonResult<T> export(){
        return service.export();
    }
}
