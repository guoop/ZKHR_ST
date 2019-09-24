package com.ruoyi.project.stock.sendWaterPart.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 工程部位表 tb_send_water_part
 * 
 * @author admin
 * @date 2019-08-14
 */
public class SendWaterPart extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 工程部位 */
	private String partName;
	/** 创建者 */
	private Long userId;
	/** 数据所属 */
	private Long deptId;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setPartName(String partName) 
	{
		this.partName = partName;
	}

	public String getPartName() 
	{
		return partName;
	}
	public void setUserId(Long userId) 
	{
		this.userId = userId;
	}

	public Long getUserId() 
	{
		return userId;
	}
	public void setDeptId(Long deptId) 
	{
		this.deptId = deptId;
	}

	public Long getDeptId() 
	{
		return deptId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("partName", getPartName())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
