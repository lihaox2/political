package com.bayee.political.json;

/**
 * @author xxl
 * @date 2021/5/7
 */
public class HealthPageResult {

    private Integer id;

    private String policeId;

    private String policeName;

    /**
     * 体重是否超标(0否 1是)
     */
    private String isOverweight;

    /**
     * 是否高血脂
     */
    private String isHyperlipidemia;

    /**
     * 是否高血压
     */
    private String isHypertension;

    /**
     * 是否高血糖
     */
    private String isHyperglycemia;

    /**
     * 是否高血尿酸
     */
    private String isHyperuricemia;

    private String year;

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    public String getIsOverweight() {
        return isOverweight;
    }

    public void setIsOverweight(String isOverweight) {
        this.isOverweight = isOverweight;
    }

    public String getIsHyperlipidemia() {
        return isHyperlipidemia;
    }

    public void setIsHyperlipidemia(String isHyperlipidemia) {
        this.isHyperlipidemia = isHyperlipidemia;
    }

    public String getIsHypertension() {
        return isHypertension;
    }

    public void setIsHypertension(String isHypertension) {
        this.isHypertension = isHypertension;
    }

    public String getIsHyperglycemia() {
        return isHyperglycemia;
    }

    public void setIsHyperglycemia(String isHyperglycemia) {
        this.isHyperglycemia = isHyperglycemia;
    }

    public String getIsHyperuricemia() {
        return isHyperuricemia;
    }

    public void setIsHyperuricemia(String isHyperuricemia) {
        this.isHyperuricemia = isHyperuricemia;
    }
}
