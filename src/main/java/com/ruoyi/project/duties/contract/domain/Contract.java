package com.ruoyi.project.duties.contract.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 合同表 tb_contract
 * 
 * @author admin
 * @date 2019-08-07
 */
public class Contract extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long conId;
	/** 合同编号 */
	private String conNo;
	/** 合同名称 */
	private String conName;
	/** 合同类型 */
	private Integer conType;
	/** 合同金额 */
	private BigDecimal conMoney;
	/** 合同状态（0：正常，1：结算，2：终止） */
	private Integer conStatus;
	/** 业主单位 */
	private String yzUnitName;
	/** 监理单位 */
	private String jlUnitName;
	/** 承包单位 */
	private String cbUnitName;
	/** 签订日期 */
	private Date signDate;
	/** 计划启动日期 */
	private Date planStartDate;
	/** 计划竣工日期 */
	private Date planEndDate;
	/** 创建人 */
	private String creater;
	/** 是否删除（0：正常，1已删除） */
	private Integer isDelete;

	public void setConId(Long conId) 
	{
		this.conId = conId;
	}

	public Long getConId() 
	{
		return conId;
	}
	public void setConNo(String conNo)
	{
		this.conNo = conNo;
	}

	public String getConNo()
	{
		return conNo;
	}
	public void setConName(String conName) 
	{
		this.conName = conName;
	}

	public String getConName() 
	{
		return conName;
	}
	public void setConType(Integer conType) 
	{
		this.conType = conType;
	}

	public Integer getConType() 
	{
		return conType;
	}
	public void setConMoney(BigDecimal conMoney) 
	{
		this.conMoney = conMoney;
	}

	public BigDecimal getConMoney() 
	{
		return conMoney;
	}
	public void setConStatus(Integer conStatus) 
	{
		this.conStatus = conStatus;
	}

	public Integer getConStatus() 
	{
		return conStatus;
	}
	public void setYzUnitName(String yzUnitName) 
	{
		this.yzUnitName = yzUnitName;
	}

	public String getYzUnitName() 
	{
		return yzUnitName;
	}
	public void setJlUnitName(String jlUnitName) 
	{
		this.jlUnitName = jlUnitName;
	}

	public String getJlUnitName() 
	{
		return jlUnitName;
	}
	public void setCbUnitName(String cbUnitName) 
	{
		this.cbUnitName = cbUnitName;
	}

	public String getCbUnitName() 
	{
		return cbUnitName;
	}
	public void setSignDate(Date signDate) 
	{
		this.signDate = signDate;
	}

	public Date getSignDate() 
	{
		return signDate;
	}
	public void setPlanStartDate(Date planStartDate) 
	{
		this.planStartDate = planStartDate;
	}

	public Date getPlanStartDate() 
	{
		return planStartDate;
	}
	public void setPlanEndDate(Date planEndDate) 
	{
		this.planEndDate = planEndDate;
	}

	public Date getPlanEndDate() 
	{
		return planEndDate;
	}
	public void setCreater(String creater) 
	{
		this.creater = creater;
	}

	public String getCreater() 
	{
		return creater;
	}
	public void setIsDelete(Integer isDelete) 
	{
		this.isDelete = isDelete;
	}

	public Integer getIsDelete() 
	{
		return isDelete;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("conId", getConId())
            .append("conNo", getConNo())
            .append("conName", getConName())
            .append("conType", getConType())
            .append("conMoney", getConMoney())
            .append("conStatus", getConStatus())
            .append("yzUnitName", getYzUnitName())
            .append("jlUnitName", getJlUnitName())
            .append("cbUnitName", getCbUnitName())
            .append("signDate", getSignDate())
            .append("planStartDate", getPlanStartDate())
            .append("planEndDate", getPlanEndDate())
            .append("createTime", getCreateTime())
            .append("creater", getCreater())
            .append("isDelete", getIsDelete())
            .toString();
    }
}
