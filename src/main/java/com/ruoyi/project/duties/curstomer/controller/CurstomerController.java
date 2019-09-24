package com.ruoyi.project.duties.curstomer.controller;

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
import com.ruoyi.project.duties.curstomer.domain.Curstomer;
import com.ruoyi.project.duties.curstomer.service.ICurstomerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 客户信息操作处理
 * 
 * @author admin
 * @date 2019-08-07
 */
@Controller
@RequestMapping("/duties/curstomer")
public class CurstomerController extends BaseController
{
    private String prefix = "duties/curstomer";
	
	@Autowired
	private ICurstomerService curstomerService;
	
	@RequiresPermissions("duties:curstomer:view")
	@GetMapping()
	public String curstomer()
	{
	    return prefix + "/curstomer";
	}

	@RequestMapping("/test")
	public String test(){
		return prefix + "/curstomer";
	}
	
	/**
	 * 查询客户列表
	 */
	@RequiresPermissions("duties:curstomer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Curstomer curstomer)
	{
		startPage();
        List<Curstomer> list = curstomerService.selectCurstomerList(curstomer);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出客户列表
	 */
	@RequiresPermissions("duties:curstomer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Curstomer curstomer)
    {
    	List<Curstomer> list = curstomerService.selectCurstomerList(curstomer);
        ExcelUtil<Curstomer> util = new ExcelUtil<Curstomer>(Curstomer.class);
        return util.exportExcel(list, "curstomer");
    }
	
	/**
	 * 新增客户
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存客户
	 */
	@RequiresPermissions("duties:curstomer:add")
	@Log(title = "客户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Curstomer curstomer)
	{		
		return toAjax(curstomerService.insertCurstomer(curstomer));
	}

	/**
	 * 修改客户
	 */
	@GetMapping("/edit/{cusId}")
	public String edit(@PathVariable("cusId") Long cusId, ModelMap mmap)
	{
		Curstomer curstomer = curstomerService.selectCurstomerById(cusId);
		mmap.put("curstomer", curstomer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户
	 */
	@RequiresPermissions("duties:curstomer:edit")
	@Log(title = "客户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Curstomer curstomer)
	{		
		return toAjax(curstomerService.updateCurstomer(curstomer));
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("duties:curstomer:remove")
	@Log(title = "客户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(curstomerService.deleteCurstomerByIds(ids));
	}
	
}
