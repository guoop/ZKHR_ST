package com.ruoyi.project.cemslink.instore.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 库存表 instore
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Instore extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** ERP运输单号 */
	private String instoreNum;
	/**  */
	private Date instoreTime;
	/** ERP运输单号 */
	private String suppID;
	/** ERP运输单号 */
	private String materialID;
	/** ERP运输单号 */
	private String materialNum;
	/** ERP运输单号 */
	private String materialName;
	/** ERP运输单号 */
	private String spec;
	/** ERP运输单号 */
	private Double gross;
	/** ERP运输单号 */
	private Double tare;
	/** ERP运输单号 */
	private Double trans;
	/** ERP运输单号 */
	private Double net;
	/** ERP运输单号 */
	private String sPOS;
	/** ERP运输单号 */
	private String vehiNum;
	/** ERP运输单号 */
	private String instoreMan;
	/** ERP运输单号 */
	private Integer lineID;
	/**  */
	private Integer syncStatus;
	/**  */
	private Integer syncStatus2;

	public void setSID(Long sID) 
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setInstoreNum(String instoreNum) 
	{
		this.instoreNum = instoreNum;
	}

	public String getInstoreNum() 
	{
		return instoreNum;
	}
	public void setInstoreTime(Date instoreTime) 
	{
		this.instoreTime = instoreTime;
	}

	public Date getInstoreTime() 
	{
		return instoreTime;
	}
	public void setSuppID(String suppID) 
	{
		this.suppID = suppID;
	}

	public String getSuppID() 
	{
		return suppID;
	}
	public void setMaterialID(String materialID) 
	{
		this.materialID = materialID;
	}

	public String getMaterialID() 
	{
		return materialID;
	}
	public void setMaterialNum(String materialNum) 
	{
		this.materialNum = materialNum;
	}

	public String getMaterialNum() 
	{
		return materialNum;
	}
	public void setMaterialName(String materialName) 
	{
		this.materialName = materialName;
	}

	public String getMaterialName() 
	{
		return materialName;
	}
	public void setSpec(String spec) 
	{
		this.spec = spec;
	}

	public String getSpec() 
	{
		return spec;
	}
	public void setGross(Double gross) 
	{
		this.gross = gross;
	}

	public Double getGross() 
	{
		return gross;
	}
	public void setTare(Double tare) 
	{
		this.tare = tare;
	}

	public Double getTare() 
	{
		return tare;
	}
	public void setTrans(Double trans) 
	{
		this.trans = trans;
	}

	public Double getTrans() 
	{
		return trans;
	}
	public void setNet(Double net) 
	{
		this.net = net;
	}

	public Double getNet() 
	{
		return net;
	}
	public void setSPOS(String sPOS) 
	{
		this.sPOS = sPOS;
	}

	public String getSPOS() 
	{
		return sPOS;
	}
	public void setVehiNum(String vehiNum) 
	{
		this.vehiNum = vehiNum;
	}

	public String getVehiNum() 
	{
		return vehiNum;
	}
	public void setInstoreMan(String instoreMan) 
	{
		this.instoreMan = instoreMan;
	}

	public String getInstoreMan() 
	{
		return instoreMan;
	}
	public void setLineID(Integer lineID) 
	{
		this.lineID = lineID;
	}

	public Integer getLineID() 
	{
		return lineID;
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
            .append("instoreNum", getInstoreNum())
            .append("instoreTime", getInstoreTime())
            .append("suppID", getSuppID())
            .append("materialID", getMaterialID())
            .append("materialNum", getMaterialNum())
            .append("materialName", getMaterialName())
            .append("spec", getSpec())
            .append("gross", getGross())
            .append("tare", getTare())
            .append("trans", getTrans())
            .append("net", getNet())
            .append("sPOS", getSPOS())
            .append("vehiNum", getVehiNum())
            .append("instoreMan", getInstoreMan())
            .append("remark", getRemark())
            .append("lineID", getLineID())
            .append("syncStatus", getSyncStatus())
            .append("syncStatus2", getSyncStatus2())
            .toString();
    }
}
