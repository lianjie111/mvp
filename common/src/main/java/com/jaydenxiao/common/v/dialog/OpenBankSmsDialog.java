package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.R;

/**
 * Created by dell on 2017/6/21.
 */

public class OpenBankSmsDialog extends Dialog {
    public OpenBankSmsDialog(@NonNull Context context) {
        super(context);
    }

    public OpenBankSmsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;

        private String phone;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder(Context context, String phone) {
            this.context = context;
            this.phone = phone;
        }

        private TextView phoneTv;

        private EditText picEt;

        private EditText smsEt;

        private ImageView picIv;

        private TextView smsTv;

        private Button btn;


        private OnClickListener picOnClickListener;

        private OnClickListener smsOnClickListener;

        private OnClickListener btnOnClickListener;

        public Builder setPhone(String s) {
            if (phoneTv != null) {
                phoneTv.setText(s);
            }
            return this;
        }

        public String getPiccode() {
            return picEt.getText().toString().trim();
        }

        public String getSmsCode() {
            return smsEt.getText().toString().trim();
        }

        public void setPicIv(Bitmap bitmap) {
            if (picIv != null) {
                picIv.setImageBitmap(bitmap);
            }
        }

        public void setSmsTv(String s) {
            if (smsTv != null) {

                smsTv.setText(s);
            }

        }

        public Builder setPicOnClickListener(OnClickListener picOnClickListener) {
            this.picOnClickListener = picOnClickListener;
            return this;
        }

        public Builder setSmsOnClickListener(OnClickListener smsOnClickListener) {
            this.smsOnClickListener = smsOnClickListener;
            return this;
        }

        public Builder setBtnOnClickListener(OnClickListener btnOnClickListener) {
            this.btnOnClickListener = btnOnClickListener;
            return this;
        }

        public OpenBankSmsDialog create() {
            final OpenBankSmsDialog dialog = new OpenBankSmsDialog(context, R.style.MyDialog1);
            View view = LayoutInflater.from(context).inflate(R.layout.open_bankcard_dialog, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


            dialog.addContentView(view, params);
            picIv = (ImageView) view.findViewById(R.id.pic_iv);
            picEt = (EditText) view.findViewById(R.id.pic_code_et);
            phoneTv = (TextView) view.findViewById(R.id.phone_tv);
            smsTv = (TextView) view.findViewById(R.id.getsms_tv);
            smsEt = (EditText) view.findViewById(R.id.sms_code_et);
            btn = (Button) view.findViewById(R.id.code_dialog_btn);
            phoneTv.setText(phone);
            picIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (picOnClickListener != null) {
                        picOnClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                    }
                }
            });

            smsTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (smsOnClickListener != null) {

                        smsOnClickListener.onClick(dialog,DialogInterface.BUTTON_NEUTRAL);

                    }
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnOnClickListener != null) {

                        btnOnClickListener.onClick(dialog,DialogInterface.BUTTON_POSITIVE);
                    }
                }
            });
            dialog.setContentView(view);
            return dialog;
        }

    }


}
