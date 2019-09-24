package com.ruoyi.api;


import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import com.ruoyi.project.duties.taskNotification.service.ITaskNotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/api")
@RestController
public class ApiMsgController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ITaskNotificationService notificationService;
    /**
     * app登陆接口
     * */
    @RequestMapping("/msgList")
    public AjaxResult msgList(HttpServletRequest request){
        DriverCar driverCar = redisUtils.getLoginDriverCar();
        List<TaskNotification> msgList = notificationService.selectTaskNotificationList(new TaskNotification(){{setNotifyId(Long.valueOf(driverCar.getNotifyId()));}});
        /**出门逻辑*/
       return AjaxResult.success().put("data",msgList);
    }
}
