package com.bm.commont.util;

import lombok.extern.slf4j.Slf4j;

/**
 * 描述:
 * 字符串工具类
 *
 * @author 北明软件
 * @create 2020-07-29 14:15
 */
@Slf4j
@SuppressWarnings("ALL")
public class StringLocalUtils {
    public StringLocalUtils() {
        super();
    }

    /**
     * 判断数字是否为null
     *
     * @param val 整型数字
     * @return true:为null；fasle ： 不为null
     */
    public static boolean isEmpty(Integer val) {
        if (val == null) {
            return true;
        }
        return false;
    }


}
