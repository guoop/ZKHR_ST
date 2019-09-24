package com.ruoyi.project.stock.receiveOffice.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 收货单位表 tb_receive_office
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveOffice extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 单位名称 */
	private String officeName;
	/** 地址 */
	private String addr;
	/** 联系方式 */
	private String contact;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setOfficeName(String officeName) 
	{
		this.officeName = officeName;
	}

	public String getOfficeName() 
	{
		return officeName;
	}
	public void setAddr(String addr) 
	{
		this.addr = addr;
	}

	public String getAddr() 
	{
		return addr;
	}
	public void setContact(String contact) 
	{
		this.contact = contact;
	}

	public String getContact() 
	{
		return contact;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("officeName", getOfficeName())
            .append("addr", getAddr())
            .append("contact", getContact())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
