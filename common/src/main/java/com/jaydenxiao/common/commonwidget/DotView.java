package com.jaydenxiao.common.commonwidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaydenxiao.common.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author:lengxue
 * date:2017/4/21 9:17
 * description:
 */

public class DotView extends LinearLayout {

    private int selectedRes = R.mipmap.blue;
    private int unselectedRes = R.mipmap.grey;


    public DotView(Context context) {
        super(context);
        init();
    }

    public DotView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public DotView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        setOrientation(LinearLayout.HORIZONTAL);
        setGravity(Gravity.CENTER);
    }

    public void initDotView(int count){
        initDotView(count,R.mipmap.grey,R.mipmap.blue);
    }

    private List<ImageView> dotList = new ArrayList<>();
    private void initDotView(int count, int focusRes, int defaultRes) {
        this.selectedRes = focusRes;
        this.unselectedRes = defaultRes;

        dotList.clear();

        for (int i = 0; i < count ; i++) {
            ImageView dotIV = new ImageView(getContext());
            dotIV.setImageResource(defaultRes);
            dotIV.setScaleType(ImageView.ScaleType.FIT_XY);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(FrameLayout.LayoutParams.WRAP_CONTENT,FrameLayout.LayoutParams.WRAP_CONTENT);
            params.leftMargin = 4;
            params.rightMargin = 4;
            this.addView(dotIV,params);

            dotList.add(dotIV);

        }
    }

    public  void setSelectDot(int index){
        for (int i = 0; i < dotList.size(); i++) {
            ImageView iv = dotList.get(i);
            if(i == index){
                iv.setImageResource(selectedRes);
            }else{
                iv.setImageResource(unselectedRes);
            }
        }

    }
}
