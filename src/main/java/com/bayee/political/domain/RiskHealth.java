package com.bayee.political.domain;

import java.util.Date;

public class RiskHealth {
    private Integer id;

    private String policeId;

    private String year;

    private Double height;

    private Double weight;
    
    private Double indexNum;

    private Double bmi;

    private Integer bmiId;
    
    private String bmiName;

    private String blood;

    private Double overweightNum;

    private Double hyperlipidemiaNum;

    private Double hypertensionNum;

    private Double hyperglycemiaNum;

    private Double hyperuricemiaNum;

    private Double prostateNum;

    private Double majorDiseasesNum;
    
    private String majorDiseasesDescribe;

    private Double heartNum;
    
    private String heartDescribe;

    private Double tumorAntigenNum;
    
    private String tumorAntigenDescribe;

    private Double orthopaedicsNum;
    
    private String orthopaedicsDescribe;

    private Date creationDate;

    private Date updateDate;
    
    private Double healthTotal;

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

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }

    public Integer getBmiId() {
        return bmiId;
    }

    public void setBmiId(Integer bmiId) {
        this.bmiId = bmiId;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood == null ? null : blood.trim();
    }

    public Double getOverweightNum() {
        return overweightNum;
    }

    public void setOverweightNum(Double overweightNum) {
        this.overweightNum = overweightNum;
    }

    public Double getHyperlipidemiaNum() {
        return hyperlipidemiaNum;
    }

    public void setHyperlipidemiaNum(Double hyperlipidemiaNum) {
        this.hyperlipidemiaNum = hyperlipidemiaNum;
    }

    public Double getHypertensionNum() {
        return hypertensionNum;
    }

    public void setHypertensionNum(Double hypertensionNum) {
        this.hypertensionNum = hypertensionNum;
    }

    public Double getHyperglycemiaNum() {
        return hyperglycemiaNum;
    }

    public void setHyperglycemiaNum(Double hyperglycemiaNum) {
        this.hyperglycemiaNum = hyperglycemiaNum;
    }

    public Double getHyperuricemiaNum() {
        return hyperuricemiaNum;
    }

    public void setHyperuricemiaNum(Double hyperuricemiaNum) {
        this.hyperuricemiaNum = hyperuricemiaNum;
    }

    public Double getProstateNum() {
        return prostateNum;
    }

    public void setProstateNum(Double prostateNum) {
        this.prostateNum = prostateNum;
    }

    public Double getMajorDiseasesNum() {
        return majorDiseasesNum;
    }

    public void setMajorDiseasesNum(Double majorDiseasesNum) {
        this.majorDiseasesNum = majorDiseasesNum;
    }

    public Double getHeartNum() {
        return heartNum;
    }

    public void setHeartNum(Double heartNum) {
        this.heartNum = heartNum;
    }

    public Double getTumorAntigenNum() {
        return tumorAntigenNum;
    }

    public void setTumorAntigenNum(Double tumorAntigenNum) {
        this.tumorAntigenNum = tumorAntigenNum;
    }

    public Double getOrthopaedicsNum() {
        return orthopaedicsNum;
    }

    public void setOrthopaedicsNum(Double orthopaedicsNum) {
        this.orthopaedicsNum = orthopaedicsNum;
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
	 * @return the indexNum
	 */
	public Double getIndexNum() {
        if (null == indexNum) {
            return null;
        }
		return Math.min(indexNum, 5);
	}

	/**
	 * @param indexNum the indexNum to set
	 */
	public void setIndexNum(Double indexNum) {
		this.indexNum = indexNum;
	}

	/**
	 * @return the bmiName
	 */
	public String getBmiName() {
		return bmiName;
	}

	/**
	 * @param bmiName the bmiName to set
	 */
	public void setBmiName(String bmiName) {
		this.bmiName = bmiName;
	}

	/**
	 * @return the majorDiseasesDescribe
	 */
	public String getMajorDiseasesDescribe() {
		return majorDiseasesDescribe;
	}

	/**
	 * @param majorDiseasesDescribe the majorDiseasesDescribe to set
	 */
	public void setMajorDiseasesDescribe(String majorDiseasesDescribe) {
		this.majorDiseasesDescribe = majorDiseasesDescribe;
	}

	/**
	 * @return the heartDescribe
	 */
	public String getHeartDescribe() {
		return heartDescribe;
	}

	/**
	 * @param heartDescribe the heartDescribe to set
	 */
	public void setHeartDescribe(String heartDescribe) {
		this.heartDescribe = heartDescribe;
	}

	/**
	 * @return the tumorAntigenDescribe
	 */
	public String getTumorAntigenDescribe() {
		return tumorAntigenDescribe;
	}

	/**
	 * @param tumorAntigenDescribe the tumorAntigenDescribe to set
	 */
	public void setTumorAntigenDescribe(String tumorAntigenDescribe) {
		this.tumorAntigenDescribe = tumorAntigenDescribe;
	}

	/**
	 * @return the orthopaedicsDescribe
	 */
	public String getOrthopaedicsDescribe() {
		return orthopaedicsDescribe;
	}

	/**
	 * @param orthopaedicsDescribe the orthopaedicsDescribe to set
	 */
	public void setOrthopaedicsDescribe(String orthopaedicsDescribe) {
		this.orthopaedicsDescribe = orthopaedicsDescribe;
	}

	public Double getHealthTotal() {
		return healthTotal;
	}

	public void setHealthTotal(Double healthTotal) {
		this.healthTotal = healthTotal;
	}
	
	
    
}