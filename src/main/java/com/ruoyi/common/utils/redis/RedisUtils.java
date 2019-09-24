package com.ruoyi.common.utils.redis;

import com.alibaba.druid.support.json.JSONUtils;
import com.google.gson.*;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.ServletUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.carSignlog.domain.CarSignlog;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driverCar.domain.DriverCar;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.saller.saler.domain.Saler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Redis工具类
 *
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-07-17 21:12
 */
@Component
@Configuration
public class RedisUtils {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;
    @Autowired
    private ValueOperations<String, String> valueOperations;
    @Autowired
    private HashOperations<String, String, Object> hashOperations;
    @Autowired
    private ListOperations<String, Object> listOperations;
    @Autowired
    private SetOperations<String, Object> setOperations;
    @Autowired
    private ZSetOperations<String, Object> zSetOperations;
    private Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /**  默认过期时长，单位：秒 */
    public final static long DEFAULT_EXPIRE = 60 * 60 * 24 * 365;
    /**  不设置过期时长 */
    public final static long NOT_EXPIRE = -1;
    private final static Gson gson = new GsonBuilder()
            .serializeNulls()
            .create();

    public void set(String key, Object value, long expire){
        valueOperations.set(key, toJson(value));
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
    }

    public void set(String key, Object value){
        set(key, value, DEFAULT_EXPIRE);
    }

    public <T> T get(String key, Class<T> clazz, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value == null ? null : fromJson(value, clazz);
    }

    public <T> T get(String key, Class<T> clazz) {
        return get(key, clazz, NOT_EXPIRE);
    }

    public String get(String key, long expire) {
        String value = valueOperations.get(key);
        if(expire != NOT_EXPIRE){
            redisTemplate.expire(key, expire, TimeUnit.SECONDS);
        }
        return value;
    }

    public String get(String key) {
        return get(key, NOT_EXPIRE);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteAll(String pattern) {
        Set<String> keys = redisTemplate.keys(pattern);
        redisTemplate.delete(keys);
    }
    /**appp
     * Object转成JSON数据
     */
    private String toJson(Object object){
        if(object instanceof Integer || object instanceof Long || object instanceof Float ||
                object instanceof Double || object instanceof Boolean || object instanceof String){
            return String.valueOf(object);
        }
        return gson.toJson(object);
    }

    /**
     * JSON数据，转成Object
     */
    private <T> T fromJson(String json, Class<T> clazz){
        return gson.fromJson(json, clazz);
    }

    public <T> List<T> getList(String key,Class<T> t){
        return jsonToBeanList(get(key),t);
    }

    private static <T> List<T> jsonToBeanList(String json, Class<T> t) {
        if(StringUtils.isBlank(json)){
            return null;
        }
        List<T> list = new ArrayList<>();
        JsonParser parser = new JsonParser();
        JsonArray jsonarray = parser.parse(json).getAsJsonArray();
        for (JsonElement element : jsonarray
        ) {
            list.add(new Gson().fromJson(element, t));
        }
        return list;
    }


    /**
     * 获取当前登陆用户
     * */
    public DriverCar getLoginDriverCar(){
        return get(Constants.APP_TOKEN+getToken(),DriverCar.class);
    }
    /**
     * 获取当前业务员用户
     * */
    public Car getCurrentOwner(){
        return get(Constants.APP_TOKEN+getToken(), Car.class);
    }

    /**
     * 获取当前业务员用户
     * */
    public Driver getCurrentDriver(){
        return get(Constants.APP_TOKEN+getToken(), Driver.class);
    }
    public String getToken(){
        String token = ServletUtils.getRequest().getHeader("Access-Token");
        if(StringUtils.isBlank(token)){
            token = ServletUtils.getRequest().getParameter("Access-Token");
        }
        if(StringUtils.isEmpty(token)){
            logger.debug("token======\n"+token);
            throw new BaseException("请登录");
        }
        return token;
    }

    /**
     * 获取对象列表,前提是不能有重复的pre_key
     * */
    public <T> List<T> getLists(String pattern,Class<T> t){
        if(!pattern.contains("*")){
            pattern+="*";
        }
        List<T> list = new ArrayList<>();
        Set<String> keys = redisTemplate.keys(pattern);
        if(null!=keys&&keys.size()>0){
            keys.forEach(item->{
                T s = get(item,t);
                if(null!=s){
                    list.add(s);
                }
            });
        }
        return list;
    }

    /**
     * 对象是否存在
     * */
    public boolean exists(String key,String id){
        return StringUtils.isNotEmpty(get(key+id));
    }

    /**列表是否存在*/
    public boolean existOne(String pattern){
        Set<String> keys = redisTemplate.keys(pattern);
        return null!=keys&&keys.size()>0;
    }



    /**
     * 获取终点车辆个数
     * */
    public int getEndPointCarCnt(Tasks tasks) {
        int ret = 0;
        List<TasksCars> list = getLists(Constants.TASKINGCAR_PATTEN,TasksCars.class);
        for (TasksCars tcar:list) {
            if(null!=tcar&&null!=tcar.getStatus()&&null!=tcar.getTaskId()&&tcar.getTaskId().equals(Long.valueOf(tasks.getId()))&&tcar.isInEnd()){
                ret++;
            }
        }
        return ret;
    }

    /**
     * 获取应今日上班车辆
     * **/
    public String getTodayCarNos(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd");
        String suffixTime = sdf.format(new Date());
        CarSignlog carSignlog = this.get(Constants.CAR_SIGN_LOG_PATTERN+suffixTime,CarSignlog.class);
        if(null!=carSignlog){
            return carSignlog.getCarNos();
        }
        return null;
    }
}
