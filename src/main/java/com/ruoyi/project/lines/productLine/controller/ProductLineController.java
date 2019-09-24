package com.ruoyi.project.lines.productLine.controller;

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
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.lines.productLine.service.IProductLineService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 产线信息操作处理
 * 
 * @author admin
 * @date 2019-06-02
 */
@Controller
@RequestMapping("/lines/productLine")
public class ProductLineController extends BaseController
{
    private String prefix = "lines/productLine";
	
	@Autowired
	private IProductLineService productLineService;
	
	@RequiresPermissions("lines:productLine:view")
	@GetMapping()
	public String productLine()
	{
	    return prefix + "/productLine";
	}
	
	/**
	 * 查询产线列表
	 */
	@RequiresPermissions("lines:productLine:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ProductLine productLine)
	{
		startPage();
        List<ProductLine> list = productLineService.selectProductLineList(productLine);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出产线列表
	 */
	@RequiresPermissions("lines:productLine:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ProductLine productLine)
    {
    	List<ProductLine> list = productLineService.selectProductLineList(productLine);
        ExcelUtil<ProductLine> util = new ExcelUtil<ProductLine>(ProductLine.class);
        return util.exportExcel(list, "productLine");
    }
	
	/**
	 * 新增产线
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存产线
	 */
	@RequiresPermissions("lines:productLine:add")
	@Log(title = "产线", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ProductLine productLine)
	{		
		return toAjax(productLineService.insertProductLine(productLine));
	}

	/**
	 * 修改产线
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ProductLine productLine = productLineService.selectProductLineById(id);
		mmap.put("productLine", productLine);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存产线
	 */
	@RequiresPermissions("lines:productLine:edit")
	@Log(title = "产线", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ProductLine productLine)
	{		
		return toAjax(productLineService.updateProductLine(productLine));
	}
	
	/**
	 * 删除产线
	 */
	@RequiresPermissions("lines:productLine:remove")
	@Log(title = "产线", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(productLineService.deleteProductLineByIds(ids));
	}
	
}
