package com.bayee.political.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bayee.political.domain.*;
import com.bayee.political.json.GeneralPromotionResult;
import com.bayee.political.json.LinkageResult;
import com.bayee.political.json.PolicePromotionPageListParam;
import com.bayee.political.json.QuantitativePromotionResult;
import com.bayee.political.mapper.*;
import com.bayee.political.service.PolicePromotionService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;
import com.github.pagehelper.Page;
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
import java.util.*;

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

    @Resource
    private PositionMapper positionMapper;

    private final String HOST = "http://8.136.146.186:9097/static";
//	private final static String HOST = "http://41.190.128.250:8080/static";

    @Override
    public JsonResult<T> pageList(PolicePromotionPageListParam param) throws ParseException {
        if(param.getType()==0){
            List<GeneralPromotionResult> list=new ArrayList<>();
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//            String format = sdf.format(param.getParticularYear());
//            Integer year = Integer.valueOf(format);
//            year-=2;
//            param.setParticularYear(sdf.parse(year.toString()));
            System.out.println(param.getEndTime()+"======================="+param.getBeginTime());
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    GeneralPromotionResult result = new GeneralPromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
                    System.out.println(pageList.get(i).getNowTime()+"================"+pageList.get(i).getLastTime());
                    result.setLastTime(pageList.get(i).getLastTime());
                    result.setNowTime(pageList.get(i).getNowTime());
//                    result.setNowPoliceLevelId(result.getNextPoliceLevelId());
//                    result.setNowPoliceLevelName(result.getNextPoliceLevelName());
//                    Position position = positionMapper.selectByPrimaryKey(result.getNextPoliceLevelId());
//                    Position position1 = positionMapper.selectByPrimaryKey(position.getpId());
//                    result.setNextPoliceLevelId(position1.getId());
//                    result.setNextPoliceLevelName(position1.getPositionName());
//                    result.setInterval(24);
//                    result.setLastTime(result.getNowTime());
//                    result.setIsCivilServant(0);
//                    result.setIsDisciplinaryAction(0);
                    list.add(result);
                }
            }

            PageHandler<GeneralPromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            pageHandler.setTotalCount(list.size());
            pageHandler.setPageIndex(param.getPageIndex());
            pageHandler.setPageSize(param.getPageSize());
            List<GeneralPromotionResult> list1=new ArrayList<>();
            for(int i=((param.getPageIndex()-1)*param.getPageSize());i<list.size();i++){
                list1.add(list.get(i));
            }
            pageHandler.setData(list1);
            JsonResult ok = JsonResult.ok(pageHandler);
            return ok;
        }else {
            List<QuantitativePromotionResult> list=new ArrayList<>();
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    QuantitativePromotionResult result = new QuantitativePromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
//                    result.setRanking(i+1);
//                    result.setNowPoliceLevelId(result.getNextPoliceLevelId());
//                    result.setNowPoliceLevelName(result.getNextPoliceLevelName());
//                    Position position = positionMapper.selectByPrimaryKey(result.getNextPoliceLevelId());
//                    Position position1 = positionMapper.selectByPrimaryKey(position.getpId());
//                    result.setNextPoliceLevelId(position1.getId());
//                    result.setNextPoliceLevelName(position1.getPositionName());
//                    result.setInterval(24);
//                    result.setLastTime(result.getNowTime());
//                    result.setResumeScore(0.00);
//                    result.setHoldOfficeScore(0.00);
//                    result.setTotalScore(0.00);
//                    result.setIsCivilServant(0);
//                    result.setIsDisciplinaryAction(0);
                    list.add(result);
                }
            }

            PageHandler<QuantitativePromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            pageHandler.setTotalCount(list.size());
            pageHandler.setPageIndex(param.getPageIndex());
            pageHandler.setPageSize(param.getPageSize());
            List<QuantitativePromotionResult> list1=new ArrayList<>();
            for(int i=((param.getPageIndex()-1)*param.getPageSize());i<list.size();i++){
                list1.add(list.get(i));
            }
            pageHandler.setData(list1);
            JsonResult ok = JsonResult.ok(pageHandler);
            return ok;
        }
    }

    @Override
    public JsonResult<List<LinkageResult>> rankList() {
        List<LinkageResult> list=new ArrayList<>();
        List<Position> infos = positionMapper.selectList();
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

    @Override
    public JsonResult<T> add() throws ParseException {
        List<PolicePromotionRecordInfo> infos = mapper.selectPromotionList();
        if(infos!=null && infos.size()>0){
           for(PolicePromotionRecordInfo pp:infos){
               Position position = positionMapper.selectByPrimaryKey(pp.getNextPoliceLevelId());
               pp.setNextPoliceLevelName(position.getPositionName());
               pp.setInterval(24);
               SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
               SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
               SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
               String format = sdf.format(pp.getNowTime());
               Integer year = Integer.valueOf(format);
               year-=2;
               String format1 = sdf1.format(pp.getNowTime());
               System.out.println("================"+year.toString()+"-"+format1);
               Date parse = sdf2.parse(year.toString() + "-" + format1);
               pp.setLastTime(parse);
               System.out.println(format1+"==============="+sdf2.format(pp.getLastTime())+"================="+format);
               System.out.println(format1+"==============="+pp.getLastTime()+"================="+format);
               pp.setResumeScore(0.00);
               pp.setHoldOfficeScore(0.00);
               pp.setTotalScore(0.00);
               pp.setIsCivilServant(0);
               pp.setIsDisciplinaryAction(0);
               pp.setType(1);
               pp.setCreateTime(new Date());
               pp.setModifyTime(new Date());
               mapper.insertSelective(pp);
           }
        }
        return JsonResult.ok();
    }
}
