package com.ruoyi.project.cemslink.hopper.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * V形送料斗表 hopper
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Hopper extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long iD;
	/**  */
	private Integer lineID;
	/**  */
	private String hopperCode;
	/**  */
	private String hopperName;
	/**  */
	private Integer materialTypeID;
	/**  */
	private Integer materialID;
	/**  */
	private String materialName;
	/**  */
	private String meterName;
	/**  */
	private BigDecimal usageRate;
	/**  */
	private Integer precision;
	/**  */
	private Integer storageID;
	/**  */
	private Integer orderCode;
	/**  */
	private String fieldCode;
	/**  */
	private Integer isEnabled;
	/**  */
	private Integer syncStatus;
	/**  */
	private Integer syncStatus2;

	public void setID(Long iD) 
	{
		this.iD = iD;
	}

	public Long getID() 
	{
		return iD;
	}
	public void setLineID(Integer lineID) 
	{
		this.lineID = lineID;
	}

	public Integer getLineID() 
	{
		return lineID;
	}
	public void setHopperCode(String hopperCode) 
	{
		this.hopperCode = hopperCode;
	}

	public String getHopperCode() 
	{
		return hopperCode;
	}
	public void setHopperName(String hopperName) 
	{
		this.hopperName = hopperName;
	}

	public String getHopperName() 
	{
		return hopperName;
	}
	public void setMaterialTypeID(Integer materialTypeID) 
	{
		this.materialTypeID = materialTypeID;
	}

	public Integer getMaterialTypeID() 
	{
		return materialTypeID;
	}
	public void setMaterialID(Integer materialID) 
	{
		this.materialID = materialID;
	}

	public Integer getMaterialID() 
	{
		return materialID;
	}
	public void setMaterialName(String materialName) 
	{
		this.materialName = materialName;
	}

	public String getMaterialName() 
	{
		return materialName;
	}
	public void setMeterName(String meterName) 
	{
		this.meterName = meterName;
	}

	public String getMeterName() 
	{
		return meterName;
	}
	public void setUsageRate(BigDecimal usageRate) 
	{
		this.usageRate = usageRate;
	}

	public BigDecimal getUsageRate() 
	{
		return usageRate;
	}
	public void setPrecision(Integer precision) 
	{
		this.precision = precision;
	}

	public Integer getPrecision() 
	{
		return precision;
	}
	public void setStorageID(Integer storageID) 
	{
		this.storageID = storageID;
	}

	public Integer getStorageID() 
	{
		return storageID;
	}
	public void setOrderCode(Integer orderCode) 
	{
		this.orderCode = orderCode;
	}

	public Integer getOrderCode() 
	{
		return orderCode;
	}
	public void setFieldCode(String fieldCode) 
	{
		this.fieldCode = fieldCode;
	}

	public String getFieldCode() 
	{
		return fieldCode;
	}
	public void setIsEnabled(Integer isEnabled) 
	{
		this.isEnabled = isEnabled;
	}

	public Integer getIsEnabled() 
	{
		return isEnabled;
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
            .append("iD", getID())
            .append("lineID", getLineID())
            .append("hopperCode", getHopperCode())
            .append("hopperName", getHopperName())
            .append("materialTypeID", getMaterialTypeID())
            .append("materialID", getMaterialID())
            .append("materialName", getMaterialName())
            .append("meterName", getMeterName())
            .append("usageRate", getUsageRate())
            .append("precision", getPrecision())
            .append("storageID", getStorageID())
            .append("orderCode", getOrderCode())
            .append("fieldCode", getFieldCode())
            .append("isEnabled", getIsEnabled())
            .append("syncStatus", getSyncStatus())
            .append("syncStatus2", getSyncStatus2())
            .toString();
    }
}
