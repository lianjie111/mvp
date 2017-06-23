package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.jaydenxiao.common.R;

/**
 * Created by Administrator on 2017/4/13.
 */

public class TenderDetailDialog extends Dialog {
    public TenderDetailDialog(@NonNull Context context) {
        super(context);
    }

    public TenderDetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private OnClickListener cancal;
        private OnClickListener confirm;

        public Builder(Context context) {
            this.context = context;
        }

        public OnClickListener getCancal() {
            return cancal;
        }

        public void setCancal(OnClickListener cancal) {
            this.cancal = cancal;
        }

        public OnClickListener getConfirm() {
            return confirm;
        }

        public void setConfirm(OnClickListener confirm) {
            this.confirm = confirm;
        }

        public Builder(Context context, OnClickListener cancal, OnClickListener confirm) {
            this.context = context;
            this.cancal = cancal;
            this.confirm = confirm;
        }

        public TenderDetailDialog creater() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final TenderDetailDialog dialog = new TenderDetailDialog(context, R.style.MyDialog1);
            View view = inflater.inflate(R.layout.tenderdialog, null);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.addContentView(view, params);
            Button confirm1 = (Button) view.findViewById(R.id.dialog_btn_confirm);
            Button cancal1 = (Button) view.findViewById(R.id.dialog_btn_cancel);
            LinearLayout layout = (LinearLayout) view.findViewById(R.id.dialog_ll);
            confirm1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    confirm.onClick(dialog, DialogInterface.BUTTON_POSITIVE);

                }
            });
            cancal1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cancal.onClick(dialog,DialogInterface.BUTTON_NEGATIVE);
                }
            });
            return dialog;
        }
    }
}
