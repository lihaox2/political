package com.bayee.political.pojo.dto;

/**
 * @author xxl
 * @date 2021/8/1
 */
public class RiskHealthReportDO {

    /**
     * 高血脂次数
     */
    private Integer hyperlipidemia;

    /**
     * 高血压次数
     */
    private Integer hypertension;

    /**
     * 高血糖次数
     */
    private Integer hyperglycemia;

    /**
     * 高血尿酸次数
     */
    private Integer hyperuricemia;

    public Integer getHyperlipidemia() {
        return hyperlipidemia;
    }

    public void setHyperlipidemia(Integer hyperlipidemia) {
        this.hyperlipidemia = hyperlipidemia;
    }

    public Integer getHypertension() {
        return hypertension;
    }

    public void setHypertension(Integer hypertension) {
        this.hypertension = hypertension;
    }

    public Integer getHyperglycemia() {
        return hyperglycemia;
    }

    public void setHyperglycemia(Integer hyperglycemia) {
        this.hyperglycemia = hyperglycemia;
    }

    public Integer getHyperuricemia() {
        return hyperuricemia;
    }

    public void setHyperuricemia(Integer hyperuricemia) {
        this.hyperuricemia = hyperuricemia;
    }
}
