package com.ruoyi.project.stock.receiveOffer.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 供货单位表 tb_receive_offer
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveOffer extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 单位名称 */
	private String offerName;
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
	public void setOfferName(String offerName) 
	{
		this.offerName = offerName;
	}

	public String getOfferName() 
	{
		return offerName;
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
            .append("offerName", getOfferName())
            .append("addr", getAddr())
            .append("contact", getContact())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
