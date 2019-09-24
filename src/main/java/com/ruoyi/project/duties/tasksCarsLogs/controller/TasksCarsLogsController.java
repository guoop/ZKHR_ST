package com.ruoyi.project.duties.tasksCarsLogs.controller;

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
import com.ruoyi.project.duties.tasksCarsLogs.domain.TasksCarsLogs;
import com.ruoyi.project.duties.tasksCarsLogs.service.ITasksCarsLogsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 车辆行驶记录信息操作处理
 * 
 * @author admin
 * @date 2019-05-28
 */
@Controller
@RequestMapping("/duties/tasksCarsLogs")
public class TasksCarsLogsController extends BaseController
{
    private String prefix = "duties/tasksCarsLogs";
	
	@Autowired
	private ITasksCarsLogsService tasksCarsLogsService;
	
	@RequiresPermissions("duties:tasksCarsLogs:view")
	@GetMapping()
	public String tasksCarsLogs()
	{
	    return prefix + "/tasksCarsLogs";
	}
	
	/**
	 * 查询车辆行驶记录列表
	 */
	@RequiresPermissions("duties:tasksCarsLogs:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TasksCarsLogs tasksCarsLogs)
	{
		startPage();
        List<TasksCarsLogs> list = tasksCarsLogsService.selectTasksCarsLogsList(tasksCarsLogs);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出车辆行驶记录列表
	 */
	@RequiresPermissions("duties:tasksCarsLogs:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TasksCarsLogs tasksCarsLogs)
    {
    	List<TasksCarsLogs> list = tasksCarsLogsService.selectTasksCarsLogsList(tasksCarsLogs);
        ExcelUtil<TasksCarsLogs> util = new ExcelUtil<TasksCarsLogs>(TasksCarsLogs.class);
        return util.exportExcel(list, "tasksCarsLogs");
    }
	
	/**
	 * 新增车辆行驶记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存车辆行驶记录
	 */
	@RequiresPermissions("duties:tasksCarsLogs:add")
	@Log(title = "车辆行驶记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TasksCarsLogs tasksCarsLogs)
	{		
		return toAjax(tasksCarsLogsService.insertTasksCarsLogs(tasksCarsLogs));
	}

	/**
	 * 修改车辆行驶记录
	 */
	@GetMapping("/edit/{taskCarId}")
	public String edit(@PathVariable("taskCarId") Long taskCarId, ModelMap mmap)
	{
		TasksCarsLogs tasksCarsLogs = tasksCarsLogsService.selectTasksCarsLogsById(taskCarId);
		mmap.put("tasksCarsLogs", tasksCarsLogs);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存车辆行驶记录
	 */
	@RequiresPermissions("duties:tasksCarsLogs:edit")
	@Log(title = "车辆行驶记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TasksCarsLogs tasksCarsLogs)
	{		
		return toAjax(tasksCarsLogsService.updateTasksCarsLogs(tasksCarsLogs));
	}
	
	/**
	 * 删除车辆行驶记录
	 */
	@RequiresPermissions("duties:tasksCarsLogs:remove")
	@Log(title = "车辆行驶记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksCarsLogsService.deleteTasksCarsLogsByIds(ids));
	}
	
}
