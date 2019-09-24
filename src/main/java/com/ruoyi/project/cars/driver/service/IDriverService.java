package com.ruoyi.project.cars.driver.service;

import com.ruoyi.project.cars.driver.domain.Driver;
import java.util.List;

/**
 * 司机 服务层
 * 
 * @author admin
 * @date 2019-04-25
 */
public interface IDriverService 
{
	/**
     * 查询司机信息
     * 
     * @param id 司机ID
     * @return 司机信息
     */
	public Driver selectDriverById(Long id);
	
	/**
     * 查询司机列表
     * 
     * @param driver 司机信息
     * @return 司机集合
     */
	public List<Driver> selectDriverList(Driver driver);
	
	/**
     * 新增司机
     * 
     * @param driver 司机信息
     * @return 结果
     */
	public int insertDriver(Driver driver);
	
	/**
     * 修改司机
     * 
     * @param driver 司机信息
     * @return 结果
     */
	public int updateDriver(Driver driver);
		
	/**
     * 删除司机信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteDriverByIds(String ids);

	Object selectDriverListByCarId(Long id);
}
