package com.ruoyi.project.stock.receiveCar.controller;

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
import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveCar.service.IReceiveCarService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 送货车号信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveCar")
public class ReceiveCarController extends BaseController
{
    private String prefix = "stock/receiveCar";
	
	@Autowired
	private IReceiveCarService receiveCarService;
	
	@GetMapping()
	public String receiveCar()
	{
	    return prefix + "/receiveCar";
	}
	
	/**
	 * 查询送货车号列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveCar receiveCar)
	{
		startPage();
        List<ReceiveCar> list = receiveCarService.selectReceiveCarList(receiveCar);
		return getDataTable(list);
	}

	/**
	 * 查询送货车号列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveCar receiveCar)
	{
		List<ReceiveCar> list = receiveCarService.selectReceiveCarList(receiveCar);
		return AjaxResult.success(list);
	}


	/**
	 * 导出送货车号列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveCar receiveCar)
    {
    	List<ReceiveCar> list = receiveCarService.selectReceiveCarList(receiveCar);
        ExcelUtil<ReceiveCar> util = new ExcelUtil<ReceiveCar>(ReceiveCar.class);
        return util.exportExcel(list, "receiveCar");
    }
	
	/**
	 * 新增送货车号
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存送货车号
	 */
	@Log(title = "送货车号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveCar receiveCar)
	{
		receiveCar.setCreateBy(getSysUser().getUserName());
		receiveCarService.insertReceiveCar(receiveCar);
		return AjaxResult.success(receiveCar);
	}

	/**
	 * 修改送货车号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveCar receiveCar = receiveCarService.selectReceiveCarById(id);
		mmap.put("receiveCar", receiveCar);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存送货车号
	 */
	@Log(title = "送货车号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveCar receiveCar)
	{
		receiveCarService.updateReceiveCar(receiveCar);
		return AjaxResult.success(receiveCar);
	}
	
	/**
	 * 删除送货车号
	 */
	@Log(title = "送货车号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveCarService.deleteReceiveCarByIds(ids));
	}
	
}
