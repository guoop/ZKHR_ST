package com.ruoyi.project.Jpush;

import java.util.Map;

/**
 * 此接口为极光推送的实现，用于对商户app的推送
 *
 * @author chenfei
 */
public interface JPushService {

    /**
     * 给指定唯一商户app推送通知
     *
     * @param registrationId     唯一用户id，需调用Android的接口获取当前商户app的唯一id
     * @param notification_alert 推送内容(必填)
     * @param notification_title 推送标题(可选)将代替app名称
     * @param map                此map为未特定需求所设置 key为所要求的的字段名称 value 是说需要的值
     * @return 0推送失败   1推送成功
     */
    public int sendToRegistrationId(String registrationId, String notification_alert, String notification_title, Map map);

    /**
     * 推送给所有商户app
     *
     * @param notification_alert 推送内容(必填)
     * @param notification_title 推送标题(可选)将代替app名称
     * @param map                此map为未特定需求所设置 key为所要求的的字段名称 value 是说需要的值
     * @param alias              接收商户规则
     * @return 0推送失败   1推送成功
     */
    public int sendPushObject_android_and_ios(String notification_alert, String notification_title, Map map, String... alias);


}
