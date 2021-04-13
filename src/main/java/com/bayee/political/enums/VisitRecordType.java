package com.bayee.political.enums;

/**
 * @author xxl
 * @date 2021/4/13
 */
public enum VisitRecordType {

    /**
     * 第一种形态
     */
    ORDER_INSPECTION(6, "责令检查"),
    CRITICAL_EDUCATION(7, "批评教育"),
    REMAINDER_TALK(8, "提醒谈话"),
    INQUIRY(9, "函询"),
    PERSUADE_AND_ADMONISH(10, "诫勉"),

    /**
     * 党纪处分
     */
    PARTY_WARNING(11, "党纪处分-警告"),
    PARTY_SERIOUS_WARNING(12, "党纪处分-严重警告"),
    PARTY_REMOVAL_OF_PARTY_POSTS(13, "党纪处分-撤销党内职务"),
    PARTY_STAY_IN_THE_PARTY_FOR_INSPECTION(14, "党纪处分-留党查看"),
    PARTY_EXPULSION_FORM_THE_PARTY(15, "党纪处分-开除党籍"),

    /**
     * 政纪处分
     */
    GOVERNMENT_WARNING(16, "政纪处分-警告"),
    GOVERNMENT_RECORD_A_DEMERIT(17, "政纪处分-记过"),
    GOVERNMENT_RECORD_A_BIG_DEMERIT(18, "政纪处分-记大过"),
    GOVERNMENT_DEMOTION_LEVEL(19, "政纪处分-降级"),
    GOVERNMENT_DEMOTION_POSITION(20, "政纪处分-撤职"),
    GOVERNMENT_EXPEL(21, "政纪处分-开除"),

    GIVE_CRIMINAL_SANCTIONS(4, "追究刑事责任"),
    INVESTIGATION_OF_CIVIL_LIABILITY(5, "追究民事责任");

    private Integer typeId;
    private String desc;

    VisitRecordType(Integer typeId, String desc) {
        this.typeId = typeId;
        this.desc = desc;
    }

    public Integer getTypeId(){
        return this.typeId;
    }

}
