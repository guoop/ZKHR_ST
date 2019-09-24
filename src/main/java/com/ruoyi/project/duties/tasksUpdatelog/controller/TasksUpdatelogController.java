package com.ruoyi.project.duties.tasksUpdatelog.controller;

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
import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import com.ruoyi.project.duties.tasksUpdatelog.service.ITasksUpdatelogService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务单修改记录信息操作处理
 * 
 * @author admin
 * @date 2019-06-14
 */
@Controller
@RequestMapping("/duties/tasksUpdatelog")
public class TasksUpdatelogController extends BaseController
{
    private String prefix = "duties/tasksUpdatelog";
	
	@Autowired
	private ITasksUpdatelogService tasksUpdatelogService;
	
	@RequiresPermissions("duties:tasksUpdatelog:view")
	@GetMapping()
	public String tasksUpdatelog()
	{
	    return prefix + "/tasksUpdatelog";
	}
	
	/**
	 * 查询任务单修改记录列表
	 */
	@RequiresPermissions("duties:tasksUpdatelog:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TasksUpdatelog tasksUpdatelog)
	{
		startPage();
        List<TasksUpdatelog> list = tasksUpdatelogService.selectTasksUpdatelogList(tasksUpdatelog);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务单修改记录列表
	 */
	@RequiresPermissions("duties:tasksUpdatelog:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TasksUpdatelog tasksUpdatelog)
    {
    	List<TasksUpdatelog> list = tasksUpdatelogService.selectTasksUpdatelogList(tasksUpdatelog);
        ExcelUtil<TasksUpdatelog> util = new ExcelUtil<TasksUpdatelog>(TasksUpdatelog.class);
        return util.exportExcel(list, "tasksUpdatelog");
    }
	
	/**
	 * 新增任务单修改记录
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务单修改记录
	 */
	@RequiresPermissions("duties:tasksUpdatelog:add")
	@Log(title = "任务单修改记录", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TasksUpdatelog tasksUpdatelog)
	{		
		return toAjax(tasksUpdatelogService.insertTasksUpdatelog(tasksUpdatelog));
	}

	/**
	 * 修改任务单修改记录
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TasksUpdatelog tasksUpdatelog = tasksUpdatelogService.selectTasksUpdatelogById(id);
		mmap.put("tasksUpdatelog", tasksUpdatelog);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务单修改记录
	 */
	@RequiresPermissions("duties:tasksUpdatelog:edit")
	@Log(title = "任务单修改记录", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TasksUpdatelog tasksUpdatelog)
	{		
		return toAjax(tasksUpdatelogService.updateTasksUpdatelog(tasksUpdatelog));
	}
	
	/**
	 * 删除任务单修改记录
	 */
	@RequiresPermissions("duties:tasksUpdatelog:remove")
	@Log(title = "任务单修改记录", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksUpdatelogService.deleteTasksUpdatelogByIds(ids));
	}
	
}
