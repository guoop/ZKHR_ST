package com.ruoyi.project.stock.receivePeople.controller;

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
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import com.ruoyi.project.stock.receivePeople.service.IReceivePeopleService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 收货人员信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receivePeople")
public class ReceivePeopleController extends BaseController
{
    private String prefix = "stock/receivePeople";
	
	@Autowired
	private IReceivePeopleService receivePeopleService;
	
	@GetMapping()
	public String receivePeople()
	{
	    return prefix + "/receivePeople";
	}
	
	/**
	 * 查询收货人员列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceivePeople receivePeople)
	{
		startPage();
        List<ReceivePeople> list = receivePeopleService.selectReceivePeopleList(receivePeople);
		return getDataTable(list);
	}

	/**
	 * 查询收货人员列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceivePeople receivePeople)
	{
        List<ReceivePeople> list = receivePeopleService.selectReceivePeopleList(receivePeople);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出收货人员列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceivePeople receivePeople)
    {
    	List<ReceivePeople> list = receivePeopleService.selectReceivePeopleList(receivePeople);
        ExcelUtil<ReceivePeople> util = new ExcelUtil<ReceivePeople>(ReceivePeople.class);
        return util.exportExcel(list, "receivePeople");
    }
	
	/**
	 * 新增收货人员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存收货人员
	 */
	@Log(title = "收货人员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceivePeople receivePeople)
	{
		receivePeople.setCreateBy(getSysUser().getUserName());
		receivePeopleService.insertReceivePeople(receivePeople);
		return AjaxResult.success(receivePeople);
	}

	/**
	 * 修改收货人员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceivePeople receivePeople = receivePeopleService.selectReceivePeopleById(id);
		mmap.put("receivePeople", receivePeople);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存收货人员
	 */
	@Log(title = "收货人员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceivePeople receivePeople)
	{		
		return toAjax(receivePeopleService.updateReceivePeople(receivePeople));
	}
	
	/**
	 * 删除收货人员
	 */
	@Log(title = "收货人员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receivePeopleService.deleteReceivePeopleByIds(ids));
	}
	
}
