package com.example.dell.base;

import android.os.Bundle;

import com.example.basemvp.base.BaseActivity;

public class MainActivity extends BaseActivity<MainPresenter,MainModel> implements MainContract.View{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mPresenter.getInfoRequest(111);//请求
    }

    @Override
    protected void setVM() {
        mPresenter.setVM(this,mModel);
    }

    /**
     * *  获取数据后的回调
     */

    @Override
    public void returnInfo(String s) {

    }
}
