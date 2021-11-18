package com.bayee.political.service.impl;

import com.bayee.political.domain.*;
import com.bayee.political.json.GeneralPromotionResult;
import com.bayee.political.json.LinkageResult;
import com.bayee.political.json.PolicePromotionPageListParam;
import com.bayee.political.json.QuantitativePromotionResult;
import com.bayee.political.mapper.DepartmentMapper;
import com.bayee.political.mapper.PolicePositionMapper;
import com.bayee.political.mapper.PolicePromotionRecordInfoMapper;
import com.bayee.political.mapper.PoliceRankInfoMapper;
import com.bayee.political.service.PolicePromotionService;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author lichenghu
 * @Title:
 * @Description: )
 * @date 2021/11/16 18:31
 */
@Service
public class PolicePromotionServiceImpl implements PolicePromotionService {

    @Resource
    private PolicePromotionRecordInfoMapper mapper;

    @Resource
    private PoliceRankInfoMapper rankInfoMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PolicePositionMapper policePositionMapper;

    @Override
    public JsonResult<T> pageList(PolicePromotionPageListParam param) throws ParseException {
        PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        if(param.getType()==0){
            List<GeneralPromotionResult> list=new ArrayList<>();
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            System.out.println("=========================================="+pageList.size());
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    GeneralPromotionResult result = new GeneralPromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
                    list.add(result);
                }
            }
            PageHandler<GeneralPromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            JsonResult ok = JsonResult.ok(pageHandler);
            return ok;
        }else {
            List<QuantitativePromotionResult> list=new ArrayList<>();
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    QuantitativePromotionResult result = new QuantitativePromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
                    result.setRanking(i+1);
                    list.add(result);
                }
            }
            PageHandler<QuantitativePromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            JsonResult ok = JsonResult.ok(pageHandler);
            return ok;
        }
    }

    @Override
    public JsonResult<List<LinkageResult>> rankList() {
        List<LinkageResult> list=new ArrayList<>();
        List<PoliceRankInfo> infos = rankInfoMapper.selectList();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getName());
                result.setValue(e.getId());
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }

    @Override
    public JsonResult<PolicePromotionRecordInfo> info(Integer id) {
        JsonResult<PolicePromotionRecordInfo> ok = JsonResult.ok(mapper.selectByPrimaryKey(id));
        ok.setDesc("查询成功");
        return null;
    }

    @Override
    public JsonResult<List<LinkageResult>> postList() {
        List<LinkageResult> list=new ArrayList<>();
        List<PolicePosition> infos = policePositionMapper.policePositionList();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getPositionName());
                result.setValue(e.getId());
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }

    @Override
    public JsonResult<List<LinkageResult>> depList() {
        List<LinkageResult> list=new ArrayList<>();
        List<Department> infos = departmentMapper.findAll();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getName());
                result.setValue(e.getId());
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }

    @Override
    public JsonResult<T> export() {

        return null;
    }
}
