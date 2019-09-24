package com.ruoyi.project.cars.car.service;

import java.util.*;

import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.idgen.IdGen;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.mapper.DriverMapper;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.mapper.DriverCarMapper;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.redisEntry.CarsLonLat;
import com.ruoyi.project.system.user.domain.UserPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.remoting.support.DefaultRemoteInvocationExecutor;
import org.springframework.stereotype.Service;
import com.ruoyi.project.cars.car.mapper.CarMapper;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.scanner.Constant;

/**
 * 车辆 服务层实现
 * 
 * @author admin
 * @date 2019-04-25
 */
@Service
public class CarServiceImpl implements ICarService 
{
	@Autowired(required = false)
	private CarMapper carMapper;
	@Autowired(required = false)
	private DriverMapper driverMapper;
	@Autowired(required = false)
	private DriverCarMapper driverCarMapper;
	@Autowired
	private RedisUtils redisUtils;

	/**
     * 查询车辆信息
     * 
     * @param id 车辆ID
     * @return 车辆信息
     */
    @Override
	public Car selectCarById(Long id)
	{
	    return carMapper.selectCarById(id);
	}
	
	/**
     * 查询车辆列表
     * 
     * @param car 车辆信息
     * @return 车辆集合
     */
	@Override
	public List<Car> selectCarList(Car car)
	{
	    return carMapper.selectCarList(car);
	}
	
    /**
     * 新增车辆
     * 
     * @param car 车辆信息
     * @return 结果
     */
	@Override
	@Transactional(transactionManager = "masterTransactionManager")
	public int insertCar(Car car)
	{
		int ret = carMapper.insertCar(car);
		insertDriverCar(car);
		car = carMapper.selectCarById(car.getId());
		//重置redis服务器上的存储情况
		RedisDomainUtils.setRedisCarDomain(car);
	    return ret;
	}
	
	/**
     * 修改车辆
     * 
     * @param car 车辆信息
     * @return 结果
     */
	@Override
	@Transactional(transactionManager = "masterTransactionManager")
	public int updateCar(Car car)
	{
		//判断有没有车辆正在运行的
		List<DriverCar> rdcList = redisUtils.getLists(Constants.DRVCAR_PATTEN,DriverCar.class);
		List<TasksCars> tcList = redisUtils.getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
		String erorrMsg = "有车辆正在运行，无法更新";
		/*if(tcList.size()>0){
			tcList.forEach(item->{
				if(item.getCarId()==car.getId()){
					if(item.getStatus()!=null&&item.getStatus() == Constants.TASK_STATUS_ING){
						throw new BaseException(erorrMsg);
					}
				}
			});
		}
		if(rdcList.size()>0){
			rdcList.forEach(item->{
				if(item.getCarId() == car.getId()){
					if(redisUtils.exists(Constants.RUNINGCAR_PATTEN,item.getNotifyId())){
						throw new BaseException(erorrMsg);
					}
				}
			});
		}*/
		/**删除redis里面的司机-车辆信息*/
		List<DriverCar> dlist = driverCarMapper.selectDriverCarList(new DriverCar(){{setCarId(car.getId());}});
		// 删除车辆与司机关联
		driverCarMapper.deleteDriverCarById(car.getId());
		int ret = carMapper.updateCar(car);
		Car aftcar = carMapper.selectCarById(car.getId());
		aftcar.setDriverIds(car.getDriverIds());
		if(rdcList.size()>0){
			rdcList.forEach(item->{
				if(item.getCarId()==car.getId()){
					redisUtils.delete(Constants.DRVCAR_PATTEN+item.getNotifyId());
				}
			});
		}
		// 新增车辆与司机管理，同时维护新的drivercar
		insertDriverCar(aftcar);
		/**重置车辆信息*/
		RedisDomainUtils.setRedisCarDomain(aftcar);
	    return ret;
	}

	private void insertDriverCar(Car car){
		Long[] drivers = car.getDriverIds();
		/**删除redis里面的司机-车辆信息*/
		if (StringUtils.isNotNull(drivers))
		{
			// 新增用户与岗位管理
			List<DriverCar> list = new ArrayList<DriverCar>();
			for (Long driverId : drivers)
			{
				Driver driver = driverMapper.selectDriverById(driverId);
				DriverCar dcar = new DriverCar();
				dcar.setCarId(car.getId());//车id
				dcar.setDriverId(driver.getId());//车主id
				dcar.setCarNo(car.getCarNo());//车号
				dcar.setCarBrand(car.getCarNum());//车牌号
				dcar.setDriverMobile(driver.getDMobile());//车主电话
				dcar.setNotifyId(IdGen.nextId()); //生成唯一id
				RedisDomainUtils.setRedisDriverCarDomain(dcar);
				list.add(dcar);
			}
			if (list.size() > 0)
			{
				driverCarMapper.batchDriverCar(list);
			}
		}
	}

	/**
     * 删除车辆对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteCarByIds(String ids)
	{
		for(String s: Convert.toStrArray(ids)){
			Car car = carMapper.selectCarById(Long.valueOf(s));
			carMapper.deleteCarById(car.getId());
			driverCarMapper.deleteDriverCarByNo(car.getCarNo());
			RedisDomainUtils.setRedisCarDomain(car,true);
			List<DriverCar> drlist = redisUtils.getLists(Constants.DRVCAR_PATTEN,DriverCar.class);
			ListIterator<DriverCar> listIterator = drlist.listIterator();
			if(listIterator!=null){
				for(ListIterator<DriverCar> iter = listIterator;iter.hasNext();){
					DriverCar dci = iter.next();
					if(null!=dci&&dci.getCarNo().equals(car.getCarNo())){
						RedisDomainUtils.setRedisDriverCarDomain(dci,true);
						continue;
					}
				}
			}
		}
		return 1;
	}

	@Override
	public void updateCarApi(Car car) {
		carMapper.updateCar(car);
	}

	@Override
	public List<Car> selectCarListOfTask(Tasks tasks) {
		List<Car> carsAll = carMapper.selectCarList(null);
		String tcars = tasks.getCarList();
		if(StringUtils.isBlank(tcars)){
			return carsAll;
		}
		String[] tlist = tcars.split(",");
		for (Car car : carsAll)
		{
			for (String carNo : tlist)
			{
				if(StringUtils.isBlank(carNo)){
					continue;
				}else if (car.getCarNo().equals(Integer.valueOf(carNo)))
				{
					car.setFlag(true);
					break;
				}
			}
		}
		return carsAll;
	}

	@Override
	public void updateCarWeight(Car car) {
		carMapper.updateCarWeight(car);
	}

	@Override
	public Car selectOne(Car car) {
		List<Car> list = carMapper.selectCarList(car);
		if(null!=list&&list.size()>0){
			return list.get(0);
		}
		return null;
	}

}
