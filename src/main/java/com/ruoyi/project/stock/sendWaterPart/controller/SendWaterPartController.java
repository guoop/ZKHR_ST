package com.ruoyi.project.stock.sendWaterPart.controller;

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
import com.ruoyi.project.stock.sendWaterPart.domain.SendWaterPart;
import com.ruoyi.project.stock.sendWaterPart.service.ISendWaterPartService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 工程部位信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendWaterPart")
public class SendWaterPartController extends BaseController
{
    private String prefix = "stock/sendWaterPart";
	
	@Autowired
	private ISendWaterPartService sendWaterPartService;
	
	@GetMapping()
	public String sendWaterPart()
	{
	    return prefix + "/sendWaterPart";
	}
	
	/**
	 * 查询工程部位列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendWaterPart sendWaterPart)
	{
		startPage();
        List<SendWaterPart> list = sendWaterPartService.selectSendWaterPartList(sendWaterPart);
		return getDataTable(list);
	}

	/**
	 * 查询工程部位列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendWaterPart sendWaterPart)
	{
		startPage();
        List<SendWaterPart> list = sendWaterPartService.selectSendWaterPartList(sendWaterPart);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出工程部位列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendWaterPart sendWaterPart)
    {
    	List<SendWaterPart> list = sendWaterPartService.selectSendWaterPartList(sendWaterPart);
        ExcelUtil<SendWaterPart> util = new ExcelUtil<SendWaterPart>(SendWaterPart.class);
        return util.exportExcel(list, "sendWaterPart");
    }
	
	/**
	 * 新增工程部位
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存工程部位
	 */
	@Log(title = "工程部位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendWaterPart sendWaterPart)
	{
		sendWaterPartService.insertSendWaterPart(sendWaterPart);
		return AjaxResult.success(sendWaterPart);
	}

	/**
	 * 修改工程部位
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendWaterPart sendWaterPart = sendWaterPartService.selectSendWaterPartById(id);
		mmap.put("sendWaterPart", sendWaterPart);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存工程部位
	 */
	@Log(title = "工程部位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendWaterPart sendWaterPart)
	{		
		return toAjax(sendWaterPartService.updateSendWaterPart(sendWaterPart));
	}
	
	/**
	 * 删除工程部位
	 */
	@Log(title = "工程部位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendWaterPartService.deleteSendWaterPartByIds(ids));
	}
	
}
