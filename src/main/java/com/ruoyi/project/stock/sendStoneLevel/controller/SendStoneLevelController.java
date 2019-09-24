package com.ruoyi.project.stock.sendStoneLevel.controller;

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
import com.ruoyi.project.stock.sendStoneLevel.domain.SendStoneLevel;
import com.ruoyi.project.stock.sendStoneLevel.service.ISendStoneLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 石子等级信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendStoneLevel")
public class SendStoneLevelController extends BaseController
{
    private String prefix = "stock/sendStoneLevel";
	
	@Autowired
	private ISendStoneLevelService sendStoneLevelService;
	
	@GetMapping()
	public String sendStoneLevel()
	{
	    return prefix + "/sendStoneLevel";
	}
	
	/**
	 * 查询石子等级列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendStoneLevel sendStoneLevel)
	{
		startPage();
        List<SendStoneLevel> list = sendStoneLevelService.selectSendStoneLevelList(sendStoneLevel);
		return getDataTable(list);
	}
	

	/**
	 * 查询石子等级列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendStoneLevel sendStoneLevel)
	{
        List<SendStoneLevel> list = sendStoneLevelService.selectSendStoneLevelList(sendStoneLevel);
		return AjaxResult.success(list);
	}


	/**
	 * 导出石子等级列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendStoneLevel sendStoneLevel)
    {
    	List<SendStoneLevel> list = sendStoneLevelService.selectSendStoneLevelList(sendStoneLevel);
        ExcelUtil<SendStoneLevel> util = new ExcelUtil<SendStoneLevel>(SendStoneLevel.class);
        return util.exportExcel(list, "sendStoneLevel");
    }
	
	/**
	 * 新增石子等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存石子等级
	 */
	@Log(title = "石子等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendStoneLevel sendStoneLevel)
	{
		sendStoneLevelService.insertSendStoneLevel(sendStoneLevel);
		return AjaxResult.success(sendStoneLevel);
	}

	/**
	 * 修改石子等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendStoneLevel sendStoneLevel = sendStoneLevelService.selectSendStoneLevelById(id);
		mmap.put("sendStoneLevel", sendStoneLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存石子等级
	 */
	@Log(title = "石子等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendStoneLevel sendStoneLevel)
	{		
		return toAjax(sendStoneLevelService.updateSendStoneLevel(sendStoneLevel));
	}
	
	/**
	 * 删除石子等级
	 */
	@Log(title = "石子等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendStoneLevelService.deleteSendStoneLevelByIds(ids));
	}
	
}
