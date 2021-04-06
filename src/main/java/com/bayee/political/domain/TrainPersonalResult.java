package com.bayee.political.domain;

import java.util.List;

/**
 * @author shentuqiwei
 * @version 2020年11月02日 下午11:51:03
 */
public class TrainPersonalResult {

	private int physicalTotalNum;// 综合体能总场次
	private int physicalSignNum;// 综合体能签到数量
	private double physicalSignRate;// 综合体能签到率
	private int physicalPassNum;// 综合体能合格数量
	private double physicalPassRate;// 综合体能合格率
	private int firearmTotalNum;// 枪械总场次
	private int firearmSignNum;// 枪械签到数量
	private double firearmSignRate;// 枪械签到率
	private int firearmPassNum;// 枪械合格数量
	private double firearmPassRate;// 枪械合格率
	private int firearmJustNum;// 枪械良好数量
	private double firearmJustRate;// 枪械良好率
	private int firearmGoodNum;// 枪械优秀数量
	private double firearmGoodRate;// 枪械优秀率
	private int officeNum;// 分局训练数量
	private int depNum;// 单位训练数量
	private List<CalculationChart> officeList;
	private List<CalculationChart> depList;

	/**
	 * @return the physicalTotalNum
	 */
	public int getPhysicalTotalNum() {
		return physicalTotalNum;
	}

	/**
	 * @param physicalTotalNum the physicalTotalNum to set
	 */
	public void setPhysicalTotalNum(int physicalTotalNum) {
		this.physicalTotalNum = physicalTotalNum;
	}

	/**
	 * @return the physicalSignNum
	 */
	public int getPhysicalSignNum() {
		return physicalSignNum;
	}

	/**
	 * @param physicalSignNum the physicalSignNum to set
	 */
	public void setPhysicalSignNum(int physicalSignNum) {
		this.physicalSignNum = physicalSignNum;
	}

	/**
	 * @return the physicalSignRate
	 */
	public double getPhysicalSignRate() {
		return physicalSignRate;
	}

	/**
	 * @param physicalSignRate the physicalSignRate to set
	 */
	public void setPhysicalSignRate(double physicalSignRate) {
		this.physicalSignRate = physicalSignRate;
	}

	/**
	 * @return the physicalPassNum
	 */
	public int getPhysicalPassNum() {
		return physicalPassNum;
	}

	/**
	 * @param physicalPassNum the physicalPassNum to set
	 */
	public void setPhysicalPassNum(int physicalPassNum) {
		this.physicalPassNum = physicalPassNum;
	}

	/**
	 * @return the physicalPassRate
	 */
	public double getPhysicalPassRate() {
		return physicalPassRate;
	}

	/**
	 * @param physicalPassRate the physicalPassRate to set
	 */
	public void setPhysicalPassRate(double physicalPassRate) {
		this.physicalPassRate = physicalPassRate;
	}

	/**
	 * @return the firearmTotalNum
	 */
	public int getFirearmTotalNum() {
		return firearmTotalNum;
	}

	/**
	 * @param firearmTotalNum the firearmTotalNum to set
	 */
	public void setFirearmTotalNum(int firearmTotalNum) {
		this.firearmTotalNum = firearmTotalNum;
	}

	/**
	 * @return the firearmSignNum
	 */
	public int getFirearmSignNum() {
		return firearmSignNum;
	}

	/**
	 * @param firearmSignNum the firearmSignNum to set
	 */
	public void setFirearmSignNum(int firearmSignNum) {
		this.firearmSignNum = firearmSignNum;
	}

	/**
	 * @return the firearmSignRate
	 */
	public double getFirearmSignRate() {
		return firearmSignRate;
	}

	/**
	 * @param firearmSignRate the firearmSignRate to set
	 */
	public void setFirearmSignRate(double firearmSignRate) {
		this.firearmSignRate = firearmSignRate;
	}

	/**
	 * @return the firearmPassNum
	 */
	public int getFirearmPassNum() {
		return firearmPassNum;
	}

	/**
	 * @param firearmPassNum the firearmPassNum to set
	 */
	public void setFirearmPassNum(int firearmPassNum) {
		this.firearmPassNum = firearmPassNum;
	}

	/**
	 * @return the firearmPassRate
	 */
	public double getFirearmPassRate() {
		return firearmPassRate;
	}

	/**
	 * @param firearmPassRate the firearmPassRate to set
	 */
	public void setFirearmPassRate(double firearmPassRate) {
		this.firearmPassRate = firearmPassRate;
	}

	/**
	 * @return the firearmJustNum
	 */
	public int getFirearmJustNum() {
		return firearmJustNum;
	}

	/**
	 * @param firearmJustNum the firearmJustNum to set
	 */
	public void setFirearmJustNum(int firearmJustNum) {
		this.firearmJustNum = firearmJustNum;
	}

	/**
	 * @return the firearmJustRate
	 */
	public double getFirearmJustRate() {
		return firearmJustRate;
	}

	/**
	 * @param firearmJustRate the firearmJustRate to set
	 */
	public void setFirearmJustRate(double firearmJustRate) {
		this.firearmJustRate = firearmJustRate;
	}

	/**
	 * @return the firearmGoodNum
	 */
	public int getFirearmGoodNum() {
		return firearmGoodNum;
	}

	/**
	 * @param firearmGoodNum the firearmGoodNum to set
	 */
	public void setFirearmGoodNum(int firearmGoodNum) {
		this.firearmGoodNum = firearmGoodNum;
	}

	/**
	 * @return the firearmGoodRate
	 */
	public double getFirearmGoodRate() {
		return firearmGoodRate;
	}

	/**
	 * @param firearmGoodRate the firearmGoodRate to set
	 */
	public void setFirearmGoodRate(double firearmGoodRate) {
		this.firearmGoodRate = firearmGoodRate;
	}

	/**
	 * @return the officeNum
	 */
	public int getOfficeNum() {
		return officeNum;
	}

	/**
	 * @param officeNum the officeNum to set
	 */
	public void setOfficeNum(int officeNum) {
		this.officeNum = officeNum;
	}

	/**
	 * @return the depNum
	 */
	public int getDepNum() {
		return depNum;
	}

	/**
	 * @param depNum the depNum to set
	 */
	public void setDepNum(int depNum) {
		this.depNum = depNum;
	}

	/**
	 * @return the officeList
	 */
	public List<CalculationChart> getOfficeList() {
		return officeList;
	}

	/**
	 * @param officeList the officeList to set
	 */
	public void setOfficeList(List<CalculationChart> officeList) {
		this.officeList = officeList;
	}

	/**
	 * @return the depList
	 */
	public List<CalculationChart> getDepList() {
		return depList;
	}

	/**
	 * @param depList the depList to set
	 */
	public void setDepList(List<CalculationChart> depList) {
		this.depList = depList;
	}
	
}
