package com.ruoyi.project.cars.appVersion.mapper;

import com.ruoyi.project.cars.appVersion.domain.AppVersion;
import java.util.List;	

/**
 * app版本 数据层
 * 
 * @author admin
 * @date 2019-06-17
 */
public interface AppVersionMapper 
{
	/**
     * 查询app版本信息
     * 
     * @param id app版本ID
     * @return app版本信息
     */
	public AppVersion selectAppVersionById(Long id);
	
	/**
     * 查询app版本列表
     * 
     * @param appVersion app版本信息
     * @return app版本集合
     */
	public List<AppVersion> selectAppVersionList(AppVersion appVersion);
	
	/**
     * 新增app版本
     * 
     * @param appVersion app版本信息
     * @return 结果
     */
	public int insertAppVersion(AppVersion appVersion);
	
	/**
     * 修改app版本
     * 
     * @param appVersion app版本信息
     * @return 结果
     */
	public int updateAppVersion(AppVersion appVersion);
	
	/**
     * 删除app版本
     * 
     * @param id app版本ID
     * @return 结果
     */
	public int deleteAppVersionById(Long id);
	
	/**
     * 批量删除app版本
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteAppVersionByIds(String[] ids);
	
}