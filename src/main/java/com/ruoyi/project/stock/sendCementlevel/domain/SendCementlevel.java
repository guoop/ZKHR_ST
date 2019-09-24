package com.ruoyi.project.stock.sendCementlevel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 水泥标号表 tb_send_cementlevel
 * 
 * @author admin
 * @date 2019-08-14
 */
public class SendCementlevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 水泥标号 */
	private String cementlevel;
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
	public void setCementlevel(String cementlevel) 
	{
		this.cementlevel = cementlevel;
	}

	public String getCementlevel() 
	{
		return cementlevel;
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
            .append("cementlevel", getCementlevel())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
