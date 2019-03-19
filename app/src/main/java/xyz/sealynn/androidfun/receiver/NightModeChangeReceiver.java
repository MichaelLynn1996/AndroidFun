package xyz.sealynn.androidfun.receiver;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.orhanobut.logger.Logger;

/**
 * Created by SeaLynn0 on 2019/1/17 13:31
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NightModeChangeReceiver extends BroadcastReceiver {

    Activity activity;

    public NightModeChangeReceiver(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        activity.recreate();
        Logger.d("receive");
    }
}
