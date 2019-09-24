package com.ruoyi.project.duties.tasksCars.service;

import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.ApiCar;
import com.ruoyi.api.domain.ApiTasksCars;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.mapper.PreproductionMapper;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.mapper.TasksMapper;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.domain.TasksCarsReport;
import com.ruoyi.project.duties.tasksCars.domain.TasksCarsVo;
import com.ruoyi.project.duties.tasksCars.mapper.TasksCarsMapper;
import com.ruoyi.project.duties.tasksCarsLogs.domain.TasksCarsLogs;
import com.ruoyi.project.duties.tasksUpdatelog.domain.TasksUpdatelog;
import com.ruoyi.project.duties.tasksUpdatelog.service.ITasksUpdatelogService;
import com.ruoyi.project.monitor.server.domain.Sys;
import com.ruoyi.project.redisEntry.CarsLonLat;
import com.ruoyi.project.redisEntry.LonLat;
import com.ruoyi.project.system.user.domain.User;
import io.swagger.models.auth.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * 任务车辆 服务层实现
 *
 * @author admin
 * @date 2019-04-25
 */
@Service
public class TasksCarsServiceImpl implements ITasksCarsService {
    @Autowired
    private TasksCarsMapper tasksCarsMapper;
    @Autowired
    private ITasksUpdatelogService updatelogService;
    @Autowired
    private IPreproductionService preproductionService;
    Logger logger = LoggerFactory.getLogger(TasksCarsServiceImpl.class);
    @Autowired
    private TasksMapper tasksMapper;

    @Autowired
    private RedisUtils redisUtils;

    private static String startLon = null;

    private static String startLat = null;

    private static String startDistance = null;
    private static String endDistance = null;

    private static String warningDistance=null;

    static {
        Global props = Global.getInstance();
        startLon = props.getConfig("project.lon");
        startLat = props.getConfig("project.lat");
        startDistance = props.getConfig("project.startDistance");
        endDistance = props.getConfig("project.endDistance");
        warningDistance=props.getConfig("project.warningDistance");
    }

    /**
     * 查询任务车辆信息
     *
     * @param id 任务车辆ID
     * @return 任务车辆信息
     */
    @Override
    public TasksCars selectTasksCarsById(Long id) {
        return tasksCarsMapper.selectTasksCarsById(id);
    }

    /**
     * 查询任务车辆列表
     *
     * @param tasksCars 任务车辆信息
     * @return 任务车辆集合
     */
    @Override
    public List<TasksCars> selectTasksCarsList(TasksCars tasksCars) {
        return tasksCarsMapper.selectTasksCarsList(tasksCars);
    }
    /**
     * 距离当前时间有多少分钟
     *
     * @param date
     * @return
     */
    private long getMinute(Date date) {
        long minute = (new Date().getTime() - date.getTime()) / 60 / 1000;
        return minute;
    }

    /**
     * 新增任务车辆
     *
     * @param tasksCars 任务车辆信息
     * @return 结果
     */
    @Override
    public int insertTasksCars(TasksCars tasksCars) {
        return tasksCarsMapper.insertTasksCars(tasksCars);
    }

    /**
     * 修改任务车辆
     *
     * @param tasksCars 任务车辆信息
     * @return 结果
     */
    @Override
    public int updateTasksCars(TasksCars tasksCars) {
        return tasksCarsMapper.updateTasksCars(tasksCars);
    }

    /**
     * 删除任务车辆对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTasksCarsByIds(String ids) {
        return tasksCarsMapper.deleteTasksCarsByIds(Convert.toStrArray(ids));
    }

    @Override
    public BigDecimal queryCurFliangBytask(String taskId) {
        return tasksCarsMapper.queryCurFliangBytask(taskId);
    }

    @Override
    public TasksCars selectOneGoodCar(List<Long> ids) {
        return tasksCarsMapper.selectOneGoodCar(ids);
    }

    @Override
    public TasksCars selectOneBadCar(List<Long> ids) {
        return tasksCarsMapper.selectOneBadCar(ids);
    }

    @Override
    public AjaxResult removeOne(Long id, User user) {
        TasksCars tasksCars = tasksCarsMapper.selectTasksCarsById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tasksCars.setRemark(String.format("删除当前车次任务单号%s,操作人%s,删除时间%s",tasksCars.getTaskNumber(),user.getUserName(), LocalDateTime.now().format(formatter)));
        tasksCars.setUpdateBy(user.getUserName());
        if(null!=tasksCars){
            //1.先检查redis里面有没有
            TasksCars rtc = redisUtils.get(Constants.WAITE_QUEUE_PATTERN+tasksCars.getProductLine(),TasksCars.class);
            //车次相同，任务单号相同，车号相同
//            if(null!=rtc&&rtc.getCarNo().equals(tasksCars.getCarNo())&&rtc.getTaskId()==tasksCars.getTaskId()&&tasksCars.getCarCnt()==rtc.getCarCnt()){
            if(null!=rtc&&rtc.getCarNo().equals(tasksCars.getCarNo())&&rtc.getTaskId()==tasksCars.getTaskId()){
                //1.先从redis队列里面删除掉
                redisUtils.delete(Constants.WAITE_QUEUE_PATTERN+tasksCars.getProductLine());
                tasksCars.setStatus(-1);
                deleteOtherTaskcarProperties(tasksCars);
                return AjaxResult.success();
            }
            //否则已经下发到主机了，检查主机是否正在生产
            List<Preproduction> plist = preproductionService.selectPreproductionList(new Preproduction(){{
                setInnerNumber(tasksCars.getCarNo()+"");
                setTaskNumber(tasksCars.getTaskNumber());
                setTransportOrder(tasksCars.getCarCnt());
                setSqlWhere(" and (state=1 or state is null or (state in (5,2,4))) order by create_time desc");
            }});
            if(null!=plist && plist.size()>0){
                Preproduction pre = plist.get(0);
                if(null!=pre.getState()&&pre.getState()==1){
                    logger.debug("主机楼正在生产或者已经生产完成，不能删除");
                    return AjaxResult.error("主机楼正在生产，不能删除");
                }
                pre.setSyncStatus(-1);//删除标志
                pre.setSyncStatus2(-1);//删除标志
                preproductionService.updatePreproduction(pre);
            }
            //删除成功了
            tasksCars.setStatus(-1);
            deleteOtherTaskcarProperties(tasksCars);
            PushUtils.pushSendTasking("您的订单已失效",tasksCars.getNotifyId(),null);
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @Override
    @Transactional(value = "masterTransactionManager")
    public AjaxResult arrived(Long id, User sysUser) {
        TasksCars tasksCars = tasksCarsMapper.selectTasksCarsById(id);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        tasksCars.setRemark(String.format("手动到达操作:当前车次任务单号%s,操作人%s,到达时间%s",tasksCars.getTaskNumber(),sysUser.getUserName(), LocalDateTime.now().format(formatter)));
        tasksCars.setUpdateBy(sysUser.getUserName());
        if(null!=tasksCars&&null!=tasksCars.getTaskId()&&tasksCars.getStatus()!=null&&tasksCars.getStatus()==Constants.TASK_STATUS_ING){
            DriverCar dc= redisUtils.get(Constants.DRVCAR_PATTEN+tasksCars.getNotifyId(),DriverCar.class);
            if(null==dc){
                return AjaxResult.error("无法到达，此车没签到");
            }
            RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+dc.getNotifyId(),RuningCar.class);
            if(null==runingCar||runingCar.getCarId()==null||runingCar.getCarNo()==null){
                return AjaxResult.error("无法到达，此车没签到").put("code",-100);
            }
            if(null==tasksCars.getStationStatus()||!tasksCars.getStationStatus().equalsIgnoreCase("OUT")){
                return AjaxResult.error("此车尚未出站称重");
            }
            runingCar.setEnding(true);
            runingCar.setTaskStatus(Constants.TASK_CAR_STATUS_COMPLATE);//完成
            //终点标志Y
            tasksCars.setIsEnd(Constants.YES);
            tasksCars.setInEnd(true);
            //终点时间
            tasksCars.setEndTime(new Date());
            //任务标志-完成
            tasksCars.setStatus(Constants.TASK_STATUS_COMPLATE);
            //车辆不在站内并且是第一辆车的情况下更新task的终点坐标
            if(tasksCars.getCarCnt()!=null&&tasksCars.getCarCnt()==1&&!runingCar.isInner()){
                if(null!=runingCar.getLat()&&null!=runingCar.getLng()){
                    tasksCars.setLat(runingCar.getLat().toString());
                    tasksCars.setLng(runingCar.getLng().toString());
                }else{
                    return AjaxResult.error("定位信息出错,请稍后重试");
                }
                //如果到达目的地，重新定位目的地坐标
                Tasks tasks = tasksMapper.selectTasksById(tasksCars.getTaskId()+"");
                tasks.setLat(runingCar.getLat()+"");
                tasks.setLon(runingCar.getLng()+"");
                tasksMapper.updateTasks(tasks);
                RedisDomainUtils.setRedisTaskDomain(tasks);
            }
            tasksCarsMapper.updateTasksCars(tasksCars);
            RedisDomainUtils.setRedisTaskCarDomain(tasksCars);
            redisUtils.set(Constants.RUNINGCAR_PATTEN+runingCar.getNotifyId(),runingCar,-1);
            return AjaxResult.success();
        }
        return AjaxResult.error();
    }

    @Override
    public List<TasksCars> selectTodayLog(List<Long> ids) {
        return tasksCarsMapper.selectTodayLog(ids);
    }

    @Override
    public List<TasksCarsReport> selectTaskCarsReport(TasksCars tasksCarsReport) {
        return tasksCarsMapper.selectTaskCarsReport(tasksCarsReport);
    }

    /**
     * 删除该任务的其他属性
     * */
    public void deleteOtherTaskcarProperties(TasksCars tasksCars){
        //2.删除redis其他的值
        RedisDomainUtils.setRedisTaskCarDomain(tasksCars,true);
        //3.先更新任务状态的累计放量，然后删除数据库
        Tasks tasks = tasksMapper.selectTasksById(tasksCars.getTaskId()+"");
        if(null!=tasks){
            //添加任务单的修改记录
            tasks.setCarCnt(tasks.getCarCnt()-1);
            TasksUpdatelog tlog = new TasksUpdatelog();
            tlog.setTaskId(Long.valueOf(tasks.getId()));
            tlog.setTaskName(tasks.getName());
            tlog.setOriCarcnt(tasks.getCarCnt());
            tlog.setCarcnt(tasks.getCarCnt());
            tlog.setOriFangliang(tasks.getLjfangliang());
            tlog.setFangliang(tasks.getLjfangliang());
            tlog.setRemark(tasksCars.getRemark());
            tlog.setCreateBy(tasksCars.getUpdateBy());
            updatelogService.insertTasksUpdatelog(tlog);
            tasksMapper.updateTasks(tasks);
        }
        tasksCarsMapper.updateTasksCars(tasksCars);
        logger.debug("删除taskcar成功\n");
        RuningCar runingCar = redisUtils.get(Constants.RUNINGCAR_PATTEN+tasksCars.getNotifyId(),RuningCar.class);
        if(null!=runingCar){
            runingCar.setTaskStatus(Constants.TASK_STATUS_INIT);
            redisUtils.set(Constants.RUNINGCAR_PATTEN+tasksCars.getNotifyId(),runingCar,-1);
        }
    }

}
