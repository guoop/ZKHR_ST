package com.ruoyi.project.cems.queue.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 排队表 tb_queue
 * 
 * @author admin
 * @date 2019-05-13
 */
public class Queue extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** id */
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
            .toString();
    }
}
