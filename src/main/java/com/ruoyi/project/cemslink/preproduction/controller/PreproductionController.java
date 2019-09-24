package com.ruoyi.project.cemslink.preproduction.controller;

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
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产指令数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/preproduction")
public class PreproductionController extends BaseController
{
    private String prefix = "cemslink/preproduction";
	
	@Autowired
	private IPreproductionService preproductionService;
	
	@RequiresPermissions("cemslink:preproduction:view")
	@GetMapping()
	public String preproduction()
	{
	    return prefix + "/preproduction";
	}
	
	/**
	 * 查询生产指令数据列表
	 */
	@RequiresPermissions("cemslink:preproduction:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Preproduction preproduction)
	{
		startPage();
        List<Preproduction> list = preproductionService.selectPreproductionList(preproduction);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出生产指令数据列表
	 */
	@RequiresPermissions("cemslink:preproduction:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Preproduction preproduction)
    {
    	List<Preproduction> list = preproductionService.selectPreproductionList(preproduction);
        ExcelUtil<Preproduction> util = new ExcelUtil<Preproduction>(Preproduction.class);
        return util.exportExcel(list, "preproduction");
    }
	
	/**
	 * 新增生产指令数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存生产指令数据
	 */
	@RequiresPermissions("cemslink:preproduction:add")
	@Log(title = "生产指令数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Preproduction preproduction)
	{		
		return toAjax(preproductionService.insertPreproduction(preproduction));
	}

	/**
	 * 修改生产指令数据
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Preproduction preproduction = preproductionService.selectPreproductionById(sID);
		mmap.put("preproduction", preproduction);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存生产指令数据
	 */
	@RequiresPermissions("cemslink:preproduction:edit")
	@Log(title = "生产指令数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Preproduction preproduction)
	{		
		return toAjax(preproductionService.updatePreproduction(preproduction));
	}
	
	/**
	 * 删除生产指令数据
	 */
	@RequiresPermissions("cemslink:preproduction:remove")
	@Log(title = "生产指令数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(preproductionService.deletePreproductionByIds(ids));
	}
	
}
