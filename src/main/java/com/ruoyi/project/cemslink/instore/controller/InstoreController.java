package com.ruoyi.project.cemslink.instore.controller;

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
import com.ruoyi.project.cemslink.instore.domain.Instore;
import com.ruoyi.project.cemslink.instore.service.IInstoreService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 库存信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/instore")
public class InstoreController extends BaseController
{
    private String prefix = "cemslink/instore";
	
	@Autowired
	private IInstoreService instoreService;
	
	@RequiresPermissions("cemslink:instore:view")
	@GetMapping()
	public String instore()
	{
	    return prefix + "/instore";
	}
	
	/**
	 * 查询库存列表
	 */
	@RequiresPermissions("cemslink:instore:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Instore instore)
	{
		startPage();
        List<Instore> list = instoreService.selectInstoreList(instore);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出库存列表
	 */
	@RequiresPermissions("cemslink:instore:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Instore instore)
    {
    	List<Instore> list = instoreService.selectInstoreList(instore);
        ExcelUtil<Instore> util = new ExcelUtil<Instore>(Instore.class);
        return util.exportExcel(list, "instore");
    }
	
	/**
	 * 新增库存
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存库存
	 */
	@RequiresPermissions("cemslink:instore:add")
	@Log(title = "库存", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Instore instore)
	{		
		return toAjax(instoreService.insertInstore(instore));
	}

	/**
	 * 修改库存
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Instore instore = instoreService.selectInstoreById(sID);
		mmap.put("instore", instore);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存库存
	 */
	@RequiresPermissions("cemslink:instore:edit")
	@Log(title = "库存", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Instore instore)
	{		
		return toAjax(instoreService.updateInstore(instore));
	}
	
	/**
	 * 删除库存
	 */
	@RequiresPermissions("cemslink:instore:remove")
	@Log(title = "库存", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(instoreService.deleteInstoreByIds(ids));
	}
	
}
