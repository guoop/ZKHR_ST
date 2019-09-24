package com.ruoyi.project.cemslink.consumption.controller;

import java.util.List;

import com.ruoyi.project.cemslink.consumption.domain.ConsumptionReport;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.service.IConsumptionService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 生产消耗数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/consumption")
public class ConsumptionController extends BaseController
{
    private String prefix = "cemslink/consumption";
	
	@Autowired
	private IConsumptionService consumptionService;
	
	@RequiresPermissions("cemslink:consumption:view")
	@GetMapping()
	public String consumption()
	{
	    return prefix + "/consumption";
	}
	
	/**
	 * 查询生产消耗数据列表
	 */
	@RequiresPermissions("cemslink:consumption:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Consumption consumption)
	{
		startPage();
        List<Consumption> list = consumptionService.selectConsumptionList(consumption);
		return getDataTable(list);
	}

	/**
	 * 查询生产消耗数据列表
	 */
	@RequiresPermissions("cemslink:consumption:list")
	@PostMapping("/selectConsumptionNow")
	@ResponseBody
	public AjaxResult selectConsumptionNow(Consumption consumption)
	{
        ConsumptionVO obj = consumptionService.selectConsumptionNow(consumption);
		return AjaxResult.success(obj);
	}

	/**
	 * 导出生产消耗数据列表
	 */
	@RequiresPermissions("cemslink:consumption:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Consumption consumption)
    {
    	List<ConsumptionReport> list = consumptionService.selectConsumptionReports(consumption);
        ExcelUtil<ConsumptionReport> util = new ExcelUtil<ConsumptionReport>(ConsumptionReport.class);
        return util.exportExcel(list, "consumption");
    }
	
	/**
	 * 新增生产消耗数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存生产消耗数据
	 */
	@RequiresPermissions("cemslink:consumption:add")
	@Log(title = "生产消耗数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Consumption consumption)
	{		
		return toAjax(consumptionService.insertConsumption(consumption));
	}

	/**
	 * 修改生产消耗数据
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Consumption consumption = consumptionService.selectConsumptionById(sID);
		mmap.put("consumption", consumption);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存生产消耗数据
	 */
	@RequiresPermissions("cemslink:consumption:edit")
	@Log(title = "生产消耗数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Consumption consumption)
	{		
		return toAjax(consumptionService.updateConsumption(consumption));
	}
	
	/**
	 * 删除生产消耗数据
	 */
	@RequiresPermissions("cemslink:consumption:remove")
	@Log(title = "生产消耗数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(consumptionService.deleteConsumptionByIds(ids));
	}
	
}
