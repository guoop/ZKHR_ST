package com.ruoyi.project.cemslink.hopper.controller;

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
import com.ruoyi.project.cemslink.hopper.domain.Hopper;
import com.ruoyi.project.cemslink.hopper.service.IHopperService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * V形送料斗信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/hopper")
public class HopperController extends BaseController
{
    private String prefix = "cemslink/hopper";
	
	@Autowired
	private IHopperService hopperService;
	
	@RequiresPermissions("cemslink:hopper:view")
	@GetMapping()
	public String hopper()
	{
	    return prefix + "/hopper";
	}
	
	/**
	 * 查询V形送料斗列表
	 */
	@RequiresPermissions("cemslink:hopper:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Hopper hopper)
	{
		startPage();
        List<Hopper> list = hopperService.selectHopperList(hopper);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出V形送料斗列表
	 */
	@RequiresPermissions("cemslink:hopper:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Hopper hopper)
    {
    	List<Hopper> list = hopperService.selectHopperList(hopper);
        ExcelUtil<Hopper> util = new ExcelUtil<Hopper>(Hopper.class);
        return util.exportExcel(list, "hopper");
    }
	
	/**
	 * 新增V形送料斗
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存V形送料斗
	 */
	@RequiresPermissions("cemslink:hopper:add")
	@Log(title = "V形送料斗", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Hopper hopper)
	{		
		return toAjax(hopperService.insertHopper(hopper));
	}

	/**
	 * 修改V形送料斗
	 */
	@GetMapping("/edit/{iD}")
	public String edit(@PathVariable("iD") Long iD, ModelMap mmap)
	{
		Hopper hopper = hopperService.selectHopperById(iD);
		mmap.put("hopper", hopper);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存V形送料斗
	 */
	@RequiresPermissions("cemslink:hopper:edit")
	@Log(title = "V形送料斗", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Hopper hopper)
	{		
		return toAjax(hopperService.updateHopper(hopper));
	}
	
	/**
	 * 删除V形送料斗
	 */
	@RequiresPermissions("cemslink:hopper:remove")
	@Log(title = "V形送料斗", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(hopperService.deleteHopperByIds(ids));
	}
	
}
