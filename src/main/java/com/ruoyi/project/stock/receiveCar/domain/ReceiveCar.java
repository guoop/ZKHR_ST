package com.ruoyi.project.stock.receiveCar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 送货车号表 tb_receive_car
 * 
 * @author admin
 * @date 2019-08-09
 */
public class ReceiveCar extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 车牌号 */
	private String carBrand;
	/** 送货人名称 */
	private String driverName;
	/** 联系方式 */
	private String mobile;

	private String sqlWhere;

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
	public void setCarBrand(String carBrand) 
	{
		this.carBrand = carBrand;
	}

	public String getCarBrand() 
	{
		return carBrand;
	}
	public void setDriverName(String driverName) 
	{
		this.driverName = driverName;
	}

	public String getDriverName() 
	{
		return driverName;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carBrand", getCarBrand())
            .append("driverName", getDriverName())
            .append("mobile", getMobile())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
