package com.ruoyi.project.stock.receiveGoods.controller;

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
import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveGoods.service.IReceiveGoodsService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 货品信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveGoods")
public class ReceiveGoodsController extends BaseController
{
    private String prefix = "stock/receiveGoods";
	
	@Autowired
	private IReceiveGoodsService receiveGoodsService;
	
	@GetMapping()
	public String receiveGoods()
	{
	    return prefix + "/receiveGoods";
	}
	
	/**
	 * 查询货品列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveGoods receiveGoods)
	{
		startPage();
        List<ReceiveGoods> list = receiveGoodsService.selectReceiveGoodsList(receiveGoods);
		return getDataTable(list);
	}

	/**
	 * 查询货品列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveGoods receiveGoods)
	{
        List<ReceiveGoods> list = receiveGoodsService.selectReceiveGoodsList(receiveGoods);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出货品列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveGoods receiveGoods)
    {
    	List<ReceiveGoods> list = receiveGoodsService.selectReceiveGoodsList(receiveGoods);
        ExcelUtil<ReceiveGoods> util = new ExcelUtil<ReceiveGoods>(ReceiveGoods.class);
        return util.exportExcel(list, "receiveGoods");
    }
	
	/**
	 * 新增货品
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存货品
	 */
	@Log(title = "货品", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveGoods receiveGoods)
	{
		receiveGoodsService.insertReceiveGoods(receiveGoods);
		return AjaxResult.success(receiveGoods);
	}

	/**
	 * 修改货品
	 */
	@GetMapping("/edit/{goodsid}")
	public String edit(@PathVariable("goodsid") Long goodsid, ModelMap mmap)
	{
		ReceiveGoods receiveGoods = receiveGoodsService.selectReceiveGoodsById(goodsid);
		mmap.put("receiveGoods", receiveGoods);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存货品
	 */
	@Log(title = "货品", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveGoods receiveGoods)
	{		
		return toAjax(receiveGoodsService.updateReceiveGoods(receiveGoods));
	}
	
	/**
	 * 删除货品
	 */
	@Log(title = "货品", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveGoodsService.deleteReceiveGoodsByIds(ids));
	}
	
}
