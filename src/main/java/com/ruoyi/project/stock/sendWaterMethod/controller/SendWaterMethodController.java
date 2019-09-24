package com.ruoyi.project.stock.sendWaterMethod.controller;

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
import com.ruoyi.project.stock.sendWaterMethod.domain.SendWaterMethod;
import com.ruoyi.project.stock.sendWaterMethod.service.ISendWaterMethodService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 浇筑方式信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendWaterMethod")
public class SendWaterMethodController extends BaseController
{
    private String prefix = "stock/sendWaterMethod";
	
	@Autowired
	private ISendWaterMethodService sendWaterMethodService;
	
	@GetMapping()
	public String sendWaterMethod()
	{
	    return prefix + "/sendWaterMethod";
	}
	
	/**
	 * 查询浇筑方式列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendWaterMethod sendWaterMethod)
	{
		startPage();
        List<SendWaterMethod> list = sendWaterMethodService.selectSendWaterMethodList(sendWaterMethod);
		return getDataTable(list);
	}

	/**
	 * 查询浇筑方式列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendWaterMethod sendWaterMethod)
	{
        List<SendWaterMethod> list = sendWaterMethodService.selectSendWaterMethodList(sendWaterMethod);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出浇筑方式列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendWaterMethod sendWaterMethod)
    {
    	List<SendWaterMethod> list = sendWaterMethodService.selectSendWaterMethodList(sendWaterMethod);
        ExcelUtil<SendWaterMethod> util = new ExcelUtil<SendWaterMethod>(SendWaterMethod.class);
        return util.exportExcel(list, "sendWaterMethod");
    }
	
	/**
	 * 新增浇筑方式
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存浇筑方式
	 */
	@Log(title = "浇筑方式", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendWaterMethod sendWaterMethod)
	{
		sendWaterMethodService.insertSendWaterMethod(sendWaterMethod);
		return AjaxResult.success(sendWaterMethod);
	}

	/**
	 * 修改浇筑方式
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendWaterMethod sendWaterMethod = sendWaterMethodService.selectSendWaterMethodById(id);
		mmap.put("sendWaterMethod", sendWaterMethod);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存浇筑方式
	 */
	@Log(title = "浇筑方式", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendWaterMethod sendWaterMethod)
	{		
		return toAjax(sendWaterMethodService.updateSendWaterMethod(sendWaterMethod));
	}
	
	/**
	 * 删除浇筑方式
	 */
	@Log(title = "浇筑方式", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendWaterMethodService.deleteSendWaterMethodByIds(ids));
	}
	
}
