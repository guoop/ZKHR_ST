package com.ruoyi.project.stock.receiveOffer.controller;

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
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import com.ruoyi.project.stock.receiveOffer.service.IReceiveOfferService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 供货单位信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveOffer")
public class ReceiveOfferController extends BaseController
{
    private String prefix = "stock/receiveOffer";
	
	@Autowired
	private IReceiveOfferService receiveOfferService;
	
	@GetMapping()
	public String receiveOffer()
	{
	    return prefix + "/receiveOffer";
	}
	
	/**
	 * 查询供货单位列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveOffer receiveOffer)
	{
		startPage();
        List<ReceiveOffer> list = receiveOfferService.selectReceiveOfferList(receiveOffer);
		return getDataTable(list);
	}

	/**
	 * 查询供货单位列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveOffer receiveOffer)
	{
        List<ReceiveOffer> list = receiveOfferService.selectReceiveOfferList(receiveOffer);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出供货单位列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveOffer receiveOffer)
    {
    	List<ReceiveOffer> list = receiveOfferService.selectReceiveOfferList(receiveOffer);
        ExcelUtil<ReceiveOffer> util = new ExcelUtil<ReceiveOffer>(ReceiveOffer.class);
        return util.exportExcel(list, "receiveOffer");
    }
	
	/**
	 * 新增供货单位
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存供货单位
	 */
	@Log(title = "供货单位", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveOffer receiveOffer)
	{
		receiveOfferService.insertReceiveOffer(receiveOffer);
		return AjaxResult.success(receiveOffer);
	}

	/**
	 * 修改供货单位
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveOffer receiveOffer = receiveOfferService.selectReceiveOfferById(id);
		mmap.put("receiveOffer", receiveOffer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存供货单位
	 */
	@Log(title = "供货单位", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveOffer receiveOffer)
	{		
		return toAjax(receiveOfferService.updateReceiveOffer(receiveOffer));
	}
	
	/**
	 * 删除供货单位
	 */
	@Log(title = "供货单位", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveOfferService.deleteReceiveOfferByIds(ids));
	}
	
}
