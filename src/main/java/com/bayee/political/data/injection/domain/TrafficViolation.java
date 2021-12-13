package com.bayee.political.data.injection.domain;

/**
 * 全国机动车违法违章信息
 */
public class TrafficViolation {
    private Integer id;

    /**
     * 警号
     */
    private String policeId;

    /**
     * 处理机关名称
     */
    private String cljgmc;

    /**
     * 处理时间
     */
    private String clsj;

    /**
     * 当事人名称
     */
    private String dsr;

    /**
     * 号牌号码
     */
    private String hphm;

    /**
     * 号牌种类
     */
    private String hpzl;

    /**
     * 驾驶证号
     */
    private String jszh;

    /**
     * 操作类型
     */
    private String rsCzlx;

    /**
     * 操作时间
     */
    private String rsCzsj;

    /**
     * 违法编号
     */
    private String wfbh;

    /**
     * 违法地点
     */
    private String wfdz;

    /**
     * 违法时间
     */
    private String wfsj;

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
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getCljgmc() {
        return cljgmc;
    }

    public void setCljgmc(String cljgmc) {
        this.cljgmc = cljgmc == null ? null : cljgmc.trim();
    }

    public String getClsj() {
        return clsj;
    }

    public void setClsj(String clsj) {
        this.clsj = clsj == null ? null : clsj.trim();
    }

    public String getDsr() {
        return dsr;
    }

    public void setDsr(String dsr) {
        this.dsr = dsr == null ? null : dsr.trim();
    }

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm == null ? null : hphm.trim();
    }

    public String getHpzl() {
        return hpzl;
    }

    public void setHpzl(String hpzl) {
        this.hpzl = hpzl == null ? null : hpzl.trim();
    }

    public String getJszh() {
        return jszh;
    }

    public void setJszh(String jszh) {
        this.jszh = jszh == null ? null : jszh.trim();
    }

    public String getRsCzlx() {
        return rsCzlx;
    }

    public void setRsCzlx(String rsCzlx) {
        this.rsCzlx = rsCzlx == null ? null : rsCzlx.trim();
    }

    public String getRsCzsj() {
        return rsCzsj;
    }

    public void setRsCzsj(String rsCzsj) {
        this.rsCzsj = rsCzsj == null ? null : rsCzsj.trim();
    }

    public String getWfbh() {
        return wfbh;
    }

    public void setWfbh(String wfbh) {
        this.wfbh = wfbh == null ? null : wfbh.trim();
    }

    public String getWfdz() {
        return wfdz;
    }

    public void setWfdz(String wfdz) {
        this.wfdz = wfdz == null ? null : wfdz.trim();
    }

    public String getWfsj() {
        return wfsj;
    }

    public void setWfsj(String wfsj) {
        this.wfsj = wfsj == null ? null : wfsj.trim();
    }
}