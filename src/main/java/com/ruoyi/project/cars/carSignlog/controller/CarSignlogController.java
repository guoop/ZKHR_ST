package com.ruoyi.project.cars.carSignlog.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.ruoyi.project.cars.carSignlog.domain.CarSignlog;
import com.ruoyi.project.cars.carSignlog.service.ICarSignlogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 上班车辆信息操作处理
 * 
 * @author admin
 * @date 2019-07-15
 */
@Controller
@RequestMapping("/cars/carSignlog")
public class CarSignlogController extends BaseController
{
    private String prefix = "cars/carSignlog";
	
	@Autowired
	private ICarSignlogService carSignlogService;
	
	@RequiresPermissions("cars:carSignlog:view")
	@GetMapping()
	public String carSignlog()
	{
	    return prefix + "/carSignlog";
	}
	
	/**
	 * 查询上班车辆列表
	 */
	@RequiresPermissions("cars:carSignlog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(CarSignlog carSignlog)
	{
		startPage();
        List<CarSignlog> list = carSignlogService.selectCarSignlogList(carSignlog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出上班车辆列表
	 */
	@RequiresPermissions("cars:carSignlog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CarSignlog carSignlog)
    {
    	List<CarSignlog> list = carSignlogService.selectCarSignlogList(carSignlog);
        ExcelUtil<CarSignlog> util = new ExcelUtil<CarSignlog>(CarSignlog.class);
        return util.exportExcel(list, "carSignlog");
    }
	
	/**
	 * 新增上班车辆
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存上班车辆
	 */
	@RequiresPermissions("cars:carSignlog:add")
	@Log(title = "上班车辆", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(CarSignlog carSignlog)
	{
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
		carSignlog.setSuffixTime(sdf.format(new Date()));
		return toAjax(carSignlogService.insertCarSignlog(carSignlog));
	}

	/**
	 * 修改上班车辆
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		CarSignlog carSignlog = carSignlogService.selectCarSignlogById(id);
		mmap.put("carSignlog", carSignlog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存上班车辆
	 */
	@RequiresPermissions("cars:carSignlog:edit")
	@Log(title = "上班车辆", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(CarSignlog carSignlog)
	{		
		return toAjax(carSignlogService.updateCarSignlog(carSignlog));
	}
	
	/**
	 * 删除上班车辆
	 */
	@RequiresPermissions("cars:carSignlog:remove")
	@Log(title = "上班车辆", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(carSignlogService.deleteCarSignlogByIds(ids));
	}
	
}
