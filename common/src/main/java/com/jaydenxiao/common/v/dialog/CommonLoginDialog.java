package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.support.design.widget.TextInputLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.jaydenxiao.common.R;

/**
 * Created by Administrator on 2017/2/22.
 */

public class CommonLoginDialog extends Dialog {

    public CommonLoginDialog(Context context) {
        super(context);
    }

    public CommonLoginDialog(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private OnClickListener ivClickListener;
        private OnClickListener btnClickListener;
        private TextInputLayout inputLayout;
        private EditText editText;
        private ImageView imageView;

        public String getCode() {
            return editText.getText().toString().trim();
        }

        public TextInputLayout getTextInputLayout() {
            return inputLayout;
        }

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setIvClick(OnClickListener onClickListener) {
            ivClickListener = onClickListener;
            return this;
        }

        public Builder setBtnClick(OnClickListener btnClickListener) {
            this.btnClickListener = btnClickListener;
            return this;
        }

        public Builder setBitmap(Bitmap bitmap) {
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
            }
            return this;
        }


        public CommonLoginDialog create() {
            LayoutInflater inflater = LayoutInflater.from(context);
            final CommonLoginDialog dialog = new CommonLoginDialog(context, R.style.MyDialog1);
            View view = inflater.inflate(R.layout.common_code_dialog, null);
            RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT);
            dialog.addContentView(view, params);
            EditText text = (EditText) view.findViewById(R.id.code_dialog_et);
            this.editText = text;
            this.inputLayout = (TextInputLayout) view.findViewById(R.id.code_dialog_til);
            imageView = (ImageView) view.findViewById(R.id.code_dialog_iv);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ivClickListener.onClick(dialog, DialogInterface.BUTTON_NEGATIVE);
                }
            });
            Button button = (Button) view.findViewById(R.id.code_dialog_btn);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    btnClickListener.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
            });
            dialog.setContentView(view);
            return dialog;
        }
    }

}
