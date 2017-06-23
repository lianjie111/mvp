package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.jaydenxiao.common.R;

/**
 * author:lengxue
 * date:2017/4/20 11:01
 * description:简单对话框
 */

public class MyTipDialog extends Dialog {

    private Button okBN;
    private Button cancelBN;
    private TextView titleTV;
    private TextView messageTV;

    public MyTipDialog(Context context) {
//		super(context);
        super(context, R.style.MyDialog1);
        setContentView(R.layout.mytipsdialog);


        okBN = (Button)findViewById(R.id.mydialog_btn_confirm);
        cancelBN = (Button)findViewById(R.id.mydialog_btn_cancel);
        titleTV = (TextView)findViewById(R.id.mydialog_title);
        messageTV = (TextView)findViewById(R.id.mydialog_content);

    }

    public MyTipDialog(Context context, int themeResId) {
        super(context, themeResId);
    }




    public void setTitle(String title){
        titleTV.setText(title);
    }

    public void setMessage(String message){
        messageTV.setText(message);
    }

    public void setOkButton(String name, View.OnClickListener listener){
        okBN.setText(name);
        okBN.setOnClickListener(listener);
    }

    public void setCancelButton(String name, View.OnClickListener listener){
        if (TextUtils.isEmpty(name)) {
            cancelBN.setVisibility(View.GONE);
        }else {
            cancelBN.setVisibility(View.VISIBLE);
            cancelBN.setText(name);
            cancelBN.setOnClickListener(listener);
        }
    }



}

