package com.example.dell.base;

/**
 * Created by jie on 2017/6/23.
 */

public class MainPresenter extends MainContract.Presenter {


    @Override
    public void getInfoRequest(int aa) {
        mView.returnInfo(mModel.getInfo(aa));
    }
}
