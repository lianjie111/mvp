package com.jaydenxiao.common.v.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.R;

/**
 * Created by dell on 2017/5/17.
 */

public class CommonRegisterFinishDialog extends Dialog{
    public CommonRegisterFinishDialog(@NonNull Context context) {
        super(context);
    }

    public CommonRegisterFinishDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    public static class Builder {
        private Context context;
        private String titles;
        private String baseUrl;
        private String html;
        private String buttonMessage;
        private OnClickListener onClickLister;
        private OnClickListener disagreeOnclickLister;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setTitle(String title) {
            this.titles = title;
            return this;
        }

        public Builder setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder setHtml(String html) {
            this.html = html;
            return this;
        }

        public Builder setOnClickLister(OnClickListener onClickLister) {
            this.onClickLister = onClickLister;
            return this;
        }
        public Builder setOnClickLister(String message,OnClickListener onClickLister) {
            buttonMessage = message;
            this.onClickLister = onClickLister;
            return this;
        }

        public Builder setDisagreeOnclickLister(String message,OnClickListener disagreeOnclickLister) {
            buttonMessage = message;
            this.disagreeOnclickLister = disagreeOnclickLister;
            return this;
        }

        public CommonRegisterFinishDialog create() {
            View inflate = LayoutInflater.from(context).inflate(R.layout.common_register_finish_dialog, null);
            final CommonRegisterFinishDialog dialog = new CommonRegisterFinishDialog(context, R.style.MyDialog1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            dialog.addContentView(inflate, params);
            TextView title = (TextView) inflate.findViewById(R.id.title);
            WebView webView = (WebView) inflate.findViewById(R.id.wv);
            Button button = (Button) inflate.findViewById(R.id.button);
            if (buttonMessage != null) {
                button.setText(buttonMessage);
            }
            if (!"".equals(titles)) {
                title.setText(titles);
            }
            webView.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
            webView.getSettings().setJavaScriptEnabled(true);
            if (!"".equals(baseUrl) && !"".equals(html)) {
                webView.loadDataWithBaseURL(baseUrl,html, "text/html", "UTF-8", null);
                webView.setWebViewClient(new WebViewClient() {
                    @Override
                    public boolean shouldOverrideUrlLoading(WebView view, String url) {
                        // 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                        view.loadUrl(url);
                        return true;
                    }
                });
            }
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onClickLister.onClick(dialog, DialogInterface.BUTTON_POSITIVE);
                }
            });

            return dialog;
        }
    }
}
