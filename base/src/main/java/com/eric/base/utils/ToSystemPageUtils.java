package com.eric.base.utils;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

public class ToSystemPageUtils {

    public static void toChrame(Activity activity,String url) {
        Intent intent = new Intent();
        intent.setAction("android.intent.action.VIEW");
        Uri content_url = Uri.parse(url);
        intent.setData(content_url);
//        intent.setDataAndType(content_url,"text/html");  // TODO: 2019/5/29  跳转系统默认浏览器（可能会找不到）
//        intent.addCategory(Intent.CATEGORY_BROWSABLE);
//        intent.setClassName("com.android.browser", "com.android.browser.BrowserActivity");
        activity.startActivity(intent);




    }
}
