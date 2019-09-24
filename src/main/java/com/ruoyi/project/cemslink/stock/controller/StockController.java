package com.ruoyi.project.cemslink.stock.controller;

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
import com.ruoyi.project.cemslink.stock.domain.Stock;
import com.ruoyi.project.cemslink.stock.service.IStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 库存数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/stock")
public class StockController extends BaseController
{
    private String prefix = "cemslink/stock";
	
	@Autowired
	private IStockService stockService;
	
	@RequiresPermissions("cemslink:stock:view")
	@GetMapping()
	public String stock()
	{
	    return prefix + "/stock";
	}
	
	/**
	 * 查询库存数据列表
	 */
	@RequiresPermissions("cemslink:stock:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Stock stock)
	{
		startPage();
        List<Stock> list = stockService.selectStockList(stock);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出库存数据列表
	 */
	@RequiresPermissions("cemslink:stock:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Stock stock)
    {
    	List<Stock> list = stockService.selectStockList(stock);
        ExcelUtil<Stock> util = new ExcelUtil<Stock>(Stock.class);
        return util.exportExcel(list, "stock");
    }
	
	/**
	 * 新增库存数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存库存数据
	 */
	@RequiresPermissions("cemslink:stock:add")
	@Log(title = "库存数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Stock stock)
	{		
		return toAjax(stockService.insertStock(stock));
	}

	/**
	 * 修改库存数据
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Stock stock = stockService.selectStockById(sID);
		mmap.put("stock", stock);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存库存数据
	 */
	@RequiresPermissions("cemslink:stock:edit")
	@Log(title = "库存数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Stock stock)
	{		
		return toAjax(stockService.updateStock(stock));
	}
	
	/**
	 * 删除库存数据
	 */
	@RequiresPermissions("cemslink:stock:remove")
	@Log(title = "库存数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(stockService.deleteStockByIds(ids));
	}
	
}
