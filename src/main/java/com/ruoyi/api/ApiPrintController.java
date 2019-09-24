package com.ruoyi.api;


import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import io.netty.util.Constant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

@RequestMapping("/webapp")
@RestController
public class ApiPrintController {
    @Autowired
    private RedisUtils redisUtils;
    private Logger logger = LoggerFactory.getLogger(ApiPrintController.class);

    /**
     * 出门称重后放入redis
     * 打印机获取后删除redis
     * **/
    @RequestMapping("/printList")
    @Transactional(transactionManager = "masterTransactionManager")
    public AjaxResult printList(HttpServletRequest request){
        List<PrintDomain> list = redisUtils.getLists(Constants.PRINT_PATTERN, PrintDomain.class);
        if(null!=list&&list.size()>0){
            list.forEach(item->{
                RedisDomainUtils.setRedisPrintDomain(item,true);
            });
        }
        return AjaxResult.success().put("list",list);
    }

}
