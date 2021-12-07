package com.bayee.police_comprehensive.domain.result;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/12/7 15:53
 */
@Data
public class PoliceUserResult {

    /**
     * 	增量主键	NUMBER	最后一条数据的ZID作为下一次的开始ID(beginid)
     */
    private Integer ZID;

    /**
     * MODIFYTIME	修改时间	DATE
     */
    private Date MODIFYTIME	;

    /**
     * CHANGETYPE	修改类型	VARCHAR2(30)	update,insert,delete分别代表修改、新增、删除；
     * 注意：修改与新增需要判断是否已经在您的数据库中，自动进行操作转换
     */
    private String CHANGETYPE;

    /**
     * IDCARD 警员主键	VARCHAR2(50)
     */
    private String IDCARD;

    /**
     * JYCODE	警员编码（警号）	VARCHAR2(20)
     */
    private String JYCODE;

    /**
     * JYNAME	警员姓名	VARCHAR2(50)
     */
    private String JYNAME;

    /**
     * ISFORMAL	是否用户	VARCHAR2(1)	0:是，1否
     */
    private String ISFORMAL;

    /**
     * SEX	性别	VARCHAR2(2)	字段保存：男、女
     */
    private String SEX;

    /**
     * NATION	民族	VARCHAR2(2)	对应民族码表
     */
    private String NATION;

    /**
     * USERNUMBER	身份证	VARCHAR2(30)	18位大写的身份证号
     */
    private String USERNUMBER;

    /**
     *  JZTYPE	警种类别	VARCHAR2(3)	对应警种码表
     */
    private String JZTYPE;

    /**
     * OFFICEPHONE	办公电话	VARCHAR2(50)
     */
    private String OFFICEPHONE;

    /**
     * LONGMOBILE	手机号码	VARCHAR2(50)
     */
    private String LONGMOBILE;

    /**
     * SHORTMOBILE	手机短号	VARCHAR2(20)
     */
    private String SHORTMOBILE;

    /**
     * EMAIL	省厅邮箱	VARCHAR2(50)
     */
    private String EMAIL;

    /**
     * UPDATEUSER	更新用户	VARCHAR2(20)
     */
    private String UPDATEUSER;

    /**
     * UPDATEDATE	更新时间	DATE
     */
    private Date UPDATEDATE;

    /**
     * JYZT	警员状态	VARCHAR2(2)	1.在职2.离职3.调出4.死亡5.其他人员
     */
    private String JYZT;

    /**
     * ADDRESS	办公地址	VARCHAR2(2000)
     */
    private String ADDRESS;

    /**
     * CREATOR	创建人	VARCHAR2(100)
     */
    private String CREATOR;

    /**
     * CREATDATE	创建时间	DATE
     */
    private Date CREATDATE;

    /**
     * BIRTHDAY	生日	DATE
     */
    private Date BIRTHDAY;

    /**
     * ZHDL	账号登录	CHAR(1)	1：禁止；2：允许
     */
    private String ZHDL;

    /**
     *  RYLB	人员类别 	CHAR(1)	1.正式民警2：现役军人 3.协辅警4：文职5. 行政职工6其他 7事业职工8.开发者
     */
    private String RYLB;

    /**
     * ISZSSFZ	是否正式身份证	VARCHAR2(1)	1：否，2：是
     */
    private String ISZSSFZ;

    /**
     * STATUS	使用状态	CHAR(1)	,n无效y有效
     */
    private String STATUS;

    /**
     * DSJH	地市警号	CHAR(50)
     */
    private String DSJH;

}
