package com.ruoyi.project.stock.receiveRemark.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 收货备注表 tb_receive_remark
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveRemark extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("remark", getRemark())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
