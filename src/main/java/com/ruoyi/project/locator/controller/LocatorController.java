package com.ruoyi.project.locator.controller;

import com.google.common.collect.Lists;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.LogUtils;
import com.ruoyi.common.utils.Md5Utils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.aspectj.lang.enums.BusinessType;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.locator.common.GeoUtil;
import com.ruoyi.project.locator.common.LocatorConstant;
import com.ruoyi.project.system.user.service.IUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.awt.dnd.DropTarget;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/locator")
public class LocatorController extends BaseController {

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private ICarService carService;

    @Autowired
    private IUserService iUserService;
    @Autowired
    private RedisUtils redisUtils;

    /**
     * 创建用户
     * @return
     */
    public AjaxResult createAcc(){
        String token = getToken();
        //iUserService.selectUserById()
        return error();
    }
    @RequestMapping("/accTree")
    public AjaxResult getAccTree(){
        //Map<String,Object> map = restTemplate.getForObject(LocatorConstant.FKZX_API + LocatorConstant.AUTH,Map.class,m);
        String token = getToken();
        if(null != token){
            System.out.println("请求token成功");
            Map<String,Object> par = new HashMap<>();
            par.put("accessToken",token);
            //ResponseEntity<Map>  result  = restTemplate.getForEntity(LocatorConstant.FKZX_API+LocatorConstant.GET_ACC_TREE,Map.class,par);
             Map  result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.GET_ACC_TREE+"?accessToken="+token,Map.class);
            if(result.get("code").equals(0)){
                System.out.println(result);
                return  success();
            }
            return error(result.get("result").toString());
        }
       return error();
    }



    /**
     * 按用户分页获取设备列表
     */
    @RequestMapping("/deviceList")
    public AjaxResult getDeviceList( @RequestParam(value = "currentPage", required = false, defaultValue = "1")Integer currentPage ,
                                       @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize ){
        Map par = new HashMap();
        String token = getToken();
        par.put("accessToken",token);
        par.put("pageSize",pageSize);
        par.put("currentPage",currentPage);
        Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.GET_DEVICE_LIST,Map.class,par);
        if(null != result){
            LogUtils.getAccessLog().debug(result.toString());
            return success();
        }
        return error();
    }

    /**
     * 统计设备状态数量
     *@return  staticNum 静止数量
     * @return  moveNum  移动数量
     * @return  offlineNum 离线数量
     * @return  unusedNum 未用数量
     */
    @RequestMapping("/statistic")
    public AjaxResult getVehicleStatusStatistic(){
        String token = getToken();
        Map  result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.COUNT_DEVICE_NUM+"?accessToken="+token,Map.class);
        if(null != result){
            LogUtils.getAccessLog().debug(result.toString());
            return success();
        }

        return error();
    }

    /**
     * 设备的实时位置
     * @Param accessToken ,imei
     * @return  lng ,lat ,address
     * @// TODO: 2019/8/12  此方法测试获取参数，实际使用应该是调取任务写定时
     */
    @RequestMapping("testLocation")
    public AjaxResult deviceLocation(){
        String imei = "868035042512951";//实际获取方式是从车辆表中获取
        String token = getToken();
        Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.LOCATION_INFO+"?accessToken="+token+"&imei="+imei,Map.class);
        if(null != result){
            //大地坐标转百度坐标
            /*double[] ll = GeoUtil.gps84_To_bd09(Double.valueOf(result.get("lat").toString()),Double.valueOf(result.get("lng").toString()));
                    result.put("lat",ll[0]);
                    result.put("lng",ll[1]);*/
            LogUtils.getAccessLog().debug(result.toString());
            return success();
        }
        return error();
    }

    /**
     * 批量创建或修改设备
     * @do 该接口用于批量创建和修改设备
     */
    @RequestMapping("addOrModify")
    public AjaxResult addOrModify(){
        String token = redisUtils.get(Constants.LOCATION_ACCESS_TOKEN);
        List<Car> list = Lists.newArrayList();
        Car car = new Car();
        car.setImei("868035042567864").setType("EV02").setVin("WP0AA2978BL012976").setMobile("15083101898").setDeviceName("稳定器").setLicenseNumber("豫A66666").setCarOwner("GXP");
        list.add(car);
        Map par = new HashMap();
        par.put("accessToken",token);
        par.put("data",list);
        ResponseEntity<Map> result = restTemplate.postForEntity(LocatorConstant.FKZX_API+LocatorConstant.POST_CREATE_DEVICE+"?accessToken="+token,par,Map.class);
        if(result.getStatusCodeValue() == 200){
            if(Integer.valueOf(result.getBody().get("code").toString()) == 0){
                LogUtils.getAccessLog().debug(result.toString());
                return success();
            }
            return error(result.getBody().get("result").toString());
        }
        return error();
    }

    /**
     * 设备批量解绑设备
     * 该接口用于批量从车辆解绑设备。单次调用该接口，设备数不能超过500条
     */
    @RequestMapping("/vehicle/unbind")
    public AjaxResult deviceUnbind(){
        String token = getToken();
        Map params = new HashMap();
        List<String> data = Lists.newArrayList();
        data.add("868035042567864");
        params.put("accessToken",token);
        params.put("imeis",data);
        HttpEntity<Map> par = new HttpEntity<>(params);
        ResponseEntity<Map> result = restTemplate.exchange(LocatorConstant.FKZX_API+LocatorConstant.VEHICLE_UNBIND+"?accessToken="+token, HttpMethod.PUT,par,Map.class);
        if(result.getStatusCodeValue() == 200){
            if(Integer.valueOf(result.getBody().get("code").toString()) == 0){
                LogUtils.getAccessLog().debug(result.getBody().toString());
                return success();
            }
            return error(result.getBody().get(result).toString());
        }
        return error();
    }

    /**
     *无线设备定位频率设置
     * @do 该接口用于无线设备定位频率设置
     */
    @RequestMapping("/rate/set")
    public AjaxResult locationRateSet(){
        String token = getToken();
        Map par = new HashMap();
        List<String> data = Lists.newArrayList();
        par.put("accessToken",token);
        par.put("rate",10);
        //par.put("rate",10);
        par.put("imeis",data.add("868035042567864"));
        ResponseEntity<Map> result = restTemplate.postForEntity(LocatorConstant.FKZX_API+LocatorConstant.INSTRUCTION_RATE+"?accessToken="+token,par,Map.class);
       if(result.getStatusCodeValue() == 200){
           if(Integer.valueOf(result.getBody().get("code").toString()) == 0){
               return success(result.getBody().get("result").toString());
           }
           return error(result.getBody().get("result").toString());
       }
        return error();
    }


    /**
     *有线断油电指令发送
     * @do 该接口用于有线断油电指令发送
     */
    @RequestMapping("/instruction/relay")
    public AjaxResult instructionRelay(){
        String token = getToken();
        Map par = new HashMap();
        List<String> data = Lists.newArrayList();
        par.put("accessToken",token);
        par.put("parameter","1");
        //par.put("rate",10);
        par.put("imeis",data.add("868035042567864"));
        ResponseEntity<Map> result = restTemplate.postForEntity(LocatorConstant.FKZX_API+LocatorConstant.POST_INSTRUCTION_RELAY+"?accessToken="+token,par,Map.class);
        if(result.getStatusCodeValue() == 200){
          if(Integer.valueOf(result.getBody().get("code").toString()) == 0){
              return success();
          }
          return error(result.getBody().get("result").toString());
        }
        return error();
    }




    /**
     * 查询设备状态
     * @do 该接口返回指定设备实时状态或指定账户下设备实时状态
     */
    @RequestMapping("/device/status")
    public AjaxResult getDeviceStatus(){
        String imei = "868035042567864";//实际获取方式是从车辆表中获取
        String token = getToken();
        Map par = new HashMap();
        par.put("accessToken",token);
        par.put("imei",imei);
        Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.DEVICE_STATUS+"?accessToken="+token,Map.class,par);
        if(Integer.valueOf(result.get("code").toString()) == 0){
            LogUtils.getAccessLog().debug(result.toString());
            return AjaxResult.success(result);
        }
        return error();
    }

    /**
     * 里程统计
     * @do 该接口返回单个设备一段时间内的里程及运行时长。
     */
    @RequestMapping("device/miles")
    public AjaxResult deviceMiles(){
        String imei = "868035042567864",startTime = DateUtil.startOfyesterday()+"",endTime =DateUtil.getSecondTimestamp(new Date())+"";
        String token = getToken();
        //Assert.isNull(token,"未授权");
        Map result = restTemplate.getForObject(LocatorConstant.FKZX_API+LocatorConstant.COUNT_MILES+"?accessToken="+token+"&imei="+imei+"&startTime="+startTime+"&endTime="+endTime,Map.class);
        if(Integer.valueOf(result.get("code").toString()) == 0){
            LogUtils.getAccessLog().debug("-----------------"+result.toString());
            return AjaxResult.success(result);
        }
        return error(result.get("result").toString());
    }






}
