package com.ruoyi.project.cemslink.operationlogs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * operationlogs表 operationlogs
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Operationlogs extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** Objective */
	private String objective;
	/** KeyNum */
	private String keyNum;
	/** 是否润泵砂浆 */
	private Integer isMortar;
	/** 生产线 */
	private Integer lineID;
	/** 操作 */
	private Integer operation;
	/** 操作员 */
	private String operator;
	/** 同步标识 */
	private Integer syncState;
	/** 错误日志 */
	private String errLog;

	public void setSID(Long sID) 
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setObjective(String objective) 
	{
		this.objective = objective;
	}

	public String getObjective() 
	{
		return objective;
	}
	public void setKeyNum(String keyNum) 
	{
		this.keyNum = keyNum;
	}

	public String getKeyNum() 
	{
		return keyNum;
	}
	public void setIsMortar(Integer isMortar) 
	{
		this.isMortar = isMortar;
	}

	public Integer getIsMortar() 
	{
		return isMortar;
	}
	public void setLineID(Integer lineID) 
	{
		this.lineID = lineID;
	}

	public Integer getLineID() 
	{
		return lineID;
	}
	public void setOperation(Integer operation) 
	{
		this.operation = operation;
	}

	public Integer getOperation() 
	{
		return operation;
	}
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	public String getOperator() 
	{
		return operator;
	}
	public void setSyncState(Integer syncState) 
	{
		this.syncState = syncState;
	}

	public Integer getSyncState() 
	{
		return syncState;
	}
	public void setErrLog(String errLog) 
	{
		this.errLog = errLog;
	}

	public String getErrLog() 
	{
		return errLog;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sID", getSID())
            .append("createTime", getCreateTime())
            .append("objective", getObjective())
            .append("keyNum", getKeyNum())
            .append("isMortar", getIsMortar())
            .append("lineID", getLineID())
            .append("operation", getOperation())
            .append("operator", getOperator())
            .append("syncState", getSyncState())
            .append("errLog", getErrLog())
            .append("remark", getRemark())
            .toString();
    }
}
