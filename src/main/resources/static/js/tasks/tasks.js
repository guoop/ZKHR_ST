(function () {
  // 调单
  var taskVue = new Vue({
    el: '#taskvue',
    data: {
      curTaskId: '', // 当前被调单的任务id
      tiaodan: {
        visibility: false,
        taskCarId: '',
        toTaskId: '',
        remark: '',
        tableData: [],
      }
    },
    methods: {
      clickTiaoDan: function (id, row) {
        // console.log(id, row);
        this.tiaodan.toTaskId = row.id;
        if (!this.tiaodan.remark) {
          this.$message.error('请输入备注信息.');
          return;
        }

        var This = this;
        this.fullscreenLoading = true;
        var request = $.ajax({
          url: "/webapp/transTasksToOther",
          type: "POST",
          data: {
            taskCarId: this.tiaodan.taskCarId,
            toTaskId: this.tiaodan.toTaskId,
            remark: this.tiaodan.remark,
          },
          dataType: "json"
        });
        request.done(function (data) {
          console.log(data)
          This.fullscreenLoading = false;
          if (data.code !== 0) {
            layer.alert(data.msg);
            return;
          }
          getFromNet();
          This.tiaodan.visibility = false;
          This.tiaodan.remark = '';
          This.tiaodan.tableData = [];
          This.$message.success(data.msg);
        });
        request.fail(function (jqXHR, textStatus) {
          This.fullscreenLoading = false;
          layer.msg('网络失败');
          console.warn('网络失败', jqXHR);
        });
      }
    }

  });

  // console.log('lodash',_.VERSION, 'jquery:', $.ajax)
  // 全局
  var global_drawEd = {}; // 已经画过的项目
  var global_taskData = []; // 最新一次的任务数据
  var global_drawViewTimer; // 任务详情定时器flag
  var global_var = {
    imgPath: '/img/tasks/', // 图片路径
    prefix: '/duties/', // api 地址 公共路径
    layerOverTask: '', // 结束任务loading
  };

  var global_windowStatus = '';
  var global_isPc = navigator.platform.toLowerCase().indexOf('win') > -1;

  /**
   * 轮询服务器请求任务状态数据
   */
  var timer_count = 0
  getFromNet();
  var timer = setInterval(getFromNet, 6000)
  // global_isPc = false; // 测试手机
  if (!global_isPc) { // 手机端手动刷新
    document.body.classList.add('notpc');
    clearInterval(timer);
    $('.task-handrefresh').show();
    $('.task-handrefresh').click(function () {
      var hand_index = layer.load(2, { time: 20 * 1000, shade: .5 });
      getFromNet();
    });
  }
  /**
   * 绘制任务
   * @param  {[collection]} data [任务集合,从服务接口 每3秒 获取]
   */
  function formaTtotalProgress(progress) {
    var progressArr = progress.split('/');
    return progressArr[0] / progressArr[1] * 100 + '%';
  }

  function drawTask(data) {
    for (var i = 0; i < data.length; i++) {
      // var taskObj_origin = data[i];
      // var taskObj = {
      //   taskName: taskObj_origin.taskName, // 任务名称
      //   totalProgress_str: taskObj_origin.totalProgress, // 任务进度
      //   totalProgress: formaTtotalProgress(taskObj_origin.totalProgress), // 任务进度
      //   taskStatus: taskObj_origin.taskStatus === 'Y' ? 'N' : 'Y', // 任务暂停还是开始
      //   taskId: taskObj_origin.taskId, // 任务id
      //   cars: taskObj_origin.cars, // 任务车辆
      // }
      var taskObj = data[i];
      // console.log(taskObj)
      var taskid = 'taskid_' + taskObj.taskId

      if (taskid in global_drawEd) {
        var line_box = document.querySelector('#' + taskid)
        document.querySelector('#' + taskid + ' .tesk-name').innerHTML = `${taskObj.taskName} (${taskObj.totalProgress_str.replace('%','')})`;
        document.querySelector('#' + taskid + ' .line-inner').style.width = taskObj.totalProgress;
        var btn_start = document.querySelector('#' + taskid + ' .btn-start')
        btn_start.setAttribute('status', taskObj.taskStatus)
        btn_start.setAttribute('taskid', taskObj.taskId)
        btn_start.innerHTML = `
          <img class="btn-img" src="${global_var.imgPath}${taskObj.taskStatus === 'Y' ? 'btn-start' : 'btn-pause'}.png" alt="">
          <div class="btn-img-text">${taskObj.taskStatus === 'Y' ? '进行中' : '已暂停'}</div><br/>
          <button class="hand_task btn btn-info btn-xs ${taskObj.taskStatus === 'Y' ? 'hide' : ''}" taskid="${taskObj.taskId}">派 单</button>
          <button class="over_task btn btn-danger btn-xs" taskid="${taskObj.taskId}">结 束</button>`;
        var line_icon_end = line_box.querySelector('.line-icon_end');
        line_icon_end.style.background = taskObj.warning_color;
        taskObj.warning === 'R' ? line_icon_end.classList.add('shake') : line_icon_end.classList.remove('shake');

        drawLine(taskObj.cars, line_box, taskid)
      } else {
        var line_box = document.createElement('div');
        line_box.className = 'line-box';
        line_box.id = taskid
        var line_html = `
          <div class="line">
            <img class="line-bg" src="${global_var.imgPath}line-bg.png" alt="">
            <div class="line-area"><div class="line-area-con"></div></div>
            <span class="line-icon_start">始</span>
            <span class="line-icon_end ${taskObj.warning === 'R' ? 'shake' : ''}" style="background:${taskObj.warning_color};">终</span>
            <div class="tesk-name">${taskObj.taskName} (${taskObj.totalProgress_str.replace('%','')})</div>
            <div class="line-innerbox"><div class="line-inner" style="width: ${taskObj.totalProgress}"></div></div>
          </div>
          <div class="btn-start" status="${taskObj.taskStatus}" taskid="${taskObj.taskId}">
            <img class="btn-img" src="${global_var.imgPath}${taskObj.taskStatus === 'Y' ? 'btn-start' : 'btn-pause'}.png" alt="">
            <div class="btn-img-text">${taskObj.taskStatus === 'Y' ? '进行中' : '已暂停'}</div><br/>
            <button class="hand_task btn btn-info btn-xs ${taskObj.taskStatus === 'Y' ? 'hide' : ''}" taskid="${taskObj.taskId}">派 单</button>
            <button class="over_task btn btn-danger btn-xs" taskid="${taskObj.taskId}">结 束</button>
          </div>`
        line_box.innerHTML = line_html;
        global_drawEd[taskid] = []
        drawLine(taskObj.cars, line_box, taskid)

        document.querySelector('.line_container').appendChild(line_box)
      }
    }
  }

  /**
   * 绘制车辆和进度
   * @param  {[Collection]} cars 车辆信息
   * @param  {[string]} totalProgress 总进度
   */
  function drawLine(cars, line_box, taskid) {
    var dom_line = line_box.firstElementChild;
    var dom_lineInner = line_box.firstElementChild.lastElementChild;
    if (cars.length <= 0) {
      return;
    }

    for (var i = 0; i < cars.length; i++) {
      var car = cars[i];
      var lineCar;
      // 判断车辆是否已经绘制过
      if (global_drawEd[taskid] && global_drawEd[taskid].indexOf(car.carId) === -1) {
        lineCar = document.createElement('span');
        lineCar.className = lineCarClass(car);
        lineCar.id = 'id_' + car.carId
        lineCar.innerHTML = '<span class="line-car-info">' + car.info + '</span>'
        lineCar.setAttribute('style', computedCarProgress(car.progress, car.status))

        dom_line.insertBefore(lineCar, dom_lineInner)
        // dom_line.appendChild(lineCar)
        global_drawEd[taskid].push(car.carId)
      } else {
        lineCar = document.querySelector('#' + taskid + ' #' + 'id_' + car.carId);
        if (!lineCar) return;

        lineCar.firstElementChild.innerText = car.info;
        lineCar.className = lineCarClass(car)
        lineCar.setAttribute('style', computedCarProgress(car.progress, car.status))
      }
    }

    // setTimeout(function () { coverTest(taskid, cars) }, 1) // 车辆重叠检测
  }

  /**
   * 计算车辆进度
   * @param  {string} progressStr 车辆进度字符串
   * @param  {string} carStatus   [车辆状态]
   * @return {[string]}             [计算后的left值]
   */
  function computedCarProgress(progressStr, carStatus) {
    var progress = carStatus === 'go' ? parseFloat(progressStr) : (100 - parseFloat(progressStr))
    if (progress > 100) {
      progress = 100;
    } else if (progress < 0) {
      progress = 0;
    }
    return 'left:' + progress * 0.92 + '%';
  }

  /**
   * 车辆状态(红色,闪烁等)
   * @param  {[obj]} car [单个车辆对象]
   * @return {[str]} lineCarClassName    [车辆状态对应的 css类名 字符串]
   */
  function lineCarClass(car) {
    var lineCarClassName = car.status === 'go' ? 'line-car_go ' : 'line-car_back ';
    lineCarClassName += car.breakdown === 'Y' ? 'redcar ' : ''
    lineCarClassName += car.waiting === 'Y' ? 'shake ' : ''
    return lineCarClassName;
  }

  /**
   * 车辆重叠检测(有 bug 暂不添加此功能)
   * @param  {[type]} taskid [任务id]
   * @param  {[type]} cars   [任务车辆数组]
   */
  function coverTest(taskid, cars) {
    // console.log(JSON.stringify(cars))
    var goCars = _.filter(cars, ['status', 'go'])
    var backCars = _.filter(cars, ['status', 'back'])
    goCars.sort(function (a, b) {
      return (parseFloat(a.progress) - parseFloat(b.progress))
    });
    backCars.sort(function (a, b) {
      return (parseFloat(a.progress) - parseFloat(b.progress))
    });
    for (var i = 0; i < goCars.length - 1; i++) {
      if (parseFloat(goCars[i + 1].progress) - parseFloat(goCars[i].progress) < 7) {
        // 下一辆与当前车辆重叠
        var curCarInfo = document.querySelector('#' + taskid + ' #' + 'id_' + goCars[i].carId + ' .line-car-info');
        var nextCarInfo = document.querySelector('#' + taskid + ' #' + 'id_' + goCars[i + 1].carId + ' .line-car-info');
        if (!curCarInfo.style.marginTop) {
          nextCarInfo.style.marginTop = '-48px';
        }
      } else {
        nextCarInfo.style = '';
      }
    }
  }

  /**
   * 请求后台数据
   * @return {[type]} [description]
   */
  function getFromNet() {
    // 检测窗口是否已经隐藏，如果隐藏则不要再继续请求数据了
    try {
      if (window.top.document.querySelector('.menuTabs .page-tabs-content .active').getAttribute('data-id') !== document.location.pathname) {
        console.log('调度室不可见，暂停请求后台接口。');
        global_windowStatus = false;
        return;
      } else {
        if (global_windowStatus === false) {
          console.log('调度室切换为可见,刷新页面')
          document.location.reload(false)
        }
        global_windowStatus = true;
      }
    } catch (e) {
      // console.log(e);
    }

    timer_count++;
    // clearInterval(timer)
    // if (timer_count >= 9) { // 测试模拟
    //   clearInterval(timer)
    // };

    var request = $.ajax({
      // url: "/web/dispath",
      url: document.location.pathname === '/duties/tasks/taskdemo' ?  "https://linkhanfeng.applinzi.com/api/api1.php" : '/web/dispath',
      type: "POST",
      data: { test_number: timer_count },
      dataType: "text"
    });

    request.done(function (data) {
      var data = JSON.parse(data.split('<script')[0]);
      console.log(23333, data);
      // if (timer_count >= 3) { // 测试用 需删除
      //   data[0]['cars'].push({ carId: 99, status: "back", progress: timer_count * 10 + "%", info: "99新/30分钟" })
      // }
      layer.closeAll();
      if (data.code != 0) {
        console.log('数据获取失败，msg::', data.msg);
        return;
      }
      layer.close(global_var.layerOverTask);
      var taskData = formatTaskData(data.data);
      console.log('taskData::', data.data, taskData);
      removeTaskAndCar(global_taskData, taskData);
      global_taskData = taskData;
      drawTask(taskData)
    });

    request.fail(function (jqXHR, textStatus) {
      console.log('接口调用失败', jqXHR)
    });
  }

  /**
   * 删除不存在的任务和车辆
   */
  function removeTaskAndCar(global_taskData, taskData) {
    var lastIds = getIds(global_taskData);
    var nowIds = getIds(taskData);
    // console.log(lastIds, nowIds);
    var deleteTaskIds = lastIds.taskIds.filter(function (ele) {
      return nowIds.taskIds.indexOf(ele) === -1;
    });
    var deleteCarIds = lastIds.carIds.filter(function (ele) {
      return nowIds.carIds.indexOf(ele) === -1;
    });
    // console.log('deleteTaskIds::', deleteTaskIds, 'deleteCarIds::', deleteCarIds);
    for (var i = 0; i < deleteTaskIds.length; i++) {
      var taskdom = document.querySelector('#taskid_' + deleteTaskIds[i]);
      taskdom.parentNode.removeChild(taskdom);
    }
    for (var j = 0; j < deleteCarIds.length; j++) {
      var cardom = document.querySelector('#id_' + deleteCarIds[j]);
      if (!cardom) return;
      cardom.parentNode.removeChild(cardom);
    }
  }

  function getIds(data) {
    var taskIds = data.map(function (ele) {
      return ele.taskId;
    });
    var carIds = [];
    for (var i = 0; i < data.length; i++) {
      var oneTaskCarIds = data[i].cars.map(function (ele) {
        return ele.carId;
      });
      carIds = carIds.concat(oneTaskCarIds);
    }
    return {
      taskIds: taskIds,
      carIds: carIds,
    };
  }
  /**
   * 格式化返回的接口数据
   */
  function formatTaskData(data) {
    // console.log('原始数据::', data);
    var newData = [];
    var taskFileds = {
      taskName: 'taskName', // 任务名称
      totalProgress_str: 'totalProgress', // 放量和总方量
      taskId: 'taskId', // 任务id
      cars: 'cars', // 任务车辆
      runList: 'runList', // 运行记录
      totalProgress: function (originData) { // 任务进度百分比
        var progressArr = originData.totalProgress.split('/');
        return (progressArr[0] / progressArr[1] * 100).toFixed(2) + '%';
      },
      taskStatus: function (originData) { // 任务暂停还是开始
        return originData.taskStatus === 'Y' ? 'N' : 'Y';
      },
      warning: 'warning',
      warning_color: function (originData) { // 终点状态 测试
        if (originData.warning === 'R') {
          return '#f00';
        } else if (originData.warning === 'Y') {
          return '#ff0';
        } else {
          return '#854334';
        }
      },
    };
    var carFileds = {
      carId: 'carId', // 车辆id
      id: 'id',
      // info: 'info', // 车号和时间 '2号/12分钟' '2号/停留12分钟'
      info: 'info', // 车号和时间 '2号/12分钟' '2号/停留12分钟'
      carNo: function (car_origin) { // 车号
        return car_origin.info.split('/')[0];
      },
      runTime: function (car_origin) { // 运行时间
        if (car_origin.info.indexOf('停留') > -1) {
          return ' ';
        } else {
          return car_origin.info.split('/')[1];
        }
      },
      stopTime: function (car_origin) { // 停留时间
        if (car_origin.info.indexOf('停留') === -1) {
          return ' ';
        } else {
          return car_origin.info.split('/')[1].replace('停留', '');
        }
      },
      progress: 'process', // 车辆进度
      status: 'status', // 去程车辆，还是回程车辆
      breakdown: function (car_origin) { // 故障
        return car_origin.breakdown ? car_origin.breakdown : 'N';
      },
      waiting: function (car_origin) { // 闪烁
        return car_origin.waiting ? car_origin.waiting : 'N';
      },
    };
    for (var i = 0; i < data.length; i++) {
      var taskTemp = {};
      for (var key in taskFileds) {
        if (taskFileds[key] === 'cars') {
          var arrj = data[i][taskFileds[key]];
          var carArr = [];
          for (var j = 0, lenj = arrj.length; j < lenj; j++) {
            var carTemp = {};
            for (var kb in carFileds) {
              if (typeof carFileds[kb] === 'function') {
                carTemp[kb] = (carFileds[kb])(arrj[j]);
              } else {
                carTemp[kb] = arrj[j][carFileds[kb]];
              }
            }
            carArr.push(carTemp);
          }
          taskTemp[key] = carArr;
          continue;
        }
        if (typeof taskFileds[key] === 'function') {
          taskTemp[key] = (taskFileds[key])(data[i]);
        } else {
          taskTemp[key] = data[i][taskFileds[key]];
        }
      }
      newData[i] = taskTemp;
    }
    // console.log('格式数据::', newData);
    return newData;
  }

  $('.line_container').on("click", ".btn-img", function () {
    startOrPause.call(this)
  })

  /**
   * 暂停开始按钮
   */
  function startOrPause() {
    var This = $(this).parent();
    var taskId = This.attr('taskid'),
      taskStatus = This.attr('status');
    // taskId = 7; // 测试数据
    var msg = taskStatus === 'Y' ? '确认要暂停任务单吗？' : '确认要开启任务单吗？';

    $.modal.confirm(msg, function () {
      var request = $.ajax({
        // url: "http://101.200.50.35/api2.php", // 开始暂停任务接口
        url: global_var.prefix + "tasks/changeStatus", // 开始暂停任务接口
        type: "POST",
        data: {
          id: taskId,
          pause: taskStatus,
        },
        dataType: "json"
      });

      request.done(function (data) {
        if (data.code !== 0) {
          layer.alert('操作失败. ' + data.msg)
          return
        }
        if (taskStatus === 'Y') {
          This.attr('status', 'N')
          This.children('.btn-img').attr('src', global_var.imgPath + 'btn-pause.png')
          This.children('.btn-img-text').text('已暂停')
          This.children('.hand_task').removeClass('hide')
        } else {
          This.attr('status', 'Y')
          This.children('.btn-img').attr('src', global_var.imgPath + 'btn-start.png')
          This.children('.btn-img-text').text('进行中')
          This.children('.hand_task').addClass('hide')
        }
      });

      request.fail(function (jqXHR, textStatus) {
        console.log('接口调用失败', jqXHR)
      });
    })
  }

  /**
   * 车辆信息转为表格形式
   * @param  {[array]} cars [车辆信息数组]
   * @return {[string]} goList [html字符串]
   * 车辆信息增加: 出发时间, 每车放量, 主机楼
   */
  function carInfoList(cars) {
    var carsLen = cars.length;
    var goList = carsLen < 1 ? '<ul class="tv-list">' : '<ul class="tv-list"><li class="tv-thead"><span class="tv-col">车号</span> <span class="tv-col">进度</span> <span class="tv-col">运行时间</span> <span class="tv-col">停留时间</span> <span class="tv-col">调单</span></li>'
    for (var i = 0; i < carsLen; i++) {
      goList += `<li>
      <span class="tv-col">${cars[i].carNo}</span>
      <span class="tv-col tv-progress">${cars[i].progress}</span>
      <span class="tv-col">${cars[i].runTime}</span>
      <span class="tv-col">${cars[i].stopTime}</span>
      <span class="tv-col"><a href="javascript:;" data-taskcarid="${cars[i].id}" class="btn btn-info btn-xs tiaodan_btn">调单</a></span>
      </li>`;
    }
    goList += '</ul>';
    return goList;
  }

  function runCarInfoList(cars) {
    var carsLen = cars.length;
    var goList = carsLen < 1 ? '<ul class="tv-list">' : '<ul class="tv-list"><li class="tv-thead"><span class="tv-col">车号</span> <span class="tv-col">车次</span> <span class="tv-col">实际方量</span> <span class="tv-col">累计方量</span> <span class="tv-col">调单</span></li></li>'
    for (var i = 0; i < carsLen; i++) {
      goList += `<li>
      <span class="tv-col">${cars[i].carNo}</span>
      <span class="tv-col tv-progress">${cars[i].carCnt}</span>
      <span class="tv-col">${cars[i].equalFangliang}</span>
      <span class="tv-col">${cars[i].ljfangliang}</span>
      <span class="tv-col"><a href="javascript:;" data-taskcarid="${cars[i].id}" class="btn btn-info btn-xs tiaodan_btn">调单</a></span>
      </li>`;
    }
    goList += '</ul>';
    return goList;
  }

  /**
   * 任务统计信息,插入html文档中
   * @param  {[string]} taskId [当前鼠标移入的任务 id]
   * @param  {[dom]} lineArea_dom [插入信息的父级元素]
   */
  function taskViewInfo(taskId, lineArea_dom) {
    var taskObj = _.find(global_taskData, ['taskId', parseInt(taskId)])
    var goCars = _.filter(taskObj.cars, ['status', 'go'])
    var backCars = _.filter(taskObj.cars, ['status', 'back'])
    // debugger
    // console.log(goCars, backCars)
    goCars.sort(function (a, b) { return (parseFloat(a.progress) - parseFloat(b.progress)) });
    backCars.sort(function (a, b) { return (parseFloat(a.progress) - parseFloat(b.progress)) });
    var runcars = taskObj.runList;
    var taskViewInfostr = `
        <p class="tv-p"><span class="tv-label">任务名称:</span> ${taskObj.taskName}</p>
        <p class="tv-p"><span class="tv-label">任务状态:</span> ${taskObj.taskStatus === 'Y' ? '进行中' : '已暂停'}</p>
        <p class="tv-p"><span class="tv-label">任务进度:</span> ${taskObj.totalProgress}</p>
        <div class="tv-cars"><span class="tv-label">去程车辆: ( ${goCars.length}/辆 )</span> ${carInfoList(goCars)}</div>
        <div class="tv-cars"><span class="tv-label">返程车辆: ( ${backCars.length}/辆 )</span> ${carInfoList(backCars)}</div>
        <div class="tv-cars"><span class="tv-label">运行记录: ( ${runcars.length}/辆 )</span> ${runCarInfoList(runcars)}</div>
      `;
    lineArea_dom.html(taskViewInfostr)
  }

  /**
   * 鼠标移入显示详细信息,移出隐藏
   */
  $('.line_container').on("mouseenter", ".line-area", function () {
    var taskId = $(this).parents('.line-box').attr('id').replace('taskid_', '');
    taskVue.curTaskId = taskId;
    var lineArea_dom = $(this).children('.line-area-con');
    clearInterval(global_drawViewTimer)
    taskViewInfo(taskId, lineArea_dom);
    global_drawViewTimer = setInterval(taskViewInfo, 5000, taskId, lineArea_dom) // 每 5s 重新更新一次 dom 结构
    $(this).css('z-index', 4).height(lineArea_dom.innerHeight())
  })
  $('.line_container').on("mouseleave", ".line-area", function () {
    clearInterval(global_drawViewTimer)
    $(this).css('z-index', 3).height('0px')
  })

  /**
   * 点击手动派单按钮
   */
  $('.line_container').on('click', '.hand_task', function () {
    var taskId = $(this).attr('taskid')
    $.modal.openTab('手动派单', global_var.prefix + 'tasks/handTask?taskid=' + taskId)
  })

  /**
   * 结束任务
   */
  $('.line_container').on('click', '.over_task', function () {
    var taskId = $(this).attr('taskid')
    var msg = '确认要结束任务吗?'
    // console.log(taskId)
    $.modal.confirm(msg, function () {
      global_var.layerOverTask = layer.load(1, {
        shade: [0.2, '#000']
      });
      var request = $.ajax({
        url: global_var.prefix + "tasks/overorder", // 结束任务接口
        type: "POST",
        data: {
          id: taskId,
        },
        dataType: "json"
      });

      request.done(function (data) {
        if (data.code !== 0) {
          layer.alert('操作失败. ' + data.msg)
          return
        } else {
          // layer.msg(data.msg)
        }
      });

      request.fail(function (jqXHR, textStatus) {
        console.log('接口调用失败', jqXHR)
      });
    })
  })


  /**
   * 调单
   */
  $('.line_container').on('click', '.tiaodan_btn', function () {
    var taskCarId = $(this).attr('data-taskcarid');
    tiaodan(taskCarId);
  })

  function tiaodan(taskCarId) {
    taskVue.tiaodan.taskCarId = taskCarId;
    taskVue.tiaodan.visibility = true;
    $.modal.loading('loading...')
    var request = $.ajax({
      url: "/webapp/getTaskListToTrans",
      type: "POST",
      data: {
        taskCarId: taskCarId,
      },
      dataType: "json"
    });
    request.done(function (data) {
      console.log(data)
      $.modal.closeLoading()
      if (data.code !== 0) {
        layer.alert(data.msg);
        return;
      }
      taskVue.tiaodan.tableData = data.list;
    });
    request.fail(function (jqXHR, textStatus) {
      $.modal.closeLoading()
      layer.msg('网络失败');
      console.warn('网络失败', jqXHR);
    });
  }

})()