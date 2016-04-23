package com.jiguang.push;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by 26071 on 2016/4/23.
 */
public class MyReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle;
        // 接受自定义消息时的动作
        if (intent.getAction().equals(JPushInterface.ACTION_MESSAGE_RECEIVED))
        {
            bundle = intent.getExtras();
            String title = bundle.getString(JPushInterface.EXTRA_TITLE);
            String message = bundle.getString(JPushInterface.EXTRA_MESSAGE);
            Toast.makeText(context , title + ":" + message , Toast.LENGTH_SHORT).show();
        }

        //接受通知栏消息时的动作
        if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_RECEIVED))
        {
            bundle  = intent.getExtras();
            String t = bundle.getString(JPushInterface.EXTRA_NOTIFICATION_TITLE);
            String s = bundle.getString(JPushInterface.EXTRA_ALERT);
            Toast.makeText(context , s , Toast.LENGTH_SHORT).show();
        }
        // 打开推送时的动作
        if (intent.getAction().equals(JPushInterface.ACTION_NOTIFICATION_OPENED))
        {
            // 设置打开应用
            if(isAppAlive(context, "com.jiguang.push"))
            {
                Intent mainIntent = new Intent(context , MainActivity.class);
                mainIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(mainIntent);
                Toast.makeText(context , "应用正在运行" , Toast.LENGTH_SHORT).show();
            }
            else {
                Intent launchIntent = context.getPackageManager().
                        getLaunchIntentForPackage("com.jiguang.push");
                launchIntent.setFlags(
                        Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_RESET_TASK_IF_NEEDED);
                context.startActivity(launchIntent);
                Toast.makeText(context, "打开推送", Toast.LENGTH_SHORT).show();
            }
        }

    }

    /**
     * 判断进程是否处于活动状态
     * @param context
     * @param packageName
     * @return
     */
    private boolean isAppAlive(Context context , String packageName)
    {
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningAppProcessInfo> appProcessInfos = manager.getRunningAppProcesses();
        for (int i = 0 ; i < appProcessInfos.size() ; i++)
        {
            if (appProcessInfos.get(i).processName.equals(packageName))
            {
                return true;
            }
        }

        return false;
    }
}
