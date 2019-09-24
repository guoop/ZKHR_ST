package com.ruoyi.project.stock.sendAdmixsture.controller;

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
import com.ruoyi.project.stock.sendAdmixsture.domain.SendAdmixsture;
import com.ruoyi.project.stock.sendAdmixsture.service.ISendAdmixstureService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 外加剂信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendAdmixsture")
public class SendAdmixstureController extends BaseController
{
    private String prefix = "stock/sendAdmixsture";
	
	@Autowired
	private ISendAdmixstureService sendAdmixstureService;
	
	@GetMapping()
	public String sendAdmixsture()
	{
	    return prefix + "/sendAdmixsture";
	}
	
	/**
	 * 查询外加剂列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendAdmixsture sendAdmixsture)
	{
		startPage();
        List<SendAdmixsture> list = sendAdmixstureService.selectSendAdmixstureList(sendAdmixsture);
		return getDataTable(list);
	}

	/**
	 * 查询外加剂列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendAdmixsture sendAdmixsture)
	{
        List<SendAdmixsture> list = sendAdmixstureService.selectSendAdmixstureList(sendAdmixsture);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出外加剂列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendAdmixsture sendAdmixsture)
    {
    	List<SendAdmixsture> list = sendAdmixstureService.selectSendAdmixstureList(sendAdmixsture);
        ExcelUtil<SendAdmixsture> util = new ExcelUtil<SendAdmixsture>(SendAdmixsture.class);
        return util.exportExcel(list, "sendAdmixsture");
    }
	
	/**
	 * 新增外加剂
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存外加剂
	 */
	@Log(title = "外加剂", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendAdmixsture sendAdmixsture)
	{
		sendAdmixstureService.insertSendAdmixsture(sendAdmixsture);
		return AjaxResult.success(sendAdmixsture);
	}

	/**
	 * 修改外加剂
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendAdmixsture sendAdmixsture = sendAdmixstureService.selectSendAdmixstureById(id);
		mmap.put("sendAdmixsture", sendAdmixsture);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存外加剂
	 */
	@Log(title = "外加剂", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendAdmixsture sendAdmixsture)
	{		
		return toAjax(sendAdmixstureService.updateSendAdmixsture(sendAdmixsture));
	}
	
	/**
	 * 删除外加剂
	 */
	@Log(title = "外加剂", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendAdmixstureService.deleteSendAdmixstureByIds(ids));
	}
	
}
