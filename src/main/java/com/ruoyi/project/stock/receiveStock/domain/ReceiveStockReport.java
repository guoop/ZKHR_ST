package com.ruoyi.project.stock.receiveStock.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.math.BigDecimal;

/**
 * 进货库存表 tb_receive_stock
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveStockReport extends BaseEntity
{
	@Excel(name="物料名称")
	private String goodsName;
	@Excel(name="原材料种类",readConverterExp = "admixtureB=外加剂B料,admixtureA=外加剂A料,ag=骨料,cement=粉料")
	private BigDecimal materialType;
	@Excel(name="总皮重",suffix = "吨")
	private BigDecimal totalCarWeight;
	@Excel(name="总毛重",suffix = "吨")
	private BigDecimal totalGrossWeight;
	@Excel(name="总净重",suffix = "吨")
	private BigDecimal totalNetWeight;
	@Excel(name="总金额",suffix = "元")
	private BigDecimal totalAmount;

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getMaterialType() {
		return materialType;
	}

	public void setMaterialType(BigDecimal materialType) {
		this.materialType = materialType;
	}

	public BigDecimal getTotalCarWeight() {
		return totalCarWeight;
	}

	public void setTotalCarWeight(BigDecimal totalCarWeight) {
		this.totalCarWeight = totalCarWeight;
	}

	public BigDecimal getTotalGrossWeight() {
		return totalGrossWeight;
	}

	public void setTotalGrossWeight(BigDecimal totalGrossWeight) {
		this.totalGrossWeight = totalGrossWeight;
	}

	public BigDecimal getTotalNetWeight() {
		return totalNetWeight;
	}

	public void setTotalNetWeight(BigDecimal totalNetWeight) {
		this.totalNetWeight = totalNetWeight;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
}
