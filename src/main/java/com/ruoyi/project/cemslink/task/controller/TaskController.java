package com.ruoyi.project.cemslink.task.controller;

import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
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
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.cemslink.task.service.ITaskService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务单数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/task")
public class TaskController extends BaseController
{
    private String prefix = "cemslink/task";

    @Autowired
	private RedisUtils redisUtils;
	
	@Autowired
	private ITaskService taskService;
	@Autowired
	private ITaskService tService;
	
	@RequiresPermissions("cemslink:task:view")
	@GetMapping()
	public String task()
	{
	    return prefix + "/task";
	}
	
	/**
	 * 查询任务单数据列表
	 */
	@RequiresPermissions("cemslink:task:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Task task)
	{
		startPage();
        List<Task> list = taskService.selectTaskList(task);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务单数据列表
	 */
	@RequiresPermissions("cemslink:task:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Task task)
    {
    	List<Task> list = taskService.selectTaskList(task);
        ExcelUtil<Task> util = new ExcelUtil<Task>(Task.class);
        return util.exportExcel(list, "task");
    }
	
	/**
	 * 新增任务单数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务单数据
	 */
	@RequiresPermissions("cemslink:task:add")
	@Log(title = "任务单数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Task task)
	{		
		return toAjax(taskService.insertTask(task));
	}

	/**
	 * 修改任务单数据
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Task task = taskService.selectTaskById(sID);
		mmap.put("task", task);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务单数据
	 */
	@RequiresPermissions("cemslink:task:edit")
	@Log(title = "任务单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Task task)
	{		
		return toAjax(taskService.updateTask(task));
	}


	/**
	 * 删除任务单数据
	 */
	@RequiresPermissions("cemslink:task:remove")
	@Log(title = "任务单数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(taskService.deleteTaskByIds(ids));
	}

}
