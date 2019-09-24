package com.ruoyi.project.cemslink.mixformula.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.cemslink.mixformula.domain.Mixformula;
import com.ruoyi.project.cemslink.mixformula.service.IMixformulaService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 配合比数据信息操作处理
 * 
 * @author admin
 * @date 2019-05-31
 */
@Controller
@RequestMapping("/cemslink/mixformula")
public class MixformulaController extends BaseController
{
    private String prefix = "cemslink/mixformula";
	
	@Autowired
	private IMixformulaService mixformulaService;
	
	@RequiresPermissions("cemslink:mixformula:view")
	@GetMapping()
	public String mixformula()
	{
	    return prefix + "/mixformula";
	}
	
	/**
	 * 查询配合比数据列表
	 */
	@RequiresPermissions("cemslink:mixformula:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Mixformula mixformula)
	{
//		startPage();
        List<Mixformula> list = mixformulaService.selectMixformulaList(mixformula);
		return getDataTable(list);
	}

	/**
	 * 查询配合比数据列表
	 */
	@RequiresPermissions("cemslink:mixformula:list")
	@PostMapping("/selectall")
	@ResponseBody
	public AjaxResult selectall(Mixformula mixformula)
	{
        List<Mixformula> list = mixformulaService.selectMixformulaList(null);
		return AjaxResult.success().put("mixList",list);
	}
	
	
	/**
	 * 导出配合比数据列表
	 */
	@RequiresPermissions("cemslink:mixformula:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Mixformula mixformula)
    {
    	List<Mixformula> list = mixformulaService.selectMixformulaList(mixformula);
        ExcelUtil<Mixformula> util = new ExcelUtil<Mixformula>(Mixformula.class);
        return util.exportExcel(list, "mixformula");
    }
	
	/**
	 * 新增配合比数据
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存配合比数据
	 */
	@RequiresPermissions("cemslink:mixformula:add")
	@Log(title = "配合比数据", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	@Transactional(transactionManager = "slaveTransactionManager")
	public AjaxResult addSave(Mixformula mixformula)
	{
		int ret = 0;
		mixformula.setIsMortar(0);
		mixformula.setProductLine(1);
		ret = mixformulaService.insertMixformula(mixformula);
		mixformula.setSID(null);
		mixformula.setProductLine(2);
		ret = mixformulaService.insertMixformula(mixformula);
		return toAjax(1);
	}

	/**
	 * 修改配合比数据
	 */
	@GetMapping("/edit/{sID}")
	public String edit(@PathVariable("sID") Long sID, ModelMap mmap)
	{
		Mixformula mixformula = mixformulaService.selectMixformulaById(sID);
		mmap.put("mixformula", mixformula);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存配合比数据
	 */
	@RequiresPermissions("cemslink:mixformula:edit")
	@Log(title = "配合比数据", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Mixformula mixformula)
	{
		mixformula.setSyncStatus(0);
		mixformula.setSyncStatus2(0);
		return toAjax(mixformulaService.updateMixformula(mixformula));
	}
	
	/**
	 * 删除配合比数据
	 */
	@RequiresPermissions("cemslink:mixformula:remove")
	@Log(title = "配合比数据", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(mixformulaService.deleteMixformulaByIds(ids));
	}
	
}
