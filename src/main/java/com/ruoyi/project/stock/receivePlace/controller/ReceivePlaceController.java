package com.ruoyi.project.stock.receivePlace.controller;

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
import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import com.ruoyi.project.stock.receivePlace.service.IReceivePlaceService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产地信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receivePlace")
public class ReceivePlaceController extends BaseController
{
    private String prefix = "stock/receivePlace";
	
	@Autowired
	private IReceivePlaceService receivePlaceService;
	
	@GetMapping()
	public String receivePlace()
	{
	    return prefix + "/receivePlace";
	}
	
	/**
	 * 查询产地列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceivePlace receivePlace)
	{
		startPage();
        List<ReceivePlace> list = receivePlaceService.selectReceivePlaceList(receivePlace);
		return getDataTable(list);
	}
	

	/**
	 * 查询产地列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceivePlace receivePlace)
	{
        List<ReceivePlace> list = receivePlaceService.selectReceivePlaceList(receivePlace);
		return AjaxResult.success(list);
	}


	/**
	 * 导出产地列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceivePlace receivePlace)
    {
    	List<ReceivePlace> list = receivePlaceService.selectReceivePlaceList(receivePlace);
        ExcelUtil<ReceivePlace> util = new ExcelUtil<ReceivePlace>(ReceivePlace.class);
        return util.exportExcel(list, "receivePlace");
    }
	
	/**
	 * 新增产地
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产地
	 */
	@Log(title = "产地", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceivePlace receivePlace)
	{
		receivePlace.setCreateBy(getSysUser().getUserName());
		receivePlaceService.insertReceivePlace(receivePlace);
		return AjaxResult.success(receivePlace);
	}

	/**
	 * 修改产地
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceivePlace receivePlace = receivePlaceService.selectReceivePlaceById(id);
		mmap.put("receivePlace", receivePlace);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产地
	 */
	@Log(title = "产地", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceivePlace receivePlace)
	{		
		return toAjax(receivePlaceService.updateReceivePlace(receivePlace));
	}
	
	/**
	 * 删除产地
	 */
	@Log(title = "产地", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receivePlaceService.deleteReceivePlaceByIds(ids));
	}
	
}
