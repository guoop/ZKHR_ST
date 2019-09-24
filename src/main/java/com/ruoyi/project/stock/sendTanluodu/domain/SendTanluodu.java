package com.ruoyi.project.stock.sendTanluodu.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 坍落度表 tb_send_tanluodu
 * 
 * @author admin
 * @date 2019-08-14
 */
public class SendTanluodu extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 坍落度 */
	private String tanluodu;
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
	public void setTanluodu(String tanluodu) 
	{
		this.tanluodu = tanluodu;
	}

	public String getTanluodu() 
	{
		return tanluodu;
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
            .append("tanluodu", getTanluodu())
            .append("userId", getUserId())
            .append("deptId", getDeptId())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
