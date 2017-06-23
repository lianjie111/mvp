package com.jaydenxiao.common.v.recyclerview;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jaydenxiao.common.commonutils.DisplayUtil;

/**
 * Created by Administrator on 2017/1/16.
 */

public class RecycleViewDivider extends RecyclerView.ItemDecoration {


    private final int MARGERTOP = 5;
    @Override
    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        Paint paint = new Paint();
        paint.setColor(0xfff6f5fa);


        Rect rect = new Rect();
        int right = parent.getWidth() ;
        final int childCount = parent.getChildCount() - 1;
        for (int i = 0; i < childCount; i++) {
            final View child = parent.getChildAt(i);

            //获得child的布局信息
            final RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) child.getLayoutParams();
            final int top = child.getBottom();
            final int itemDividerHeight = DisplayUtil.dip2px(MARGERTOP);//px
            rect.set(0, top, right, top + itemDividerHeight);
            c.drawRect(rect,paint);
        }
    }

}
