package com.jaydenxiao.common.v.dialog;


/**
 * Created by liang.wu on 2015/5/8.
 */
public class Tools {

    public static long lastClickTime;
    public static final String KEY_PROVINCE = "PROVINCE";
    public static final String KEY_CITY = "CITY";
    public static final String KEY_BANKNAME="BANKNAME";
    public synchronized static boolean isFastDoubleClick() {
        long time = System.currentTimeMillis();
        if (time - lastClickTime < 1000) {
            return true;
        }
        lastClickTime = time;
        return false;
    }

}
