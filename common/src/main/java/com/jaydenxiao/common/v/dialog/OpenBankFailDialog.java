package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.R;

/**
 * Created by dell on 2017/6/21.
 */

public class OpenBankFailDialog extends Dialog {
    public OpenBankFailDialog(@NonNull Context context) {
        super(context);
    }

    public OpenBankFailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;

        public Builder(Context context) {
            this.context = context;
        }

        private String message;
        private TextView msgTv;
        private OnClickListener closeOnClickListener;

        private OnClickListener btnOnClickListener;

        private ImageView closeIv;

        private Button btn;


        public Builder setMessage(String message) {
            this.message = message;
            if (msgTv != null) {
                msgTv.setText(message);
            }
            return this;
        }

        public Builder setCloseOnClickListener(OnClickListener closeOnClickListener) {
            this.closeOnClickListener = closeOnClickListener;
            return this;
        }

        public Builder setBtnOnClickListener(OnClickListener btnOnClickListener) {
            this.btnOnClickListener = btnOnClickListener;
            return this;
        }

        public OpenBankFailDialog create() {
            final OpenBankFailDialog dialog = new OpenBankFailDialog(context, R.style.MyDialog1);
            View view = LayoutInflater.from(context).inflate(R.layout.open_bank_fail_dialog, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.addContentView(view, params);
            closeIv = (ImageView) view.findViewById(R.id.close_iv);

            msgTv = (TextView) view.findViewById(R.id.becase_tv);

            btn = (Button) view.findViewById(R.id.code_dialog_btn);

            closeIv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (closeOnClickListener != null) {
                        closeOnClickListener.onClick(dialog, Dialog.BUTTON_NEGATIVE);
                    }
                }
            });

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (btnOnClickListener != null) {
                        closeOnClickListener.onClick(dialog, Dialog.BUTTON_POSITIVE);
                    }
                }
            });
            dialog.setContentView(view);
            return dialog;
        }

    }

}
