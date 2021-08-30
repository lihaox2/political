package com.bayee.political.controller;

import cn.hutool.core.util.StrUtil;
import com.bayee.political.domain.ExcellentPoliceInfo;
import com.bayee.political.domain.PoliceKind;
import com.bayee.political.domain.PoliceLabel;
import com.bayee.political.json.AddPoliceLabelParam;
import com.bayee.political.json.ExcellentPoliceInfoAddParam;
import com.bayee.political.json.TalentsParticularsResultList;
import com.bayee.political.json.TalentsUserParam;
import com.bayee.political.service.ExcellentPoliceInfoService;
import com.bayee.political.service.PoliceKindService;
import com.bayee.political.service.PoliceLabelService;
import com.bayee.political.service.UserService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/talents")
public class TalentsController {

    @Autowired
    private UserService userService;

    @Autowired
    PoliceKindService policeKindService;

    @Autowired
    private PoliceLabelService policeLabelService;

    @Autowired
    ExcellentPoliceInfoService excellentPoliceInfoService;

    /**
     * 人才库列表
     * @param talentsUserParam
     * @return
     */
    @GetMapping("/find/page/list")
    public ResponseEntity<?> findTalentsPageList(TalentsUserParam talentsUserParam) {
        Map<String, Object> result = new HashMap<>();
        result.put("pageIndex", talentsUserParam.getPageIndex());
        result.put("pageSize", talentsUserParam.getPageSize());
        result.put("data", userService.talentsFindPageList(talentsUserParam));
        result.put("totalCount", userService.talentsFindPageCount(talentsUserParam));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

    /**
     * 添加人才库
     * @param addParam
     * @return
     */
    @PostMapping("/excellent/add")
    public ResponseEntity<?> addExcellentPoliceInfo(@RequestBody ExcellentPoliceInfoAddParam addParam) {
        if (StrUtil.isBlank(addParam.getLabelStr())) {
            return new ResponseEntity<>(DataListReturn.error("请选择标签！"), HttpStatus.OK);
        }
        String[] ids = addParam.getLabelStr().split(",");
        for (int i=0; i<ids.length; i++) {
            PoliceLabel policeLabel = policeLabelService.findById(Integer.valueOf(ids[i]));
            if (policeLabel == null) {
                return new ResponseEntity<>(DataListReturn.error("标签错误！"), HttpStatus.OK);
            }


            Integer count = excellentPoliceInfoService.countByPoliceIdAndCount(addParam.getPoliceId(), Integer.valueOf(ids[i]));
            if (count != null && count >= 1) {
                return new ResponseEntity<>(DataListReturn.error("警员已拥有该标签！"), HttpStatus.OK);
            }

            ExcellentPoliceInfo excellentPoliceInfo = new ExcellentPoliceInfo();
            excellentPoliceInfo.setDesc(addParam.getDesc());
            excellentPoliceInfo.setPoliceId(addParam.getPoliceId());
            excellentPoliceInfo.setLabel(Integer.valueOf(ids[i]));
            excellentPoliceInfo.setScore(policeLabel.getAwardNum());
            excellentPoliceInfo.setCreationDate(new Date());

            excellentPoliceInfoService.insertExcellentPoliceInfo(excellentPoliceInfo);
        }
        return new ResponseEntity<>(DataListReturn.ok(), HttpStatus.OK);
    }

    /**
     * 查询id查询人才库详情
     * @param firstId
     * @param sendId
     * @return
     */
    @GetMapping("/find/user/info")
    public ResponseEntity<?> findTalentsUserInfo(String firstId, String sendId){
        TalentsParticularsResultList talentsUserInfo = userService.findTalentsUserInfo(firstId, sendId);
        if(talentsUserInfo == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(talentsUserInfo), HttpStatus.OK);
    }

    /**
     * 查询所有警种
     * @return
     */
    @GetMapping("/find/police/kind")
    public ResponseEntity<?> findPoliceKind(){
        List<PoliceKind> policeKindList = policeKindService.findAll();
        if(policeKindList == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(policeKindList), HttpStatus.OK);
    }

    /**
     * 查询所有标签
     * @return
     */
    @GetMapping("/find/label/all")
    public ResponseEntity<?> findLabelAll(){
        List<PoliceLabel> policeLabelList = policeLabelService.findLabelAll();
        if(policeLabelList == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(policeLabelList), HttpStatus.OK);
    }

    /**
     * 查询标签
     * @param labelName
     * @return
     */
    @GetMapping("/find/label")
    public ResponseEntity<?> findLabelByName(@RequestParam("labelName") String labelName) {

        return new ResponseEntity<>(DataListReturn.ok(policeLabelService.findLabelByName(labelName)), HttpStatus.OK);
    }

    @PostMapping("/add/policeLabel")
    public ResponseEntity<?> addPoliceLabel(@RequestBody AddPoliceLabelParam labelParam) {
        Integer count = policeLabelService.countByLabelName(labelParam.getLabelName());
        if (count != null && count >= 1) {
            return new ResponseEntity<>(DataListReturn.error("标签已存在！"), HttpStatus.OK);
        }
        if (StrUtil.isBlank(labelParam.getLabelName())) {
            return new ResponseEntity<>(DataListReturn.error("请输入标签名称！"), HttpStatus.OK);
        }

        PoliceLabel policeLabel = new PoliceLabel();
        policeLabel.setAwardNum(1);
        policeLabel.setLabelName(labelParam.getLabelName());
        policeLabel.setCreateTime(new Date());

        policeLabelService.insertPoliceLabel(policeLabel);

        if (policeLabel.getId() == null) {
            return new ResponseEntity<>(DataListReturn.error("新增失败！"), HttpStatus.OK);
        }

        Map<String, Integer> result = new HashMap<>();
        result.put("id", policeLabel.getId());
        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }

}
