package com.ruoyi.api;

import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.project.Jpush.JPushService;
import com.ruoyi.project.duties.taskNotification.domain.TaskNotification;
import com.ruoyi.project.duties.taskNotification.service.ITaskNotificationService;

import java.util.HashMap;
import java.util.Map;

public class PushUtils {
    private static JPushService jPushService = SpringUtils.getBean(JPushService.class);
    private static ITaskNotificationService taskNotificationService = SpringUtils.getBean(ITaskNotificationService.class);
    //推送新任务消息无参数
    public static void pushSendTasking(String content, String alias, Map map){
            jPushService.sendPushObject_android_and_ios(content,"新消息",map,alias);
            TaskNotification taskNotification =  new TaskNotification();
            taskNotification.setNotifyId(Long.valueOf(alias));
            taskNotification.setMsg(content);
            taskNotification.setMsgtype("系统消息");
            taskNotificationService.insertTaskNotification(taskNotification);

    }

    //推送新任务消息无参数
    public static void pushSendTasking(String content,String type,String alias, Map... maps){
        if(null!=alias){
            Map map = new HashMap();
            if(null!=maps&&maps.length>0){
                map = maps[0];
            }
            jPushService.sendPushObject_android_and_ios(content,"新消息",map,alias);
            TaskNotification taskNotification =  new TaskNotification();
            taskNotification.setNotifyId(Long.valueOf(alias));
            taskNotification.setMsg(content);
            taskNotification.setMsgtype(type);
            taskNotificationService.insertTaskNotification(taskNotification);
        }
    }
}
