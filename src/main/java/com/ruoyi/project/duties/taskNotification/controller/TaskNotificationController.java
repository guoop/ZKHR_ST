package com.ruoyi.project.duties.taskNotification.controller;

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
import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import com.ruoyi.project.duties.taskNotification.service.ITaskNotificationService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 消息信息操作处理
 * 
 * @author admin
 * @date 2019-04-29
 */
@Controller
@RequestMapping("/duties/taskNotification")
public class TaskNotificationController extends BaseController
{
    private String prefix = "duties/taskNotification";
	
	@Autowired
	private ITaskNotificationService taskNotificationService;
	
	@RequiresPermissions("duties:taskNotification:view")
	@GetMapping()
	public String taskNotification()
	{
	    return prefix + "/taskNotification";
	}
	
	/**
	 * 查询消息列表
	 */
	@RequiresPermissions("duties:taskNotification:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TaskNotification taskNotification)
	{
		startPage();
        List<TaskNotification> list = taskNotificationService.selectTaskNotificationList(taskNotification);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出消息列表
	 */
	@RequiresPermissions("duties:taskNotification:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TaskNotification taskNotification)
    {
    	List<TaskNotification> list = taskNotificationService.selectTaskNotificationList(taskNotification);
        ExcelUtil<TaskNotification> util = new ExcelUtil<TaskNotification>(TaskNotification.class);
        return util.exportExcel(list, "taskNotification");
    }
	
	/**
	 * 新增消息
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存消息
	 */
	@RequiresPermissions("duties:taskNotification:add")
	@Log(title = "消息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TaskNotification taskNotification)
	{		
		return toAjax(taskNotificationService.insertTaskNotification(taskNotification));
	}

	/**
	 * 修改消息
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TaskNotification taskNotification = taskNotificationService.selectTaskNotificationById(id);
		mmap.put("taskNotification", taskNotification);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存消息
	 */
	@RequiresPermissions("duties:taskNotification:edit")
	@Log(title = "消息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TaskNotification taskNotification)
	{		
		return toAjax(taskNotificationService.updateTaskNotification(taskNotification));
	}
	
	/**
	 * 删除消息
	 */
	@RequiresPermissions("duties:taskNotification:remove")
	@Log(title = "消息", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(taskNotificationService.deleteTaskNotificationByIds(ids));
	}
	
}
