package com.jaydenxiao.common.v.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jaydenxiao.common.commonutils.DisplayUtil;

/**
 * Created by Administrator on 2017/3/6.
 */
public class HomeRecyclerViewDivider extends RecyclerView.ItemDecoration {


    private final int MARGERTOP = 10;

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = DisplayUtil.dip2px(MARGERTOP);
    }
}