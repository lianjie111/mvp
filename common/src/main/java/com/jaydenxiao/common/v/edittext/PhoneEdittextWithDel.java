package com.jaydenxiao.common.v.edittext;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by dell on 2017/6/21.
 */

public class PhoneEdittextWithDel extends EdittextDel{
    public PhoneEdittextWithDel(Context context) {
        super(context);
        init();
    }

    public PhoneEdittextWithDel(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public PhoneEdittextWithDel(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        final MyTextWatch myTextWatch = new MyTextWatch();
        setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    deleteDrawable();
                    removeTextChangedListener(myTextWatch);
//                    if (getText() != null) {
//                        DecimalFormat df = new DecimalFormat("######0.00");
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

    class MyTextWatch implements TextWatcher {
        private int oldL;
        private int newL;

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            oldL = s.length();
            if (length() < 1) {
                deleteDrawable();
            }
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            String text = s.toString();
            if (!"".equals(text) && text != null) {
                setDrawable();
                newL = text.length();
                if (newL > oldL) {

                    if (text.length() == 3) {
                        text += " ";
                        setText(text);
                        setSelection(text.length());
                    }
                    if (text.length() == 8) {
                        text += " ";
                        setText(text);
                        setSelection(text.length());
                    }

                }
            }
        }
    }
}
