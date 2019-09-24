package com.ruoyi.project.locator.common;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 各地图API坐标转换;
 * WGS84坐标系：国际上通用的坐标系;
 * GCJ02坐标系：即火星坐标系。
 * BD-09： 百度坐标系
 * @Date 2019-08-12 17:46
 */
public class GeoUtil {

    public static double pi = 3.1415926535897932384626;
    public static double x_pi = 3.14159265358979324 * 3000.0 / 180.0;
    public static double a = 6378245.0;
    public static double ee = 0.00669342162296594323;

    public static double EARTH_RADIUS = 6372.796924;


    public static double transformLat(double x, double y) {
        double ret = -100.0 + 2.0 * x + 3.0 * y + 0.2 * y * y + 0.1 * x * y
                + 0.2 * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(y * pi) + 40.0 * Math.sin(y / 3.0 * pi)) * 2.0 / 3.0;
        ret += (160.0 * Math.sin(y / 12.0 * pi) + 320 * Math.sin(y * pi / 30.0)) * 2.0 / 3.0;
        return ret;
    }

    public static double transformLon(double x, double y) {
        double ret = 300.0 + x + 2.0 * y + 0.1 * x * x + 0.1 * x * y + 0.1
                * Math.sqrt(Math.abs(x));
        ret += (20.0 * Math.sin(6.0 * x * pi) + 20.0 * Math.sin(2.0 * x * pi)) * 2.0 / 3.0;
        ret += (20.0 * Math.sin(x * pi) + 40.0 * Math.sin(x / 3.0 * pi)) * 2.0 / 3.0;
        ret += (150.0 * Math.sin(x / 12.0 * pi) + 300.0 * Math.sin(x / 30.0
                * pi)) * 2.0 / 3.0;
        return ret;
    }

    public static double[] transform(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] { lat, lon };
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLat, mgLon };
    }

    public static boolean outOfChina(double lat, double lon) {
        if (lon < 72.004 || lon > 137.8347)
            return true;
        if (lat < 0.8293 || lat > 55.8271)
            return true;
        return false;
    }

    /**
     * 84 to 火星坐标系 (GCJ-02) World Geodetic System ==> Mars Geodetic System
     *
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gps84_To_Gcj02(double lat, double lon) {
        if (outOfChina(lat, lon)) {
            return new double[] { lat, lon };
        }
        double dLat = transformLat(lon - 105.0, lat - 35.0);
        double dLon = transformLon(lon - 105.0, lat - 35.0);
        double radLat = lat / 180.0 * pi;
        double magic = Math.sin(radLat);
        magic = 1 - ee * magic * magic;
        double sqrtMagic = Math.sqrt(magic);
        dLat = (dLat * 180.0) / ((a * (1 - ee)) / (magic * sqrtMagic) * pi);
        dLon = (dLon * 180.0) / (a / sqrtMagic * Math.cos(radLat) * pi);
        double mgLat = lat + dLat;
        double mgLon = lon + dLon;
        return new double[] { mgLat, mgLon };
    }

    /**
     * * 火星坐标系 (GCJ-02) to 84 * * @param lon * @param lat * @return
     * */
    public static double[] gcj02_To_Gps84(double lat, double lon) {
        double[] gps = transform(lat, lon);
        double lontitude = lon * 2 - gps[1];
        double latitude = lat * 2 - gps[0];
        return new double[] { latitude, lontitude };
    }

    /**
     * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 将 GCJ-02 坐标转换成 BD-09 坐标
     *
     * @param lat
     * @param lon
     */
    public static double[] gcj02_To_Bd09(double lat, double lon) {
        double x = lon, y = lat;
        double z = Math.sqrt(x * x + y * y) + 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) + 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta) + 0.0065;
        double tempLat = z * Math.sin(theta) + 0.006;
        double[] gps = { tempLat, tempLon };
        return gps;
    }

    /**
     * * 火星坐标系 (GCJ-02) 与百度坐标系 (BD-09) 的转换算法 * * 将 BD-09 坐标转换成GCJ-02 坐标 * * @param
     * bd_lat * @param bd_lon * @return
     */
    public static double[] bd09_To_Gcj02(double lat, double lon) {
        double x = lon - 0.0065, y = lat - 0.006;
        double z = Math.sqrt(x * x + y * y) - 0.00002 * Math.sin(y * x_pi);
        double theta = Math.atan2(y, x) - 0.000003 * Math.cos(x * x_pi);
        double tempLon = z * Math.cos(theta);
        double tempLat = z * Math.sin(theta);
        double[] gps = { tempLat, tempLon };
        return gps;
    }

    /**
     * 将gps84转为bd09
     * @param lat
     * @param lon
     * @return
     */
    public static double[] gps84_To_bd09(double lat, double lon) {
        double[] gcj02 = gps84_To_Gcj02(lat, lon);
        double[] bd09 = gcj02_To_Bd09(gcj02[0], gcj02[1]);
        return bd09;
    }


    public static double[] bd09_To_gps84(double lat, double lon) {
        double[] gcj02 = bd09_To_Gcj02(lat, lon);
        double[] gps84 = gcj02_To_Gps84(gcj02[0], gcj02[1]);
        // 保留小数点后六位
        gps84[0] = retain6(gps84[0]);
        gps84[1] = retain6(gps84[1]);
        return gps84;
    }

    /**
     * 保留小数点后六位
     *
     * @param num
     * @return
     */
    private static double retain6(double num) {
        String result = String.format("%.6f", num);
        return Double.valueOf(result);
    }

    /** *
             * @param center
	 *            中心坐标
	 * @param distance
	 *            离中心坐标距离（单位：米）
            * @return 随机坐标
	 */
    public static GPSLocation GetRandomLocation(GPSLocation center, double distance) {
        if (distance <= 0) distance = 50;
        double lat, lon, brg;
        double dist = 0;
        double rad360 = 2 * pi;
        distance = distance / 1000;
        GPSLocation location = new GPSLocation();
        double maxdist = distance;
        maxdist = maxdist / EARTH_RADIUS;
        double startlat = rad(center.getLatitude());
        double startlon = rad(center.getLongitude());
        double cosdif = Math.cos(maxdist) - 1;
        double sinstartlat = Math.sin(startlat);
        double cosstartlat = Math.cos(startlat);
        dist = Math.acos((new Random().nextDouble() * cosdif + 1));
        brg = rad360 * new Random().nextDouble();
        lat = Math.asin(sinstartlat * Math.cos(dist) + cosstartlat * Math.sin(dist) * Math.cos(brg));
        lon = deg(normalizeLongitude(startlon * 1 + Math.atan2(Math.sin(brg) * Math.sin(dist) * cosstartlat, Math.cos(dist) - sinstartlat * Math.sin(lat))));
        lat = deg(lat);
        location.setLatitude(padZeroRight(lat));
        location.setLongitude(padZeroRight(lon));
        return location;
    }
    /**
     * 获取两点间的距离(单位：米)
     *
     * @param start
     *            起始坐标
     * @param end
     *            结束坐标
     * @return 距离
     */
    public static double GetDistance(GPSLocation start, GPSLocation end) {
        double radLat1 = rad(start.getLatitude());
        double radLat2 = rad(end.getLatitude());
        double a = radLat1 - radLat2;
        double b = rad(start.getLongitude()) - rad(end.getLongitude());
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = (int) (s * 10000000) / 10000;
        return s;
    }
    /**
     * 弧度
     * @param d
     * @return
     */
    static double rad(double d) {
        return d * pi / 180.0;
    }

    /**
     * 角度
     * @param rd
     * @return
     */
    static double deg(double rd) {
        return (rd * 180 / Math.PI);
    }

    static double normalizeLongitude(double lon) {
        double n = pi;
        if (lon > n) {
            lon = lon - 2 * n;
        } else if (lon < -n) {
            lon = lon + 2 * n;
        }
        return lon;
    }

    static double padZeroRight(double s) {
        double sigDigits = 8;
        s = Math.round(s * Math.pow(10, sigDigits)) / Math.pow(10, sigDigits);
        return s;
    }



    public static void main(String[] args) {
        String str = "113.756195,34.718901;";
        String[] arr = str.split(";");
        String res = "";
        for(String s : arr) {
            String[] lntlat = s.split(",");
            Double lnt = Double.parseDouble(lntlat[0]);
            Double lat = Double.parseDouble(lntlat[1]);

            double[] bd09 = gps84_To_bd09(lnt, lat);
            res += bd09[0] + "," + bd09[1] + ";";
        }
        res = res.substring(0, res.lastIndexOf(";"));
        System.out.println(res);

    }




}
