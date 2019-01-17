package xyz.sealynn.androidfun.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by SeaLynn0 on 2018/12/5 2:58
 * <p>
 * Email：sealynndev@gmail.com
 */
public class DebugUtils {

    private DebugUtils(){
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断当前应用是否是debug状态
     */
    public static boolean isApkInDebug(Context context) {
        try {
            ApplicationInfo info = context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE) != 0;
        } catch (Exception e) {
            return false;
        }
    }
}
