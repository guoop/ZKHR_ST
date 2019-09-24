package com.ruoyi.project.locator.common;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.Md5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 定位器使用的常量
 */
public class LocatorConstant {


    @Autowired
    RestTemplate restTemplate;
    /**
     * 风控在线网关
     */
    public static final String FKZX_API = "http://open.gumigps.com/api";
    /**验证*/
    public static final String AUTH = "/auth";

    /** 创建用户*/
    public static final String USER = "/user";

    /**获取用户数*/
    public static final String GET_ACC_TREE = "/account/tree";

    /**按用户分页获取设备列表GET*/
    public static final String GET_DEVICE_LIST = "/device";

    /**设备的实时位置信息*/
    public static final String LOCATION_INFO = "/device/location";

    /**统计设备数量*/
    public static final String COUNT_DEVICE_NUM = "/device/status/statistic";

    /**查询设备状态*/
    public static final String DEVICE_STATUS = "/device/status";

    /**按车查询设备状态*/
    public static final String VEHICLE_STATUS = "/vehicle/status";

    /**新增车辆并绑定设备*/
    public static final String VEHICLE_ADD = "/vehicle";

    /**设备批量解绑设备*/
    public static final String VEHICLE_UNBIND = "/device/unbind/vehicle";

    /**批量创建或修改设备*/
    public static final String POST_CREATE_DEVICE = "/device";

    /**有线断油电指令发送*/
    public static final String GET_INSTRUCTION_RELAY = "/instruction/relay";

    /**无线设备定位频率设置*/
    public static final String INSTRUCTION_RATE = "/instruction/rate";

    /**查看指令执行结果*/
    public static final String POST_INSTRUCTION_RELAY = "/instruction/relay";

    /**获取单台设备历史轨迹*/
    public static final String TRACK_HISTORY = "/device/track/history";

    /**获取单台设备报警记录*/
    public static final String ALARM_RECORD = "/device/alarm";

    /**里程统计*/
    public static final String COUNT_MILES = "/device/miles";



}