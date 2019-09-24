package com.ruoyi.project.stock.receiveOffice.controller;

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
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import com.ruoyi.project.stock.receiveOffice.service.IReceiveOfficeService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 收货单位信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveOffice")
public class ReceiveOfficeController extends BaseController
{
    private String prefix = "stock/receiveOffice";
	
	@Autowired
	private IReceiveOfficeService receiveOfficeService;
	
	@GetMapping()
	public String receiveOffice()
	{
	    return prefix + "/receiveOffice";
	}
	
	/**
	 * 查询收货单位列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveOffice receiveOffice)
	{
		startPage();
        List<ReceiveOffice> list = receiveOfficeService.selectReceiveOfficeList(receiveOffice);
		return getDataTable(list);
	}

	/**
	 * 查询收货单位列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveOffice receiveOffice)
	{
        List<ReceiveOffice> list = receiveOfficeService.selectReceiveOfficeList(receiveOffice);
		return AjaxResult.success(list);
	}
	
	
	/**
	 * 导出收货单位列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveOffice receiveOffice)
    {
    	List<ReceiveOffice> list = receiveOfficeService.selectReceiveOfficeList(receiveOffice);
        ExcelUtil<ReceiveOffice> util = new ExcelUtil<ReceiveOffice>(ReceiveOffice.class);
        return util.exportExcel(list, "receiveOffice");
    }
	
	/**
	 * 新增收货单位
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存收货单位
	 */
	@Log(title = "收货单位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveOffice receiveOffice)
	{
		receiveOfficeService.insertReceiveOffice(receiveOffice);
		return AjaxResult.success(receiveOffice);
	}

	/**
	 * 修改收货单位
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveOffice receiveOffice = receiveOfficeService.selectReceiveOfficeById(id);
		mmap.put("receiveOffice", receiveOffice);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存收货单位
	 */
	@Log(title = "收货单位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveOffice receiveOffice)
	{		
		return toAjax(receiveOfficeService.updateReceiveOffice(receiveOffice));
	}
	
	/**
	 * 删除收货单位
	 */
	@Log(title = "收货单位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveOfficeService.deleteReceiveOfficeByIds(ids));
	}
	
}
