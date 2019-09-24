package com.ruoyi.project.cars.appVersion.controller;

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
import com.ruoyi.project.cars.appVersion.domain.AppVersion;
import com.ruoyi.project.cars.appVersion.service.IAppVersionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * app版本信息操作处理
 * 
 * @author admin
 * @date 2019-06-17
 */
@Controller
@RequestMapping("/cars/appVersion")
public class AppVersionController extends BaseController
{
    private String prefix = "cars/appVersion";
	
	@Autowired
	private IAppVersionService appVersionService;
	
	@RequiresPermissions("cars:appVersion:view")
	@GetMapping()
	public String appVersion()
	{
	    return prefix + "/appVersion";
	}
	
	/**
	 * 查询app版本列表
	 */
	@RequiresPermissions("cars:appVersion:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AppVersion appVersion)
	{
		startPage();
        List<AppVersion> list = appVersionService.selectAppVersionList(appVersion);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出app版本列表
	 */
	@RequiresPermissions("cars:appVersion:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AppVersion appVersion)
    {
    	List<AppVersion> list = appVersionService.selectAppVersionList(appVersion);
        ExcelUtil<AppVersion> util = new ExcelUtil<AppVersion>(AppVersion.class);
        return util.exportExcel(list, "appVersion");
    }
	
	/**
	 * 新增app版本
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存app版本
	 */
	@RequiresPermissions("cars:appVersion:add")
	@Log(title = "app版本", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AppVersion appVersion)
	{		
		return toAjax(appVersionService.insertAppVersion(appVersion));
	}

	/**
	 * 修改app版本
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		AppVersion appVersion = appVersionService.selectAppVersionById(id);
		mmap.put("appVersion", appVersion);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存app版本
	 */
	@RequiresPermissions("cars:appVersion:edit")
	@Log(title = "app版本", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AppVersion appVersion)
	{		
		return toAjax(appVersionService.updateAppVersion(appVersion));
	}
	
	/**
	 * 删除app版本
	 */
	@RequiresPermissions("cars:appVersion:remove")
	@Log(title = "app版本", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(appVersionService.deleteAppVersionByIds(ids));
	}
	
}
