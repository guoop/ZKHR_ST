package com.ruoyi.project.cemslink.preproduction.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 生产指令数据表 preproduction
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Preproduction extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** 运输单号 */
	private String productNumber;
	/** 生产线 */
	private Integer productLine;
	/** 任务单号 */
	private String taskNumber;
	/** 配合比编号 */
	private String mixNumber;
	/** 是否润泵砂浆 */
	private Integer isMortar;
	/** 客户名称 */
	private String customerName;
	/** 工程名称 */
	private String projectName;
	/** 施工单位 */
	private String unitName;
	/** 浇注部位 */
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
	/** 内部车号 */
	private String innerNumber;
	/** 车牌号 */
	private String plateNumber;
	/** 驾驶员 */
	private String driver;
	/** 发货方量 */
	private Double transportAmount;
	/** 本车生产方量 */
	private Double productAmount;
	/** PieceAmount */
	private Double pieceAmount;
	/** ERP运输单号 */
	private Integer pieceCount;
	/** 发货车次 */
	private Integer transportOrder;
	/** 序号 */
	private Integer sort;
	/** 状态:1 为正在生产 3.为排单已手动删除(未生产),4.为排单生产完删除,5.为排单生产完，删除后，打开拌缸下料 */
	private Integer state;
	/** 累计方量 */
	private Double accumulativeAmount;
	/** 工控小票编号 */
	private String ipcProductNumber;
	/** 同步状态 */
	private Integer syncStatus;
	/** 从机同步状态 */
	private Integer syncStatus2;
	private String lat;
	private String lng;
	private String sqlWhere;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public void setSID(Long sID)
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setProductNumber(String productNumber) 
	{
		this.productNumber = productNumber;
	}

	public String getProductNumber() 
	{
		return productNumber;
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
	public void setMixNumber(String mixNumber) 
	{
		this.mixNumber = mixNumber;
	}

	public String getMixNumber() 
	{
		return mixNumber;
	}
	public void setIsMortar(Integer isMortar) 
	{
		this.isMortar = isMortar;
	}

	public Integer getIsMortar() 
	{
		return isMortar;
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
	public void setInnerNumber(String innerNumber) 
	{
		this.innerNumber = innerNumber;
	}

	public String getInnerNumber() 
	{
		return innerNumber;
	}
	public void setPlateNumber(String plateNumber) 
	{
		this.plateNumber = plateNumber;
	}

	public String getPlateNumber() 
	{
		return plateNumber;
	}
	public void setDriver(String driver) 
	{
		this.driver = driver;
	}

	public String getDriver() 
	{
		return driver;
	}
	public void setTransportAmount(Double transportAmount) 
	{
		this.transportAmount = transportAmount;
	}

	public Double getTransportAmount() 
	{
		return transportAmount;
	}
	public void setProductAmount(Double productAmount) 
	{
		this.productAmount = productAmount;
	}

	public Double getProductAmount() 
	{
		return productAmount;
	}
	public void setPieceAmount(Double pieceAmount) 
	{
		this.pieceAmount = pieceAmount;
	}

	public Double getPieceAmount() 
	{
		return pieceAmount;
	}
	public void setPieceCount(Integer pieceCount) 
	{
		this.pieceCount = pieceCount;
	}

	public Integer getPieceCount() 
	{
		return pieceCount;
	}
	public void setTransportOrder(Integer transportOrder) 
	{
		this.transportOrder = transportOrder;
	}

	public Integer getTransportOrder() 
	{
		return transportOrder;
	}
	public void setSort(Integer sort) 
	{
		this.sort = sort;
	}

	public Integer getSort() 
	{
		return sort;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}
	public void setAccumulativeAmount(Double accumulativeAmount) 
	{
		this.accumulativeAmount = accumulativeAmount;
	}

	public Double getAccumulativeAmount() 
	{
		return accumulativeAmount;
	}
	public void setIpcProductNumber(String ipcProductNumber) 
	{
		this.ipcProductNumber = ipcProductNumber;
	}

	public String getIpcProductNumber() 
	{
		return ipcProductNumber;
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
            .append("productNumber", getProductNumber())
            .append("productLine", getProductLine())
            .append("taskNumber", getTaskNumber())
            .append("mixNumber", getMixNumber())
            .append("isMortar", getIsMortar())
            .append("customerName", getCustomerName())
            .append("projectName", getProjectName())
            .append("unitName", getUnitName())
            .append("constructionPart", getConstructionPart())
            .append("constructionAddress", getConstructionAddress())
            .append("pouringWay", getPouringWay())
            .append("concreteLabel", getConcreteLabel())
            .append("slump", getSlump())
            .append("distance", getDistance())
            .append("innerNumber", getInnerNumber())
            .append("plateNumber", getPlateNumber())
            .append("driver", getDriver())
            .append("transportAmount", getTransportAmount())
            .append("productAmount", getProductAmount())
            .append("pieceAmount", getPieceAmount())
            .append("pieceCount", getPieceCount())
            .append("transportOrder", getTransportOrder())
            .append("sort", getSort())
            .append("state", getState())
            .append("accumulativeAmount", getAccumulativeAmount())
            .append("ipcProductNumber", getIpcProductNumber())
            .append("remark", getRemark())
            .append("syncStatus", getSyncStatus())
            .append("syncStatus2", getSyncStatus2())
            .toString();
    }
}
