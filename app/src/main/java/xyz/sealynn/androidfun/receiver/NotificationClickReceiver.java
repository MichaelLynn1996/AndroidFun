package xyz.sealynn.androidfun.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import xyz.sealynn.androidfun.module.year.YearProgressActivity;

/**
 * Created by SeaLynn0 on 2019/1/16 1:32
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NotificationClickReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent newIntent = new Intent(context, YearProgressActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(newIntent);
    }
}
