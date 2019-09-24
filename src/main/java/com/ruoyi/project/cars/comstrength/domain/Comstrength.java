package com.ruoyi.project.cars.comstrength.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 强度等级表 tb_comstrength
 * 
 * @author admin
 * @date 2019-08-01
 */
public class Comstrength extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 砼强度 */
	private String strengthNo;
	/** 折方系数 */
	private BigDecimal ratio;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setStrengthNo(String strengthNo) 
	{
		this.strengthNo = strengthNo;
	}

	public String getStrengthNo() 
	{
		return strengthNo;
	}
	public void setRatio(BigDecimal ratio) 
	{
		this.ratio = ratio;
	}

	public BigDecimal getRatio() 
	{
		return ratio;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("strengthNo", getStrengthNo())
            .append("ratio", getRatio())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
