package com.ruoyi.project.Jpush.impl;

import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import com.ruoyi.common.config.Global;
import com.ruoyi.project.Jpush.JPushService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

@Component
public class JPushServiceImpl implements JPushService {

    private static String APPKET = null;

    private static String MASTERSECRET = null;

    private static JPushClient jPushClient = null;//通知默认保留24小时。
    private Logger logger = LoggerFactory.getLogger(JPushServiceImpl.class);

    static {

        Global props = Global.getInstance();
        APPKET = props.getConfig("jpush.appkey");
        MASTERSECRET = props.getConfig("jpush.mastersecret");
        jPushClient = new JPushClient(MASTERSECRET, APPKET);//通知默认保留24小时。
    }

    /**
     * 给指定唯一商户app推送通知
     *
     * @param registrationId     唯一用户id，需调用Android的接口获取当前商户app的唯一id
     * @param notification_alert 推送内容(必填)
     * @param notification_title 推送标题(可选)将代替app名称
     * @param map                此map为未特定需求所设置 key为所要求的的字段名称 value 是说需要的值
     * @return 0推送失败   1推送成功
     */
    @Override
    public int sendToRegistrationId(String registrationId, String notification_alert, String notification_title,
                                    Map map) {
        int result = 0;
        try {
            PushPayload pushPayload = buildPushObjectWithRegistrationId(registrationId,
                    notification_alert, notification_title, map);

            PushResult pushResult = jPushClient.sendPush(pushPayload);  //发送推送对象
            if(null==pushPayload){
                logger.debug("推送对象为空，不能完成推送");
                return 0;
            }
            if (pushResult.getResponseCode() == 200) {  //状态码等于200 为成功
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * 推送给指定商户app
     *
     * @param notification_alert 推送内容(必填)
     * @param notification_title 推送标题(可选)将代替app名称
     * @param map                此map为未特定需求所设置 key为所要求的的字段名称 value 是说需要的值
     * @param alias 接收商户规则
     * @return 0推送失败   1推送成功
     */
    @Override
    public int sendPushObject_android_and_ios(String notification_alert, String notification_title, Map map, String... alias) {
        int result = 0;
        try {
            PushPayload pushPayload = buildPushObjectWithAll(notification_alert,
                    notification_title, map, alias);
            if(null==pushPayload){
                logger.debug("推送对象为空，不能完成推送");
                return 0;
            }
            PushResult pushResult = jPushClient.sendPush(pushPayload);  //发送推送对象
            //System.out.println(pushResult);
            if (pushResult.getResponseCode() == 200) {  //状态码等于200 为成功
                result = 1;
            }
        } catch (APIConnectionException e) {
            e.printStackTrace();
        } catch (APIRequestException e) {
            e.printStackTrace();
        }

        return result;
    }


    /**
     * 建立以唯一设备标识符推送的对象
     *
     * @param registrationId     唯一设备标识
     * @param notification_alert 通知内容
     * @param notification_title 通知标题
     * @param map                扩展字段对象
     * @return 返回推送对象
     */
    private PushPayload buildPushObjectWithRegistrationId(String registrationId, String notification_alert, String notification_title,
                                                          Map map) {


        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.registrationId(registrationId)).setOptions(Options.newBuilder()  //设置推送可选项
                        .setApnsProduction(true)  //True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。JPush 官方 API LIbrary (SDK) 默认设置为推送 “开发环境”。
                        .build())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(setAndroidExtraFields(AndroidNotification.newBuilder()
                                .setAlert(notification_alert)   //设置通知内容（必填）
                                .setTitle(notification_title), map))

                        //指定当前推送的iOS通知
                        .addPlatformNotification(setIOSExtraFields(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_alert)
                                //直接传alert
                                //此项是指定此推送的badge（应用角标）自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf"), map))

                        //指定当前推送的winPhone通知
                        /*.addPlatformNotification(WinphoneNotification.newBuilder()
                              .setAlert(notification_alert)
                              //.setTitle(""))  //设置通知标题（可选）此标题将取代显示app名称的地方
                            .build())*/
                        .build())
                .build();
    }

    private AndroidNotification setAndroidExtraFields(AndroidNotification.Builder builder, Map map) {
        AndroidNotification androidNotification = null;
        if (map == null || map.isEmpty()) {
            androidNotification = builder.addExtra("nonekey", "").build();
        } else {
            Set set = map.keySet();
            Iterator iterator = set.iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {

                key = iterator.next().toString();
                value = map.get(key).toString();
                builder = builder.addExtra(key, value);
            }
            androidNotification = builder.build();
        }
        return androidNotification;
    }

    private IosNotification setIOSExtraFields(IosNotification.Builder builder, Map map) {
        IosNotification androidNotification = null;
        if (map == null || map.isEmpty()) {
            androidNotification = builder.addExtra("nonekey", "").build();
        } else {
            Set set = map.keySet();
            Iterator iterator = set.iterator();
            String key = null;
            String value = null;
            while (iterator.hasNext()) {
                key = iterator.next().toString();
                value = map.get(key).toString();
                builder = builder.addExtra(key, value);
            }
            androidNotification = builder.build();
        }
        return androidNotification;
    }

    /**
     * 建立推送所有用户的推送对象
     *
     * @param notification_alert 通知内容
     * @param notification_title 通知标题
     * @param map                扩展字段对象
     * @return 返回推送对象
     */
    private PushPayload buildPushObjectWithAll(String notification_alert,
                                               String notification_title, Map map, String... alias) {
        return PushPayload.newBuilder()
                //指定要推送的平台，all代表当前应用配置了的所有平台，也可以传android等具体平台
                .setPlatform(Platform.all())
                //指定推送的接收对象，all代表所有人，也可以指定已经设置成功的tag或alias或该应应用客户端调用接口获取到的registration id
                .setAudience(Audience.alias(alias)).setOptions(Options.newBuilder()  //设置推送可选项
                        .setApnsProduction(true)  //True 表示推送生产环境，False 表示要推送开发环境；如果不指定则为推送生产环境。JPush 官方 API LIbrary (SDK) 默认设置为推送 “开发环境”。
                        .build())
                //jpush的通知，android的由jpush直接下发，iOS的由apns服务器下发，Winphone的由mpns下发
                .setNotification(Notification.newBuilder()
                        //指定当前推送的android通知
                        .addPlatformNotification(setAndroidExtraFields(AndroidNotification.newBuilder()
                                        .setAlert(notification_alert)   //设置通知内容（必填）
                                        .setTitle(notification_title),    //设置通知标题（可选）
                                map))

                        //指定当前推送的iOS通知
                        .addPlatformNotification(setIOSExtraFields(IosNotification.newBuilder()
                                //传一个IosAlert对象，指定apns title、title、subtitle等
                                .setAlert(notification_alert)
                                //直接传alert
                                //此项是指定此推送的badge（应用角标）自动加1
                                .incrBadge(1)
                                //此字段的值default表示系统默认声音；传sound.caf表示此推送以项目里面打包的sound.caf声音来提醒，
                                // 如果系统没有此音频则以系统默认声音提醒；此字段如果传空字符串，iOS9及以上的系统是无声音提醒，以下的系统是默认声音
                                .setSound("sound.caf"), map))

                        //指定当前推送的winPhone通知
                        /*.addPlatformNotification(WinphoneNotification.newBuilder()
                              .setAlert(notification_alert)
                              //.setTitle(""))  //设置通知标题（可选）此标题将取代显示app名称的地方
                            .build())*/
                        .build())
                .build();
    }

    public static void main(String[] args) {
        JPushServiceImpl jPushService = new JPushServiceImpl();
        String notification_title = "Jpush";
        String notification_alert = "快过五一啦";
        String extrasparam = "1095628903116656640";
//		jPushService.sendToRegistrationId("1095628903116656640",notification_alert,notification_title,extrasparam);
//		jPushService.sendToAll(notification_alert,notification_title,extrasparam);
    }

}
