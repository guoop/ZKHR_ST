package com.ruoyi.project.duties.tasksUpdatelog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 任务单修改记录表 tb_tasks_updatelog
 * 
 * @author admin
 * @date 2019-06-14
 */
public class TasksUpdatelog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 任务id */
	private Long taskId;
	/** 任务名 */
	private String taskName;
	/** 改前方量 */
	private BigDecimal oriFangliang;
	/** 改后方量 */
	private BigDecimal fangliang;
	/** 改前车次 */
	private Integer oriCarcnt;
	/** 改后车次 */
	private Integer carcnt;
	private String taskNumber;

	public String getTaskNumber() {
		return taskNumber;
	}

	public void setTaskNumber(String taskNumber) {
		this.taskNumber = taskNumber;
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
	public void setTaskName(String taskName) 
	{
		this.taskName = taskName;
	}

	public String getTaskName() 
	{
		return taskName;
	}
	public void setOriFangliang(BigDecimal oriFangliang) 
	{
		this.oriFangliang = oriFangliang;
	}

	public BigDecimal getOriFangliang() 
	{
		return oriFangliang;
	}
	public void setFangliang(BigDecimal fangliang) 
	{
		this.fangliang = fangliang;
	}

	public BigDecimal getFangliang() 
	{
		return fangliang;
	}
	public void setOriCarcnt(Integer oriCarcnt) 
	{
		this.oriCarcnt = oriCarcnt;
	}

	public Integer getOriCarcnt() 
	{
		return oriCarcnt;
	}
	public void setCarcnt(Integer carcnt) 
	{
		this.carcnt = carcnt;
	}

	public Integer getCarcnt() 
	{
		return carcnt;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("taskId", getTaskId())
            .append("taskName", getTaskName())
            .append("oriFangliang", getOriFangliang())
            .append("fangliang", getFangliang())
            .append("oriCarcnt", getOriCarcnt())
            .append("carcnt", getCarcnt())
            .append("updateBy", getUpdateBy())
            .append("createTime", getCreateTime())
            .toString();
    }
}
