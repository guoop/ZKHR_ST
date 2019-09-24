package com.ruoyi.project.profile.profileroom.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;
import java.math.BigDecimal;

/**
 * 任务资料表 tb_profileroom
 * 
 * @author admin
 * @date 2019-05-30
 */
public class Profileroom extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	private Long taskId;
	/** 工程名称 */
	private String projectName;
	/** 工程部位 */
	private String projectPart;
	/** 强度等级 */
	private String productKind;
	/** 施工单位 */
	private String doPart;
	/** 发车时间 */
	private Date firstCartime;
	/** 累计放量 */
	private BigDecimal ljfangliang;
	private Long status;

	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setProjectName(String projectName) 
	{
		this.projectName = projectName;
	}

	public String getProjectName() 
	{
		return projectName;
	}
	public void setProjectPart(String projectPart) 
	{
		this.projectPart = projectPart;
	}

	public String getProjectPart() 
	{
		return projectPart;
	}
	public void setProductKind(String productKind) 
	{
		this.productKind = productKind;
	}

	public String getProductKind() 
	{
		return productKind;
	}
	public void setDoPart(String doPart) 
	{
		this.doPart = doPart;
	}

	public String getDoPart() 
	{
		return doPart;
	}
	public void setFirstCartime(Date firstCartime) 
	{
		this.firstCartime = firstCartime;
	}

	public Date getFirstCartime() 
	{
		return firstCartime;
	}
	public void setLjfangliang(BigDecimal ljfangliang) 
	{
		this.ljfangliang = ljfangliang;
	}

	public BigDecimal getLjfangliang() 
	{
		return ljfangliang;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("projectName", getProjectName())
            .append("projectPart", getProjectPart())
            .append("productKind", getProductKind())
            .append("doPart", getDoPart())
            .append("firstCartime", getFirstCartime())
            .append("ljfangliang", getLjfangliang())
            .toString();
    }
}
