package com.ruoyi.project.profile.profileroom.controller;

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
import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import com.ruoyi.project.profile.profileroom.service.IProfileroomService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务资料信息操作处理
 * 
 * @author admin
 * @date 2019-05-30
 */
@Controller
@RequestMapping("/profile/profileroom")
public class ProfileroomController extends BaseController
{
    private String prefix = "profile/profileroom";
	
	@Autowired
	private IProfileroomService profileroomService;
	
	@RequiresPermissions("profile:profileroom:view")
	@GetMapping()
	public String profileroom()
	{
	    return prefix + "/profileroom";
	}
	
	/**
	 * 查询任务资料列表
	 */
	@RequiresPermissions("profile:profileroom:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Profileroom profileroom)
	{
		startPage();
        List<Profileroom> list = profileroomService.selectProfileroomList(profileroom);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务资料列表
	 */
	@RequiresPermissions("profile:profileroom:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Profileroom profileroom)
    {
    	List<Profileroom> list = profileroomService.selectProfileroomList(profileroom);
        ExcelUtil<Profileroom> util = new ExcelUtil<Profileroom>(Profileroom.class);
        return util.exportExcel(list, "profileroom");
    }
	
	/**
	 * 新增任务资料
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务资料
	 */
	@RequiresPermissions("profile:profileroom:add")
	@Log(title = "任务资料", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Profileroom profileroom)
	{		
		return toAjax(profileroomService.insertProfileroom(profileroom));
	}

	/**
	 * 修改任务资料
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Profileroom profileroom = profileroomService.selectProfileroomById(id);
		mmap.put("profileroom", profileroom);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务资料
	 */
	@RequiresPermissions("profile:profileroom:edit")
	@Log(title = "任务资料", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Profileroom profileroom)
	{		
		return toAjax(profileroomService.updateProfileroom(profileroom));
	}
	
	/**
	 * 删除任务资料
	 */
	@RequiresPermissions("profile:profileroom:remove")
	@Log(title = "任务资料", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(profileroomService.deleteProfileroomByIds(ids));
	}
	
}
