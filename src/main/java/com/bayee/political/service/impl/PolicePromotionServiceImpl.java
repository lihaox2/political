package com.bayee.political.service.impl;

import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.bayee.political.domain.*;
import com.bayee.political.json.*;
import com.bayee.political.mapper.*;
import com.bayee.political.service.PolicePromotionService;
import com.bayee.political.utils.DataListReturn;
import com.bayee.political.utils.HttpIPUtil;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import javax.annotation.Resource;
import javax.xml.crypto.Data;
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
    private UserMapper userMapper;

    @Resource
    private PoliceRankInfoMapper rankInfoMapper;

    @Resource
    private DepartmentMapper departmentMapper;

    @Resource
    private PolicePositionMapper policePositionMapper;

    @Resource
    private PositionMapper positionMapper;

//    @Resource
//    private JobUpdateRecordInfoMapper jobUpdateRecordInfoMapper;


    @Override
    public JsonResult<T> pageList(PolicePromotionPageListParam param) throws ParseException {
        List<PolicePromotionRecordResult> results = mapper.selectListPage(param.getParticularYear());
        if(param.getType()==1){
            List<QuantitativePromotionResult> list=new ArrayList<>();
            if(results!=null && results.size()>0){
                for(PolicePromotionRecordResult ppr:results){
                    Integer stage=24;
                    stage+=ppr.getSum()==null?0:ppr.getSum();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
                    String nowTime = sdf.format(ppr.getNowTime());
                    String nowYear=sdf.format(param.getParticularYear());
                    String monthNowTime = sdf1.format(ppr.getNowTime());
                    //?????????
                    Integer year = Integer.valueOf(nowTime);
                    Integer sum=stage/12;
                    Integer yearI=year+sum;
                    //?????????
                    Integer sumI=stage%12;
                    Integer monthSum = Integer.valueOf(monthNowTime);
                    Integer month=monthSum+sumI;
                    if(month>12){
                        yearI+=1;
                        month-=12;
                    }
                    String months=month.toString();
                    if(month<10){
                        months="0"+months;
                    }
                    String years=yearI+"-"+months;
                    Date parse = sdf2.parse(years);
                    if(param.getQuarter()==1){
                        if(years.equals(nowYear+"-01") || years.equals(nowYear+"-02") || years.equals(nowYear+"-03")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getQuantitativePromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }

                    if(param.getQuarter()==2){
                        if(years.equals(nowYear+"-04") || years.equals(nowYear+"-05") || years.equals(nowYear+"-06")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getQuantitativePromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                    if(param.getQuarter()==3){
                        if(years.equals(nowYear+"-07") || years.equals(nowYear+"-08") || years.equals(nowYear+"-09")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getQuantitativePromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                    if(param.getQuarter()==4){
                        if(years.equals(nowYear+"-10") || years.equals(nowYear+"-11") || years.equals(nowYear+"-12")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getQuantitativePromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                }
            }


            List<QuantitativePromotionResult> resultList = getQuantitativePromotionResultList(param);
            if(resultList!=null && resultList.size()>0){
                for(QuantitativePromotionResult gp:resultList){
                    list.add(gp);
                }
            }

            for(int i=0;i<list.size()-1;i++) {
                for (int j = 0; j < list.size()-1 - i; j++) {
                    if (list.get(j).getTotalScore() < list.get(j + 1).getTotalScore()) {
                        {
                            QuantitativePromotionResult result = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, result);
                        }
                    }
                }
            }
            PageHandler<QuantitativePromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            pageHandler.setTotalCount(list.size());
            pageHandler.setPageIndex(param.getPageIndex());
            pageHandler.setPageSize(param.getPageSize());
            List<QuantitativePromotionResult> list1=new ArrayList<>();
            for(int i=((param.getPageIndex()-1)*param.getPageSize());i<param.getPageIndex()*param.getPageSize();i++){
                if(i<list.size()){
                    QuantitativePromotionResult result = list.get(i);
                    result.setRanking(i+1);
                    list1.add(result);
                }
            }
            pageHandler.setData(list1);
            JsonResult ok = JsonResult.ok(pageHandler);
            return ok;
        }else {
            List<GeneralPromotionResult> list=new ArrayList<>();
            if(results!=null && results.size()>0){
                for(PolicePromotionRecordResult ppr:results){
                    Integer stage=24;
                    stage+=ppr.getSum()==null?0:ppr.getSum();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                    SimpleDateFormat sdf1 = new SimpleDateFormat("MM");
                    SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM");
                    String nowTime = sdf.format(ppr.getNowTime());
                    String nowYear=sdf.format(param.getParticularYear());
                    String monthNowTime = sdf1.format(ppr.getNowTime());
                    //?????????
                    Integer year = Integer.valueOf(nowTime);
                    Integer sum=stage/12;
                    Integer yearI=year+sum;
                    //?????????
                    Integer sumI=stage%12;
                    Integer monthSum = Integer.valueOf(monthNowTime);
                    Integer month=monthSum+sumI;
                    if(month>12){
                        yearI+=1;
                        month-=12;
                    }
                    String months=month.toString();
                    if(month<10){
                        months="0"+months;
                    }
                    String years=yearI+"-"+months;
                    Date parse = sdf2.parse(years);
                    if(param.getQuarter()==1){
                        if(years.equals(nowYear+"-01") || years.equals(nowYear+"-02") || years.equals(nowYear+"-03")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getGeneralPromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }

                    if(param.getQuarter()==2){
                        if(years.equals(nowYear+"-04") || years.equals(nowYear+"-05") || years.equals(nowYear+"-06")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getGeneralPromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                    if(param.getQuarter()==3){
                        if(years.equals(nowYear+"-07") || years.equals(nowYear+"-08") || years.equals(nowYear+"-09")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getGeneralPromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                    if(param.getQuarter()==4){
                        if(years.equals(nowYear+"-10") || years.equals(nowYear+"-11") || years.equals(nowYear+"-12")){
                            Date parse1 = sdf2.parse(years);
                            list.add(getGeneralPromotionResult(ppr,parse1,stage,param.getParticularYear()));
                        }
                    }
                }
            }
            List<GeneralPromotionResult> resultList = getGeneralPromotionResultList(param);
            if(resultList!=null && resultList.size()>0){
                for(GeneralPromotionResult gp:resultList){
                    list.add(gp);
                }
            }

            for(int i=0;i<list.size()-1;i++) {
                for (int j = 0; j < list.size()-1 - i; j++) {
                    if (list.get(j).getIsCivilServant() < list.get(j + 1).getIsCivilServant()) {
                        {
                            GeneralPromotionResult result = list.get(j);
                            list.set(j, list.get(j + 1));
                            list.set(j + 1, result);
                        }
                    }
                }
            }
            PageHandler<GeneralPromotionResult> pageHandler = new PageHandler<>(new PageInfo<>(list));
            pageHandler.setTotalCount(list.size());
            pageHandler.setPageIndex(param.getPageIndex());
            pageHandler.setPageSize(param.getPageSize());
            List<GeneralPromotionResult> list1=new ArrayList<>();
            for(int i=((param.getPageIndex()-1)*param.getPageSize());i<param.getPageIndex()*param.getPageSize();i++){
                if(i<list.size()){
                    list1.add(list.get(i));
                }
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
        ok.setDesc("????????????");
        return ok;
    }

    @Override
    public JsonResult<PolicePromotionInfoResult> info(String policeId) {
        PolicePromotionInfoResult result = new PolicePromotionInfoResult();
        List<PolicePromotionSuccessivePostsResult> recordInfos = mapper.selectSuccessivePosts(policeId);
        result.setPosts(recordInfos);
        PolicePromotionBasicUserInfoResult info = mapper.selectPolicePromotionInfo(policeId);
        Position position = positionMapper.selectByPrimaryKey(info.getPolicePosition());
        Position position1 = positionMapper.selectByPrimaryKey(position.getpId());
        info.setNextPoliceLevelId(position1.getId());
        info.setNextPoliceLevelName(position1.getPositionName());
        result.setUserInfo(info);
        JsonResult<PolicePromotionInfoResult> ok = JsonResult.ok(result);
        ok.setDesc("????????????");
        return ok;
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
        ok.setDesc("????????????");
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
        ok.setDesc("????????????");
        return ok;
    }

    @Override
    public JsonResult<T> export(PolicePromotionPageListParam param) {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        String year = sdf1.format(param.getParticularYear());
        List<PolicePromotionRecordInfo> infos = mapper.SelectExports(year,param.getType());
        System.out.println("==========="+param.getType());
        System.out.println("======================"+year);
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
                if(param.getType()==1){
                    map.put("resumeScore",e.getResumeScore());
                    map.put("holdOfficeScore",e.getHoldOfficeScore());
                    map.put("totalScore",e.getTotalScore());
                }else {
                    map.put("isCivilServant",e.getIsCivilServant()==0?"???":"???");
                    map.put("isDisciplinaryAction",e.getIsDisciplinaryAction()==0?"???":"???");
                }
                list.add(map);
            });
//            list.remove(0);
            try {
                File file = new File("/mnt/qiantang/policeInfo/?????????????????????.xlsx");
//                File file = new File("D:\\?????????????????????.xlsx");
                file.delete();
                //?????????????????????writer
                ExcelWriter writer = ExcelUtil.getWriter("/mnt/qiantang/policeInfo/?????????????????????.xlsx");
//                ExcelWriter writer = ExcelUtil.getWriter("D:\\?????????????????????.xlsx");
                //????????????????????????????????????????????????????????????
                writer.passCurrentRow();
                writer.addHeaderAlias("id","??????");
                writer.addHeaderAlias("name","??? ???");
                writer.addHeaderAlias("policeId","??? ???");
                writer.addHeaderAlias("depName","????????????");
                writer.addHeaderAlias("postName","????????????");
                writer.addHeaderAlias("nowPoliceLevelName","????????????????????????");
                writer.addHeaderAlias("nextPoliceLevelName","???????????????????????????");
                writer.addHeaderAlias("lastTime","????????????????????????");
                writer.addHeaderAlias("nowTime","??????????????????");
                writer.addHeaderAlias("interval","??????????????????????????????");
                if(param.getType()==1){
                    writer.addHeaderAlias("resumeScore","?????????");
                    writer.addHeaderAlias("holdOfficeScore","?????????");
                    writer.addHeaderAlias("totalScore","????????????");
                }else {
                    writer.addHeaderAlias("isCivilServant","????????????????????????");
                    writer.addHeaderAlias("isDisciplinaryAction","?????????????????????");
                }
                //?????????????????????????????????????????????????????????
                writer.merge(13, "?????????????????????");
                //??????????????????????????????????????????
                ExcelWriter write = writer.write(list,true);

                //??????writer???????????????
                writer.close();
                JsonResult ok = JsonResult.ok(HttpIPUtil.HOST + "/policeInfo/?????????????????????.xlsx");
                return ok;
            }catch (Exception e){
                return JsonResult.error("????????????");
            }
        }
        JsonResult ok = JsonResult.ok(HttpIPUtil.HOST + "/policeInfo/?????????????????????.xlsx");
        return ok;
    }

    @Override
    @Transactional
    public JsonResult<T> add(String policeId,Integer type) throws ParseException {
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy");
        String format = sdf1.format(new Date());
        Date parse1 = sdf1.parse(format);
        List<PolicePromotionRecordInfo> byis = mapper.findByis(policeId, parse1);
        if(byis==null || byis.size()<1){
            PolicePromotionRecordInfo pp = mapper.selectPromotionInfos(policeId);
            if(pp!=null){
                Position position = positionMapper.selectByPrimaryKey(pp.getNextPoliceLevelId());
                pp.setNextPoliceLevelName(position.getPositionName());
                pp.setInterval(24);
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String format1 = sdf2.format(new Date());
                Date parse = sdf2.parse( format1);
                PolicePromotionBasicUserInfoResult info = mapper.selectPolicePromotionInfo(policeId);
                pp.setNextPoliceLevelName(pp.getNowPoliceLevelName());
                pp.setNextPoliceLevelId(pp.getNowPoliceLevelId());
                Position position1 = positionMapper.findByPrimary(pp.getNowPoliceLevelId());
                Position position2 = positionMapper.selectByPrimaryKey(position1.getId());
                pp.setNowPoliceLevelId(position2.getId());
                pp.setNowPoliceLevelName(position2.getPositionName());
                pp.setLastTime(pp.getNowTime());
                pp.setNowTime(parse);
                pp.setType(type);
                if(type==1){
                    //?????????
                    pp.setResumeScore(resume(parse1,pp.getPoliceId()));
                    //?????????
                    pp.setHoldOfficeScore(holdOffice(parse1,pp.getPoliceId()));
                    pp.setTotalScore(pp.getResumeScore()+pp.getHoldOfficeScore());
                }
                //????????????????????????
                pp.setIsCivilServant(isCivilServant(parse1,pp.getPoliceId())==true?1:0);
                //?????????????????????
                pp.setIsDisciplinaryAction(isPromotion(parse1,pp.getPoliceId())==true?1:0);
                pp.setCreateTime(new Date());
                pp.setModifyTime(new Date());
                mapper.insertSelective(pp);
                User ids = userMapper.findByPoliceIds(policeId);
                ids.setPolicePosition(pp.getNowPoliceLevelId());
                ids.setPolicePositionAssignDate(pp.getNowTime());
                userMapper.updateByPrimaryKeySelective(ids);
            }
            return JsonResult.ok();
        }else {
            return JsonResult.error("??????????????????");
        }
    }

    @Override
    public JsonResult<T> adds() throws ParseException {
        List<PolicePromotionRecordInfo> infos = mapper.selectPromotionList();
        if(infos!=null && infos.size()>0){
            for(PolicePromotionRecordInfo pp:infos){
//                Position position = positionMapper.selectByPrimaryKey(pp.getNextPoliceLevelId());
//                pp.setNextPoliceLevelName(position.getPositionName());
                Position position = positionMapper.findByPrimary(pp.getNowPoliceLevelId());
//                positionMapper.selectByPrimaryKey(position.getId());
                pp.setNowPoliceLevelId(position.getId());
                pp.setNextPoliceLevelName(position.getPositionName());
                pp.setInterval(24);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
                SimpleDateFormat sdf1 = new SimpleDateFormat("MM-dd");
                SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy-MM-dd");
                String format = sdf.format(pp.getNowTime());
                Integer year = Integer.valueOf(format);
                year-=2;
                String format1 = sdf1.format(pp.getNowTime());
                Date parse = sdf2.parse(year.toString() + "-" + format1);
                pp.setLastTime(parse);
                pp.setResumeScore(0.00);
                pp.setHoldOfficeScore(0.00);
                pp.setTotalScore(0.00);
                pp.setIsCivilServant(0);
                pp.setIsDisciplinaryAction(0);
                pp.setType(0);
                pp.setCreateTime(new Date());
                pp.setModifyTime(new Date());
                mapper.insertSelective(pp);
            }
        }
        return JsonResult.ok();
    }



    public QuantitativePromotionResult getQuantitativePromotionResult(PolicePromotionRecordResult ppr,Date date,Integer stage,Date year) throws ParseException {
        QuantitativePromotionResult result = new QuantitativePromotionResult();
        BeanUtils.copyProperties(ppr,result);
        result.setNowTime(date);
        result.setLastTime(ppr.getNowTime());
        result.setNextPoliceLevelId(ppr.getNowPoliceLevelId());
        result.setNextPoliceLevelName(ppr.getNowPoliceLevelName());
        Position position = positionMapper.selectByPrimaryKey(result.getNextPoliceLevelId());
        Position position1 = positionMapper.selectByPrimaryKey(position.getpId());
        result.setNowPoliceLevelId(position1.getId());
        result.setNowPoliceLevelName(position1.getPositionName());
        result.setInterval(stage);
        result.setIsPromotion(0);
        //?????????
        result.setResumeScore(resume(year,ppr.getPoliceId()));
        //?????????
        result.setHoldOfficeScore(holdOffice(year,ppr.getPoliceId()));
        result.setTotalScore(result.getResumeScore()+result.getHoldOfficeScore());
        //????????????????????????
        result.setIsCivilServant(isCivilServant(year,ppr.getPoliceId())==true?1:0);
        //?????????????????????
        result.setIsDisciplinaryAction(isPromotion(year,ppr.getPoliceId())==true?1:0);
        return result;
    }

    public GeneralPromotionResult getGeneralPromotionResult(PolicePromotionRecordResult ppr,Date date,Integer stage,Date year) throws ParseException {
        GeneralPromotionResult result = new GeneralPromotionResult();
        BeanUtils.copyProperties(ppr,result);
        result.setNowTime(date);
        result.setLastTime(ppr.getNowTime());
        result.setNextPoliceLevelId(ppr.getNowPoliceLevelId());
        result.setNextPoliceLevelName(ppr.getNowPoliceLevelName());
        Position position = positionMapper.selectByPrimaryKey(result.getNextPoliceLevelId());
        Position position1 = positionMapper.selectByPrimaryKey(position.getpId());
        result.setNowPoliceLevelId(position1.getId());
        result.setNowPoliceLevelName(position1.getPositionName());
        result.setInterval(stage);
        result.setIsPromotion(0);
        //????????????????????????
        result.setIsCivilServant(isCivilServant(year,ppr.getPoliceId())==true?1:0);
        //?????????????????????
        result.setIsDisciplinaryAction(isPromotion(year,ppr.getPoliceId())==true?1:0);
        return result;
    }

    /**
     * ???????????????
     * @return
     */
    public Double resume(Date year,String policeId){
        Double score=0.00;
        UserWorkingHoursResult hours = mapper.selectUserWorkingHours(policeId);
        if(hours!=null){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            String nowYear = sdf.format(year);
            System.out.println("==================="+hours.getPoliceId());
            System.out.println("==================="+hours.getWorkingStartDate());
            String hoursYear = sdf.format(hours.getWorkingStartDate());
            score+=Double.valueOf(Integer.valueOf(nowYear)-Integer.valueOf(hoursYear))*2;
            List<PolicePromotionsResult> list = mapper.selectPoliceDisciplinaryActions(policeId);
            Integer promotionScore= list.size()*2;
            score-=Double.valueOf(promotionScore);
        }
        return Double.valueOf(String.format("%.2f",score));
    }

    /**
     * ??????????????????????????????
     */
    private boolean isCivilServant(Date year,String policeId) throws ParseException {
        List<Date> list=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        list.add(year);
        String years = sdf.format(year);
        Integer num1=Integer.valueOf(years)-1;
        Integer num2=Integer.valueOf(years)-2;
        list.add(sdf.parse(num1.toString()));
        list.add(sdf.parse(num2.toString()));
        List<RiskHonour> riskHonours = mapper.selectIsCivilServant(policeId, list);
        if(riskHonours!=null && riskHonours.size()>0){
            return true;
        }else {
            return false;
        }
    }


    /**
     * ??????????????????????????????
     */
    private boolean isPromotion(Date year,String policeId) throws ParseException {
        List<Date> list=new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        list.add(year);
        String years = sdf.format(year);
        Integer num1=Integer.valueOf(years)-1;
        Integer num2=Integer.valueOf(years)-2;
        list.add(sdf.parse(num1.toString()));
        list.add(sdf.parse(num2.toString()));
        List<RiskHonour> riskHonours = mapper.selectIsCivilServant(policeId, list);
        if(riskHonours!=null && riskHonours.size()>0){
            return true;
        }else {
            return false;
        }
    }

    /**
     * ???????????????
     */
    private Double holdOffice(Date year,String policeId) throws ParseException {
//        List<JobUpdateRecordInfo> infos = jobUpdateRecordInfoMapper.selectPoliceIds(policeId);
        Double score=0.00;
//        if(infos!=null && infos.size()>0){
//           for(JobUpdateRecordInfo ju:infos){
//               SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
//               String format = sdf.format(year);
//               String format1 = sdf.format(ju.getHoldOfficeTime());
//               Integer yearNum = Integer.valueOf(format);
//               Integer yearNum1 = Integer.valueOf(format1);
//               Date parse = sdf.parse(format1);
//               if(yearNum1<=yearNum){
//                   List<DisciplinaryRecordInfo> recordInfos = disciplinaryRecordInfoMapper.selectPoliceId(policeId, parse);
//                   if(recordInfos==null || recordInfos.size()<=0){
//                       score+=1;
//                   }
//               }
//
//           }
//        }
        return Double.valueOf(String.format("%.2f",score));
    }

    public List<GeneralPromotionResult> getGeneralPromotionResultList(PolicePromotionPageListParam param){
        Date particularYear = param.getParticularYear();
            List<GeneralPromotionResult> list=new ArrayList<>();
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    GeneralPromotionResult result = new GeneralPromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
                    result.setIsPromotion(1);
                    list.add(result);
                }
            }
            return list;
    }

    public List<QuantitativePromotionResult> getQuantitativePromotionResultList(PolicePromotionPageListParam param){

        List<QuantitativePromotionResult> list=new ArrayList<>();
        List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
        if(pageList!=null && pageList.size()>0){
            for(int i=0;i<pageList.size();i++){
                QuantitativePromotionResult result = new QuantitativePromotionResult();
                BeanUtils.copyProperties(pageList.get(i),result);
                result.setIsPromotion(1);
                list.add(result);
            }
        }
        return list;
    }

    /**
     * ???????????????
     * @param param
     * @return
     * @throws ParseException
     */
    public JsonResult<T> pageLists(PolicePromotionPageListParam param) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM");
        SimpleDateFormat sdf2 = new SimpleDateFormat("MM");
        Date particularYear = param.getParticularYear();
        if(param.getType()==0){
            List<GeneralPromotionResult> list=new ArrayList<>();
            String format = sdf.format(param.getParticularYear());
            Integer year = Integer.valueOf(format);
            year-=2;
            param.setParticularYear(sdf.parse(year.toString()),param.getQuarter());
            System.out.println(param.getEndTime()+"======================="+param.getBeginTime());
            System.out.println(param.getParticularYear()+"=======================");
            List<PolicePromotionRecordInfo> pageList = mapper.selectPageList(param);
            if(pageList!=null && pageList.size()>0){
                for(int i=0;i<pageList.size();i++){
                    GeneralPromotionResult result = new GeneralPromotionResult();
                    BeanUtils.copyProperties(pageList.get(i),result);
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
            String format = sdf.format(param.getParticularYear());
            Integer year = Integer.valueOf(format);
            year-=2;
            param.setParticularYear(sdf.parse(year.toString()),param.getQuarter());
            System.out.println(param.getEndTime()+"======================="+param.getBeginTime());
            System.out.println(param.getParticularYear()+"=======================");
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
}
