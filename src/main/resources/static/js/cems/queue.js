$(document).ready(function() {
  var ajaxUrl = {
    hostStatus: '/web/productList', // 主机状态
    carStatus: '/web/getCarList', // 主副班车辆
    // carStatus: 'http://101.200.50.35/api2.php',
    plannedCars: '/web/plancaingList', // 每小时计划用车
    notify: '/web/notify_freecar', // 通知副班车辆
    offlineDriver: '/web/offlineDriver', // 下线主班车辆
  };
  var hostStatus = [{
      hostName: '1 号主机',
      lineCar: [{
        carNo: '4 号车',
        door: '北门',
        status: '接料',
      }, {
        carNo: '6 号车',
        door: '1 号门',
        status: '准备',
      }, {
        carNo: '9 号车',
        door: '北门',
        status: '排队',
      }],
    },
    {
      hostName: '2 号主机',
      lineCar: [{
        carNo: '1 号车',
        door: '南门',
        status: '接料',
      }, {
        carNo: '2 号车',
        door: '2 号门',
        status: '准备',
      }, {
        carNo: '8 号车',
        door: '南门',
        status: '排队',
      }],
    },
  ];
  var carsData = {
    mastList: [
      // { carNo: '1', fangl: '15' },
    ],
    slaveList: [
      // { carNo: '2', fangl: '21' },
    ],
  };

  var plannedCars = {
    xAxis: ['1', '7', '3', '4', '5', '6', '7', '8', '9', '10', '11', '12', '13', '14', '15', '16', '17', '18', '19', '20', '21', '22', '23', '24'],
    yAxis: [8, 18, 30, 40, 53, 30, 40, 20, 30, 50, 40, 8, 18, 14, 32, 12, 30, 40, 53, 30, 40, 20, 30, 50, ],
  };

  // 主机状态
  var vm_host = new Vue({
    el: '#vm_host',
    data: {
      hosts: hostStatus,
    },
    methods: {}
  });

  // 主副班车辆
  var vm_cars = new Vue({
    el: '#vm_cars',
    data: {
      title: '主班车辆',
      carsData: carsData,
    }
  });

  function fmstate(str) {
    if (str == 1) {
      return '正在打灰';
    } else if (str == 2) {
      return '排队';
    } else if (str === null) {
      return '准备';
    } else {
      return '未知';
    }
  }

  function getHostData() {
    $.ajax({
      // url: "http://101.200.50.35/api2.php",
      url: ajaxUrl.hostStatus,
      type: "POST",
      data: { flag: '1' },
      dataType: "json"
    }).done(function(data) {
      if (data.code !== 0) {
        return;
      }

      var list_line1 = Array.isArray(data.data.list_line1) ? data.data.list_line1 : [];
      var list_line2 = Array.isArray(data.data.list_line2) ? data.data.list_line2 : [];

      for (let i = 0; i < list_line1.length; i++) {
        list_line1[i].state = fmstate(list_line1[i].state);
      }
      for (let i = 0; i < list_line2.length; i++) {
        list_line2[i].state = fmstate(list_line2[i].state);
      }
      // console.log(list_line1, list_line2)
      var hostStatus = [{
          hostName: '1 号主机',
          lineCar: list_line1,
        },
        {
          hostName: '2 号主机',
          lineCar: list_line2,
        },
      ];

      vm_host.hosts = hostStatus;
    }).fail(function(jqXHR, textStatus) {
      console.log('接口调用失败', jqXHR);
    });
  }

  function getCarsData() {
    $.ajax({
      url: ajaxUrl.carStatus,
      type: "POST",
      data: { flag: '2' },
      dataType: "json"
    }).done(function(data) {
      if (data.code != 0) {
        console.log('数据获取失败，msg::', data.msg);
        return;
      }
      var newData = {
        mastList: Array.isArray(data.mastList) ? data.mastList : [],
        slaveList: Array.isArray(data.slaveList) ? data.slaveList : [],
      };
      vm_cars.carsData = newData;
    }).fail(function(jqXHR, textStatus) {
      console.log('接口调用失败', jqXHR);
    });
  }
  // 计划用车数量
  var myChart = echarts.init(document.getElementById('main'));
  option = {
    xAxis: {
      type: 'category',
      // data: plannedCars.xAxis,
      data: [],
    },
    yAxis: {
      type: 'value',
      name: '车辆数量',
      min: 0,
      max: function(value) {
        return value.max > 60 ? value.max : 60;
      },
    },
    series: [{
      type: 'bar',
      label: {
        show: true,
        position: 'top',
      },
      itemStyle: {
        color: function(item) {
          if (item.value <= 10) {
            return '#337ab7';
          } else if (item.value <= 20) {
            return '#398439';
          } else if (item.value <= 30) {
            return '#5bc0de';
          } else if (item.value <= 40) {
            return '#f0ad4e';
          } else {
            return '#d9534f';
          }
          // console.log(item.value);
        }
      },
      // data: plannedCars.yAxis,
      data: [],
    }],
    tooltip: {
      formatter: '{b}点 -> {c}辆车'
    },
    color: ['#337ab7'],
    barCategoryGap: '4%',
    grid: {
      top: 30,
      left: 30,
      right: 10,
      bottom: 20,
    },
    textStyle: {
      fontSize: 14,
    },
  };

  myChart.setOption(option);

  function getPlaneCarData() {
    $.ajax({
      // url: "http://101.200.50.35/api2.php",
      url: ajaxUrl.plannedCars,
      type: "POST",
      data: { flag: '3' },
      dataType: "json"
    }).done(function(data) {
      if (data.code != 0) {
        console.log('数据获取失败，msg::', data.msg);
        return;
      }
      var dataMap = data.map;
      var xAxis = [];
      var series = [];
      for (var keym in dataMap) {
        xAxis.push(keym);
        series.push(dataMap[keym]);
      }
      // console.log(xAxis, series);
      myChart.setOption({
        xAxis: {
          data: xAxis,
        },
        series: [{
          data: series,
        }]
      });
    }).fail(function(jqXHR, textStatus) {
      console.log('接口调用失败', jqXHR);
    });
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

  // 主机状态 // 5秒 一次
  getHostData();
  setInterval(function() {
    getHostData();
  }, 5000);
  // 待接料车辆 // 10秒 一次
  getCarsData();
  setInterval(function() {
    getCarsData();
  }, 10000);
  // 计划用车 10分钟 一次
  getPlaneCarData();
  setInterval(function() {
    getPlaneCarData();
  }, 600000);

  /**
   * 允许副班车辆
   */
  $('.onoffswitch-checkbox').change(function() {
    var ischecked = $(this).is(':checked');
  });
  // 通知副班车辆
  $('#fuban').on('click', '.btn', function() {
    $(this).toggleClass('cars-caractive');
  });
  $('#fuban_title').click(function() {
    $('#fuban_body').slideToggle();
  });
  // 全选车辆
  $('#cars_checkbox').change(function() {
    var ischecked = $(this).is(':checked');
    if (ischecked) {
      $('#fuban .btn').addClass('cars-caractive');
    } else {
      $('#fuban .btn').removeClass('cars-caractive');
    }
  });
  // 发送通知请求
  $('#tongzhicheliang').click(function() {
    var isAllow = $('.onoffswitch-checkbox').is(':checked');
    if (!isAllow) {
      layer.alert('需要允许副班车辆');
      return;
    }
    if ($('#fuban .cars-caractive').length < 1) {
      layer.alert('请选择需要通知的副班车辆');
      return;
    }
    var carnos = [];
    $('#fuban .cars-caractive').each(function() {
      carnos.push($(this).attr('data-carno'));
    });
    console.log(carnos);

    layer.load(1);
    var request = $.ajax({
      url: ajaxUrl.notify,
      type: "POST",
      data: {
        carNos: carnos.join(','),
        content: '您好，调度在召唤您，站内任务单较多，请您尽快赶到博力商砼站',
      },
      dataType: "json"
    });
    request.done(function(data) {
      layer.closeAll('loading');
      if (data.code !== 0) {
        layer.alert(data.msg);
        return;
      }
      layer.alert(data.msg);
    });
    request.fail(function(jqXHR, textStatus) {
      layer.msg('网络失败');
      console.warn('网络失败', jqXHR);
    });
  });
  // 下线主班车辆
  $('#zhuban').on('click', '.btn', function() {
    $(this).toggleClass('cars-caractive');
  });
  $('#xixiancheliang').click(function() {
    if ($('#zhuban .cars-caractive').length < 1) {
      layer.alert('请选择需要下线的车辆');
      return;
    }
    var carnos = [];
    $('#zhuban .cars-caractive').each(function() {
      carnos.push($(this).attr('data-carno'));
    });
    console.log(carnos);

    layer.confirm('确定要下线选中的车辆吗？', {
      btn: ['确定','取消'] //按钮
    }, function(){
      offlineAjax();
    }, function(){
      layer.closeAll();
    });

    function offlineAjax(){
      layer.load(1);
      var request = $.ajax({
        url: ajaxUrl.offlineDriver,
        type: "POST",
        data: {
          carNos: carnos.join(','),
        },
        dataType: "json"
      });
      request.done(function(data) {
        layer.closeAll('loading');
        if (data.code !== 0) {
          layer.alert(data.msg);
          return;
        }
        layer.alert(data.msg, function(index){
          location.reload(false)
        });
      });
      request.fail(function(jqXHR, textStatus) {
        layer.msg('网络失败');
        console.warn('网络失败', jqXHR);
      });
    }

  });


});