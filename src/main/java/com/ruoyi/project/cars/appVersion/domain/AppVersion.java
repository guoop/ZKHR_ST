package com.ruoyi.project.cars.appVersion.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.framework.web.domain.BaseEntity;

/**
 * app版本表 tb_app_version
 * 
 * @author admin
 * @date 2019-06-17
 */
public class AppVersion extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 序号 */
	private Long id;
	/** 版本号 */
	private String version;
	/** 下载地址 */
	private String url;
	private String target_size;
	private String update;
	private String constraint;
	private String new_version;
	private String apk_file_url;
	private String update_log;
	private String new_md5;
	private String sqlWhere;

	public String getTarget_size() {
		return target_size;
	}

	public void setTarget_size(String target_size) {
		this.target_size = target_size;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}

	public String getConstraint() {
		return constraint;
	}

	public void setConstraint(String constraint) {
		this.constraint = constraint;
	}

	public String getNew_version() {
		return new_version;
	}

	public void setNew_version(String new_version) {
		this.new_version = new_version;
	}

	public String getApk_file_url() {
		return apk_file_url;
	}

	public void setApk_file_url(String apk_file_url) {
		this.apk_file_url = apk_file_url;
	}

	public String getUpdate_log() {
		return update_log;
	}

	public void setUpdate_log(String update_log) {
		this.update_log = update_log;
	}

	public String getNew_md5() {
		return new_md5;
	}

	public void setNew_md5(String new_md5) {
		this.new_md5 = new_md5;
	}

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
	public void setVersion(String version) 
	{
		this.version = version;
	}

	public String getVersion() 
	{
		return version;
	}
	public void setUrl(String url) 
	{
		this.url = url;
	}

	public String getUrl() 
	{
		return url;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("version", getVersion())
            .append("createTime", getCreateTime())
            .append("url", getUrl())
            .toString();
    }
}
