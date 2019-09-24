package com.ruoyi.project.cems.queue.controller;

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
import com.ruoyi.project.cems.queue.domain.Queue;
import com.ruoyi.project.cems.queue.service.IQueueService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 排队信息操作处理
 * 
 * @author admin
 * @date 2019-05-13
 */
@Controller
@RequestMapping("/cems/queue")
public class QueueController extends BaseController
{
    private String prefix = "cems/queue";
	
	@Autowired
	private IQueueService queueService;
	
	@RequiresPermissions("cems:queue:view")
	@GetMapping()
	public String queue()
	{
	    return prefix + "/queue";
	}
	
	/**
	 * 查询排队列表
	 */
	@RequiresPermissions("cems:queue:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Queue queue)
	{
		startPage();
        List<Queue> list = queueService.selectQueueList(queue);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出排队列表
	 */
	@RequiresPermissions("cems:queue:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Queue queue)
    {
    	List<Queue> list = queueService.selectQueueList(queue);
        ExcelUtil<Queue> util = new ExcelUtil<Queue>(Queue.class);
        return util.exportExcel(list, "queue");
    }
	
	/**
	 * 新增排队
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存排队
	 */
	@RequiresPermissions("cems:queue:add")
	@Log(title = "排队", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Queue queue)
	{		
		return toAjax(queueService.insertQueue(queue));
	}

	/**
	 * 修改排队
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		Queue queue = queueService.selectQueueById(id);
		mmap.put("queue", queue);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存排队
	 */
	@RequiresPermissions("cems:queue:edit")
	@Log(title = "排队", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Queue queue)
	{		
		return toAjax(queueService.updateQueue(queue));
	}
	
	/**
	 * 删除排队
	 */
	@RequiresPermissions("cems:queue:remove")
	@Log(title = "排队", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(queueService.deleteQueueByIds(ids));
	}
	
}
