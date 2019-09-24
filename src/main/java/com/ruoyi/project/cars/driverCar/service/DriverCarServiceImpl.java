package com.ruoyi.project.cars.driverCar.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.driverCar.mapper.DriverCarMapper;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 司机-车辆关系 服务层实现
 * 
 * @author admin
 * @date 2019-04-25
 */
@Service
public class DriverCarServiceImpl implements IDriverCarService 
{
	@Autowired
	private DriverCarMapper driverCarMapper;

	/**
     * 查询司机-车辆关系信息
     * 
     * @param driverId 司机-车辆关系ID
     * @return 司机-车辆关系信息
     */
    @Override
	public DriverCar selectDriverCarById(Long driverId)
	{
	    return driverCarMapper.selectDriverCarById(driverId);
	}
	
	/**
     * 查询司机-车辆关系列表
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 司机-车辆关系集合
     */
	@Override
	public List<DriverCar> selectDriverCarList(DriverCar driverCar)
	{
	    return driverCarMapper.selectDriverCarList(driverCar);
	}
	
    /**
     * 新增司机-车辆关系
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 结果
     */
	@Override
	public int insertDriverCar(DriverCar driverCar)
	{
	    return driverCarMapper.insertDriverCar(driverCar);
	}
	
	/**
     * 修改司机-车辆关系
     * 
     * @param driverCar 司机-车辆关系信息
     * @return 结果
     */
	@Override
	public int updateDriverCar(DriverCar driverCar)
	{
	    return driverCarMapper.updateDriverCar(driverCar);
	}

	/**
     * 删除司机-车辆关系对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteDriverCarByIds(String ids)
	{
		return driverCarMapper.deleteDriverCarByIds(Convert.toStrArray(ids));
	}
	
}
