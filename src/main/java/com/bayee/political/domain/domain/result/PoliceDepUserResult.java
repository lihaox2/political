package com.bayee.political.domain.domain.result;

import lombok.Data;

/**
 * @author
 * @Title: 用户部门关联信息
 * @Description: )
 * @date 2021/12/7 16:22
 */
@Data
public class PoliceDepUserResult {

    /**
     * ZID	日志对应	NUMBER	与警员日志的ZID对应
     */
    private Integer ZID;

    /**
     * IDCARD	警员主键	VARCHAR2(50)
     */
    private String IDCARD;

    /**
     * BMCODE	所属部门代码	VARCHAR2(21)
     */
    private String BMCODE;

    /**
     * JYPX	排序	VARCHAR2(50)
     */
    private String JYPX;

    /**
     * BMMC	部门名称	VARCHAR2(200)
     */
    private String BMMC;

    /**
     * ISXS	是否是领导	VARCHAR2(1)	1不是0，是
     */
    private String ISXS;

    /**
     * ZW	职务	VARCHAR2(200)
     */
    private String ZW;

    /**
     * BMID	部门id	VARCHAR2(50)
     */
    private String BMID;

    /**
     * ISZBM	是否主部门	VARCHAR2(1)	0不是1，是
     */
    private String ISZBM;

}
