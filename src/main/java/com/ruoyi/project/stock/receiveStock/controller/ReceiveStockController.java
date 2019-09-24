package com.ruoyi.project.stock.receiveStock.controller;

import java.util.List;

import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveCar.service.IReceiveCarService;
import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveGoods.service.IReceiveGoodsService;
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import com.ruoyi.project.stock.receiveGoodsLevel.service.IReceiveGoodsLevelService;
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import com.ruoyi.project.stock.receiveOffer.service.IReceiveOfferService;
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import com.ruoyi.project.stock.receiveOffice.service.IReceiveOfficeService;
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import com.ruoyi.project.stock.receivePeople.service.IReceivePeopleService;
import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import com.ruoyi.project.stock.receivePlace.service.IReceivePlaceService;
import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import com.ruoyi.project.stock.receiveRemark.service.IReceiveRemarkService;
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
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.service.IReceiveStockService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 进货库存信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receiveStock")
public class ReceiveStockController extends BaseController
{
    private String prefix = "stock/receiveStock";
	
	@Autowired
	private IReceiveStockService receiveStockService;
	@Autowired
	private IReceiveCarService receiveCarService;
	@Autowired
	private IReceiveGoodsLevelService receiveGoodsLevelService;
	@Autowired
	private IReceiveOfferService receiveOfferService;
	@Autowired
	private IReceiveOfficeService receiveOfficeService;
	@Autowired
	private IReceivePeopleService receivePeopleService;
	@Autowired
	private IReceivePlaceService receivePlaceService;
	@Autowired
	private IReceiveRemarkService receiveRemarkService;
	@Autowired
	private IReceiveGoodsService receiveGoodsService;
	
	@GetMapping()
	public String receiveStock(ModelMap mmp)
	{
		List<ReceiveCar> carList = receiveCarService.selectReceiveCarList(new ReceiveCar());
		List<ReceiveGoodsLevel> goodsLevelList = receiveGoodsLevelService.selectReceiveGoodsLevelList(new ReceiveGoodsLevel());
		List<ReceiveGoods> goodsList = receiveGoodsService.selectReceiveGoodsList(new ReceiveGoods());
		List<ReceiveOffice> officeList = receiveOfficeService.selectReceiveOfficeList(new ReceiveOffice());
		List<ReceivePeople> peoplelist = receivePeopleService.selectReceivePeopleList(new ReceivePeople());
		List<ReceivePlace> placesList = receivePlaceService.selectReceivePlaceList(new ReceivePlace());
		List<ReceiveRemark> remarksList = receiveRemarkService.selectReceiveRemarkList(new ReceiveRemark());
		List<ReceiveOffer> offerList = receiveOfferService.selectReceiveOfferList(new ReceiveOffer());
		mmp.put("carList",carList);
		mmp.put("goodsLevelList",goodsLevelList);
		mmp.put("goodsList",goodsList);
		mmp.put("officeList",officeList);
		mmp.put("peoplelist",peoplelist);
		mmp.put("placesList",placesList);
		mmp.put("remarksList",remarksList);
		mmp.put("offerList",offerList);
	    return prefix + "/receiveStock";
	}
	
	/**
	 * 查询进货库存列表
	 */
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(ReceiveStock receiveStock)
	{
		startPage();
        List<ReceiveStock> list = receiveStockService.selectReceiveStockList(receiveStock);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出进货库存列表
	 */
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ReceiveStock receiveStock)
    {
    	List<ReceiveStock> list = receiveStockService.selectReceiveStockList(receiveStock);
        ExcelUtil<ReceiveStock> util = new ExcelUtil<ReceiveStock>(ReceiveStock.class);
        return util.exportExcel(list, "receiveStock");
    }
	
	/**
	 * 新增进货库存
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存进货库存
	 */
	@Log(title = "进货库存", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(ReceiveStock receiveStock)
	{
		receiveStockService.insertReceiveStock(receiveStock);
		return AjaxResult.success(receiveStock);
	}

	/**
	 * 修改进货库存
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		ReceiveStock receiveStock = receiveStockService.selectReceiveStockById(id);
		mmap.put("receiveStock", receiveStock);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存进货库存
	 */
	@Log(title = "进货库存", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(ReceiveStock receiveStock)
	{		
		return toAjax(receiveStockService.updateReceiveStock(receiveStock));
	}
	
	/**
	 * 删除进货库存
	 */
	@Log(title = "进货库存", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(receiveStockService.deleteReceiveStockByIds(ids));
	}
	
}
