package com.bm.word.utils;

/**
 * 菜单工具
 *
 * @author : qinguoqing
 * @date : 2019/8/31
 */
public class MenuUtils {

    private static Integer count = 0;
    private static String menuStr = "null";

    public static boolean isMenu(String tags) {
        if (menuStr.equals(tags)) {
            count++;
        } else {
            menuStr = tags;
            count = 0;
        }
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }
}
