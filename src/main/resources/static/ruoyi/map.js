// 百度地图API功能
var map = new BMap.Map("allmap");    // 创建Map实例
if(location.pathname.indexOf('edit') > -1){
    var editLon = $("#lon").val();
    var editLat = $("#lat").val();
    var editPoint = new BMap.Point( editLon, editLat);
    // console.log(23333,editPoint);
    map.addOverlay(new BMap.Marker(editPoint));
    map.centerAndZoom(editPoint, 18);  // 初始化地图,设置中心点坐标和地图级别
}else{
    map.centerAndZoom(new BMap.Point(114.426468, 35.094869), 18);  // 初始化地图,设置中心点坐标和地图级别
}
// 添加带有定位的导航控件
var navigationControl = new BMap.NavigationControl({
    // 靠左上角位置
    anchor: BMAP_ANCHOR_TOP_RIGHT,
    // LARGE类型
    type: BMAP_NAVIGATION_CONTROL_LARGE,
    // 启用显示定位
    enableGeolocation: true
});
map.addOverlay(new BMap.Marker(new BMap.Point(114.426468, 35.094869)));
map.enableScrollWheelZoom(true);
map.addControl(navigationControl);
var geoc = new BMap.Geocoder();
//单击获取点击的经纬度
map.addEventListener("click", function (e) {
    // alert(e.point.lng + "," + e.point.lat);
    map.clearOverlays();
    var point = e.point;

    geoc.getLocation(point, function (rs) {
        // console.log($("#name"))
        // $("#name").val(rs.address);
        // console.log($("#name").val())
        $("#lon").val(point.lng);
        $("#lat").val(point.lat);
    });


    map.addOverlay(new BMap.Marker(e.point));
});

var ac = new BMap.Autocomplete(    //建立一个自动完成的对象
    {
        "input": "name"
        , "location": map
    });

ac.addEventListener("onhighlight", function (e) {  //鼠标放在下拉列表上的事件
    var str = "";
    var _value = e.fromitem.value;
    var value = "";
    if (e.fromitem.index > -1) {
        value = _value.province + _value.city + _value.district + _value.street + _value.business;
    }
    str = "FromItem<br />index = " + e.fromitem.index + "<br />value = " + value;

    value = "";
    if (e.toitem.index > -1) {
        _value = e.toitem.value;
        value = _value.province + _value.city + _value.district + _value.street + _value.business;
    }
    str += "<br />ToItem<br />index = " + e.toitem.index + "<br />value = " + value;
    G("searchResultPanel").innerHTML = str;
});

var myValue;
ac.addEventListener("onconfirm", function (e) {    //鼠标点击下拉列表后的事件
    var _value = e.item.value;
    myValue = _value.province + _value.city + _value.district + _value.street + _value.business;
    G("searchResultPanel").innerHTML = "onconfirm<br />index = " + e.item.index + "<br />myValue = " + myValue;

    setPlace();
});

function setPlace() {
    map.clearOverlays();    //清除地图上所有覆盖物
    function myFun() {
        var pp = local.getResults().getPoi(0).point;    //获取第一个智能搜索的结果
        map.centerAndZoom(pp, 18);
        // map.addOverlay(new BMap.Marker(pp));    //添加标注
    }

    var local = new BMap.LocalSearch(map, { //智能搜索
        onSearchComplete: myFun
    });
    local.search(myValue);
}

// 百度地图API功能
function G(id) {
    return document.getElementById(id);
}