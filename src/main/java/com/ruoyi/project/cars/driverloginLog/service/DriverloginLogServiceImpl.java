package com.ruoyi.project.cars.driverloginLog.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.driverloginLog.mapper.DriverloginLogMapper;
import com.ruoyi.project.cars.driverloginLog.domain.DriverloginLog;
import com.ruoyi.project.cars.driverloginLog.service.IDriverloginLogService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 登陆记录 服务层实现
 * 
 * @author admin
 * @date 2019-06-15
 */
@Service
public class DriverloginLogServiceImpl implements IDriverloginLogService 
{
	@Autowired
	private DriverloginLogMapper driverloginLogMapper;

	/**
     * 查询登陆记录信息
     * 
     * @param id 登陆记录ID
     * @return 登陆记录信息
     */
    @Override
	public DriverloginLog selectDriverloginLogById(Long id)
	{
	    return driverloginLogMapper.selectDriverloginLogById(id);
	}
	
	/**
     * 查询登陆记录列表
     * 
     * @param driverloginLog 登陆记录信息
     * @return 登陆记录集合
     */
	@Override
	public List<DriverloginLog> selectDriverloginLogList(DriverloginLog driverloginLog)
	{
	    return driverloginLogMapper.selectDriverloginLogList(driverloginLog);
	}
	
    /**
     * 新增登陆记录
     * 
     * @param driverloginLog 登陆记录信息
     * @return 结果
     */
	@Override
	public int insertDriverloginLog(DriverloginLog driverloginLog)
	{
	    return driverloginLogMapper.insertDriverloginLog(driverloginLog);
	}
	
	/**
     * 修改登陆记录
     * 
     * @param driverloginLog 登陆记录信息
     * @return 结果
     */
	@Override
	public int updateDriverloginLog(DriverloginLog driverloginLog)
	{
	    return driverloginLogMapper.updateDriverloginLog(driverloginLog);
	}

	/**
     * 删除登陆记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDriverloginLogByIds(String ids)
	{
		return driverloginLogMapper.deleteDriverloginLogByIds(Convert.toStrArray(ids));
	}
	
}
