package com.bayee.political.service.impl;

import com.bayee.political.domain.DisciplinaryActionLevelInfo;
import com.bayee.political.domain.DisciplinaryActionOfficeInfo;
import com.bayee.political.domain.DisciplinaryActionTypeInfo;
import com.bayee.political.domain.DisciplinaryRecordInfo;
import com.bayee.political.json.DAListPageParam;
import com.bayee.political.json.DisciplinaryActionInfoResult;
import com.bayee.political.json.LinkageResult;
import com.bayee.political.mapper.DisciplinaryActionLevelInfoMapper;
import com.bayee.political.mapper.DisciplinaryActionOfficeInfoMapper;
import com.bayee.political.mapper.DisciplinaryActionTypeInfoMapper;
import com.bayee.political.mapper.DisciplinaryRecordInfoMapper;
import com.bayee.political.service.DisciplinaryActionService;
import com.bayee.political.utils.JsonResult;
import com.bayee.political.utils.PageHandler;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author lichenghu
 * @Title: 纪律处分·
 * @Description: )
 * @date 2021/11/15 14:20
 */
@Service
public class DisciplinaryActionServiceImpl implements DisciplinaryActionService {

    @Resource
    private DisciplinaryRecordInfoMapper mapper;

    @Resource
    private DisciplinaryActionTypeInfoMapper typeInfoMapper;

    @Resource
    private DisciplinaryActionLevelInfoMapper levelInfoMapper;

    @Resource
    private DisciplinaryActionOfficeInfoMapper officeInfoMapper;

    /**
     *
     * 分页查询纪律处分
     * @param param
     * @return
     */
    @Override
    public JsonResult<PageHandler<DisciplinaryActionInfoResult>> listPage(DAListPageParam param) {
        param.setKeyword("%"+param.getKeyword()+"%");
        PageHelper.startPage(param.getPageIndex(),param.getPageSize());
        PageHandler<DisciplinaryActionInfoResult> pageHandler = new PageHandler<>(new PageInfo<>(mapper.listPage(param)));
        JsonResult<PageHandler<DisciplinaryActionInfoResult>> ok = JsonResult.ok(pageHandler);
        ok.setDesc("查询成功");
        return ok;
    }

    /**
     * 纪律处分详情查询
     * @param id 纪律处分id
     * @return
     */
    @Override
    public JsonResult<DisciplinaryActionInfoResult> info(Integer id) {
        DisciplinaryActionInfoResult info = mapper.info(id);
        JsonResult<DisciplinaryActionInfoResult> ok = JsonResult.ok(info);
        ok.setDesc("查询成功");
        return ok;
    }

    /**
     * 纪律处分添加
     * @param info
     * @return
     */
    @Override
    public JsonResult<?> add(DisciplinaryRecordInfo info) {
        info.setCreateTime(new Date());
        info.setModifyTime(new Date());
        int i = mapper.insertSelective(info);
        if(i<1){
            return JsonResult.error("添加失败");
        }
        JsonResult ok = JsonResult.ok();
        ok.setDesc("添加成功");
        return ok;
    }

    /**
     * 编辑纪律处分信息
     * @param info
     * @return
     */
    @Override
    public JsonResult<?> modify(DisciplinaryRecordInfo info) {
        if(info.getId()==null){
            return JsonResult.error("纪律处分id不能为null");
        }
        info.setModifyTime(new Date());
        int update = mapper.updateByPrimaryKeySelective(info);
        if(update<1){
            return JsonResult.error("编辑失败");
        }
        JsonResult ok = JsonResult.ok();
        ok.setDesc("编辑成功");
        return ok;
    }

    /**
     * 根据纪律处分id删除纪律处分信息
     * @param id
     * @return
     */
    @Override
    public JsonResult<?> del(Integer id) {
        int i = mapper.deleteByPrimaryKey(id);
        if(i<1){
            return JsonResult.error("删除失败");
        }
        JsonResult ok = JsonResult.ok();
        ok.setDesc("删除成功");
        return ok;
    }

    /**
     * 查询全部的纪律处分类型
     * @return
     */
    @Override
    public JsonResult<List<LinkageResult>> typeList() {
        List<LinkageResult> list=new ArrayList<>();
        List<DisciplinaryActionTypeInfo> infos = typeInfoMapper.selectList();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getTitle());
                result.setValue(e.getId());
                List<DisciplinaryActionTypeInfo> typeInfos = typeInfoMapper.selectParentList(e.getId());
                if(typeInfos!=null && typeInfos.size()>0){
                    List<LinkageResult> linkageResults=new ArrayList<>();
                    typeInfos.stream().forEach(s->{
                        LinkageResult result1 = new LinkageResult();
                        result1.setLabel(s.getTitle());
                        result1.setValue(s.getId());
                        linkageResults.add(result1);
                    });
                    result.setChildren(linkageResults);
                }
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }

    /**
     * 查询全部的纪律处分机关级别
     * @return
     */
    @Override
    public JsonResult<List<LinkageResult>> levelList() {
        List<LinkageResult> list=new ArrayList<>();
        List<DisciplinaryActionLevelInfo> infos = levelInfoMapper.selectList();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getTitle());
                result.setValue(e.getId());
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }

    /**
     * 查询全部的纪律处理机关
     * @return
     */
    @Override
    public JsonResult<List<LinkageResult>> officeList() {
        List<LinkageResult> list=new ArrayList<>();
        List<DisciplinaryActionOfficeInfo> infos = officeInfoMapper.selectList();
        if(infos!=null && infos.size()>0){
            infos.stream().forEach(e->{
                LinkageResult result = new LinkageResult();
                result.setLabel(e.getTitle());
                result.setValue(e.getId());
                list.add(result);
            });
        }
        JsonResult<List<LinkageResult>> ok = JsonResult.ok(list);
        ok.setDesc("查询成功");
        return ok;
    }
}
