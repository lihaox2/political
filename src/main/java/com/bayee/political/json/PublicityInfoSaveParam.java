package com.bayee.political.json;

import java.io.Serializable;

public class PublicityInfoSaveParam implements Serializable {

    /**
     * 主键id
     */
    private Integer id;

    /**
     * 标题名称
     */
    private String titleName;

    /**
     * 类型表id
     */
    private Integer typeId;

    /**
     * 级别名称（1、国家级 2、省级 3、市级 4、区级）
     */
    private Integer levelName;

    /**
     * 媒体名称
     */
    private String mediaName;

    /**
     * 内容
     */
    private String content;

    /**
     * 图片地址（用，分隔）
     */
    private String imgUrl;

    /**
     * 警号，多个用,号隔开
     */
    private String policeArr;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitleName() {
        return titleName;
    }

    public void setTitleName(String titleName) {
        this.titleName = titleName;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Integer getLevelName() {
        return levelName;
    }

    public void setLevelName(Integer levelName) {
        this.levelName = levelName;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getPoliceArr() {
        return policeArr;
    }

    public void setPoliceArr(String policeArr) {
        this.policeArr = policeArr;
    }
}
