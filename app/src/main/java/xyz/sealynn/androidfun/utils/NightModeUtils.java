package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import xyz.sealynn.androidfun.base.Constants;
import xyz.sealynn.androidfun.module.AppCompatPreferenceActivity;

/**
 * Created by SeaLynn0 on 2018/10/13 11:52
 * <p>
 * Email：sealynndev@gmail.com
 */
public final class NightModeUtils {

    private static final String NIGHT_MODE_PREF = "night_mode";

    private NightModeUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static int getNightModeState(Context context) {
        SharedPreferences pref = context.getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE);
        return pref.getInt(NIGHT_MODE_PREF, AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static void setNightModeState(AppCompatActivity activity, int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
//        SharedPreferencesUtils.editDefaultConfig(activity, NIGHT_MODE_PREF, mode);
        activity.getApplicationContext()
                .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .edit()
                .putInt(NIGHT_MODE_PREF, mode)
                .apply();
        activity.recreate();
    }

    public static void setNightModeState(AppCompatPreferenceActivity activity, int mode) {
        AppCompatDelegate.setDefaultNightMode(mode);
//        SharedPreferencesUtils.editDefaultConfig(activity, NIGHT_MODE_PREF, mode);
        activity.getApplicationContext()
                .getSharedPreferences(Constants.CONFIG_DEFAULT, Context.MODE_PRIVATE)
                .edit()
                .putInt(NIGHT_MODE_PREF, mode)
                .apply();
        activity.recreate();
    }
}
