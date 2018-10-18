package xyz.sealynn.androidfun;

import android.app.Application;
import android.content.Context;
import androidx.appcompat.app.AppCompatDelegate;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import xyz.sealynn.androidfun.utils.NightModeUtils;

/**
 * Created by SeaLynn0 on 2018/8/28 19:56
 * <p>
 * Email：sealynndev@gmail.com
 */
public class APP extends Application {

    private static Context appContext;
    private static long exitTime = 0;

    /**
     * 获取Application的Context
     *
     * @return 全局Context
     */
    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Logger.addLogAdapter(new AndroidLogAdapter());
        appContext = getApplicationContext();
        if (NightModeUtils.getNightModeState(getAppContext())){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }
    }

    /**
     * 退出APP
     */
    public static void exitApp() {
//        if (System.currentTimeMillis() - exitTime > 2000) {
//            ToastUtils.shortToast(getAppContext(), appContext.getString(R.string.text_press_again));
//            exitTime = System.currentTimeMillis();
//        } else {
//            android.os.Process.killProcess(android.os.Process.myPid());
//        }
        android.os.Process.killProcess(android.os.Process.myPid());
    }
}
