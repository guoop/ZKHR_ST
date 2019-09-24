package com.ruoyi.project.cars.driver.controller;

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
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 司机信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@RequestMapping("/cars/driver")
public class DriverController extends BaseController
{
    private String prefix = "cars/driver";
	
	@Autowired
	private IDriverService driverService;
	
	@RequiresPermissions("cars:driver:view")
	@GetMapping()
	public String driver()
	{
	    return prefix + "/driver";
	}
	
	/**
	 * 查询司机列表
	 */
	@RequiresPermissions("cars:driver:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Driver driver)
	{
		startPage();
        List<Driver> list = driverService.selectDriverList(driver);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出司机列表
	 */
	@RequiresPermissions("cars:driver:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Driver driver)
    {
    	List<Driver> list = driverService.selectDriverList(driver);
        ExcelUtil<Driver> util = new ExcelUtil<Driver>(Driver.class);
        return util.exportExcel(list, "driver");
    }
	
	/**
	 * 新增司机
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存司机
	 */
	@RequiresPermissions("cars:driver:add")
	@Log(title = "司机", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Driver driver)
	{		
		return toAjax(driverService.insertDriver(driver));
	}

	/**
	 * 修改司机
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Driver driver = driverService.selectDriverById(id);
		mmap.put("driver", driver);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存司机
	 */
	@RequiresPermissions("cars:driver:edit")
	@Log(title = "司机", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Driver driver)
	{		
		return toAjax(driverService.updateDriver(driver));
	}
	
	/**
	 * 删除司机
	 */
	@RequiresPermissions("cars:driver:remove")
	@Log(title = "司机", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(driverService.deleteDriverByIds(ids));
	}
	
}
