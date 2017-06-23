package com.jaydenxiao.common.v.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import static com.jaydenxiao.common.commonutils.DisplayUtil.dip2px;

/**
 * Created by Administrator on 2017/4/10.
 */

public class MySnapHelper extends LinearSnapHelper {
    private int height;
    public MySnapHelper() {

    }

    public MySnapHelper(int height) {
        this.height = height;
    }

    @Override
    public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
        int targetPos = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
        final View currentView = findSnapView(layoutManager);
        if(targetPos != RecyclerView.NO_POSITION && currentView != null){
            int currentPostion = layoutManager.getPosition(currentView);
            int first = ((LinearLayoutManager)layoutManager).findFirstVisibleItemPosition();
            int last = ((LinearLayoutManager)layoutManager).findLastVisibleItemPosition();
            currentPostion = targetPos < currentPostion ? last : (targetPos > currentPostion ? first : currentPostion);
            targetPos = targetPos < currentPostion ? currentPostion - 1 : (targetPos > currentPostion ? currentPostion + 1 : currentPostion);
        }
        return targetPos;
    }

    @Override
    public int[] calculateDistanceToFinalSnap(@NonNull RecyclerView.LayoutManager layoutManager, @NonNull View targetView) {
        int[] out = new int[2];
        out[0] = 0;
        out[1] = dip2px(height);
        return out;
    }
}
