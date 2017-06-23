package com.jaydenxiao.common.commonutils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by Administrator on 2016/12/20.
 */

public class BaseIntent extends Intent {
    public BaseIntent() {
    }

    public BaseIntent(Intent o) {
        super(o);
    }

    public BaseIntent(String action) {
        super(action);
    }

    public BaseIntent(String action, Uri uri) {
        super(action, uri);
    }

    public BaseIntent(Context packageContext, Class<?> cls) {
        super(packageContext, cls);
    }

    public BaseIntent(String action, Uri uri, Context packageContext, Class<?> cls) {
        super(action, uri, packageContext, cls);
    }
}
