package com.jaydenxiao.common.v.recyclerview;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by Administrator on 2017/3/3.
 */

public class ItemRecyclerViewDivider extends RecyclerView.ItemDecoration {
    private int space;

    public ItemRecyclerViewDivider(int space) {
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
//        outRect.left = space;
//        outRect.right = space;
//        outRect.bottom = space;

        // Add top margin only for the first item to avoid double space between items
        if(parent.getChildPosition(view) == 0)
            outRect.top = space;
    }
}
