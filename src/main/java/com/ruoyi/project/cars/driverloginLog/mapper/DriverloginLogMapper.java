package com.ruoyi.project.cars.driverloginLog.mapper;

import com.ruoyi.project.cars.driverloginLog.domain.DriverloginLog;
import java.util.List;	

/**
 * 登陆记录 数据层
 * 
 * @author admin
 * @date 2019-06-15
 */
public interface DriverloginLogMapper 
{
	/**
     * 查询登陆记录信息
     * 
     * @param id 登陆记录ID
     * @return 登陆记录信息
     */
	public DriverloginLog selectDriverloginLogById(Long id);
	
	/**
     * 查询登陆记录列表
     * 
     * @param driverloginLog 登陆记录信息
     * @return 登陆记录集合
     */
	public List<DriverloginLog> selectDriverloginLogList(DriverloginLog driverloginLog);
	
	/**
     * 新增登陆记录
     * 
     * @param driverloginLog 登陆记录信息
     * @return 结果
     */
	public int insertDriverloginLog(DriverloginLog driverloginLog);
	
	/**
     * 修改登陆记录
     * 
     * @param driverloginLog 登陆记录信息
     * @return 结果
     */
	public int updateDriverloginLog(DriverloginLog driverloginLog);
	
	/**
     * 删除登陆记录
     * 
     * @param id 登陆记录ID
     * @return 结果
     */
	public int deleteDriverloginLogById(Long id);
	
	/**
     * 批量删除登陆记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDriverloginLogByIds(String[] ids);
	
}