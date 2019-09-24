package com.ruoyi.project.cemslink.consumption.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 生产消耗数据表 consumption
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Consumption extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** 生产线 */
	private Long productLine;
	/** ERP运输单号 */
	private String erpProductNumber;
	/** 工控小票编号 */
	private String ipcProductNumber;
	/** 生产盘编号 */
	private String pieceNumber;
	/** 本车盘号 */
	private Integer pieceOrder;
	/** 本盘生产方量 */
	private Double pieceAmount;
	/** ERP运输单号 */
	private Double vehiAmount;
	/** ERP运输单号 */
	private String vehiNum;
	/** ERP运输单号 */
	private String driver;
	/** 本盘生产时间 */
	private Date productDateTime;
	/** 任务单号 */
	private String taskNumber;
	/** 本车配合比编号 */
	private String mixNumber;
	/** 操作员 */
	private String operator;
	/** 砼标号 */
	private String concreteLabel;
	/** 是否润泵砂浆 */
	private Integer isMortar;
	/** 水泥 1 实际用量 */
	private Double cementActualDosage1;
	/** 水泥 1 设定用量 */
	private Double cementDesignDosage1;
	/** 水泥 2 实际用量 */
	private Double cementActualDosage2;
	/** 水泥 2 设定用量 */
	private Double cementDesignDosage2;
	/** 水泥 3 实际用量 */
	private Double cementActualDosage3;
	/** 水泥 3 设定用量 */
	private Double cementDesignDosage3;
	/** 水泥 4 实际用量 */
	private Double cementActualDosage4;
	/** 水泥 4 设定用量 */
	private Double cementDesignDosage4;
	/** 水泥 5 实际用量 */
	private Double cementActualDosage5;
	/** 水泥 5 设定用量 */
	private Double cementDesignDosage5;
	/** 掺合料 1 实际用 */
	private Double mixActualDosage1;
	/** 掺合料 1 设定用 */
	private Double mixDesignDosage1;
	/** 掺合料 2 实际用 */
	private Double mixActualDosage2;
	/** 掺合料 2 设定用 */
	private Double mixDesignDosage2;
	/** 掺合料 3 实际用 */
	private Double mixActualDosage3;
	/** 掺合料 3 设定用 */
	private Double mixDesignDosage3;
	/** 掺合料 4 实际用 */
	private Double mixActualDosage4;
	/** 掺合料 4 设定用 */
	private Double mixDesignDosage4;
	/** 掺合料 5 实际用 */
	private Double mixActualDosage5;
	/** 掺合料 5 设定用 */
	private Double mixDesignDosage5;
	/** 骨料 1 实际用量 */
	private Double aGActualDosage1;
	/** 骨料 1 设定用量 */
	private Double aGDesignDosage1;
	/** AGHSL1 */
	private Double aGHSL1;
	/** 骨料 2 实际用量 */
	private Double aGActualDosage2;
	/** 骨料 2 设定用量 */
	private Double aGDesignDosage2;
	/** AGHSL2 */
	private Double aGHSL2;
	/** 骨料 3 实际用量 */
	private Double aGActualDosage3;
	/** 骨料 3 设定用量 */
	private Double aGDesignDosage3;
	/** AGHSL3 */
	private Double aGHSL3;
	/** 骨料 4 实际用量 */
	private Double aGActualDosage4;
	/** 骨料 4 设定用量 */
	private Double aGDesignDosage4;
	/** AGHSL4 */
	private Double aGHSL4;
	/** 骨料 5 实际用量 */
	private Double aGActualDosage5;
	/** 骨料 5 设计用量 */
	private Double aGDesignDosage5;
	/** AGHSL5 */
	private Double aGHSL5;
	/** 骨料 6 实际用量 */
	private Double aGActualDosage6;
	/** 骨料 6 设计用量 */
	private Double aGDesignDosage6;
	/** AGHSL6 */
	private Double aGHSL6;
	/** 骨料 7 实际用量 */
	private Double aGActualDosage7;
	/** 骨料 7 设计用量 */
	private Double aGDesignDosage7;
	/** AGHSL7 */
	private Double aGHSL7;
	/** 骨料 8 设计用量 */
	private Double aGActualDosage8;
	/** 骨料 8 实际用量 */
	private Double aGDesignDosage8;
	/** AGHSL8 */
	private Double aGHSL8;
	/** 外加剂 1 实际用  */
	private Double additiveActualDosage1;
	/** 外加剂 1 设计用  */
	private Double additiveDesignDosage1;
	/** 外加剂 2 实际用  */
	private Double additiveActualDosage2;
	/** 外加剂 2 设计用  */
	private Double additiveDesignDosage2;
	/** 外加剂 3 实际用  */
	private Double additiveActualDosage3;
	/** 外加剂 3 设计用  */
	private Double additiveDesignDosage3;
	/** 外加剂 4 实际用  */
	private Double additiveActualDosage4;
	/** 外加剂 4 设计用  */
	private Double additiveDesignDosage4;
	/** 水 1 实际用量 */
	private Double waterActualDosage1;
	/** 水 1 设计用量 */
	private Double waterDesignDosage1;
	/** 水 2 实际用量 */
	private Double waterActualDosage2;
	/** 水 2 设计用量 */
	private Double waterDesignDosage2;
	/** 水 3 实际用量 */
	private Double waterActualDosage3;
	/** 水 3 设计用量 */
	private Double waterDesignDosage3;
	/**  */
	private Integer isExstore;

	public void setSID(Long sID) 
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setProductLine(Long productLine) 
	{
		this.productLine = productLine;
	}

	public Long getProductLine() 
	{
		return productLine;
	}
	public void setErpProductNumber(String erpProductNumber) 
	{
		this.erpProductNumber = erpProductNumber;
	}

	public String getErpProductNumber() 
	{
		return erpProductNumber;
	}
	public void setIpcProductNumber(String ipcProductNumber) 
	{
		this.ipcProductNumber = ipcProductNumber;
	}

	public String getIpcProductNumber() 
	{
		return ipcProductNumber;
	}
	public void setPieceNumber(String pieceNumber) 
	{
		this.pieceNumber = pieceNumber;
	}

	public String getPieceNumber() 
	{
		return pieceNumber;
	}
	public void setPieceOrder(Integer pieceOrder) 
	{
		this.pieceOrder = pieceOrder;
	}

	public Integer getPieceOrder() 
	{
		return pieceOrder;
	}
	public void setPieceAmount(Double pieceAmount) 
	{
		this.pieceAmount = pieceAmount;
	}

	public Double getPieceAmount() 
	{
		return pieceAmount;
	}
	public void setVehiAmount(Double vehiAmount) 
	{
		this.vehiAmount = vehiAmount;
	}

	public Double getVehiAmount() 
	{
		return vehiAmount;
	}
	public void setVehiNum(String vehiNum) 
	{
		this.vehiNum = vehiNum;
	}

	public String getVehiNum() 
	{
		return vehiNum;
	}
	public void setDriver(String driver) 
	{
		this.driver = driver;
	}

	public String getDriver() 
	{
		return driver;
	}
	public void setProductDateTime(Date productDateTime) 
	{
		this.productDateTime = productDateTime;
	}

	public Date getProductDateTime() 
	{
		return productDateTime;
	}
	public void setTaskNumber(String taskNumber) 
	{
		this.taskNumber = taskNumber;
	}

	public String getTaskNumber() 
	{
		return taskNumber;
	}
	public void setMixNumber(String mixNumber) 
	{
		this.mixNumber = mixNumber;
	}

	public String getMixNumber() 
	{
		return mixNumber;
	}
	public void setOperator(String operator) 
	{
		this.operator = operator;
	}

	public String getOperator() 
	{
		return operator;
	}
	public void setConcreteLabel(String concreteLabel) 
	{
		this.concreteLabel = concreteLabel;
	}

	public String getConcreteLabel() 
	{
		return concreteLabel;
	}
	public void setIsMortar(Integer isMortar) 
	{
		this.isMortar = isMortar;
	}

	public Integer getIsMortar() 
	{
		return isMortar;
	}
	public void setCementActualDosage1(Double cementActualDosage1) 
	{
		this.cementActualDosage1 = cementActualDosage1;
	}

	public Double getCementActualDosage1() 
	{
		return cementActualDosage1;
	}
	public void setCementDesignDosage1(Double cementDesignDosage1) 
	{
		this.cementDesignDosage1 = cementDesignDosage1;
	}

	public Double getCementDesignDosage1() 
	{
		return cementDesignDosage1;
	}
	public void setCementActualDosage2(Double cementActualDosage2) 
	{
		this.cementActualDosage2 = cementActualDosage2;
	}

	public Double getCementActualDosage2() 
	{
		return cementActualDosage2;
	}
	public void setCementDesignDosage2(Double cementDesignDosage2) 
	{
		this.cementDesignDosage2 = cementDesignDosage2;
	}

	public Double getCementDesignDosage2() 
	{
		return cementDesignDosage2;
	}
	public void setCementActualDosage3(Double cementActualDosage3) 
	{
		this.cementActualDosage3 = cementActualDosage3;
	}

	public Double getCementActualDosage3() 
	{
		return cementActualDosage3;
	}
	public void setCementDesignDosage3(Double cementDesignDosage3) 
	{
		this.cementDesignDosage3 = cementDesignDosage3;
	}

	public Double getCementDesignDosage3() 
	{
		return cementDesignDosage3;
	}
	public void setCementActualDosage4(Double cementActualDosage4) 
	{
		this.cementActualDosage4 = cementActualDosage4;
	}

	public Double getCementActualDosage4() 
	{
		return cementActualDosage4;
	}
	public void setCementDesignDosage4(Double cementDesignDosage4) 
	{
		this.cementDesignDosage4 = cementDesignDosage4;
	}

	public Double getCementDesignDosage4() 
	{
		return cementDesignDosage4;
	}
	public void setCementActualDosage5(Double cementActualDosage5) 
	{
		this.cementActualDosage5 = cementActualDosage5;
	}

	public Double getCementActualDosage5() 
	{
		return cementActualDosage5;
	}
	public void setCementDesignDosage5(Double cementDesignDosage5) 
	{
		this.cementDesignDosage5 = cementDesignDosage5;
	}

	public Double getCementDesignDosage5() 
	{
		return cementDesignDosage5;
	}
	public void setMixActualDosage1(Double mixActualDosage1) 
	{
		this.mixActualDosage1 = mixActualDosage1;
	}

	public Double getMixActualDosage1() 
	{
		return mixActualDosage1;
	}
	public void setMixDesignDosage1(Double mixDesignDosage1) 
	{
		this.mixDesignDosage1 = mixDesignDosage1;
	}

	public Double getMixDesignDosage1() 
	{
		return mixDesignDosage1;
	}
	public void setMixActualDosage2(Double mixActualDosage2) 
	{
		this.mixActualDosage2 = mixActualDosage2;
	}

	public Double getMixActualDosage2() 
	{
		return mixActualDosage2;
	}
	public void setMixDesignDosage2(Double mixDesignDosage2) 
	{
		this.mixDesignDosage2 = mixDesignDosage2;
	}

	public Double getMixDesignDosage2() 
	{
		return mixDesignDosage2;
	}
	public void setMixActualDosage3(Double mixActualDosage3) 
	{
		this.mixActualDosage3 = mixActualDosage3;
	}

	public Double getMixActualDosage3() 
	{
		return mixActualDosage3;
	}
	public void setMixDesignDosage3(Double mixDesignDosage3) 
	{
		this.mixDesignDosage3 = mixDesignDosage3;
	}

	public Double getMixDesignDosage3() 
	{
		return mixDesignDosage3;
	}
	public void setMixActualDosage4(Double mixActualDosage4) 
	{
		this.mixActualDosage4 = mixActualDosage4;
	}

	public Double getMixActualDosage4() 
	{
		return mixActualDosage4;
	}
	public void setMixDesignDosage4(Double mixDesignDosage4) 
	{
		this.mixDesignDosage4 = mixDesignDosage4;
	}

	public Double getMixDesignDosage4() 
	{
		return mixDesignDosage4;
	}
	public void setMixActualDosage5(Double mixActualDosage5) 
	{
		this.mixActualDosage5 = mixActualDosage5;
	}

	public Double getMixActualDosage5() 
	{
		return mixActualDosage5;
	}
	public void setMixDesignDosage5(Double mixDesignDosage5) 
	{
		this.mixDesignDosage5 = mixDesignDosage5;
	}

	public Double getMixDesignDosage5() 
	{
		return mixDesignDosage5;
	}
	public void setAGActualDosage1(Double aGActualDosage1) 
	{
		this.aGActualDosage1 = aGActualDosage1;
	}

	public Double getAGActualDosage1() 
	{
		return aGActualDosage1;
	}
	public void setAGDesignDosage1(Double aGDesignDosage1) 
	{
		this.aGDesignDosage1 = aGDesignDosage1;
	}

	public Double getAGDesignDosage1() 
	{
		return aGDesignDosage1;
	}
	public void setAGHSL1(Double aGHSL1) 
	{
		this.aGHSL1 = aGHSL1;
	}

	public Double getAGHSL1() 
	{
		return aGHSL1;
	}
	public void setAGActualDosage2(Double aGActualDosage2) 
	{
		this.aGActualDosage2 = aGActualDosage2;
	}

	public Double getAGActualDosage2() 
	{
		return aGActualDosage2;
	}
	public void setAGDesignDosage2(Double aGDesignDosage2) 
	{
		this.aGDesignDosage2 = aGDesignDosage2;
	}

	public Double getAGDesignDosage2() 
	{
		return aGDesignDosage2;
	}
	public void setAGHSL2(Double aGHSL2) 
	{
		this.aGHSL2 = aGHSL2;
	}

	public Double getAGHSL2() 
	{
		return aGHSL2;
	}
	public void setAGActualDosage3(Double aGActualDosage3) 
	{
		this.aGActualDosage3 = aGActualDosage3;
	}

	public Double getAGActualDosage3() 
	{
		return aGActualDosage3;
	}
	public void setAGDesignDosage3(Double aGDesignDosage3) 
	{
		this.aGDesignDosage3 = aGDesignDosage3;
	}

	public Double getAGDesignDosage3() 
	{
		return aGDesignDosage3;
	}
	public void setAGHSL3(Double aGHSL3) 
	{
		this.aGHSL3 = aGHSL3;
	}

	public Double getAGHSL3() 
	{
		return aGHSL3;
	}
	public void setAGActualDosage4(Double aGActualDosage4) 
	{
		this.aGActualDosage4 = aGActualDosage4;
	}

	public Double getAGActualDosage4() 
	{
		return aGActualDosage4;
	}
	public void setAGDesignDosage4(Double aGDesignDosage4) 
	{
		this.aGDesignDosage4 = aGDesignDosage4;
	}

	public Double getAGDesignDosage4() 
	{
		return aGDesignDosage4;
	}
	public void setAGHSL4(Double aGHSL4) 
	{
		this.aGHSL4 = aGHSL4;
	}

	public Double getAGHSL4() 
	{
		return aGHSL4;
	}
	public void setAGActualDosage5(Double aGActualDosage5) 
	{
		this.aGActualDosage5 = aGActualDosage5;
	}

	public Double getAGActualDosage5() 
	{
		return aGActualDosage5;
	}
	public void setAGDesignDosage5(Double aGDesignDosage5) 
	{
		this.aGDesignDosage5 = aGDesignDosage5;
	}

	public Double getAGDesignDosage5() 
	{
		return aGDesignDosage5;
	}
	public void setAGHSL5(Double aGHSL5) 
	{
		this.aGHSL5 = aGHSL5;
	}

	public Double getAGHSL5() 
	{
		return aGHSL5;
	}
	public void setAGActualDosage6(Double aGActualDosage6) 
	{
		this.aGActualDosage6 = aGActualDosage6;
	}

	public Double getAGActualDosage6() 
	{
		return aGActualDosage6;
	}
	public void setAGDesignDosage6(Double aGDesignDosage6) 
	{
		this.aGDesignDosage6 = aGDesignDosage6;
	}

	public Double getAGDesignDosage6() 
	{
		return aGDesignDosage6;
	}
	public void setAGHSL6(Double aGHSL6) 
	{
		this.aGHSL6 = aGHSL6;
	}

	public Double getAGHSL6() 
	{
		return aGHSL6;
	}
	public void setAGActualDosage7(Double aGActualDosage7) 
	{
		this.aGActualDosage7 = aGActualDosage7;
	}

	public Double getAGActualDosage7() 
	{
		return aGActualDosage7;
	}
	public void setAGDesignDosage7(Double aGDesignDosage7) 
	{
		this.aGDesignDosage7 = aGDesignDosage7;
	}

	public Double getAGDesignDosage7() 
	{
		return aGDesignDosage7;
	}
	public void setAGHSL7(Double aGHSL7) 
	{
		this.aGHSL7 = aGHSL7;
	}

	public Double getAGHSL7() 
	{
		return aGHSL7;
	}
	public void setAGActualDosage8(Double aGActualDosage8) 
	{
		this.aGActualDosage8 = aGActualDosage8;
	}

	public Double getAGActualDosage8() 
	{
		return aGActualDosage8;
	}
	public void setAGDesignDosage8(Double aGDesignDosage8) 
	{
		this.aGDesignDosage8 = aGDesignDosage8;
	}

	public Double getAGDesignDosage8() 
	{
		return aGDesignDosage8;
	}
	public void setAGHSL8(Double aGHSL8) 
	{
		this.aGHSL8 = aGHSL8;
	}

	public Double getAGHSL8() 
	{
		return aGHSL8;
	}
	public void setAdditiveActualDosage1(Double additiveActualDosage1) 
	{
		this.additiveActualDosage1 = additiveActualDosage1;
	}

	public Double getAdditiveActualDosage1() 
	{
		return additiveActualDosage1;
	}
	public void setAdditiveDesignDosage1(Double additiveDesignDosage1) 
	{
		this.additiveDesignDosage1 = additiveDesignDosage1;
	}

	public Double getAdditiveDesignDosage1() 
	{
		return additiveDesignDosage1;
	}
	public void setAdditiveActualDosage2(Double additiveActualDosage2) 
	{
		this.additiveActualDosage2 = additiveActualDosage2;
	}

	public Double getAdditiveActualDosage2() 
	{
		return additiveActualDosage2;
	}
	public void setAdditiveDesignDosage2(Double additiveDesignDosage2) 
	{
		this.additiveDesignDosage2 = additiveDesignDosage2;
	}

	public Double getAdditiveDesignDosage2() 
	{
		return additiveDesignDosage2;
	}
	public void setAdditiveActualDosage3(Double additiveActualDosage3) 
	{
		this.additiveActualDosage3 = additiveActualDosage3;
	}

	public Double getAdditiveActualDosage3() 
	{
		return additiveActualDosage3;
	}
	public void setAdditiveDesignDosage3(Double additiveDesignDosage3) 
	{
		this.additiveDesignDosage3 = additiveDesignDosage3;
	}

	public Double getAdditiveDesignDosage3() 
	{
		return additiveDesignDosage3;
	}
	public void setAdditiveActualDosage4(Double additiveActualDosage4) 
	{
		this.additiveActualDosage4 = additiveActualDosage4;
	}

	public Double getAdditiveActualDosage4() 
	{
		return additiveActualDosage4;
	}
	public void setAdditiveDesignDosage4(Double additiveDesignDosage4) 
	{
		this.additiveDesignDosage4 = additiveDesignDosage4;
	}

	public Double getAdditiveDesignDosage4() 
	{
		return additiveDesignDosage4;
	}
	public void setWaterActualDosage1(Double waterActualDosage1) 
	{
		this.waterActualDosage1 = waterActualDosage1;
	}

	public Double getWaterActualDosage1() 
	{
		return waterActualDosage1;
	}
	public void setWaterDesignDosage1(Double waterDesignDosage1) 
	{
		this.waterDesignDosage1 = waterDesignDosage1;
	}

	public Double getWaterDesignDosage1() 
	{
		return waterDesignDosage1;
	}
	public void setWaterActualDosage2(Double waterActualDosage2) 
	{
		this.waterActualDosage2 = waterActualDosage2;
	}

	public Double getWaterActualDosage2() 
	{
		return waterActualDosage2;
	}
	public void setWaterDesignDosage2(Double waterDesignDosage2) 
	{
		this.waterDesignDosage2 = waterDesignDosage2;
	}

	public Double getWaterDesignDosage2() 
	{
		return waterDesignDosage2;
	}
	public void setWaterActualDosage3(Double waterActualDosage3) 
	{
		this.waterActualDosage3 = waterActualDosage3;
	}

	public Double getWaterActualDosage3() 
	{
		return waterActualDosage3;
	}
	public void setWaterDesignDosage3(Double waterDesignDosage3) 
	{
		this.waterDesignDosage3 = waterDesignDosage3;
	}

	public Double getWaterDesignDosage3() 
	{
		return waterDesignDosage3;
	}
	public void setIsExstore(Integer isExstore) 
	{
		this.isExstore = isExstore;
	}

	public Integer getIsExstore() 
	{
		return isExstore;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sID", getSID())
            .append("productLine", getProductLine())
            .append("erpProductNumber", getErpProductNumber())
            .append("ipcProductNumber", getIpcProductNumber())
            .append("pieceNumber", getPieceNumber())
            .append("pieceOrder", getPieceOrder())
            .append("pieceAmount", getPieceAmount())
            .append("vehiAmount", getVehiAmount())
            .append("vehiNum", getVehiNum())
            .append("driver", getDriver())
            .append("productDateTime", getProductDateTime())
            .append("taskNumber", getTaskNumber())
            .append("mixNumber", getMixNumber())
            .append("operator", getOperator())
            .append("concreteLabel", getConcreteLabel())
            .append("isMortar", getIsMortar())
            .append("cementActualDosage1", getCementActualDosage1())
            .append("cementDesignDosage1", getCementDesignDosage1())
            .append("cementActualDosage2", getCementActualDosage2())
            .append("cementDesignDosage2", getCementDesignDosage2())
            .append("cementActualDosage3", getCementActualDosage3())
            .append("cementDesignDosage3", getCementDesignDosage3())
            .append("cementActualDosage4", getCementActualDosage4())
            .append("cementDesignDosage4", getCementDesignDosage4())
            .append("cementActualDosage5", getCementActualDosage5())
            .append("cementDesignDosage5", getCementDesignDosage5())
            .append("mixActualDosage1", getMixActualDosage1())
            .append("mixDesignDosage1", getMixDesignDosage1())
            .append("mixActualDosage2", getMixActualDosage2())
            .append("mixDesignDosage2", getMixDesignDosage2())
            .append("mixActualDosage3", getMixActualDosage3())
            .append("mixDesignDosage3", getMixDesignDosage3())
            .append("mixActualDosage4", getMixActualDosage4())
            .append("mixDesignDosage4", getMixDesignDosage4())
            .append("mixActualDosage5", getMixActualDosage5())
            .append("mixDesignDosage5", getMixDesignDosage5())
            .append("aGActualDosage1", getAGActualDosage1())
            .append("aGDesignDosage1", getAGDesignDosage1())
            .append("aGHSL1", getAGHSL1())
            .append("aGActualDosage2", getAGActualDosage2())
            .append("aGDesignDosage2", getAGDesignDosage2())
            .append("aGHSL2", getAGHSL2())
            .append("aGActualDosage3", getAGActualDosage3())
            .append("aGDesignDosage3", getAGDesignDosage3())
            .append("aGHSL3", getAGHSL3())
            .append("aGActualDosage4", getAGActualDosage4())
            .append("aGDesignDosage4", getAGDesignDosage4())
            .append("aGHSL4", getAGHSL4())
            .append("aGActualDosage5", getAGActualDosage5())
            .append("aGDesignDosage5", getAGDesignDosage5())
            .append("aGHSL5", getAGHSL5())
            .append("aGActualDosage6", getAGActualDosage6())
            .append("aGDesignDosage6", getAGDesignDosage6())
            .append("aGHSL6", getAGHSL6())
            .append("aGActualDosage7", getAGActualDosage7())
            .append("aGDesignDosage7", getAGDesignDosage7())
            .append("aGHSL7", getAGHSL7())
            .append("aGActualDosage8", getAGActualDosage8())
            .append("aGDesignDosage8", getAGDesignDosage8())
            .append("aGHSL8", getAGHSL8())
            .append("additiveActualDosage1", getAdditiveActualDosage1())
            .append("additiveDesignDosage1", getAdditiveDesignDosage1())
            .append("additiveActualDosage2", getAdditiveActualDosage2())
            .append("additiveDesignDosage2", getAdditiveDesignDosage2())
            .append("additiveActualDosage3", getAdditiveActualDosage3())
            .append("additiveDesignDosage3", getAdditiveDesignDosage3())
            .append("additiveActualDosage4", getAdditiveActualDosage4())
            .append("additiveDesignDosage4", getAdditiveDesignDosage4())
            .append("waterActualDosage1", getWaterActualDosage1())
            .append("waterDesignDosage1", getWaterDesignDosage1())
            .append("waterActualDosage2", getWaterActualDosage2())
            .append("waterDesignDosage2", getWaterDesignDosage2())
            .append("waterActualDosage3", getWaterActualDosage3())
            .append("waterDesignDosage3", getWaterDesignDosage3())
            .append("isExstore", getIsExstore())
            .toString();
    }
}
