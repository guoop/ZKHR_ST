package com.ruoyi.project.stock.sendKslevel.controller;

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
import com.ruoyi.project.stock.sendKslevel.domain.SendKslevel;
import com.ruoyi.project.stock.sendKslevel.service.ISendKslevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 抗渗等级信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendKslevel")
public class SendKslevelController extends BaseController
{
    private String prefix = "stock/sendKslevel";
	
	@Autowired
	private ISendKslevelService sendKslevelService;
	
	@GetMapping()
	public String sendKslevel()
	{
	    return prefix + "/sendKslevel";
	}
	
	/**
	 * 查询抗渗等级列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendKslevel sendKslevel)
	{
		startPage();
        List<SendKslevel> list = sendKslevelService.selectSendKslevelList(sendKslevel);
		return getDataTable(list);
	}

	/**
	 * 查询抗渗等级列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendKslevel sendKslevel)
	{
        List<SendKslevel> list = sendKslevelService.selectSendKslevelList(sendKslevel);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出抗渗等级列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendKslevel sendKslevel)
    {
    	List<SendKslevel> list = sendKslevelService.selectSendKslevelList(sendKslevel);
        ExcelUtil<SendKslevel> util = new ExcelUtil<SendKslevel>(SendKslevel.class);
        return util.exportExcel(list, "sendKslevel");
    }
	
	/**
	 * 新增抗渗等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存抗渗等级
	 */
	@Log(title = "抗渗等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendKslevel sendKslevel)
	{
		sendKslevelService.insertSendKslevel(sendKslevel);
		return AjaxResult.success(sendKslevel);
	}

	/**
	 * 修改抗渗等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendKslevel sendKslevel = sendKslevelService.selectSendKslevelById(id);
		mmap.put("sendKslevel", sendKslevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存抗渗等级
	 */
	@Log(title = "抗渗等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendKslevel sendKslevel)
	{		
		return toAjax(sendKslevelService.updateSendKslevel(sendKslevel));
	}
	
	/**
	 * 删除抗渗等级
	 */
	@Log(title = "抗渗等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendKslevelService.deleteSendKslevelByIds(ids));
	}
	
}
