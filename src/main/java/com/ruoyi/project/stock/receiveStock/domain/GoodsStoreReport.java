package com.ruoyi.project.stock.receiveStock.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.math.BigDecimal;

/**
 * 库存表
 * 
 * @author admin
 * @date 2019-08-09
 */
public class GoodsStoreReport extends BaseEntity
{
	@Excel(name="货品")
	private String goodsName;
	@Excel(name="进货总重")
	private BigDecimal totalInWeight;
	@Excel(name="出货总重")
	private BigDecimal totalOutWeight;
	@Excel(name="库存")
	private BigDecimal totalBalance;
	private char flag = '0';

	public char getFlag() {
		return flag;
	}

	public void setFlag(char flag) {
		this.flag = flag;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public BigDecimal getTotalInWeight() {
		return totalInWeight;
	}

	public void setTotalInWeight(BigDecimal totalInWeight) {
		this.totalInWeight = totalInWeight;
	}

	public BigDecimal getTotalOutWeight() {
		return totalOutWeight;
	}

	public void setTotalOutWeight(BigDecimal totalOutWeight) {
		this.totalOutWeight = totalOutWeight;
	}

	public BigDecimal getTotalBalance() {
		return totalBalance;
	}

	public void setTotalBalance(BigDecimal totalBalance) {
		this.totalBalance = totalBalance;
	}
	@Override
	public String toString(){
		return this.getGoodsName();
	}
	@Override
	//重写equals方法
	public boolean equals(Object obj){
		if(obj instanceof GoodsStoreReport){
			GoodsStoreReport gobj = (GoodsStoreReport)obj;
			return gobj.getGoodsName().equalsIgnoreCase(this.getGoodsName());
		}
		return false;
	}

	public static void main(String[] args) {
		GoodsStoreReport g1= new GoodsStoreReport();
		g1.setGoodsName("a");
		g1.setTotalInWeight(new BigDecimal(1));
		GoodsStoreReport g2 = new GoodsStoreReport();
		g2.setGoodsName("a");
		g2.setTotalInWeight(new BigDecimal(2));
		System.out.println(g1.equals(g2));
	}
}
