package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

/**
 * Created by SeaLynn0 on 2018/10/13 11:52
 * <p>
 * Email：sealynndev@gmail.com
 */
public class NightModeUtils {

    private NightModeUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static Boolean getNightModeState(Context context) {
        SharedPreferences pref = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return pref.getBoolean("night_mode", false);
    }

    public static void setNightModeOff(AppCompatActivity activity) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        SharedPreferencesUtils.editDefaultConfig(activity,"night_mode",false);
        activity.recreate();
    }

    public static void setNightModeOn(AppCompatActivity activity) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        SharedPreferencesUtils.editDefaultConfig(activity,"night_mode",true);
        activity.recreate();
    }
}
