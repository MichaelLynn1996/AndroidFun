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

    private NightModeUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int getNightModeState(Context context) {
        SharedPreferences pref = context.getSharedPreferences("config", Context.MODE_PRIVATE);
        return pref.getInt("night_mode", AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void setNightModeState(AppCompatActivity activity, int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
        SharedPreferencesUtils.editDefaultConfig(activity, "night_mode", mode);
        activity.recreate();
    }
}
