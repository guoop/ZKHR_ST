package com.ruoyi.project.cemslink.consumption.domain;

import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 生产消耗数据表 consumption
 * 
 * @author admin
 * @date 2019-05-31
 */
public class ConsumptionVO extends BaseEntity
{
	private static final long serialVersionUID = 1L;

	/** ERP运输单号 */
	private BigDecimal cement;//水泥
	private BigDecimal ag1Dosage;//石粉-外加粉
	private BigDecimal ag2Dosage;//1-2石子
	private BigDecimal ag3Dosage;//0-5石子
	private BigDecimal ag4Dosage;//米石-3#沙子
	private BigDecimal ag5Dosage;//面沙-4#沙子
	private BigDecimal mix1Dosage;//粉煤灰
	private BigDecimal mix2Dosage;//矿粉
	private BigDecimal additive1Dosage;//外加剂A料
	private BigDecimal additive2Dosage;//外加剂B料

	public BigDecimal getCement() {
		return cement;
	}

	public void setCement(BigDecimal cement) {
		this.cement = cement;
	}

	public BigDecimal getAg1Dosage() {
		return ag1Dosage;
	}

	public void setAg1Dosage(BigDecimal ag1Dosage) {
		this.ag1Dosage = ag1Dosage;
	}

	public BigDecimal getAg2Dosage() {
		return ag2Dosage;
	}

	public void setAg2Dosage(BigDecimal ag2Dosage) {
		this.ag2Dosage = ag2Dosage;
	}

	public BigDecimal getAg3Dosage() {
		return ag3Dosage;
	}

	public void setAg3Dosage(BigDecimal ag3Dosage) {
		this.ag3Dosage = ag3Dosage;
	}

	public BigDecimal getAg4Dosage() {
		return ag4Dosage;
	}

	public void setAg4Dosage(BigDecimal ag4Dosage) {
		this.ag4Dosage = ag4Dosage;
	}

	public BigDecimal getAg5Dosage() {
		return ag5Dosage;
	}

	public void setAg5Dosage(BigDecimal ag5Dosage) {
		this.ag5Dosage = ag5Dosage;
	}

	public BigDecimal getMix1Dosage() {
		return mix1Dosage;
	}

	public void setMix1Dosage(BigDecimal mix1Dosage) {
		this.mix1Dosage = mix1Dosage;
	}

	public BigDecimal getMix2Dosage() {
		return mix2Dosage;
	}

	public void setMix2Dosage(BigDecimal mix2Dosage) {
		this.mix2Dosage = mix2Dosage;
	}

	public BigDecimal getAdditive1Dosage() {
		return additive1Dosage;
	}

	public void setAdditive1Dosage(BigDecimal additive1Dosage) {
		this.additive1Dosage = additive1Dosage;
	}

	public BigDecimal getAdditive2Dosage() {
		return additive2Dosage;
	}

	public void setAdditive2Dosage(BigDecimal additive2Dosage) {
		this.additive2Dosage = additive2Dosage;
	}
}
