package com.ruoyi.api.front;


import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.FrontDispatherEntity;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.api.domain.TasksCarsEntity;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.monitor.server.domain.Sys;
import com.ruoyi.project.saller.saler.domain.Saler;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.scanner.Constant;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ListIterator;

import org.springframework.stereotype.Controller;

//@RestController
@Controller
public class ApiWebappController extends BaseController {
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ICarService carService;

    /**
     * 返回一个测试页面
     */
    @GetMapping("webapp/test")
    public String test() {
        return "webapp/test";
    }

    /**
     * webapp-登录
     */
    @GetMapping("webapp/login")
    public String login() {
        return "webapp/login";
    }

    /**
     * webapp-首页
     */
    @GetMapping("webapp/iframe")
    public String iframe() {
        return "webapp/iframe";
    }

    /**
     * 业务经理-下单
     */
    @GetMapping("webapp/manager")
    public String manager() {
        return "webapp/manager";
    }

    /**
     * 业务经理-暂停任务
     */
    @GetMapping("webapp/manager-stop")
    public String managerStop() {
        return "webapp/manager-stop";
    }

    /**
     * 实验室-任务单配比
     */
    @GetMapping("webapp/laboratory")
    public String laboratory() {
        return "webapp/laboratory";
    }

    /**
     * 实验室-配比单管理
     */
    @GetMapping("webapp/labomix")
    public String labomix() {
        return "webapp/labomix";
    }


}