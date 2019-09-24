package com.ruoyi.project.stock.receiveGoodsLevel.controller;

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
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import com.ruoyi.project.stock.receiveGoodsLevel.service.IReceiveGoodsLevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 货品等级信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveGoodsLevel")
public class ReceiveGoodsLevelController extends BaseController
{
    private String prefix = "stock/receiveGoodsLevel";
	
	@Autowired
	private IReceiveGoodsLevelService receiveGoodsLevelService;
	
	@GetMapping()
	public String receiveGoodsLevel()
	{
	    return prefix + "/receiveGoodsLevel";
	}
	
	/**
	 * 查询货品等级列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveGoodsLevel receiveGoodsLevel)
	{
		startPage();
        List<ReceiveGoodsLevel> list = receiveGoodsLevelService.selectReceiveGoodsLevelList(receiveGoodsLevel);
		return getDataTable(list);
	}
	

	/**
	 * 查询货品等级列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(ReceiveGoodsLevel receiveGoodsLevel)
	{
        List<ReceiveGoodsLevel> list = receiveGoodsLevelService.selectReceiveGoodsLevelList(receiveGoodsLevel);
		return AjaxResult.success(list);
	}


	/**
	 * 导出货品等级列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveGoodsLevel receiveGoodsLevel)
    {
    	List<ReceiveGoodsLevel> list = receiveGoodsLevelService.selectReceiveGoodsLevelList(receiveGoodsLevel);
        ExcelUtil<ReceiveGoodsLevel> util = new ExcelUtil<ReceiveGoodsLevel>(ReceiveGoodsLevel.class);
        return util.exportExcel(list, "receiveGoodsLevel");
    }
	
	/**
	 * 新增货品等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存货品等级
	 */
	@Log(title = "货品等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveGoodsLevel receiveGoodsLevel)
	{
		receiveGoodsLevel.setCreateBy(getSysUser().getUserName());
		receiveGoodsLevelService.insertReceiveGoodsLevel(receiveGoodsLevel);
		return AjaxResult.success(receiveGoodsLevel);
	}

	/**
	 * 修改货品等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveGoodsLevel receiveGoodsLevel = receiveGoodsLevelService.selectReceiveGoodsLevelById(id);
		mmap.put("receiveGoodsLevel", receiveGoodsLevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存货品等级
	 */
	@Log(title = "货品等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveGoodsLevel receiveGoodsLevel)
	{		
		return toAjax(receiveGoodsLevelService.updateReceiveGoodsLevel(receiveGoodsLevel));
	}
	
	/**
	 * 删除货品等级
	 */
	@Log(title = "货品等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveGoodsLevelService.deleteReceiveGoodsLevelByIds(ids));
	}
	
}
