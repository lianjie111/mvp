package com.jaydenxiao.common.commonutils;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by dell on 2017/5/17.
 */

public class SPSetUtil {

    private static SharedPreferences sp;
    private static SPSetUtil spSetUtil;

    private SPSetUtil(Context context) {
        if (sp == null) {
            sp = (SharedPreferences)context.getSharedPreferences("notify", Context.MODE_PRIVATE);
        }
    }

    public  static SPSetUtil  init(Context context) {
        if (spSetUtil == null) {
            spSetUtil = new SPSetUtil(context);
        }
        return spSetUtil;
    }

    public Set<String> gets() {

        return sp.getStringSet("notifySet", new HashSet<String>());
    }

    public void puts(Set<String> strings) {
        SharedPreferences.Editor edit = sp.edit();
        if (strings != null) {
            edit.putStringSet("notifySet", strings);
            edit.commit();

        }

    }



}
