package com.jaydenxiao.common.v.edittext;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.EditText;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.ToastUitl;

/**
 * Created by Administrator on 2017/3/16.
 */

public class EditTextWithDelPwd extends EditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Drawable imgAble;
    private Context mContext;
    private MyTextWatch myTextWatch;


    public EditTextWithDelPwd(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDelPwd(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDelPwd(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    private void init() {
        imgInable = mContext.getResources().getDrawable(R.mipmap.list_delete_icon);
        imgAble = mContext.getResources().getDrawable(R.mipmap.list_delete_icon);
        myTextWatch = new MyTextWatch();
        //事件监听
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    deleteDrawable();
                    removeTextChangedListener(myTextWatch);
//                    if (getText() != null) {
//                        DecimalFormat df = new DecimalFormat("######0.00");
////
//                        String format = df.format(Double.valueOf(getText().toString().trim()));
//                        LogUtils.loge("33333");
//                        setText(format);
//                    }
                } else {
                    if (length() > 0) {
                        setDrawable();
                    }
                    addTextChangedListener(myTextWatch);
                }
            }
        });
    }


    //设置删除图片
    private void setDrawable() {
        if (length() < 1) {
        } else {
            setCompoundDrawablesWithIntrinsicBounds(null, null, imgAble, null);
        }
    }

    private void deleteDrawable() {
        setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);

    }

    // 处理删除事件
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (imgAble != null && event.getAction() == MotionEvent.ACTION_UP) {
            int eventX = (int) event.getRawX();
            int eventY = (int) event.getRawY();
            Log.e(TAG, "eventX = " + eventX + "; eventY = " + eventY);
            Rect rect = new Rect();
            getGlobalVisibleRect(rect);
            rect.left = rect.right - 70;
            if (rect.contains(eventX, eventY)) {
                if (isFocused()) {

                    setText("");
                }
            }
        }
        return super.onTouchEvent(event);
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }


    /**
     * 设置晃动动画
     */
    public void setShakeAnimation() {
        this.startAnimation(shakeAnimation(5));
    }


    /**
     * 晃动动画
     *
     * @param counts 1秒钟晃动多少下
     * @return
     */
    public static Animation shakeAnimation(int counts) {
        Animation translateAnimation = new TranslateAnimation(0, 10, 0, 0);
        translateAnimation.setInterpolator(new CycleInterpolator(counts));
        translateAnimation.setDuration(1000);
        return translateAnimation;
    }


    class MyTextWatch implements TextWatcher {
        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            if (length() < 1) {
                deleteDrawable();
            }
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            if (getText() != null) {
                setDrawable();
                String trim = getText().toString().trim();
                if (trim.length() > 20) {
                    ToastUitl.showShort("你输入的字数已经超过了限制");
                    setText(trim.substring(0, length() - 1));
                    return;
                }
            }
        }
    }

}
