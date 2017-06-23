package com.example.dell.base;

import com.example.basemvp.base.BaseModel;
import com.example.basemvp.base.BasePresenter;
import com.example.basemvp.base.BaseView;

/**
 * Created by jie on 2017/6/23.
 */

public interface MainContract {
    interface Model extends BaseModel {
        String getInfo(int aa);
    }

    interface View extends BaseView {
        void returnInfo(String s);
    }

    public abstract class Presenter extends BasePresenter<View, Model> {
        public abstract void getInfoRequest(int aa) ;
    }
}
