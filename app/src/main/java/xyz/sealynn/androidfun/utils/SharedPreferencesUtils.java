package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by SeaLynn0 on 2018/10/13 12:04
 * <p>
 * Email：sealynndev@gmail.com
 */
public class SharedPreferencesUtils {

    private SharedPreferencesUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    public static void editDefaultConfig(Context context,String key,Boolean value){
        SharedPreferences.Editor editor = context.getSharedPreferences("config",Context.MODE_PRIVATE).edit();
        editor.putBoolean(key,value);
        editor.apply();
    }
}
