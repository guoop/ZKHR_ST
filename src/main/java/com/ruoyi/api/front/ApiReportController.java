package com.ruoyi.api.front;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.api.ApiTasksCarsController;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.common.utils.text.Convert;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.jobs.TaskJob;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.lines.productLine.domain.ProductLine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("/web/report")
public class ApiReportController extends BaseController {
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private TaskJob taskJob;
    @Autowired
    private ICarService carService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private IPreproductionService preproductionService;
    @Autowired
    private ApiTasksCarsController apiTasksCarsController;
    private Logger logger = LoggerFactory.getLogger(ApiReportController.class);

    /**按照时间查询车辆时间段拉的次数*/
    @RequestMapping("/getCarcntByTime")
    public AjaxResult getCarcntByTime(HttpServletRequest request,String taskId){
        return AjaxResult.success();
    }

}
