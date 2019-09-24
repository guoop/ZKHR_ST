package com.ruoyi.api;


import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.appVersion.domain.AppVersion;
import com.ruoyi.project.cars.appVersion.service.IAppVersionService;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
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

@RequestMapping("/api")
@RestController
public class ApiAppVersionController {
    @Autowired
    private IAppVersionService appVersionService;
    @RequestMapping("/getVersion")
    public AjaxResult getVersion(HttpServletRequest request){
        AppVersion app=new AppVersion();
        List<AppVersion> list = appVersionService.selectAppVersionList(new AppVersion(){{setSqlWhere(" and 1=1 order by create_time desc limit 1");}});
        if(null!=list&&list.size()>0){
            app = list.get(0);
        }
        AjaxResult ajs = AjaxResult.success();
        ajs.remove("msg");
        ajs.remove("code");
        return ajs.put("target_size",StringUtils.isBlank(app.getTarget_size())?"":app.getTarget_size())
                .put("update",app.getUpdate())
                .put("new_version",app.getNew_version())
                .put("constraint",app.getConstraint())
                .put("apk_file_url",app.getApk_file_url())
                .put("update_log",app.getUpdate_log())
                .put("new_md5",app.getNew_md5());
    }
}
