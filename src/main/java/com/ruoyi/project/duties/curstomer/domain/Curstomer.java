package com.ruoyi.project.duties.curstomer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 客户表 tb_curstomer
 * 
 * @author admin
 * @date 2019-08-07
 */
public class Curstomer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 主键 */
	private Long cusId;
	/** 客户名称 */
	private String cusName;
	/** 客户电话 */
	private String cusPhone;
	/** 客户地址 */
	private String cusAddress;
	/** 客户状态（0开启，2关闭） */
	private Integer cusStatus;
	/** 创建人 */
	private String creater;

	public void setCusId(Long cusId) 
	{
		this.cusId = cusId;
	}

	public Long getCusId() 
	{
		return cusId;
	}
	public void setCusName(String cusName) 
	{
		this.cusName = cusName;
	}

	public String getCusName() 
	{
		return cusName;
	}
	public void setCusPhone(String cusPhone) 
	{
		this.cusPhone = cusPhone;
	}

	public String getCusPhone() 
	{
		return cusPhone;
	}
	public void setCusAddress(String cusAddress) 
	{
		this.cusAddress = cusAddress;
	}

	public String getCusAddress() 
	{
		return cusAddress;
	}
	public void setCusStatus(Integer cusStatus) 
	{
		this.cusStatus = cusStatus;
	}

	public Integer getCusStatus() 
	{
		return cusStatus;
	}
	public void setCreater(String creater) 
	{
		this.creater = creater;
	}

	public String getCreater() 
	{
		return creater;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("cusId", getCusId())
            .append("cusName", getCusName())
            .append("cusPhone", getCusPhone())
            .append("cusAddress", getCusAddress())
            .append("cusStatus", getCusStatus())
            .append("createTime", getCreateTime())
            .append("creater", getCreater())
            .toString();
    }
}
