package com.ruoyi.project.cars.car.mapper;

import com.ruoyi.project.cars.car.domain.Car;
import java.util.List;	

/**
 * 车辆 数据层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface CarMapper 
{
	/**
     * 查询车辆信息
     * 
     * @param id 车辆ID
     * @return 车辆信息
     */
	public Car selectCarById(Long id);
	
	/**
     * 查询车辆列表
     * 
     * @param car 车辆信息
     * @return 车辆集合
     */
	public List<Car> selectCarList(Car car);
	
	/**
     * 新增车辆
     * 
     * @param car 车辆信息
     * @return 结果
     */
	public int insertCar(Car car);
	
	/**
     * 修改车辆
     * 
     * @param car 车辆信息
     * @return 结果
     */
	public int updateCar(Car car);
	
	/**
     * 删除车辆
     * 
     * @param id 车辆ID
     * @return 结果
     */
	public int deleteCarById(Long id);
	
	/**
     * 批量删除车辆
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteCarByIds(String[] ids);

	void updateCarWeight(Car car);
}