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
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaydenxiao.common.R;
import com.jaydenxiao.common.commonutils.AppUtil;
import com.jaydenxiao.common.commonutils.LogUtils;
import com.jaydenxiao.common.commonutils.ToastUitl;

import java.text.DecimalFormat;

import static android.R.attr.format;

/**
 * Created by Administrator on 2017/3/16.
 */

public class EditTextWithDelBug extends EditText {
    private final static String TAG = "EditTextWithDel";
    private Drawable imgInable;
    private Drawable imgAble;
    private Context mContext;
    private TextView poundage;
    private MyTextWatch myTextWatch;
    private TextView textView;
    private double redpag;
    private double rate;
    private double interest;
    private int date;
    private TextView allMoney;
    private boolean isNine;
    private Button button;
    private double maxMoney;
    private double redMoney;
    private int repaymentType=0;
    private int DurationType=0;//DurationType 1  天 2 月

    public void setDurationType(int durationType) {
        DurationType = durationType;
    }
    public void setRepaymentType(int repaymentType) {
        this.repaymentType = repaymentType;
    }

    public EditTextWithDelBug setRedMoney(double redMoney) {
        this.redMoney = redMoney;
        return this;
    }

    public void setDate(int date) {
        this.date = date;
    }

    public void setButton(Button button) {
        this.button = button;
    }

    public void setTextView(TextView textView) {
        this.textView = textView;
    }

    public void setNine(boolean nine) {
        isNine = nine;
    }

    public void setInterest(double interest) {
        this.interest = interest;
    }

    public void setAllMoney(TextView allMoney) {
        this.allMoney = allMoney;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public void setRedpag(int redpag) {
        this.redpag = redpag;
    }

    public EditTextWithDelBug(Context context) {
        super(context);
        mContext = context;
        init();
    }

    public EditTextWithDelBug(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init();
    }

    public EditTextWithDelBug(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init();
    }

    public void setMaxMoney(double maxMoney) {
        this.maxMoney = maxMoney;
    }

    private void init() {
        imgInable = mContext.getResources().getDrawable(R.mipmap.list_delete_icon);
        imgAble = mContext.getResources().getDrawable(R.mipmap.list_delete_icon);
        //事件监听
        myTextWatch = new MyTextWatch();
        setOnFocusChangeListener(new OnFocusChangeListener() {

            private String format;

            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    deleteDrawable();
                    removeTextChangedListener(myTextWatch);
                    if (length() > 0) {
                        setEarnings();
                        if (button != null) {
                            DecimalFormat df = new DecimalFormat("######0.00");
                            if (redMoney != 0) {
                                format = df.format(Double.valueOf(getText().toString().trim()) - redMoney);
                            } else {

                                format = df.format(Double.valueOf(getText().toString().trim()));
                            }
                            button.setText("确认支付" + format + "元");
                        }
                    }
                } else {
                    if (length() > 0) {
                        setDrawable();
                    }
                    addTextChangedListener(myTextWatch);
                }
            }
        });
    }


    public void afterRedMoney() {
         String format;
        if (button != null) {
            DecimalFormat df = new DecimalFormat("######0.00");
            if (redMoney != 0) {
                format = df.format(Double.valueOf(getText().toString().trim()) - redMoney);
            } else {

                format = df.format(Double.valueOf(getText().toString().trim()));
            }
            button.setText("确认支付" + format + "元");
        }
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

    private boolean setMsg(TextView view, String msg) {
        if (view != null) {
            view.setText(msg);
            return true;
        } else {
            return false;
        }
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
                    setMsg(allMoney, "00.00");
                    setMsg(button, "确认支付00.00元");
                    setMsg(textView, "00.00元");
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
            if (!getText().toString().equals("")) {
                if (getText().length() != 0) {
                    if (maxMoney != 0 && maxMoney > 0) {
                        if (Integer.valueOf(getText().toString().trim()) > maxMoney) {
                            String s1 = getText().toString().trim();
                            if (s1.length() >= 2) {
                                s1 = s1.substring(0, s1.length() - 1);
                                setText(s1);
                                ToastUitl.showShort("投标金额不能大于可投金额");
                            } else {
                                setText("");
                                ToastUitl.showShort("投标金额不能大于可投金额");
                            }
                        }
                    }
                    if (getText().toString().trim().contains(".")) {
                        String trim = getText().toString().trim();
                        String substring = trim.substring(trim.lastIndexOf(".") + 1);
                        if (substring.length() > 2) {
                            ToastUitl.showShort("最多只能输入两位小数");
                            setText(trim.substring(0, trim.length() - 1));
                            return;
                        }
                    }
                    setDrawable();
                    DecimalFormat df = new DecimalFormat("######0.00");
                    String input = getText().toString().trim();
                    if (input.lastIndexOf(".") != input.indexOf(".")) {
                        ToastUitl.showShort("请输入正确的格式的充值金额");
                        return;
                    }
                    if (allMoney != null) {
                        if (redpag != 0) {
                            allMoney.setText(df.format(Double.valueOf(input) + redpag + "元"));
                        } else {
                            allMoney.setText(df.format(Double.valueOf(input)) + "元");
                        }

                    }

                    if (button != null) {
                        String format = df.format(Double.valueOf(s.toString().trim()));
                        button.setText("确认支付" + format + "元");
                    }
                    setEarnings();
                }

            } else {
                setMsg(button, "确认支付");
                setMsg(allMoney, "00.00元");
                setMsg(textView, "00.00元");
            }
        }
    }

    public void setAllMoney(double redbag) {
        DecimalFormat df = new DecimalFormat("######0.00");
        this.redpag = redbag;
        allMoney.setText(df.format(Double.valueOf(getText().toString().trim()) + redbag + "元"));
    }

    public void setEarnings() {
        String trim = getText().toString().trim();
        Double money = Double.valueOf(trim);
        if (redpag != 0) {
            money += redpag;
        }
        if (rate != 0) {
            interest += rate;
        }
        if (isNine) {
            getDataMonth(money, date, interest);
        } else {
            getDataMonth(money, date, interest);
        }
    }

    private void Newcounter(double money, int date, double interest) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String result = df.format(money * date * interest / 365).toString();
        textView.setText(result + "元");
        LogUtils.loge(money+"new="+date+"天new:"+interest);

    }

    private void Ninecounter(double money, int date, double interest) {
        double max = Math.pow((1 + interest / 12), date) * money - money;// 月
        // double min = Math.pow((1 + interest / 365), date) * money - money;//天
        String result = AppUtil.reserveTwoDecimalPlacesint(max);
        textView.setText(result + "元");
        LogUtils.loge(money+"nine="+date+"月nine:"+interest);
    }
    /**DurationType 1  天
    /**DurationType 2  月

     RepaymentType  1 先息后本
     RepaymentType  2 等额本息
     RepaymentType  other 一次性还款

     * 一次性还款：（按天）到期收益=年化收益/365*投资期限*本金
     （按月）到期收益=年化收益/12*投资期限*本金
     先息后本：（按天）每月支付的利息=本金×年利率÷365×天数，最后一期还款额=每月支付利息+本金

     （按月）每月支付的利息=本金×年利率÷12，最后一期还款额=每月支付利息+本金
     */
    private void getDataCounter(double money, int date, double interest) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String result = "";
        if (this.repaymentType == 1) {
            // 按天先本后利息   每月支付的利息=本金×年利率÷365×天数，最后一期还款额=每月支付利息+本金
            result=df.format(money*interest/365*date).toString();

        } else if (this.repaymentType == 2) {
            // 按天等额本息
//            result=df.format(money*interest/365*date).toString();
        } else {
            // 按天一次性还款  到期收益=年化收益/365*投资期限*本金
            result= df.format(interest/365*date*money).toString();
        }
        textView.setText(result + "元");

    }
    private void getMonthCounter(double money, int date, double interest) {

        DecimalFormat df = new DecimalFormat("#0.00");
        String result = "";
        if (this.repaymentType == 1) {
//            先息后本 （按月）每月支付的利息=本金×年利率÷12，最后一期还款额=每月支付利息+本金
            result = df.format(money*interest/12*date).toString();
        } else if (this.repaymentType == 2) {
            // 按月等额本息  每月还款额=[本金*月利率*（1+月利率）^还款月数]/[(1+月利率)^还款月数-1]
            result = df.format(Math.pow((money*interest*(1+interest)),date)/(Math.pow((1+interest),date-1))-money).toString();
        } else {
//            一次性还款  （按月）到期收益=年化收益/12*投资期限*本金
            result = df.format(interest/12*date*money).toString();
        }
        textView.setText(result + "元");

    }
    private void getDataMonth(double money, int date, double interest) {
        if (this.DurationType == 1) {
            //天
            getDataCounter(money,date,interest);
        } else if (this.DurationType ==2){
            //月
            getMonthCounter(money,date,interest);
        }

    }
}
