package com.ruoyi.project.stock.receiveGoods.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.math.BigDecimal;

/**
 * 货品表 tb_receive_goods
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveGoods extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 商品id */
	private Long goodsid;
	/** 名称 */
	private String goodsname;
	/** 价格 */
	private BigDecimal price;
	private String materialType;

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public void setGoodsid(Long goodsid)
	{
		this.goodsid = goodsid;
	}

	public Long getGoodsid() 
	{
		return goodsid;
	}
	public void setGoodsname(String goodsname) 
	{
		this.goodsname = goodsname;
	}

	public String getGoodsname() 
	{
		return goodsname;
	}
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("goodsid", getGoodsid())
            .append("goodsname", getGoodsname())
            .append("price", getPrice())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
