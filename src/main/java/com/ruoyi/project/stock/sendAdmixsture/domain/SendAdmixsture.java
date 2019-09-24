package com.ruoyi.project.stock.sendAdmixsture.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 外加剂表 tb_send_admixsture
 * 
 * @author admin
 * @date 2019-08-14
 */
public class SendAdmixsture extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 外加剂 */
	private String mixture;
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
	public void setMixture(String mixture) 
	{
		this.mixture = mixture;
	}

	public String getMixture() 
	{
		return mixture;
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
            .append("mixture", getMixture())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("updateBy", getUpdateBy())
            .toString();
    }
}
