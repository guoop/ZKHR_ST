package com.ruoyi.project.stock.receiveRemark.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.xml.AbstractJaxb2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import com.ruoyi.project.stock.receiveRemark.service.IReceiveRemarkService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 收货备注信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveRemark")
public class ReceiveRemarkController extends BaseController
{
    private String prefix = "stock/receiveRemark";
	
	@Autowired
	private IReceiveRemarkService receiveRemarkService;
	
	@GetMapping()
	public String receiveRemark()
	{
	    return prefix + "/receiveRemark";
	}
	
	/**
	 * 查询收货备注列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveRemark receiveRemark)
	{
		startPage();
        List<ReceiveRemark> list = receiveRemarkService.selectReceiveRemarkList(receiveRemark);
		return getDataTable(list);
	}

	/**
	 * 查询收货备注列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveRemark receiveRemark)
	{
        List<ReceiveRemark> list = receiveRemarkService.selectReceiveRemarkList(receiveRemark);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出收货备注列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveRemark receiveRemark)
    {
    	List<ReceiveRemark> list = receiveRemarkService.selectReceiveRemarkList(receiveRemark);
        ExcelUtil<ReceiveRemark> util = new ExcelUtil<ReceiveRemark>(ReceiveRemark.class);
        return util.exportExcel(list, "receiveRemark");
    }
	
	/**
	 * 新增收货备注
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存收货备注
	 */
	@Log(title = "收货备注", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveRemark receiveRemark)
	{
		receiveRemark.setCreateBy(getSysUser().getUserName());
		receiveRemarkService.insertReceiveRemark(receiveRemark);
		return AjaxResult.success(receiveRemark);
	}

	/**
	 * 修改收货备注
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveRemark receiveRemark = receiveRemarkService.selectReceiveRemarkById(id);
		mmap.put("receiveRemark", receiveRemark);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存收货备注
	 */
	@Log(title = "收货备注", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveRemark receiveRemark)
	{		
		return toAjax(receiveRemarkService.updateReceiveRemark(receiveRemark));
	}
	
	/**
	 * 删除收货备注
	 */
	@Log(title = "收货备注", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveRemarkService.deleteReceiveRemarkByIds(ids));
	}
	
}
