## 7-10 问题
---
pc:
7. (ok) 待审核任务铃声提醒
8. (ok) 过磅确定监听回车键事件
9. (ok2) 新增和编辑任务单 去掉地图
12. `业务流程(销售人员) 商砼demo(调度监控)`
10. 配比去掉详情,增加tab切换
11. 根据时间段导出车辆运行记录 excel 表格 (会计做单用)
--
13. 调度监控->调单(确定后没有更新 车辆 bug)(待测试)

主机楼:
4. (等待接口测试)生产指令铃声提醒 [ getPreproductioning ]
---

## pc端
1. (ok) pc端->任务单->配比列表 弹框(左侧列表,右侧详情)
2. // 去掉好活和赖活
3. // 是否 两个 radio 改为 一个 checkbox
4. (ok) 配比单 加圈
5. (ok 待测试) 过磅
6. (ok) 调度监控新增,每辆车的运行记录.
7. 新任务铃声提醒
8. 过磅确定监听 回车键事件
9. 新增和编辑任务单 去掉地图
10. 配比去掉详情,增加tab切换
11. 根据时间段导出车辆运行记录 excel 表格 (会计做单用)
12. 调度监控->调单(确定后没有更新列表bug)

## 业务员手机端
1. (ok 待测试) 业务员手机端 - 可查看和修改任务单详情 (需要调用 /webapp/addTask 传入id)
2. (ok) 业务员端 - 地图查看车辆实时位置

## 主机楼手机端
2. (ok) 主机楼可重复配比
3. (ok) 主机楼可选1号线和2号线
1. (ok) 主机楼 app 后台保活, 5+app iframe
3. (ok) 主机楼接到新的待配比任务单需要有铃声提醒
  4. 生产指令 铃声提醒 [ getPreproductioning ]
5. (ok) mixList 产线列表 [ laboratoryTaskList ]

----------------------------------
1. (ok) 业务员 默认地图地点 (县政府)
3. (ok) 手机端 登出; 修改密码;
4. (ok) 打印第4行和第5行互换,单价不为0
5. (ok) CEO 可以看到所有任务单
2. (ok) 业务员看所有人 可以看到 调度室;
----------------------------------

## 优化与bug
-----------------------------------
1. 任务单编辑添加时: 时间选择有时有问题;
2. 主机楼任务单配比铃声
3. 长垣车辆站内少的时候不能使用特权
-----------------------------------

## ajax
```
  layer.load(1);
  $.modal.loading('111')
  $.modal.closeLoading()
  closeItem();
  var request = $.ajax({
    url: "/",
    type: "POST",
    data: {},
    dataType: "json"
  });
  request.done(function(data) {
    layer.closeAll('loading');
    if (data.code !== 0) {
      layer.alert(data.msg);
      return;
    }
  });
  request.fail(function(jqXHR, textStatus) {
    layer.msg('网络失败');
    console.warn('网络失败', jqXHR);
  });

  {
    msg: "操作成功",
    code: 0,
    data: {
      start: { lng: "", lat: "" },
      end: { lng: "",  lat: "" },
      list: [
        {carId: 74, carNo: 898, lng: 114.426385, lat: 35.093949},
        {carId: 32, carNo: 999, lng: 114.426393, lat: 35.093934},
      ]
    }
  }

```

# 业务员-下单
1. 选用车辆列表api;
-. (ok)字段校验: 使用方量>0

# 业务员-中止任务单
- (ok)重复调用ajax 接口

# 配比单管理(班主任)
- (ok)去掉 删除按钮

# 任务单配比(实验室)
1. 需要分页
- (ok)配比成功后 不应该显示 配比单列表;

# 打印任务单
1. 缺少 user.userprint


```
/duties/tasks/add
任务单列表 /webapp/taskList
{"msg":"操作成功","code":0,"list":[{"searchValue":null,"createBy":null,"createTime":"2019-05-28 10:03:47","updateBy":null,"updateTime":"2019-05-29 17:21:05","remark":null,"params":{},"id":"30","name":"新乡市新乡县新乡县新港蔬菜水果市场","planOrderNo":null,"subTime":30,"isGoodJob":"N","isPinch":"Y","isJcdou":"Y","carType":200,"pinchFl":45.00,"totalFl":123456.00,"shajiangfl":0.00,"productKind":"C25","startTime":"2019-05-31 09:35:35","arriveTime":"2019-05-29 17:20:07","targetAddr":null,"lon":"113.93273","lat":"35.310424","createtime":"2019-05-28 10:03:47","status":0,"pause":"N","isAuto":"Y","waterMethod":"泵打","waterPart":"fdsa","locationTanluodu":"180±20","tanLuodu":"180±20","ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":323.00,"officerMobile":"3223","officer":"2fdsafdsa","receivor":"fdsa","receiver":"港蔬菜水果市场","receivorMobile":"23243","doDeptPart":"路面","doDept":"建业","isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":"2332","planCarCnt":45,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":"2019-05-29 17:20:10","cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":null,"createTime":"2019-05-28 18:45:03","updateBy":null,"updateTime":"2019-05-29 18:11:54","remark":null,"params":{},"id":"35","name":"新乡市封丘县封丘县第二高级中学","planOrderNo":null,"subTime":10,"isGoodJob":"Y","isPinch":"N","isJcdou":"N","carType":200,"pinchFl":5000.00,"totalFl":5000.00,"shajiangfl":5000.00,"productKind":"建业","startTime":"2019-05-28 17:29:33","arriveTime":"2019-05-27 00:00:00","targetAddr":null,"lon":"114.51117","lat":"35.011166","createtime":"2019-05-28 18:45:03","status":0,"pause":"N","isAuto":"N","waterMethod":"泵打1","waterPart":"楼梯","locationTanluodu":"180±22233","tanLuodu":"180±2123","ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"N","isCobblestone":"Y","timeoutfee":"N","bufang":"Y","price":52.00,"officerMobile":"18898878787","officer":"建业","receivor":"建业","receiver":"建业","receivorMobile":"18822222","doDeptPart":"地面","doDept":"建业","isOtherArea":"N","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":"2343343","planCarCnt":50,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":"2019-06-18 00:00:00","cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-05-29 21:47:40","updateBy":null,"updateTime":"2019-05-29 21:47:40","remark":null,"params":{},"id":"44","name":null,"planOrderNo":null,"subTime":null,"isGoodJob":"Y","isPinch":"Y","isJcdou":"N","carType":2,"pinchFl":0.00,"totalFl":0.00,"shajiangfl":0.00,"productKind":null,"startTime":null,"arriveTime":null,"targetAddr":null,"lon":null,"lat":null,"createtime":"2019-05-29 21:47:40","status":0,"pause":"N","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":null,"tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"N","isCobblestone":"N","timeoutfee":null,"bufang":"N","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":null,"receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"N","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":"100,150,200,250,300,350","mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-05-29 21:56:13","updateBy":null,"updateTime":"2019-05-29 21:56:13","remark":null,"params":{},"id":"45","name":"新乡市卫滨区新乡市中心医院","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":220.00,"totalFl":245.00,"shajiangfl":4.00,"productKind":"C25","startTime":"2019-05-29 21:56:16","arriveTime":"2019-05-29 21:55:56","targetAddr":null,"lon":"113.872598","lat":"35.303435","createtime":"2019-05-29 21:56:13","status":0,"pause":"N","isAuto":"Y","waterMethod":"地泵，汽车泵，自卸，塔吊吊卸","waterPart":"梁板梯，墙柱，楼梯，砌筑","locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":23.00,"officerMobile":"1323","officer":"2332","receivor":null,"receiver":"河南建业集团3","receivorMobile":"123222","doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":32,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":"2019-05-29 00:00:00","cartypeList":"250,350","mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 15:35:12","updateBy":null,"updateTime":"2019-06-01 15:35:12","remark":null,"params":{},"id":"46","name":"新乡市延津县升达地板","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":45.00,"totalFl":23232323.00,"shajiangfl":0.00,"productKind":"C25","startTime":"2019-06-01 15:35:28","arriveTime":null,"targetAddr":null,"lon":"114.19951","lat":"35.159322","createtime":"2019-06-01 15:35:12","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":"sdf","locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司","receivorMobile":"18601288359","doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":"200","mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 15:40:30","updateBy":null,"updateTime":"2019-06-01 15:40:30","remark":null,"params":{},"id":"47","name":"新乡市长垣县长垣县","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":34.00,"totalFl":324543.00,"shajiangfl":0.00,"productKind":"C25","startTime":"2019-06-01 15:40:47","arriveTime":null,"targetAddr":null,"lon":"114.425597","lat":"35.09522","createtime":"2019-06-01 15:40:30","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司","receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 15:47:35","updateBy":null,"updateTime":"2019-06-01 15:47:35","remark":null,"params":{},"id":"48","name":"新乡市牧野区定国湖","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":34.00,"totalFl":23232323.00,"shajiangfl":0.00,"productKind":"C25","startTime":"2019-06-01 15:47:32","arriveTime":null,"targetAddr":null,"lon":"113.963884","lat":"35.318112","createtime":"2019-06-01 15:47:35","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":null,"receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 15:53:02","updateBy":null,"updateTime":"2019-06-01 15:53:02","remark":null,"params":{},"id":"49","name":"新乡市红旗区胖东来大胖","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":39.00,"totalFl":23232323.00,"shajiangfl":0.00,"productKind":"C30","startTime":"2019-06-01 15:53:13","arriveTime":null,"targetAddr":null,"lon":"113.885655","lat":"35.307893","createtime":"2019-06-01 15:53:02","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司","receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 16:10:03","updateBy":null,"updateTime":"2019-06-01 16:10:03","remark":null,"params":{},"id":"50","name":"新乡市红旗区胖东来大胖","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":39.00,"totalFl":23232323.00,"shajiangfl":0.00,"productKind":"C30","startTime":"2019-06-01 15:53:13","arriveTime":null,"targetAddr":null,"lon":"113.885655","lat":"35.307893","createtime":"2019-06-01 16:10:03","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司hfhf","receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 16:12:25","updateBy":null,"updateTime":"2019-06-01 16:12:25","remark":null,"params":{},"id":"51","name":"新乡市红旗区胖东来大胖","planOrderNo":null,"subTime":20,"isGoodJob":"Y","isPinch":"Y","isJcdou":"Y","carType":2,"pinchFl":39.00,"totalFl":23232323.00,"shajiangfl":0.00,"productKind":"C30","startTime":"2019-06-01 15:53:13","arriveTime":null,"targetAddr":null,"lon":"113.885655","lat":"35.307893","createtime":"2019-06-01 16:12:25","status":0,"pause":"Y","isAuto":"Y","waterMethod":null,"waterPart":null,"locationTanluodu":"180±20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"Y","isCobblestone":"Y","timeoutfee":"Y","bufang":"Y","price":0.00,"officerMobile":null,"officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司hfhf","receivorMobile":null,"doDeptPart":null,"doDept":null,"isOtherArea":"Y","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null},{"searchValue":null,"createBy":"博力商砼","createTime":"2019-06-01 16:32:25","updateBy":null,"updateTime":"2019-06-01 16:32:25","remark":null,"params":{},"id":"52","name":"新乡市牧野区新乡市第十三中学","planOrderNo":null,"subTime":null,"isGoodJob":"Y","isPinch":"Y","isJcdou":"N","carType":2,"pinchFl":0.00,"totalFl":205.50,"shajiangfl":0.00,"productKind":"C30","startTime":null,"arriveTime":null,"targetAddr":null,"lon":"113.873595","lat":"35.327918","createtime":"2019-06-01 16:32:25","status":0,"pause":"Y","isAuto":"Y","waterMethod":"地泵","waterPart":"7层墙柱梁板梯","locationTanluodu":"180+-20","tanLuodu":null,"ksLevel":null,"ljfangliang":0.00,"sqlWhere":null,"lastCarTime":null,"isCarMoney":"N","isCobblestone":"N","timeoutfee":null,"bufang":"N","price":0.00,"officerMobile":"15888888888","officer":null,"receivor":null,"receiver":"新乡市隆基建筑安装有限公司","receivorMobile":"18601288359","doDeptPart":null,"doDept":null,"isOtherArea":"N","financePause":"N","isMixture":"N","isSchedule":"N","mixNo":null,"mixor":null,"mixtime":null,"cnt":0,"taskId":null,"carCnt":0,"cancelBy":null,"contact":null,"planCarCnt":0,"mixCar":0,"maxCar":0,"height":0.0,"planEndTime":null,"cartypeList":null,"mixTime":null}]}
配比单- 列表 /cemslink/mixformula/list
{"total":3,"rows":[{"searchValue":null,"createBy":null,"createTime":"2019-06-01 12:18:53","updateBy":null,"updateTime":"2019-06-01 12:19:43","remark":null,"params":{},"mixNumber":"TEST_PEIHEBI","isMortar":1,"concreteLabel":"C25","pouringWay":"泵打","slump":"180+20","productLine":2,"mixQnty":null,"mixTime":null,"cement1Dosage":5.0,"cement2Dosage":0.0,"cement3Dosage":5.0,"cement4Dosage":6.0,"cement5Dosage":8.0,"mix1Dosage":9.0,"mix2Dosage":4.0,"mix3Dosage":2.0,"mix4Dosage":1.0,"mix5Dosage":5.0,"additive1Dosage":1.0,"additive2Dosage":1.0,"additive3Dosage":4.0,"additive4Dosage":9.0,"water1Dosage":1.0,"water2Dosage":1.0,"water3Dosage":1.0,"type":1,"syncStatus":0,"syncStatus2":1,"sid":1,"ag1Dosage":3.0,"ag4Dosage":1.0,"ag6Dosage":1.0,"ag3Dosage":4.0,"ag2Dosage":12.0,"ag7Dosage":1.0,"ag8Dosage":1.0,"ag5Dosage":1.0},{"searchValue":null,"createBy":null,"createTime":"2019-06-01 12:18:56","updateBy":null,"updateTime":"2019-06-01 12:19:43","remark":null,"params":{},"mixNumber":"TEST_MIX2","isMortar":1,"concreteLabel":"C35","pouringWay":"泵打","slump":"180-20","productLine":2,"mixQnty":null,"mixTime":null,"cement1Dosage":1.0,"cement2Dosage":1.0,"cement3Dosage":1.0,"cement4Dosage":1.0,"cement5Dosage":1.0,"mix1Dosage":1.0,"mix2Dosage":1.0,"mix3Dosage":1.0,"mix4Dosage":1.0,"mix5Dosage":1.0,"additive1Dosage":1.0,"additive2Dosage":1.0,"additive3Dosage":1.0,"additive4Dosage":1.0,"water1Dosage":1.0,"water2Dosage":1.0,"water3Dosage":1.0,"type":1,"syncStatus":0,"syncStatus2":1,"sid":2,"ag1Dosage":1.0,"ag4Dosage":1.0,"ag6Dosage":1.0,"ag3Dosage":1.0,"ag2Dosage":1.0,"ag7Dosage":1.0,"ag8Dosage":1.0,"ag5Dosage":1.0},{"searchValue":null,"createBy":null,"createTime":"2019-06-01 12:19:00","updateBy":null,"updateTime":"2019-06-01 12:19:43","remark":null,"params":{},"mixNumber":"长垣细石C25","isMortar":1,"concreteLabel":"C25","pouringWay":"泵打","slump":"180+20","productLine":2,"mixQnty":null,"mixTime":null,"cement1Dosage":1.0,"cement2Dosage":2.0,"cement3Dosage":3.0,"cement4Dosage":4.0,"cement5Dosage":5.0,"mix1Dosage":6.0,"mix2Dosage":7.0,"mix3Dosage":8.0,"mix4Dosage":9.0,"mix5Dosage":10.0,"additive1Dosage":19.0,"additive2Dosage":20.0,"additive3Dosage":21.0,"additive4Dosage":22.0,"water1Dosage":23.0,"water2Dosage":24.0,"water3Dosage":25.0,"type":1,"syncStatus":0,"syncStatus2":1,"sid":3,"ag1Dosage":11.0,"ag4Dosage":14.0,"ag6Dosage":16.0,"ag3Dosage":13.0,"ag2Dosage":12.0,"ag7Dosage":17.0,"ag8Dosage":18.0,"ag5Dosage":15.0}],"code":0}
配比单- 新增 /cemslink/mixformula/add
配比单- 修改 /cemslink/mixformula/edit
```

```
mixNumber   是           配比编号
concreteLabel            砼强度
ag1Dosage   是   double  骨料=米石
ag2Dosage   是   double  骨料=1-2石子
ag3Dosage   是   double  骨料=0-5石子
ag4Dosage   是   double  骨料=机制砂
ag5Dosage   是   double  骨料=面沙
cement1Dosage   是   double  粉料=水泥
cement2Dosage   是   double  粉料=水泥
mix1Dosage  是   double  粉料=粉煤灰
mix2Dosage  是   double  粉料=矿粉
mix3Dosage  是   double  水=污水
water1Dosage    是   double  水=M1
water2Dosage    是   double  水=M1
additive1Dosage 是   double  外加剂=减胶剂1
additive2Dosage  是   double  外加剂=减胶剂2
additive3Dosage 是   double  外加剂=减水剂1
additive4Dosage 是   double  外加剂=减水剂2
```
```

```


```
// 调度监控api
{"msg":"操作成功","code":0,"data":[{"taskId":54,"taskName":"新乡市红旗区菲度摄影(道清路)","taskStatus":"N","totalProgress":"24.72/345.00","warning":"R","cars":[{"carId":75,"carNo":999,"info":"999号/1分钟","process":"0.078%","status":"go","breakdown":"Y"},{"carId":74,"carNo":898,"info":"898号/1分钟","process":"0.058%","status":"go","breakdown":"Y"}],"runList":[{"searchValue":null,"createBy":null,"createTime":"2019-07-04 16:11:03","updateBy":null,"updateTime":"2019-07-04 16:14:59","remark":"手动到达操作:当前车次任务单号T190704152802,操作人博力商砼,到达时间2019-07-04 16:15:30","params":{},"id":159,"taskId":54,"taskName":"新乡市红旗区菲度摄影(道清路)","planFangliang":17.00,"carId":75,"carNo":999,"carBrand":"多少","isEnd":"Y","isStart":"Y","status":3,"doorNo":"2号门","productLine":2,"grossWeight":45.67,"netWeight":29.44,"carWeight":16.23,"equalFangliang":12.36,"startTime":"2019-07-04 16:15:23","endTime":"2019-07-04 16:15:31","shajiangfl":1.0,"sqlWhere":null,"driverMobile":"18601288359","comment":null,"urls":null,"lat":"35.275829","lng":"113.911603","stationStatus":"OUT","carCnt":1,"queueStatus":null,"receiver":"DSFA","receivePhone":"787878343","taskTime":"2019-07-04 15:27:32","price":0.00,"waterPart":"DSAF","waterMethod":"FDSA","officerMobile":"15888888888","officer":"博力商砼","productKind":"C23","mixNumber":"长垣斜顶c30","taskNumber":"T190704152802","ljfangliang":24.72,"syncStatus":null,"signTime":null,"sendTask":false,"cnt":null,"notifyId":"1145946711048249344","privilegeTask":false,"inEnd":false,"last":false},{"searchValue":null,"createBy":null,"createTime":"2019-07-04 16:12:37","updateBy":null,"updateTime":"2019-07-04 16:14:51","remark":"手动到达操作:当前车次任务单号T190704152802,操作人博力商砼,到达时间2019-07-04 16:15:32","params":{},"id":160,"taskId":54,"taskName":"新乡市红旗区菲度摄影(道清路)","planFangliang":19.00,"carId":74,"carNo":898,"carBrand":"豫a4569","isEnd":"Y","isStart":"Y","status":3,"doorNo":"2号门","productLine":2,"grossWeight":45.67,"netWeight":29.44,"carWeight":16.23,"equalFangliang":12.36,"startTime":"2019-07-04 16:15:15","endTime":"2019-07-04 16:15:32","shajiangfl":1.0,"sqlWhere":null,"driverMobile":"13783948535","comment":null,"urls":null,"lat":"35.275829","lng":"113.911603","stationStatus":"OUT","carCnt":2,"queueStatus":null,"receiver":"DSFA","receivePhone":"787878343","taskTime":"2019-07-04 15:27:32","price":0.00,"waterPart":"DSAF","waterMethod":"FDSA","officerMobile":"15888888888","officer":"博力商砼","productKind":"C23","mixNumber":"长垣斜顶c30","taskNumber":"T190704152802","ljfangliang":12.36,"syncStatus":null,"signTime":null,"sendTask":false,"cnt":null,"notifyId":"1145627534098030592","privilegeTask":false,"inEnd":false,"last":false}]}]}

/webapp/transTasksToOther
taskCarId
toTaskId
remark
```

铃声测试
```
开始: 10:45
结束: 11:41

开始: 14.50
结束: 15:40

```

```js
$('#bootstrap-table').bootstrapTable({
  onRefresh: function (data) {
    console.log(23333,data)
  }
})
```

配置文件
```
        master:
            driverClassName: com.mysql.cj.jdbc.Driver
            url: jdbc:mysql://36.99.34.117:3306/blst?u
        slave:
            # 从数据源开关/默认关闭
            driverClassName: com.microsoft.sqlserver.jdbc.SQLServerDriver
            enabled: true
            url: jdbc:sqlserver://47.105.130.125:1433;DatabaseName=test_cems
```