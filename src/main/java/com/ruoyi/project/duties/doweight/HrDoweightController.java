package com.ruoyi.project.duties.doweight;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.api.ApiBangController;
import com.ruoyi.api.MySeq;
import com.ruoyi.api.PushUtils;
import com.ruoyi.api.RedisDomainUtils;
import com.ruoyi.api.domain.PrintDomain;
import com.ruoyi.api.domain.RuningCar;
import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.redis.RedisUtils;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.AjaxResult;
import com.ruoyi.project.cars.car.domain.Car;
import com.ruoyi.project.cars.car.service.ICarService;
import com.ruoyi.project.cars.comstrength.domain.Comstrength;
import com.ruoyi.project.cars.comstrength.service.IComstrengthService;
import com.ruoyi.project.cars.driver.domain.Driver;
import com.ruoyi.project.cars.driver.service.IDriverService;
import com.ruoyi.project.cemslink.preproduction.domain.Preproduction;
import com.ruoyi.project.cemslink.preproduction.service.IPreproductionService;
import com.ruoyi.project.duties.tasks.domain.Tasks;
import com.ruoyi.project.duties.tasks.service.ITasksService;
import com.ruoyi.project.duties.tasksCars.domain.TasksCars;
import com.ruoyi.project.duties.tasksCars.service.ITasksCarsService;
import com.ruoyi.project.stock.receiveCar.domain.ReceiveCar;
import com.ruoyi.project.stock.receiveCar.service.IReceiveCarService;
import com.ruoyi.project.stock.receiveGoods.domain.ReceiveGoods;
import com.ruoyi.project.stock.receiveGoods.service.IReceiveGoodsService;
import com.ruoyi.project.stock.receiveGoodsLevel.domain.ReceiveGoodsLevel;
import com.ruoyi.project.stock.receiveGoodsLevel.service.IReceiveGoodsLevelService;
import com.ruoyi.project.stock.receiveOffer.domain.ReceiveOffer;
import com.ruoyi.project.stock.receiveOffer.service.IReceiveOfferService;
import com.ruoyi.project.stock.receiveOffice.domain.ReceiveOffice;
import com.ruoyi.project.stock.receiveOffice.service.IReceiveOfficeService;
import com.ruoyi.project.stock.receivePeople.domain.ReceivePeople;
import com.ruoyi.project.stock.receivePeople.service.IReceivePeopleService;
import com.ruoyi.project.stock.receivePlace.domain.ReceivePlace;
import com.ruoyi.project.stock.receivePlace.service.IReceivePlaceService;
import com.ruoyi.project.stock.receiveRemark.domain.ReceiveRemark;
import com.ruoyi.project.stock.receiveRemark.service.IReceiveRemarkService;
import com.ruoyi.project.stock.receiveStock.domain.ReceiveStock;
import com.ruoyi.project.stock.receiveStock.service.IReceiveStockService;
import com.ruoyi.project.stock.sendAdmixsture.domain.SendAdmixsture;
import com.ruoyi.project.stock.sendAdmixsture.service.ISendAdmixstureService;
import com.ruoyi.project.stock.sendCementlevel.domain.SendCementlevel;
import com.ruoyi.project.stock.sendCementlevel.service.ISendCementlevelService;
import com.ruoyi.project.stock.sendKslevel.domain.SendKslevel;
import com.ruoyi.project.stock.sendKslevel.service.ISendKslevelService;
import com.ruoyi.project.stock.sendSandLevel.domain.SendSandLevel;
import com.ruoyi.project.stock.sendSandLevel.service.ISendSandLevelService;
import com.ruoyi.project.stock.sendStoneLevel.domain.SendStoneLevel;
import com.ruoyi.project.stock.sendStoneLevel.service.ISendStoneLevelService;
import com.ruoyi.project.stock.sendTanluodu.domain.SendTanluodu;
import com.ruoyi.project.stock.sendTanluodu.service.ISendTanluoduService;
import com.ruoyi.project.stock.sendWaterMethod.domain.SendWaterMethod;
import com.ruoyi.project.stock.sendWaterMethod.service.ISendWaterMethodService;
import com.ruoyi.project.stock.sendWaterPart.domain.SendWaterPart;
import com.ruoyi.project.stock.sendWaterPart.service.ISendWaterPartService;
import com.ruoyi.project.system.dict.domain.DictData;
import com.ruoyi.project.system.dict.service.IDictDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.aspectj.weaver.loadtime.Aj;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import oshi.util.StringUtil;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/duties/doweight")
public class HrDoweightController extends BaseController {
    private String prefix = "duties/doweight";

    @Autowired
    private ITasksCarsService tasksCarsService;
    @Autowired
    private RedisUtils redisUtils;
    @Autowired
    private ITasksService tasksService;
    @Autowired
    private ICarService carService;
    @Autowired
    private IDriverService driverService;
    private BigDecimal equalRate = new BigDecimal(Global.getConfig("project.equalRate"));
    @Autowired
    private IPreproductionService preproductionService;
    @Autowired
    private ApiBangController apiBangController;
    @Autowired
    private IComstrengthService comstrengthService;
    @Autowired
    private IReceiveStockService receiveStockService;
    /**收货表*/
    //送货车辆
    @Autowired
    private IReceiveCarService receiveCarService;
    //商品列表
    @Autowired
    private IReceiveGoodsLevelService receiveGoodsLevelService;
    @Autowired
    private IReceiveOfficeService receiveOfficeService;
    @Autowired
    private IReceivePeopleService receivePeopleService;
    @Autowired
    private IReceiveRemarkService receiveRemarkService;
    @Autowired
    private IReceivePlaceService receivePlaceService;
    @Autowired
    private IReceiveOfferService receiveOfferService;
    @Autowired
    private IReceiveGoodsService goodsService;
    //浇筑部位
    @Autowired
    private ISendWaterPartService waterPartService;
    //浇筑方式
    @Autowired
    private ISendWaterMethodService waterMethodService;
    //外加剂
    @Autowired
    private ISendAdmixstureService sendAdmixstureService;
    //沙子等级
    @Autowired
    private ISendSandLevelService sandLevelService;
    //石子等级
    @Autowired
    private ISendStoneLevelService stoneLevelService;
    @Autowired
    //水泥标号
    private ISendCementlevelService cementlevelService;
    //坍落度
    @Autowired
    private ISendTanluoduService tanluoduService;
    //抗渗等级
    @Autowired
    private ISendKslevelService kslevelService;
    @Autowired
    private IReceivePeopleService peopleService;
    @Autowired
    private IDictDataService dictDataService;


    private Logger logger = LoggerFactory.getLogger(HrDoweightController.class);

    /**
     * 1.鸿睿商砼称重页面
     * */
    @RequestMapping("/materialTypeList")
    @GetMapping
    public AjaxResult materialTypeList()
    {
        List<DictData> dataList = dictDataService.selectDictDataByType("mertial_type");
        return AjaxResult.success(dataList);
    }


    /**
     * 1.鸿睿商砼称重页面
     * */
    @RequiresPermissions("duties:tasksCars:view")
    @RequestMapping("/hrdoweight")
    @GetMapping
    public String hrdoweight(ModelMap mmp)
    {
        return prefix + "/hrdoweight";
    }


    /**
     * 2.鸿睿商砼预置数据
     * */
    @RequiresPermissions("duties:tasksCars:view")
    @RequestMapping("/getPreData")
    @GetMapping
    @ResponseBody
    public AjaxResult getPreData(@RequestParam(required = true) String type){
        Boolean btnUse = false;
        if(getSysUser().getUserName().equalsIgnoreCase("boss")){
            btnUse = true;
        }
        if(type.equalsIgnoreCase("OUT")){
            //车号列表
            List<Car> carList = carService.selectCarList(new Car());
            List<DictData> materialList = dictDataService.selectDictDataByType("mertial_type");
            //任务名称列表
            List<Tasks> tlist = tasksService.selectTasksList(new Tasks(){{setSqlWhere(" and status in (0,1)");}});
            List<Comstrength> comList = comstrengthService.selectComstrengthList(new Comstrength());
            //司机列表
            List<Driver> driverList = driverService.selectDriverList(new Driver());
            //收货单位
            List<ReceiveOffice>  receiveDeptList = receiveOfficeService.selectReceiveOfficeList(new ReceiveOffice());
            //工程部位
            List<SendWaterPart> waterPartList = waterPartService.selectSendWaterPartList(new SendWaterPart());
            //坍落度
            List<SendTanluodu> tanLuoduList = tanluoduService.selectSendTanluoduList(new SendTanluodu());
            //浇筑方式
            List<SendWaterMethod> waterMethodList = waterMethodService.selectSendWaterMethodList(new SendWaterMethod());
            //抗渗等级
            List<SendKslevel> ksList = kslevelService.selectSendKslevelList(new SendKslevel());
            //水泥标号
            List<SendCementlevel> cementLevel = cementlevelService.selectSendCementlevelList(new SendCementlevel());
            //外加剂
            List<SendAdmixsture> wjjList = sendAdmixstureService.selectSendAdmixstureList(new SendAdmixsture());
            //沙子等级
            List<SendSandLevel> sandList = sandLevelService.selectSendSandLevelList(new SendSandLevel());
            //石子等级
            List<SendStoneLevel> stoneList = stoneLevelService.selectSendStoneLevelList(new SendStoneLevel());
            //操作员
            List<ReceivePeople> operatList  = peopleService.selectReceivePeopleList(new ReceivePeople());
            //发货人
            List<ReceivePeople> sendorList = peopleService.selectReceivePeopleList(new ReceivePeople());
            List<ReceiveRemark> remarksList = receiveRemarkService.selectReceiveRemarkList(new ReceiveRemark());
            return AjaxResult.success()
                    .put("outCarList",carList)
                    .put("tlist",tlist)
                    .put("comList",comList)
                    .put("driverList",driverList)
                    .put("receiveDeptList",receiveDeptList)
                    .put("waterPartList",waterPartList)
                    .put("tanLuoduList",tanLuoduList)
                    .put("waterMethodList",waterMethodList)
                    .put("ksList",ksList)
                    .put("cementLevel",cementLevel)
                    .put("wjjList",wjjList)
                    .put("sandList",sandList)
                    .put("stoneList",stoneList)
                    .put("operatList",operatList)
                    .put("materialList",materialList)
                    .put("btnUse",btnUse)
                    .put("sendorList",sendorList);
        }else {
            List<ReceiveCar> carList = receiveCarService.selectReceiveCarList(new ReceiveCar());
            List<ReceiveGoods> goodsList = goodsService.selectReceiveGoodsList(new ReceiveGoods());
            List<ReceiveGoodsLevel> goodsLevelList = receiveGoodsLevelService.selectReceiveGoodsLevelList(new ReceiveGoodsLevel());
            List<ReceiveOffice> officeList = receiveOfficeService.selectReceiveOfficeList(new ReceiveOffice());
            List<ReceivePeople> peoplelist = receivePeopleService.selectReceivePeopleList(new ReceivePeople());
            List<ReceivePlace> placesList = receivePlaceService.selectReceivePlaceList(new ReceivePlace());
            List<ReceiveRemark> remarksList = receiveRemarkService.selectReceiveRemarkList(new ReceiveRemark());
            List<ReceiveOffer> offerList = receiveOfferService.selectReceiveOfferList(new ReceiveOffer());
            return AjaxResult.success()
                    .put("inCarList",carList)
                    .put("goodsLevelList",goodsLevelList)
                    .put("remarksList",remarksList)
                    .put("goodsList",goodsList)
                    .put("officeList",officeList)
                    .put("peoplelist",peoplelist)
                    .put("placesList",placesList)
                    .put("remarksList",remarksList)
                    .put("offerList",offerList);
        }
    }


    /**
     * 设置【补票数据】开关0：关，1开
     * */
    @RequestMapping("/settingIsBP/{isBP}")
    @ResponseBody
    public AjaxResult settingIsBP(@PathVariable(required = true) Long isBP){
        if(isBP.equals(0L)){
            redisUtils.set(Constants.IS_BUPIAO,false,-1);
        }else{
            redisUtils.set(Constants.IS_BUPIAO,true,-1);
        }
        return AjaxResult.success();
    }


    /**
     * 获取能否补票的接口
     * **/
    @RequestMapping("/gettingIsBP")
    @ResponseBody
    public AjaxResult settingIsBP(){
        Boolean isBP = redisUtils.get(Constants.IS_BUPIAO,Boolean.class);
        if(null==isBP)isBP=false;
        return AjaxResult.success(isBP);
    }


    /**
     * 3.根据车牌号获取最近一次记录
     * **/
    @RequiresPermissions("duties:tasksCars:view")
    @GetMapping("/getlogbycarNo")
    @ResponseBody
    public AjaxResult getlogbycarNo(@RequestParam(required = true) String carNumber){
        List<ReceiveStock> listOrder = receiveStockService.selectReceiveStockList(new ReceiveStock(){{
            setCarBrand(carNumber);
            setSqlWhere(" order by create_time desc limit 1");
        }});
        ReceiveStock rorder = new ReceiveStock();
        if(null!=listOrder&&listOrder.size()>0){
            rorder = listOrder.get(0);
            rorder.setGrossWeight(null);
            rorder.setCarWeight(null);
            rorder.setTotalFee(null);
            return AjaxResult.success(rorder);
        }
        return AjaxResult.success(rorder);
    }


    /**
     * 【收货】时点击称皮还是称重
     * **/
    @RequiresPermissions("duties:tasksCars:view")
    @GetMapping("/doWeighting")
    @ResponseBody
    public AjaxResult doWeighting(@RequestParam(required = true) String carNumber,@RequestParam(required = true)String weightType){
        //收货
        if(weightType.equalsIgnoreCase(Constants.GROSS_WEIGHT)){
            List<ReceiveStock> olist = receiveStockService.selectReceiveStockList(new ReceiveStock(){{
                setCarBrand(carNumber);
                setSqlWhere(" and car_weight is not null and car_weight >0 and (gross_weight is null or gross_weight =0 ) order by create_time desc limit 1");
            }});
            if(olist!=null&&olist.size()>0){
                ReceiveStock roder = olist.get(0);
                return AjaxResult.success(roder);
            }
            return AjaxResult.success(new ReceiveStock());
            //称总重，要把上次皮重带出来
        /*if(null!=car){
            o.setCarWeight(car.getWeight());
        }*/
//			return AjaxResult.success(o);
        }else if(weightType.equalsIgnoreCase(Constants.CAR_WEIGHT)){
            //称皮重,查看上次有没有未过磅的榜单
            List<ReceiveStock> orderList = receiveStockService.selectReceiveStockList(new ReceiveStock(){{
                setCarBrand(carNumber);
                setSqlWhere(" and gross_weight is not null and gross_weight > 0 and (car_weight is null or car_weight = 0) order by create_time desc limit 1 ");
            }});
            if(orderList!=null&&orderList.size()>0){
                ReceiveStock order = orderList.get(0);
                return AjaxResult.success(order);
            }else{
                return AjaxResult.success(new ReceiveStock());
            }
        }
        return AjaxResult.error();
    }



    /**
     * 保存收货称重
     * **/
    @RequiresPermissions("stone:order:weight")
    @PostMapping("/saveReceiveWeight")
    @ResponseBody
    public AjaxResult saveReceiveWeight(ReceiveStock order){
        order.setCreateBy(getSysUser().getUserName());
        if(!validate(order)){
            return AjaxResult.error();
        }
        if(StringUtils.isBlank(order.getGoodsName())){
            return AjaxResult.error("请选择货名");
        }
        final String dictLale = order.getGoodsName();
        if(StringUtils.isNotBlank(order.getGoodsName())){
            List<DictData> dictDataList =dictDataService.selectDictDataList(new DictData(){{
                setDictLabel(dictLale);
                setDictType("mertial_type");
            }});
            if(null!=dictDataList&&dictDataList.size()>0){
                order.setMaterialType(dictDataList.get(0).getDictValue());
            }
        }
        if(null!=order){
            //维护扩展表
            saveExtData(order);
            /**
             * 1.如果称重为皮重，则只保留皮重信息即可
             * 2.如果称重为皮重，并且grossweight也不为空，则代表上次已经过重了，此时需要保存完整数据
             * */
            if(null!=order.getWeightType()&&order.getWeightType().equalsIgnoreCase(Constants.CAR_WEIGHT)){
                if(order.getCarWeight()==null||order.getCarWeight().compareTo(new BigDecimal(0))<=0){
                    return AjaxResult.error("皮重数据需大于0");
                }
                String carNumber = order.getCarBrand();
                ReceiveCar car = receiveCarService.selectOne(new ReceiveCar(){{setCarBrand(carNumber);}});
                if(null==car){
                    car = new ReceiveCar();
                    car.setCarBrand(order.getCarBrand());
                }
                if(car.getId()!=null){
                    car.setUpdateBy(getLoginName());
                    receiveCarService.updateReceiveCar(car);
                }else{
                    car.setCreateBy(getLoginName());
                    receiveCarService.insertReceiveCar(car);
                }
                //保存皮重信息为一条记录
//                order.setSno(MySeq.getSequece());
                if(order.getId()==null){
                    receiveStockService.insertReceiveStock(order);
                }else{
                    receiveStockService.updateReceiveStock(order);
                }

                //如果此时grossweight》0
                if(order.getGrossWeight()!=null&&order.getGrossWeight().compareTo(new BigDecimal(0))>0){
                    if(null==order.getId()){
                        logger.debug("id或者serialNo为空");
                        return AjaxResult.error("未查询到上次单据信息");
                    }
                    //保存所有数据
                    order = setRealData(order);
                    order.setUpdateBy(getLoginName());
                    receiveStockService.updateReceiveStock(order);
                }
            }else if(null!=order.getWeightType()&&order.getWeightType().equalsIgnoreCase(Constants.GROSS_WEIGHT)){
                /**
                 * 1.如果称毛重，则先创建单子
                 * 2.如果称毛重并且皮重不为0，则直接保存所有的单子信息
                 * */
                if(null==order.getGrossWeight()||order.getGrossWeight().compareTo(new BigDecimal(0))==0){
                    return AjaxResult.error("毛重不能为0");
                }
                //此时如果皮重为0，则不保存皮重
                if(order.getCarWeight()==null||order.getCarWeight().compareTo(new BigDecimal(0))==0){
                    order.setCarWeight(null);
                }else{
                    //保存所有数据
                    if(order.getId()!=null){
                        order = setRealData(order);
                    }
                }
                if(null!=order.getId()){
                    order.setUpdateBy(getLoginName());
                    receiveStockService.updateReceiveStock(order);
                }else{
                    order.setCreateBy(getLoginName());
                    order.setCreateBy(getSysUser().getUserName());
                    receiveStockService.insertReceiveStock(order);
                }
            }else{
                //补录
                receiveStockService.insertReceiveStock(order);
            }
        }
        return AjaxResult.success(order);
    }


    /**
     * 保存其他扩展表数据
     * **/
    private void saveExtData(ReceiveStock order) {
        //车辆
        if(StringUtils.isNotBlank(order.getCarBrand())){
            ReceiveCar car = receiveCarService.selectOne(new ReceiveCar(){{setCarBrand(order.getCarBrand());}});
            if(null==car){
                car = new ReceiveCar();
                car.setCarBrand(order.getCarBrand());
                car.setDriverName(order.getDriver());
                receiveCarService.insertReceiveCar(car);
            }
        }

        //货品
        /*if(StringUtils.isNotBlank(order.getGoodsName())){
            ReceiveGoods goods = goodsService.selectOne(new ReceiveGoods(){{setGoodsname(order.getGoodsName());}});
            if(null==goods){
                goods = new ReceiveGoods();
                goods.setPrice(order.getPrice());
                goods.setGoodsname(order.getGoodsName());
                goodsService.insertReceiveGoods(goods);
            }
        }*/
        //货品等级
        if(StringUtils.isNotBlank(order.getLevel())){
            ReceiveGoodsLevel level = receiveGoodsLevelService.selectOne(new ReceiveGoodsLevel(){{setLevelName(order.getLevel());}});
            if(null==level){
                level =new ReceiveGoodsLevel();
                level.setLevelName(order.getLevel());
                receiveGoodsLevelService.insertReceiveGoodsLevel(level);
            }
        }
        //收货单位
        if(StringUtils.isNotBlank(order.getReceiveDept())){
            ReceiveOffice office = receiveOfficeService.selectOne(new ReceiveOffice(){{setOfficeName(order.getReceiveDept());}});
            if(null==office){
                office =new ReceiveOffice();
                office.setOfficeName(order.getReceiveDept());
                receiveOfficeService.insertReceiveOffice(office);
            }
        }
        //供货单位
        if(StringUtils.isNotBlank(order.getOfferDept())){
            ReceiveOffer offer = receiveOfferService.selectOne(new ReceiveOffer(){{setOfferName(order.getOfferDept());}});
            if(null==offer){
                offer =new ReceiveOffer();
                offer.setOfferName(order.getOfferDept());
                receiveOfferService.insertReceiveOffer(offer);
            }
        }
        //收货人
        if(StringUtils.isNotBlank(order.getReceiver())){
            ReceivePeople people = receivePeopleService.selectOne(new ReceivePeople(){{setName(order.getReceiver());}});
            if(null==people){
                people =new ReceivePeople();
                people.setName(order.getReceiver());
                receivePeopleService.insertReceivePeople(people);
            }
        }
        //产地
        if(StringUtils.isNotBlank(order.getPlace())){
            ReceivePlace place = receivePlaceService.selectOne(new ReceivePlace(){{setPlace(order.getPlace());}});
            if(null==place){
                place =new ReceivePlace();
                place.setPlace(order.getPlace());
                receivePlaceService.insertReceivePlace(place);
            }
        }
    }

    private ReceiveStock setRealData(ReceiveStock order) {
        order.setUpdateBy(getSysUser().getUserName());
        if(order.getGrossWeight().compareTo(order.getCarWeight())<=0){
            throw new BaseException("毛重必须大于皮重");
        }
        ReceiveStock dbo = receiveStockService.selectReceiveStockById(order.getId());
        order.setNetWeight(order.getGrossWeight().subtract(order.getCarWeight()).setScale(2,RoundingMode.HALF_UP));//单据净重
        if(order.getPrice()!=null){
            order.setTotalFee(order.getPrice().multiply(order.getNetWeight()).setScale(3,RoundingMode.HALF_UP));//单据总额
        }
        return order;
    }

    //校验
    private boolean validate(ReceiveStock order) {
        boolean ret = null==order||
                StringUtils.isBlank(order.getCarBrand())||(order.getGrossWeight()==null&&order.getCarWeight()==null);
        return !ret;
    }



    @RequestMapping("/saveOutWeight")
    @ResponseBody
    public AjaxResult saveOutWeight(TasksCars tasksCars){
        //称净重
        if(null!=tasksCars.getGrossWeight()&&tasksCars.getCarWeight()!=null){
            if(!validateLog(tasksCars)){
                return AjaxResult.error();
            }
            tasksCars.setSure(true);
            tasksCars.setStationStatus("OUT");
            tasksCars.setNetWeight(tasksCars.getGrossWeight().subtract(tasksCars.getCarWeight()));
            //折方系数
            Comstrength comstrength = comstrengthService.selectOne(new Comstrength(){{setStrengthNo(tasksCars.getProductKind());}});
            if(null==comstrength){
                return AjaxResult.error("没有找到该标号的折方系数");
            }
            //折方量
            tasksCars.setEqualFangliang(tasksCars.getNetWeight().divide(comstrength.getRatio(),2,RoundingMode.HALF_UP));
            Tasks tasks = tasksService.selectTasksById(tasksCars.getTaskId().toString());
            if(tasks!=null){
                Car car = carService.selectOne(new Car(){{setCarNum(tasksCars.getCarBrand());setSqlWhere(" limit 1 ");}});
//                tasksCars.setCarCnt(tasks.getCarCnt()+1);
//                tasksCars.setLjfangliang(tasks.getLjfangliang().add(tasksCars.getEqualFangliang()));
                tasksCars.setStatus(Constants.TASK_STATUS_ING);//收到任务
//                tasksCars.setTaskId(Long.valueOf(tasks.getId()));
//                tasksCars.setTaskNumber(tasks.getPlanOrderNo());
//                tasksCars.setReceiver(tasks.getReceiver());//接收单位
//                tasksCars.setReceivePhone(tasks.getReceivorMobile());////收货人电话
//                tasksCars.setOfficer(tasks.getCreateBy());//业务经理
//                tasksCars.setOfficerMobile(tasks.getOfficerMobile());//业务经理电话
//                tasksCars.setTaskTime(tasks.getArriveTime());//到达时间
//                tasksCars.setProductKind(tasks.getProductKind());//砼标号
                tasksCars.setTaskNumber(tasks.getPlanOrderNo());//任务编号
                //背砂浆需要减去多少方量
                tasksCars.setPlanFangliang(car.getFangl());
                tasksCars.setStartLat(Global.getConfig("project.lat"));
                tasksCars.setStartLng(Global.getConfig("project.lon"));
                //设置皮重为车重
//                tasksCars.setCarWeight(car.getCarWeight());
//                tasksCars.setWaterMethod(tasks.getWaterMethod());
//                tasksCars.setWaterPart(tasks.getWaterPart());
                //派发时间，用来计算3分钟内司机是否确认订单
                tasksCars.setDispatchTime(System.currentTimeMillis());
                tasksCars.setOfficerMobile(tasks.getOfficerMobile());
                tasksCars.setOfficer(tasks.getOfficer());
                tasksCars.setLat(tasks.getLat());
                tasksCars.setLng(tasks.getLon());
//                tasksCars.setTanluodu("");
                tasksCarsService.insertTasksCars(tasksCars);
                tasks.setCarCnt(tasks.getCarCnt()+1);
                tasks.setLjfangliang(tasks.getLjfangliang().add(tasksCars.getEqualFangliang()));
                tasksService.updateTasks(tasks);
            }else{
                return AjaxResult.error("任务单不存在");
            }
        }else if(null!=tasksCars.getCarWeight()){
            Car tcar = null;
            //称皮重
            List<Car> carList = carService.selectCarList(new Car(){{setCarNum(tasksCars.getCarBrand());}});
            if(null==carList||carList.size()==0){
                tcar = new Car();
                tcar.setCarNum(tasksCars.getCarBrand());
                tcar.setCarWeight(tasksCars.getCarWeight());
                carService.insertCar(tcar);
            }else{
                tcar = carList.get(0);
                tcar.setCarWeight(tasksCars.getCarWeight());
                carService.updateCarApi(tcar);
            }
        }
        return AjaxResult.success();
    }




    /**
     * 校验称重信息
     * **/
    private boolean validateLog(TasksCars tasksCars) {
        if(tasksCars==null||tasksCars.getTaskId()==null||
        StringUtils.isBlank(tasksCars.getCarBrand())||
        StringUtils.isBlank(tasksCars.getTaskName())||
        StringUtils.isBlank(tasksCars.getProductKind())
        )return false;
        return true;
    }

}
