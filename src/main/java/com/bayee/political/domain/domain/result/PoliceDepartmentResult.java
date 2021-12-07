package com.bayee.political.domain.domain.result;

import lombok.Data;

import java.util.Date;

/**
 * @author lichenghu
 * @Title: 部门返回信息
 * @Description: )
 * @date 2021/11/17 11:03
 */
@Data
public class PoliceDepartmentResult {

    /**
     * 开始ID
     */
    private Integer BEGINID;

    /**
     * 操作
     */
    private String CZ;

    /**
     * 机构编号
     */
    private String JGBH;

    /**
     * 上级编号
     */
    private String SJBH;

    /**
     * 机构名称
     */
    private String MC;

    /**
     * 机构简称
     */
    private String JC;

    /**
     * 机构代码
     */
    private String DM;

    /**
     * 上级机构代码
     */
    private String SJDM;

    /**
     * 序号
     */
    private Integer XH;

    /**
     * 类别
     */
    private String LB;

    /**
     * 创建时间
     */
    private Date CJSJ;

    /**
     * 创建人员
     */
    private String CJRY;

    /**
     * 修改时间
     */
    private Date XGSJ;

    /**
     * 修改人员
     */
    private String XGRY;

    /**
     * 机构电话
     */
    private String DH;

    /**
     * 机构地址
     */
    private String DZ;

    /**
     * 所属警种
     */
    private String SSJZ;




}
