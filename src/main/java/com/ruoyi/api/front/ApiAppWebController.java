package com.ruoyi.api.front;


import com.ruoyi.api.MySeq;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.tasksMixformula.domain.TasksMixformula;
import com.ruoyi.project.cars.tasksMixformula.service.ITasksMixformulaService;
import com.ruoyi.project.cemslink.mixformula.controller.MixformulaController;
import com.ruoyi.project.cemslink.mixformula.domain.Mixformula;
import com.ruoyi.project.cemslink.mixformula.service.IMixformulaService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.cemslink.task.domain.Task;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import com.ruoyi.project.duties.tasksUpdatelog.service.ITasksUpdatelogService;
import com.ruoyi.project.redisEntry.LonLat;
import com.ruoyi.project.redisEntry.LonLatEntity;
import com.ruoyi.project.system.user.domain.User;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

//@RestController
@RestController
@Configuration
@RequestMapping("/webapp")
public class ApiAppWebController extends BaseController {
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private IMixformulaService mixformulaService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ICarService carService;
    @Autowired
    private MixformulaController mixformulaController;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private IPreproductionService preproductionService;

    @Autowired
    private ITasksUpdatelogService updatelogService;
    @Autowired
    private ITasksMixformulaService tasksMixformulaService;

    private Logger logger = LoggerFactory.getLogger(ApiAppWebController.class);
    @Value("${project.lat}")
    private Double startLat;
    @Value("${project.lon}")
    private Double startLng;
    @Value("${project.endLng}")
    private String endLng;
    @Value("${project.endLat}")
    private String endLat;

    @ApiOperation("业务员添加任务单")
    @RequiresPermissions("duties:tasks:add")
    @RequestMapping("/addTask")
    public AjaxResult taskList(HttpServletRequest request,Tasks tasks) {
        tasks.setLon(endLng);
        tasks.setLat(endLat);
        if(StringUtils.isBlank(tasks.getId())){
            if(tasks.getArriveTime().getTime()<System.currentTimeMillis()){
                return AjaxResult.error("用砼时间已过期");
            }
            tasks.setCreateBy(this.getSysUser().getUserName());
            tasks.setCreateId(this.getSysUser().getUserId());
            tasks.setOfficerMobile(this.getSysUser().getPhonenumber());
            tasks.setPlanOrderNo(MySeq.getTask());//获取任务单号
            return toAjax(tasksService.insertTasks(tasks));
        }else{
            return toAjax(tasksService.updateTasks(tasks));
        }
    }

    @ApiOperation("业务员任务单列表")
    @PostMapping("/taskList")
    @RequiresPermissions("duties:tasks:list")
    public TableDataInfo taskList(HttpServletRequest request) {
        startPage();
        Tasks task = new Tasks();
        Long[] postIds = getSysUser().getPostIds();
        String sqlWhere = " and 1=1 ";
        if(!contains(postIds,1L)){
            task.setCreateId(getUserId());
        }
        task.setSqlWhere(sqlWhere+" order by create_time desc ");
        List<Tasks> list = tasksService.selectTasksList(task);
        return getDataTable(list);
    }


    /**
     * 财务原因暂停（业务员暂停）
     */
    @Log(title = "财务原因暂停", businessType = BusinessType.DELETE)
    @ResponseBody
    @PostMapping("/financePause")
    @RequiresPermissions("duties:tasks:financePause")//操作权限
    public AjaxResult financePause(HttpServletRequest request,@RequestParam String id) {
        return doFinance(id,Constants.YES);
    }

    /**
     * 财务原因暂停（业务员暂停）
     */
    @Log(title = "财务原因开启", businessType = BusinessType.DELETE)
    @ResponseBody
    @PostMapping("/financeStart")
    @RequiresPermissions("duties:tasks:financePause")//操作权限
    public AjaxResult financeStart(HttpServletRequest request,@RequestParam String id) {
        return doFinance(id,Constants.NO);
    }

    /**修改财务状态*/
    private AjaxResult doFinance(String id,String state){
        Tasks task = redisUtils.get(Constants.TASK_PATTEN+id,Tasks.class);
        if(null==task){
            task = tasksService.selectTasksById(id);
        }
        if(state.equalsIgnoreCase(task.getFinancePause())){
            return AjaxResult.error("当前状态已经是"+ (state.equalsIgnoreCase(Constants.YES)? "暂停状态":"正常状态"));
        }
        task.setFinancePause(state);//暂停标注
        task.setUpdateBy(this.getSysUser().getUserName());
        int ret = tasksService.updateTasks(task);
        RedisDomainUtils.setRedisTaskDomain(task);
        return AjaxResult.success();
    }


    @ApiOperation("实验室任务单列表")
    @PostMapping("/laboratoryTaskList")
    @RequiresPermissions("duties:tasks:list")
    public TableDataInfo laboratoryTaskList(HttpServletRequest request) {
        startPage();
        List<Tasks> list = tasksService.selectTasksList(new Tasks() {{
            setIsSchedule(Constants.YES);
            setSqlWhere(" order by create_time desc ");
        }});
        for (Tasks task:list
             ) {
            if(null==task){
                continue;
            }
            List<TasksMixformula> mixList = tasksMixformulaService.selectTasksMixformulaList(new TasksMixformula(){{
                setTaskId(Long.valueOf(task.getId()));
            }});
            if(null!=mixList&&mixList.size()>0){
                task.setMixList(mixList);
            }
        }
        return getDataTable(list);
    }


    /**车辆列表**/
    @RequestMapping("/carlist")
    @ResponseBody
    public AjaxResult carList(HttpServletRequest request){
        List<Car> list = carService.selectCarList(null);
        return AjaxResult.success().put("list",list);
    }


    /**
     * 查询配合比数据列表
     */
    @PostMapping("/mixFormulaList")
    @RequiresPermissions("cemslink:mixformula:list")
    @ResponseBody
    public AjaxResult mixFormulaList(String taskId)
    {
        Mixformula mixformula = null;
        if(StringUtils.isNotEmpty(taskId)){
            Tasks t = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
            if(t==null){
                t = tasksService.selectTasksById(taskId);
            }
            if(t!=null){
                List<Mixformula> list = mixformulaService.selectDistinctList();
                return AjaxResult.success().put("list",list);
            }
        }
        List<Mixformula> list = mixformulaService.selectMixformulaList(mixformula);
        return AjaxResult.success().put("list",list);
    }
    /**
     * 新增保存配合比数据
     */
    @Log(title = "新增保存配合比数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("cemslink:mixformula:add")
    @PostMapping("/addMixformula")
    public AjaxResult addMixformula(Mixformula mixformula)
    {
        return mixformulaController.addSave(mixformula);
    }

    /**
     * 修改保存配合比数据
     */
    @Log(title = "配合比数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("cemslink:mixformula:edit")
    @PostMapping("/editMixformula")
    public AjaxResult editMixformula(Mixformula mixformula)
    {
        return mixformulaController.editSave(mixformula);
    }

    /**
     * 任务单配比
     *
     * @param taskId    任务id
     * @param mixNumber 配比编号
     */
    @Log(title = "任务单配比")
    @ResponseBody
    @PostMapping("/mixformulaTask")
    @RequiresPermissions("duties:tasks:mixture")//操作权限
    public AjaxResult mixformulaTask(HttpServletRequest request,@RequestParam(required = true)  String taskId,@RequestParam(required = true) String mixNumber,@RequestParam(required = true) String productLine) {
        Tasks t = tasksService.selectTasksById(taskId);
        if (null == t) {
            return AjaxResult.error("任务单不存在");
        }else{
            if(!t.getIsSchedule().equalsIgnoreCase(Constants.YES)){
                return AjaxResult.error("调度室没有审核");
            }
        }
        //检查是否有正在生产的任务配比
        String sql = String.format(" and ( sort>0 or sort is null) and state = 1 and MixNumber='%s' and ProductLine=%s",mixNumber,productLine);
        List<Preproduction> preList =  preproductionService.selectPreproductionList(new Preproduction(){{
            setSqlWhere(sql);
        }});
        if(preList!=null&&preList.size()>0){
            return AjaxResult.error("主机楼有正在生产的任务，请稍后操作");
        }
        List<Mixformula> list = mixformulaService.selectMixformulaList(new Mixformula() {{
            setMixNumber(mixNumber);
            setProductLine(Integer.valueOf(productLine));
        }});
        if (null == list || list.size() == 0) {
            return AjaxResult.error("找不到配比单");
        }
        Mixformula m = list.get(0);
//        t.setMixNo(mixNumber);

        t.setIsMixture(Constants.YES);//配比标志位
        t.setProductLine(productLine);
        //保存或更新配比
        tasksService.Mixformula(mixNumber,Integer.valueOf(productLine),Long.valueOf(t.getId()));
        tasksService.updateTasks(t);
        RedisDomainUtils.setRedisTaskDomain(t);
        return AjaxResult.success();
    }

    /**
     * 对接问题：
     * 1.transaction都要注解  ok
     * 2.目的地不要
     * 3.大小车不要
     * 4.业务员的任务单列表
     * 5.事务问题
     * 6.
     * **/

    /**
     * 查询配合比数据列表
     */
    @PostMapping("/getLatLngCars")
    @RequiresPermissions("duties:tasks:list")
    @ResponseBody
    public AjaxResult getLatLngCars(@RequestParam(required = true) String taskId)
    {
        List<LonLatEntity> lonLats = new ArrayList<>();
        Tasks tasks = redisUtils.get(Constants.TASK_PATTEN+taskId,Tasks.class);
        if(null!=tasks&&tasks.getStatus()!=null&&tasks.getStatus()==Constants.TASK_STATUS_ING){
            List<TasksCars> tclist = redisUtils.getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
            List<String> notifyIds = new ArrayList<>();
            for (TasksCars tccar:tclist
                 ) {
                if(tccar!=null&&tccar.getStatus()!=null&&tccar.getTaskId()!=null&&tccar.getTaskId().equals(Long.valueOf(taskId))&&tccar.getStatus()==Constants.TASK_STATUS_ING){
                    notifyIds.add(tccar.getNotifyId());
                }
            }
            List<RuningCar> runingCarsList = redisUtils.getLists(Constants.RUNINGCAR_PATTEN,RuningCar.class);
            for (RuningCar runcar:runingCarsList
                 ) {
                for (String notifyId:notifyIds
                     ) {
                    if(runcar!=null&&runcar.getNotifyId()!=null&&runcar.getNotifyId().equalsIgnoreCase(notifyId)){
                        LonLatEntity lngt = new LonLatEntity();
                        lngt.setLat(runcar.getLat());
                        lngt.setLon(runcar.getLng());
                        lngt.setCarNo(runcar.getCarNo());
                        lonLats.add(lngt);
                    }
                }
            }
        }
        return AjaxResult.success().put("list",lonLats).put("lat",tasks==null?"":tasks.getLat()).put("lng",tasks==null?"":tasks.getLon()).put("startLat", startLat).put("startLng",startLng);
    }

    /**
     * 查询配合比数据列表
     */
    @PostMapping("/getPreproductioning")
    @RequiresPermissions("duties:tasks:list")
    @ResponseBody
    public AjaxResult getPreproductioning(){
        Preproduction pre =  preproductionService.selectOneProducting();
        if(pre!=null){
            return AjaxResult.success().put("preproduction",pre);
        }
        return AjaxResult.success();
    }




    /**
     * 调正单子获取任务列表
     */
    @PostMapping("/getTaskListToTrans")
    @RequiresPermissions("duties:tasks:transtask")
    @ResponseBody
    public AjaxResult getTaskListToTrans(@RequestParam(required = true) Long taskCarId){
        TasksCars tasksCars= tasksCarsService.selectTasksCarsById(taskCarId);
        if(null==tasksCars){
            return AjaxResult.error("此记录没有找到");
        }
        List<Tasks> list = tasksService.selectTasksList(new Tasks(){{
            setSqlWhere(String.format(" and status <>3 and is_schedule='Y' and  is_mixture ='Y' and pause='N' and SyncStatus=1 and id <> %d ",tasksCars.getTaskId()));
        }});
        return AjaxResult.success().put("list",list);
    }


    /**
     * 调正单子
     */
    @PostMapping("/transTasksToOther")
    @RequiresPermissions("duties:tasks:transtask")
    @ResponseBody
    public AjaxResult transTasksToOther(@RequestParam(required = true) Long taskCarId,@RequestParam(required = true) Long toTaskId,@RequestParam(required = true) String remark){
        User user = this.getSysUser();
        TasksCars tasksCars= tasksCarsService.selectTasksCarsById(taskCarId);
        boolean completeFlag = false;
        if(null!=tasksCars&&!tasksCars.isSure()){
            return AjaxResult.error("任务没有接单确定，不能调单");
        }
        if(null!=tasksCars&&tasksCars.getStatus()==Constants.DELETE_FLAG){
            return AjaxResult.error("任务单已被删除，无法调单");
        }
        if(tasksCars!=null&&tasksCars.getStatus()!=null&&tasksCars.getStatus()==Constants.TASK_CAR_STATUS_COMPLATE){
            tasksCars = tasksCarsService.selectTasksCarsById(taskCarId);
            completeFlag = true;
        }
        if(tasksCars==null){
            return AjaxResult.error("此派单记录不存在");
        }
        //目标任务单
        Tasks targetTasks = tasksService.selectTasksById(toTaskId+"");
        BigDecimal targetOriLjfangliang = targetTasks.getLjfangliang();
        int targetOriCarcnt = targetTasks.getCarCnt();
        ////原任务单
        Tasks sourceTasks = tasksService.selectTasksById(tasksCars.getTaskId()+"");
        String sourceRemark = String.format("调整单子[%s]目标单子[%s],原因:[%s],调整人[%s]",sourceTasks.getName(),targetTasks.getName(),remark,user.getUserName());
        BigDecimal oriLjfangliang = sourceTasks.getLjfangliang();
        int carcnt = sourceTasks.getCarCnt();
        tasksCars.setRemark(sourceRemark);
        //调整原来的单子
        transFormSourceTasks(sourceTasks,targetTasks,tasksCars,completeFlag);
        //维护原记录
        maitainLogs(oriLjfangliang,carcnt,sourceTasks,sourceRemark);
        //维护目标记录记录
        String targetRemark = String.format("调整单子[%s]原单子[%s],原因:[%s],调整人[%s]",targetTasks.getName(),sourceTasks.getName(),remark,user.getUserName());
        maitainLogs(targetOriLjfangliang,targetOriCarcnt,targetTasks,targetRemark);
        return AjaxResult.success();
    }

    @Transactional(value = "masterTransactionManager")
    //调整原来的单子
    public void transFormSourceTasks(Tasks s_Tasks,Tasks t_Tasks, TasksCars s_TasksCars,boolean completeFlag) {
        s_Tasks.setLjfangliang(s_Tasks.getLjfangliang().subtract(s_TasksCars.getEqualFangliang()));
        s_Tasks.setCarCnt(s_Tasks.getCarCnt()>0?s_Tasks.getCarCnt()-1:0);

        s_TasksCars.setStatus(Constants.DELETE_FLAG);//删除旧的单子
        if(null!=t_Tasks.getLjfangliang()){
            if(null!=s_TasksCars.getEqualFangliang()){
                t_Tasks.setLjfangliang(t_Tasks.getLjfangliang().add(s_TasksCars.getEqualFangliang()).setScale(2, RoundingMode.HALF_UP));
            }else{
                t_Tasks.setLjfangliang(t_Tasks.getLjfangliang());
            }
        }
        t_Tasks.setCarCnt(t_Tasks.getCarCnt()+1);
        TasksCars t_Taskcar =new TasksCars();
        BeanUtils.copyProperties(s_TasksCars,t_Taskcar);
        //已经送达的单子，直接调整
        if(completeFlag){
            /*TasksCars rtc = redisUtils.get(Constants.TASKINGCAR_PATTEN+s_TasksCars.getNotifyId(),TasksCars.class);
            if(null!=rtc&&rtc.getTaskId()!=null&&rtc.getStatus()!=null&&rtc.getCarId()!=null&&rtc.getCarNo()!=null){
                if(rtc.getCarNo().equals(s_TasksCars.getCarNo())&&!rtc.getTaskId().equals(s_TasksCars.getTaskId())&&rtc.getStatus().equals(Constants.TASK_STATUS_ING)){
                    t_Taskcar.setStatus(Constants.TASK_STATUS_COMPLATE);
                }
            }*/
            t_Taskcar.setStatus(Constants.TASK_STATUS_COMPLATE);
        }
        if(t_Taskcar.getStatus()==null||t_Taskcar.getStatus()==Constants.DELETE_FLAG){
            t_Taskcar.setStatus(Constants.TASK_STATUS_ING);
        }
        t_Taskcar.setTaskId(Long.valueOf(t_Tasks.getId()));
        t_Taskcar.setCarCnt(t_Tasks.getCarCnt());
        t_Taskcar.setTaskName(t_Tasks.getName());
        t_Taskcar.setLng(t_Tasks.getLon());
        t_Taskcar.setLat(t_Tasks.getLat());
        t_Taskcar.setTaskNumber(t_Tasks.getPlanOrderNo());
        t_Taskcar.setUpdateBy(getSysUser().getUserName());
        t_Taskcar.setId(null);
        t_Taskcar.setSyncStatus(1);
        t_Taskcar.setOfficerMobile(t_Tasks.getOfficerMobile());
        t_Taskcar.setOfficer(t_Tasks.getOfficer());
        t_Taskcar.setReceivePhone(t_Tasks.getReceivorMobile());
        t_Taskcar.setReceiver(t_Tasks.getReceiver());
        t_Taskcar.setWaterPart(t_Tasks.getWaterPart());
        t_Taskcar.setWaterMethod(t_Tasks.getWaterMethod());
        t_Taskcar.setLjfangliang(t_Tasks.getLjfangliang());
        t_Taskcar.setWaterPart(t_Tasks.getWaterPart());
        //新增新的车次信息
        tasksCarsService.insertTasksCars(t_Taskcar);
        //更新原来的车次信息
        tasksCarsService.updateTasksCars(s_TasksCars);
        //更新原来的任务单
        tasksService.updateTasks(s_Tasks);
        //更新目标任务单
        tasksService.updateTasks(t_Tasks);
        TasksCars redisTaskscar = redisUtils.get(Constants.TASKINGCAR_PATTEN+s_TasksCars.getNotifyId(),TasksCars.class);
        //在跑车辆的任务单id与现在的任务单id一样，并且车次一样，就重新设置在跑车辆信息
        if(redisTaskscar!=null&&redisTaskscar.getTaskId()!=null&&redisTaskscar.getTaskId().equals(Long.valueOf(s_Tasks.getId()))){
            if(redisTaskscar.getCarCnt()==(s_TasksCars.getCarCnt())){
                RedisDomainUtils.setRedisTaskCarDomain(t_Taskcar);
                RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+t_Taskcar.getNotifyId(),RuningCar.class);
                logger.debug("更改了taskstatus:方法【{}】","transFormSourceTasks");
//                runingCar.setTaskStatus(1);
                runingCar.setTaskId(t_Taskcar.getTaskId());
//                runingCar.setTasking(true);
                redisUtils.set(Constants.RUNINGCAR_PATTEN+t_Taskcar.getNotifyId(),runingCar,-1);
            }
        }
    }

    /**
     *
     * **/
    private void maitainLogs(BigDecimal oriFangliang,int oricarcnt,Tasks tasks, String remark){
        try{
            TasksUpdatelog log = new TasksUpdatelog();
            log.setTaskId(Long.valueOf(tasks.getId()));
            log.setTaskName(tasks.getName());
            log.setTaskNumber(tasks.getPlanOrderNo());
            log.setOriFangliang(oriFangliang);
            log.setOriCarcnt(oricarcnt);
            log.setRemark(remark);
            log.setFangliang(tasks.getLjfangliang());
            log.setCarcnt(tasks.getCarCnt());
            log.setUpdateBy(tasks.getUpdateBy());
            updatelogService.insertTasksUpdatelog(log);
        }catch (Exception e){
            logger.debug(e.getMessage());
        }
    }


    private boolean contains(Long[] sets,Long obj){
        if(null==sets||sets.length==0){
            return true;
        }
        for (Long ori:sets){
            if(null!=obj&&null!=ori&&obj.equals(ori)){
                return true;
            }
        }
        return false;
    }

}