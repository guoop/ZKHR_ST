/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.ruoyi.common.utils.idgen;

import java.security.SecureRandom;
import java.util.UUID;

/**
 * 封装各种生成唯一性ID算法的工具类.
 *
 * @author ThinkGem
 * @version 2014-8-19
 */
public class IdGen {

    private static SecureRandom random = new SecureRandom();
    private static IdWorker idWorker = new IdWorker(-1, -1);

    /**
     * 生成UUID, 中间无-分割.
     */
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    /**
     * 使用SecureRandom随机生成Long.
     */
    public static long randomLong() {
        return Math.abs(random.nextLong());
    }

    /**
     * 获取新唯一编号（18为数值）
     * 来自于twitter项目snowflake的id产生方案，全局唯一，时间有序。
     * 64位ID (42(毫秒)+5(机器ID)+5(业务编码)+12(重复累加))
     */
    public static String nextId() {
        return String.valueOf(idWorker.nextId());
    }


}
