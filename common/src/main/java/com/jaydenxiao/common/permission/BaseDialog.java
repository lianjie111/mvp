package com.jaydenxiao.common.permission;

import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.jaydenxiao.common.R;


/**
 * Created by jmm on 2016/11/3.
 */

public class BaseDialog extends Dialog {
    private final TextView titleTV;
    private final TextView messageTV;
    private final TextView okBN;
    private final TextView noBN;

    public BaseDialog(Context context) {
        super(context, R.style.MyDialog);
        setContentView(R.layout.dialog_base);

        titleTV = (TextView) findViewById(R.id.dialog_base_tv_title);
        messageTV = (TextView) findViewById(R.id.dialog_base_tv_message);
        okBN = (TextView) findViewById(R.id.dialog_base_tv_ok);
        noBN = (TextView) findViewById(R.id.dialog_base_tv_no);
    }

    public void setTitle(String s) {
        titleTV.setText(s);
    }

    public void setMessage(String s) {
        messageTV.setText(s);
    }

    public void setOKListener(String s, View.OnClickListener listener) {
        okBN.setText(s);
        okBN.setOnClickListener(listener);
    }

    public void setNOListener(String s, View.OnClickListener listener) {
        noBN.setText(s);
        noBN.setOnClickListener(listener);
    }

}
