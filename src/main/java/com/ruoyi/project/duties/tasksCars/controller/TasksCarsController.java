package com.ruoyi.project.duties.tasksCars.controller;

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
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务车辆信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@RequestMapping("/duties/tasksCars")
public class TasksCarsController extends BaseController
{
    private String prefix = "duties/tasksCars";
	
	@Autowired
	private ITasksCarsService tasksCarsService;
	
	@RequiresPermissions("duties:tasksCars:view")
	@GetMapping()
	public String tasksCars()
	{
	    return prefix + "/tasksCars";
	}
	
	/**
	 * 查询任务车辆列表
	 */
	@RequiresPermissions("duties:tasksCars:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TasksCars tasksCars)
	{
		startPage();
		tasksCars.setCarCnt(null);
		tasksCars.setPrivilegeTask(null);
        List<TasksCars> list = tasksCarsService.selectTasksCarsList(tasksCars);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务车辆列表
	 */
	@RequiresPermissions("duties:tasksCars:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TasksCars tasksCars)
    {
    	List<TasksCars> list = tasksCarsService.selectTasksCarsList(tasksCars);
        ExcelUtil<TasksCars> util = new ExcelUtil<TasksCars>(TasksCars.class);
        return util.exportExcel(list, "tasksCars");
    }
	
	/**
	 * 新增任务车辆
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务车辆
	 */
	@RequiresPermissions("duties:tasksCars:add")
	@Log(title = "任务车辆", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TasksCars tasksCars)
	{		
		return toAjax(tasksCarsService.insertTasksCars(tasksCars));
	}

	/**
	 * 修改任务车辆
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TasksCars tasksCars = tasksCarsService.selectTasksCarsById(id);
		mmap.put("tasksCars", tasksCars);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务车辆
	 */
	@RequiresPermissions("duties:tasksCars:edit")
	@Log(title = "任务车辆", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TasksCars tasksCars)
	{		
		return toAjax(tasksCarsService.updateTasksCars(tasksCars));
	}
	
	/**
	 * 删除任务车辆
	 */
	@RequiresPermissions("duties:tasksCars:remove")
	@Log(title = "任务车辆", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksCarsService.deleteTasksCarsByIds(ids));
	}


	/**
	 * 删除任务车辆
	 */
	@RequiresPermissions("duties:tasksCars:remove")
	@Log(title = "删除任务车辆", businessType = BusinessType.DELETE)
	@PostMapping( "/removeOne/{id}")
	@ResponseBody
	public AjaxResult removeOne(@PathVariable("id") Long id)
	{
		return tasksCarsService.removeOne(id,this.getSysUser());
	}

	/**
	 * 调度手动到达任务
	 */
	@RequiresPermissions("duties:tasksCars:edit")
	@Log(title = "调度手动到达任务", businessType = BusinessType.UPDATE)
	@PostMapping( "/arrived/{id}")
	@ResponseBody
	public AjaxResult arrived(@PathVariable("id") Long id)
	{
		return tasksCarsService.arrived(id,this.getSysUser());
	}

}
