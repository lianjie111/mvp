package com.example.basemvp.base;

import android.content.Context;

/**
 * Created by jie on 2017/6/23.
 */

public abstract class BasePresenter<V, M> {
    public Context mContext;
    public V mView;
    public M mModel;

    public void setVM(V v,M m) {
        this.mModel = m;
        this.mView = v;

    }

}
