package com.bayee.political.domain;

import java.util.Date;
import java.util.List;

public class RiskReportRecord {
    private Integer id;

    private String headImage;// 头像
    
   	private String policeId;// 警号
   	
   	private String name;// 警员姓名
   	
   	private String departmentName;// 部门名
   	
   	private String positionName;// 职位名称

    private String year;

    private String month;

    private Double totalNum;
    
    private double currentTotalNum;// 本月总风险值
    
    private double lastTotalNum;// 上月总风险值
    
    private Double riskRate;

    private Double conductNum;

    private Double handlingCaseNum;

    private Double dutyNum;

    private Double trainNum;

    private Double socialContactNum;

    private Double amilyEvaluationNum;

    private Double healthNum;
    
    private Double drinkNum;

    private Double studyNum;
    
    private Double workNum;
    
    private List<ScreenDoubeChart> chartList;

    private Date creationDate;

    private Date updateDate;

    public RiskReportRecord() {

	}

	public RiskReportRecord(double initValue) {
    	this.totalNum = initValue;
    	this.conductNum = initValue;
    	this.handlingCaseNum = initValue;
    	this.dutyNum = initValue;
    	this.trainNum = initValue;
    	this.socialContactNum = initValue;
    	this.amilyEvaluationNum = initValue;
    	this.healthNum = initValue;
    	this.studyNum = initValue;
    	this.drinkNum = initValue;
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
        this.policeId = policeId == null ? null : policeId.trim();
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year == null ? null : year.trim();
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month == null ? null : month.trim();
    }

    public Double getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Double totalNum) {
        this.totalNum = totalNum;
    }

    public Double getConductNum() {
        return Math.min(conductNum, 25);
    }

    public void setConductNum(Double conductNum) {
        this.conductNum = conductNum;
    }

    public Double getHandlingCaseNum() {
        return Math.min(handlingCaseNum, 20);
    }

    public void setHandlingCaseNum(Double handlingCaseNum) {
        this.handlingCaseNum = handlingCaseNum;
    }

    public Double getDutyNum() {
        return Math.min(dutyNum, 20);
    }

    public void setDutyNum(Double dutyNum) {
        this.dutyNum = dutyNum;
    }

    public Double getTrainNum() {
        return Math.min(trainNum, 10);
    }

    public void setTrainNum(Double trainNum) {
        this.trainNum = trainNum;
    }

    public Double getSocialContactNum() {
        return Math.min(socialContactNum, 15);
    }

    public void setSocialContactNum(Double socialContactNum) {
        this.socialContactNum = socialContactNum;
    }

    public Double getAmilyEvaluationNum() {
        return Math.min(amilyEvaluationNum, 5);
    }

    public void setAmilyEvaluationNum(Double amilyEvaluationNum) {
        this.amilyEvaluationNum = amilyEvaluationNum;
    }

    public Double getHealthNum() {
        return Math.min(healthNum, 5);
    }

    public void setHealthNum(Double healthNum) {
        this.healthNum = healthNum;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

	/**
	 * @return the headImage
	 */
	public String getHeadImage() {
		return headImage;
	}

	/**
	 * @param headImage the headImage to set
	 */
	public void setHeadImage(String headImage) {
		this.headImage = headImage;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the departmentName
	 */
	public String getDepartmentName() {
		return departmentName;
	}

	/**
	 * @param departmentName the departmentName to set
	 */
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName() {
		return positionName;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	/**
	 * @return the lastTotalNum
	 */
	public double getLastTotalNum() {
		return lastTotalNum;
	}

	/**
	 * @param lastTotalNum the lastTotalNum to set
	 */
	public void setLastTotalNum(double lastTotalNum) {
		this.lastTotalNum = lastTotalNum;
	}

	/**
	 * @return the riskRate
	 */
	public Double getRiskRate() {
		return riskRate;
	}

	/**
	 * @param riskRate the riskRate to set
	 */
	public void setRiskRate(Double riskRate) {
		this.riskRate = riskRate;
	}

	/**
	 * @return the chartList
	 */
	public List<ScreenDoubeChart> getChartList() {
		return chartList;
	}

	/**
	 * @param chartList the chartList to set
	 */
	public void setChartList(List<ScreenDoubeChart> chartList) {
		this.chartList = chartList;
	}

	/**
	 * @return the drinkNum
	 */
	public Double getDrinkNum() {
		return drinkNum;
	}

	/**
	 * @param drinkNum the drinkNum to set
	 */
	public void setDrinkNum(Double drinkNum) {
		this.drinkNum = drinkNum;
	}

	/**
	 * @return the studyNum
	 */
	public Double getStudyNum() {
		return studyNum;
	}

	/**
	 * @param studyNum the studyNum to set
	 */
	public void setStudyNum(Double studyNum) {
		this.studyNum = studyNum;
	}

	/**
	 * @return the workNum
	 */
	public Double getWorkNum() {
		return workNum;
	}

	/**
	 * @param workNum the workNum to set
	 */
	public void setWorkNum(Double workNum) {
		this.workNum = workNum;
	}

	/**
	 * @return the currentTotalNum
	 */
	public double getCurrentTotalNum() {
		return currentTotalNum;
	}

	/**
	 * @param currentTotalNum the currentTotalNum to set
	 */
	public void setCurrentTotalNum(double currentTotalNum) {
		this.currentTotalNum = currentTotalNum;
	}
	
}