package com.jaydenxiao.common.commonutils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by dell on 2017/5/29.
 */

public class SPSetUtil2 {
    private static SharedPreferences sp;
    private static SPSetUtil2 spSetUtil;

    private SPSetUtil2(Context context) {
        if (sp == null) {
            sp = (SharedPreferences)context.getSharedPreferences("notifyid", Context.MODE_PRIVATE);
        }
    }

    public  static SPSetUtil2  init(Context context) {
        if (spSetUtil == null) {
            spSetUtil = new SPSetUtil2(context);
        }
        return spSetUtil;
    }
    public int get() {
        return sp.getInt("notifyLastID", 0);
    }
    public void put(int strings) {
        SharedPreferences.Editor edit = sp.edit();

        edit.putInt("notifyLastID", strings);
        edit.commit();

    }
}
