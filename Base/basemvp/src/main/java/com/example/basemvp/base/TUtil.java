package com.example.basemvp.base;

import java.lang.reflect.ParameterizedType;

/**
 * Created by jie on 2017/6/23.
 */

public class TUtil {

    /**
     * 通过反射获取class泛型中的实例对象()
     * @param o
     * @param i 第几个泛型
     * @param <T>
     * @return
     */
    public static <T> T getT(Object o, int i) {
        try {
            return  ((Class<T>)(((ParameterizedType) o.getClass().getGenericSuperclass()).getActualTypeArguments()[i])).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

}
