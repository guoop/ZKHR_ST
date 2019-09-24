package com.ruoyi.project.stock.receiveStock.domain;

import com.ruoyi.framework.aspectj.lang.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.beans.Transient;
import java.math.BigDecimal;

/**
 * 进货库存表 tb_receive_stock
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveStock extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	@Excel(name = "序号")
	/**序列号*/
	private String sno;
	@Excel(name="供货单位")
	/** 收货单位 */
	private String offerDept;
	/** 车牌号 */
	@Excel(name="车牌号")
	private String carBrand;
	/** 货名 */
	@Excel(name="货名")
	private String goodsName;
	/** 送货人 */
	@Excel(name="送货人")
	private String driver;
	/** 等级 */
	@Excel(name="等级")
	private String level;
	/** 收货单位 */
	@Excel(name="收货单位")
	private String receiveDept;
	/** 收货人 */
	@Excel(name="收货人")
	private String receiver;
	/** 产地 */
	@Excel(name="产地")
	private String place;
	/** 验收人 */
	@Excel(name="验收人")
	private String acceptor;
	/** 监磅员 */
	@Excel(name="监磅员")
	private String bangyuan;
	/** 毛重 */
	@Excel(name="毛重")
	private BigDecimal grossWeight;
	/** 皮重 */
	@Excel(name="皮重")
	private BigDecimal carWeight;
	/** 净重 */
	@Excel(name="净重")
	private BigDecimal netWeight;
	/** 扣杂 */
	@Excel(name="扣杂")
	private BigDecimal takeOff;
	/** 扣杂百分比 */
	@Excel(name="扣杂百分比")
	private BigDecimal takeoffRate;
	@Excel(name="结算净重")
	/** 结算净重 */
	private BigDecimal settleNetweight;
	/** 原发净重 */
	@Excel(name="原发净重")
	private BigDecimal sendNetweight;
	/** 方量 */

	private BigDecimal fangliang;
	/** 单价 */
	@Excel(name="单价")
	private BigDecimal price;
	/** 总金额 */
	@Excel(name="总金额")
	private BigDecimal totalFee;
	private String sqlWhere;
	private String weightType;
	private String bangNo;
	private String materialType;

	public String getMaterialType() {
		return materialType;
	}

	public void setMaterialType(String materialType) {
		this.materialType = materialType;
	}

	public String getSno() {
		return sno;
	}

	public void setSno(String sno) {
		this.sno = sno;
	}

	public String getBangNo() {
		return bangNo;
	}

	public void setBangNo(String bangNo) {
		this.bangNo = bangNo;
	}

	public String getWeightType() {
		return weightType;
	}

	public void setWeightType(String weightType) {
		this.weightType = weightType;
	}

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setOfferDept(String offerDept) 
	{
		this.offerDept = offerDept;
	}

	public String getOfferDept() 
	{
		return offerDept;
	}
	public void setCarBrand(String carBrand) 
	{
		this.carBrand = carBrand;
	}

	public String getCarBrand() 
	{
		return carBrand;
	}
	public void setGoodsName(String goodsName) 
	{
		this.goodsName = goodsName;
	}

	public String getGoodsName() 
	{
		return goodsName;
	}
	public void setDriver(String driver) 
	{
		this.driver = driver;
	}

	public String getDriver() 
	{
		return driver;
	}
	public void setLevel(String level) 
	{
		this.level = level;
	}

	public String getLevel() 
	{
		return level;
	}
	public void setReceiveDept(String receiveDept) 
	{
		this.receiveDept = receiveDept;
	}

	public String getReceiveDept() 
	{
		return receiveDept;
	}
	public void setReceiver(String receiver) 
	{
		this.receiver = receiver;
	}

	public String getReceiver() 
	{
		return receiver;
	}
	public void setPlace(String place) 
	{
		this.place = place;
	}

	public String getPlace() 
	{
		return place;
	}
	public void setAcceptor(String acceptor) 
	{
		this.acceptor = acceptor;
	}

	public String getAcceptor() 
	{
		return acceptor;
	}
	public void setBangyuan(String bangyuan) 
	{
		this.bangyuan = bangyuan;
	}

	public String getBangyuan() 
	{
		return bangyuan;
	}
	public void setGrossWeight(BigDecimal grossWeight) 
	{
		this.grossWeight = grossWeight;
	}

	public BigDecimal getGrossWeight() 
	{
		return grossWeight;
	}
	public void setCarWeight(BigDecimal carWeight) 
	{
		this.carWeight = carWeight;
	}

	public BigDecimal getCarWeight() 
	{
		return carWeight;
	}
	public void setNetWeight(BigDecimal netWeight) 
	{
		this.netWeight = netWeight;
	}

	public BigDecimal getNetWeight() 
	{
		return netWeight;
	}
	public void setTakeOff(BigDecimal takeOff) 
	{
		this.takeOff = takeOff;
	}

	public BigDecimal getTakeOff() 
	{
		return takeOff;
	}
	public void setTakeoffRate(BigDecimal takeoffRate) 
	{
		this.takeoffRate = takeoffRate;
	}

	public BigDecimal getTakeoffRate() 
	{
		return takeoffRate;
	}
	public void setSettleNetweight(BigDecimal settleNetweight) 
	{
		this.settleNetweight = settleNetweight;
	}

	public BigDecimal getSettleNetweight() 
	{
		return settleNetweight;
	}
	public void setSendNetweight(BigDecimal sendNetweight) 
	{
		this.sendNetweight = sendNetweight;
	}

	public BigDecimal getSendNetweight() 
	{
		return sendNetweight;
	}
	public void setFangliang(BigDecimal fangliang) 
	{
		this.fangliang = fangliang;
	}

	public BigDecimal getFangliang() 
	{
		return fangliang;
	}
	public void setPrice(BigDecimal price) 
	{
		this.price = price;
	}

	public BigDecimal getPrice() 
	{
		return price;
	}
	public void setTotalFee(BigDecimal totalFee) 
	{
		this.totalFee = totalFee;
	}

	public BigDecimal getTotalFee() 
	{
		return totalFee;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("offerDept", getOfferDept())
            .append("carBrand", getCarBrand())
            .append("goodsName", getGoodsName())
            .append("driver", getDriver())
            .append("level", getLevel())
            .append("receiveDept", getReceiveDept())
            .append("receiver", getReceiver())
            .append("place", getPlace())
            .append("acceptor", getAcceptor())
            .append("bangyuan", getBangyuan())
            .append("grossWeight", getGrossWeight())
            .append("carWeight", getCarWeight())
            .append("netWeight", getNetWeight())
            .append("takeOff", getTakeOff())
            .append("takeoffRate", getTakeoffRate())
            .append("settleNetweight", getSettleNetweight())
            .append("sendNetweight", getSendNetweight())
            .append("fangliang", getFangliang())
            .append("price", getPrice())
            .append("totalFee", getTotalFee())
            .append("remark", getRemark())
            .toString();
    }
}
