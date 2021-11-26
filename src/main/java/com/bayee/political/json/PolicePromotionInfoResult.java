package com.bayee.political.json;

import lombok.Data;

import java.util.List;

/**
 * @author
 * @Title:
 * @Description: )
 * @date 2021/11/26 12:43
 */
@Data
public class PolicePromotionInfoResult {

    /**
     * 警员基本信息
     */
    private  PolicePromotionBasicUserInfoResult userInfo;

    /**
     * 历任职级
     */
    private List<PolicePromotionSuccessivePostsResult> posts;
}
