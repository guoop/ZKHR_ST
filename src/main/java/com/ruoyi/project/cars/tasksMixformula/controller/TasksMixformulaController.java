package com.ruoyi.project.cars.tasksMixformula.controller;

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
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 任务配比产线关联信息操作处理
 * 
 * @author admin
 * @date 2019-07-04
 */
@Controller
@RequestMapping("/cars/tasksMixformula")
public class TasksMixformulaController extends BaseController
{
    private String prefix = "cars/tasksMixformula";
	
	@Autowired
	private ITasksMixformulaService tasksMixformulaService;
	
	@RequiresPermissions("cars:tasksMixformula:view")
	@GetMapping()
	public String tasksMixformula()
	{
	    return prefix + "/tasksMixformula";
	}
	
	/**
	 * 查询任务配比产线关联列表
	 */
	@RequiresPermissions("cars:tasksMixformula:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TasksMixformula tasksMixformula)
	{
		startPage();
        List<TasksMixformula> list = tasksMixformulaService.selectTasksMixformulaList(tasksMixformula);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务配比产线关联列表
	 */
	@RequiresPermissions("cars:tasksMixformula:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TasksMixformula tasksMixformula)
    {
    	List<TasksMixformula> list = tasksMixformulaService.selectTasksMixformulaList(tasksMixformula);
        ExcelUtil<TasksMixformula> util = new ExcelUtil<TasksMixformula>(TasksMixformula.class);
        return util.exportExcel(list, "tasksMixformula");
    }
	
	/**
	 * 新增任务配比产线关联
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务配比产线关联
	 */
	@RequiresPermissions("cars:tasksMixformula:add")
	@Log(title = "任务配比产线关联", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TasksMixformula tasksMixformula)
	{		
		return toAjax(tasksMixformulaService.insertTasksMixformula(tasksMixformula));
	}

	/**
	 * 修改任务配比产线关联
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		TasksMixformula tasksMixformula = tasksMixformulaService.selectTasksMixformulaById(id);
		mmap.put("tasksMixformula", tasksMixformula);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存任务配比产线关联
	 */
	@RequiresPermissions("cars:tasksMixformula:edit")
	@Log(title = "任务配比产线关联", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TasksMixformula tasksMixformula)
	{		
		return toAjax(tasksMixformulaService.updateTasksMixformula(tasksMixformula));
	}
	
	/**
	 * 删除任务配比产线关联
	 */
	@RequiresPermissions("cars:tasksMixformula:remove")
	@Log(title = "任务配比产线关联", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksMixformulaService.deleteTasksMixformulaByIds(ids));
	}
	
}
