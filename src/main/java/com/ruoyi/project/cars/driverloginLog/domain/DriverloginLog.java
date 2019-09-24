package com.ruoyi.project.cars.driverloginLog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 登陆记录表 tb_driverlogin_log
 * 
 * @author admin
 * @date 2019-06-15
 */
public class DriverloginLog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 司机姓名 */
	private String driverName;
	/** 手机号 */
	private String driverMobile;
	/** 经度 */
	private String lat;
	/** 维度 */
	private String lng;

	public void setId(Long id) 
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDriverName(String driverName) 
	{
		this.driverName = driverName;
	}

	public String getDriverName() 
	{
		return driverName;
	}
	public void setDriverMobile(String driverMobile) 
	{
		this.driverMobile = driverMobile;
	}

	public String getDriverMobile() 
	{
		return driverMobile;
	}
	public void setLat(String lat) 
	{
		this.lat = lat;
	}

	public String getLat() 
	{
		return lat;
	}
	public void setLng(String lng) 
	{
		this.lng = lng;
	}

	public String getLng() 
	{
		return lng;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("driverName", getDriverName())
            .append("driverMobile", getDriverMobile())
            .append("remark", getRemark())
            .append("lat", getLat())
            .append("lng", getLng())
            .append("createTime", getCreateTime())
            .toString();
    }
}
