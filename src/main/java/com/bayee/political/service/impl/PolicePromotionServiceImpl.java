package com.bayee.political.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
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
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    private final String HOST = "http://8.136.146.186:9097/static";
//	private final static String HOST = "http://41.190.128.250:8080/static";

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
    public JsonResult<T> export(Integer type) {
        List<PolicePromotionRecordInfo> infos = mapper.SelectExport(type);
        List<Map<String,Object>> list=new ArrayList<>();
        System.out.println("================"+infos.size());
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                Map<String,Object> map=new HashMap<>();
                System.out.println("----------------"+e.getName());
                map.put("id",e.getId());
                map.put("name",e.getName());
                map.put("policeId",e.getPoliceId());
                map.put("depName",e.getDepName());
                map.put("postName",e.getPostName());
                map.put("nowPoliceLevelName",e.getNowPoliceLevelName());
                map.put("nextPoliceLevelName",e.getNextPoliceLevelName());
                map.put("lastTime",e.getLastTime());
                map.put("nowTime",e.getNowTime());
                map.put("interval",e.getInterval());
                if(type==1){
                    map.put("resumeScore",e.getResumeScore());
                    map.put("holdOfficeScore",e.getHoldOfficeScore());
                    map.put("totalScore",e.getTotalScore());
                }else {
                    map.put("isCivilServant",e.getIsCivilServant()==0?"否":"是");
                    map.put("isDisciplinaryAction",e.getIsDisciplinaryAction()==0?"否":"是");
                }
                list.add(map);
            });
//            list.remove(0);
            try {
                File file = new File("/mnt/qiantang/policeInfo/警员晋升花名册.xlsx");
                file.delete();
                //通过工具类创建writer
                ExcelWriter writer = ExcelUtil.getWriter("/mnt/qiantang/policeInfo/警员晋升花名册.xlsx");
                //跳过当前行，既第一行，非必须，在此演示用
                writer.passCurrentRow();
                writer.addHeaderAlias("id","序列");
                writer.addHeaderAlias("name","姓 名");
                writer.addHeaderAlias("policeId","警 号");
                writer.addHeaderAlias("depName","部门名称");
                writer.addHeaderAlias("postName","警员职务");
                writer.addHeaderAlias("nowPoliceLevelName","当前警员级别名称");
                writer.addHeaderAlias("nextPoliceLevelName","晋升的警员级别名称");
                writer.addHeaderAlias("lastTime","上一次晋升的时间");
                writer.addHeaderAlias("nowTime","现在晋升时间");
                writer.addHeaderAlias("interval","距离上一次晋升的时间");
                if(type==1){
                    writer.addHeaderAlias("resumeScore","履历分");
                    writer.addHeaderAlias("holdOfficeScore","任职分");
                    writer.addHeaderAlias("totalScore","考评总分");
                }else {
                    writer.addHeaderAlias("isCivilServant","是否被评为公务员");
                    writer.addHeaderAlias("isDisciplinaryAction","是否被纪律处分");
                }
                //合并单元格后的标题行，使用默认标题样式
                writer.merge(13, "警员晋升花名册");
                //一次性写出内容，强制输出标题
                ExcelWriter write = writer.write(list,true);

                //关闭writer，释放内存
                writer.close();
                JsonResult ok = JsonResult.ok(HOST + "/policeInfo/警员晋升花名册.xlsx");
                return ok;
            }catch (Exception e){
                return JsonResult.error("异常报错");
            }
        }
        JsonResult ok = JsonResult.ok(HOST + "/policeInfo/警员晋升花名册.xlsx");
        return ok;
    }
}
