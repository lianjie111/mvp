package com.jaydenxiao.common.v.progressbar;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ProgressBar;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.DisplayUtil;

/**
 * Created by Administrator on 2017/2/28.
 */

public class ItemProgressBar extends ProgressBar {
    /**
     * 默认的属性
     */
    private static final int DEFAULT_VALUES = 3;//默认的值
    private static final int DEFAULT_CIRLE_IN_HEIGHT = 7;//内园直径
    private static final int DEFAULT_CIRLE_IN_COLOR = 0xffFF7700;
    private static final int DEFAULT_CIRLE_OUT_HEIGHT = 12;
    private static final int DEFAULT_CIRLE_OUT_COLOR = 0xffFFD6B2;//外圆颜色
    //    private static final int DEFAULT_INDICATOR_COLOR = 0xff868383;
    private static final int DEFAULT_BACK_LINE_HEIGHT = DEFAULT_VALUES;//进度条默认高度（无进度）
    private static final int DEFAULT_BACK_LINE_COLOR = 0xffe5e5e5;//进度条默认颜色(灰)
    private static final int DEFAULT_FORE_LINE_HEIGHT = DEFAULT_VALUES;//进度条默认高度（有进度）
    private static final int DEFAULT_FORE_LINE_COLOR = 0xffFF7700;//进度条默认颜色(cheng)
    /**
     * 变量
     */
    private int in_cirle_height = dp2px(DEFAULT_CIRLE_IN_HEIGHT);
    //    private int in_radiu = in_cirle_height / 2;
    private int in_cirle_color = DEFAULT_CIRLE_IN_COLOR;
//    private int in_cirle_color ;

    private int out_cirle_height = dp2px(DEFAULT_CIRLE_OUT_HEIGHT);
    private int out_cirle_color = DEFAULT_CIRLE_OUT_COLOR;
    //    private int out_cirle_color ;
    private int back_height = dp2px(DEFAULT_BACK_LINE_HEIGHT);
    private int back_color = DEFAULT_BACK_LINE_COLOR;
    private int fore_height = dp2px(DEFAULT_FORE_LINE_HEIGHT);
    private int fore_color = DEFAULT_FORE_LINE_COLOR;
    private int progress_width;//绘制线的宽度
//    private int triangle_width = in_cirle_height;//底边宽的一半
    /**
     * 绘制
     */
    private Paint cirle_in_paint;//绘制指示器的画笔
    private Paint cirle_out_paint;
    private Paint back_paint;//绘制进度条的底部
    private Paint fore_paint;//绘制进度条的进度
    private int line_endX;//进度条的终点坐标
    private Context context;

    public void setFore_color(int fore_color) {
        this.fore_color = fore_color;
    }

    /**
     * 构造方法
     *
     * @param context
     */
    public ItemProgressBar(Context context) {
        this(context, null);
        this.context = context;
    }

    public ItemProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        //获取自定义属性
        obtainAttributes(attrs);
        //初始化画笔
        initPaint();
    }

    /**
     * 绘制画笔
     */
    private void initPaint() {
        //绘制内圆的画笔
        cirle_in_paint = new Paint();
        cirle_in_paint.setAntiAlias(true);
        cirle_in_paint.setStyle(Paint.Style.FILL);//填充
        cirle_in_paint.setColor(in_cirle_color);//设置颜色
        //绘制外圆画笔
        cirle_out_paint = new Paint();
        cirle_out_paint.setAntiAlias(true);
        cirle_out_paint.setAlpha(10);
        cirle_out_paint.setStyle(Paint.Style.FILL);
        cirle_out_paint.setColor(out_cirle_color);

        //绘制进度条的底部
        back_paint = new Paint();
        back_paint.setStyle(Paint.Style.FILL);//填充
        back_paint.setColor(back_color);//设置颜色
        back_paint.setStrokeWidth(back_height);
        //绘制进度条的进度
        fore_paint = new Paint();
        fore_paint.setStyle(Paint.Style.FILL);//填充
        fore_paint.setColor(fore_color);//设置颜色
        fore_paint.setStrokeWidth(fore_height);
    }

    /**
     * 获取自定义属性
     */
    private void obtainAttributes(AttributeSet attrs) {
        TypedArray typedArray = getContext().obtainStyledAttributes(
                attrs, R.styleable.ItemProgressBar);
        //内圆
        in_cirle_height = (int) typedArray.getDimension(
                R.styleable.ItemProgressBar_fore_line_height, in_cirle_height);
//        in_cirle_color = typedArray.getColor(
//                R.styleable.MyProgressBar_fore_line_color,DEFAULT_CIRLE_IN_COLOR);
        //外圆
        in_cirle_height = (int) typedArray.getDimension(
                R.styleable.ItemProgressBar_fore_line_height, in_cirle_height);
//        in_cirle_color = typedArray.getColor(
//                R.styleable.MyProgressBar_fore_line_color,DEFAULT_CIRLE_OUT_COLOR);
        //底部进度条
        back_height = (int) typedArray.getDimension(
                R.styleable.ItemProgressBar_back_line_height, back_height);
        back_color = typedArray.getColor(
                R.styleable.ItemProgressBar_back_line_color, DEFAULT_BACK_LINE_COLOR);
        //顶部进度
        fore_height = (int) typedArray.getDimension(
                R.styleable.ItemProgressBar_fore_line_height, fore_height);
//        fore_color = typedArray.getColor(
//                R.styleable.ItemProgressBar_fore_line_color, DEFAULT_FORE_LINE_COLOR);
        //回收资源
        typedArray.recycle();
    }

    /**
     * 测量
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */

    @Override
    protected synchronized void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = measureHeight(heightMeasureSpec);
        setMeasuredDimension(width, height);
        progress_width = getMeasuredWidth()
                - getPaddingRight() - getPaddingLeft();//进度条的宽度
        line_endX = progress_width ;//进度条线的终点
    }

    private int measureHeight(int measureSpec) {
        int result;
        int specMode = MeasureSpec.getMode(measureSpec);//获取测量高度模式
        int specSize = MeasureSpec.getSize(measureSpec);//获取测量高度的值
        if (specMode == MeasureSpec.EXACTLY) {//精确的测量模式
            result = specSize;
        } else {
            result = (getPaddingTop() + getPaddingBottom()
                    + Math.max(back_height, fore_height)) + in_cirle_height;
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    /**
     * 绘制
     *
     * @param canvas
     */
    @Override
    protected synchronized void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.save();
        canvas.translate(getPaddingLeft(), getHeight() / 2);
        RectF rectF = new RectF(0, 0, line_endX, back_height);
        canvas.drawRoundRect(rectF, back_height / 2,
                back_height / 2, back_paint);
        float progress_with = getProgress() * 1.0f / getMax();
        float progressPosX = (int) ((line_endX
                ) * progress_with);
//        花园
        if (DisplayUtil.px2dip(progressPosX ) > 6 && (DisplayUtil.px2dip(line_endX-(progressPosX )) > 6)){

            setTriangle(canvas, progressPosX);
        }
        if (line_endX - (progressPosX ) == 0) {
            fore_paint.setColor(0xff858585);
        } else {
            fore_paint.setColor(0xffFF7700);
        }
        RectF rectF1 = new RectF(0, 0, progressPosX , back_height);
        canvas.drawRoundRect(rectF1,
                back_height / 2, back_height / 2, fore_paint);
        canvas.restore();
    }

    /**
     * 绘制园
     *
     * @param canvas
     */
    private void setTriangle(Canvas canvas, float progressPosX) {
        canvas.drawCircle(progressPosX , back_height / 2, out_cirle_height / 2,
                cirle_out_paint);
        canvas.drawCircle(progressPosX , back_height / 2, in_cirle_height / 2,
                cirle_in_paint);
    }

    /**
     * dp转px
     *
     * @param dpValues
     * @return
     */
    private int dp2px(int dpValues) {
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, dpValues,
                getResources().getDisplayMetrics());
    }

}
