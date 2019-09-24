package com.ruoyi.project.cars.driver.service;

import java.util.List;

import com.ruoyi.api.RedisDomainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.driver.mapper.DriverMapper;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 司机 服务层实现
 * 
 * @author admin
 * @date 2019-04-25
 */
@Service
public class DriverServiceImpl implements IDriverService 
{
	@Autowired
	private DriverMapper driverMapper;

	/**
     * 查询司机信息
     * 
     * @param id 司机ID
     * @return 司机信息
     */
    @Override
	public Driver selectDriverById(Long id)
	{
	    return driverMapper.selectDriverById(id);
	}
	
	/**
     * 查询司机列表
     * 
     * @param driver 司机信息
     * @return 司机集合
     */
	@Override
	public List<Driver> selectDriverList(Driver driver)
	{
	    return driverMapper.selectDriverList(driver);
	}
	
    /**
     * 新增司机
     * 
     * @param driver 司机信息
     * @return 结果
     */
	@Override
	public int insertDriver(Driver driver)
	{
		int ret = driverMapper.insertDriver(driver);
		RedisDomainUtils.setRedisDriverDomain(driver);
	    return ret;
	}
	
	/**
     * 修改司机
     * 
     * @param driver 司机信息
     * @return 结果
     */
	@Override
	public int updateDriver(Driver driver)
	{
		int ret = driverMapper.updateDriver(driver);
		driver = driverMapper.selectDriverById(driver.getId());
		RedisDomainUtils.setRedisDriverDomain(driver);
	    return ret;
	}

	/**
     * 删除司机对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDriverByIds(String ids)
	{
		return driverMapper.deleteDriverByIds(Convert.toStrArray(ids));
	}

	@Override
	public List<Driver> selectDriverListByCarId(Long id) {
		List<Driver> carDrivers = driverMapper.selectDriversByCarId(id);//拥有的
		List<Driver> driversAll = driverMapper.selectDriverList(new Driver());//所有的
		for (Driver itemAll : driversAll)
		{
			for (Driver item : carDrivers)
			if (item.getId().longValue() == itemAll.getId().longValue())
			{
				itemAll.setFlag(true);
				break;
			}
		}
		return driversAll;
	}

}
