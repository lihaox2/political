package com.bayee.political.enums;

/**
 * 预警类型枚举
 *
 * @author xxl
 * @date 2021/3/29
 */
public enum AlarmTypeEnum {
    /**
     * 综合指数风险
     */
    COMPREHENSIVE_RISK(11001),

    /**
     * 行为规范风险
     */
    BEHAVIOR_RISK(11002),

    /**
     * 执法办案风险
     */
    HANDLING_CASES_RISK(11003),

    /**
     * 接警执勤风险
     */
    DUTY_RISK(11004),

    /**
     * 警务技能风险
     */
    SKILL_RISK(11005),

    /**
     * 社交风险
     */
    SOCIAL_CONTACT(11006),

    /**
     * 家属评价
     */
    FAMILY_RISK(11007),

    /**
     * 健康风险
     */
    HEALTHY_RISK(11008);

    Integer id;

    AlarmTypeEnum(Integer val) {
        this.id = val;
    }

    public Integer getId(){
        return this.id;
    }
}
