package com.ruoyi.api;


import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.driverCar.service.IDriverCarService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.List;

@RequestMapping("/api/task")
@RestController
public class ApiTaskController {
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private IDriverCarService driverCarService;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ITasksCarsService tasksCarsService;
    /**
     * app登陆接口
     * */
    @RequestMapping("/comment")
    public AjaxResult tasks(HttpServletRequest request, @RequestParam String urls,@RequestParam String taskId,@RequestParam String content){
        List<TasksCars> list =  tasksCarsService.selectTasksCarsList(new TasksCars(){{setTaskId(Long.valueOf(taskId));setNotifyId(redisUtils.getLoginDriverCar().getNotifyId());}});
        if(list!=null&&list.size()>0){
            TasksCars taskcar = list.get(0);
            taskcar.setComment(content);
            taskcar.setUrls(urls);
            tasksCarsService.updateTasksCars(taskcar);
        }
        /**出门逻辑*/
       return AjaxResult.success();
    }
}
