package com.ruoyi.project.stock.sendTanluodu.controller;

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
import com.ruoyi.project.stock.sendTanluodu.domain.SendTanluodu;
import com.ruoyi.project.stock.sendTanluodu.service.ISendTanluoduService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 坍落度信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendTanluodu")
public class SendTanluoduController extends BaseController
{
    private String prefix = "stock/sendTanluodu";
	
	@Autowired
	private ISendTanluoduService sendTanluoduService;
	
	@GetMapping()
	public String sendTanluodu()
	{
	    return prefix + "/sendTanluodu";
	}
	
	/**
	 * 查询坍落度列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendTanluodu sendTanluodu)
	{
		startPage();
        List<SendTanluodu> list = sendTanluoduService.selectSendTanluoduList(sendTanluodu);
		return getDataTable(list);
	}

	/**
	 * 查询坍落度列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendTanluodu sendTanluodu)
	{
        List<SendTanluodu> list = sendTanluoduService.selectSendTanluoduList(sendTanluodu);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出坍落度列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendTanluodu sendTanluodu)
    {
    	List<SendTanluodu> list = sendTanluoduService.selectSendTanluoduList(sendTanluodu);
        ExcelUtil<SendTanluodu> util = new ExcelUtil<SendTanluodu>(SendTanluodu.class);
        return util.exportExcel(list, "sendTanluodu");
    }
	
	/**
	 * 新增坍落度
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存坍落度
	 */
	@Log(title = "坍落度", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendTanluodu sendTanluodu)
	{
		sendTanluoduService.insertSendTanluodu(sendTanluodu);
		return AjaxResult.success(sendTanluodu);
	}

	/**
	 * 修改坍落度
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendTanluodu sendTanluodu = sendTanluoduService.selectSendTanluoduById(id);
		mmap.put("sendTanluodu", sendTanluodu);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存坍落度
	 */
	@Log(title = "坍落度", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendTanluodu sendTanluodu)
	{		
		return toAjax(sendTanluoduService.updateSendTanluodu(sendTanluodu));
	}
	
	/**
	 * 删除坍落度
	 */
	@Log(title = "坍落度", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendTanluoduService.deleteSendTanluoduByIds(ids));
	}
	
}
