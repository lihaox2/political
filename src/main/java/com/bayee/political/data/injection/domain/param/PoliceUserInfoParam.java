package com.bayee.political.data.injection.domain.param;

import lombok.Data;

/**
 * @author
 * @Title: 警员返回数据
 * @Description: )
 * @date 2021/12/8 11:10
 */
@Data
public class PoliceUserInfoParam {

    /**
     * REQUESTID	请求码	VARCHAR2(50)	通过申请开通获得，必填
     */
    private String REQUESTID;

    /**
     * BEGINID	开始标识	NUMBER	增量数据，由上次获得的最后一条作为本次的开始标识
     */
    private Integer BEGINID;

    /**
     * MAXROWS	最大返回条数	NUMBER	默认为1；运行区间1-10000
     */
    private Integer MAXROWS;

    /**
     *  ISYH	是否用户	VARCHAR2(10)	此值只要不为空时，就是只查用户
     */
    private String ISYH;

    /**
     * 按部门查询	VARCHAR2(12)	该字段的值一般情况为空，当一个系统分地市部署时，可填该值进行获取不同地市数据。输入值为各地市部门编码前4位如如下：
     *     省厅：3300
     *     杭州：3301
     *     宁波：3302
     *     温州：3303
     *     嘉兴：3304
     *     湖州：3305
     *     绍兴：3306
     *     金华：3307
     *     衢州：3308
     *     舟山：3309
     *     台州：3310
     *     丽水：3311
     */
    private String BMCX;



}
