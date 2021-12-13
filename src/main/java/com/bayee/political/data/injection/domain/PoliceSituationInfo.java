package com.bayee.political.data.injection.domain;

import java.util.Date;

/**
 * 执法办案二期警情信息
 */
public class PoliceSituationInfo {
    private Integer id;

    /**
     * 报警电话
     */
    private String bjdhPg;

    /**
     * 报警人姓名
     */
    private String bjrXmPg;

    /**
     * 处警时间
     */
    private String cjsjPg;

    /**
     * 处理单位编号
     */
    private String cldwBhPg;

    /**
     * 处理单位名称
     */
    private String cldwMcPg;

    /**
     * 处理单位系统编号
     */
    private String cldwXtbhPg;

    /**
     * 处理民警身份证号
     */
    private String clrSfzhPg;

    /**
     * 处理民警姓名
     */
    private String clrXmPg;

    /**
     * 数据标识
     */
    private String dataFlagPg;

    /**
     * 电话定位经度
     */
    private String dhdwjdPg;

    /**
     * 电话定位纬度
     */
    private String dhdwwdPg;

    /**
     * 地市代码
     */
    private String dsdmPg;

    /**
     * 管辖区域代码(区县局编号)
     */
    private String gxqydmPg;

    /**
     * ID_PG
     */
    private String idPg;

    /**
     * 是否删除
     */
    private String isdelPg;

    /**
     * 接报时间
     */
    private String jbsjPg;

    /**
     * 接警单号
     */
    private String jjdhPg;

    /**
     * 警情标识
     */
    private String jqbsPg;

    /**
     * 警情地点
     */
    private String jqddPg;

    /**
     * 警情类别
     */
    private String jqlbPg;

    /**
     * 警情来源
     */
    private String jqlyPg;

    /**
     * 警情名称
     */
    private String jqmcPg;

    /**
     * 警情状态
     */
    private String jqztPg;

    /**
     * 录入单位编号
     */
    private String lrdwBhPg;

    /**
     * 录入单位地市代码
     */
    private String lrdwDsdmPg;

    /**
     * 录入单位区域代码
     */
    private String lrdwQydmPg;

    /**
     * 录入单位系统编号
     */
    private String lrdwXtbhPg;

    /**
     * 录入人
     */
    private String lrrSfzhPg;

    /**
     * 录入时间
     */
    private String lrsjPg;

    /**
     * 首次审核人身份证号
     */
    private String scshrSfzhPg;

    /**
     * 首次审核人姓名
     */
    private String scshrXmPg;

    /**
     * 首次审核时间
     */
    private String scshsjPg;

    /**
     * 是否涉案警情
     */
    private String sfsajqPg;

    /**
     * 是否巡查
     */
    private String sfxcPg;

    /**
     * 是否已初审
     */
    private String sfycsPg;

    /**
     * 是否重点关注
     */
    private String sfzdgzPg;

    /**
     * 审核状态
     */
    private String shztPg;

    /**
     * 受警单位代码
     */
    private String sjdwdmPg;

    /**
     * 数据来源
     */
    private String sjlyPg;

    /**
     * 审结时间
     */
    private String sjsjPg;

    /**
     * 修改人身份证
     */
    private String xgrSfzhPg;

    /**
     * 修改时间
     */
    private String xgsjPg;

    /**
     * 整改情况
     */
    private String zgqkPg;

    /**
     * 报警内容
     */
    private String bjrnPg;

    /**
     * 处警最终反馈
     */
    private String cjzzfkPg;

    /**
     * 处置意见
     */
    private String czyjPg;

    /**
     * 创建时间
     */
    private Date creationDate;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public String getBjrnPg() {
        return bjrnPg;
    }

    public void setBjrnPg(String bjrnPg) {
        this.bjrnPg = bjrnPg;
    }

    public String getCjzzfkPg() {
        return cjzzfkPg;
    }

    public void setCjzzfkPg(String cjzzfkPg) {
        this.cjzzfkPg = cjzzfkPg;
    }

    public String getCzyjPg() {
        return czyjPg;
    }

    public void setCzyjPg(String czyjPg) {
        this.czyjPg = czyjPg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBjdhPg() {
        return bjdhPg;
    }

    public void setBjdhPg(String bjdhPg) {
        this.bjdhPg = bjdhPg == null ? null : bjdhPg.trim();
    }

    public String getBjrXmPg() {
        return bjrXmPg;
    }

    public void setBjrXmPg(String bjrXmPg) {
        this.bjrXmPg = bjrXmPg == null ? null : bjrXmPg.trim();
    }

    public String getCjsjPg() {
        return cjsjPg;
    }

    public void setCjsjPg(String cjsjPg) {
        this.cjsjPg = cjsjPg == null ? null : cjsjPg.trim();
    }

    public String getCldwBhPg() {
        return cldwBhPg;
    }

    public void setCldwBhPg(String cldwBhPg) {
        this.cldwBhPg = cldwBhPg == null ? null : cldwBhPg.trim();
    }

    public String getCldwMcPg() {
        return cldwMcPg;
    }

    public void setCldwMcPg(String cldwMcPg) {
        this.cldwMcPg = cldwMcPg == null ? null : cldwMcPg.trim();
    }

    public String getCldwXtbhPg() {
        return cldwXtbhPg;
    }

    public void setCldwXtbhPg(String cldwXtbhPg) {
        this.cldwXtbhPg = cldwXtbhPg == null ? null : cldwXtbhPg.trim();
    }

    public String getClrSfzhPg() {
        return clrSfzhPg;
    }

    public void setClrSfzhPg(String clrSfzhPg) {
        this.clrSfzhPg = clrSfzhPg == null ? null : clrSfzhPg.trim();
    }

    public String getClrXmPg() {
        return clrXmPg;
    }

    public void setClrXmPg(String clrXmPg) {
        this.clrXmPg = clrXmPg == null ? null : clrXmPg.trim();
    }

    public String getDataFlagPg() {
        return dataFlagPg;
    }

    public void setDataFlagPg(String dataFlagPg) {
        this.dataFlagPg = dataFlagPg == null ? null : dataFlagPg.trim();
    }

    public String getDhdwjdPg() {
        return dhdwjdPg;
    }

    public void setDhdwjdPg(String dhdwjdPg) {
        this.dhdwjdPg = dhdwjdPg == null ? null : dhdwjdPg.trim();
    }

    public String getDhdwwdPg() {
        return dhdwwdPg;
    }

    public void setDhdwwdPg(String dhdwwdPg) {
        this.dhdwwdPg = dhdwwdPg == null ? null : dhdwwdPg.trim();
    }

    public String getDsdmPg() {
        return dsdmPg;
    }

    public void setDsdmPg(String dsdmPg) {
        this.dsdmPg = dsdmPg == null ? null : dsdmPg.trim();
    }

    public String getGxqydmPg() {
        return gxqydmPg;
    }

    public void setGxqydmPg(String gxqydmPg) {
        this.gxqydmPg = gxqydmPg == null ? null : gxqydmPg.trim();
    }

    public String getIdPg() {
        return idPg;
    }

    public void setIdPg(String idPg) {
        this.idPg = idPg == null ? null : idPg.trim();
    }

    public String getIsdelPg() {
        return isdelPg;
    }

    public void setIsdelPg(String isdelPg) {
        this.isdelPg = isdelPg == null ? null : isdelPg.trim();
    }

    public String getJbsjPg() {
        return jbsjPg;
    }

    public void setJbsjPg(String jbsjPg) {
        this.jbsjPg = jbsjPg == null ? null : jbsjPg.trim();
    }

    public String getJjdhPg() {
        return jjdhPg;
    }

    public void setJjdhPg(String jjdhPg) {
        this.jjdhPg = jjdhPg == null ? null : jjdhPg.trim();
    }

    public String getJqbsPg() {
        return jqbsPg;
    }

    public void setJqbsPg(String jqbsPg) {
        this.jqbsPg = jqbsPg == null ? null : jqbsPg.trim();
    }

    public String getJqddPg() {
        return jqddPg;
    }

    public void setJqddPg(String jqddPg) {
        this.jqddPg = jqddPg == null ? null : jqddPg.trim();
    }

    public String getJqlbPg() {
        return jqlbPg;
    }

    public void setJqlbPg(String jqlbPg) {
        this.jqlbPg = jqlbPg == null ? null : jqlbPg.trim();
    }

    public String getJqlyPg() {
        return jqlyPg;
    }

    public void setJqlyPg(String jqlyPg) {
        this.jqlyPg = jqlyPg == null ? null : jqlyPg.trim();
    }

    public String getJqmcPg() {
        return jqmcPg;
    }

    public void setJqmcPg(String jqmcPg) {
        this.jqmcPg = jqmcPg == null ? null : jqmcPg.trim();
    }

    public String getJqztPg() {
        return jqztPg;
    }

    public void setJqztPg(String jqztPg) {
        this.jqztPg = jqztPg == null ? null : jqztPg.trim();
    }

    public String getLrdwBhPg() {
        return lrdwBhPg;
    }

    public void setLrdwBhPg(String lrdwBhPg) {
        this.lrdwBhPg = lrdwBhPg == null ? null : lrdwBhPg.trim();
    }

    public String getLrdwDsdmPg() {
        return lrdwDsdmPg;
    }

    public void setLrdwDsdmPg(String lrdwDsdmPg) {
        this.lrdwDsdmPg = lrdwDsdmPg == null ? null : lrdwDsdmPg.trim();
    }

    public String getLrdwQydmPg() {
        return lrdwQydmPg;
    }

    public void setLrdwQydmPg(String lrdwQydmPg) {
        this.lrdwQydmPg = lrdwQydmPg == null ? null : lrdwQydmPg.trim();
    }

    public String getLrdwXtbhPg() {
        return lrdwXtbhPg;
    }

    public void setLrdwXtbhPg(String lrdwXtbhPg) {
        this.lrdwXtbhPg = lrdwXtbhPg == null ? null : lrdwXtbhPg.trim();
    }

    public String getLrrSfzhPg() {
        return lrrSfzhPg;
    }

    public void setLrrSfzhPg(String lrrSfzhPg) {
        this.lrrSfzhPg = lrrSfzhPg == null ? null : lrrSfzhPg.trim();
    }

    public String getLrsjPg() {
        return lrsjPg;
    }

    public void setLrsjPg(String lrsjPg) {
        this.lrsjPg = lrsjPg == null ? null : lrsjPg.trim();
    }

    public String getScshrSfzhPg() {
        return scshrSfzhPg;
    }

    public void setScshrSfzhPg(String scshrSfzhPg) {
        this.scshrSfzhPg = scshrSfzhPg == null ? null : scshrSfzhPg.trim();
    }

    public String getScshrXmPg() {
        return scshrXmPg;
    }

    public void setScshrXmPg(String scshrXmPg) {
        this.scshrXmPg = scshrXmPg == null ? null : scshrXmPg.trim();
    }

    public String getScshsjPg() {
        return scshsjPg;
    }

    public void setScshsjPg(String scshsjPg) {
        this.scshsjPg = scshsjPg == null ? null : scshsjPg.trim();
    }

    public String getSfsajqPg() {
        return sfsajqPg;
    }

    public void setSfsajqPg(String sfsajqPg) {
        this.sfsajqPg = sfsajqPg == null ? null : sfsajqPg.trim();
    }

    public String getSfxcPg() {
        return sfxcPg;
    }

    public void setSfxcPg(String sfxcPg) {
        this.sfxcPg = sfxcPg == null ? null : sfxcPg.trim();
    }

    public String getSfycsPg() {
        return sfycsPg;
    }

    public void setSfycsPg(String sfycsPg) {
        this.sfycsPg = sfycsPg == null ? null : sfycsPg.trim();
    }

    public String getSfzdgzPg() {
        return sfzdgzPg;
    }

    public void setSfzdgzPg(String sfzdgzPg) {
        this.sfzdgzPg = sfzdgzPg == null ? null : sfzdgzPg.trim();
    }

    public String getShztPg() {
        return shztPg;
    }

    public void setShztPg(String shztPg) {
        this.shztPg = shztPg == null ? null : shztPg.trim();
    }

    public String getSjdwdmPg() {
        return sjdwdmPg;
    }

    public void setSjdwdmPg(String sjdwdmPg) {
        this.sjdwdmPg = sjdwdmPg == null ? null : sjdwdmPg.trim();
    }

    public String getSjlyPg() {
        return sjlyPg;
    }

    public void setSjlyPg(String sjlyPg) {
        this.sjlyPg = sjlyPg == null ? null : sjlyPg.trim();
    }

    public String getSjsjPg() {
        return sjsjPg;
    }

    public void setSjsjPg(String sjsjPg) {
        this.sjsjPg = sjsjPg == null ? null : sjsjPg.trim();
    }

    public String getXgrSfzhPg() {
        return xgrSfzhPg;
    }

    public void setXgrSfzhPg(String xgrSfzhPg) {
        this.xgrSfzhPg = xgrSfzhPg == null ? null : xgrSfzhPg.trim();
    }

    public String getXgsjPg() {
        return xgsjPg;
    }

    public void setXgsjPg(String xgsjPg) {
        this.xgsjPg = xgsjPg == null ? null : xgsjPg.trim();
    }

    public String getZgqkPg() {
        return zgqkPg;
    }

    public void setZgqkPg(String zgqkPg) {
        this.zgqkPg = zgqkPg == null ? null : zgqkPg.trim();
    }
}