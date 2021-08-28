package com.bayee.political.controller;

import com.bayee.political.domain.Department;
import com.bayee.political.domain.PublicityType;
import com.bayee.political.domain.User;
import com.bayee.political.json.PublicityInfoSaveParam;
import com.bayee.political.pojo.dto.DepartmentDO;
import com.bayee.political.pojo.dto.PoliceKindDO;
import com.bayee.political.pojo.dto.UserDO;
import com.bayee.political.service.PoliceKindService;
import com.bayee.political.service.PublicityInfoService;
import com.bayee.political.utils.DataListReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 宣传模块信息
 */
@RestController
public class PublicityInfoController {

    @Autowired
    private PublicityInfoService publicityInfoService; //宣传信息

    @Autowired
    private PoliceKindService policeKindService; //警种


    @InitBinder
    public void initBinder(WebDataBinder binder, WebRequest request) {

        //转换日期
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));// CustomDateEditor为自定义日期编辑器
    }


    /**
     * 点击新增宣传时，查询所有宣传类型
     *
     * @return
     */
    @GetMapping(value = "/find/publicity/type")
    public ResponseEntity<?> findAllPublicType() {
        return new ResponseEntity<>(DataListReturn.ok(publicityInfoService.findAllPublicType()), HttpStatus.OK);
    }


    /**
     * 点击新增宣传时，查询所有民警信息
     *
     * @return
     */
    @GetMapping(value = "/find/user")
    public ResponseEntity<?> findAllUser() {
        return new ResponseEntity<>(DataListReturn.ok(publicityInfoService.findAllUser()), HttpStatus.OK);
    }


    /**
     * 点击新增宣传时，查询所有民警部门信息
     *
     * @return
     */
    @GetMapping(value = "/find/dept")
    public ResponseEntity<?> findAllDept() {
        return new ResponseEntity<>(DataListReturn.ok(publicityInfoService.findAllDept()), HttpStatus.OK);
    }


    /**
     * 新增宣传信息
     *
     * @param saveParam
     * @return
     */
    @PostMapping(value = "/add/publicity/info")
    public ResponseEntity<?> addPublicityInfo(@RequestBody PublicityInfoSaveParam saveParam) {
        Integer result = publicityInfoService.addPublicityInfo(saveParam);
        if (result > 0) {
            return new ResponseEntity<>(DataListReturn.ok("新增成功！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.error("新增失败！"), HttpStatus.OK);
    }


    /**
     * 修改宣传信息
     *
     * @param saveParam
     * @return
     */
    @PostMapping("/update/publicity/info")
    public ResponseEntity<?> updatePublicityInfo(@RequestBody PublicityInfoSaveParam saveParam) {
        Integer result = publicityInfoService.updatePublicityInfo(saveParam);
        if (result > 0) {
            return new ResponseEntity<>(DataListReturn.ok("修改成功！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.error("修改失败！"), HttpStatus.OK);
    }


    /**
     * 删除宣传信息
     *
     * @param id
     * @return
     */
    @DeleteMapping("/delete/publicity/info")
    public ResponseEntity<?> deletePublicityInfo(Integer id) {
        Integer result = publicityInfoService.deletePublicityInfo(id);
        if (result > 0) {
            return new ResponseEntity<>(DataListReturn.ok("删除成功！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.error("删除失败！"), HttpStatus.OK);
    }


    /**
     * 根据id查询详情
     *
     * @param id
     * @return
     */
    @GetMapping("/find/publicity/info")
    public ResponseEntity<?> findPublicityInfo(Integer id) {
        Map<String, Object> info = publicityInfoService.findPublicityInfo(id);
        if (info == null) {
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(info), HttpStatus.OK);
    }


    /**
     * 列表
     *
     * @return
     */
    @GetMapping("/find/page/list")
    public ResponseEntity<?> findPageList(Integer typeId,
                                   Integer deptId,
                                   Integer levelName,
                                   String inputDate,
                                   String likeName,
                                   Integer pageIndex, Integer pageSize) {
        Map<String,Object> result = new HashMap<>();
        result.put("pageIndex", pageIndex);
        result.put("pageSize", pageSize);
        result.put("data", publicityInfoService.findPageList(typeId, deptId, levelName, inputDate, likeName, pageIndex, pageSize));
        result.put("totalCount", publicityInfoService.findPageListCount(typeId, deptId, levelName, inputDate, likeName));

        return new ResponseEntity<>(DataListReturn.ok(result), HttpStatus.OK);
    }


    /**
     * 宣传报道单位排名TOP5
     *
     * @return
     */
    @GetMapping("/find/publicity/count/num")
    public ResponseEntity<?> findPublicityCountNum() {
        List<DepartmentDO> departmentDOList = publicityInfoService.findPublicityCountNum();
        if (departmentDOList == null) {
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(departmentDOList), HttpStatus.OK);
    }


    /**
     * 查询宣传报道警员排名TOP5
     *
     * @return
     */
    @GetMapping("/find/user/ran/king")
    public ResponseEntity<?> findUserRanKing() {
        List<UserDO> userDOList = publicityInfoService.findUserRanKing();
        if (userDOList == null) {
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(userDOList), HttpStatus.OK);
    }


    /**
     * 宣传报道警种排名TOP5
     * @return
     */
    @GetMapping("/find/police/ran/kind")
    public ResponseEntity<?> findPoliceRanKind(){
        List<PoliceKindDO> policeKindDOList = policeKindService.findPoliceRanKind();
        if(policeKindDOList == null){
            return new ResponseEntity<>(DataListReturn.error("查询失败！"), HttpStatus.OK);
        }
        return new ResponseEntity<>(DataListReturn.ok(policeKindDOList), HttpStatus.OK);
    }








}
