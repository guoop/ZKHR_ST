package com.ruoyi.project.duties.tasks.service;

import java.util.List;
import java.util.Map;

import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.mapper.TasksMixformulaMapper;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.tasksCars.service.TasksCarsServiceImpl;
import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import com.ruoyi.project.duties.tasksUpdatelog.service.ITasksUpdatelogService;
import com.ruoyi.project.profile.profileroom.domain.Profileroom;
import com.ruoyi.project.redisEntry.RTaskVo;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.project.duties.tasks.mapper.TasksMapper;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.common.utils.text.Convert;
import org.springframework.transaction.annotation.Transactional;
import org.yaml.snakeyaml.scanner.Constant;

/**
 * 任务单 服务层实现
 * 
 * @author admin
 * @date 2019-04-25
 */
@Service
public class TasksServiceImpl implements ITasksService 
{
	@Autowired
	private TasksMapper tasksMapper;
	@Autowired
	private TasksCarsServiceImpl tasksCarsService;
	@Autowired
	private RedisUtils redisUtils;
	@Autowired
	private IDictDataService dictDataService;
	@Autowired
	private ITasksUpdatelogService updatelogService;
	@Autowired
	private TaskJob taskJob;
	@Autowired
	private TasksMixformulaMapper tasksMixformulaMapper;

	/**
     * 查询任务单信息
     * 
     * @param id 任务单ID
     * @return 任务单信息
     */
    @Override
	public Tasks selectTasksById(String id)
	{
	    return tasksMapper.selectTasksById(id);
	}
	
	/**
     * 查询任务单列表
     * 
     * @param tasks 任务单信息
     * @return 任务单集合
     */
	@Override
	public List<Tasks> selectTasksList(Tasks tasks)
	{
	    return tasksMapper.selectTasksList(tasks);
	}
	
    /**
     * 新增任务单
     * 
     * @param tasks 任务单信息
     * @return 结果
     */
	@Override
	public int insertTasks(Tasks tasks)
	{
		int ret = tasksMapper.insertTasks(tasks);
		tasks = tasksMapper.selectTasksById(tasks.getId());
		RedisDomainUtils.setRedisTaskDomain(tasks);
	    return ret;
	}
	
	/**
     * 修改任务单
     * 
     * @param tasks 任务单信息
     * @return 结果
     */
	@Override
	@Transactional(transactionManager = "masterTransactionManager")
	public int updateTasks(Tasks tasks)
	{
		/*Tasks oriTask = tasksMapper.selectTasksById(tasks.getId());
		if((null!=tasks.getMixNo()&&!tasks.getMixNo().equalsIgnoreCase(oriTask.getMixNo()))||
		   !tasks.getIsCobblestone().equalsIgnoreCase(oriTask.getIsCobblestone())||
		(tasks.getProductLine()!=null&&oriTask.getProductLine()!=null&&!tasks.getProductLine().equalsIgnoreCase(oriTask.getProductLine()))){
			tasks.setSyncStatus(-1);//重置同步位
		}*/
		String remark = tasks.getRemark();
		tasks.setRemark(null);
		int ret = tasksMapper.updateTasks(tasks);
		tasks = tasksMapper.selectTasksById(tasks.getId());
		//如果任务单有备注
		if(StringUtils.isNotEmpty(remark)){
			Tasks t = redisUtils.get(Constants.TASK_PATTEN+ tasks.getId(),Tasks.class);
			TasksUpdatelog log = new TasksUpdatelog();
			log.setTaskId(Long.valueOf(tasks.getId()));
			log.setTaskName(tasks.getName());
			log.setTaskNumber(tasks.getPlanOrderNo());
			if(t!=null&&t.getLjfangliang()!=null){
				log.setOriFangliang(t.getLjfangliang());
			}
			if(t!=null&&t.getCarCnt()!=null){
				log.setOriCarcnt(t.getCarCnt());
			}
			log.setRemark(remark);
			log.setFangliang(tasks.getLjfangliang());
			log.setCarcnt(tasks.getCarCnt());
			log.setUpdateBy(tasks.getUpdateBy());
			updatelogService.insertTasksUpdatelog(log);
		}
		RedisDomainUtils.setRedisTaskDomain(tasks);
	    return ret;
	}

	/**
     * 删除任务单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	@Transactional(transactionManager = "masterTransactionManager")
	public int deleteTasksByIds(String ids)
	{
		for (String s : Convert.toStrArray(ids)) {
			Tasks t = tasksMapper.selectTasksById(s);
			if(t.getStatus().equals(Constants.TASK_STATUS_ING)){
				throw new BaseException("任务已经开始无法删除");
			}
			tasksMapper.deleteTasksById(t.getId());
			RedisDomainUtils.setRedisTaskDomain(t,true);
		}
//		int ret = tasksMapper.deleteTasksByIds(Convert.toStrArray(ids));;

		return 1;
	}


	/*
	* 开启或者关闭任务
	* */
	@Override
//	@Transactional(transactionManager = "masterTransactionManager")
	public AjaxResult updateTaskStatus(String id, String pause) {
		Tasks task = tasksMapper.selectTasksById(id);
		if(task.getFinancePause().equalsIgnoreCase(Constants.YES)){
			return AjaxResult.error("财务原因暂停中，无法操作");
		}
		task.setPause(pause);
		int ret = tasksMapper.updateTasks(task);
		Tasks t = redisUtils.get(Constants.TASK_PATTEN+task.getId(),Tasks.class);
		if(t==null){
			t =task;
		}
		t.setPause(pause);
		RedisDomainUtils.setRedisTaskDomain(t);
		try{
			taskJob.autoSendTasks();
		}catch (Exception ex){
			ex.printStackTrace();
			return AjaxResult.success();
		}
		return AjaxResult.success();
	}

	@Override
	public Tasks selectOneToTask(Map<String, String> prams) {
		return tasksMapper.selectOneToTask(prams);
	}

	@Override
	public int stopTasks(String id) {
		return 0;
	}

	@Override
	public List<DictData> selectCartypeList(String id) {
		Tasks tasks = tasksMapper.selectTasksById(id);
		if(null==tasks)return null;
		List<DictData> list = dictDataService.selectDictDataList(new DictData(){{setDictType("shangtong_cars_types");}});
		list.forEach(item->{
			if(StringUtils.isNotEmpty(tasks.getCartypeList())){
				if(tasks.getCartypeList().contains(item.getDictValue())){
					item.setFlag(true);
				}
			}
		});
		return list;
	}

	@Override
	public Profileroom selectProfileRoom(String id) {
		return tasksMapper.selectProfileRoom(id);
	}

	@Override
	public List<Tasks> selectFirstTasksToDispath(Integer taskCntToDispath) {
		return tasksMapper.selectFirstTasksToDispath(taskCntToDispath);
	}

	@Override
	public List<Tasks> selectDispathList() {
		return tasksMapper.selectDispathList();
	}

	@Override
	public int updateTopping(Map<String, String> prams) {
		return tasksMapper.updateTopping(prams);
	}

	@Override
	public void updateTasksSyncstatusByNumber(String taskNumber) {
		tasksMapper.updateTasksSyncstatusByNumber(taskNumber);
	}


	/**
	 * 配比或者更新配比
	 * */
	@Override
	public void Mixformula(String mixNumber, int productLine,Long taskId) {
		//1.检查中间表是否有此配比
		TasksMixformula tasksMixformula = tasksMixformulaMapper.selectOne(new TasksMixformula(){{
			setProductLine(productLine);
			setTaskId(taskId);
		}});
		if(null==tasksMixformula){
			tasksMixformula = new TasksMixformula();
			tasksMixformula.setMixNumber(mixNumber);//更新
			tasksMixformula.setProductLine(productLine);
			tasksMixformula.setTaskId(taskId);
			tasksMixformula.setSyncstatus(0);//重新更新标志位
			tasksMixformulaMapper.insertTasksMixformula(tasksMixformula);
		}else{
			tasksMixformula.setMixNumber(mixNumber);//更新
			tasksMixformula.setSyncstatus(0);
			tasksMixformulaMapper.updateTasksMixformula(tasksMixformula);
		}
	}

	@Override
	public List<Tasks> selectTasks4Dispath() {
		return tasksMapper.selectTasks4Dispath();
	}

	@Override
	public List<Tasks> selectTaskNameList(Tasks tasks) {
		return tasksMapper.selectTaskNameList(tasks);
	}

	@Override
	public List<Car> selectCarByTask(String taskId) {
		return tasksMapper.selectCarByTask(taskId);
	}

}
