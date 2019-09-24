package com.ruoyi.project.cars.carSignlog.mapper;

import com.ruoyi.project.cars.carSignlog.domain.CarSignlog;
import java.util.List;	

/**
 * 上班车辆 数据层
 * 
 * @author admin
 * @date 2019-07-15
 */
public interface CarSignlogMapper 
{
	/**
     * 查询上班车辆信息
     * 
     * @param id 上班车辆ID
     * @return 上班车辆信息
     */
	public CarSignlog selectCarSignlogById(Long id);
	
	/**
     * 查询上班车辆列表
     * 
     * @param carSignlog 上班车辆信息
     * @return 上班车辆集合
     */
	public List<CarSignlog> selectCarSignlogList(CarSignlog carSignlog);
	
	/**
     * 新增上班车辆
     * 
     * @param carSignlog 上班车辆信息
     * @return 结果
     */
	public int insertCarSignlog(CarSignlog carSignlog);
	
	/**
     * 修改上班车辆
     * 
     * @param carSignlog 上班车辆信息
     * @return 结果
     */
	public int updateCarSignlog(CarSignlog carSignlog);
	
	/**
     * 删除上班车辆
     * 
     * @param id 上班车辆ID
     * @return 结果
     */
	public int deleteCarSignlogById(Long id);
	
	/**
     * 批量删除上班车辆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarSignlogByIds(String[] ids);
	
}