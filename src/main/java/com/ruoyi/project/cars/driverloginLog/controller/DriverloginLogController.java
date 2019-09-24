package com.ruoyi.project.cars.driverloginLog.controller;

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
import com.ruoyi.project.cars.driverloginLog.domain.DriverloginLog;
import com.ruoyi.project.cars.driverloginLog.service.IDriverloginLogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 登陆记录信息操作处理
 * 
 * @author admin
 * @date 2019-06-15
 */
@Controller
@RequestMapping("/cars/driverloginLog")
public class DriverloginLogController extends BaseController
{
    private String prefix = "cars/driverloginLog";
	
	@Autowired
	private IDriverloginLogService driverloginLogService;
	
	@RequiresPermissions("cars:driverloginLog:view")
	@GetMapping()
	public String driverloginLog()
	{
	    return prefix + "/driverloginLog";
	}
	
	/**
	 * 查询登陆记录列表
	 */
	@RequiresPermissions("cars:driverloginLog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(DriverloginLog driverloginLog)
	{
		startPage();
        List<DriverloginLog> list = driverloginLogService.selectDriverloginLogList(driverloginLog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出登陆记录列表
	 */
	@RequiresPermissions("cars:driverloginLog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DriverloginLog driverloginLog)
    {
    	List<DriverloginLog> list = driverloginLogService.selectDriverloginLogList(driverloginLog);
        ExcelUtil<DriverloginLog> util = new ExcelUtil<DriverloginLog>(DriverloginLog.class);
        return util.exportExcel(list, "driverloginLog");
    }
	
	/**
	 * 新增登陆记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存登陆记录
	 */
	@RequiresPermissions("cars:driverloginLog:add")
	@Log(title = "登陆记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(DriverloginLog driverloginLog)
	{		
		return toAjax(driverloginLogService.insertDriverloginLog(driverloginLog));
	}

	/**
	 * 修改登陆记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		DriverloginLog driverloginLog = driverloginLogService.selectDriverloginLogById(id);
		mmap.put("driverloginLog", driverloginLog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存登陆记录
	 */
	@RequiresPermissions("cars:driverloginLog:edit")
	@Log(title = "登陆记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(DriverloginLog driverloginLog)
	{		
		return toAjax(driverloginLogService.updateDriverloginLog(driverloginLog));
	}
	
	/**
	 * 删除登陆记录
	 */
	@RequiresPermissions("cars:driverloginLog:remove")
	@Log(title = "登陆记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(driverloginLogService.deleteDriverloginLogByIds(ids));
	}
	
}
