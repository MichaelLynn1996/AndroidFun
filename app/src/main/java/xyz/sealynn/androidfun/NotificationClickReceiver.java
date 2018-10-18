package xyz.sealynn.androidfun;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import xyz.sealynn.androidfun.module.year.YearProgressActivity;

/**
 * Created by SeaLynn0 on 2018/10/14 1:53
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NotificationClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        //todo 跳转之前要处理的逻辑
        Intent newIntent = new Intent(context, YearProgressActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
