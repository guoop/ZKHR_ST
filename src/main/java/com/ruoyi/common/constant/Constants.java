package com.ruoyi.common.constant;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.StringUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.InetAddress;

/**
 * 通用常量信息
 * 
 * @author admin
 */
public class Constants
{
    /**
     * UTF-8 字符集
     */
    public static final String UTF8 = "UTF-8";

    /**
     * 通用成功标识
     */
    public static final String SUCCESS = "0";

    /**
     * 通用失败标识
     */
    public static final String FAIL = "1";

    /**
     * 登录成功
     */
    public static final String LOGIN_SUCCESS = "Success";

    /**
     * 注销
     */
    public static final String LOGOUT = "Logout";

    /**
     * 登录失败
     */
    public static final String LOGIN_FAIL = "Error";

    /**
     * 自动去除表前缀
     */
    public static final String AUTO_REOMVE_PRE = "true";

    /**
     * 当前记录起始索引
     */
    public static final String PAGE_NUM = "pageNum";

    /**
     * 每页显示记录数
     */
    public static final String PAGE_SIZE = "pageSize";

    /**
     * 排序列
     */
    public static final String ORDER_BY_COLUMN = "orderByColumn";

    /**
     * 排序的方向 "desc" 或者 "asc".
     */
    public static final String IS_ASC = "isAsc";


    /**
     * 汽车别名
     */
//    public static final String CAR="SHANGTONG_CAR_";
//    public static final String CAR_LIST="SHANGTONG_CAR_LIST";

    /**已经登陆车辆*/
//    public static final String RUNING_CAR ="SHANGTONG_RUNING_CAR_";//used
//    /**已经登陆车辆列表*/
//    public static final String RUNING_CARLIST ="SHANGTONG_RUNING_CAR_LIST";//used

    /**
     * 任务_汽车
     */
//    public static final String TASKS_CARS="SHANGTONG_TASKS_CARS_";//used
    /**
     * 任务_汽车列表
     */
//    public static final String TASKS_CARSLIST="SHANGTONG_TASKS_CARS_LIST";//used

    /**
     * 任务
     * */
//    public static final String TASK = "SHANGTONG_TASK_"; //used
    /**
     * 任务列表
     * */
//    public static final String TASKLIST = "SHANGTONG_TASK_LIST";//used
//
//    public static final String DRIVER_CARS ="SHANGTONG_DRIVER_CAR_" ;//used
//    public static final String DRIVER_CARSLIST ="SHANGTONG_DRIVER_CAR_LIST" ;//used




    public static final Integer pagesize = 10;
    public static final Integer TASK_STATUS_INIT = 0; //任务待开始
    public static final Integer TASK_STATUS_ING = 1; //任务进行中
//    public static final Integer TASK_STATUS_ING = 2; //任务.。。
    public static final Integer TASK_STATUS_COMPLATE = 3; //任务已完成
    public static final Integer TASK_STATUS_CANCEL = 4; //任务已完成


    public static final Integer TASK_CAR_STATUS_INIT = 0; //任务待开始
    public static final Integer TASK_CAR_STATUS_IN = 1; //进站
    public static final Integer TASK_CAR_STATUS_OUT = 2; //出战
    public static final Integer TASK_CAR_STATUS_COMPLATE = 3; //任务已完成


    //特殊小型车
    public static final Integer CAR_TYPE_SPETIAL_LITTLE_CAR = 100;
    //小型车
    public static final Integer CAR_TYPE_LITTLE_CAR = 150;
    //较低中型车
    public static final Integer CAR_TYPE_SPETIAL_MIDDLE_CAR = 200;
    //中型车
    public static final Integer CAR_TYPE_MIDDLE_CAR = 250;
    //跨线特殊车
    public static final Integer CAR_TYPE_SPETIAL_BIG_CAR = 300;
    //大型车
    public static final Integer CAR_TYPE_BIG_CAR = 350;
    public static final String YES ="Y";
    public static final String NO ="N";


    /**所需要的列表信息
     * 1.签到之后说明这个车辆就上班了。上班后就需要上传定位，此时就是【所有上班车辆的列表】放入redis
     * **/



    /**任务redis的prekey--SHANGTONG_PATTERN_*/
    public static final String REDIS_WEBSITE_PRE = StringUtils.isBlank(Global.getConfig("project.redis_website_pre"))||"null".equalsIgnoreCase(Global.getConfig("project.redis_website_pre"))?"":Global.getConfig("project.redis_website_pre");
    //TOKEN
    public static final String APP_TOKEN = REDIS_WEBSITE_PRE+"APP_TOKEN_";
    //验证码
    public static final String MOBILE_CODE = REDIS_WEBSITE_PRE+"SHANGTONG_MOBILE_CODE_";
    public static final String BANG_NO ="SHANGTONG_BANG_" ;
    public static final String TASK_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_TASK_";
    /**执行中的车辆redis的prekey*/
    public static final String TASKINGCAR_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_TSKINGCAR_";
    public static final String PRIORITY_QUEUE = REDIS_WEBSITE_PRE+"ST_PTN_PRIORITYQUEUE_";
    public static final String DRIVER_PATTERN =  REDIS_WEBSITE_PRE+"ST_PTN_DRIVER_";
    /**车辆-司机redis的prekey*/
    public static final String DRVCAR_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_DVCAR_";
    /**行驶中车辆redis的prekey*/
    public static final String RUNINGCAR_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_RUNINGCAR_";
    /**总共的车辆redis的prekey*/
    public static final String CAR_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_CAR_";
    /**CAR_USEING_ID*/
    public static final String CARUSING_PATTEN = REDIS_WEBSITE_PRE+"ST_PTN_CARUSING_";
    /**业务员工登陆的token==》prekey*/
    public static final String SALER_PATTERN = REDIS_WEBSITE_PRE+"ST_SALER_TOKEN_";
    /**业务员工登陆的token==》prekey*/
    public static final String PRINT_PATTERN = REDIS_WEBSITE_PRE+"ST_PRINT_";
    /**redis排队队列列表*/
    public static final String WAITE_QUEUE_PATTERN = REDIS_WEBSITE_PRE+"ST_WAITE_QUEUE_";

    /**主机状态*/
    public static final String PRODUCT_LINE_PATTERN=REDIS_WEBSITE_PRE+"ST_PTN_PRODUCTLINE_";

    /**主机生产状态列表*/
    public static final String PRODUCT_LINE_TASKING_PATTERN =REDIS_WEBSITE_PRE+ "ST_PRODUCT_LINE_TASKING_";
    public static final String CAR_SIGN_LOG_PATTERN =REDIS_WEBSITE_PRE+ "ST_CAR_SIGNLOG_";



    public static final int YELLOW_WARNING_VALUE = 1;
    public static final int RED_WARNING_VALUE = 0;
    public static final BigDecimal PRE_WAITE_TIME = new BigDecimal("1.5").setScale(1, RoundingMode.HALF_UP);
    public static final Integer OFF = 1;
    public static final Integer ON = 0;

    public static final Integer QUEUE_INIT = 0;//排队
    public static final Integer QUEUE_WAITING = 1;//准备
    public static final Integer QUEUE_ING = 2;//接料中
    public static final Integer COBBLESTONE = 1;
    public static final Integer NO_COBBLESTONE =1 ;
    public static final Integer QING_STONE = 0;
    public static final String POST_CODE_DIAODU ="DIAODU" ;
    public static final String POST_CODE_BANGFANG ="bangfang" ;
    public static final Integer TASK_CNT_TO_DISPATH = 6;
    public static final Integer DELETE_FLAG = -1;//删除标志位
    public static final String TASK_NUMBER_SHANGJIANG ="BL_砂浆" ;
    public static final long TIME_TO_DROPTASK = (long)3*1000*60;
    public static final String CamelCase ="camelCase" ;
    public static final String NOT_CAMELCASE = "notCamelCase";
    public static final String GROSS_WEIGHT ="gross_weight" ;
    public static final String CAR_WEIGHT ="car_weight" ;

    public static final String LOCATION_ACCESS_TOKEN = "accessToken";
    public static final String IS_BUPIAO = REDIS_WEBSITE_PRE+"BUPIAO";//能否补票
}
