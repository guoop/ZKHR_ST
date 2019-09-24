package com.ruoyi.project.duties.tasksCarsLogs.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;
import java.util.Date;

/**
 * 车辆行驶记录表 tb_tasks_cars_logs
 * 
 * @author admin
 * @date 2019-05-28
 */
public class TasksCarsLogs extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private Long taskCarId;
	/**  */
	private Long taskId;
	/**  */
	private Long carId;
	/**  */
	private String carNo;
	/** 车次 */
	private Integer carCnt;
	/**  */
	private String carBrand;
	/** 司机电话 */
	private String mobile;
	/** 停留开始时间 */
	private Date stopStartTime;
	/** 停留时间 */
	private Integer times;
	/** 经度 */
	private Double lng;
	/** 维度 */
	private Double lat;

	public void setTaskCarId(Long taskCarId) 
	{
		this.taskCarId = taskCarId;
	}

	public Long getTaskCarId() 
	{
		return taskCarId;
	}
	public void setTaskId(Long taskId) 
	{
		this.taskId = taskId;
	}

	public Long getTaskId() 
	{
		return taskId;
	}
	public void setCarId(Long carId) 
	{
		this.carId = carId;
	}

	public Long getCarId() 
	{
		return carId;
	}
	public void setCarNo(String carNo) 
	{
		this.carNo = carNo;
	}

	public String getCarNo() 
	{
		return carNo;
	}
	public void setCarCnt(Integer carCnt) 
	{
		this.carCnt = carCnt;
	}

	public Integer getCarCnt() 
	{
		return carCnt;
	}
	public void setCarBrand(String carBrand) 
	{
		this.carBrand = carBrand;
	}

	public String getCarBrand() 
	{
		return carBrand;
	}
	public void setMobile(String mobile) 
	{
		this.mobile = mobile;
	}

	public String getMobile() 
	{
		return mobile;
	}
	public void setStopStartTime(Date stopStartTime) 
	{
		this.stopStartTime = stopStartTime;
	}

	public Date getStopStartTime() 
	{
		return stopStartTime;
	}
	public void setTimes(Integer times) 
	{
		this.times = times;
	}

	public Integer getTimes() 
	{
		return times;
	}
	public void setLng(Double lng) 
	{
		this.lng = lng;
	}

	public Double getLng() 
	{
		return lng;
	}
	public void setLat(Double lat) 
	{
		this.lat = lat;
	}

	public Double getLat() 
	{
		return lat;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("taskCarId", getTaskCarId())
            .append("taskId", getTaskId())
            .append("carId", getCarId())
            .append("carNo", getCarNo())
            .append("carCnt", getCarCnt())
            .append("carBrand", getCarBrand())
            .append("mobile", getMobile())
            .append("stopStartTime", getStopStartTime())
            .append("times", getTimes())
            .append("lng", getLng())
            .append("lat", getLat())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
