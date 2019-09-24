package com.ruoyi.project.cars.driverCar.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 司机-车辆关系表 tb_driver_car
 * 
 * @author admin
 * @date 2019-04-25
 */
public class DriverCar extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	private Long id;
	/** 司机id */
	private Long driverId;
	private Long carId;
	/** 司机手机号 */
	private String driverMobile;
	/** 车号 */
	private Integer carNo;
	/** 车牌号 */
	private String carBrand;
	/** 客户端通知标识alias */
	private String notifyId;
	/**优先权次数*/
	private Integer privilege;

	private String sqlWhere;

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getPrivilege() {
		return privilege;
	}

	public void setPrivilege(Integer privilege) {
		this.privilege = privilege;
	}

	public Long getCarId() {
		return carId;
	}

	public void setCarId(Long carId) {
		this.carId = carId;
	}

	public void setDriverId(Long driverId)
	{
		this.driverId = driverId;
	}

	public Long getDriverId() 
	{
		return driverId;
	}
	public void setDriverMobile(String driverMobile) 
	{
		this.driverMobile = driverMobile;
	}

	public String getDriverMobile() 
	{
		return driverMobile;
	}
	public void setCarNo(Integer carNo) 
	{
		this.carNo = carNo;
	}

	public Integer getCarNo() 
	{
		return carNo;
	}
	public void setCarBrand(String carBrand) 
	{
		this.carBrand = carBrand;
	}

	public String getCarBrand() 
	{
		return carBrand;
	}
	public void setNotifyId(String notifyId) 
	{
		this.notifyId = notifyId;
	}

	public String getNotifyId() 
	{
		return notifyId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("driverId", getDriverId())
            .append("driverMobile", getDriverMobile())
            .append("carNo", getCarNo())
            .append("carBrand", getCarBrand())
            .append("notifyId", getNotifyId())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
