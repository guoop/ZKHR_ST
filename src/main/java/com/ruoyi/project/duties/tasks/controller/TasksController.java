package com.ruoyi.project.duties.tasks.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.github.pagehelper.util.StringUtil;
import com.google.code.kaptcha.Producer;
import com.google.common.collect.Lists;
import com.ruoyi.api.MySeq;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.project.cemslink.mixformula.domain.Mixformula;
import com.ruoyi.project.cemslink.mixformula.service.IMixformulaService;
import com.ruoyi.project.cemslink.preproduction.domain.PreproductionQueue;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.cemslink.task.service.ITaskService;
import com.ruoyi.project.duties.tasks.domain.TasksData;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import com.ruoyi.project.locator.common.GPSLocation;
import com.ruoyi.project.locator.common.GeoUtil;
import com.ruoyi.project.locator.common.LocatorConstant;
import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import com.ruoyi.project.profile.profileroom.service.IProfileroomService;
import com.ruoyi.project.redisEntry.CarsLonLat;
import com.ruoyi.project.redisEntry.RTaskVo;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import io.swagger.models.auth.In;
import org.apache.commons.collections.map.HashedMap;
import org.apache.poi.hssf.record.chart.CatLabRecord;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;

/**
 * 任务单信息操作处理
 * 
 * @author admin
 * @date 2019-04-25
 */
@Controller
@Configuration
@RequestMapping("/duties/tasks")
public class TasksController extends BaseController
{
    private String prefix = "duties/tasks";
	
	@Autowired
	private ITasksService tasksService;
	@Autowired
	private ITaskService tService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private IDictDataService dictDataService;
	@Autowired
	private IProfileroomService profileroomService;
	@Autowired
	private IMixformulaService mixformulaService;
	@Autowired
	private ICarService carService;
	@Autowired
	private ITasksMixformulaService tasksMixformulaService;
	@Autowired
	private IComstrengthService comstrengthService;

	@Autowired
	private RestTemplate restTemplate;

	ThreadPoolExecutor executor = new ThreadPoolExecutor(2,5,20, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(200));


	@Value("${project.endLng}")
	private String endLng;
	@Value("${project.endLat}")
	private String endLat;

	private Logger logger = LoggerFactory.getLogger(TasksController.class);
	
	@RequiresPermissions("duties:tasks:view")
	@GetMapping()
	public String tasks()
	{
	    return prefix + "/tasks";
	}

	@RequiresPermissions("duties:tasks:view")
	@GetMapping("/taskdemo")
	public String taskdemo()
	{
	    return prefix + "/taskdemo";
	}

	/**
	 * 查询任务单列表
	 */
	@RequiresPermissions("duties:tasks:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Tasks tasks)
	{
		startPage();
        List<Tasks> list = tasksService.selectTasksList(tasks);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出任务单列表
	 */
	@RequiresPermissions("duties:tasks:export")
	@PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Tasks tasks)
    {
    	List<Tasks> list = tasksService.selectTasksList(tasks);
        ExcelUtil<Tasks> util = new ExcelUtil<Tasks>(Tasks.class);
        return util.exportExcel(list, "tasks");
    }
	
	/**
	 * 新增任务单
	 */
	@GetMapping("/add")
	public String add(ModelMap mmap)
	{
		List<Car> carList = carService.selectCarList(null);
		List<Comstrength> comList = comstrengthService.selectComstrengthList(new Comstrength());
		mmap.put("carList",carList);
		mmap.put("comList",comList);
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存任务单
	 */
	@RequiresPermissions("duties:tasks:add")
	@Log(title = "任务单", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Tasks tasks)
	{
		tasks.setCreateBy(this.getSysUser().getUserName());
		tasks.setOfficerMobile(this.getSysUser().getPhonenumber());
		tasks.setPlanOrderNo(MySeq.getTask());//获取任务单号
		tasks.setLon(endLng);
		tasks.setLat(endLat);
		//派发间隔
		if(null==tasks.getSubTime()||tasks.getSubTime()==0){
			return AjaxResult.error(String.format("任务单[%s]的\"派车间隔\"必填!",tasks.getName()));
		}
		/*if(StringUtils.isNotEmpty(tasks.getIsGoodJob())&&tasks.getIsGoodJob().equalsIgnoreCase(Constants.NO)&&(null==tasks.getPrivilege()||tasks.getPrivilege()==0)){
			return AjaxResult.error(String.format("任务单[%s]的\"优先权\"必填!",tasks.getName()));
		}else if(StringUtils.isNotEmpty(tasks.getIsGoodJob())&&tasks.getIsGoodJob().equalsIgnoreCase(Constants.YES)&&(null!=tasks.getPrivilege()&&tasks.getPrivilege()>0)){
			return AjaxResult.error(String.format("任务单[%s]是好活;\"优先权\"不能大于0!",tasks.getName()));
		}
		if(null==tasks.getLat()||null==tasks.getLon()){
			return AjaxResult.error(String.format("请完善任务单[%s]的坐标信息;",tasks.getName()));
		}*/
		if(StringUtils.isNotEmpty(tasks.getIsOtherArea())&&tasks.getIsOtherArea().equalsIgnoreCase(Constants.NO)){
			if(null==tasks.getMixCar()||tasks.getMixCar()==0||null==tasks.getMaxCar()||tasks.getMaxCar()==0){
				return AjaxResult.error(String.format("非跨县任务单[%s]需要选择填写【车辆方量区间】",tasks.getName()));
			}
		}

		if(StringUtils.isNotEmpty(tasks.getIsPinch())&&tasks.getIsPinch().equalsIgnoreCase(Constants.YES)){
			if(null==tasks.getPinchFl()||tasks.getPinchFl().compareTo(new BigDecimal(0))<=0){
				return AjaxResult.error(String.format("任务单[%s]需要选择填写【掐方方量】",tasks.getName()));
			}
		}
		if(tasks.getPlanCarCnt()==null||tasks.getPlanCarCnt()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计用车数】",tasks.getName()));
		}

		if(tasks.getPlanEndTime()==null){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计结束时间】",tasks.getName()));
		}

		if(null==tasks.getHeight()||tasks.getHeight().intValue()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【限高】",tasks.getName()));
		}
		tasks.setIsSchedule(Constants.YES);
		return toAjax(tasksService.insertTasks(tasks));
	}

	/**
	 * 修改任务单
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") String id, ModelMap mmap)
	{
		Tasks tasks = tasksService.selectTasksById(id);
		tasks.setUpdateBy(this.getSysUser().getUserName());
//		mmap.put("cartypeList",tasksService.selectCartypeList(id));
		mmap.put("tasks", tasks);
		List<Car> carList = carService.selectCarListOfTask(tasks);
		mmap.put("carList",carList);
		List<Comstrength> comList = comstrengthService.selectComstrengthList(new Comstrength());
		mmap.put("comList",comList);
	    return prefix + "/edit";
	}



	/**
	 * 修改保存任务单
	 */
	@RequiresPermissions("duties:tasks:edit")
	@Log(title = "任务单", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Tasks tasks)
	{
		//派发间隔
		if(null==tasks.getSubTime()||tasks.getSubTime()==0){
			return AjaxResult.error(String.format("任务单[%s]的\"派车间隔\"必填!",tasks.getName()));
		}
		/*if(null==tasks.getLat()||null==tasks.getLon()){
			return AjaxResult.error(String.format("请完善任务单[%s]的坐标信息;",tasks.getName()));
		}*/
		if(StringUtils.isNotEmpty(tasks.getIsOtherArea())&&tasks.getIsOtherArea().equalsIgnoreCase(Constants.NO)){
			if(null==tasks.getMixCar()||tasks.getMixCar()==0||null==tasks.getMaxCar()||tasks.getMaxCar()==0){
				return AjaxResult.error(String.format("非跨县任务单[%s]需要选择填写【车辆方量区间】",tasks.getName()));
			}
		}
		if(tasks.getPlanCarCnt()==null||tasks.getPlanCarCnt()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计用车数】",tasks.getName()));
		}
		if(StringUtils.isNotEmpty(tasks.getIsPinch())&&tasks.getIsPinch().equalsIgnoreCase(Constants.YES)){
			if(null==tasks.getPinchFl()||tasks.getPinchFl().compareTo(new BigDecimal(0))<=0){
				return AjaxResult.error(String.format("任务单[%s]需要选择填写【掐方方量】",tasks.getName()));
			}
		}
		if(tasks.getPlanEndTime()==null){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计结束时间】",tasks.getName()));
		}

		if(null==tasks.getHeight()||tasks.getHeight().intValue()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【限高】",tasks.getName()));
		}
		tasks.setIsSchedule(Constants.YES);
		tasks.setUpdateBy(this.getSysUser().getUserName());
		return toAjax(tasksService.updateTasks(tasks));
	}
	
	/**
	 * 删除任务单
	 */
	@RequiresPermissions("duties:tasks:remove")
	@Log(title = "任务单", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tasksService.deleteTasksByIds(ids));
	}

	/**
	 * 置顶任务单
	 */
	@RequiresPermissions("duties:tasks:topping")
	@Log(title = "置顶任务单", businessType = BusinessType.DELETE)
	@PostMapping( "/topping/{id}/{topping}")
	@ResponseBody
	public AjaxResult topping(@PathVariable("id") String id,@PathVariable("topping") String topping)
	{
		Map<String,String> params = new HashMap<>();
		params.put("topping",topping);
		params.put("id",id);
		int ret = tasksService.updateTopping(params);
		if(ret>0){
			Tasks t = redisUtils.get(Constants.TASK_PATTEN+id,Tasks.class);
			t.setTopping(Integer.valueOf(topping));
			RedisDomainUtils.setRedisTaskDomain(t);
		}
		return toAjax(ret);
	}

	/**
	 * 暂停/开启任务
	 */
	@Log(title = "任务单", businessType = BusinessType.DELETE)
	@ResponseBody
	@PostMapping("/changeStatus")
	public AjaxResult changeStatus(HttpServletRequest request,String id,String pause){
		return tasksService.updateTaskStatus(id,pause);
	}

	/**
	 * 结束任务单
	 */
	@Log(title = "结束任务单", businessType = BusinessType.DELETE)
	@ResponseBody
	@PostMapping("/overorder")
	@Transactional(transactionManager = "masterTransactionManager")
	public AjaxResult overorder(HttpServletRequest request,String id){
		try{
			Tasks task = tasksService.selectTasksById(id);
			task.setStatus(Constants.TASK_CAR_STATUS_COMPLATE);
			task.setUpdateBy(this.getSysUser().getUserName());
			int ret = tasksService.updateTasks(task);
			//---资料室开始
			Profileroom profileroom = tasksService.selectProfileRoom(id);
			List<Profileroom> proList = profileroomService.selectProfileroomList(new Profileroom(){{setTaskId(Long.valueOf(id));}});
			if(null==proList||proList.size()==0){
				if(null!=profileroom&&null!=profileroom.getProjectName()){
					profileroomService.insertProfileroom(profileroom);
				}
			}
			//---资料室结束
			Tasks t = redisUtils.get(Constants.TASK_PATTEN+task.getId(),Tasks.class);
			if(t==null){
				t =task;
			}
			t.setStatus(Constants.TASK_STATUS_COMPLATE);
			RedisDomainUtils.setRedisTaskDomain(t);
		}catch (Exception ex){
			ex.printStackTrace();
			return AjaxResult.error();
		}
		return toAjax(1);
	}
	/**
	 * 保存并下发任务单
	 */
	@RequiresPermissions(value = {"cemslink:task:dispath","duties:tasks:edit"},logical = Logical.AND)
	@Log(title = "任务单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/saveDispath")
	@ResponseBody
	public AjaxResult saveDispath(Tasks tasks)
	{
		//派发间隔
		if(null==tasks.getSubTime()||tasks.getSubTime()==0){
			return AjaxResult.error(String.format("任务单[%s]的\"派车间隔\"必填!",tasks.getName()));
		}
//		if(null==tasks.getPrivilege()||tasks.getPrivilege()==0){
//			return AjaxResult.error(String.format("任务单[%s]的\"优先权\"必填!",tasks.getName()));
//		}else if(StringUtils.isNotEmpty(tasks.getIsGoodJob())&&tasks.getIsGoodJob().equalsIgnoreCase(Constants.YES)&&(null!=tasks.getPrivilege()&&tasks.getPrivilege()>0)){
//			return AjaxResult.error(String.format("任务单[%s]是好活;\"优先权\"不能大于0!",tasks.getName()));
//		}
		if(null==tasks.getLat()||null==tasks.getLon()){
			return AjaxResult.error(String.format("请完善任务单[%s]的坐标信息;",tasks.getName()));
		}
		if(StringUtils.isNotEmpty(tasks.getIsOtherArea())&&tasks.getIsOtherArea().equalsIgnoreCase(Constants.NO)){
			if(null==tasks.getMixCar()||tasks.getMixCar()==0||null==tasks.getMaxCar()||tasks.getMaxCar()==0){
				return AjaxResult.error(String.format("非跨县任务单[%s]需要选择填写【车辆方量区间】",tasks.getName()));
			}
		}
		if(tasks.getPlanCarCnt()==null||tasks.getPlanCarCnt()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计用车数】",tasks.getName()));
		}
		if(StringUtils.isNotEmpty(tasks.getIsPinch())&&tasks.getIsPinch().equalsIgnoreCase(Constants.YES)){
			if(null==tasks.getPinchFl()||tasks.getPinchFl().compareTo(new BigDecimal(0))<=0){
				return AjaxResult.error(String.format("任务单[%s]需要选择填写【掐方方量】",tasks.getName()));
			}
		}
		if(tasks.getPlanEndTime()==null){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计结束时间】",tasks.getName()));
		}

		if(null==tasks.getHeight()||tasks.getHeight().intValue()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【限高】",tasks.getName()));
		}
		tasks.setIsSchedule(Constants.YES);
		int ret = tasksService.updateTasks(tasks);
		return dispath(tasks.getId());
	}


	/**
	 * 保存并下发任务单
	 */
	@RequiresPermissions("cemslink:task:dispath")
	@Log(title = "任务单数据", businessType = BusinessType.UPDATE)
	@PostMapping("/dispath")
	@ResponseBody
	public AjaxResult dispath(String ids)
	{
		int ret = 0;
		Long[] postIds = Convert.toLongArray(ids);
		for(Long id: postIds){
			AjaxResult aj = dispathOne(id);
			if(!aj.get("code").equals("0")){
				return aj;
			}
			ret++;
		}
		return toAjax(ret);
	}


	/**单个任务下发*/
	public AjaxResult dispathOne(Long id){
		Tasks tasks = tasksService.selectTasksById(id.toString());
		//派发间隔
		if(null==tasks.getSubTime()||tasks.getSubTime()==0){
			return AjaxResult.error(String.format("任务单[%s]的\"派车间隔\"必填!",tasks.getName()));
		}
		if(null==tasks.getLat()||null==tasks.getLon()){
			return AjaxResult.error(String.format("请完善任务单[%s]的坐标信息;",tasks.getName()));
		}
		if(StringUtils.isNotEmpty(tasks.getIsOtherArea())&&tasks.getIsOtherArea().equalsIgnoreCase(Constants.NO)){
			if(null==tasks.getMixCar()||tasks.getMixCar()==0||null==tasks.getMaxCar()||tasks.getMaxCar()==0){
				return AjaxResult.error(String.format("非跨县任务单[%s]需要选择填写【车辆方量区间】",tasks.getName()));
			}
		}
		if(tasks.getPlanCarCnt()==null||tasks.getPlanCarCnt()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计用车数】",tasks.getName()));
		}

		if(tasks.getPlanEndTime()==null){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【预计结束时间】",tasks.getName()));
		}

		if(null==tasks.getHeight()||tasks.getHeight().intValue()==0){
			return AjaxResult.error(String.format("任务单[%s]需要选择填写【限高】",tasks.getName()));
		}
		if(StringUtils.isNotEmpty(tasks.getIsMixture())&&!tasks.getIsMixture().equalsIgnoreCase(Constants.YES)){
			logger.debug("该任务单没有配比，单号[{}]",tasks.getPlanOrderNo());
			return AjaxResult.error(String.format("该任务单没有配比，单号[{%s}]",tasks.getPlanOrderNo()));
		}

		//选择出来产线进行下发任务
		List<TasksMixformula> lineIdList = getLineIds(id);
		if(null!=lineIdList&&lineIdList.size()>0){
			for (TasksMixformula tasksMixformula:lineIdList
			) {
				doDispath(tasks,tasksMixformula);
			}
		}
		return AjaxResult.success();
	}


	public void doDispath(Tasks tasks,TasksMixformula tasksMixformula){
		Task param = new Task();
		param.setProductLine(tasksMixformula.getProductLine());
		param.setTaskNumber(tasks.getPlanOrderNo());
		List<Task> list = tService.selectTaskList(param);
		Task task = null;
		if(list!=null&&list.size()>0){
			task = list.get(0);
			logger.debug("任务单【{}-{}】已经存在，重新更新任务单",task.getTaskNumber(),task.getProjectName());
		}else{
			task = new Task();
		}
		task.setProductLine(tasksMixformula.getProductLine());//产线标志
		task.setConcreteLabel(tasks.getProductKind());//砼标号
		task.setTaskNumber(tasks.getPlanOrderNo());//任务单号
		task.setCustomerName(tasks.getReceiver());
		task.setProjectName(tasks.getName());
		task.setUnitName(tasks.getReceiver());//使用单位
		task.setConstructionPart(tasks.getWaterPart());
		task.setPouringWay(tasks.getWaterMethod());
		task.setSlump(tasks.getLocationTanluodu());
		task.setMixNumber(tasksMixformula.getMixNumber());//此时需要检查
		task.setPlanAmount(tasks.getTotalFl().setScale(2, RoundingMode.HALF_UP).doubleValue());
		task.setPlanDateTime(tasks.getArriveTime());
		task.setBooker(tasks.getCreateBy());
		task.setContact(tasks.getReceiver());
		task.setContactPhone(tasks.getReceivorMobile());
		task.setSyncStatus(0);
		task.setSyncStatus2(0);
		Boolean isDemo = Boolean.valueOf(Global.getConfig("project.demo"));
		//演示系统
		if(isDemo){
			task.setSyncStatus(1);
			task.setSyncStatus2(1);
		}
		try{
			if(null==task.getSID()){
				tService.insertTask(task);
			}else{
				tService.updateTask(task);
			}
			//更新同步状态
			tasksService.updateTasksSyncstatusByNumber(task.getTaskNumber());
			updateTasksMixformulaStatus(tasksMixformula,1);
			logger.debug("任务单下发成功，产线:[{}],任务单号:[{}]",task.getProductLine(),task.getTaskNumber());
		}catch (Exception ex){
			ex.printStackTrace();
			logger.debug("下发出错，任务单号:",tasks.getPlanOrderNo());
		}
	}

	/**
	 * 更新同步状态
	 * */
	private void updateTasksMixformulaStatus(TasksMixformula tasksMixformula,int syncstatus) {
		Map param = new HashMap();
		param.put("taskId",tasksMixformula.getTaskId());
		param.put("productLine",tasksMixformula.getProductLine());
		param.put("mixNumber",tasksMixformula.getMixNumber());
		param.put("syncstatus",syncstatus);
		tasksMixformulaService.updateTasksMixformulaMap(param);
	}


	/**
	 * 获取下发的产线id的list
	 * */
	private List<TasksMixformula> getLineIds(Long id) {
		List<TasksMixformula> tmflist = tasksMixformulaService.selectTasksMixformulaList(new TasksMixformula(){{
			setTaskId(id);
			setSyncstatus(0);
		}});
		if(tmflist==null||tmflist.size()==0){
			return null;
		}
		for(ListIterator<TasksMixformula> iter = tmflist.listIterator(); iter.hasNext();){
			TasksMixformula t = iter.next();
			List<Mixformula> mixformulaList = mixformulaService.selectMixformulaList(new Mixformula(){{
				setMixNumber(t.getMixNumber());
				setProductLine(t.getProductLine());
				setSyncStatus(1);
			}});
			if(null==mixformulaList||mixformulaList.size()==0){
				logger.debug("[{}]主机楼没有该配比[{}]不派发任务单",t.getProductLine(),t.getMixNumber());
				iter.remove();
				continue;
			}
		}
		return tmflist;
	}


	/**
	 * 手动派单
	 */
	@GetMapping("/handTask")
	public String handTask(HttpServletRequest request){
		return prefix+"/handTask";
	}

	/**
	 * 手动派单
	 */
	/*@Log(title = "手动派单", businessType = BusinessType.INSERT)
	@PostMapping("/handTaskSave")
	@ResponseBody
	public AjaxResult handTaskSave(String taskId,String carNo,String bcfl)
	{
		// 1.先查找该任务中的车辆，计算累计方量
		Tasks task = tasksService.selectTasksById(taskId);
		//汽车类型-判断
		Integer carType = task.getCarType();
		//运行中的车辆
		List<RuningCar> runingList = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);

		*//**
		 * 查询当前任务的累计方量
		 * 两种方式 1.redis   2.查询南方路基接口.
		 * 目前暂时用redis
		 * *//*
		RTaskVo rTaskVo =  redisUtils.get(Constants.TASK+taskId,RTaskVo.class);
		if(null == rTaskVo){
			return AjaxResult.error();
		}
		//如果 总方量 - 累计放量  < 本地方量
		*//*if(rTaskVo.getTotalFl().subtract(rTaskVo.getCurFangLiang()).compareTo(new BigDecimal(bcfl))<0){
			throw new BaseException("派单量大于计划量");
		}*//*
		BigDecimal curFl = rTaskVo.getCurFangLiang();
		return toAjax(1);
	}*/



	/**选出来一个主机进行生产*/
	/*private String[] getProductLine2Task(Tasks task) {
		PreproductionQueue que = null;
		*//**
		 * 分配主机号，
		 * 1. 检查主机状态，开机才能打料
		 * 2. 检查主机当前石子类型，如果任务单是卵石，只能在能打卵石的主机生产
		 * 3. 检查主机生产排队状态，哪个少去哪个。
		 * 如果是卵石只能在1号主机
		 * **//*
		List<ProductLine> productLines = redisUtils.getLists(Constants.PRODUCT_LINE_PATTERN,ProductLine.class);
		if(null==productLines)
			return null;
		String ids  =generateIds(productLines,task); // getIdsFromProductline(productLines,task);
		if(StringUtil.isEmpty(ids)){
			return null;
		}
		ids= StringUtils.substringBeforeLast(ids,"-");
		if(StringUtils.isBlank(ids)){
			return null;
		}
		return ids.split("-");
	}*/


	public String getIdsFromProductline(List<ProductLine> productLines,Tasks task){
		String ids = "";
		for(ListIterator<ProductLine> iter = productLines.listIterator(); iter.hasNext();){
			ProductLine productLine = iter.next();
			//1.在线状态
			if(productLine.getState()==Constants.OFF){
				iter.remove();
				continue;
			}
			//2. 1.卵石  0.青石
			if(task.getIsCobblestone().equalsIgnoreCase(Constants.YES)){
				if(productLine.getStoneType()!=Constants.COBBLESTONE){
					iter.remove();
					continue;
				}
			}else{
				if(productLine.getStoneType()!=Constants.QING_STONE){
					iter.remove();
					continue;
				}
			}
			ids+=productLine.getProductLine()+"-";
		}
		return ids;
	}
/*
    public String generateIds(List<ProductLine> productLines,Tasks task){
	    if(null==productLines||productLines.size()==0){
	        return "";
        }
        List<Mixformula> mixlist = mixformulaService.selectMixformulaList(new Mixformula(){{setSqlWhere(" and (SyncStatus=1 or SyncStatus2=1)");}});
        String ids = "";
        for(ListIterator<ProductLine> iter = productLines.listIterator(); iter.hasNext();){
            ProductLine productLine = iter.next();
            boolean flag = false;
            for (Mixformula mix:mixlist) {
                if(mix.getMixNumber().equalsIgnoreCase(task.getMixNo())&&mix.getProductLine()==productLine.getProductLine()){
                    flag = true;
                    break;
                }
            }
            if(!flag){
                logger.debug("[{}]号产线没有配比[{}]，不符合条件",productLine.getProductLine(),task.getMixNo());
                iter.remove();
                continue;
            }
            ids+=productLine.getProductLine()+"-";
        }
        return ids;
    }*/


	/**
	 * 查询任务单修改详细
	 */
	@RequiresPermissions("duties:tasksUpdatelog:list")
	@GetMapping("/detail/{taskId}")
	public String detail(@PathVariable("taskId") String taskId, ModelMap mmap)
	{
		mmap.put("task", tasksService.selectTasksById(taskId));
		mmap.put("tasksList", tasksService.selectTasksList(null));
		return "duties/tasksUpdatelog/tasksUpdatelog";
	}

	/**
	 * 查询正在运行的任务列表
	 */
	@RequestMapping("/run/list")
	@ResponseBody
	public AjaxResult getRunTasksList(){
		return AjaxResult.success(tasksService.selectDispathList());
	}

	/**清理redis缓存*/
	@RequestMapping("deleteAllRedis")
	@RequiresPermissions("duties:taks:clearredis")
	@ResponseBody
	public AjaxResult deleteAllRedis(){
		redisUtils.deleteAll(Constants.REDIS_WEBSITE_PRE+"ST_*");
		redisUtils.deleteAll(Constants.REDIS_WEBSITE_PRE+"SHANGTONG*");
		redisUtils.deleteAll(Constants.REDIS_WEBSITE_PRE+"APP_TOKEN_*");
		return AjaxResult.success();
	}

	/**
	 *  新增 设备位置
	 * @return 开始位置  结束位置  当前设备的位置信息
	 */
	@RequestMapping("/device/location")
	@ResponseBody
	public AjaxResult deviceLocation(String TaskId,String carId){
		//String taskId = "78";
		Map resultData = new HashMap();
		String token = redisUtils.get(Constants.LOCATION_ACCESS_TOKEN);
		if(null != token ){
			List<Map> resultMap = Lists.newArrayList();
			List<Car> listData = new ArrayList<>();

			Tasks task= new Tasks();
			//查询的任务车辆
			if(StringUtils.isNotBlank(TaskId)){
				listData = tasksService.selectCarByTask(TaskId);
				task = tasksService.selectTasksById(TaskId);
			}else{
				listData = redisUtils.getLists(Constants.CAR_PATTEN,Car.class);
				if(listData==null||listData.size()==0){
					listData = carService.selectCarList(new Car());
				}
			}
			if(StringUtils.isNotBlank(carId)){
				listData.clear();
				Car oneCar = redisUtils.get(Constants.CAR_PATTEN+carId,Car.class);
				if(null==oneCar ){
					oneCar = carService.selectCarById(Long.valueOf(carId));
				}
				listData.add(oneCar);
			}
			try{
				listData.forEach((car) -> {
							if(null != car.getImei()){
//								executor.execute(new Runnable() {
//									@Override
//									public void run() {
//
//									}
//								});
								Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.LOCATION_INFO+"?accessToken="+token+"&imei="+car.getImei(),Map.class);
								if(Integer.valueOf(result.get("code").toString()) == 0){
									double[] ll = GeoUtil.gps84_To_bd09(Double.valueOf(result.get("lat").toString()),Double.valueOf(result.get("lng").toString()));
									result.put("lat",ll[0]);
									result.put("lng",ll[1]);
									result.put("carNum",car.getCarNum());
									result.put("carNo",car.getCarNo());
									resultMap.add(result);
								}
								logger.info("2s種請求一次api成功");
							}
						}
				);
			}catch (Exception e){
				return AjaxResult.error("gps服务器异常");
			}
		/*double distance = GeoUtil.GetDistance(new GPSLocation(Double.valueOf(resultMap.get(0).get("lat").toString()),Double.valueOf(resultMap.get(0).get("lng").toString()))
				,new GPSLocation(34.72385,113.769966));*/
			if(resultMap.size()>0){
				resultData.put("cars",resultMap);
				TasksData td = TasksData.getInstance();
				td.setTargetAddr(task.getTargetAddr());
				td.setLat(task.getLat() != null ? task.getLat() : Global.getConfig("project.lat"));
				td.setLon(task.getLon() != null ? task.getLon() : Global.getConfig("project.lon"));
				td.setStartAddr(task.getStartAddr());
					td.setStartLat(task.getStartLat()!=null ? task.getStartLat() : Global.getConfig("project.lat"));
				td.setStartLng(task.getStartLng()!=null ? task.getStartLng() : Global.getConfig("project.lon"));
				resultData.put("task",td);
			}
		}
//		else{
//			redisUtils.set(Constants.LOCATION_ACCESS_TOKEN,getToken(),100*60*1000);
//		}
		return AjaxResult.success(resultData);
	}

	/**
	 * 任务监控
	 */
	@GetMapping("/monitor")
	public String tasksMonitor()
	{
		return prefix + "/monitor";
	}
}
