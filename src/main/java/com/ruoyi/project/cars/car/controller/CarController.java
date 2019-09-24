package com.ruoyi.project.cars.car.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.locator.common.LocatorConstant;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 车辆信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@RequestMapping("/cars/car")
public class CarController extends BaseController
{
    private String prefix = "cars/car";
	
	@Autowired
	private ICarService carService;

    @Autowired
	private IDriverService driverService;

	@Autowired
	private RestTemplate restTemplate;
	
	@RequiresPermissions("cars:car:view")
	@GetMapping()
	public String car()
	{
	    return prefix + "/car";
	}
	
	/**
	 * 查询车辆列表
	 */
	@RequiresPermissions("cars:car:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Car car)
	{
		startPage();
        List<Car> list = carService.selectCarList(car);
		return getDataTable(list);
	}


	/**
	 * 查询车辆列表
	 */
	@RequiresPermissions("cars:car:listAll")
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(Car car)
	{
		List<Car> list = carService.selectCarList(car);
		return AjaxResult.success(list);
	}


	/**
	 * 导出车辆列表
	 */
	@RequiresPermissions("cars:car:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Car car)
    {
    	List<Car> list = carService.selectCarList(car);
        ExcelUtil<Car> util = new ExcelUtil<Car>(Car.class);
        return util.exportExcel(list, "car");
    }

    //merge测试
	
	/**
	 * 新增车辆
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<Driver> drivers = driverService.selectDriverList(new Driver());
		mmap.put("drivers",drivers);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车辆
	 */
	@RequiresPermissions("cars:car:add")
	@Log(title = "车辆", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Car car)
	{
        String token = getToken();
        Map par = new HashMap();
        List<Car> data = Lists.newArrayList();
        par.put("accessToken",token);
        par.put("vin",car.getVin());
        par.put("licenseNumber",car.getCarNo());
        par.put("carOwner",car.getOwner());
        par.put( "contactUser",car.getOwner());
        par.put("contactTel",car.getOwnerPhone());
		data.add(car.setName(car.getOwner()+"--"+car.getOwnerPhone()));
        par.put("data",data);
        Map result = restTemplate.postForObject(LocatorConstant.FKZX_API+LocatorConstant.POST_CREATE_DEVICE+"?accessToken="+token,par,Map.class);
        if(null != result){
        	if(Integer.valueOf(result.get("code").toString()) == 0){
				return toAjax(carService.insertCar(car));
			}
         return error(result.get("result").toString());
		}
		return error();
	}

	/**
	 * 修改车辆
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Car car = carService.selectCarById(id);
		mmap.put("car", car);
		mmap.put("drivers", driverService.selectDriverListByCarId(car.getId()));
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车辆
	 */
	@RequiresPermissions("cars:car:edit")
	@Log(title = "车辆", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Car car)
	{		
		return toAjax(carService.updateCar(car));
	}
	
	/**
	 * 删除车辆
	 */
	@RequiresPermissions("cars:car:remove")
	@Log(title = "车辆", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(carService.deleteCarByIds(ids));
	}



}
