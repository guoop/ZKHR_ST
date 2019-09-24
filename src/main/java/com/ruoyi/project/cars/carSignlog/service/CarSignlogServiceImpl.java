package com.ruoyi.project.cars.carSignlog.service;

import java.util.List;

import com.ruoyi.api.RedisDomainUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.carSignlog.mapper.CarSignlogMapper;
import com.ruoyi.project.cars.carSignlog.domain.CarSignlog;
import com.ruoyi.project.cars.carSignlog.service.ICarSignlogService;
import com.ruoyi.common.utils.text.Convert;

/**
 * 上班车辆 服务层实现
 * 
 * @author admin
 * @date 2019-07-15
 */
@Service
public class CarSignlogServiceImpl implements ICarSignlogService 
{
	@Autowired
	private CarSignlogMapper carSignlogMapper;

	/**
     * 查询上班车辆信息
     * 
     * @param id 上班车辆ID
     * @return 上班车辆信息
     */
    @Override
	public CarSignlog selectCarSignlogById(Long id)
	{
	    return carSignlogMapper.selectCarSignlogById(id);
	}
	
	/**
     * 查询上班车辆列表
     * 
     * @param carSignlog 上班车辆信息
     * @return 上班车辆集合
     */
	@Override
	public List<CarSignlog> selectCarSignlogList(CarSignlog carSignlog)
	{
	    return carSignlogMapper.selectCarSignlogList(carSignlog);
	}
	
    /**
     * 新增上班车辆
     * 
     * @param carSignlog 上班车辆信息
     * @return 结果
     */
	@Override
	public int insertCarSignlog(CarSignlog carSignlog)
	{
		List<CarSignlog> list = carSignlogMapper.selectCarSignlogList(new CarSignlog(){{
			setSqlWhere(" and to_days(now())-to_days(create_time)=0 ");
		}});
		//存在
		CarSignlog exist=null;
		if(list!=null&&list.size()>0){
			exist = list.get(0);
			exist.setCarNos(carSignlog.getCarNos());
			exist.setSuffixTime(carSignlog.getSuffixTime());
			carSignlogMapper.updateCarSignlog(exist);
		}else{
			int i = carSignlogMapper.insertCarSignlog(carSignlog);
			exist = carSignlog;
		}
		exist = carSignlogMapper.selectCarSignlogById(exist.getId());
		RedisDomainUtils.setRedisCarSignLog(exist);
	    return 1;
	}
	
	/**
     * 修改上班车辆
     * 
     * @param carSignlog 上班车辆信息
     * @return 结果
     */
	@Override
	public int updateCarSignlog(CarSignlog carSignlog)
	{
		int i=carSignlogMapper.updateCarSignlog(carSignlog);
		carSignlog = carSignlogMapper.selectCarSignlogById(carSignlog.getId());
		RedisDomainUtils.setRedisCarSignLog(carSignlog);
	    return i;
	}

	/**
     * 删除上班车辆对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarSignlogByIds(String ids)
	{
		return carSignlogMapper.deleteCarSignlogByIds(Convert.toStrArray(ids));
	}
	
}
