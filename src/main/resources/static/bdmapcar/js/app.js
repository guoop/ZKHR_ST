(function (t) {
    function e(e) {
        for (var a, r, s = e[0], l = e[1], c = e[2], u = 0, f = []; u < s.length; u++) r = s[u], i[r] && f.push(i[r][0]), i[r] = 0;
        for (a in l) Object.prototype.hasOwnProperty.call(l, a) && (t[a] = l[a]);
        p && p(e);
        while (f.length) f.shift()();
        return o.push.apply(o, c || []), n()
    }

    function n() {
        for (var t, e = 0; e < o.length; e++) {
            for (var n = o[e], a = !0, r = 1; r < n.length; r++) {
                var l = n[r];
                0 !== i[l] && (a = !1)
            }
            a && (o.splice(e--, 1), t = s(s.s = n[0]))
        }
        return t
    }

    var a = {}, i = {app: 0}, o = [];

    function r(t) {
        return s.p + "bdmapcar/js/" + ({about: "about"}[t] || t) + ".js"
    }

    function s(e) {
        if (a[e]) return a[e].exports;
        var n = a[e] = {i: e, l: !1, exports: {}};
        return t[e].call(n.exports, n, n.exports, s), n.l = !0, n.exports
    }

    s.e = function (t) {
        var e = [], n = i[t];
        if (0 !== n) if (n) e.push(n[2]); else {
            var a = new Promise(function (e, a) {
                n = i[t] = [e, a]
            });
            e.push(n[2] = a);
            var o, l = document.createElement("script");
            l.charset = "utf-8", l.timeout = 120, s.nc && l.setAttribute("nonce", s.nc), l.src = r(t), o = function (e) {
                l.onerror = l.onload = null, clearTimeout(c);
                var n = i[t];
                if (0 !== n) {
                    if (n) {
                        var a = e && ("load" === e.type ? "missing" : e.type), o = e && e.target && e.target.src,
                            r = new Error("Loading chunk " + t + " failed.\n(" + a + ": " + o + ")");
                        r.type = a, r.request = o, n[1](r)
                    }
                    i[t] = void 0
                }
            };
            var c = setTimeout(function () {
                o({type: "timeout", target: l})
            }, 12e4);
            l.onerror = l.onload = o, document.head.appendChild(l)
        }
        return Promise.all(e)
    }, s.m = t, s.c = a, s.d = function (t, e, n) {
        s.o(t, e) || Object.defineProperty(t, e, {enumerable: !0, get: n})
    }, s.r = function (t) {
        "undefined" !== typeof Symbol && Symbol.toStringTag && Object.defineProperty(t, Symbol.toStringTag, {value: "Module"}), Object.defineProperty(t, "__esModule", {value: !0})
    }, s.t = function (t, e) {
        if (1 & e && (t = s(t)), 8 & e) return t;
        if (4 & e && "object" === typeof t && t && t.__esModule) return t;
        var n = Object.create(null);
        if (s.r(n), Object.defineProperty(n, "default", {
            enumerable: !0,
            value: t
        }), 2 & e && "string" != typeof t) for (var a in t) s.d(n, a, function (e) {
            return t[e]
        }.bind(null, a));
        return n
    }, s.n = function (t) {
        var e = t && t.__esModule ? function () {
            return t["default"]
        } : function () {
            return t
        };
        return s.d(e, "a", e), e
    }, s.o = function (t, e) {
        return Object.prototype.hasOwnProperty.call(t, e)
    }, s.p = "/", s.oe = function (t) {
        throw console.error(t), t
    };
    var l = window["webpackJsonp"] = window["webpackJsonp"] || [], c = l.push.bind(l);
    l.push = e, l = l.slice();
    for (var u = 0; u < l.length; u++) e(l[u]);
    var p = c;
    o.push([0, "chunk-vendors"]), n()
})({
    0: function (t, e, n) {
        t.exports = n("56d7")
    }, "034f": function (t, e, n) {
        "use strict";
        var a = n("64a9"), i = n.n(a);
        i.a
    }, "56d7": function (t, e, n) {
        "use strict";
        n.r(e);
        n("cadf"), n("551c"), n("f751"), n("097d");
        var a = n("2b0e"), i = function () {
                var t = this, e = t.$createElement, n = t._self._c || e;
                return n("div", {attrs: {id: "app"}}, [n("router-view")], 1)
            }, o = [], r = {name: "app"}, s = r, l = (n("034f"), n("2877")),
            c = Object(l["a"])(s, i, o, !1, null, null, null), u = c.exports, p = n("8c4f"), f = function () {
                var t = this, e = t.$createElement, n = t._self._c || e;
                return n("div", {staticClass: "home"}, [n("div", {staticClass: "task-select"}, [n("el-cascader", {
                    attrs: {
                        options: t.selects.options,
                        props: t.selects.props
                    }, on: {
                        "visible-change": function (e) {
                            return t.selectVisible(e)
                        }, change: t.selectsChange
                    }, model: {
                        value: t.selects.value, callback: function (e) {
                            t.$set(t.selects, "value", e)
                        }, expression: "selects.value"
                    }
                })], 1), n("div", {staticClass: "car-map", attrs: {id: "car_map"}})])
            }, d = [], h = (n("456d"), n("55dd"), n("ac6a"), n("7f7f"), function (t) {
                return new BMap.Point(t.lng, t.lat)
            }), m = function (t) {
                return new BMap.Marker(h(t))
            }, g = function (t) {
                var e = m(t.point), n = new BMap.Label(t.label, {offset: new BMap.Size(-5, -24)});
                return n.setStyle({fontSize: "12px", maxWidth: "100px", textAlign: "center"}), e.setLabel(n), e
            }, v = function () {
                var t = arguments.length > 0 && void 0 !== arguments[0] ? arguments[0] : {}, e = t.uri,
                    n = void 0 === e ? "/car.png" : e, a = t.width, i = void 0 === a ? 20 : a, o = t.height,
                    r = void 0 === o ? 40 : o, s = document.createElement("canvas");
                s.width = i, s.height = r;
                var l = s.getContext("2d");
                return new Promise(function (t, e) {
                    var a = new Image;
                    a.onload = function () {
                        l.drawImage(a, 0, 0, i, r);
                        var e = s.toDataURL();
                        t(e)
                    }, a.onerror = function () {
                        e(new Error("Could not load image from " + n))
                    }, a.src = n
                })
            }, b = function (t, e, n, a) {
                return n / a * (e - t) + t
            }, y = function (t, e, n, a) {
                var i = n.pointToPixel(t), o = n.pointToPixel(e), r = 0;
                if (o.x != i.x) {
                    var s = (o.y - i.y) / (o.x - i.x), l = Math.atan(s);
                    r = 360 / (2 * Math.PI) * l, r = o.x > i.x ? r + 90 : r - 90, a.setRotation(r)
                } else r = o.y - i.y > 0 ? 180 : 0, a.setRotation(r)
            }, w = function (t, e, n, a) {
                var i = arguments.length > 4 && void 0 !== arguments[4] ? arguments[4] : 3e3,
                    o = arguments.length > 5 ? arguments[5] : void 0;
                o = o || i / 30;
                var r = 0, s = n.getMapType().getProjection().lngLatToPoint(t),
                    l = n.getMapType().getProjection().lngLatToPoint(e), c = parseInt(i / o), u = setInterval(function () {
                        if (r >= c) clearInterval(u); else {
                            0 === r && y(t, e, n, a), r++;
                            var i = b(s.x, l.x, r, c), o = b(s.y, l.y, r, c),
                                p = n.getMapType().getProjection().pointToLngLat(new BMap.Pixel(i, o));
                            a.setPosition(p)
                        }
                    }, o)
            }, P = n("2ef0"), M = n("5ef9"), x = n.n(M), L = {
                name: "home", data: function () {
                    return {
                        apiUrls: {
                            base: "",
                            lines: "/duties/tasks/device/location",
                            tasks: "/duties/tasks/run/list",
                            carList: "/cars/car/listAll"
                        },
                        selects: {
                            value: [],
                            options: [{label: "任务", value: "task", children: []}, {
                                label: "车号",
                                value: "car",
                                children: []
                            }, {label: "全部车辆", value: "allCar"}]
                        },
                        pointsParams: {TaskId: "", carId: ""},
                        isFirstGetLines: !0,
                        mp: "",
                        map: {
                            mapCenter: "",
                            mapZoom: 12,
                            lineColors: [{bg: "#DC143C", font: "#000"}, {bg: "#FFA500", font: "#000"}, {
                                bg: "#006400",
                                font: "#fff"
                            }, {bg: "#FFFF00", font: "#000"}, {bg: "#191970", font: "#fff"}, {
                                bg: "#4B0082",
                                font: "#fff"
                            }, {bg: "#FF69B4", font: "#fff"}, {bg: "#F4A460", font: "#fff"}, {
                                bg: "#32CD32",
                                font: "#fff"
                            }, {bg: "#FFD700", font: "#000"}, {bg: "#00BFFF", font: "#fff"}, {bg: "#9932CC", font: "#fff"}],
                            autoSetViewport: !0,
                            carIcon: ""
                        },
                        getLinesTime: 5e3,
                        lines: [],
                        lastLines: [],
                        carIconBase64: "",
                        cache: {createMap: !0, test_n: 0, maxPoint: {lng: 0, lat: 0}, timer: ""}
                    }
                }, mounted: function () {
                    function t(t) {
                        return new Promise(function (e, n) {
                            setTimeout(e, t, "延迟了 ".concat(t, " 毫秒"))
                        })
                    }

                    this.cache.createMap && (this.map.mapCenter = {
                        lable: "",
                        point: {lng: 113.66562119915868, lat: 34.77184921365784}
                    }, this.createMap()), t(1e3).then(function (t) {
                    }), this.getMapPoints()
                }, methods: {
                    selectVisible: function (t) {
                        !0 === t && (this.getTaskList(), this.getCarList())
                    }, getTaskList: function () {
                        var t = this;
                        this.$get(this.apis.tasks).then(function (e) {
                            if (0 === e.code) {
                                var n = e.data.map(function (t) {
                                    return {label: t.name, value: t.id}
                                });
                                t.selects.options[0].children = n
                            }
                        })
                    }, getCarList: function () {
                        var t = this;
                        this.$post(this.apis.carList).then(function (e) {
                            if (0 === e.code) {
                                var n = e.data.map(function (t) {
                                    return {label: t.carNum, carNo: t.carNo, value: t.id}
                                });
                                t.selects.options[1].children = n
                            }
                        })
                    }, selectsChange: function (t) {
                        console.log(t), this.mp.clearOverlays(), "task" === t[0] ? (this.pointsParams = {
                            TaskId: t[1],
                            carId: ""
                        }, this.getMapPoints()) : "car" === t[0] ? (this.pointsParams = {
                            TaskId: "",
                            carId: t[1]
                        }, this.getMapPoints()) : "allCar" === t[0] && (this.pointsParams = {
                            TaskId: "",
                            carId: ""
                        }, this.getMapPoints())
                    }, getMapPoints: function (t) {
                        var e = this, n = function () {
                            e.$post(e.apis.lines, e.pointsParams, {isLoading: e.isFirstGetLines}).then(function (t) {
                                if (0 === t.code && (t.data.task || t.data.cars)) {
                                    var n = t.data.task, a = t.data.cars.map(function (t) {
                                        return {carNo: t.carNum, lng: t.lng, lat: t.lat}
                                    }), i = {};
                                    i.lines = [{
                                        id: 1,
                                        start: {
                                            label: n.name ? "起点-" + n.name : "起点",
                                            point: {lng: n.startLng, lat: n.startLat}
                                        },
                                        end: {
                                            label: n.receiver ? "终点-" + n.receiver : "终点",
                                            point: {lng: n.lon, lat: n.lat}
                                        },
                                        cars: a
                                    }], i.centerPoint = {}, e.lastLines = JSON.parse(JSON.stringify(e.lines)), e.lines = i.lines, e.addOverlays()
                                } else e.mp.clearOverlays()
                            })
                        };
                        n(), this.isFirstGetLines = !1, clearInterval(this.cache.timer), this.cache.timer = setInterval(n, this.getLinesTime)
                    }, createMap: function () {
                        this.mp = new BMap.Map("car_map", {enableMapClick: !1}), this.mp.centerAndZoom(new BMap.Point(this.map.mapCenter.point.lng, this.map.mapCenter.point.lat), this.map.mapZoom), this.mp.enableScrollWheelZoom(), this.mp.enablePinchToZoom(), this.mp.enableDoubleClickZoom(), this.mp.addEventListener("click", function (t) {
                            console.log(JSON.stringify(t.point))
                        }), this.cache.createMap = !1
                    }, addOverlays: function () {
                        this.mp.clearOverlays();
                        for (var t = [], e = 0; e < this.lines.length; e++) this.lines[e].start.point.lng && this.lines[e].start.point.lat && t.push(h(this.lines[e].start.point)), this.lines[e].end.point.lng && this.lines[e].end.point.lat && t.push(h(this.lines[e].end.point)), this.makeStartEndPoint(this.lines[e], e), this.makeCars(this.lines[e].cars, this.lines[e], e);
                        1 === this.lines.length && this.lines[0].cars.forEach(function (e) {
                            t.push(h(e))
                        });
                        var n = t.sort(function (t, e) {
                            return e.lng - t.lng
                        })[0].lng, a = t.sort(function (t, e) {
                            return e.lat - t.lat
                        })[0].lat;
                        this.map.autoSetViewport && (Math.abs(n - this.cache.maxPoint.lng) > .005 || Math.abs(a - this.cache.maxPoint.lat) > .005) && (this.cache.maxPoint.lng = n, this.cache.maxPoint.lat = a, this.mp.setViewport(t))
                    }, makeStartEndPoint: function (t, e) {
                        var n = this, a = [t.start, t.end];
                        a.forEach(function (t) {
                            if (t && t.point.lng && t.point.lat) {
                                var e = g(t);
                                n.mp.addOverlay(e)
                            }
                        })
                    }, makeCars: function (t, e, n) {
                        var a = this, i = new Promise(function (t, e) {
                            var n;
                            a.map.carIcon ? (n = new BMap.Icon(a.map.carIcon, new BMap.Size(20, 40)), t(n)) : v({
                                uri: x.a,
                                width: 16,
                                height: 32
                            }).then(function (e) {
                                a.map.carIcon = e, n = new BMap.Icon(e, new BMap.Size(16, 32)), t(n)
                            })
                        });
                        i.then(function (i) {
                            for (var o = 0; o < t.length; o++) if (a.lastLinesSimplify[e.id] && a.lastLinesSimplify[e.id][t[o].carNo]) {
                                var r = a.lastLinesSimplify[e.id][t[o].carNo], s = h(t[o]), l = h(r),
                                    c = new BMap.Marker(l, {icon: i, title: r.carNo}),
                                    u = new BMap.Label(r.carNo, {offset: new BMap.Size(20, 0)});
                                u.setStyle({
                                    fontSize: "12px",
                                    maxWidth: "100px",
                                    textAlign: "center",
                                    // background: "transparent",
                                    border: "1px solid #f00",
                                    padding: "6px",
                                    // opacity: "0.5",
                                    color: a.map.lineColors[n].font
                                    // borderRadius: "0%"
                                }), c.setLabel(u), y(l, s, a.mp, c), a.mp.addOverlay(c), w(l, s, a.mp, c, a.getLinesTime)
                            } else {
                                var p = h(t[o]), f = new BMap.Marker(p, {icon: i, title: t[o].carNo}),
                                    d = new BMap.Label(t[o].carNo, {offset: new BMap.Size(20, 0)});
                                d.setStyle({
                                    fontSize: "12px",
                                    maxWidth: "100px",
                                    textAlign: "center",
                                    // background: "transparent",
                                    border: "1px solid #f00",
                                    padding: "6px",
                                    color: a.map.lineColors[n].font,
                                    // borderRadius: "50%"
                                }), f.setLabel(d), y(p, h(e.end.point), a.mp, f), a.mp.addOverlay(f)
                            }
                        })
                    }
                }, computed: {
                    lastLinesSimplify: function () {
                        if (0 === this.lastLines.length) return this.lastLines;
                        for (var t = {}, e = 0; e < this.lastLines.length; e++) {
                            var n = this.lastLines[e];
                            t[n.id] = P["keyBy"](n.cars, "carNo")
                        }
                        return t
                    }, apis: function () {
                        for (var t = {}, e = this.apiUrls, n = 0, a = Object.keys(e); n < a.length; n++) {
                            var i = a[n];
                            "base" === i ? t.base = e.base : t[i] = e.base + e[i]
                        }
                        return t
                    }
                }
            }, k = L, C = (n("cccb"), Object(l["a"])(k, f, d, !1, null, null, null)), S = C.exports;
        a["default"].use(p["a"]);
        var T = new p["a"]({
            routes: [{path: "/", name: "home", component: S}, {
                path: "/home",
                name: "home",
                component: S
            }, {
                path: "/about", name: "about", component: function () {
                    return n.e("about").then(n.bind(null, "f820"))
                }
            }]
        }), O = n("2f62");
        a["default"].use(O["a"]);
        var I = new O["a"].Store({state: {}, mutations: {}, actions: {}}),
            B = (n("f5df"), n("be4f"), n("450d"), n("896a")), j = n.n(B), $ = (n("46a1"), n("e5f2")), F = n.n($),
            N = (n("bc1c"), n("4726")), _ = n.n(N), E = (n("28b2"), n("c7ad")), z = n.n(E), A = (n("6611"), n("e772")),
            D = n.n(A), J = (n("1f1a"), n("4e4b")), R = n.n(J), Z = (n("1951"), n("eedf")), V = n.n(Z);
        a["default"].use(V.a), a["default"].use(R.a), a["default"].use(D.a), a["default"].use(z.a), a["default"].use(_.a), a["default"].prototype.$notify = F.a, a["default"].prototype.$loading = j.a.service;
        var U = n("bc3a"), W = n.n(U), G = n("4328"), q = n.n(G);

        function H(t) {
            var e = this, n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
                a = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {}, i = a.isLoading,
                o = void 0 === i || i, r = a.loadingMsg, s = void 0 === r ? "请稍后..." : r,
                l = (a.dataType, !o || this.$loading({background: "rgba(0, 0, 0, 0.7)", lock: !0, text: s}));
            return new Promise(function (a, i) {
                W.a.get(t, {params: n}).then(function (t) {
                    if (0 !== t.data.code) return console.log("api返回错误状态码::", t), void e.$notify.error({message: t.data.msg || "apiCode err"});
                    a(t.data)
                }).catch(function (t) {
                    console.log("api通信失败:", t), e.$notify.error({message: "api err"}), i(t)
                }).finally(function () {
                    !o || l.close()
                })
            })
        }

        function K(t) {
            var e = this, n = arguments.length > 1 && void 0 !== arguments[1] ? arguments[1] : {},
                a = arguments.length > 2 && void 0 !== arguments[2] ? arguments[2] : {}, i = a.isLoading,
                o = void 0 === i || i, r = a.loadingMsg, s = void 0 === r ? "请稍后..." : r, l = a.dataType,
                c = void 0 === l ? "form" : l,
                u = !o || this.$loading({background: "rgba(0, 0, 0, 0.7)", lock: !0, text: s});
            return new Promise(function (a, i) {
                W()({method: "post", url: t, data: n, dataType: c}).then(function (t) {
                    if (0 !== t.data.code) return console.log("api返回错误状态码::", t), void e.$notify.error({message: t.data.msg || "apiCode err"});
                    a(t.data)
                }, function (t) {
                    console.log("api通信失败:", t), e.$notify.error({message: "api err"}), i(t)
                }).finally(function () {
                    !o || u.close()
                })
            })
        }

        W.a.defaults.timeout = 6e4, W.a.defaults.baseURL = "", W.a.interceptors.request.use(function (t) {
            return "json" === t.dataType ? (t.data = JSON.stringify(t.data), t.headers = {"Content-Type": "application/json"}) : (t.data = q.a.stringify(t.data), t.headers = {"Content-Type": "application/x-www-form-urlencoded"}), t
        }, function (t) {
            return Promise.reject(err)
        }), W.a.interceptors.response.use(function (t) {
            return t
        }, function (t) {
            return Promise.reject(t)
        }), a["default"].prototype.$get = H, a["default"].prototype.$post = K;
        var Q = !1, X = Q ? "http://127.0.0.1:3000/" : "/", Y = {lines: X + "duties/tasks/device/location"};
        a["default"].prototype.$global_var = {
            isDev: Q,
            apis: Y
        }, a["default"].config.productionTip = !1, new a["default"]({
            router: T, store: I, render: function (t) {
                return t(u)
            }
        }).$mount("#app")
    }, "5ef9": function (t, e, n) {
        t.exports = n.p + "bdmapcar/img/car.png"
    }, "64a9": function (t, e, n) {
    }, cccb: function (t, e, n) {
        "use strict";
        var a = n("d563"), i = n.n(a);
        i.a
    }, d563: function (t, e, n) {
    }
});
//# sourceMappingURL=app.js.map