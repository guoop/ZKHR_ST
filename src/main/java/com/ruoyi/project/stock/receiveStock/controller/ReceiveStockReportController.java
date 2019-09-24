package com.ruoyi.project.stock.receiveStock.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.google.gson.JsonObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.project.cemslink.consumption.domain.Consumption;
import com.ruoyi.project.cemslink.consumption.domain.ConsumptionVO;
import com.ruoyi.project.cemslink.consumption.service.IConsumptionService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.domain.TasksCarsReport;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveGoods.service.IReceiveGoodsService;
import com.ruoyi.project.stock.receiveStock.domain.GoodsStoreReport;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStockReport;
import com.ruoyi.project.stock.receiveStock.service.IReceiveStockService;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * 进货库存信息操作处理
 * 
 * @author admin
 * @date 2019-08-09
 */
@Controller
@RequestMapping("/stock/receivereport")
public class ReceiveStockReportController extends BaseController
{

	@Autowired
	private IReceiveStockService receiveStockService;
	@Autowired
	private IReceiveGoodsService receiveGoodsService;
	@Autowired
	private ITasksCarsService tasksCarsService;
	@Autowired
	private IComstrengthService comstrengthService;
	@Autowired
	private ITasksService tasksService;
	@Autowired
	private IDictDataService dictDataService;
	@Autowired
	private IConsumptionService consumptionService;


    private String prefix = "stock/receiveStock";

    //进货
	@GetMapping()
	public String receiveStock(ModelMap mmp)
	{
		List<ReceiveGoods> goodsList = receiveGoodsService.selectReceiveGoodsList(new ReceiveGoods());
		mmp.put("goodsList",goodsList);
	    return prefix + "/receivereport";
	}


	//发货
	@GetMapping("/sendreport")
	public String sendStock(ModelMap mmp)
	{
		List<Comstrength> comList = comstrengthService.selectComstrengthList(new Comstrength());
		List<Tasks> taskList = tasksService.selectTaskNameList(new Tasks(){{setSqlWhere(" group by task_id,task_name ");}});
		mmp.put("comList",comList);
		mmp.put("taskList",taskList);
		return prefix + "/sendreport";
	}


	//库存
	@GetMapping("/stockreport")
	public String stockreport(ModelMap mmp)
	{
		List<ReceiveGoods> goodsList = receiveGoodsService.selectReceiveGoodsList(new ReceiveGoods());
		mmp.put("goodsList",goodsList);
		return prefix + "/stockreport";
	}



	/**
	 * 查询收货报表
	 */
	@RequestMapping("/report")
	@ResponseBody
	public AjaxResult report(ReceiveStock receiveStock)
	{
		if(StringUtil.isNotEmpty(receiveStock.getGoodsName())){
			List<DictData> dlist = dictDataService.selectDictDataList(new DictData(){{setDictLabel(receiveStock.getGoodsName());setDictType("mertial_type");}});
			if(null!=dlist&&dlist.size()>0){
				receiveStock.setMaterialType(dlist.get(0).getDictValue());
			}
		}
		List<ReceiveStockReport> reportList = receiveStockService.selectReceiveStockReport(receiveStock);
		return AjaxResult.success().put("reportList",reportList);
	}


	/**
	 * 查询发货报表
	 */
	@RequestMapping("/sendreportlist")
	@ResponseBody
	public AjaxResult sendreportlist(TasksCars tasksCarsReport)
	{
		tasksCarsReport.setStationStatus("OUT");
		List<TasksCarsReport> reportList = tasksCarsService.selectTaskCarsReport(tasksCarsReport);
		return AjaxResult.success().put("reportList",reportList);
	}

	/**
	 * 查询库存报表
	 */
	@RequestMapping("/stockreportlist")
	@ResponseBody
	public AjaxResult stockreportlist(ReceiveStock receiveStock)
	{
		//获取收货统计
		ConsumptionVO receiveConsumption = receiveStockService.selectConsumptionNow(new ReceiveStock());
		//获取消耗统计
		ConsumptionVO consumeConsumption = consumptionService.selectConsumptionNow(new Consumption());

		ConsumptionVO vo = new ConsumptionVO();
		ArrayList<GoodsStoreReport> reportList = new ArrayList<>();
		reportList = ConvertReceiveObjToGoodsReport(receiveConsumption,reportList);//收单
		reportList = ConvertReceiveObjToGoodsReport(consumeConsumption,reportList);//消耗
		reportList = ConvertReceiveObjToGoodsReport(receiveConsumption,reportList);//库存
//		vo.setTotalCement(receiveConsumption.getTotalCement().subtract(consumeConsumption.getTotalCement()));
//		vo.setAdditive1Dosage(receiveConsumption.getAdditive1Dosage().subtract(consumeConsumption.getAdditive1Dosage()));
//		vo.setAdditive2Dosage(receiveConsumption.getAdditive2Dosage().subtract(consumeConsumption.getAdditive2Dosage()));
//		vo.setAg1Dosage(receiveConsumption.getAg1Dosage().subtract(consumeConsumption.getAg1Dosage()));
//		vo.setAg2Dosage(receiveConsumption.getAg2Dosage().subtract(consumeConsumption.getAg2Dosage()));
//		vo.setAg3Dosage(receiveConsumption.getAg3Dosage().subtract(consumeConsumption.getAg3Dosage()));
//		vo.setAg4Dosage(receiveConsumption.getAg4Dosage().subtract(consumeConsumption.getAg4Dosage()));
//		vo.setAg5Dosage(receiveConsumption.getAg5Dosage().subtract(consumeConsumption.getAg5Dosage()));
//		vo.setMix1Dosage(receiveConsumption.getMix1Dosage().subtract(consumeConsumption.getMix1Dosage()));
//		vo.setMix2Dosage(receiveConsumption.getMix2Dosage().subtract(consumeConsumption.getMix2Dosage()));
		return AjaxResult.success().put("reportList",reportList);
	}

	public static ArrayList<GoodsStoreReport> ConvertReceiveObjToGoodsReport(ConsumptionVO obj,ArrayList<GoodsStoreReport> goodsStoreReports){
		ArrayList<String> list=new ArrayList<String>();
		if (obj == null)
			return null;
		Field[] fields = obj.getClass().getDeclaredFields();
		try {
			for(int i=0;i<fields.length;i++){
				try {
					String fieldName=fields[i].getName();
					if(fieldName.equalsIgnoreCase("serialVersionUID")){
						continue;
					}
					Field f = obj.getClass().getDeclaredField(fieldName);
					f.setAccessible(true);
					Object o = f.get(obj);
					GoodsStoreReport gr = new GoodsStoreReport();
					int index=0;
					if(goodsStoreReports.contains(new GoodsStoreReport(){{setGoodsName(f.getName());}})){
						index = goodsStoreReports.indexOf(new GoodsStoreReport(){{setGoodsName(f.getName());}});
						gr = goodsStoreReports.get(index);
					}
					//收单数据
					if(gr.getFlag()=='0'){
						//先设置收单数据
						gr.setGoodsName(f.getName());//货名
						gr.setTotalInWeight(new BigDecimal(o.toString()));
						gr.setFlag('1');
						goodsStoreReports.add(gr);
					}else if(gr.getFlag()=='1'){
						//设置消耗数据
						gr.setTotalOutWeight(new BigDecimal(o.toString()).setScale(2, RoundingMode.HALF_UP));
						gr.setFlag('2');
						goodsStoreReports.set(index,gr);
					}else if(gr.getFlag()=='2'){
						gr.setTotalBalance(gr.getTotalInWeight().subtract(gr.getTotalOutWeight()).setScale(2, RoundingMode.HALF_UP));
						gr.setFlag('9');
						goodsStoreReports.set(index,gr);
					}else{
						continue;
					}
				} catch (NoSuchFieldException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return goodsStoreReports;
	}
	/**
	 * 导出生产消耗数据列表
	 */
	@PostMapping("/export")
	@ResponseBody
	public AjaxResult export(ReceiveStock receiveStock)
	{
		List<ReceiveStockReport> list = receiveStockService.selectReceiveStockReport(receiveStock);
		ExcelUtil<ReceiveStockReport> util = new ExcelUtil<ReceiveStockReport>(ReceiveStockReport.class);
		return util.exportExcel(list, "receivereport");
	}

	/**
	 * 导出生产消耗数据列表
	 */
	@PostMapping("/sendReportExport")
	@ResponseBody
	public AjaxResult sendReportExport(TasksCars tasksCarsReport)
	{
		List<TasksCarsReport> list = tasksCarsService.selectTaskCarsReport(tasksCarsReport);
		ExcelUtil<TasksCarsReport> util = new ExcelUtil<TasksCarsReport>(TasksCarsReport.class);
		return util.exportExcel(list, "sendreport");
	}

	/**
	 * 导出库存消耗数据列表
	 */
	@PostMapping("/stockReportExport")
	@ResponseBody
	public AjaxResult stockReportExport(ReceiveStock store)
	{
//		List<GoodsStoreReport> list = receiveGoodsService.selectGoodsStoreReport(store);
		ExcelUtil<GoodsStoreReport> util = new ExcelUtil<GoodsStoreReport>(GoodsStoreReport.class);
		return util.exportExcel(null, "storereport");
	}

}
