package com.ruoyi.project.cars.tasksMixformula.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 任务配比产线关联表 tb_tasks_mixformula
 * 
 * @author admin
 * @date 2019-07-04
 */
public class TasksMixformula extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 任务单id */
	private Long taskId;
	/** 配比单号 */
	private String mixNumber;
	private Integer syncstatus;
	/** 产线号 */
	private Integer productLine;

	public Integer getSyncstatus() {
		return syncstatus;
	}

	public void setSyncstatus(Integer syncstatus) {
		this.syncstatus = syncstatus;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setTaskId(Long taskId) 
	{
		this.taskId = taskId;
	}

	public Long getTaskId() 
	{
		return taskId;
	}
	public void setMixNumber(String mixNumber) 
	{
		this.mixNumber = mixNumber;
	}

	public String getMixNumber() 
	{
		return mixNumber;
	}
	public void setProductLine(Integer productLine) 
	{
		this.productLine = productLine;
	}

	public Integer getProductLine() 
	{
		return productLine;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("mixNumber", getMixNumber())
            .append("productLine", getProductLine())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
