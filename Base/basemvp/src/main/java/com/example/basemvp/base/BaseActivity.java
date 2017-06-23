package com.example.basemvp.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by jie on 2017/6/23.
 */

public abstract class BaseActivity<P extends BasePresenter, M extends BaseModel> extends AppCompatActivity {
    public P mPresenter;
    public M mModel;
    private Context mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
        mPresenter = TUtil.getT(this, 0);
        mModel = TUtil.getT(this, 1);
        mPresenter.mContext = mContext;

        setVM();

    }

    protected abstract void setVM();
}
