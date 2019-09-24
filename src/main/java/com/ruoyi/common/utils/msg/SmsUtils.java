package com.ruoyi.common.utils.msg;


import com.ruoyi.common.exception.base.BaseException;
import com.ruoyi.common.utils.DateUtil;
import com.ruoyi.common.utils.LocationUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.config.Global;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 发送短信（请实现send方法）
 */
public class SmsUtils {

    private final static Logger logger = LoggerFactory.getLogger(SmsUtils.class);

    /**
     * 模拟发送短信
     *
     * @param content 短信内容
     * @param mobile  接受手机号码
     */
    public static String send(String content, String mobile) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String url = Global.getConfig("sms.url");
        String username = Global.getConfig("sms.username");
        String pswd = Global.getConfig("sms.password");
        String prefix = Global.getConfig("sms.prefix");
        String suffix = Global.getConfig("sms.suffix");

        if (StringUtils.isEmpty(prefix)) {
            prefix="";
        }
        if (StringUtils.isEmpty(suffix)) {
            suffix="";
        }
        if(StringUtils.isEmpty(pswd)||StringUtils.isEmpty(username)||StringUtils.isEmpty(url)||StringUtils.isEmpty(url)){
            throw new BaseException("发送验证码错误");
        }
        String md5Psd = string2MD5(pswd);
        String time = DateUtil.toString(DateUtil.getDateline(), "yyyyMMddHHmmss");

        pswd = string2MD5(md5Psd + time);
        StringBuffer sb = new StringBuffer("");
        sb.append("username=");
        sb.append(username);
        sb.append("&password=");
        sb.append(pswd);
        sb.append("&tkey=");
        sb.append(time);
        sb.append("&mobile=");
        sb.append(mobile);
        sb.append("&content=");

//		// 短信内容
        sb.append(URLEncoder.encode(prefix + ": " + content + suffix, "UTF-8"));

        String result = HttpUtils.sendPost(url, sb.toString());
        logger.debug(result);
        logger.debug("短信内容:[{}]",content);
        sb=null;
        logger.debug("发送短信成功！请实现 " + SmsUtils.class + " 的 send 方法。");
        return "{result:1,message:\"发送短信成功！\"}";
    }


    /***
     * MD5加码 生成32位md5码
     */
    public static String string2MD5(String inStr) throws NoSuchAlgorithmException {
        MessageDigest md5 = null;
        md5 = MessageDigest.getInstance("MD5");
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];

        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();

    }


    public static int getRandomLong(){
        return (int) ((Math.random()*9+1)*100);
    }

    public static String sendMobileCode(String mobile) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        return send(getRandomLong()+"",mobile);
    }


    public static void main(String[] args){
        System.out.println(Double.valueOf(Global.getConfig("project.startDistance")).compareTo(LocationUtils.getDistance(Double.valueOf(Global.getConfig("project.lat")),Double.valueOf(Global.getConfig("project.lon")),35.063769,114.391349))>=0);
        System.out.println(LocationUtils.getDistance(Double.valueOf(Global.getConfig("project.lat")),Double.valueOf(Global.getConfig("project.lon")),35.063769,114.391349));
    }
}
