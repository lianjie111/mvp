package com.jaydenxiao.common.permission;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;

/**
 * Created by jmm on 2016/11/10.
 */

public class PermissionManager {
    /**
     * 弹出权限对话框
     */
    public static boolean checkPermission(Context context, String permission, int requestCode) {
        // 判断运行sdk版本是否大于等于23
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            // 判断是否拥有读内存卡权限（不管manifest里是否注册）
            if (ContextCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                // 申请READ_EXTERNAL_STORAGE权限，等待onRequestPermissionsResult回调
                ((Activity) context).requestPermissions(new String[]{permission}, requestCode);
                return false;
            }
        }
        return true;
    }

    /**
     * 弹出权限对话框
     */
    private static BaseDialog permissionDialog;
    public static void showPermissionDialog(final Context context, String permission) {
        permissionDialog = new BaseDialog(context);
        permissionDialog.setTitle("提示");
        permissionDialog.setMessage("需要赋予\n" + permission + "\n权限!\n不开启将无法正常工作!\n去开启?");
        permissionDialog.setOKListener("确定", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAppSetting(context);
                permissionDialog.dismiss();
            }
        });
        permissionDialog.setNOListener("取消", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                permissionDialog.dismiss();
            }
        });
        permissionDialog.show();
    }

    /**
     * 跳转到应用设置页面
     */
    public static void openAppSetting(Context context) {
        Intent localIntent = new Intent();
        localIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        if (Build.VERSION.SDK_INT >= 9) {
            localIntent.setAction("android.settings.APPLICATION_DETAILS_SETTINGS");
            localIntent.setData(Uri.fromParts("package", context.getPackageName(), null));
        } else if (Build.VERSION.SDK_INT <= 8) {
            localIntent.setAction(Intent.ACTION_VIEW);
            localIntent.setClassName("com.android.settings", "com.android.settings.InstalledAppDetails");
            localIntent.putExtra("com.android.settings.ApplicationPkgName", context.getPackageName());
        }
        context.startActivity(localIntent);
    }

}
