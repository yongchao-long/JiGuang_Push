package com.jiguang.push;

import android.app.Application;

import cn.jpush.android.api.CustomPushNotificationBuilder;
import cn.jpush.android.api.JPushInterface;
import cn.jpush.android.data.JPushLocalNotification;
import cn.jpush.android.data.JPushView;

/**
 * Created by 26071 on 2016/4/23.
 */
public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        JPushInterface.setDebugMode(true);
        JPushInterface.init(this);

        /*
        // 指定定制的 Notification Layout
        CustomPushNotificationBuilder(MainActivity.this,
                R.layout.customer_notitfication_layout,
                R.id.icon,
                R.id.title,
                R.id.text);
        // 指定最顶层状态栏小图标
        builder.statusBarDrawable = R.drawable.your_notification_icon;

        // 指定下拉状态栏时显示的通知图标
        builder.layoutIconDrawable = R.drawable.your_2_notification_icon;

        JPushInterface.setPushNotificationBuilder(2, builder);
        */

    }
}
