package com.ruoyi.project.cars.driver.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * 司机表 tb_driver
 * 
 * @author admin
 * @date 2019-04-25
 */
public class Driver extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 司机姓名 */
	private String dName;
	/** 手机号 */
	private String dMobile;
	private Integer status;
	/** 用户是否存在此岗位标识 默认不存在 */
	private boolean flag = false;
	private Integer privilegeCnt;

	public Integer getPrivilegeCnt() {
		return privilegeCnt;
	}

	public void setPrivilegeCnt(Integer privilegeCnt) {
		this.privilegeCnt = privilegeCnt;
	}

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public void setId(Long id)
	{
		this.id = id;
	}

	public Long getId() 
	{
		return id;
	}
	public void setDName(String dName) 
	{
		this.dName = dName;
	}

	public String getDName() 
	{
		return dName;
	}
	public void setDMobile(String dMobile) 
	{
		this.dMobile = dMobile;
	}

	public String getDMobile() 
	{
		return dMobile;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("dName", getDName())
            .append("dMobile", getDMobile())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
