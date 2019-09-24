package com.ruoyi.project.duties.tasksCars.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 任务车辆表 tb_tasks_cars
 * 
 * @author admin
 * @date 2019-04-25
 */
public class TasksCarsReport extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private String carBrand;
	private Long taskId;
	@Excel(name = "工程名称")
	//工程名称
	private String taskName;
	//砼标号
	@Excel(name = "砼标号")
	private String productKind;
	//总皮重
	@Excel(name = "总皮重")
	private BigDecimal totalCarWeight;
	//总皮重
	@Excel(name = "总皮重")
	private BigDecimal totalGrossWeight;
	//总净重
	@Excel(name = "总净重")
	private BigDecimal totalNetWeight;
	//总方量
	@Excel(name = "总方量")
	private BigDecimal totalFangLiang;
	//总价格
	@Excel(name = "总价格")
	private BigDecimal totalAmount;

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getProductKind() {
		return productKind;
	}

	public void setProductKind(String productKind) {
		this.productKind = productKind;
	}

	public BigDecimal getTotalCarWeight() {
		return totalCarWeight;
	}

	public void setTotalCarWeight(BigDecimal totalCarWeight) {
		this.totalCarWeight = totalCarWeight;
	}

	public BigDecimal getTotalGrossWeight() {
		return totalGrossWeight;
	}

	public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}

	public BigDecimal getTotalNetWeight() {
		return totalNetWeight;
	}

	public void setTotalNetWeight(BigDecimal totalNetWeight) {
		this.totalNetWeight = totalNetWeight;
	}

	public BigDecimal getTotalFangLiang() {
		return totalFangLiang;
	}

	public void setTotalFangLiang(BigDecimal totalFangLiang) {
		this.totalFangLiang = totalFangLiang;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
