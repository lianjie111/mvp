package com.jaydenxiao.common.commonutils;

import android.os.CountDownTimer;
import android.widget.TextView;

/**
 * Created by dell on 2017/6/19.
 */

public class TimeCountUtils extends CountDownTimer {

    private TextView textView;


    /**
     * @param millisInFuture    The number of millis in the future from the call
     *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
     *                          is called.
     * @param countDownInterval The interval along the way to receive
     *                          {@link #onTick(long)} callbacks.
     */
    public TimeCountUtils(long millisInFuture, long countDownInterval, TextView textView) {
        super(millisInFuture, countDownInterval);
        this.textView = textView;
    }

    @Override
    public void onTick(long millisUntilFinished) {
        textView.setClickable(false);
        textView.setText(millisUntilFinished / 1000 + "秒");
    }

    @Override
    public void onFinish() {

        textView.setText("重新发送");
        textView.setClickable(true);
    }

    public void reset() {
        cancel();
        textView.setText("重新验证");
        textView.setClickable(true);
    }
}
