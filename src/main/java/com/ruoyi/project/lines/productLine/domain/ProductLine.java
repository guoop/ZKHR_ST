package com.ruoyi.project.lines.productLine.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 产线表 tb_product_line
 * 
 * @author admin
 * @date 2019-06-02
 */
public class ProductLine extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 产线 */
	private Integer productLine;
	/** 0:青石  1:卵石 */
	private Integer stoneType;
	/** 主机状态0.正在生产，1.关闭生产 */
	private Integer state;
	private Double mixheight;//最低门的高度
	private String mixDoorName;//低门名称
	private String maxDoorName;//高门名称

	public String getMixDoorName() {
		return mixDoorName;
	}

	public void setMixDoorName(String mixDoorName) {
		this.mixDoorName = mixDoorName;
	}

	public String getMaxDoorName() {
		return maxDoorName;
	}

	public void setMaxDoorName(String maxDoorName) {
		this.maxDoorName = maxDoorName;
	}

	public Double getMixheight() {
		return mixheight;
	}

	public void setMixheight(Double mixheight) {
		this.mixheight = mixheight;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setProductLine(Integer productLine) 
	{
		this.productLine = productLine;
	}

	public Integer getProductLine() 
	{
		return productLine;
	}
	public void setStoneType(Integer stoneType) 
	{
		this.stoneType = stoneType;
	}

	public Integer getStoneType() 
	{
		return stoneType;
	}
	public void setState(Integer state) 
	{
		this.state = state;
	}

	public Integer getState() 
	{
		return state;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("productLine", getProductLine())
            .append("stoneType", getStoneType())
            .append("state", getState())
            .toString();
    }
}
