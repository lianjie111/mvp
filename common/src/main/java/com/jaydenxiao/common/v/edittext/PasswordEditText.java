package com.jaydenxiao.common.v.edittext;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PixelFormat;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.MotionEvent;

import com.jaydenxiao.common.R;

/**
 * Created by 家杰 on 2015/11/20.
 */
public class PasswordEditText extends ClearEdit {

    //资源
    private final int INVISIBLE = R.mipmap.ic_eye_close;
    private final int VISIBLE = R.mipmap.ic_eye_open;
    //按钮宽度dp
    private int mWidth;
    //按钮的bitmap
    private Bitmap mBitmap_invisible;
    private Bitmap mBitmap_visible;
    //间隔
    private int Interval;
    //内容是否可见
    private boolean isVisible = false;

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public PasswordEditText(final Context context) {
        super(context);
        init(context);
    }

    public PasswordEditText(final Context context,final AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public PasswordEditText(final Context context,final AttributeSet attrs,final int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        setSingleLine();
        //设置EditText文本为隐藏的(注意！需要在setSingleLine()之后调用)
        setTransformationMethod(PasswordTransformationMethod.getInstance());

        mWidth = getmWidth_clear();
        Interval = getInterval();
        addRight(mWidth+Interval);
        mBitmap_invisible = createBitmap1(INVISIBLE,context);
        mBitmap_visible = createBitmap1(VISIBLE,context);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int right = getWidth()+getScrollX()- Interval ;
        int left = right-mWidth;
        int top = (getHeight()-mWidth)/2;
        int bottom = top + mWidth;
        Rect rect = new Rect(left,top,right,bottom);

        if(isVisible){
            canvas.drawBitmap(mBitmap_visible, null, rect, null);
        }else{
            canvas.drawBitmap(mBitmap_invisible, null, rect, null);
        }
    }

    /**
     * 改写父类的方法
     */
    @Override
    protected void drawClear(int translationX, Canvas canvas) {
        float scale = 1f - (float)(translationX)/(float)(getmWidth_clear()+Interval);
        int right = (int) (getWidth()+getScrollX()- Interval-mWidth-Interval -getmWidth_clear()*(1f-scale)/2f);
        int left = (int) (getWidth()+getScrollX()- Interval-mWidth-Interval -getmWidth_clear()*(scale+(1f-scale)/2f));
        int top = (int) ((getHeight()-getmWidth_clear()*scale)/2);
        int bottom = (int) (top + getmWidth_clear()*scale);
        Rect rect = new Rect(left,top,right,bottom);
        canvas.drawBitmap(getmBitmap_clear(), null, rect, null);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            boolean touchable = ( getWidth() - mWidth - Interval < event.getX() ) && (event.getX() < getWidth() - Interval);
            if (touchable) {
                isVisible = !isVisible;
                if (isVisible){
                    //设置EditText文本为可见的
                    setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //设置EditText文本为隐藏的
                    setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        }
        return super.onTouchEvent(event);
    }


    /**
     * 给图标染上当前提示文本的颜色并且转出Bitmap
     * @param resources
     * @param context
     * @return
     */
    public Bitmap createBitmap1(int resources,Context context) {
        final Drawable drawable = ContextCompat.getDrawable(context, resources);
        final Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
//        DrawableCompat.setTint(wrappedDrawable, R.color.eye_color);
        return drawableToBitamp(wrappedDrawable);
    }

    /**
     * drawable转换成bitmap
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable)
    {
        int w = drawable.getIntrinsicWidth();
        int h = drawable.getIntrinsicHeight();
        Bitmap.Config config = drawable.getOpacity() != PixelFormat.OPAQUE ? Bitmap.Config.ARGB_8888 : Bitmap.Config.RGB_565;
        Bitmap bitmap = Bitmap.createBitmap(w, h, config);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, w, h);
        drawable.draw(canvas);
        return bitmap;
    }

    public int dp2px(float dipValue) {
        final float scale = getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }


}
