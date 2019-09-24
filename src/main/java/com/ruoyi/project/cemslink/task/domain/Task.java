package com.ruoyi.project.cemslink.task.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 任务单数据表 task
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Task extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** 生产线 */
	private Integer productLine;
	/** 任务单号 */
	private String taskNumber;
	/** 合同编号 */
	private String contractNumber;
	/** 客户名称 */
	private String customerName;
	/** 工程名称 */
	private String projectName;
	/** 施工单位 （客户） */
	private String unitName;
	/** 施工部位 */
	private String constructionPart;
	/** 施工地址 */
	private String constructionAddress;
	/** 浇注方式 */
	private String pouringWay;
	/** 砼标号 */
	private String concreteLabel;
	/** 坍落度 */
	private String slump;
	/** 运输距离 */
	private Double distance;
	/** 送货工具 */
	private String deliveryMode;
	/** 水泥品种 */
	private String cementGrade;
	/** AGSize */
	private String aGSize;
	/** 技术要求 */
	private String techRequest;
	/** 配比比例 */
	private String peibiScale;
	/** 抗渗等级 */
	private String contraLeakLevel;
	/** 外加剂品种 */
	private String additionSort;
	/** 配合比编号 */
	private String mixNumber;
	/** 砂浆配合比编号 */
	private String mortarMixNumber;
	/** 计划方量 */
	private Double planAmount;
	/** 计划开盘时间 */
	private Date planDateTime;
	/** 任务登记人 */
	private String booker;
	/** 联系人 */
	private String contact;
	/** 联系方式 */
	private String contactPhone;
	/** 最后一次修改时 */
	private Date lastModifiedTime;
	/** 创建时间 */
	private Date createdTime;
	/** Type */
	private Integer type;
	/** 同步标识 */
	private Integer syncStatus;
	/** 从机同步标识 */
	private Integer syncStatus2;

	public void setSID(Long sID) 
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setProductLine(Integer productLine) 
	{
		this.productLine = productLine;
	}

	public Integer getProductLine() 
	{
		return productLine;
	}
	public void setTaskNumber(String taskNumber) 
	{
		this.taskNumber = taskNumber;
	}

	public String getTaskNumber() 
	{
		return taskNumber;
	}
	public void setContractNumber(String contractNumber) 
	{
		this.contractNumber = contractNumber;
	}

	public String getContractNumber() 
	{
		return contractNumber;
	}
	public void setCustomerName(String customerName) 
	{
		this.customerName = customerName;
	}

	public String getCustomerName() 
	{
		return customerName;
	}
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}

	public String getProjectName() 
	{
		return projectName;
	}
	public void setUnitName(String unitName) 
	{
		this.unitName = unitName;
	}

	public String getUnitName() 
	{
		return unitName;
	}
	public void setConstructionPart(String constructionPart) 
	{
		this.constructionPart = constructionPart;
	}

	public String getConstructionPart() 
	{
		return constructionPart;
	}
	public void setConstructionAddress(String constructionAddress) 
	{
		this.constructionAddress = constructionAddress;
	}

	public String getConstructionAddress() 
	{
		return constructionAddress;
	}
	public void setPouringWay(String pouringWay) 
	{
		this.pouringWay = pouringWay;
	}

	public String getPouringWay() 
	{
		return pouringWay;
	}
	public void setConcreteLabel(String concreteLabel) 
	{
		this.concreteLabel = concreteLabel;
	}

	public String getConcreteLabel() 
	{
		return concreteLabel;
	}
	public void setSlump(String slump) 
	{
		this.slump = slump;
	}

	public String getSlump() 
	{
		return slump;
	}
	public void setDistance(Double distance) 
	{
		this.distance = distance;
	}

	public Double getDistance() 
	{
		return distance;
	}
	public void setDeliveryMode(String deliveryMode) 
	{
		this.deliveryMode = deliveryMode;
	}

	public String getDeliveryMode() 
	{
		return deliveryMode;
	}
	public void setCementGrade(String cementGrade) 
	{
		this.cementGrade = cementGrade;
	}

	public String getCementGrade() 
	{
		return cementGrade;
	}
	public void setAGSize(String aGSize) 
	{
		this.aGSize = aGSize;
	}

	public String getAGSize() 
	{
		return aGSize;
	}
	public void setTechRequest(String techRequest) 
	{
		this.techRequest = techRequest;
	}

	public String getTechRequest() 
	{
		return techRequest;
	}
	public void setPeibiScale(String peibiScale) 
	{
		this.peibiScale = peibiScale;
	}

	public String getPeibiScale() 
	{
		return peibiScale;
	}
	public void setContraLeakLevel(String contraLeakLevel) 
	{
		this.contraLeakLevel = contraLeakLevel;
	}

	public String getContraLeakLevel() 
	{
		return contraLeakLevel;
	}
	public void setAdditionSort(String additionSort) 
	{
		this.additionSort = additionSort;
	}

	public String getAdditionSort() 
	{
		return additionSort;
	}
	public void setMixNumber(String mixNumber) 
	{
		this.mixNumber = mixNumber;
	}

	public String getMixNumber() 
	{
		return mixNumber;
	}
	public void setMortarMixNumber(String mortarMixNumber) 
	{
		this.mortarMixNumber = mortarMixNumber;
	}

	public String getMortarMixNumber() 
	{
		return mortarMixNumber;
	}
	public void setPlanAmount(Double planAmount) 
	{
		this.planAmount = planAmount;
	}

	public Double getPlanAmount() 
	{
		return planAmount;
	}
	public void setPlanDateTime(Date planDateTime) 
	{
		this.planDateTime = planDateTime;
	}

	public Date getPlanDateTime() 
	{
		return planDateTime;
	}
	public void setBooker(String booker) 
	{
		this.booker = booker;
	}

	public String getBooker() 
	{
		return booker;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getContact() 
	{
		return contact;
	}
	public void setContactPhone(String contactPhone) 
	{
		this.contactPhone = contactPhone;
	}

	public String getContactPhone() 
	{
		return contactPhone;
	}
	public void setLastModifiedTime(Date lastModifiedTime) 
	{
		this.lastModifiedTime = lastModifiedTime;
	}

	public Date getLastModifiedTime() 
	{
		return lastModifiedTime;
	}
	public void setCreatedTime(Date createdTime) 
	{
		this.createdTime = createdTime;
	}

	public Date getCreatedTime() 
	{
		return createdTime;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setSyncStatus(Integer syncStatus) 
	{
		this.syncStatus = syncStatus;
	}

	public Integer getSyncStatus() 
	{
		return syncStatus;
	}
	public void setSyncStatus2(Integer syncStatus2) 
	{
		this.syncStatus2 = syncStatus2;
	}

	public Integer getSyncStatus2() 
	{
		return syncStatus2;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sID", getSID())
            .append("productLine", getProductLine())
            .append("taskNumber", getTaskNumber())
            .append("contractNumber", getContractNumber())
            .append("customerName", getCustomerName())
            .append("projectName", getProjectName())
            .append("unitName", getUnitName())
            .append("constructionPart", getConstructionPart())
            .append("constructionAddress", getConstructionAddress())
            .append("pouringWay", getPouringWay())
            .append("concreteLabel", getConcreteLabel())
            .append("slump", getSlump())
            .append("distance", getDistance())
            .append("deliveryMode", getDeliveryMode())
            .append("cementGrade", getCementGrade())
            .append("aGSize", getAGSize())
            .append("techRequest", getTechRequest())
            .append("peibiScale", getPeibiScale())
            .append("contraLeakLevel", getContraLeakLevel())
            .append("additionSort", getAdditionSort())
            .append("mixNumber", getMixNumber())
            .append("mortarMixNumber", getMortarMixNumber())
            .append("planAmount", getPlanAmount())
            .append("planDateTime", getPlanDateTime())
            .append("booker", getBooker())
            .append("contact", getContact())
            .append("contactPhone", getContactPhone())
            .append("lastModifiedTime", getLastModifiedTime())
            .append("remark", getRemark())
            .append("createdTime", getCreatedTime())
            .append("type", getType())
            .append("syncStatus", getSyncStatus())
            .append("syncStatus2", getSyncStatus2())
            .toString();
    }
}
