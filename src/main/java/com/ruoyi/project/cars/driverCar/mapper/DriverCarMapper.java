package com.ruoyi.project.cars.driverCar.mapper;

import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import java.util.List;	

/**
 * 司机-车辆关系 数据层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface DriverCarMapper 
{
	/**
     * 查询司机-车辆关系信息
     * 
     * @param driverId 司机-车辆关系ID
     * @return 司机-车辆关系信息
     */
	public DriverCar selectDriverCarById(Long driverId);
	
	/**
     * 查询司机-车辆关系列表
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 司机-车辆关系集合
     */
	public List<DriverCar> selectDriverCarList(DriverCar driverCar);
	
	/**
     * 新增司机-车辆关系
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 结果
     */
	public int insertDriverCar(DriverCar driverCar);
	
	/**
     * 修改司机-车辆关系
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 结果
     */
	public int updateDriverCar(DriverCar driverCar);
	
	/**
     * 删除司机-车辆关系
     * 
     * @param carId 司机-车辆关系ID
     * @return 结果
     */
	public int deleteDriverCarById(Long carId);
	
	/**
     * 批量删除司机-车辆关系
     * 
     * @param driverIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteDriverCarByIds(String[] driverIds);

    void batchDriverCar(List<DriverCar> list);

    void deleteDriverCarByNo(Integer carNo);
}