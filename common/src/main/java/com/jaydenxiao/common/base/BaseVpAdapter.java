package com.jaydenxiao.common.base;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by Administrator on 2016/12/20.
 */

public class BaseVpAdapter<T extends View> extends PagerAdapter {
    protected List<T> datas;

    public BaseVpAdapter(List<T> datas) {
        this.datas = datas;
    }

    public void addData(T t) {
        if (t != null) {
            datas.add(t);
            this.notifyDataSetChanged();
        }
    }


    public void addData(T t, int index) {
        if (t != null) {
            datas.add(index,t);
            this.notifyDataSetChanged();
        }
    }
    public void addAllData(List<T> ts) {
        if (ts != null) {
            datas.addAll(ts);
            this.notifyDataSetChanged();
        }
    }

    public void removeData(T t) {
        if (datas.contains(t)) {
            datas.remove(t);
            this.notifyDataSetChanged();
        }
    }

    public void remoceDataAll() {
        datas.clear();
        this.notifyDataSetChanged();

    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ViewGroup parent = (ViewGroup) datas.get(position).getParent();
        if (parent != null) {
            parent.removeAllViews();
        }
        container.addView(datas.get(position));
        return datas.get(position);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView(datas.get(position));
    }
}
