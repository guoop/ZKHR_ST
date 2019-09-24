package com.ruoyi.project.stock.sendSandLevel.controller;

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
import com.ruoyi.project.stock.sendSandLevel.domain.SendSandLevel;
import com.ruoyi.project.stock.sendSandLevel.service.ISendSandLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 沙子等级信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendSandLevel")
public class SendSandLevelController extends BaseController
{
    private String prefix = "stock/sendSandLevel";
	
	@Autowired
	private ISendSandLevelService sendSandLevelService;
	
	@GetMapping()
	public String sendSandLevel()
	{
	    return prefix + "/sendSandLevel";
	}
	
	/**
	 * 查询沙子等级列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendSandLevel sendSandLevel)
	{
		startPage();
        List<SendSandLevel> list = sendSandLevelService.selectSendSandLevelList(sendSandLevel);
		return getDataTable(list);
	}

	/**
	 * 查询沙子等级列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendSandLevel sendSandLevel)
	{
        List<SendSandLevel> list = sendSandLevelService.selectSendSandLevelList(sendSandLevel);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出沙子等级列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendSandLevel sendSandLevel)
    {
    	List<SendSandLevel> list = sendSandLevelService.selectSendSandLevelList(sendSandLevel);
        ExcelUtil<SendSandLevel> util = new ExcelUtil<SendSandLevel>(SendSandLevel.class);
        return util.exportExcel(list, "sendSandLevel");
    }
	
	/**
	 * 新增沙子等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存沙子等级
	 */
	@Log(title = "沙子等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendSandLevel sendSandLevel)
	{
		sendSandLevelService.insertSendSandLevel(sendSandLevel);
		return AjaxResult.success(sendSandLevel);
	}

	/**
	 * 修改沙子等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendSandLevel sendSandLevel = sendSandLevelService.selectSendSandLevelById(id);
		mmap.put("sendSandLevel", sendSandLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存沙子等级
	 */
	@Log(title = "沙子等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendSandLevel sendSandLevel)
	{		
		return toAjax(sendSandLevelService.updateSendSandLevel(sendSandLevel));
	}
	
	/**
	 * 删除沙子等级
	 */
	@Log(title = "沙子等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendSandLevelService.deleteSendSandLevelByIds(ids));
	}
	
}
