package com.ruoyi.project.stock.sendCementlevel.controller;

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
import com.ruoyi.project.stock.sendCementlevel.domain.SendCementlevel;
import com.ruoyi.project.stock.sendCementlevel.service.ISendCementlevelService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 水泥标号信息操作处理
 * 
 * @author admin
 * @date 2019-08-14
 */
@Controller
@RequestMapping("/stock/sendCementlevel")
public class SendCementlevelController extends BaseController
{
    private String prefix = "stock/sendCementlevel";
	
	@Autowired
	private ISendCementlevelService sendCementlevelService;
	
	@GetMapping()
	public String sendCementlevel()
	{
	    return prefix + "/sendCementlevel";
	}
	
	/**
	 * 查询水泥标号列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(SendCementlevel sendCementlevel)
	{
		startPage();
        List<SendCementlevel> list = sendCementlevelService.selectSendCementlevelList(sendCementlevel);
		return getDataTable(list);
	}

	/**
	 * 查询水泥标号列表
	 */
	@PostMapping("/listAll")
	@ResponseBody
	public AjaxResult listAll(SendCementlevel sendCementlevel)
	{
        List<SendCementlevel> list = sendCementlevelService.selectSendCementlevelList(sendCementlevel);
		return AjaxResult.success(list);
	}

	
	/**
	 * 导出水泥标号列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SendCementlevel sendCementlevel)
    {
    	List<SendCementlevel> list = sendCementlevelService.selectSendCementlevelList(sendCementlevel);
        ExcelUtil<SendCementlevel> util = new ExcelUtil<SendCementlevel>(SendCementlevel.class);
        return util.exportExcel(list, "sendCementlevel");
    }
	
	/**
	 * 新增水泥标号
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存水泥标号
	 */
	@Log(title = "水泥标号", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(SendCementlevel sendCementlevel)
	{
		sendCementlevelService.insertSendCementlevel(sendCementlevel);
		return AjaxResult.success(sendCementlevel);
	}

	/**
	 * 修改水泥标号
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		SendCementlevel sendCementlevel = sendCementlevelService.selectSendCementlevelById(id);
		mmap.put("sendCementlevel", sendCementlevel);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存水泥标号
	 */
	@Log(title = "水泥标号", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(SendCementlevel sendCementlevel)
	{		
		return toAjax(sendCementlevelService.updateSendCementlevel(sendCementlevel));
	}
	
	/**
	 * 删除水泥标号
	 */
	@Log(title = "水泥标号", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(sendCementlevelService.deleteSendCementlevelByIds(ids));
	}
	
}
