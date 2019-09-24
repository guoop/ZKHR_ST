商砼站---实时监测项目
  var request = $.ajax({
    url: "http://localhost:8099/cemslink/mixformula/list", // 任务单
    // url: "./api/list.json", // 任务单
    type: "POST",
    data: {
    },
    dataType: "json"
  });