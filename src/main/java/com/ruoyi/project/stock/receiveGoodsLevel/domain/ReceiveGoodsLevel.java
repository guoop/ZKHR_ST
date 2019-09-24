package com.ruoyi.project.stock.receiveGoodsLevel.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 货品等级表 tb_receive_goods_level
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveGoodsLevel extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 等级 */
	private String levelName;
	/** 商品id */
	private Long goodsId;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setLevelName(String levelName) 
	{
		this.levelName = levelName;
	}

	public String getLevelName() 
	{
		return levelName;
	}
	public void setGoodsId(Long goodsId) 
	{
		this.goodsId = goodsId;
	}

	public Long getGoodsId() 
	{
		return goodsId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("levelName", getLevelName())
            .append("goodsId", getGoodsId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
