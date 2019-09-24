package com.ruoyi.project.cars.appVersion.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.appVersion.mapper.AppVersionMapper;
import com.ruoyi.project.cars.appVersion.domain.AppVersion;
import com.ruoyi.project.cars.appVersion.service.IAppVersionService;
import com.ruoyi.common.utils.text.Convert;

/**
 * app版本 服务层实现
 * 
 * @author admin
 * @date 2019-06-17
 */
@Service
public class AppVersionServiceImpl implements IAppVersionService 
{
	@Autowired
	private AppVersionMapper appVersionMapper;

	/**
     * 查询app版本信息
     * 
     * @param id app版本ID
     * @return app版本信息
     */
    @Override
	public AppVersion selectAppVersionById(Long id)
	{
	    return appVersionMapper.selectAppVersionById(id);
	}
	
	/**
     * 查询app版本列表
     * 
     * @param appVersion app版本信息
     * @return app版本集合
     */
	@Override
	public List<AppVersion> selectAppVersionList(AppVersion appVersion)
	{
	    return appVersionMapper.selectAppVersionList(appVersion);
	}
	
    /**
     * 新增app版本
     * 
     * @param appVersion app版本信息
     * @return 结果
     */
	@Override
	public int insertAppVersion(AppVersion appVersion)
	{
	    return appVersionMapper.insertAppVersion(appVersion);
	}
	
	/**
     * 修改app版本
     * 
     * @param appVersion app版本信息
     * @return 结果
     */
	@Override
	public int updateAppVersion(AppVersion appVersion)
	{
	    return appVersionMapper.updateAppVersion(appVersion);
	}

	/**
     * 删除app版本对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteAppVersionByIds(String ids)
	{
		return appVersionMapper.deleteAppVersionByIds(Convert.toStrArray(ids));
	}
	
}
