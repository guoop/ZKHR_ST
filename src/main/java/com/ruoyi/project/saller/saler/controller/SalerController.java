package com.ruoyi.project.saller.saler.controller;

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
import com.ruoyi.project.saller.saler.domain.Saler;
import com.ruoyi.project.saller.saler.service.ISalerService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 业务员信息操作处理
 * 
 * @author admin
 * @date 2019-05-28
 */
@Controller
@RequestMapping("/saller/saler")
public class SalerController extends BaseController
{
    private String prefix = "saller/saler";
	
	@Autowired
	private ISalerService salerService;
	
	@RequiresPermissions("saller:saler:view")
	@GetMapping()
	public String saler()
	{
	    return prefix + "/saler";
	}
	
	/**
	 * 查询业务员列表
	 */
	@RequiresPermissions("saller:saler:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Saler saler)
	{
		startPage();
        List<Saler> list = salerService.selectSalerList(saler);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出业务员列表
	 */
	@RequiresPermissions("saller:saler:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Saler saler)
    {
    	List<Saler> list = salerService.selectSalerList(saler);
        ExcelUtil<Saler> util = new ExcelUtil<Saler>(Saler.class);
        return util.exportExcel(list, "saler");
    }
	
	/**
	 * 新增业务员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存业务员
	 */
	@RequiresPermissions("saller:saler:add")
	@Log(title = "业务员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Saler saler)
	{
		saler.setCreateBy(this.getSysUser().getUserName());
		saler.setDeptId(this.getSysUser().getDeptId());
		int ret = salerService.insertSaler(saler);
		return toAjax(ret);
	}

	/**
	 * 修改业务员
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Saler saler = salerService.selectSalerById(id);
		mmap.put("saler", saler);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存业务员
	 */
	@RequiresPermissions("saller:saler:edit")
	@Log(title = "业务员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Saler saler)
	{
		saler.setUpdateBy(this.getSysUser().getUserName());
		int ret = salerService.updateSaler(saler);
		return toAjax(ret);
	}
	
	/**
	 * 删除业务员
	 */
	@RequiresPermissions("saller:saler:remove")
	@Log(title = "业务员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(salerService.deleteSalerByIds(ids));
	}
	
}
