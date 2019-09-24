package com.ruoyi.project.cemslink.stock.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 库存数据表 stock
 * 
 * @author admin
 * @date 2019-05-31
 */
public class Stock extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序列号 */
	private Long sID;
	/** ERP运输单号 */
	private Integer lineID;
	/**  */
	private Date stockTime;
	/** KuChun1 */
	private Double kuChun1;
	/** KuChun2 */
	private Double kuChun2;
	/** KuChun3 */
	private Double kuChun3;
	/** KuChun4 */
	private Double kuChun4;
	/** KuChun5 */
	private Double kuChun5;
	/** KuChun6 */
	private Double kuChun6;
	/** KuChun7 */
	private Double kuChun7;
	/** KuChun8 */
	private Double kuChun8;
	/** KuChun9 */
	private Double kuChun9;
	/** KuChun10 */
	private Double kuChun10;
	/** KuChun11 */
	private Double kuChun11;
	/** KuChun12 */
	private Double kuChun12;
	/** KuChun13 */
	private Double kuChun13;
	/** KuChun14 */
	private Double kuChun14;
	/** KuChun15 */
	private Double kuChun15;
	/** KuChun16 */
	private Double kuChun16;

	public void setSID(Long sID) 
	{
		this.sID = sID;
	}

	public Long getSID() 
	{
		return sID;
	}
	public void setLineID(Integer lineID) 
	{
		this.lineID = lineID;
	}

	public Integer getLineID() 
	{
		return lineID;
	}
	public void setStockTime(Date stockTime) 
	{
		this.stockTime = stockTime;
	}

	public Date getStockTime() 
	{
		return stockTime;
	}
	public void setKuChun1(Double kuChun1) 
	{
		this.kuChun1 = kuChun1;
	}

	public Double getKuChun1() 
	{
		return kuChun1;
	}
	public void setKuChun2(Double kuChun2) 
	{
		this.kuChun2 = kuChun2;
	}

	public Double getKuChun2() 
	{
		return kuChun2;
	}
	public void setKuChun3(Double kuChun3) 
	{
		this.kuChun3 = kuChun3;
	}

	public Double getKuChun3() 
	{
		return kuChun3;
	}
	public void setKuChun4(Double kuChun4) 
	{
		this.kuChun4 = kuChun4;
	}

	public Double getKuChun4() 
	{
		return kuChun4;
	}
	public void setKuChun5(Double kuChun5) 
	{
		this.kuChun5 = kuChun5;
	}

	public Double getKuChun5() 
	{
		return kuChun5;
	}
	public void setKuChun6(Double kuChun6) 
	{
		this.kuChun6 = kuChun6;
	}

	public Double getKuChun6() 
	{
		return kuChun6;
	}
	public void setKuChun7(Double kuChun7) 
	{
		this.kuChun7 = kuChun7;
	}

	public Double getKuChun7() 
	{
		return kuChun7;
	}
	public void setKuChun8(Double kuChun8) 
	{
		this.kuChun8 = kuChun8;
	}

	public Double getKuChun8() 
	{
		return kuChun8;
	}
	public void setKuChun9(Double kuChun9) 
	{
		this.kuChun9 = kuChun9;
	}

	public Double getKuChun9() 
	{
		return kuChun9;
	}
	public void setKuChun10(Double kuChun10) 
	{
		this.kuChun10 = kuChun10;
	}

	public Double getKuChun10() 
	{
		return kuChun10;
	}
	public void setKuChun11(Double kuChun11) 
	{
		this.kuChun11 = kuChun11;
	}

	public Double getKuChun11() 
	{
		return kuChun11;
	}
	public void setKuChun12(Double kuChun12) 
	{
		this.kuChun12 = kuChun12;
	}

	public Double getKuChun12() 
	{
		return kuChun12;
	}
	public void setKuChun13(Double kuChun13) 
	{
		this.kuChun13 = kuChun13;
	}

	public Double getKuChun13() 
	{
		return kuChun13;
	}
	public void setKuChun14(Double kuChun14) 
	{
		this.kuChun14 = kuChun14;
	}

	public Double getKuChun14() 
	{
		return kuChun14;
	}
	public void setKuChun15(Double kuChun15) 
	{
		this.kuChun15 = kuChun15;
	}

	public Double getKuChun15() 
	{
		return kuChun15;
	}
	public void setKuChun16(Double kuChun16) 
	{
		this.kuChun16 = kuChun16;
	}

	public Double getKuChun16() 
	{
		return kuChun16;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("sID", getSID())
            .append("lineID", getLineID())
            .append("stockTime", getStockTime())
            .append("kuChun1", getKuChun1())
            .append("kuChun2", getKuChun2())
            .append("kuChun3", getKuChun3())
            .append("kuChun4", getKuChun4())
            .append("kuChun5", getKuChun5())
            .append("kuChun6", getKuChun6())
            .append("kuChun7", getKuChun7())
            .append("kuChun8", getKuChun8())
            .append("kuChun9", getKuChun9())
            .append("kuChun10", getKuChun10())
            .append("kuChun11", getKuChun11())
            .append("kuChun12", getKuChun12())
            .append("kuChun13", getKuChun13())
            .append("kuChun14", getKuChun14())
            .append("kuChun15", getKuChun15())
            .append("kuChun16", getKuChun16())
            .toString();
    }
}
