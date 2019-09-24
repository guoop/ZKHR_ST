package com.ruoyi.project.cars.carSignlog.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

import java.text.SimpleDateFormat;

/**
 * 上班车辆表 tb_car_signlog
 * 
 * @author admin
 * @date 2019-07-15
 */
public class CarSignlog extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/**  */
	private String carNos;

	private String suffixTime;

	private String sqlWhere;

	public String getSqlWhere() {
		return sqlWhere;
	}

	public void setSqlWhere(String sqlWhere) {
		this.sqlWhere = sqlWhere;
	}

	public String getSuffixTime() {
		return suffixTime;
	}

	public void setSuffixTime(String suffixTime) {
		this.suffixTime = suffixTime;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setCarNos(String carNos) 
	{
		this.carNos = carNos;
	}

	public String getCarNos() 
	{
		return carNos;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("carNos", getCarNos())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
