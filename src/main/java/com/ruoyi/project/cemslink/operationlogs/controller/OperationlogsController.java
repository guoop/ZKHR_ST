package com.ruoyi.project.cemslink.operationlogs.controller;

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
import com.ruoyi.project.cemslink.operationlogs.domain.Operationlogs;
import com.ruoyi.project.cemslink.operationlogs.service.IOperationlogsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * operationlogs信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/operationlogs")
public class OperationlogsController extends BaseController
{
    private String prefix = "cemslink/operationlogs";
	
	@Autowired
	private IOperationlogsService operationlogsService;
	
	@RequiresPermissions("cemslink:operationlogs:view")
	@GetMapping()
	public String operationlogs()
	{
	    return prefix + "/operationlogs";
	}
	
	/**
	 * 查询operationlogs列表
	 */
	@RequiresPermissions("cemslink:operationlogs:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Operationlogs operationlogs)
	{
		startPage();
        List<Operationlogs> list = operationlogsService.selectOperationlogsList(operationlogs);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出operationlogs列表
	 */
	@RequiresPermissions("cemslink:operationlogs:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Operationlogs operationlogs)
    {
    	List<Operationlogs> list = operationlogsService.selectOperationlogsList(operationlogs);
        ExcelUtil<Operationlogs> util = new ExcelUtil<Operationlogs>(Operationlogs.class);
        return util.exportExcel(list, "operationlogs");
    }
	
	/**
	 * 新增operationlogs
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存operationlogs
	 */
	@RequiresPermissions("cemslink:operationlogs:add")
	@Log(title = "operationlogs", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Operationlogs operationlogs)
	{		
		return toAjax(operationlogsService.insertOperationlogs(operationlogs));
	}

	/**
	 * 修改operationlogs
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Operationlogs operationlogs = operationlogsService.selectOperationlogsById(sID);
		mmap.put("operationlogs", operationlogs);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存operationlogs
	 */
	@RequiresPermissions("cemslink:operationlogs:edit")
	@Log(title = "operationlogs", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Operationlogs operationlogs)
	{		
		return toAjax(operationlogsService.updateOperationlogs(operationlogs));
	}
	
	/**
	 * 删除operationlogs
	 */
	@RequiresPermissions("cemslink:operationlogs:remove")
	@Log(title = "operationlogs", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(operationlogsService.deleteOperationlogsByIds(ids));
	}
	
}
