package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SeaLynn0 on 2018/10/13 12:04
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SharedPreferencesUtils {

    private static final String DEFAULT_CONFIG = "config";

    private SharedPreferencesUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

//    public static void editDefaultConfig(Context context,String key,Boolean value){
//        SharedPreferences.Editor editor = context.getSharedPreferences("config",Context.MODE_PRIVATE).edit();
//        editor.putBoolean(key,value);
//        editor.apply();
//    }
//
//    public static void editDefaultConfig(Context context,String key,int value){
//        SharedPreferences.Editor editor = context.getSharedPreferences("config",Context.MODE_PRIVATE).edit();
//        editor.putInt(key,value);
//        editor.apply();
//    }

    public static void editDefaultConfig(Context context, String key, Object value) {
        SharedPreferences.Editor editor = context.getSharedPreferences(DEFAULT_CONFIG, Context.MODE_PRIVATE).edit();
//        editor.putInt(key,value);
        if (value instanceof String) {
            editor.putString(key, (String) value);
        } else if (value instanceof Integer) {
            editor.putInt(key, (Integer) value);
        } else if (value instanceof Boolean) {
            editor.putBoolean(key, (Boolean) value);
        } else if (value instanceof Float) {
            editor.putFloat(key, (Float) value);
        } else if (value instanceof Long) {
            editor.putLong(key, (Long) value);
        } else {
            editor.putString(key, value.toString());
        }
//        editor.commit();
        editor.apply();
    }

    /**
     * 获取保存的数据
     */
    public static Object getDefaultConfig(Context context, String key, Object defaultObj) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(DEFAULT_CONFIG,
                Context.MODE_PRIVATE);
        if (defaultObj instanceof String) {
            return sharedPreferences.getString(key, (String) defaultObj);
        } else if (defaultObj instanceof Integer) {
            return sharedPreferences.getInt(key, (Integer) defaultObj);
        } else if (defaultObj instanceof Boolean) {
            return sharedPreferences.getBoolean(key, (Boolean) defaultObj);
        } else if (defaultObj instanceof Float) {
            return sharedPreferences.getFloat(key, (Float) defaultObj);
        } else if (defaultObj instanceof Long) {
            return sharedPreferences.getLong(key, (Long) defaultObj);
        } else {
            return sharedPreferences.getString(key, null);
        }
    }
}
