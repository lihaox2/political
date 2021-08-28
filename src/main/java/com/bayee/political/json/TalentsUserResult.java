package com.bayee.political.json;

import com.bayee.political.domain.PoliceLabel;

import java.util.List;

/**
 * 人才库返回参数
 */
public class TalentsUserResult {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 警员姓名
     */
    private String name;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 警员类型（1民警，2辅警）
     */
    private String policeType;

    /**
     * 警员部门名称
     */
    private String policeDeptName;

    /**
     * 头像
     */
    private String headImage;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 警种名称
     */
    private String kingName;

    /**
     * 分数
     */
    private Integer score;

    /**
     * 警员标签
     */
    private List<String> labelList;

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public List<String> getLabelList() {
        return labelList;
    }

    public void setLabelList(List<String> labelList) {
        this.labelList = labelList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    public String getPoliceDeptName() {
        return policeDeptName;
    }

    public void setPoliceDeptName(String policeDeptName) {
        this.policeDeptName = policeDeptName;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getKingName() {
        return kingName;
    }

    public void setKingName(String kingName) {
        this.kingName = kingName;
    }

}
