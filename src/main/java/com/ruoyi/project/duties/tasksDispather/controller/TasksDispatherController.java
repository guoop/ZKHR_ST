package com.ruoyi.project.duties.tasksDispather.controller;

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
import com.ruoyi.project.duties.tasksDispather.domain.TasksDispather;
import com.ruoyi.project.duties.tasksDispather.service.ITasksDispatherService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 调度室信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@RequestMapping("/duties/tasksDispather")
public class TasksDispatherController extends BaseController
{
    private String prefix = "duties/tasksDispather";
	
	@Autowired
	private ITasksDispatherService tasksDispatherService;
	
	@RequiresPermissions("duties:tasksDispather:view")
	@GetMapping()
	public String tasksDispather()
	{
	    return prefix + "/tasksDispather";
	}
	
	/**
	 * 查询调度室列表
	 */
	@RequiresPermissions("duties:tasksDispather:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TasksDispather tasksDispather)
	{
		startPage();
        List<TasksDispather> list = tasksDispatherService.selectTasksDispatherList(tasksDispather);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出调度室列表
	 */
	@RequiresPermissions("duties:tasksDispather:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TasksDispather tasksDispather)
    {
    	List<TasksDispather> list = tasksDispatherService.selectTasksDispatherList(tasksDispather);
        ExcelUtil<TasksDispather> util = new ExcelUtil<TasksDispather>(TasksDispather.class);
        return util.exportExcel(list, "tasksDispather");
    }
	
	/**
	 * 新增调度室
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存调度室
	 */
	@RequiresPermissions("duties:tasksDispather:add")
	@Log(title = "调度室", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TasksDispather tasksDispather)
	{		
		return toAjax(tasksDispatherService.insertTasksDispather(tasksDispather));
	}

	/**
	 * 修改调度室
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TasksDispather tasksDispather = tasksDispatherService.selectTasksDispatherById(id);
		mmap.put("tasksDispather", tasksDispather);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存调度室
	 */
	@RequiresPermissions("duties:tasksDispather:edit")
	@Log(title = "调度室", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TasksDispather tasksDispather)
	{		
		return toAjax(tasksDispatherService.updateTasksDispather(tasksDispather));
	}
	
	/**
	 * 删除调度室
	 */
	@RequiresPermissions("duties:tasksDispather:remove")
	@Log(title = "调度室", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksDispatherService.deleteTasksDispatherByIds(ids));
	}
	
}
