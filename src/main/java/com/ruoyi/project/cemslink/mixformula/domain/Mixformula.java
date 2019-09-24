package com.ruoyi.project.cemslink.mixformula.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 配合比数据表 mixformula
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Mixformula extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** 配合比编号 */
	private String mixNumber;
	/** 是否润泵砂浆 */
	private Integer isMortar;
	/** 砼标号 */
	private String concreteLabel;
	/** 浇注方式 */
	private String pouringWay;
	/** 坍落度 */
	private String slump;
	/** 生产线 */
	private Integer productLine;
	/** 预留 */
	private Double mixQnty;
	/** 搅拌时间 */
	private Double mixTime;
	/** 水泥 1 设定量 */
	private Double cement1Dosage;//粉料=水泥
	/** 水泥 2 设定量 */
	private Double cement2Dosage;//粉料=水泥
	/** 水泥 3 设定量 */
	private Double cement3Dosage;
	/** 水泥 4 设定量 */
	private Double cement4Dosage;
	/** 水泥 5 设定量 */
	private Double cement5Dosage;
	/** 掺合料 1 设定量 */
	private Double mix1Dosage;//粉料=粉煤灰
	/** 掺合料 2 设定量 */
	private Double mix2Dosage;//粉料=矿粉
	/** 掺合料 3 设定量 */
	private Double mix3Dosage;//水=污水
	/** 掺合料 4 设定量 */
	private Double mix4Dosage;//
	/** 掺合料 5 设定量 */
	private Double mix5Dosage;
	/** 骨料 1 设定量 */
	private Double aG1Dosage;//骨料=米石
	/** 骨料 2 设定量 */
	private Double aG2Dosage;//骨料=1-2石子
	/** 骨料 3 设定量 */
	private Double aG3Dosage;//骨料=0-5石子
	/** 骨料 4 设定量 */
	private Double aG4Dosage;//骨料=机制砂
	/** 骨料 5 设定量 */
	private Double aG5Dosage;//骨料=面沙
	/** 骨料 6 设定量 */
	private Double aG6Dosage;
	/** 骨料 7 设定量 */
	private Double aG7Dosage;
	/** 骨料 8 设定量 */
	private Double aG8Dosage;
	/** 外加剂 1 设定量 */
	private Double additive1Dosage;//外加剂=外加剂1
	/** 外加剂 2 设定量 */
	private Double additive2Dosage;//外加剂= (此处是空格)外加剂1
	/** 外加剂 3 设定量 */
	private Double additive3Dosage;//外加剂=外加剂2
	/** 外加剂 4 设定量 */
	private Double additive4Dosage;//外加剂=外加剂2
	/** 水 1 设定量 */
	private Double water1Dosage;//水=M1
	/** 水 2 设定量 */
	private Double water2Dosage;//水=M2
	/** 水 3 设定量 */
	private Double water3Dosage;
	/** Type */
	private Integer type;
	/** 同步标识 */
	private Integer syncStatus;
	/** 从机同步标识 */
	private Integer syncStatus2;
	private String sqlWhere;

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public void setSID(Long sID)
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setMixNumber(String mixNumber) 
	{
		this.mixNumber = mixNumber;
	}

	public String getMixNumber() 
	{
		return mixNumber;
	}
	public void setIsMortar(Integer isMortar) 
	{
		this.isMortar = isMortar;
	}

	public Integer getIsMortar() 
	{
		return isMortar;
	}
	public void setConcreteLabel(String concreteLabel) 
	{
		this.concreteLabel = concreteLabel;
	}

	public String getConcreteLabel() 
	{
		return concreteLabel;
	}
	public void setPouringWay(String pouringWay) 
	{
		this.pouringWay = pouringWay;
	}

	public String getPouringWay() 
	{
		return pouringWay;
	}
	public void setSlump(String slump) 
	{
		this.slump = slump;
	}

	public String getSlump() 
	{
		return slump;
	}
	public void setProductLine(Integer productLine) 
	{
		this.productLine = productLine;
	}

	public Integer getProductLine() 
	{
		return productLine;
	}
	public void setMixQnty(Double mixQnty) 
	{
		this.mixQnty = mixQnty;
	}

	public Double getMixQnty() 
	{
		return mixQnty;
	}
	public void setMixTime(Double mixTime) 
	{
		this.mixTime = mixTime;
	}

	public Double getMixTime() 
	{
		return mixTime;
	}
	public void setCement1Dosage(Double cement1Dosage) 
	{
		this.cement1Dosage = cement1Dosage;
	}

	public Double getCement1Dosage() 
	{
		return cement1Dosage;
	}
	public void setCement2Dosage(Double cement2Dosage) 
	{
		this.cement2Dosage = cement2Dosage;
	}

	public Double getCement2Dosage() 
	{
		return cement2Dosage;
	}
	public void setCement3Dosage(Double cement3Dosage) 
	{
		this.cement3Dosage = cement3Dosage;
	}

	public Double getCement3Dosage() 
	{
		return cement3Dosage;
	}
	public void setCement4Dosage(Double cement4Dosage) 
	{
		this.cement4Dosage = cement4Dosage;
	}

	public Double getCement4Dosage() 
	{
		return cement4Dosage;
	}
	public void setCement5Dosage(Double cement5Dosage) 
	{
		this.cement5Dosage = cement5Dosage;
	}

	public Double getCement5Dosage() 
	{
		return cement5Dosage;
	}
	public void setMix1Dosage(Double mix1Dosage) 
	{
		this.mix1Dosage = mix1Dosage;
	}

	public Double getMix1Dosage() 
	{
		return mix1Dosage;
	}
	public void setMix2Dosage(Double mix2Dosage) 
	{
		this.mix2Dosage = mix2Dosage;
	}

	public Double getMix2Dosage() 
	{
		return mix2Dosage;
	}
	public void setMix3Dosage(Double mix3Dosage) 
	{
		this.mix3Dosage = mix3Dosage;
	}

	public Double getMix3Dosage() 
	{
		return mix3Dosage;
	}
	public void setMix4Dosage(Double mix4Dosage) 
	{
		this.mix4Dosage = mix4Dosage;
	}

	public Double getMix4Dosage() 
	{
		return mix4Dosage;
	}
	public void setMix5Dosage(Double mix5Dosage) 
	{
		this.mix5Dosage = mix5Dosage;
	}

	public Double getMix5Dosage() 
	{
		return mix5Dosage;
	}
	public void setAG1Dosage(Double aG1Dosage) 
	{
		this.aG1Dosage = aG1Dosage;
	}

	public Double getAG1Dosage() 
	{
		return aG1Dosage;
	}
	public void setAG2Dosage(Double aG2Dosage) 
	{
		this.aG2Dosage = aG2Dosage;
	}

	public Double getAG2Dosage() 
	{
		return aG2Dosage;
	}
	public void setAG3Dosage(Double aG3Dosage) 
	{
		this.aG3Dosage = aG3Dosage;
	}

	public Double getAG3Dosage() 
	{
		return aG3Dosage;
	}
	public void setAG4Dosage(Double aG4Dosage) 
	{
		this.aG4Dosage = aG4Dosage;
	}

	public Double getAG4Dosage() 
	{
		return aG4Dosage;
	}
	public void setAG5Dosage(Double aG5Dosage) 
	{
		this.aG5Dosage = aG5Dosage;
	}

	public Double getAG5Dosage() 
	{
		return aG5Dosage;
	}
	public void setAG6Dosage(Double aG6Dosage) 
	{
		this.aG6Dosage = aG6Dosage;
	}

	public Double getAG6Dosage() 
	{
		return aG6Dosage;
	}
	public void setAG7Dosage(Double aG7Dosage) 
	{
		this.aG7Dosage = aG7Dosage;
	}

	public Double getAG7Dosage() 
	{
		return aG7Dosage;
	}
	public void setAG8Dosage(Double aG8Dosage) 
	{
		this.aG8Dosage = aG8Dosage;
	}

	public Double getAG8Dosage() 
	{
		return aG8Dosage;
	}
	public void setAdditive1Dosage(Double additive1Dosage) 
	{
		this.additive1Dosage = additive1Dosage;
	}

	public Double getAdditive1Dosage() 
	{
		return additive1Dosage;
	}
	public void setAdditive2Dosage(Double additive2Dosage) 
	{
		this.additive2Dosage = additive2Dosage;
	}

	public Double getAdditive2Dosage() 
	{
		return additive2Dosage;
	}
	public void setAdditive3Dosage(Double additive3Dosage) 
	{
		this.additive3Dosage = additive3Dosage;
	}

	public Double getAdditive3Dosage() 
	{
		return additive3Dosage;
	}
	public void setAdditive4Dosage(Double additive4Dosage) 
	{
		this.additive4Dosage = additive4Dosage;
	}

	public Double getAdditive4Dosage() 
	{
		return additive4Dosage;
	}
	public void setWater1Dosage(Double water1Dosage) 
	{
		this.water1Dosage = water1Dosage;
	}

	public Double getWater1Dosage() 
	{
		return water1Dosage;
	}
	public void setWater2Dosage(Double water2Dosage) 
	{
		this.water2Dosage = water2Dosage;
	}

	public Double getWater2Dosage() 
	{
		return water2Dosage;
	}
	public void setWater3Dosage(Double water3Dosage) 
	{
		this.water3Dosage = water3Dosage;
	}

	public Double getWater3Dosage() 
	{
		return water3Dosage;
	}
	public void setType(Integer type) 
	{
		this.type = type;
	}

	public Integer getType() 
	{
		return type;
	}
	public void setSyncStatus(Integer syncStatus) 
	{
		this.syncStatus = syncStatus;
	}

	public Integer getSyncStatus() 
	{
		return syncStatus;
	}
	public void setSyncStatus2(Integer syncStatus2) 
	{
		this.syncStatus2 = syncStatus2;
	}

	public Integer getSyncStatus2() 
	{
		return syncStatus2;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sID", getSID())
            .append("mixNumber", getMixNumber())
            .append("isMortar", getIsMortar())
            .append("concreteLabel", getConcreteLabel())
            .append("pouringWay", getPouringWay())
            .append("slump", getSlump())
            .append("productLine", getProductLine())
            .append("mixQnty", getMixQnty())
            .append("mixTime", getMixTime())
            .append("cement1Dosage", getCement1Dosage())
            .append("cement2Dosage", getCement2Dosage())
            .append("cement3Dosage", getCement3Dosage())
            .append("cement4Dosage", getCement4Dosage())
            .append("cement5Dosage", getCement5Dosage())
            .append("mix1Dosage", getMix1Dosage())
            .append("mix2Dosage", getMix2Dosage())
            .append("mix3Dosage", getMix3Dosage())
            .append("mix4Dosage", getMix4Dosage())
            .append("mix5Dosage", getMix5Dosage())
            .append("aG1Dosage", getAG1Dosage())
            .append("aG2Dosage", getAG2Dosage())
            .append("aG3Dosage", getAG3Dosage())
            .append("aG4Dosage", getAG4Dosage())
            .append("aG5Dosage", getAG5Dosage())
            .append("aG6Dosage", getAG6Dosage())
            .append("aG7Dosage", getAG7Dosage())
            .append("aG8Dosage", getAG8Dosage())
            .append("additive1Dosage", getAdditive1Dosage())
            .append("additive2Dosage", getAdditive2Dosage())
            .append("additive3Dosage", getAdditive3Dosage())
            .append("additive4Dosage", getAdditive4Dosage())
            .append("water1Dosage", getWater1Dosage())
            .append("water2Dosage", getWater2Dosage())
            .append("water3Dosage", getWater3Dosage())
            .append("type", getType())
            .append("syncStatus", getSyncStatus())
            .append("syncStatus2", getSyncStatus2())
            .toString();
    }
}
