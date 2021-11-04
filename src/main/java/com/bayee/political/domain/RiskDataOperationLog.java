package com.bayee.political.domain;

import java.util.Date;

/**
 *
 * @author xxl
 */
public class RiskDataOperationLog {
    /**
     * 操作类型ADD(新增)，UPDATE(修改)，DELETE(删除)，DATA_IMPORT（数据导入）
     */
    private String operationType;

    private Integer id;

    /**
     * 操作人警号
     */
    private String operationPoliceId;

    /**
     * 数据源类型，关联risk_alarm_type表
     */
    private Integer dataOriginType;

    /**
     * 源数据id
     */
    private Integer dataOriginId;

    /**
     * 源数据警号信息
     */
    private String dataOriginPoliceId;

    /**
     * 源数据业务时间
     */
    private Date dataOriginBusinessDate;

    /**
     * 创建时间
     */
    private Date creationDate;

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType == null ? null : operationType.trim();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOperationPoliceId() {
        return operationPoliceId;
    }

    public void setOperationPoliceId(String operationPoliceId) {
        this.operationPoliceId = operationPoliceId == null ? null : operationPoliceId.trim();
    }

    public Integer getDataOriginType() {
        return dataOriginType;
    }

    public void setDataOriginType(Integer dataOriginType) {
        this.dataOriginType = dataOriginType;
    }

    public Integer getDataOriginId() {
        return dataOriginId;
    }

    public void setDataOriginId(Integer dataOriginId) {
        this.dataOriginId = dataOriginId;
    }

    public String getDataOriginPoliceId() {
        return dataOriginPoliceId;
    }

    public void setDataOriginPoliceId(String dataOriginPoliceId) {
        this.dataOriginPoliceId = dataOriginPoliceId == null ? null : dataOriginPoliceId.trim();
    }

    public Date getDataOriginBusinessDate() {
        return dataOriginBusinessDate;
    }

    public void setDataOriginBusinessDate(Date dataOriginBusinessDate) {
        this.dataOriginBusinessDate = dataOriginBusinessDate;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}