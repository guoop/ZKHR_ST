package com.ruoyi.project.duties.taskNotification.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 消息表 tb_task_notification
 * 
 * @author admin
 * @date 2019-04-29
 */
public class TaskNotification extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long id;
	/** 消息内容 */
	private String msg;
	/** 通知id */
	private Long notifyId;
	private String sqlWhere;
	private String msgtype;

	public String getMsgtype() {
		return msgtype;
	}

	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setMsg(String msg) 
	{
		this.msg = msg;
	}

	public String getMsg() 
	{
		return msg;
	}
	public void setNotifyId(Long notifyId) 
	{
		this.notifyId = notifyId;
	}

	public Long getNotifyId() 
	{
		return notifyId;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("msg", getMsg())
            .append("notifyId", getNotifyId())
            .append("createTime", getCreateTime())
            .toString();
    }
}
