package com.ruoyi.project.cars.comstrength.controller;

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
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 强度等级信息操作处理
 * 
 * @author admin
 * @date 2019-08-01
 */
@Controller
@RequestMapping("/cars/comstrength")
public class ComstrengthController extends BaseController
{
    private String prefix = "cars/comstrength";
	
	@Autowired
	private IComstrengthService comstrengthService;
	
	@RequiresPermissions("cars:comstrength:view")
	@GetMapping()
	public String comstrength()
	{
	    return prefix + "/comstrength";
	}
	
	/**
	 * 查询强度等级列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Comstrength comstrength)
	{
		startPage();
        List<Comstrength> list = comstrengthService.selectComstrengthList(comstrength);
		return getDataTable(list);
	}

	/**
	 * 查询强度等级列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(Comstrength comstrength)
	{
        List<Comstrength> list = comstrengthService.selectComstrengthList(comstrength);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出强度等级列表
	 */
	@RequiresPermissions("cars:comstrength:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Comstrength comstrength)
    {
    	List<Comstrength> list = comstrengthService.selectComstrengthList(comstrength);
        ExcelUtil<Comstrength> util = new ExcelUtil<Comstrength>(Comstrength.class);
        return util.exportExcel(list, "comstrength");
    }
	
	/**
	 * 新增强度等级
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存强度等级
	 */
	@RequiresPermissions("cars:comstrength:add")
	@Log(title = "强度等级", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Comstrength comstrength)
	{
		comstrength.setCreateBy(getSysUser().getUserName());
		return toAjax(comstrengthService.insertComstrength(comstrength));
	}

	/**
	 * 修改强度等级
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Comstrength comstrength = comstrengthService.selectComstrengthById(id);
		mmap.put("comstrength", comstrength);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存强度等级
	 */
	@RequiresPermissions("cars:comstrength:edit")
	@Log(title = "强度等级", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Comstrength comstrength)
	{		
		return toAjax(comstrengthService.updateComstrength(comstrength));
	}
	
	/**
	 * 删除强度等级
	 */
	@RequiresPermissions("cars:comstrength:remove")
	@Log(title = "强度等级", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(comstrengthService.deleteComstrengthByIds(ids));
	}
	
}
