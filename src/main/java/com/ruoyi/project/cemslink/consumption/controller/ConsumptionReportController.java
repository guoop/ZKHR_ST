package com.ruoyi.project.cemslink.consumption.controller;

import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionReport;
import com.ruoyi.project.cemslink.consumption.service.IConsumptionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 生产消耗数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/consumptionreport")
public class ConsumptionReportController extends BaseController
{
    private String prefix = "cemslink/consumption";
	
	@Autowired
	private IConsumptionService consumptionService;
	
	@RequiresPermissions("cemslink:consumption:report")
	@GetMapping()
	public String consumption()
	{
	    return prefix + "/consumptionreport";
	}

	/**
	 * 查询生产消耗报表
	 */
	@RequiresPermissions("cemslink:consumption:list")
	@RequestMapping("/report")
	@ResponseBody
	public AjaxResult report(Consumption consumption)
	{
		List<ConsumptionReport> reportList = consumptionService.selectConsumptionReports(consumption);
		return AjaxResult.success().put("reportList",reportList);
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
}
