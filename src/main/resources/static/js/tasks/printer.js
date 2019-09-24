  (function() {
    var userPrint = document.getElementById('userprint').value;
    if (userPrint !== 'true') {
      console.log('不打印');
      return false;
    }
    var LODOP; // 打印
    var styles = '<style>.order {width: 100%;margin: 0 auto;background: transparent;font-size: 13px;width: 190mm;}.title {text-align: center;font-weight: bold;font-size: 17px;padding-top: 8px;}.table {border: 1px solid #000000;width: 100%;background-color: transparent;border-spacing: 0;border-collapse: collapse;}.table td {border: 1px solid #000000;padding: 2px;line-height: 1.42857143;vertical-align: middle;text-align: center;}.subtitle {width: 100%;margin-bottom: 4px;}.subtitle td {/*border: 0;*/width: 50%;}.subtitle-time {text-align: right;}.foot {text-align: center;}.foot p {margin: 4px auto 0;}.foot-lian span {padding: 0 50px;}.shouhuo {vertical-align: middle;}.test-box {text-align: center;}.test-box a {display: inline-block;padding: 10px 20px;border: 1px solid #000;margin: 10px 50px;}.row {display: flex;list-style: none;padding: 0;margin: 0;text-align: center;}.row li {display: inline-block;border: 1px solid #000000;border-right-width: 0;border-bottom-width: 0;padding: 7px 0;}.label1 {text-align: center;width: 10%;}.value1 {width: 15%;/* flex: 1; */}.value4 {width: 40%;}.row1 li.b-r-n {border-right-width: 1px;}.rowb {display: flex;overflow: hidden;border-bottom: 1px solid #000;}.rowb .col1 {width: 74.8%;}.rowb .col2 {width: 25.2%;}.rowb .label2 {width: 13.2%;text-align: center;}.rowb .value2 {width: 19.84%;}.rowb .label3 {width: 20%;justify-content: center;}.rowb .value3 {width: 80%;}.row3 {/* height: 100%; */}.row3 li {display: flex;align-items: center;}</style>';
    var Order; // 打印对象
    // var Order = {
    //   orderNo: 'iso02374634529', //序号
    //   dateTime: '2018-9-1 12:00', // 时间
    //   receivingCompany: '医院', //收货单位
    //   telephone: '18001288345', //电话
    //   arrivalTime: '16:50', //到达时间
    //   engineeringName: '修路工程', //工程名称
    //   strengthGrade: 'P80', //强度等级
    //   unitPrice: '180元', //单价
    //   pouringSite: '浇筑部位test', //浇筑部位
    //   pouringMethod: '浇筑方式test', //浇筑方式
    //   remarks: '车带款', //备注
    //   licenseNumber: '12', //车号
    //   trainNumber: '21', //车次
    //   squareQuantity: '156', //方量
    //   consignee: '', //收货人
    //   grossWeight: '30', //毛重
    //   tare: '35', //皮重
    //   accumulatedVolume: '198', //累计方量
    //   serviceManager: '王经理', //业务经理
    //   managerTelephone: '18001288345', //电话
    //   stdWeight: '432', //净重
    // };
    var Order = {
      orderNo: '', //序号
      dateTime: '', // 时间
      receivingCompany: '', //收货单位
      telephone: '', //电话
      arrivalTime: '', //到达时间
      engineeringName: '', //工程名称
      strengthGrade: '', //强度等级
      unitPrice: '', //单价
      pouringSite: '', //浇筑部位
      pouringMethod: '', //浇筑方式
      remarks: '', //备注
      licenseNumber: '', //车号
      trainNumber: '', //车次
      squareQuantity: '', //方量
      consignee: '', //收货人
      grossWeight: '', //毛重
      tare: '', //皮重
      accumulatedVolume: '', //累计方量
      serviceManager: '', //业务经理
      managerTelephone: '', //电话
      stdWeight: '', //净重
    };

    /**
     * 请求数据
     */
    var timer_flag = 1;
    var timer = setInterval(function() {
      $.ajax({
        url: "/webapp/printList",
        type: 'POST',
        data: {
          flag: timer_flag,
        },
        dataType: 'json',
      }).done(function(data) {
        console.log(data);
        var dt = data.list;
        var newData = formatterData(dt, {
          // '0': {
          //   orderNo: 'orderNo',
          //   dateTime: 'dateTime',
          //   receivingCompany: 'receivingCompany',
          //   telephone: 'telephone',
          //   arrivalTime: 'arrivalTime',
          //   engineeringName: 'engineeringName',
          //   strengthGrade: 'strengthGrade',
          //   unitPrice: 'unitPrice',
          //   pouringSite: 'pouringSite',
          //   pouringMethod: 'pouringMethod',
          //   remarks: 'remarks',
          //   licenseNumber: 'licenseNumber',
          //   trainNumber: 'trainNumber',
          //   squareQuantity: 'squareQuantity',
          //   consignee: 'consignee',
          //   grossWeight: 'grossWeight',
          //   tare: 'tare',
          //   accumulatedVolume: 'accumulatedVolume',
          //   serviceManager: 'serviceManager',
          //   managerTelephone: 'managerTelephone',
          //   stdWeight: 'stdWeight',
          // },
        });
        // console.log(data, newData);
        for (var i = 0; i < newData.length; i++) {
          // console.log(timer_flag, newData[i]);
          var order_data = newData[i];
          printOrder(order_data);
        }
      });
      timer_flag++;
      if (timer_flag > 100) { timer_flag = 1 };
      // clearInterval(timer); // 测试
    }, 5000);

    /**
     * 打印数据
     */
    function printOrder(order) {
      // console.log('printer::', order);
      var orderHtml = `<div class="order">
      <div id="title">
        <div class="title">混凝土发货单</div>
      </div>
      <div id="subtitle">
        <table class="subtitle">
          <tr>
            <td>序号：${order.orderNo}</td>
            <td class="subtitle-time">时间：${order.dateTime}</td>
          </tr>
        </table>
      </div>
      <div class="tableul">
        <ul class="row row1">
          <li class="label1">收货单位</li>
          <li class="value4">${order.receivingCompany}</li>
          <li class="label1">电话</li>
          <li class="value1">${order.telephone}</li>
          <li class="label1">到达时间</li>
          <li class="b-r-n value1"></li>
        </ul>
        <ul class="row row1">
          <li class="label1">工程名称</li>
          <li class="value4">${order.engineeringName}</li>
          <li class="label1">强度等级</li>
          <li class="value1">${order.strengthGrade}</li>
          <li class="label1">单价</li>
          <li class="b-r-n value1">${order.unitPrice ? order.unitPrice : ''}</li>
        </ul>
        <ul class="row row1">
          <li class="label1">浇筑部位</li>
          <li class="value4">${order.pouringSite}</li>
          <li class="label1">浇筑方式</li>
          <li class="value1">${order.pouringMethod}</li>
          <li class="label1">备注</li>
          <li class="b-r-n value1">${order.remarks}</li>
        </ul>
        <div class="rowb">
          <div class="col col1">
            <ul class="row row1 row2">
              <li class="label2">毛重</li>
              <li class="value2">${order.grossWeight}</li>
              <li class="label2">皮重</li>
              <li class="value2">${order.tare}</li>
              <li class="label2">净重</li>
              <li class="value2">${order.stdWeight}</li>
            </ul>
            <ul class="row row1 row2">
              <li class="label2">车号</li>
              <li class="value2">${order.licenseNumber}</li>
              <li class="label2">车次</li>
              <li class="value2">${order.trainNumber}</li>
              <li class="label2">方量</li>
              <li class="value2">${order.squareQuantity}</li>
            </ul>
            <ul class="row row1 row2">
              <li class="label2">业务经理</li>
              <li class="value2">${order.serviceManager}</li>
              <li class="label2">电话</li>
              <li class="value2">${order.managerTelephone}</li>
              <li class="label2">累计方量</li>
              <li class="value2">${order.accumulatedVolume}</li>
            </ul>
          </div>
          <ul class="col col2 row row1 row3">
            <li class="label3">收<br>货<br>人</li>
            <li class="value3 b-r-n"></li>
          </ul>
        </div>
      </div>
      <div id="footer">
        <div class="foot">
          <p class="foot-lian"><span>白联：存根╱记账</span> <span>粉联：工地</span> <span>黄联：车辆</span></p>
          <p>说明：未经本公司许可不得在混凝土中加水或其他材料，否则本公司对此引起的质量问题概不负责</p>
        </div>
      </div>
    </div>`;
      // document.getElementById('vue_el').innerHTML = orderHtml;
      // var domStr = styles + "<body>" + document.getElementById("vue_el").innerHTML + "</body>";
      var domStr = styles + "<body>" + orderHtml + "</body>";
      LODOP = getLodop();
      LODOP.SET_PRINT_PAGESIZE(1, '241mm', '93mm', 'CreateCustomPage');
      // LODOP.ADD_PRINT_HTM("2%", "7mm", "200mm", "96%", document.documentElement.innerHTML);
      LODOP.ADD_PRINT_HTM("2%", "7mm", "200mm", "96%", domStr);
      LODOP.PRINT();
      // LODOP.PREVIEW();
    }

    function CheckIsInstall() {
      try {
        var LODOP = getLodop();
        if (LODOP.VERSION) {
          if (LODOP.CVERSION)
            alert("可用!\n 版本:" + LODOP.CVERSION + "(version" + LODOP.VERSION + ")");
          else
            alert("可用!\n 版本号:" + LODOP.VERSION);
        } else {
          alert('不可用');
        }
      } catch (err) {}
    }

    /**
     * 替换 ajax 返回数据中的 键名
     * @param  {[type]} originData [原始数据]
     * @param  {[type]} matterData [键名替换规则]
     */
    function formatterData(originData, matterData) {
      // var matterData = {
      //   '0': { // 级别
      //     originKey: 'newKey',
      //     lineCar: 'LINECAR',
      //   },
      //   '1': {
      //     carNo: 'CARNO',
      //     status: 'STATUS',
      //   }
      // };
      if (!matterData || Object.keys(matterData) < 1) {
        return originData;
      }
      var typeIs = function(o) {
        var s = Object.prototype.toString.call(o);
        return s.match(/\[object (.*?)\]/)[1].toLowerCase();
      };

      function fmData(origin, level, delArr) {
        var matter = matterData[level + ''];
        for (var k in origin) {
          if (typeIs(origin[k]) === 'array') {
            if (delArr && k !== matter[k]) {
              if (k in matter) delete origin[k];
            } else {
              origin[matter[k]] = origin[k];
            }
            ++level;
            fmData(origin[k], level, delArr);
          } else if (typeIs(origin[k]) === 'object') {
            fmData(origin[k], level, delArr);
          } else {
            if (!delArr) {
              if (k in matter && k !== matter[k]) {
                origin[matter[k]] = origin[k];
                delete origin[k];
              }
            }
          }
        }
        return origin;
      }

      var formatObj = fmData(JSON.parse(JSON.stringify(originData)), 0, 0);
      var formatArr = fmData(formatObj, 0, 1);
      return formatArr;
    }
  })();