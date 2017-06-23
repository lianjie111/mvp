package com.jaydenxiao.common.commonutils;

import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

/**
 * Created by dell on 2017/6/23.
 */

public class TextViewUtils {
    public static void setTextHightLight(TextView view, String text, int color, View.OnClickListener onClickListener) {
        String allText = view.getText().toString().trim();
        if (allText == null || text.isEmpty()) {
            Log.i("TextViewUtils", "textview data isempty");
            return;
        }
        if (!allText.contains(text)) {
            Log.i("TextViewUtils", "textview do not contains text");
            return;
        }
        int startIndex = allText.indexOf(text);
        int endIndex = startIndex + text.length();
        SpannableStringBuilder builder = new SpannableStringBuilder(allText);
        //改变颜色
        builder.setSpan(new ForegroundColorSpan(color), startIndex, endIndex, Spanned.SPAN_COMPOSING);
//        点击事件
        builder.setSpan(new ClickSpannable(onClickListener), startIndex, endIndex, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        view.setMovementMethod(LinkMovementMethod.getInstance());

        view.setText(builder);
    }

    private static class ClickSpannable extends ClickableSpan implements View.OnClickListener {

        private View.OnClickListener onClickListener;

        public ClickSpannable(View.OnClickListener onClickListener) {
            this.onClickListener = onClickListener;
        }

        @Override
        public void onClick(View widget) {
            onClickListener.onClick(widget);
        }
    }
}
