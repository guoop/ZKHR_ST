package com.ruoyi.project.cars.driverCar.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 司机-车辆关系信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@RequestMapping("/cars/driverCar")
public class DriverCarController extends BaseController
{
    private String prefix = "cars/driverCar";
	
	@Autowired
	private IDriverCarService driverCarService;
	
	@RequiresPermissions("cars:driverCar:view")
	@GetMapping()
	public String driverCar()
	{
	    return prefix + "/driverCar";
	}
	
	/**
	 * 查询司机-车辆关系列表
	 */
	@RequiresPermissions("cars:driverCar:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DriverCar driverCar)
	{
		startPage();
        List<DriverCar> list = driverCarService.selectDriverCarList(driverCar);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出司机-车辆关系列表
	 */
	@RequiresPermissions("cars:driverCar:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DriverCar driverCar)
    {
    	List<DriverCar> list = driverCarService.selectDriverCarList(driverCar);
        ExcelUtil<DriverCar> util = new ExcelUtil<DriverCar>(DriverCar.class);
        return util.exportExcel(list, "driverCar");
    }
	
	/**
	 * 新增司机-车辆关系
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存司机-车辆关系
	 */
	@RequiresPermissions("cars:driverCar:add")
	@Log(title = "司机-车辆关系", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DriverCar driverCar)
	{		
		return toAjax(driverCarService.insertDriverCar(driverCar));
	}

	/**
	 * 修改司机-车辆关系
	 */
	@GetMapping("/edit/{driverId}")
	public String edit(@PathVariable("driverId") Long driverId, ModelMap mmap)
	{
		DriverCar driverCar = driverCarService.selectDriverCarById(driverId);
		mmap.put("driverCar", driverCar);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存司机-车辆关系
	 */
	@RequiresPermissions("cars:driverCar:edit")
	@Log(title = "司机-车辆关系", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DriverCar driverCar)
	{		
		return toAjax(driverCarService.updateDriverCar(driverCar));
	}
	
	/**
	 * 删除司机-车辆关系
	 */
	@RequiresPermissions("cars:driverCar:remove")
	@Log(title = "司机-车辆关系", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(driverCarService.deleteDriverCarByIds(ids));
	}
	
}
